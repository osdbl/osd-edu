package net.croz.osd.edu.ui.element;

import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import net.croz.osd.edu.ui.action.LogoutButtonActionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class LogoutButton extends JButton {
	@Autowired
	@Lazy
	LogoutButtonActionListener logoutButtonActionListener;
	
	public JButton init() {
		//setText("Logout");
		setIcon(new ImageIcon("icons/SignOut-16.png"));
		setToolTipText("Sign out");
		setMargin(new Insets(0, 0, 0, 0));
		addActionListener(logoutButtonActionListener);
		return this;
	}
}
