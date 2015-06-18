package net.narusas.tools.deplor.crowler;

import static org.junit.Assert.*;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import net.narusas.tools.deplor.config.ApplicationConfig;
import net.narusas.tools.deplor.domain.model.Branch;
import net.narusas.tools.deplor.domain.model.Repository;
import net.narusas.tools.deplor.domain.repository.RepoRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ApplicationConfig.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@Slf4j
public class SvnCrowlerTest {
	@Autowired
	private RepoRepository	repoRepository;

	@Test
	public void test() {
		List<Repository> all = repoRepository.findAll();
		System.out.println(all);
		assertTrue(all.size() > 0);

		for (Repository repository : all) {
			System.out.println("##########");
			List<Branch> branches = repository.getBranches();
			for (Branch branch : branches) {
				System.out.println(branch);
			}
		}
	}

	@Test
	public void brancheList() {
		Repository repo = repoRepository.findAll().get(0);
		SvnCrowler crowler = new SvnCrowler(repo);
		log.info("Head Revision: {}", crowler.headRevision());

		assertTrue(crowler.headRevision() > 0);
	}
}
