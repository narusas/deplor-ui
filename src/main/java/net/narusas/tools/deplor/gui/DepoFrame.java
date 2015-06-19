package net.narusas.tools.deplor.gui;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


public class DepoFrame extends JFrame {

    private JPanel contentPane;
    private JTextField loginId;
    private JTextField loginPass;
    private JTextField txtDdddd;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                try {
                    DepoFrame frame = new DepoFrame();
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
    public DepoFrame() {

        setTitle("Request Deploy");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 816, 472);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel top = new JPanel();
        contentPane.add(top, BorderLayout.NORTH);
        top.setLayout(new BorderLayout(0, 0));

        JPanel branchListPanel = new JPanel();
        top.add(branchListPanel, BorderLayout.WEST);

        JLabel lblNewLabel_2 = new JLabel("Branch List");
        branchListPanel.add(lblNewLabel_2);

        JComboBox repostoryCombo = new JComboBox();
        repostoryCombo.setModel(new DefaultComboBoxModel(new String[] {"AAAAAAAAAAAAAAAAAAA", "BBBBBBBBBBBBBBBBBBBBB"}));
        branchListPanel.add(repostoryCombo);

        JPanel loginPanel = new JPanel();
        top.add(loginPanel, BorderLayout.EAST);

        JLabel lblNewLabel = new JLabel("ID");
        loginPanel.add(lblNewLabel);

        loginId = new JTextField();
        loginPanel.add(loginId);
        loginId.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("PASS");
        loginPanel.add(lblNewLabel_1);

        loginPass = new JTextField();
        loginPanel.add(loginPass);
        loginPass.setColumns(10);

        JButton btnNewButton = new JButton("Sing In");
        loginPanel.add(btnNewButton);

        JPanel statusPanel = new JPanel();
        statusPanel.setBorder(new TitledBorder(null, "Status", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPane.add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setLayout(new BorderLayout(0, 0));

        JLabel lblNewLabel_3 = new JLabel("New label");
        statusPanel.add(lblNewLabel_3, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));

        JSplitPane splitPane = new JSplitPane();
        panel.add(splitPane, BorderLayout.CENTER);

        JPanel panel_1 = new JPanel();
        panel.add(panel_1, BorderLayout.SOUTH);
        panel_1.setLayout(new BorderLayout(0, 0));

        txtDdddd = new JTextField();
        txtDdddd.setText("ddddd");
        panel_1.add(txtDdddd, BorderLayout.NORTH);
        txtDdddd.setColumns(10);
    }

}
