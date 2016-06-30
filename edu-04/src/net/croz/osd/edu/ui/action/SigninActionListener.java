package net.croz.osd.edu.ui.action;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import net.croz.osd.edu.ui.GuiService;
import net.croz.osd.edu.ui.element.LocalizedLabel;
import net.croz.osd.edu.ui.element.LoginFormPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SigninActionListener implements ActionListener {
	LocalizedLabel loginFailedLabel;
	public void setLoginFailedLabel(LocalizedLabel loginFailedLabel) {
		this.loginFailedLabel = loginFailedLabel;
	}

	@Autowired JFrame mainFrame;
	@Autowired LoginFormPanel loginFormPanel;
	@Autowired private AuthenticationManager authManager;
	
	@Override
	public void actionPerformed(ActionEvent event) {	
		
		try {
			Authentication authRequest = new UsernamePasswordAuthenticationToken(
				loginFormPanel.usernameField.getText(), new String(loginFormPanel.passwordField.getPassword()));
			Authentication authResult = authManager.authenticate(authRequest);
			SecurityContextHolder.getContext().setAuthentication(authResult);
			loginFailedLabel.setVisible(false);
		} catch(AuthenticationException e) {
			System.out.println("Authentication failed: " + e.getMessage());
			loginFailedLabel.setVisible(true);
			return;
		}
		
		CardLayout cardLayout = (CardLayout) mainFrame.getContentPane().getLayout();
		cardLayout.show(mainFrame.getContentPane(), GuiService.SHAPE_PANEL);
	}

}
