package net.narusas.tools.deplor.deploy;

import java.awt.EventQueue;

import net.narusas.tools.deplor.config.ApplicationConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("resource")
			@Override
			public void run() {
				try {
					ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
					MainController controller = applicationContext.getBean(MainController.class);
					UI frame = new UI();
					frame.setController(controller);
					controller.setUI(frame);
					controller.init();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
