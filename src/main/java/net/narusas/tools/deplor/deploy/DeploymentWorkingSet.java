package net.narusas.tools.deplor.deploy;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import net.narusas.tools.deplor.domain.model.DeploymentRequest;

@Data
public class DeploymentWorkingSet {
	List<DeploymentRequest>	list	= new ArrayList<>();

	public int size() {
		return list.size();
	}

	public DeploymentRequest get(int row) {
		return list.get(row);
	}

	public void add(List<DeploymentRequest> request) {
		for (DeploymentRequest deploymentRequest : request) {
			if (list.contains(deploymentRequest) == false) {
				list.add(deploymentRequest);
			}
		}
	}

}
