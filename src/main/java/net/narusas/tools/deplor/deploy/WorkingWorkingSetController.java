package net.narusas.tools.deplor.deploy;

import static net.narusas.tools.deplor.deploy.UIUtil.col;

import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import net.narusas.tools.deplor.domain.model.DeploymentRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@SuppressWarnings("serial")
public class WorkingWorkingSetController {
	@Autowired
	CoreModel					coreModel;

	DeployFrame					ui;

	WorkingSetTableModel		workingSetTableModel;

	private WorkingController	parent;

	public void setUI(DeployFrame ui) {
		this.ui = ui;
	}

	public void init() {
		ui.getWorkingSetTable().setAutoCreateColumnsFromModel(false);
		ui.getWorkingSetTable().setModel(getWorkingsetTableModel());
		ui.getWorkingSetTable().setColumnModel(getWorkingsetTableColumnModel());

		getWorkingsetTableModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				parent.updateWorkingSets(coreModel.getWorkingSet());
			}
		});
	}

	private WorkingSetTableModel getWorkingsetTableModel() {
		if (workingSetTableModel != null) {
			return workingSetTableModel;
		}
		workingSetTableModel = new WorkingSetTableModel(coreModel.workingSet);
		return workingSetTableModel;
	}

	private WorkingSetTableColumnModel getWorkingsetTableColumnModel() {
		return new WorkingSetTableColumnModel();
	}

	public void addToWorking(List<DeploymentRequest> request) {
		getWorkingsetTableModel().addRequests(request);
	}

	class WorkingSetTableModel extends AbstractTableModel {

		private DeploymentWorkingSet	workingSet;

		public WorkingSetTableModel(DeploymentWorkingSet workingSet) {
			this.workingSet = workingSet;
		}

		public void addRequests(List<DeploymentRequest> request) {
			workingSet.add(request);
			fireTableDataChanged();
		}

		@Override
		public int getRowCount() {
			return workingSet.size();
		}

		@Override
		public Object getValueAt(int row, int column) {
			switch (column) {
			case 0:
				return workingSet.get(row).getId();
			case 1:
				return workingSet.get(row).getStatus();
			case 2:
				return workingSet.get(row).getAuthor().getName();
			case 3:
				return new DateLabel(workingSet.get(row).getTimestamp());
			}
			return null;
		}

		@Override
		public int getColumnCount() {
			return 4;
		}
	}

	class WorkingSetTableColumnModel extends DefaultTableColumnModel {
		public WorkingSetTableColumnModel() {
			addColumn(col(0, 50, "ID"));
			addColumn(col(1, 50, "Status"));
			addColumn(col(2, 70, "Author"));
			addColumn(col(3, "Time"));
		}
	}

	public void setParent(WorkingController workingController) {
		this.parent = workingController;
	}

	public void updateWorkingSet(DeploymentWorkingSet workingSet) {

	}

}
