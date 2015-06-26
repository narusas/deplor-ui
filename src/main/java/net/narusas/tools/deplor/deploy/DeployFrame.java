package net.narusas.tools.deplor.deploy;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Dimension;

import javax.swing.border.EtchedBorder;

import java.awt.Color;

import javax.swing.border.LineBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

@SuppressWarnings("serial")
public class DeployFrame extends JFrame {

	private JPanel				contentPane;
	private JTextField			requestFilteringTextField;
	private JTable				requestsTable;
	private JTable				workingSetTable;
	private JTable				requestChangesTable;
	private JTable				deploySetsChangesTable;
	private JTable				candidatesChangesTable;
	private JTable				deployHistoryTable;
	private JTable				candidatesDeploySetTable;
	private JList				stageList;
	private JButton				addToWorkingButton;
	private JButton				removeRequestButton;
	private DeployController	controller;
	private JComboBox			repositoryComboBox;
	private JComboBox			branchComboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					DeployFrame frame = new DeployFrame();
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
	public DeployFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1270, 710);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.3);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel.add(splitPane, BorderLayout.CENTER);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(173, 216, 230));
		panel_4.setBorder(new TitledBorder(null, "Working", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setLeftComponent(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setBorder(null);
		splitPane_1.setResizeWeight(0.3);
		panel_4.add(splitPane_1, BorderLayout.CENTER);

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(null);
		splitPane_1.setLeftComponent(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));

		JPanel panel_17 = new JPanel();
		panel_17.setBorder(new TitledBorder(null, "Request", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.add(panel_17, BorderLayout.CENTER);
		panel_17.setLayout(new BorderLayout(0, 0));

		JPanel panel_14 = new JPanel();
		panel_17.add(panel_14);
		panel_14.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(70, 70));
		panel_14.add(scrollPane, BorderLayout.CENTER);

		requestsTable = new JTable();
		scrollPane.setViewportView(requestsTable);

		requestFilteringTextField = new JTextField();
		
		panel_14.add(requestFilteringTextField, BorderLayout.NORTH);
		requestFilteringTextField.setColumns(10);

		JPanel panel_12 = new JPanel();
		panel_17.add(panel_12, BorderLayout.EAST);
		panel_12.setLayout(new BoxLayout(panel_12, BoxLayout.Y_AXIS));

		addToWorkingButton = new JButton("");
		addToWorkingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.addToWorking();
			}
		});
		addToWorkingButton.setToolTipText("Add to Working Set");
		addToWorkingButton.setIcon(new ImageIcon(DeployFrame.class.getResource("/icons/arrow_right.png")));
		panel_12.add(addToWorkingButton);

		JPanel panel_7 = new JPanel();
		splitPane_1.setRightComponent(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setBorder(null);
		splitPane_2.setResizeWeight(0.5);
		panel_7.add(splitPane_2, BorderLayout.CENTER);

		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(null, "Working Set", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane_2.setLeftComponent(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(70, 70));
		panel_8.add(scrollPane_1, BorderLayout.CENTER);

		workingSetTable = new JTable();
		scrollPane_1.setViewportView(workingSetTable);

		JPanel panel_19 = new JPanel();
		panel_8.add(panel_19, BorderLayout.EAST);
		panel_19.setLayout(new BoxLayout(panel_19, BoxLayout.Y_AXIS));

		removeRequestButton = new JButton("");
		removeRequestButton.setToolTipText("Remove selected request");
		removeRequestButton.setIcon(new ImageIcon(DeployFrame.class.getResource("/icons/delete.png")));
		panel_19.add(removeRequestButton);

		JButton applyWorkingSetButton = new JButton("");
		applyWorkingSetButton.setToolTipText("Apply working set");
		applyWorkingSetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		applyWorkingSetButton.setIcon(new ImageIcon(DeployFrame.class.getResource("/icons/accept.png")));
		panel_19.add(applyWorkingSetButton);

		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "Changes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane_2.setRightComponent(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setPreferredSize(new Dimension(70, 70));
		panel_9.add(scrollPane_2, BorderLayout.CENTER);

		requestChangesTable = new JTable();
		scrollPane_2.setViewportView(requestChangesTable);

		JPanel panel_20 = new JPanel();
		panel_9.add(panel_20, BorderLayout.EAST);
		panel_20.setLayout(new BoxLayout(panel_20, BoxLayout.Y_AXIS));

		JButton removeChangeFromWorkingSetButton = new JButton("");
		removeChangeFromWorkingSetButton.setToolTipText("Remove selected change from working set");
		removeChangeFromWorkingSetButton.setIcon(new ImageIcon(DeployFrame.class.getResource("/icons/delete.png")));
		panel_20.add(removeChangeFromWorkingSetButton);

		JPanel panel_5 = new JPanel();
		splitPane.setRightComponent(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane_3 = new JSplitPane();
		splitPane_3.setResizeWeight(0.3);
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_5.add(splitPane_3, BorderLayout.CENTER);

		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(102, 205, 170));
		panel_10.setBorder(new TitledBorder(null, "Candidates", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane_3.setLeftComponent(panel_10);
		panel_10.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane_4 = new JSplitPane();
		splitPane_4.setBorder(null);
		splitPane_4.setResizeWeight(0.2);
		panel_10.add(splitPane_4, BorderLayout.CENTER);

		JPanel panel_13 = new JPanel();
		panel_13.setBorder(null);
		splitPane_4.setLeftComponent(panel_13);
		panel_13.setLayout(new BorderLayout(0, 0));

		JPanel panel_16 = new JPanel();
		panel_13.add(panel_16, BorderLayout.EAST);
		panel_16.setLayout(new BoxLayout(panel_16, BoxLayout.Y_AXIS));

		JButton cloneCandidatesToWorkingButton = new JButton("");
		cloneCandidatesToWorkingButton.setToolTipText("Clone to Working Set");
		cloneCandidatesToWorkingButton.setIcon(new ImageIcon(DeployFrame.class.getResource("/icons/arrow_up.png")));
		panel_16.add(cloneCandidatesToWorkingButton);

		JButton applySelectedCandidateButton = new JButton("");
		applySelectedCandidateButton.setToolTipText("Apply to Stage");
		applySelectedCandidateButton.setIcon(new ImageIcon(DeployFrame.class.getResource("/icons/accept.png")));
		panel_16.add(applySelectedCandidateButton);

		JPanel panel_18 = new JPanel();
		panel_18.setBorder(new TitledBorder(null, "Deploy Sets", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_13.add(panel_18, BorderLayout.CENTER);
		panel_18.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_3 = new JScrollPane();
		panel_18.add(scrollPane_3, BorderLayout.CENTER);

		candidatesDeploySetTable = new JTable();
		scrollPane_3.setViewportView(candidatesDeploySetTable);

		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new TitledBorder(null, "Changes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane_4.setRightComponent(panel_15);
		panel_15.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_4 = new JScrollPane();
		panel_15.add(scrollPane_4, BorderLayout.CENTER);

		deploySetsChangesTable = new JTable();
		scrollPane_4.setViewportView(deploySetsChangesTable);

		JPanel panel_11 = new JPanel();
		panel_11.setBackground(new Color(205, 92, 92));
		panel_11.setBorder(new TitledBorder(null, "Deploy", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane_3.setRightComponent(panel_11);
		panel_11.setLayout(new BorderLayout(0, 0));

		JPanel panel_22 = new JPanel();
		panel_11.add(panel_22, BorderLayout.CENTER);
		panel_22.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane_5 = new JSplitPane();
		splitPane_5.setBorder(null);
		splitPane_5.setResizeWeight(0.5);
		panel_22.add(splitPane_5);

		JPanel panel_21 = new JPanel();
		splitPane_5.setLeftComponent(panel_21);
		panel_21.setLayout(new BorderLayout(0, 0));

		JPanel panel_25 = new JPanel();
		panel_25.setBorder(new TitledBorder(null, "Changes & Conflicts", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_21.add(panel_25, BorderLayout.CENTER);
		panel_25.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setPreferredSize(new Dimension(70, 4));
		panel_25.add(scrollPane_5, BorderLayout.CENTER);

		candidatesChangesTable = new JTable();
		scrollPane_5.setViewportView(candidatesChangesTable);

		JPanel panel_24 = new JPanel();
		scrollPane_5.setColumnHeaderView(panel_24);

		JPanel panel_27 = new JPanel();
		panel_25.add(panel_27, BorderLayout.EAST);
		panel_27.setLayout(new BoxLayout(panel_27, BoxLayout.Y_AXIS));

		JButton cancleCandidateButton = new JButton("");
		cancleCandidateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cancleCandidateButton.setIcon(new ImageIcon(DeployFrame.class.getResource("/icons/cancel.png")));
		panel_27.add(cancleCandidateButton);

		JButton applyCandidateToSelectedStageButton = new JButton("");
		applyCandidateToSelectedStageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		applyCandidateToSelectedStageButton.setIcon(new ImageIcon(DeployFrame.class.getResource("/icons/accept.png")));
		panel_27.add(applyCandidateToSelectedStageButton);

		JPanel panel_23 = new JPanel();
		splitPane_5.setRightComponent(panel_23);
		panel_23.setLayout(new BorderLayout(0, 0));

		JPanel panel_26 = new JPanel();
		panel_26.setBorder(new TitledBorder(null, "Stage", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_23.add(panel_26, BorderLayout.WEST);
		panel_26.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setPreferredSize(new Dimension(70, 4));
		panel_26.add(scrollPane_6);

		stageList = new JList();
		stageList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
			}
		});
		stageList.setModel(new AbstractListModel() {
			String[]	values	= new String[] { "DEV  ", "TST", "PRD  " };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane_6.setViewportView(stageList);

		JPanel panel_28 = new JPanel();
		panel_26.add(panel_28, BorderLayout.EAST);
		panel_28.setLayout(new BoxLayout(panel_28, BoxLayout.Y_AXIS));

		JButton rollbackFromLSBButton = new JButton("");
		rollbackFromLSBButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		rollbackFromLSBButton.setIcon(new ImageIcon(DeployFrame.class.getResource("/icons/arrow_undo.png")));
		panel_28.add(rollbackFromLSBButton);

		JButton proceedToNextButton = new JButton("");
		proceedToNextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		proceedToNextButton.setIcon(new ImageIcon(DeployFrame.class.getResource("/icons/arrow_switch.png")));
		panel_28.add(proceedToNextButton);

		JButton mageNewLSBButton = new JButton("");
		mageNewLSBButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mageNewLSBButton.setToolTipText("Make LSB");
		mageNewLSBButton.setIcon(new ImageIcon(DeployFrame.class.getResource("/icons/award_star_gold_2.png")));
		panel_28.add(mageNewLSBButton);

		JPanel panel_29 = new JPanel();
		panel_29.setBorder(new TitledBorder(null, "History", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_23.add(panel_29, BorderLayout.CENTER);
		panel_29.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setPreferredSize(new Dimension(70, 4));
		panel_29.add(scrollPane_7, BorderLayout.CENTER);

		deployHistoryTable = new JTable();
		scrollPane_7.setViewportView(deployHistoryTable);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_2, BorderLayout.WEST);

		JLabel lblNewLabel = new JLabel("Repository");
		panel_2.add(lblNewLabel);

		repositoryComboBox = new JComboBox();
		repositoryComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.repositorySelected(e);
			}
		});
		repositoryComboBox.setMaximumRowCount(20);
		panel_2.add(repositoryComboBox);

		JLabel lblNewLabel_1 = new JLabel("Branch");
		panel_2.add(lblNewLabel_1);

		branchComboBox = new JComboBox();
		branchComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.branchSelected(e);
			}
		});
		branchComboBox.setMaximumRowCount(20);
		panel_2.add(branchComboBox);

		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.EAST);

		JButton btnNewButton = new JButton("New Deploy Set");
		panel_3.add(btnNewButton);
	}

	public JTable getRequestsTable() {
		return requestsTable;
	}

	public JTextField getRequestFilteringTextField() {
		return requestFilteringTextField;
	}

	public JTable getWorkingSetTable() {
		return workingSetTable;
	}

	public JTable getRequestChangesTable() {
		return requestChangesTable;
	}

	public JTable getCandidatesDeploySetTable() {
		return candidatesDeploySetTable;
	}

	public JTable getDeploySetsChangesTable() {
		return deploySetsChangesTable;
	}

	public JTable getCandidatesChangesTable() {
		return candidatesChangesTable;
	}

	public JList getStageList() {
		return stageList;
	}

	public JTable getDeployHistoryTable() {
		return deployHistoryTable;
	}

	public JButton getAddToWorkingButton() {
		return addToWorkingButton;
	}

	public JButton getRemoveRequestButton() {
		return removeRequestButton;
	}

	public void setController(DeployController controller) {
		this.controller = controller;
	}

	public JComboBox getRepositoryComboBox() {
		return repositoryComboBox;
	}

	public JComboBox getBranchComboBox() {
		return branchComboBox;
	}
}
