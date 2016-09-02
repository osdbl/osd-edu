package net.croz.osd.edu.ui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.croz.osd.edu.ui.element.MainFrame;
import net.croz.osd.edu.ui.element.UsersTable;

@Component
public class GuiServiceImpl implements GuiService {
	@Autowired MainFrame mainFrame;
	
	
	@Override
	public void createAndShowGUI() {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	mainFrame.init("app.title");
            }
        });
	}
	
	@Override
	public void doUserAction(ActionEvent e) {
		
		
		CardLayout cardLayout = (CardLayout) mainFrame.getContentPane().getLayout();
		cardLayout.show(mainFrame.getContentPane(), GuiService.USER_PANEL);
	
	}
}
