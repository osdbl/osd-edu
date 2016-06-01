package net.croz.osd.edu.ui.element;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShapesCard extends JPanel {
	@Autowired LanguagesPanel LanguagesPanel;
	@Autowired ShapesPanel shapesPanel;
	
	public JPanel init() {
		setLayout(new BorderLayout());
		add(LanguagesPanel.init(), BorderLayout.NORTH);
		add(shapesPanel.init(), BorderLayout.CENTER);
		return this;
	}
}
