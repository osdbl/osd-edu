package net.croz.osd.edu.ui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.croz.osd.edu.ShapeConsumer;
import net.croz.osd.edu.shapes.ShapeException;
import net.croz.osd.edu.ui.element.ShapesComboBox;
import net.croz.osd.edu.ui.element.ShapesSizeField;
import net.croz.osd.edu.util.ShapeComboModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShapesSubmitButtonListener implements ActionListener {
	@Autowired ShapesComboBox shapesComboBox;
	@Autowired ShapesSizeField shapesSizeField;
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			String inShape = ((ShapeComboModel.Item)shapesComboBox.getSelectedItem()).getValue();
			double inSize = ((Number)shapesSizeField.getValue()).doubleValue();					
			ShapeConsumer consumer = new ShapeConsumer(inShape, inSize);
			consumer.printShapeInfo();
		} catch (ShapeException e) {  
			//
		}	
	}

}
