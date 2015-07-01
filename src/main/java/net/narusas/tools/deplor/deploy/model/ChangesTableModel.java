package net.narusas.tools.deplor.deploy.model;

import static net.narusas.tools.deplor.deploy.UIUtil.col;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumnModel;

import lombok.Data;
import net.narusas.tools.deplor.domain.model.Change;

@Data
@SuppressWarnings("serial")
public class ChangesTableModel extends AbstractTableModel {
	List<Change>		changes		= new ArrayList<>();
	TableColumnModel	colModel	= new ChangesTableColumnModel();

	public void update(List<Change> newChanges) {
		changes = newChanges;
		fireTableDataChanged();
	}

	public void add(Collection<Change> addition) {
		for (Change change : addition) {
			changes.add(change);
		}
		fireTableDataChanged();
	}

	public void clear() {
		changes.clear();
		fireTableDataChanged();
	}

	public void remove(Collection<Change> addition) {
		for (Change change : addition) {
			changes.remove(change);
		}
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return changes.size();
	}

	@Override
	public int getColumnCount() {
		return colModel.getColumnCount();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return changes.get(rowIndex).getRevision().getVersion();
		case 1:
			return changes.get(rowIndex).getType();
		case 2:
			return changes.get(rowIndex).getPath();
		}
		return null;
	}

	public static class ChangesTableColumnModel extends DefaultTableColumnModel {
		public ChangesTableColumnModel() {
			addColumn(col(0, 50, "Revision"));
			addColumn(col(1, 50, "Type"));
			addColumn(col(2, "Path"));
		}
	}

}
