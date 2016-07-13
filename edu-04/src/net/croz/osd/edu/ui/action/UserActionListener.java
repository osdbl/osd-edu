package net.croz.osd.edu.ui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import net.croz.osd.edu.ui.GuiService;
import net.croz.osd.edu.ui.element.MainFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
public class UserActionListener implements ActionListener {
	@Autowired GuiService guiService;
	@Autowired MainFrame mainFrame;
	
	@Override 
	public void actionPerformed(ActionEvent event) {
		try {
			guiService.doUserAction(event); 
		}
		catch (AccessDeniedException e) {
			JOptionPane.showMessageDialog(mainFrame, "Access is denied.");
		}
	}

	
}
