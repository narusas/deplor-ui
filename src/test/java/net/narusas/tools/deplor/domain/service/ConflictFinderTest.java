package net.narusas.tools.deplor.domain.service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import net.narusas.tools.deplor.config.ApplicationConfig;
import net.narusas.tools.deplor.domain.model.DeploySet;
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
@DatabaseSetup("sampleData.xml")
@Slf4j
public class ConflictFinderTest {
	@Autowired
	DeploySetRepository	deploySetRepository;

	@Autowired
	ConflictFinder		conflictFinder;

	@Test
	public void test() {
		List<DeploySet> sets = deploySetRepository.findAll();
		for (DeploySet deploySet : sets) {
			System.out.println(deploySet);
		}
	}

	@Test
	public void test2() {
	}

}
