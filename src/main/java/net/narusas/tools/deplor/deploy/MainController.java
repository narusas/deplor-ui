package net.narusas.tools.deplor.deploy;

import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;

import net.narusas.tools.deplor.domain.model.Branch;
import net.narusas.tools.deplor.domain.model.DeploySet;
import net.narusas.tools.deplor.domain.model.DeploymentRequest;
import net.narusas.tools.deplor.domain.repository.DeploySetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 최상위 컨트롤러
 * 
 * @author narusas
 *
 */
@Controller
public class MainController {

	@Autowired
	RepositoryController	repositoryController;

	@Autowired
	WorkingController		workingController;

	@Autowired
	DeployController		deployController;

	@Autowired
	DeploySetRepository		deploySetRepository;

	private UI		ui;

	public void setUI(UI ui) {
		this.ui = ui;
		repositoryController.setUI(ui);
		workingController.setUI(ui);
		deployController.setUi(ui);
	}

	public void init() {
		repositoryController.init(this);
		workingController.init(this);
		deployController.초기화(this);
	}

	public void 저장소가_선택됨() {
		repositoryController.repositorySelected();
	}

	public void 브랜치가_선택됨() {
		repositoryController.branchSelected();
	}

	public void 선택된_브랜치로_변경(Branch branch) {
		workingController.updateBranch(branch);
	}

	public void 선택됨_요청을_작업목록에_추가() {
		workingController.addToWorking();
	}

	public void 선택된_요청을_작업목록에서_제거(ActionEvent e) {
		workingController.removeRequestFromWorking();
	}

	public void 배포요청_거부() {
		// @TODO 나중에
	}

	public void 거부된_배포요청_복구() {
		// @TODO 나중에
	}

	public void 작업목록을_배포후보로_반영() {
		List<DeploymentRequest> request = workingController.getWorkingSet();

		DeploySet set = new DeploySet();
		set.setTimestamp(new Date());
		set.addRequest(request);
		set.setPrevious(workingController.getPrevious());
		set = deploySetRepository.save(set);

		deployController.후보_배포셋_갱신();
		작업페이지로_전환();

	}

	public void 선택된_배포후보의_내역을_작업목록으로_복사() {
		DeploySet deploySet = deployController.getSelectedDeploySet();
		workingController.선택된_배포후보의_내역을_작업목록으로_복사(deploySet);
		배포페이지로_전환();

	}

	private void 작업페이지로_전환() {
		ui.getTabbedPane().setSelectedIndex(1);
	}

	private void 배포페이지로_전환() {
		ui.getTabbedPane().setSelectedIndex(0);
	}

	public void 배포요청_자동선택() {
		workingController.배포요청_자동선택();
	}
}
