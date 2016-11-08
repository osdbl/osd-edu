package net.croz.osd.edu.ui.element;

import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatter;

import org.springframework.stereotype.Component;

@Component
public class ShapesSizeField extends JFormattedTextField {
	public JFormattedTextField init () {
		
		setFormatter(new DefaultFormatter());
		setValue(1.0);
		setColumns(5);
		return this;
	}
}
