package net.narusas.tools.deplor.deploy;

import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.narusas.tools.deplor.deploy.model.ChangesTableModel;
import net.narusas.tools.deplor.deploy.model.DeploySetTableModel;
import net.narusas.tools.deplor.deploy.model.RequestTableModel;
import net.narusas.tools.deplor.domain.model.DeploySet;
import net.narusas.tools.deplor.domain.model.DeploymentRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@Data
public class DeployCandidateController {
	@Autowired
	ApplicationContext	ctxt;

	@Autowired
	DeploySetTableModel	deploySetTableModelModel;

	UI					ui;
	DeployController	parent;

	RequestTableModel	requestTableModel;
	ChangesTableModel	changesTableModel;

	public void 초기화(DeployController deployController) {
		this.parent = deployController;
		초기화_배포셋_테이블();
		초기화_배포요청_테이블();
		초기화_변경사항_테이블();

		// 객체 바인딩이 모두 끝난후에 데이터를 요청한다.
		후보_배포셋_갱신();
	}

	private void 초기화_배포셋_테이블() {
		ui.getCandidatesDeploySetTable().setAutoCreateColumnsFromModel(false);
		ui.getCandidatesDeploySetTable().setModel(getDeploySetTableModelModel());
		ui.getCandidatesDeploySetTable().setColumnModel(getDeploySetTableModelModel().getColModel());
		ui.getCandidatesDeploySetTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				deploySetSelected(ui.getCandidatesDeploySetTable().getSelectedRows());
			}
		});
	}

	private void 초기화_배포요청_테이블() {
		ui.getCandidateRequestTable().setAutoCreateColumnsFromModel(false);
		ui.getCandidateRequestTable().setModel(getRequestTableModel());
		ui.getCandidateRequestTable().setColumnModel(getRequestTableModel().getColModel());
		ui.getCandidateRequestTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				requestSelected(ui.getCandidateRequestTable().getSelectedRows());
			}
		});
	}

	private void 초기화_변경사항_테이블() {
		ui.getCandidateChangesTable().setAutoCreateColumnsFromModel(false);
		ui.getCandidateChangesTable().setModel(getChangeTableModel());
		ui.getCandidateChangesTable().setColumnModel(getChangeTableModel().getColModel());
		// Changes 는 선택해도 할 액션이 없다
	}

	private DeploySetTableModel getDeploySetTableModelModel() {
		// if (deploySetTableModelModel != null) {
		// return deploySetTableModelModel;
		// }
		// deploySetTableModelModel = new DeploySetTableModel();
		// // ctxt.getAutowireCapableBeanFactory().autowireBean(deploySetTableModelModel);
		return deploySetTableModelModel;
	}

	private RequestTableModel getRequestTableModel() {
		if (requestTableModel != null) {
			return requestTableModel;
		}
		requestTableModel = new RequestTableModel();
		return requestTableModel;
	}

	private ChangesTableModel getChangeTableModel() {
		if (changesTableModel != null) {
			return changesTableModel;
		}
		changesTableModel = new ChangesTableModel();
		return changesTableModel;
	}

	public void 후보_배포셋_갱신() {
		getDeploySetTableModelModel().refresh();
	}

	protected void deploySetSelected(int[] selectedRows) {
		List<DeploySet> sets = getDeploySetTableModelModel().getSelectedSet(selectedRows);
		getRequestTableModel().updateRequests(sets.get(0).getRequests());
	}

	protected void requestSelected(int[] selectedRows) {
		List<DeploymentRequest> requests = getRequestTableModel().getSelectedRequest(selectedRows);
		getChangesTableModel().clear();
		for (DeploymentRequest deploymentRequest : requests) {
			getChangesTableModel().add(deploymentRequest.getChanges());
		}
	}

	public DeploySet getSelectedDeploySet() {
		return getDeploySetTableModelModel().getSelectedSet(ui.getCandidatesDeploySetTable().getSelectedRows()).get(0);
	}

}
