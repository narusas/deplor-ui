package net.narusas.tools.deplor.gui.sample;

import java.awt.EventQueue;

import net.narusas.tools.deplor.config.ApplicationConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class Main {
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
					
					TestController controller = new TestControllerDummyImpl();
					applicationContext.getAutowireCapableBeanFactory().autowireBean(controller);
					
					
					TestFrame frame = new TestFrame(controller);
					controller.setFrame(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
