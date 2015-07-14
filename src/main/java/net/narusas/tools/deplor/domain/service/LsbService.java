package net.narusas.tools.deplor.domain.service;

import java.util.List;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.narusas.tools.deplor.domain.model.Branch;
import net.narusas.tools.deplor.domain.model.DeployedResource;
import net.narusas.tools.deplor.domain.model.Resource;
import net.narusas.tools.deplor.domain.model.Stage;
import net.narusas.tools.deplor.domain.repository.ResourceRepository;
import net.narusas.tools.deplor.domain.repository.StageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Data
public class LsbService {
	@Autowired
	ResourceRepository	resourceRepository;

	@Autowired
	StageRepository		stageReposutory;

	public void LSB_신규생성(Branch branch) {

		Stage lsb = branch.getStage("LSB");
		lsb.clearResources();
		List<Resource> resources = resourceRepository.findByBranchAndTypeNot(branch, "D");
		for (Resource resource : resources) {
			lsb.add(new DeployedResource(branch, resource, resource.getLatestChange()));
		}
		stageReposutory.save(lsb);

	}

}
