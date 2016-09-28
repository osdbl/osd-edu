package net.croz.osd.edu.ui.element;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CardShapes extends JPanel {
	@Autowired MenuPanel menuPanel;
	@Autowired ShapesPanel shapesPanel;
	
	public JPanel init() {
		setLayout(new BorderLayout());
		add(menuPanel.init(true), BorderLayout.NORTH);
		add(shapesPanel.init(), BorderLayout.CENTER);
		
		return this;
	}
}
