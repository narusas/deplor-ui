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
import net.narusas.tools.deplor.domain.model.DeploySet;
import net.narusas.tools.deplor.domain.model.DeploymentRequest;
import net.narusas.tools.deplor.domain.repository.DeploymentRequestRepository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 상단 Working Set 패널들에 대한 컨트롤러
 * 
 * @author narusas
 *
 */
@Controller
public class WorkingController {
	@Autowired
	DeploymentRequestRepository	requetsRepository;

	@Autowired
	WorkingRequestController	workingRequestController;

	@Autowired
	WorkingWorkingSetController	workingWorkingSetController;

	UI					ui;
	MainController				parent;

	@Getter
	DeploySet					previous;

	public void setUI(UI ui) {
		this.ui = ui;
		workingRequestController.setup(ui, this);
		workingWorkingSetController.setup(ui, this);
	}

	public void init(MainController parent) {
		this.parent = parent;
		workingRequestController.init();
		workingWorkingSetController.init();
	}

	public void updateBranch(Branch branch) {
		workingRequestController.updateBranch(branch);
	}

	public void addToWorking() {
		List<DeploymentRequest> request = workingRequestController.getSelectedDeploymentRequest();
		workingWorkingSetController.clone(request);
	}

	public void removeRequestFromWorking() {
		workingWorkingSetController.removeRequestFromWorking();
	}

	public List<DeploymentRequest> getWorkingSet() {
		return workingWorkingSetController.getWorkingSet();
	}

	public void 선택된_배포후보의_내역을_작업목록으로_복사(DeploySet deploySet) {
		previous = deploySet;
		workingWorkingSetController.clone(deploySet.getRequests());
	}

	public void 배포요청_자동선택() {
		workingRequestController.automaticSelect();
	}
}
