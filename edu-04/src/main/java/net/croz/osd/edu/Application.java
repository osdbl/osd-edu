package net.croz.osd.edu;

import net.croz.osd.edu.conf.AppConfig;
import net.croz.osd.edu.conf.ShapeConfig;
import net.croz.osd.edu.conf.SwingConfig;
import net.croz.osd.edu.ui.GuiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Application {
	static ApplicationContext ctx = new AnnotationConfigApplicationContext(
		AppConfig.class, 
		SwingConfig.class);
	
	@Autowired GuiService guiService;
	
	public static void main(String[] args) {
		ShapeConfig.configure("conf/shape.properties");
		Application application = ctx.getBean(Application.class);
		application.runGui();
	}
	
	private void runGui() {
		guiService.createAndShowGUI();
	}

}
