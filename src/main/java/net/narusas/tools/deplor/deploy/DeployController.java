package net.narusas.tools.deplor.deploy;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.narusas.tools.deplor.domain.model.Branch;
import net.narusas.tools.deplor.domain.model.Repository;
import net.narusas.tools.deplor.domain.repository.RepoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 최상위 컨트롤러
 * 
 * @author narusas
 *
 */
@Controller
public class DeployController {

	@Autowired
	RepositoryController	repositoryController;

	@Autowired
	WorkingController		workingController;

	private DeployFrame		ui;

	public void setUI(DeployFrame ui) {
		this.ui = ui;
		repositoryController.setUI(ui);
		workingController.setUI(ui);
	}

	public void init() {
		repositoryController.init(this);
		workingController.init();
	}

	public void repositorySelected(ActionEvent e) {
		repositoryController.repositorySelected(e);
	}

	public void branchSelected(ActionEvent e) {
		repositoryController.branchSelected(e);
	}

	public void updateBranch(Branch branch) {
		workingController.updateBranch(branch);
	}

	public void addToWorking() {
		workingController.addToWorking();
	}

}
