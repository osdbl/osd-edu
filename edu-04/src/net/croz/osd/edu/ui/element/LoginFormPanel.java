package net.croz.osd.edu.ui.element;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginFormPanel extends JPanel {
	Image background = Toolkit.getDefaultToolkit().createImage("icons/CROZ.jpg");
	@Autowired LoginButton loginButton;
	
	public JPanel init() {	
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints cs = new GridBagConstraints(); 
        cs.fill = GridBagConstraints.NONE;
 
        cs.gridx = 1;
        cs.gridy = 0;
        cs.insets = new Insets(0, 0, 20, 0);
        add(new JLabel("Please identify yourself: "), cs);
 
        cs.insets = new Insets(0, 0, 0, 0);
        cs.gridx = 0;
        cs.gridy = 1;
        add(new JLabel("User name: "), cs);
 
        cs.gridx = 1;
        cs.gridy = 1;
        add(new JTextField(20), cs);
 
        cs.gridx = 0;
        cs.gridy = 2;
        add(new JLabel("Password: "), cs);
 
        cs.gridx = 1;
        cs.gridy = 2;
        add(new JPasswordField(20), cs);
        
        cs.gridx = 2;
        cs.gridy = 2;
        add(loginButton.init(), cs);
	
		return this;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(background, (this.getWidth() - background.getWidth(null))/2, 0, null);
	}
}
