package net.narusas.tools.deplor.deploy.model;

import static net.narusas.tools.deplor.deploy.UIUtil.col;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumnModel;

import lombok.Data;
import net.narusas.tools.deplor.domain.model.DeploymentRequest;

import org.apache.commons.lang3.StringUtils;

@Data
@SuppressWarnings("serial")
public class RequestTableModel extends AbstractTableModel {

	List<DeploymentRequest>	source		= new ArrayList<>();				;
	List<DeploymentRequest>	requests	= new ArrayList<>();

	TableColumnModel		colModel	= new RequestTableColumnModel();

	String					filtering;

	public void updateRequests(List<DeploymentRequest> request) {
		this.source = request;
		filter();
		fireTableDataChanged();
	}

	public void addRequests(List<DeploymentRequest> request) {
		source.addAll(request);
		filter();
		fireTableDataChanged();
	}

	public void filter() {
		this.requests = new FilteredList(this.source, filtering);
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return requests.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		switch (column) {
		case 0:
			return requests.get(row).getId();
		case 1:
			return requests.get(row).getStatus();
		case 2:
			return requests.get(row).getAuthor().getName();
		case 3:
			return new DateLabel(requests.get(row).getTimestamp());

		case 4:
			return requests.get(row).getMessage();
		}
		return null;
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	public List<DeploymentRequest> removeRequestAt(int[] toRemove) {
		List<DeploymentRequest> remove = new ArrayList();
		for (int i : toRemove) {
			remove.add(requests.get(i));
		}
		requests.removeAll(remove);
		fireTableDataChanged();
		return remove;

	}

	public List<DeploymentRequest> getSelectedRequest(int[] selectedRows) {
		List<DeploymentRequest> res = new ArrayList<>();
		for (int row : selectedRows) {
			res.add(requests.get(row));
		}
		return res;
	}

	public void filter(String text) {
		filtering = text;
		filter();
	}
}

@SuppressWarnings("serial")
class RequestTableColumnModel extends DefaultTableColumnModel {
	public RequestTableColumnModel() {
		addColumn(col(0, 50, "ID"));
		addColumn(col(1, 50, "Status"));
		addColumn(col(2, 70, "Author"));
		addColumn(col(3, 150, "Time"));
		addColumn(col(4, "Message"));
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
