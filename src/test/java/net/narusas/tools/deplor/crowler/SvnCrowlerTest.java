package net.narusas.tools.deplor.crowler;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import net.narusas.tools.deplor.config.ApplicationConfig;
import net.narusas.tools.deplor.domain.model.Account;
import net.narusas.tools.deplor.domain.model.Branch;
import net.narusas.tools.deplor.domain.model.Change;
import net.narusas.tools.deplor.domain.model.Repository;
import net.narusas.tools.deplor.domain.model.Resource;
import net.narusas.tools.deplor.domain.model.Revision;
import net.narusas.tools.deplor.domain.repository.AccountRepository;
import net.narusas.tools.deplor.domain.repository.BranchRepository;
import net.narusas.tools.deplor.domain.repository.ChangeRepository;
import net.narusas.tools.deplor.domain.repository.RepoRepository;
import net.narusas.tools.deplor.domain.repository.ResourceRepository;
import net.narusas.tools.deplor.domain.repository.RevisionRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNLogEntryPath;
import org.tmatesoft.svn.core.SVNNodeKind;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ApplicationConfig.class })
// @TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
// TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@Slf4j
public class SvnCrowlerTest {
	@Autowired
	RepoRepository				repoRepository;

	@Autowired
	BranchRepository			branchRepository;

	@Autowired
	AccountRepository			accountRepository;

	@Autowired
	ChangeRepository			changeRepository;

	@Autowired
	ResourceRepository			resourceRepository;

	@Autowired
	RevisionRepository			revisionRepository;

	@Autowired
	PlatformTransactionManager	txManager;

	@Test
	public void test() {
		List<Repository> all = repoRepository.findAll();
		System.out.println(all);
		assertTrue(all.size() > 0);

		for (Repository repository : all) {
			System.out.println("##########");
			List<Branch> branches = repository.getBranches();
			for (Branch branch : branches) {
				System.out.println("##!!!!!!!");
				System.out.println(branch);
				System.out.println(branch.getRepository());
			}
		}
	}

	@Test
	public void headRevision() {
		Repository repo = repoRepository.findAll().get(0);
		SvnCrowler crowler = new SvnCrowler(repo);
		log.info("Head Revision: {}", crowler.headRevision());
		assertTrue(crowler.headRevision() > 0);
	}

	@Test
	public void brancheList() {
		Repository repo = repoRepository.findAll().get(0);
		SvnCrowler crowler = new SvnCrowler(repo);
		crowler.setRepoRepository(repoRepository);
		crowler.processBranches();
	}

	@Test
	public void branchHistory() {

		// TODO 지워진 파일 처리가 필요함

		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = txManager.getTransaction(def);
		Repository repo = repoRepository.findAll().get(0);
		SvnCrowler crowler = new SvnCrowler(repo);
		crowler.setRepoRepository(repoRepository);
		Branch branch = repo.getBranche("trunk");
		System.out.println("######## " + branch);
		long lastRevision = branch.getLastRevision();
		System.out.println("#############:" + lastRevision);
		Collection<SVNLogEntry> logs = crowler.getSvn().logs("/trunk", lastRevision);
		for (SVNLogEntry svnLogEntry : logs) {
			System.out.println(svnLogEntry);
			lastRevision = svnLogEntry.getRevision();
			String authorName = svnLogEntry.getAuthor();
			Account account = repo.getAccount(authorName);
			if (account == null) {
				account = accountRepository.save(new Account(repo, authorName));
				repo.addAccount(account);
			}
			Revision revision = revisionRepository.save(Revision.from(branch, svnLogEntry, account));
			Collection<SVNLogEntryPath> paths = svnLogEntry.getChangedPaths().values();
			for (SVNLogEntryPath path : paths) {
				if (path.getKind() != SVNNodeKind.FILE) {
					continue;
				}
				String fullPath = path.getPath();
				String shortPath = fullPath.replaceFirst("/" + branch.getName(), "");
				Resource resource = resourceRepository.findByPathAndRepository(shortPath, repo);
				if (resource == null) {
					resource = resourceRepository.save(new Resource(repo, branch, shortPath));
				}
				Change change = changeRepository.save(new Change(resource, shortPath, String.valueOf(path.getType())));
				resource.setLatestChange(change);
				resource.setType(String.valueOf(path.getType()));

				revision.addChange(change);
				revision.setAuthor(account);
				branch.addRevision(revision);
			}
			System.out.println("##" + revision);
			System.out.println("##" + revision.getChanges());

		}
		branch.setLastRevision(lastRevision);
		branchRepository.save(branch);
		txManager.commit(status);
	}
}
