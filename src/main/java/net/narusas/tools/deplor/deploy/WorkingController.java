package net.narusas.tools.deplor.deploy;

import static net.narusas.tools.deplor.deploy.UIUtil.col;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumnModel;

import lombok.Getter;
import net.narusas.tools.deplor.domain.model.Branch;
import net.narusas.tools.deplor.domain.model.DeploymentRequest;
import net.narusas.tools.deplor.domain.repository.DeploymentRequestRepository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class WorkingController {
	@Autowired
	DeploymentRequestRepository	requetsRepository;

	@Autowired
	WorkingRequestController	workingRequestController;

	@Autowired
	WorkingWorkingSetController	workingWorkingSetController;

	DeployFrame					ui;

	public void setUI(DeployFrame ui) {
		this.ui = ui;
		workingRequestController.setUI(ui);
		workingWorkingSetController.setUI(ui);
		workingWorkingSetController.setParent(this);
	}

	public void init() {

		workingRequestController.init();
		workingWorkingSetController.init();
	}

	public void addToWorking() {
		List<DeploymentRequest> request = workingRequestController.getSelectedDeploymentRequest();
		System.out.println("## Add: " + request);
		workingWorkingSetController.addToWorking(request);
	}

	public void updateBranch(Branch branch) {
		workingRequestController.updateBranch(branch);
	}

	public void updateWorkingSets(DeploymentWorkingSet workingSet) {
		workingWorkingSetController.updateWorkingSet(workingSet);
	}
}
