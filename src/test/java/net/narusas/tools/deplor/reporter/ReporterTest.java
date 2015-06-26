package net.narusas.tools.deplor.reporter;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import net.narusas.tools.deplor.config.ApplicationConfig;
import net.narusas.tools.deplor.domain.model.Account;
import net.narusas.tools.deplor.domain.model.Branch;
import net.narusas.tools.deplor.domain.model.Change;
import net.narusas.tools.deplor.domain.model.DeploymentRequest;
import net.narusas.tools.deplor.domain.model.DeploymentStatus;
import net.narusas.tools.deplor.domain.model.Repository;
import net.narusas.tools.deplor.domain.model.Revision;
import net.narusas.tools.deplor.domain.model.User;
import net.narusas.tools.deplor.domain.repository.AccountRepository;
import net.narusas.tools.deplor.domain.repository.DeploymentRequestRepository;
import net.narusas.tools.deplor.domain.repository.RepoRepository;
import net.narusas.tools.deplor.domain.repository.RevisionRepository;
import net.narusas.tools.deplor.domain.repository.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ApplicationConfig.class })
@Slf4j
public class ReporterTest {
	@Autowired
	RepoRepository				repoRepository;

	@Autowired
	RevisionRepository			revisionRepository;

	@Autowired
	DeploymentRequestRepository	deploymentRepository;

	@Autowired
	AccountRepository			accountRepository;

	@Autowired
	UserRepository				userRepository;

	@Test
	public void createDeploymentRequest() {
		Repository repo = repoRepository.findAll().get(0);
		Branch branch = repo.getBranche("trunk");

		// 사용자의 계정을 입력받아 해당하는 Account 를 찾아온다
		Account account = accountRepository.findOneByName("jsan");

		Revision revision = revisionRepository.findOneByBranchAndVersion(branch, 811L);
		assertNotNull(revision);
		System.out.println("##########" + revision);

		DeploymentRequest request = new DeploymentRequest();
		request.setAuthor(account);
		request.setMessage("테스트 한글 메시지");
		request.setBranch(branch);

		for (Change change : revision.getChanges()) {
			System.out.println(change);
			request.addChange(change);
		}

		// 제출 시간은 저장 직전에 넣어 주는게 좋다
		request.setTimestamp(new Date());
		request.setStatus(DeploymentStatus.제출됨);
		deploymentRepository.save(request);
	}

	@Test
	public void getOldOne() {
		DeploymentRequest request = deploymentRepository.findOne(1L);
		assertNotNull(request);
		System.out.println(request);
		for (Change change : request.getChanges()) {
			System.out.println(change);
		}
	}

	@Test
	public void getRequestHistory() {
		Account account = accountRepository.findOneByName("jsan");
		List<DeploymentRequest> requests = deploymentRepository.findByAuthorOrderByTimestamp(account);
		assertNotNull(requests);
		for (DeploymentRequest deploymentRequest : requests) {
			System.out.println("###" + deploymentRequest);
		}
	}

	@Test
	public void testUser() {
		User user = userRepository.findOneByName("Jisung, Ahn");
		assertNotNull(user);
		System.out.println("######" + user);
	}

}
