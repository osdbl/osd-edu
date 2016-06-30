package net.croz.osd.edu.ui.element;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import net.croz.osd.edu.ui.action.ShapesSubmitButtonListener;
import net.croz.osd.edu.ui.model.MessageAwareComboModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ShapesInPanel extends JPanel {
	@Autowired LocalizedComboBox localizedComboBox;
	@Autowired @Qualifier("shapeComboModel") MessageAwareComboModel shapeComboModel;
	@Autowired LocalizedButton shapesSubmitButton;
	@Autowired ShapesSizeField shapesSizeField;
	@Autowired LocalizedLabel shapeLabel;
	@Autowired LocalizedLabel sizeLabel;
	@Autowired @Lazy ShapesSubmitButtonListener shapesSubmitButtonListener;
	
	public JPanel init() {
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(Integer.MIN_VALUE,35));
		
		GridBagConstraints cs = new GridBagConstraints(); 
        cs.fill = GridBagConstraints.NONE;
		
        cs.gridx = 0;
        cs.gridy = 0;
        add(shapeLabel.init("select.shape"), cs);
 
        cs.gridx = 1;
        cs.gridy = 0;
        cs.insets = new Insets(0, 0, 0, 30);
        add(localizedComboBox.init(shapeComboModel), cs);
        
        cs.gridx = 2;
        cs.gridy = 0; 
        cs.insets = new Insets(0, 0, 0, 0);      
        add(sizeLabel.init("input.size"), cs);
		
        cs.gridx = 3;
        cs.gridy = 0;
        cs.insets = new Insets(0, 0, 0, 30);
        add(shapesSizeField.init(), cs);
        
        cs.gridx = 4;
        cs.gridy = 0;
        
        shapesSubmitButtonListener.setLocalizedComboBox(localizedComboBox);
        shapesSubmitButton.addActionListener(shapesSubmitButtonListener);
        add(shapesSubmitButton.init("btn.submit", null, null), cs);
        
		return this;
	}
}
