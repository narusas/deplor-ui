package net.narusas.tools.deplor.deploy;

import javax.swing.table.TableColumn;

public class UIUtil {
	public static TableColumn col(int modelIntex, int width, String label) {
		TableColumn tc = new TableColumn(modelIntex, width);
		tc.setMaxWidth(width);
		tc.setHeaderValue(label);
		return tc;
	}

	public static TableColumn col(int modelIntex, String label) {
		TableColumn tc = new TableColumn(modelIntex, 50);
		tc.setHeaderValue(label);
		return tc;
	}
}
