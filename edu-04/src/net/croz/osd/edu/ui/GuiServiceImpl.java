package net.croz.osd.edu.ui;

import javax.swing.SwingUtilities;

import net.croz.osd.edu.ui.element.MainFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GuiServiceImpl implements GuiService {
	@Autowired
	MainFrame mainFrame;
	
	@Override
	public void createAndShowGUI() {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	mainFrame.init();
            }
        });
	}
}
