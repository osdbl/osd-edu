package net.croz.osd.edu.ui.action;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import net.croz.osd.edu.ui.GuiService;
import net.croz.osd.edu.ui.element.LoginFormPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SignoutActionListener implements ActionListener {
	@Autowired private JFrame mainFrame;
	@Autowired LoginFormPanel loginFormPanel;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		loginFormPanel.usernameField.setText(null);
		loginFormPanel.passwordField.setText(null);
		SecurityContextHolder.getContext().setAuthentication(null);
		
		CardLayout cardLayout = (CardLayout) mainFrame.getContentPane().getLayout();
		cardLayout.show(mainFrame.getContentPane(), GuiService.LOGIN_PANEL);
	}
}
