package net.narusas.tools.deplor.deploy;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.narusas.tools.deplor.deploy.model.ChangesTableModel;
import net.narusas.tools.deplor.deploy.model.DeploySetTableModel;
import net.narusas.tools.deplor.domain.model.DeploySet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@Data
public class DeployController {

	@Autowired
	DeployCandidateController	deployCandidateController;

	UI							ui;

	MainController				parent;

	public void 초기화(MainController mainController) {
		this.parent = mainController;
		deployCandidateController.setUi(ui);
		deployCandidateController.초기화(this);

	}

	public void 후보_배포셋_갱신() {
		deployCandidateController.후보_배포셋_갱신();
	}

	public DeploySet getSelectedDeploySet() {
		return deployCandidateController.getSelectedDeploySet();
	}

}
