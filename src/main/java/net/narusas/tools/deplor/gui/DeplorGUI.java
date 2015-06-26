package net.narusas.tools.deplor.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import net.narusas.tools.deplor.gui.old.DeplorController;


public class DeplorGUI extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTable table;
    private JTable table_1;
    private JTextField textField_1;
    private JPasswordField passwordField;
    private DeplorController dController;


    /**
     * Launch the application. //
     */
    // public static void main(String[] args) {
    //
    // EventQueue.invokeLater(new Runnable() {
    //
    // @Override
    // public void run() {
    //
    // try {
    // DeplorGUI frame = new DeplorGUI();
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
    public DeplorGUI(final DeplorController dController) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 972, 531);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JSplitPane splitPane = new JSplitPane();
        splitPane.setResizeWeight(0.1);
        contentPane.add(splitPane, BorderLayout.CENTER);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "Revision", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        splitPane.setLeftComponent(panel_1);
        panel_1.setLayout(new BorderLayout(0, 0));

        JList revisionList = new JList();
        revisionList.setModel(new AbstractListModel() {

            String[] values = new String[] {"aaaaaaaaaaaaa", "bbbbbbbbbbbb", "cccccccccccccc"};


            @Override
            public int getSize() {

                return values.length;
            }


            @Override
            public Object getElementAt(int index) {

                return values[index];
            }
        });
        panel_1.add(revisionList, BorderLayout.CENTER);

        JPanel panel_6 = new JPanel();
        panel_6.setBackground(SystemColor.activeCaption);
        panel_1.add(panel_6, BorderLayout.SOUTH);

        textField = new JTextField();
        panel_6.add(textField);
        textField.setColumns(7);

        JButton btnNewButton = new JButton("");
        btnNewButton.setIcon(new ImageIcon(DeplorGUI.class.getResource("/icons/application_form_magnify.png")));
        btnNewButton.setMargin(new Insets(2, 2, 2, 2));
        btnNewButton.setPreferredSize(new Dimension(40, 25));
        panel_6.add(btnNewButton);

        JPanel panel_2 = new JPanel();
        splitPane.setRightComponent(panel_2);
        panel_2.setLayout(new BorderLayout(0, 0));

        JPanel panel_3 = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        panel_2.add(panel_3, BorderLayout.NORTH);

        JButton historyButton = new JButton("History");
        historyButton.setIcon(new ImageIcon(DeplorGUI.class.getResource("/icons/application_cascade.png")));
        panel_3.add(historyButton);

        JPanel panel_5 = new JPanel();
        panel_2.add(panel_5, BorderLayout.CENTER);
        panel_5.setLayout(new BorderLayout(0, 0));

        JTextArea infoRevisionTextArea = new JTextArea();
        infoRevisionTextArea.setBackground(SystemColor.info);
        infoRevisionTextArea.setEditable(false);
        infoRevisionTextArea.setMargin(new Insets(5, 5, 5, 5));
        infoRevisionTextArea.setPreferredSize(new Dimension(4, 40));
        panel_5.add(infoRevisionTextArea, BorderLayout.NORTH);

        JSplitPane splitPane_1 = new JSplitPane();
        splitPane_1.setResizeWeight(0.4);
        splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
        panel_5.add(splitPane_1, BorderLayout.CENTER);

        JPanel panel_7 = new JPanel();
        panel_7.setBorder(new TitledBorder(null, "Change List", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.desktop));
        splitPane_1.setLeftComponent(panel_7);
        panel_7.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane_1 = new JScrollPane();
        panel_7.add(scrollPane_1, BorderLayout.CENTER);

        table = new JTable();
        scrollPane_1.setViewportView(table);

        JPanel panel_8 = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) panel_8.getLayout();
        panel_7.add(panel_8, BorderLayout.SOUTH);

        JButton btnAdd = new JButton("");
        btnAdd.setIcon(new ImageIcon(DeplorGUI.class.getResource("/icons/application_put.png")));
        btnAdd.setSelectedIcon(new ImageIcon(DeplorGUI.class.getResource("/icons/application_put.png")));
        panel_8.add(btnAdd);

        JPanel panel_9 = new JPanel();
        panel_9.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Deploy List", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(0, 0, 0)));
        splitPane_1.setRightComponent(panel_9);
        panel_9.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane_2 = new JScrollPane();
        panel_9.add(scrollPane_2, BorderLayout.CENTER);

        table_1 = new JTable();
        scrollPane_2.setViewportView(table_1);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Deploy Comment", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(0, 0, 0)));
        scrollPane.setPreferredSize(new Dimension(2, 60));
        panel_2.add(scrollPane, BorderLayout.SOUTH);

        JTextArea textArea = new JTextArea();
        scrollPane.setViewportView(textArea);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new BorderLayout(0, 0));

        JPanel panel_10 = new JPanel();
        panel.add(panel_10, BorderLayout.WEST);

        JLabel lblRepository = new JLabel("Repository");
        panel_10.add(lblRepository);

        JComboBox repositoryList = new JComboBox();
        repositoryList.setModel(new DefaultComboBoxModel(new String[] {"aaaaaaaaaaaaaaaaaa", "vvvvvvvvvvvvvvvvvvvvv"}));
        panel_10.add(repositoryList);

        JLabel lblBranch = new JLabel("Branch");
        panel_10.add(lblBranch);

        JComboBox branchList = new JComboBox();
        branchList.setModel(new DefaultComboBoxModel(new String[] {"ccccccccccccccccccccc", "ccccccccccccccccccccccc"}));
        panel_10.add(branchList);

        JPanel panel_11 = new JPanel();
        panel_11.setBackground(SystemColor.text);
        panel.add(panel_11, BorderLayout.EAST);

        JLabel lblNewLabel = new JLabel("ID");
        panel_11.add(lblNewLabel);

        textField_1 = new JTextField();
        panel_11.add(textField_1);
        textField_1.setColumns(5);

        JLabel lblNewLabel_1 = new JLabel("Pass");
        panel_11.add(lblNewLabel_1);

        passwordField = new JPasswordField();
        passwordField.setColumns(5);
        panel_11.add(passwordField);

        JButton btnNewButton_1 = new JButton("");
        btnNewButton_1.setMargin(new Insets(2, 4, 2, 4));
        btnNewButton_1.setIcon(new ImageIcon(DeplorGUI.class.getResource("/icons/lock_go.png")));
        panel_11.add(btnNewButton_1);

        JPanel panel_4 = new JPanel();
        contentPane.add(panel_4, BorderLayout.SOUTH);

        JButton submitButton = new JButton("Submit");
        submitButton.setIcon(new ImageIcon(DeplorGUI.class.getResource("/icons/accept.png")));
        panel_4.add(submitButton);
    }

}
