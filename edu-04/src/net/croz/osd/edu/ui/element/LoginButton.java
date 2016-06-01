package net.croz.osd.edu.ui.element;

import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import net.croz.osd.edu.ui.action.LoginButtonActionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class LoginButton extends JButton {
	@Autowired @Lazy LoginButtonActionListener loginButtonActionListener;
	
	public JButton init() {
		setIcon(new ImageIcon("icons/SignIn-16.png"));
		setToolTipText("Sign in");
		setMargin(new Insets(0, 0, 0, 0));
		addActionListener(loginButtonActionListener);
		return this;
	}
}
