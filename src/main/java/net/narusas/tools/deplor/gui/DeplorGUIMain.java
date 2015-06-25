package net.narusas.tools.deplor.gui;


import java.awt.EventQueue;

import net.narusas.tools.deplor.config.ApplicationConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class DeplorGUIMain {

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
