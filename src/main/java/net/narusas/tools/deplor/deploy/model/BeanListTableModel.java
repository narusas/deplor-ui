package net.narusas.tools.deplor.deploy.model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

@SuppressWarnings("serial")
public class BeanListTableModel<T> extends AbstractTableModel {
	private List<T>		list	= new ArrayList<>();
	private String[]	properties;

	public BeanListTableModel(String... properties) {
		super();
		this.properties = properties;
	}

	public void updateList(Collection<T> data) throws RuntimeException {
		list = new ArrayList<T>(data);
		fireTableDataChanged();
	}

	public List<T> selectedItems(int[] rows) {
		List<T> res = new ArrayList<>();
		for (int row : rows) {
			res.add(list.get(row));
		}
		return res;
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return properties.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		try {
			T item = list.get(rowIndex);
			return BeanUtils.getPropertyDescriptor(item.getClass(), properties[columnIndex]).getReadMethod().invoke(item, null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
