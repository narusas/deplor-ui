package net.narusas.tools.deplor.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextArea;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JSplitPane;


import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.ImageIcon;

import lombok.Data;
public class MainFrame extends JFrame {

	private JPanel	contentPane;
	private JTextField idTextField;
	private JPasswordField passwordField;
	private JTable resourceTable;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("Deplor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel controlpanel = new JPanel();
		controlpanel.setBorder(new TitledBorder(null, "Controls", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(controlpanel, BorderLayout.NORTH);
		controlpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblRepo = new JLabel("Repo");
		controlpanel.add(lblRepo);
		
		JComboBox repositoryComboBox = new JComboBox();
		controlpanel.add(repositoryComboBox);
		
		JLabel lblId = new JLabel("id");
		controlpanel.add(lblId);
		
		idTextField = new JTextField();
		idTextField.setText("idTextField");
		controlpanel.add(idTextField);
		idTextField.setColumns(10);
		
		JLabel lblPw = new JLabel("pw");
		controlpanel.add(lblPw);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		controlpanel.add(passwordField);
		
		JLabel label = new JLabel("");
		controlpanel.add(label);
		
		JLabel lblBranch = new JLabel("Branch");
		controlpanel.add(lblBranch);
		
		JComboBox brancheComboBox = new JComboBox();
		controlpanel.add(brancheComboBox);
		
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new TitledBorder(null, "Status", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(statusPanel, BorderLayout.SOUTH);
		statusPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("New label");
		statusPanel.add(lblNewLabel, BorderLayout.CENTER);
		
		JProgressBar progressBar = new JProgressBar();
		statusPanel.add(progressBar, BorderLayout.EAST);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4, BorderLayout.EAST);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.7);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(0.1);
		splitPane.setLeftComponent(splitPane_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Revisions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane_1.setLeftComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		JList revisionList = new JList();
		scrollPane.setViewportView(revisionList);
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_5, BorderLayout.NORTH);
		
		JLabel lblFilter = new JLabel("Filter");
		panel_5.add(lblFilter);
		
		textField = new JTextField();
		panel_5.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/arrow_rotate_clockwise.png")));
		panel_5.add(btnNewButton_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Resources", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane_1.setRightComponent(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1);
		
		resourceTable = new JTable();
		scrollPane_1.setViewportView(resourceTable);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_3, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] {0, 0, 2};
		gbl_panel_3.rowHeights = new int[] {0, 0, 0, 4};
		gbl_panel_3.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel_3.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 0;
		panel_3.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 1;
		panel_3.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 2;
		panel_3.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JPanel panel_8 = new JPanel();
		panel_1.add(panel_8, BorderLayout.NORTH);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Selected", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setRightComponent(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_6.add(scrollPane_2, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane_2.setViewportView(table);
		
		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7, BorderLayout.NORTH);
		
		JButton addButton = new JButton("");
		addButton.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/add.png")));
		panel_7.add(addButton);
		
		JButton deleteButton = new JButton("");
		deleteButton.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/cross.png")));
		panel_7.add(deleteButton);
		
		JButton submitButton = new JButton("Submit");
		submitButton.setIcon(new ImageIcon(MainFrame.class.getResource("/icons/accept.png")));
		panel_7.add(submitButton);
	}

}
