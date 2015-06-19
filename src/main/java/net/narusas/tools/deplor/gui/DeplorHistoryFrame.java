package net.narusas.tools.deplor.gui;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


public class DeplorHistoryFrame extends JFrame {

    private JPanel contentPane;
    private JTable table;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {

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


    /**
     * Create the frame.
     */
    public DeplorHistoryFrame() {

        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Deploy list", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));

        JPanel panel_1 = new JPanel();
        panel.add(panel_1, BorderLayout.SOUTH);

        JButton btnNewButton = new JButton("Select");
        panel_1.add(btnNewButton);

        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        table.setRowHeight(25);
        table.setModel(new DefaultTableModel(new Object[][] {{null, null, null},}, new String[] {"Time", "Requester", "Revision"}));
        scrollPane.setViewportView(table);
    }

}
