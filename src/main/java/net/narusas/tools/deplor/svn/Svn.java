package net.narusas.tools.deplor.svn;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNCopySource;
import org.tmatesoft.svn.core.wc.SVNDiffClient;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

@Slf4j
public class Svn {
	public SVNRepository		repository;
	private final String		repositoryUrl;
	private final String		userName;
	private final String		password;
	private SVNClientManager	svnClientManager;

	public Svn(String url, String name, String password) {
		this.repositoryUrl = url;
		log.info("Repository URL: {}", repositoryUrl);
		this.userName = name;
		this.password = password;
		log.info("Working Account: {}", userName);
		init(url);
	}

	private void init(String url) {
		try {
			SVNRepositoryFactoryImpl.setup();
			repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));
			ISVNAuthenticationManager authManager = SVNWCUtil
					.createDefaultAuthenticationManager(this.userName, this.password.toCharArray());
			repository.setAuthenticationManager(authManager);
			svnClientManager = SVNClientManager.newInstance(new DefaultSVNOptions(), this.userName, this.password);
		} catch (SVNException e) {
			throw new MalformedSvnURLException(e);
		}
	}

	/**
	 * 해당 디렉토라안의 엔트리 목록을 리스트업 한다.
	 * 
	 * @param path
	 * @param isRecursive
	 * @throws SVNException
	 */
	public void listEntries(String path, boolean isRecursive) throws SVNException {
		Collection entries = repository.getDir(path, -1, null, (Collection) null);
		Iterator iterator = entries.iterator();
		while (iterator.hasNext()) {
			SVNDirEntry entry = (SVNDirEntry) iterator.next();
			System.out.println("/" + (path.equals("") ? "" : path + "/") + entry.getName() + " ( author: '" + entry.getAuthor()
					+ "'; revision: " + entry.getRevision() + "; date: " + entry.getDate() + ")");

		}
	}

	public void merge(String relativePath, String fromPath, long fromRevisionNo, String toPath, long toRevisionNo) throws SVNException,
			IOException {
		System.out.println(fromPath);
		System.out.println(toPath);
		SVNClientManager ourClientManager = SVNClientManager.newInstance(new DefaultSVNOptions(), userName, password);

		SVNURL fromUrl = SVNURL.parseURIDecoded(repositoryUrl + "/" + fromPath);
		SVNRevision fromRevision = SVNRevision.create(fromRevisionNo);
		System.out.println(fromUrl);
		System.out.println(fromRevision);

		SVNURL toUrl = SVNURL.parseURIDecoded(repositoryUrl + "/" + toPath);
		SVNRevision toRevision = SVNRevision.HEAD;// create(toRevisionNo);
		System.out.println(toUrl);
		System.out.println(toRevisionNo);

		// File wcRoot = File.createTempFile("branchmerge", ".tmp");
		File wcRoot = File.createTempFile("branchmerge", ".tmp");
		if (wcRoot.exists()) {
			wcRoot.delete();
		}

		wcRoot.deleteOnExit();
		wcRoot.mkdirs();
		checkOutWorkingCopy(toUrl, wcRoot, toRevision);
		// SVNRevision toRevision = SVNRevision.create(toRevisionNo);

		SVNDiffClient diff = ourClientManager.getDiffClient();
		File mergeTarget = new File(wcRoot, relativePath.substring(relativePath.lastIndexOf("/")));
		System.out.println("######## " + mergeTarget);
		diff.doMerge(fromUrl, fromRevision, toUrl, toRevision, mergeTarget, SVNDepth.UNKNOWN, false, false, false, false);
		ourClientManager.getCommitClient().doCommit(new File[] { new File(wcRoot, relativePath.substring(relativePath.lastIndexOf("/"))) },
				false, "merge", null, null, false, true, SVNDepth.IMMEDIATES);

		// ourClientManager.getCommitClient().doCommit(new File[] { new
		// File(wcRoot, relativePath.substring(relativePath.lastIndexOf("/")))
		// },
		// false, "merged", true, false);
	}

	public void checkOutWorkingCopy(SVNURL url, File wcRoot, SVNRevision revision) throws SVNException {
		System.out.println("## checkOutWorkingCopy:" + url);
		SVNUpdateClient updateClient = svnClientManager.getUpdateClient();
		url = url.removePathTail();
		System.out.println("###### User:" + userName);
		System.out.println("###### Password:" + password);
		updateClient.doCheckout(url, wcRoot, revision, SVNRevision.HEAD, SVNDepth.IMMEDIATES, false);

	}

	public void listEntries(String path) throws SVNException {
		listEntries(path, false);
	}

	public List<String> listBranches() throws SVNException {
		Collection<SVNDirEntry> temp = repository.getDir("branches", -1, null, (Collection) null);
		List<String> branches = new ArrayList<String>();
		branches.add("trunk");
		Iterator iterator = temp.iterator();
		while (iterator.hasNext()) {
			SVNDirEntry entry = (SVNDirEntry) iterator.next();
			branches.add(entry.getPath());
		}
		return branches;
	}

	public boolean exists(String path, long revision) throws SVNException {
		SVNException ex = null;
		for (int i = 0; i < 3; i++) {
			try {
				SVNNodeKind nodeKind = repository.checkPath(path, revision);
				return (nodeKind == SVNNodeKind.NONE) == false;
			} catch (org.tmatesoft.svn.core.SVNException e) {
				ex = e;
				e.printStackTrace();
			}
		}

		throw ex;
	}

	public SVNFile getFile(String path, long revision) throws SVNException, FileNotFoundException {
		System.out.println("######### getFile:" + path);
		// if (exists(path, revision) == false) {
		// throw new FileNotFoundException(path);
		// }
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		long remoteRevision = repository.getFile(path, revision, null, out);

		return new SVNFile(remoteRevision, out.toByteArray());
	}

	public SVNFile getFile(String branch, WorkingResource resource) throws SVNException, FileNotFoundException {
		return getFile(resource.getFullPath(branch), resource.getRevision());
	}

	public boolean copyContents(WorkingResource resource, String fromPath, long fromRevisionNo, String toPath, long toRevisionNo)
			throws SVNException, IOException {
		System.out.println("From Path:" + fromPath);
		System.out.println("TO Path:" + toPath);

		SVNURL fromUrl = SVNURL.parseURIDecoded(repositoryUrl + "/" + fromPath);
		SVNRevision fromRevision = SVNRevision.create(fromRevisionNo);
		System.out.println("From URL:" + fromUrl);
		System.out.println("Fomr Revision:" + fromRevision);
		SVNURL toUrl = SVNURL.parseURIDecoded(repositoryUrl + "/" + toPath);
		SVNRevision toRevision = SVNRevision.HEAD;// create(toRevisionNo);
		String owner = resource.getOwner();
		System.out.println(toUrl);
		System.out.println(toRevisionNo);

		if (exists(toPath, -1)) {
			return copyContents(fromPath, fromRevisionNo, toPath, fromUrl, fromRevision, toUrl, toRevision, owner);
		} else {
			System.out.println("Remote HEAD File is not exists :" + toPath);
			copyFile(fromUrl, fromRevision, toUrl, toRevision, owner);
			return true;
		}

	}

	private boolean copyContents(String fromPath, long fromRevisionNo, String toPath, SVNURL fromUrl, SVNRevision fromRevision,
			SVNURL toUrl, SVNRevision toRevision, String owner) throws SVNException, FileNotFoundException, IOException {
		System.out.println("Remote HEAD File exists :" + toPath);

		File wcRoot = new File("branchmerge.tmp");
		if (wcRoot.exists()) {
			deleteDirectory(wcRoot);
		}

		checkOutWorkingCopy(toUrl, wcRoot, toRevision);

		SVNFile file = getFile(fromPath, fromRevisionNo);
		String fileMD5 = md5(file.getData());
		System.out.println("## fromFile revision:" + file.getRevision() + " size:" + file.getData().length);
		File mergeTarget = new File(wcRoot, toPath.substring(toPath.lastIndexOf("/")));
		String mergeTargetMD5 = md5(getData(mergeTarget));
		if (mergeTargetMD5.equals(fileMD5)) {
			System.out.println("Same Contents. Skip");
			return false;
		}

		BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(mergeTarget));
		bout.write(file.getData());
		bout.close();

		String commitMessage = makeCommitMessage(fromUrl, fromRevision, toUrl, toRevision, owner);
		SVNCommitInfo result = svnClientManager.getCommitClient().doCommit(new File[] { mergeTarget }, false, commitMessage, null, null,
				false, true, SVNDepth.IMMEDIATES);
		deleteDirectory(wcRoot);
		if (result.getErrorMessage() != null) {
			throw new RuntimeException(result.getErrorMessage().getMessage());
		}
		System.out.println("## Contents is copied");
		return true;
	}

	private String md5(byte[] data) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] digested = digest.digest(data);
			StringBuffer buf = new StringBuffer();
			for (byte b : digested) {
				buf.append(Integer.toHexString(0xFF & b));
			}
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private byte[] getData(File mergeTarget) {
		try {
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			FileInputStream fin = new FileInputStream(mergeTarget);
			byte[] buf = new byte[4096];
			while (true) {
				int r = fin.read(buf, 0, 4096);
				if (r == -1) {
					break;
				}
				bout.write(buf, 0, r);
			}
			bout.close();
			return bout.toByteArray();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public void copyFile(SVNURL fromUrl, SVNRevision fromRevision, SVNURL toUrl, SVNRevision toRevision, String owner) throws SVNException {
		boolean isMove = false;
		boolean makeParents = true;
		boolean failWhenDstExists = true;
		String commitMessage = makeCommitMessage(fromUrl, fromRevision, toUrl, toRevision, owner);
		SVNProperties revisionProperties = null;

		svnClientManager.getCopyClient().doCopy(new SVNCopySource[] { new SVNCopySource(SVNRevision.UNDEFINED, fromRevision, fromUrl) },
				toUrl, isMove, makeParents, failWhenDstExists, commitMessage, revisionProperties);

	}

	private String makeCommitMessage(SVNURL fromUrl, SVNRevision fromRevision, SVNURL toUrl, SVNRevision toRevision, String owner)
			throws SVNException {
		// Collection logEntries = repository.log(new String[] {
		// fromUrl.getPath() }, null, fromRevision.getNumber(),
		// fromRevision.getNumber(), true, true);
		// String owerComment = "";
		// // ChangeType changeType = null;
		// if (logEntries != null && logEntries.size()>0){
		// SVNLogEntry entry = (SVNLogEntry) logEntries.iterator().next();
		// SVNLogEntryPath pathEntry =
		// (SVNLogEntryPath)entry.getChangedPaths().values().iterator().next();
		// owerComment = entry.getMessage();
		// // changeType = ChangeType.getType(pathEntry.getType());
		// }

		// return "copy from " + fromUrl.getPath() + " (" + owner + ":" +
		// fromRevision.getNumber() + ") to " + toUrl.getPath() + " (R"
		// + toRevision.getNumber() + ")\n"+owerComment;
		return "copy from " + fromUrl.getPath() + " (" + owner + ":" + fromRevision.getNumber() + ") to " + toUrl.getPath() + " (R"
				+ toRevision.getNumber();
	}

	static public boolean deleteDirectory(File path) {
		if (path.exists()) {
			File[] files = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteDirectory(files[i]);
				} else {
					files[i].delete();
				}
			}
		}
		return (path.delete());
	}

	public void delete(String fullPath) throws SVNException {
		System.out.println("Delete :" + fullPath);
		SVNURL deletResource = SVNURL.parseURIDecoded(repositoryUrl + "/" + fullPath);
		System.out.println(deletResource);
		svnClientManager.getCommitClient().doDelete(new SVNURL[] { deletResource }, "DELETED");
	}
}
