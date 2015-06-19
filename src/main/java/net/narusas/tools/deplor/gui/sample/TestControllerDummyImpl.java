package net.narusas.tools.deplor.gui.sample;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import net.narusas.tools.deplor.domain.model.Repository;
import net.narusas.tools.deplor.domain.repository.RepoRepository;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class TestControllerDummyImpl implements TestController, InitializingBean {

	@Autowired
	RepoRepository		repoRepository;

	private TestFrame	ui;

	@Override
	public void setFrame(TestFrame ui) {
		this.ui = ui;
	}

	@Override
	public void added() {
		// TODO Auto-generated method stub

	}

	@Override
	public void removed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void imports() {
		// TODO Auto-generated method stub

	}

	public TableModel getPairTableModel() {
		final List<Repository> repos = repoRepository.findAll();
		return new DefaultTableModel() {

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				switch (columnIndex) {
				case 0:
					return repos.get(rowIndex).getId();
				case 1:
					return repos.get(rowIndex).getName();
				case 2:
					return repos.get(rowIndex).getUrl();
				}

				return null;

			}

			@Override
			public int getRowCount() {
				return repos.size();
			}

			@Override
			public int getColumnCount() {
				return 3;
			}

			@Override
			public String getColumnName(int column) {
				switch (column) {
				case 0:
					return "ID";
				case 1:
					return "Name";
				case 2:
					return "URL";
				}
				return null;
			}
		};
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Initialized");
	}

	@Override
	public void initPairTable(JTable table) {
		table.setModel(getPairTableModel());
		table.getColumnModel().getColumn(0).setMaxWidth(60);
		table.getColumnModel().getColumn(1).setMaxWidth(60);
		table.getColumnModel().getColumn(2).setMaxWidth(400);

	}

}
