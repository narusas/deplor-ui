package net.narusas.tools.deplor.deploy;

import static net.narusas.tools.deplor.deploy.UIUtil.col;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import net.narusas.tools.deplor.deploy.model.ChangesTableModel;
import net.narusas.tools.deplor.deploy.model.RequestTableModel;
import net.narusas.tools.deplor.domain.model.Branch;
import net.narusas.tools.deplor.domain.model.DeploymentRequest;
import net.narusas.tools.deplor.domain.repository.DeploymentRequestRepository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class WorkingRequestController {
	@Autowired
	DeploymentRequestRepository	requetsRepository;

	UI					ui;
	WorkingController			parent;

	RequestTableModel			requestTableModel;
	ChangesTableModel			changesTableModel;

	public void setup(UI ui, WorkingController workingController) {
		this.ui = ui;
		this.parent = workingController;
	}

	public void init() {
		setupFilterTextField();
		setupRequestTable();
		setupChangeTable();
	}

	private void setupFilterTextField() {
		ui.getRequestFilteringTextField().getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				getRequestTableModel().filter(ui.getRequestFilteringTextField().getText());
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				getRequestTableModel().filter(ui.getRequestFilteringTextField().getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});
	}

	private void setupRequestTable() {
		ui.getRequestsTable().setAutoCreateColumnsFromModel(false);
		ui.getRequestsTable().setModel(getRequestTableModel());
		ui.getRequestsTable().setColumnModel(getRequestTableModel().getColModel());
		ui.getRequestsTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				requestRowSelected(ui.getRequestsTable().getSelectedRows());
			}
		});
	}

	private void setupChangeTable() {
		ui.getRequestChangesTable().setAutoCreateColumnsFromModel(false);
		ui.getRequestChangesTable().setModel(getRequestChangeTableModel());
		ui.getRequestChangesTable().setColumnModel(getRequestChangeTableModel().getColModel());
	}

	RequestTableModel getRequestTableModel() {
		if (requestTableModel != null) {
			return requestTableModel;
		}
		requestTableModel = new RequestTableModel();
		return requestTableModel;
	}

	private ChangesTableModel getRequestChangeTableModel() {
		if (changesTableModel != null) {
			return changesTableModel;
		}
		changesTableModel = new ChangesTableModel();
		return changesTableModel;
	}

	protected void requestRowSelected(int[] rows) {
		List<DeploymentRequest> requests = getRequestTableModel().getSelectedRequest(rows);
		getRequestChangeTableModel().clear();
		for (DeploymentRequest deploymentRequest : requests) {
			getRequestChangeTableModel().add(deploymentRequest.getChanges());
		}
	}

	public void updateBranch(Branch branch) {
		getRequestTableModel().updateRequests(requetsRepository.findByBranch(branch));
	}

	public List<DeploymentRequest> getSelectedDeploymentRequest() {
		return getRequestTableModel().getSelectedRequest(ui.getRequestsTable().getSelectedRows());
	}

	public void automaticSelect() {

	}

}
