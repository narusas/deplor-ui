package net.narusas.tools.deplor.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


public class DeporGUIFrame extends JFrame {

    private JPanel contentPane;
    private JTextField loginId;
    private JTextField loginPass;
    private JTable revListTable;
    private JTable requestList;

    private DeplorController dController;
    private JTextField RevDetailTime;
    private JTextField RevDetailOwner;
    private JTextArea revInfoText;


    /**
     * Launch the application.
     * 
     * For GUI
     */
    // public static void main(String[] args) {
    //
    // EventQueue.invokeLater(new Runnable() {
    //
    // @Override
    // public void run() {
    //
    // try {
    // DeporGUIFrame frame = new DeporGUIFrame();
    // frame.setVisible(true);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }
    // });
    // }


    /**
     * Create the frame.
     */
    public DeporGUIFrame(final DeplorController dController) {

        setTitle("Request Deploy");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 700);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel mainTop = new JPanel();
        contentPane.add(mainTop, BorderLayout.NORTH);
        mainTop.setLayout(new BorderLayout(0, 0));

        JPanel branchListPanel = new JPanel();
        mainTop.add(branchListPanel, BorderLayout.WEST);

        JLabel lblNewLabel_2 = new JLabel("Repo");
        branchListPanel.add(lblNewLabel_2);

        JComboBox repostoryCombo = new JComboBox();
        dController.initRepositoryList(repostoryCombo);
        branchListPanel.add(repostoryCombo);



        JLabel lblRepository = new JLabel("Branch");
        branchListPanel.add(lblRepository);

        JComboBox branchComboBox = new JComboBox();
        dController.initBranchList(branchComboBox);
        branchListPanel.add(branchComboBox);

        JPanel loginPanel = new JPanel();
        mainTop.add(loginPanel, BorderLayout.EAST);

        JLabel lblNewLabel = new JLabel("ID");
        loginPanel.add(lblNewLabel);

        loginId = new JTextField();
        loginPanel.add(loginId);
        loginId.setColumns(8);

        JLabel lblNewLabel_1 = new JLabel("PASS");
        loginPanel.add(lblNewLabel_1);

        loginPass = new JTextField();
        loginPanel.add(loginPass);
        loginPass.setColumns(8);

        JButton btnNewButton = new JButton("Sing In");
        loginPanel.add(btnNewButton);

        JPanel mainBottom = new JPanel();
        contentPane.add(mainBottom, BorderLayout.SOUTH);
        mainBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JButton btnNewButton_3 = new JButton("Submit");
        mainBottom.add(btnNewButton_3);

        JPanel mainCenterLayer = new JPanel();
        contentPane.add(mainCenterLayer, BorderLayout.CENTER);
        mainCenterLayer.setLayout(new BorderLayout(0, 0));

        JPanel splitePanel = new JPanel();
        mainCenterLayer.add(splitePanel, BorderLayout.CENTER);
        splitePanel.setLayout(new BorderLayout(0, 0));

        JSplitPane splitPane = new JSplitPane();
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setResizeWeight(0.2);
        splitePanel.add(splitPane, BorderLayout.CENTER);

        JSplitPane splitPane_1 = new JSplitPane();
        splitPane.setLeftComponent(splitPane_1);

        JPanel panel = new JPanel();
        splitPane_1.setRightComponent(panel);
        panel.setLayout(new BorderLayout(0, 0));

        JPanel RevDetailPanel = new JPanel();
        RevDetailPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Revision Info", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        RevDetailPanel.setPreferredSize(new Dimension(10, 130));
        panel.add(RevDetailPanel, BorderLayout.NORTH);
        RevDetailPanel.setLayout(new BorderLayout(0, 0));

        JPanel historyPanel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) historyPanel.getLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        RevDetailPanel.add(historyPanel, BorderLayout.NORTH);

        JButton btnNewButton_1 = new JButton("Find history");
        btnNewButton_1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {

                        try {
                            DeplorHistoryFrame frame = new DeplorHistoryFrame();
                            frame.setVisible(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
        historyPanel.add(btnNewButton_1);

        JPanel panel_4 = new JPanel();
        RevDetailPanel.add(panel_4, BorderLayout.CENTER);
        panel_4.setLayout(new BorderLayout(0, 0));

        JPanel panel_5 = new JPanel();
        FlowLayout flowLayout_2 = (FlowLayout) panel_5.getLayout();
        flowLayout_2.setAlignment(FlowLayout.LEFT);
        panel_4.add(panel_5, BorderLayout.NORTH);

        JLabel lblNewLabel_3 = new JLabel("Time");
        panel_5.add(lblNewLabel_3);

        RevDetailTime = new JTextField();
        RevDetailTime.setEditable(false);
        panel_5.add(RevDetailTime);
        RevDetailTime.setColumns(20);

        JLabel lblOwner = new JLabel("Owner");
        panel_5.add(lblOwner);

        RevDetailOwner = new JTextField();
        RevDetailOwner.setEditable(false);
        panel_5.add(RevDetailOwner);
        RevDetailOwner.setColumns(10);

        JPanel panel_6 = new JPanel();
        panel_6.setForeground(new Color(128, 128, 128));
        panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_4.add(panel_6, BorderLayout.CENTER);
        panel_6.setLayout(new BorderLayout(0, 0));

        revInfoText = new JTextArea();
        revInfoText.setEditable(false);
        revInfoText.setBackground(new Color(255, 255, 224));
        panel_6.add(revInfoText);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(2, 80));
        panel.add(scrollPane, BorderLayout.CENTER);

        revListTable = new JTable();
        revListTable.setRowMargin(3);
        revListTable.setRowHeight(25);
        revListTable.setModel(new DefaultTableModel(new Object[][] { {null, null}, {null, null},}, new String[] {"Type", "Path"}));
        revListTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        revListTable.getColumnModel().getColumn(0).setMinWidth(100);
        revListTable.getColumnModel().getColumn(0).setMaxWidth(100);
        revListTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        revListTable.getColumnModel().getColumn(1).setMinWidth(200);
        scrollPane.setViewportView(revListTable);

        JPanel applyPanel = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) applyPanel.getLayout();
        panel.add(applyPanel, BorderLayout.SOUTH);

        JButton btnApply = new JButton("\u25BC Apply selected items");
        btnApply.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                dController.addSelectedItem();
            }
        });
        applyPanel.add(btnApply);
        btnApply.setSize(new Dimension(100, 30));

        JPanel branchPanel = new JPanel();
        branchPanel.setPreferredSize(new Dimension(180, 10));
        branchPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Revision List", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        splitPane_1.setLeftComponent(branchPanel);
        branchPanel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane_1 = new JScrollPane();
        branchPanel.add(scrollPane_1, BorderLayout.CENTER);

        JList revisionList = new JList();
        revisionList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {

                dController.revisionSelected();
            }
        });
        revisionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dController.initRevisionList(revisionList);
        scrollPane_1.setViewportView(revisionList);

        JPanel requestPanel = new JPanel();
        requestPanel.setBackground(new Color(173, 216, 230));
        requestPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Request Deploy", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(128, 0, 0)));
        splitPane.setRightComponent(requestPanel);
        requestPanel.setLayout(new BorderLayout(0, 0));

        JPanel panel_1 = new JPanel();
        requestPanel.add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane_3 = new JScrollPane();
        scrollPane_3.setPreferredSize(new Dimension(2, 50));
        panel_1.add(scrollPane_3, BorderLayout.CENTER);

        requestList = new JTable();
        requestList.setModel(new DefaultTableModel(new Object[][] {{null, null, null, null, null},}, new String[] {"New column",
                "New column", "New column", "New column", "New column"}));
        scrollPane_3.setViewportView(requestList);

        JPanel panel_3 = new JPanel();
        panel_1.add(panel_3, BorderLayout.SOUTH);

        JButton btnNewButton_2 = new JButton("Remove seleted items");
        panel_3.add(btnNewButton_2);

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new TitledBorder(null, "Comment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        requestPanel.add(panel_2, BorderLayout.SOUTH);
        panel_2.setLayout(new BorderLayout(0, 0));

        JPanel panel_7 = new JPanel();
        panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_2.add(panel_7, BorderLayout.NORTH);
        panel_7.setLayout(new BorderLayout(0, 0));

        JTextArea comment = new JTextArea();
        comment.setBackground(new Color(255, 255, 224));
        panel_7.add(comment);
        comment.setPreferredSize(new Dimension(4, 40));
    }


    public JTextField getRevDetailTime() {

        return RevDetailTime;
    }


    public JTextField getRevDetailOwner() {

        return RevDetailOwner;
    }


    public JTextArea getRevInfoText() {

        return revInfoText;
    }


    public JTable getRevListTable() {

        return revListTable;
    }


    public JTable getRequestList() {

        return requestList;
    }
}
