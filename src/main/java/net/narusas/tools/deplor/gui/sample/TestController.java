package net.narusas.tools.deplor.gui.sample;

import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public interface TestController {

	void setFrame(TestFrame frame);

	void added();

	void removed();

	void imports();
	
	void initPairTable(JTable table);


}
