package net.croz.osd.edu.ui.element;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShapesInPanel extends JPanel {
	@Autowired ShapesComboBox shapesComboBox;
	@Autowired ShapesSubmitButton shapesSubmitButton;
	@Autowired ShapesSizeField shapesSizeField;
	
	public JPanel init() {
		setLayout(new GridBagLayout());
		
		setPreferredSize(new Dimension(Integer.MIN_VALUE,35));
		
		GridBagConstraints cs = new GridBagConstraints(); 
        cs.fill = GridBagConstraints.NONE;
		
        cs.gridx = 0;
        cs.gridy = 0;
        
        add(new JLabel("Shape: "), cs);
 
        cs.gridx = 1;
        cs.gridy = 0;
        cs.insets = new Insets(0, 0, 0, 30);
        add(shapesComboBox.init(), cs);
        
        cs.gridx = 2;
        cs.gridy = 0; 
        cs.insets = new Insets(0, 0, 0, 0);      
        add(new JLabel("Dimension: "), cs);
		
        cs.gridx = 3;
        cs.gridy = 0;
        cs.insets = new Insets(0, 0, 0, 30);
        add(shapesSizeField.init(), cs);
        
        cs.gridx = 4;
        cs.gridy = 0;
        add(shapesSubmitButton.init(), cs);
        
		return this;
	}
}
