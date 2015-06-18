package net.narusas.tools.deplor.gui;

import javax.swing.AbstractListModel;

public class SampleListModel extends AbstractListModel<String>{
	String[] data = {"abc", "def"};
	@Override
	public int getSize() {
		return data.length;
	}

	@Override
	public String getElementAt(int index) {
		return data[index];
	}

}
