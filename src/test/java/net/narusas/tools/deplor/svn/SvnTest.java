package net.narusas.tools.deplor.svn;

import java.io.File;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNClientManager;

@Slf4j
public class SvnTest {
	@Test
	public void log() throws SVNException {
		String repositoryUri = "file://" + new File("repo/prj1").getAbsolutePath();

		SVNRepositoryFactoryImpl.setup();
		SVNRepository repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(repositoryUri));
		SVNClientManager svnClientManager = SVNClientManager.newInstance(new DefaultSVNOptions(), null, null);
		

	}

}
