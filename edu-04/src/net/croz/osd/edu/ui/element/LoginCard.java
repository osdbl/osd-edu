package net.croz.osd.edu.ui.element;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginCard extends JPanel {
	@Autowired LanguagesPanel LanguagesPanel;
	@Autowired LoginFormPanel loginFormPanel;
	
	public JPanel init() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(LanguagesPanel.init(), this);
		add(loginFormPanel.init(), this);
		add(Box.createVerticalGlue(), this);
		return this;
	}
}
