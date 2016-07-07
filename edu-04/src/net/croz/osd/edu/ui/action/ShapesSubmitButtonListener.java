package net.croz.osd.edu.ui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import net.croz.osd.edu.shapes.RegularPoligon;
import net.croz.osd.edu.shapes.Shape;
import net.croz.osd.edu.shapes.ShapeFactory;
import net.croz.osd.edu.shapes.ShapeType;
import net.croz.osd.edu.ui.element.LocalizedComboBox;
import net.croz.osd.edu.ui.element.ShapesSizeField;
import net.croz.osd.edu.ui.model.MessageAwareOutTableModel;
import net.croz.osd.edu.ui.model.ShapeComboModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShapesSubmitButtonListener implements ActionListener {
	LocalizedComboBox localizedComboBox;
	public void setLocalizedComboBox(LocalizedComboBox localizedComboBox) {
		this.localizedComboBox = localizedComboBox;
	}

	@Autowired ShapesSizeField shapesSizeField;
	@Autowired MessageAwareOutTableModel tableModel;
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String inShape = ((ShapeComboModel.Item)localizedComboBox.getSelectedItem()).value;
		double inSize = ((Number)shapesSizeField.getValue()).doubleValue();			
		
		Shape shape = ShapeFactory.getShape(inShape, inSize);
		if (shape != null) {
			Object angle = (shape.getType().equals(ShapeType.CIRCLE)) ? "N/A" : ((RegularPoligon) shape).angle();
			tableModel.addRow(new Object[] {shape, shape.getType(), angle, format(inSize), format(shape.area()), format(shape.perimeter())});
		}
		else {
			System.out.println("not.implemented");
			tableModel.addRow(new Object[] {"N/A", "N/A", "N/A", inSize, "N/A", "N/A"});
		}
	}
	
	private String format(double x) {
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(x);
	}
}
