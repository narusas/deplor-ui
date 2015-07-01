package net.narusas.tools.deplor.deploy;

import static net.narusas.tools.deplor.deploy.UIUtil.col;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumnModel;

import net.narusas.tools.deplor.deploy.model.ChangesTableModel;
import net.narusas.tools.deplor.deploy.model.CoreModel;
import net.narusas.tools.deplor.deploy.model.RequestTableModel;
import net.narusas.tools.deplor.domain.model.Change;
import net.narusas.tools.deplor.domain.model.DeploymentRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@SuppressWarnings("serial")
public class WorkingWorkingSetController {
	@Autowired
	CoreModel					coreModel;

	UI					ui;

	RequestTableModel			requestTableModel;

	private WorkingController	parent;

	private ChangesTableModel	changesTableModel;

	public void setup(UI ui, WorkingController workingController) {
		this.ui = ui;
		parent = workingController;
	}

	public void init() {
		setupWorkingSetTable();
		setupWorkingChangesTable();
	}

	private void setupWorkingSetTable() {
		ui.getWorkingSetTable().setAutoCreateColumnsFromModel(false);
		ui.getWorkingSetTable().setModel(getRequestTableModel());
		ui.getWorkingSetTable().setColumnModel(getRequestTableModel().getColModel());
		ui.getWorkingSetTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				workingsetSelected(ui.getWorkingSetTable().getSelectedRows());
			}
		});
	}

	private void setupWorkingChangesTable() {
		ui.getWorkingChangesTable().setAutoCreateColumnsFromModel(false);
		ui.getWorkingChangesTable().setModel(getChangeTableModel());
		ui.getWorkingChangesTable().setColumnModel(getChangeTableModel().getColModel());
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

	protected void workingsetSelected(int[] selectedRows) {
		getChangeTableModel().clear();
		List<DeploymentRequest> list = getRequestTableModel().getSelectedRequest(selectedRows);
		for (DeploymentRequest deploymentRequest : list) {
			getChangeTableModel().add(deploymentRequest.getChanges());
		}
	}

	public void removeRequestFromWorking() {
		int[] toRemove = ui.getWorkingSetTable().getSelectedRows();
		List<DeploymentRequest> removed = getRequestTableModel().removeRequestAt(toRemove);
		for (DeploymentRequest deploymentRequest : removed) {
			getChangeTableModel().remove(deploymentRequest.getChanges());
		}
	}

	public List<DeploymentRequest> getWorkingSet() {
		return Collections.unmodifiableList(getRequestTableModel().getRequests());
	}

	public void clone(List<DeploymentRequest> requests) {
		getRequestTableModel().updateRequests(requests);
	}
}
