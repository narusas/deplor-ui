package net.narusas.tools.deplor.gui.sample;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ListSelectionModel;

import lombok.Data;
import lombok.Setter;

public class TestFrame extends JFrame {

	private JPanel			contentPane;
	private JTextField		aField;
	private JTextField		bField;
	private JTable			pairTable;

	@Setter
	private TestController	controller;

	/**
	 * Create the frame.
	 */
	public TestFrame(TestController c) {
		controller = c;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("New label");
		panel.add(lblNewLabel);

		aField = new JTextField();
		panel.add(aField);
		aField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("New label");
		panel.add(lblNewLabel_1);

		bField = new JTextField();
		panel.add(bField);
		bField.setColumns(10);

		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.added();
			}
		});
		panel.add(addButton);

		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.removed();
			}
		});
		panel.add(removeButton);

		JButton importButton = new JButton("Import");
		importButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.imports();
			}
		});
		panel.add(importButton);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);

		pairTable = new JTable();
		pairTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		controller.initPairTable(pairTable);
		scrollPane.setViewportView(pairTable);
	}

	public JTextField getAField() {
		return aField;
	}

	public JTextField getBField() {
		return bField;
	}
}
