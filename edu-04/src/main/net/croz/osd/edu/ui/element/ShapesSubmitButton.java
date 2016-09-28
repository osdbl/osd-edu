package net.croz.osd.edu.ui.element;

import javax.swing.JButton;

import net.croz.osd.edu.ui.action.ShapesSubmitButtonListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShapesSubmitButton extends JButton {
	@Autowired ShapesSubmitButtonListener shapesSubmitButtonListener;
	
	public JButton init() {
		setText("Submit");
		addActionListener(shapesSubmitButtonListener);
		return this;
	}
}
