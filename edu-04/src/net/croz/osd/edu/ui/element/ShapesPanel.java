package net.croz.osd.edu.ui.element;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShapesPanel extends JPanel {
	@Autowired ShapesInPanel shapesInPanel;
	@Autowired ShapesOutPanel shapesOutPanel;
	
	public JPanel init() {
		setLayout(new BorderLayout());
        add(shapesInPanel.init(), BorderLayout.NORTH);
        add(shapesOutPanel.init(), BorderLayout.CENTER);
		return this;
	}
}
