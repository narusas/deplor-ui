package net.narusas.tools.deplor.deploy.model;

import static net.narusas.tools.deplor.deploy.UIUtil.col;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;

import lombok.Data;
import net.narusas.tools.deplor.domain.model.DeploySet;
import net.narusas.tools.deplor.domain.repository.DeploySetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Data
@Component
public class DeploySetTableModel extends AbstractTableModel {
	@Autowired
	DeploySetRepository			deploySetRepository;

	DeploySetTableColumnModel	colModel		= new DeploySetTableColumnModel();

	private List<DeploySet>		deploySetList	= new ArrayList<>();

	@Override
	public int getRowCount() {
		return deploySetList.size();
	}

	@Override
	public int getColumnCount() {
		return colModel.getColumnCount();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return deploySetList.get(rowIndex).getId();
		case 1:
			return deploySetList.get(rowIndex).getStatus();
		case 2:
			return deploySetList.get(rowIndex).getPrevious() == null ? "N" : deploySetList.get(rowIndex).getPrevious().getId();
		case 3:
			return new DateLabel(deploySetList.get(rowIndex).getTimestamp());
		}
		return null;
	}

	public void refresh() {
		deploySetList = deploySetRepository.findAll();
		fireTableDataChanged();
	}

	public List<DeploySet> getSelectedSet(int[] selectedRows) {
		List<DeploySet> res = new ArrayList<>();
		for (int row : selectedRows) {
			res.add(deploySetList.get(row));
		}
		return res;
	}

}

@SuppressWarnings("serial")
class DeploySetTableColumnModel extends DefaultTableColumnModel {
	public DeploySetTableColumnModel() {
		addColumn(col(0, 30, "ID"));
		addColumn(col(1, 50, "Status"));
		addColumn(col(2, 50, "Prev"));
		addColumn(col(3, "Time"));
	}
}