package net.croz.osd.edu.ui.element;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import net.croz.osd.edu.ui.action.SigninActionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class LoginFormPanel extends JPanel {
	//Image background = Toolkit.getDefaultToolkit().createImage("icons/CROZ.jpg"); TODO
	
	public JTextField usernameField = new JTextField(20);
	public JPasswordField passwordField = new JPasswordField(20);
	
	@Autowired LocalizedButton loginButton;
	@Autowired @Lazy SigninActionListener signinActionListener;
	@Autowired LocalizedLabel headLabel;
	@Autowired LocalizedLabel usernameLabel;
	@Autowired LocalizedLabel passwordLabel;
	@Autowired LocalizedLabel loginFailedLabel;
	
	public JPanel init() {	
		setLayout(new GridBagLayout());
		
		GridBagConstraints cs = new GridBagConstraints(); 
        cs.fill = GridBagConstraints.NONE;
 
        cs.gridx = 1;
        cs.gridy = 0;
        cs.insets = new Insets(0, 0, 20, 0);
        add(headLabel.init("login.head"), cs);
        
        cs.insets = new Insets(0, 0, 0, 0);
        cs.gridx = 0;
        cs.gridy = 1;
        cs.anchor = GridBagConstraints.EAST;
        add(usernameLabel.init("login.username"), cs);
 
        cs.gridx = 1;
        cs.gridy = 1;
        add(usernameField, cs);
 
        cs.gridx = 0;
        cs.gridy = 2;
        add(passwordLabel.init("login.password"), cs);
 
        cs.gridx = 1;
        cs.gridy = 2;
        add(passwordField, cs);
        
        cs.gridx = 2;
        cs.gridy = 2;
        loginButton = (LocalizedButton)loginButton.init(null, "login.signIn", "icons/SignIn-16.png");
        loginButton.setMargin(new Insets(0, 0, 0, 0));
        signinActionListener.setLoginFailedLabel(loginFailedLabel);
        loginButton.addActionListener(signinActionListener);
        add(loginButton, cs);
        
        cs.insets = new Insets(20, 0, 0, 0);
        cs.gridx = 1;
        cs.gridy = 3;
        cs.anchor = GridBagConstraints.CENTER;
        loginFailedLabel.setForeground(Color.RED);
        loginFailedLabel.setVisible(false);
        add(loginFailedLabel.init("auth.failed"), cs);
        
        
		return this;
	}
	
	/*
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(background, (this.getWidth() - background.getWidth(null))/2, 0, null);
	}
	*/
	
}
