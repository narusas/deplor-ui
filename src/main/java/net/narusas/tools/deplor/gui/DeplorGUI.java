package net.narusas.tools.deplor.gui;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


public class DeplorGUI extends JFrame {

    private JPanel contentPane;
    private JTextField textField;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                try {
                    DeplorGUI frame = new DeplorGUI();
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
    public DeplorGUI() {

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
        panel_1.add(panel_6, BorderLayout.SOUTH);

        textField = new JTextField();
        panel_6.add(textField);
        textField.setColumns(5);

        JButton btnNewButton = new JButton("search");
        btnNewButton.setMargin(new Insets(2, 2, 2, 2));
        btnNewButton.setPreferredSize(new Dimension(76, 27));
        panel_6.add(btnNewButton);

        JPanel panel_2 = new JPanel();
        splitPane.setRightComponent(panel_2);
        panel_2.setLayout(new BorderLayout(0, 0));

        JPanel panel_3 = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        panel_2.add(panel_3, BorderLayout.NORTH);

        JButton historyButton = new JButton("History");
        panel_3.add(historyButton);

        JPanel panel_5 = new JPanel();
        panel_2.add(panel_5, BorderLayout.CENTER);
        panel_5.setLayout(new BorderLayout(0, 0));

        JTextArea infoRevisionTextArea = new JTextArea();
        infoRevisionTextArea.setEditable(false);
        infoRevisionTextArea.setMargin(new Insets(5, 5, 5, 5));
        infoRevisionTextArea.setPreferredSize(new Dimension(4, 40));
        panel_5.add(infoRevisionTextArea, BorderLayout.NORTH);

        JTextArea requestCommentTxtArea = new JTextArea();
        requestCommentTxtArea.setPreferredSize(new Dimension(4, 50));
        requestCommentTxtArea.setMargin(new Insets(5, 5, 5, 5));
        panel_2.add(requestCommentTxtArea, BorderLayout.SOUTH);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);

        JLabel lblRepository = new JLabel("Repository");
        panel.add(lblRepository);

        JComboBox repositoryList = new JComboBox();
        panel.add(repositoryList);

        JLabel lblBranch = new JLabel("Branch");
        panel.add(lblBranch);

        JComboBox branchList = new JComboBox();
        panel.add(branchList);

        JPanel panel_4 = new JPanel();
        contentPane.add(panel_4, BorderLayout.SOUTH);

        JButton submitButton = new JButton("Submit");
        panel_4.add(submitButton);
    }

}
