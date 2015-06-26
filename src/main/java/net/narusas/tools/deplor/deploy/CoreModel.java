package net.narusas.tools.deplor.deploy;

import lombok.Data;

import org.springframework.stereotype.Component;

@Component
@Data
public class CoreModel {
	DeploymentWorkingSet	workingSet	= new DeploymentWorkingSet();
}
