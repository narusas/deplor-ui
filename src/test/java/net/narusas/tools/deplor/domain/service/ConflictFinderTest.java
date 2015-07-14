package net.narusas.tools.deplor.domain.service;

import lombok.extern.slf4j.Slf4j;
import net.narusas.tools.deplor.config.ApplicationConfig;
import net.narusas.tools.deplor.domain.model.Branch;
import net.narusas.tools.deplor.domain.model.Change;
import net.narusas.tools.deplor.domain.model.DeploymentRequest;
import net.narusas.tools.deplor.domain.repository.BranchRepository;
import net.narusas.tools.deplor.domain.repository.DeploySetRepository;

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
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ApplicationConfig.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@Slf4j
@DatabaseSetup("fixture.xml")
public class ConflictFinderTest {
	@Autowired
	DeploySetRepository	deploySetRepository;

	@Autowired
	BranchRepository	branchRepository;

	@Autowired
	ConflictFinder		conflictFinder;

	@Autowired
	LsbService			lsbService;

	@Test
	public void test() {
		
		
//		Branch branch = branchRepository.getOne(8L);
//		System.out.println(branch);
//		System.out.println(branch.getStage().get(0));
//		
//		lsbService.LSB_신규생성(branch);
		
	}

	@Test
	public void test2() {
//		Branch branch = branchRepository.getOne(8L);
//		
//		
//		DeploymentRequest req = new DeploymentRequest();
//		req.addChange(new Change);
		
	}

}
