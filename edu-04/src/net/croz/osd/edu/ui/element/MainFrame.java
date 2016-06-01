package net.croz.osd.edu.ui.element;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import net.croz.osd.edu.ui.GuiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainFrame extends JFrame {
	@Autowired LoginCard loginCard;
	@Autowired ShapesCard shapesCard;
	
	public void init() {
		setTitle("Shape application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(600, 500));
		setMinimumSize(new Dimension(600, 500));
		getContentPane().setLayout(new CardLayout());
		getContentPane().add(loginCard.init(), GuiService.LOGIN_PANEL);
		getContentPane().add(shapesCard.init(), GuiService.SHAPE_PANEL);
	
		pack();
        setVisible(true);
    }
}
