package net.narusas.tools.deplor.gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.narusas.tools.deplor.config.ApplicationConfig;
import net.narusas.tools.deplor.gui.old.DeplorController;
import net.narusas.tools.deplor.gui.old.DeplorControllerImpl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class DeplorGUIMain extends JFrame {

    private JPanel contentPane;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                try {

                    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);

                    DeplorController controller = new DeplorControllerImpl();
                    applicationContext.getAutowireCapableBeanFactory().autowireBean(controller);

                    DeplorGUI frame = new DeplorGUI(controller);
                    // controller.setDeporFrame(frame);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
