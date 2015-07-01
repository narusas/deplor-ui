package net.narusas.tools.deplor.reporter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import net.narusas.tools.deplor.config.ApplicationConfig;
import net.narusas.tools.deplor.domain.model.Change;
import net.narusas.tools.deplor.domain.model.DeploySet;
import net.narusas.tools.deplor.domain.model.DeploymentRequest;
import net.narusas.tools.deplor.domain.model.DeploymentStatus;
import net.narusas.tools.deplor.domain.repository.DeploySetRepository;
import net.narusas.tools.deplor.domain.repository.DeploymentRequestRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ApplicationConfig.class })
@Slf4j
public class DeployTest {

	@Autowired
	DeploymentRequestRepository	deploymentRepository;

	@Autowired
	DeploySetRepository			deploySetRepository;

	@Test
	public void create() {
		List<DeploymentRequest> requestList = deploymentRepository.findByStatus(DeploymentStatus.제출됨);
		assertNotNull(requestList);
		assertTrue(requestList.size() > 0);

		DeploySet set = new DeploySet();

		for (DeploymentRequest deploymentRequest : requestList) {
			set.addRequest(deploymentRequest);
		}

		set.setTimestamp(new Date());

		deploySetRepository.save(set);
	}

	@Test
	public void lookup() {
		DeploySet set = deploySetRepository.findAll().get(0);
		assertNotNull(set);

		Set<Change> changes = set.getAllChanges();
		for (Change change : changes) {
			System.out.println("##### " + change);
		}
	}
	
	@Test
	public void trial() {
//		Deployment dep = new Deployment();
//		
//		DeploySet set = deploySetRepository.findAll().get(0);
//		dep.setDeploySet(set);
//		
//		Stage stage = new Stage(StageType.DEV, branch);
//		dep.setStage(stage);
//		
//		
//		deploymentRepository.save(dep);
//		dep.run();
		
		
	
	}
}
