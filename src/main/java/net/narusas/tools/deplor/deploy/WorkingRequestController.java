package net.narusas.tools.deplor.deploy;

import static net.narusas.tools.deplor.deploy.UIUtil.col;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumnModel;

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

	RequestTableModel			requestTableModel;
	DeployFrame					ui;

	public void init() {
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

		ui.getRequestsTable().setAutoCreateColumnsFromModel(false);
		ui.getRequestsTable().setModel(getRequestTableModel());
		ui.getRequestsTable().setColumnModel(getRequestTableColumnModel());

	}

	private TableColumnModel getRequestTableColumnModel() {
		return new RequestTableColumnModel();
	}

	@SuppressWarnings("serial")
	class RequestTableColumnModel extends DefaultTableColumnModel {
		public RequestTableColumnModel() {
			addColumn(col(0, 50, "ID"));
			addColumn(col(1, 50, "Status"));
			addColumn(col(2, 70, "Author"));
			addColumn(col(3, "Time"));
		}
	}

	RequestTableModel getRequestTableModel() {
		if (requestTableModel != null) {
			return requestTableModel;
		}
		requestTableModel = new RequestTableModel();
		return requestTableModel;
	}

	public void updateBranch(Branch branch) {
		getRequestTableModel().updateRequest(requetsRepository.findByBranch(branch));
	}

	@SuppressWarnings("serial")
	class RequestTableModel extends AbstractTableModel {
		List<DeploymentRequest>	request	= new ArrayList<>();
		List<DeploymentRequest>	source;
		String					filtering;

		public void updateRequest(List<DeploymentRequest> update) {
			this.source = update;
			filter();
			fireTableDataChanged();
		}

		private void filter() {
			this.request = new FilteredList(this.source, filtering);
			fireTableDataChanged();
		}

		void filter(String filtering) {
			this.filtering = filtering;
			filter();
		}

		@Override
		public int getRowCount() {
			return request.size();
		}

		@Override
		public int getColumnCount() {
			return 3;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return request.get(rowIndex).getId();
			case 1:
				return request.get(rowIndex).getStatus();
			case 2:
				return request.get(rowIndex).getAuthor().getName();
			case 3:
				return new DateLabel(request.get(rowIndex).getTimestamp());
			}
			return null;
		}

		public List<DeploymentRequest> getRequests(int[] selectedRows) {
			List<DeploymentRequest> res = new ArrayList<>();
			for (int i : selectedRows) {
				res.add(request.get(i));
			}
			return res;
		}

	}

	@SuppressWarnings("serial")
	class FilteredList extends ArrayList<DeploymentRequest> {

		private String	filtering;

		public FilteredList(List<DeploymentRequest> source, String filtering) {
			if (source == null) {
				return;
			}
			this.filtering = filtering;
			for (DeploymentRequest deploymentRequest : source) {
				if (StringUtils.isEmpty(filtering)) {
					add(deploymentRequest);
					continue;
				}
				if (deploymentRequest.getAuthor().getName().contains(filtering)) {
					add(deploymentRequest);
				}
			}
		}
	}

	public List<DeploymentRequest> getSelectedDeploymentRequest() {
		return getRequestTableModel().getRequests(ui.getRequestsTable().getSelectedRows());
	}

	public void setUI(DeployFrame ui) {
		this.ui = ui;
	}

}
