package net.croz.osd.edu.ui.action;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import net.croz.osd.edu.ui.GuiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginButtonActionListener implements ActionListener {
	@Autowired
	JFrame mainFrame;
	
	@Override
	public void actionPerformed(ActionEvent e) {	
		CardLayout cardLayout = (CardLayout) mainFrame.getContentPane().getLayout();
		cardLayout.show(mainFrame.getContentPane(), GuiService.SHAPE_PANEL);
	}

}
