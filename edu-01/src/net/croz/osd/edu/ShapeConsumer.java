package net.croz.osd.edu;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import net.croz.osd.edu.shapes.RegularPoligon;
import net.croz.osd.edu.shapes.Shape;
import net.croz.osd.edu.shapes.ShapeException;
import net.croz.osd.edu.shapes.ShapeType;
import net.croz.osd.edu.util.ShapeFactory;
import net.croz.osd.edu.util.config.Configuration;

public class ShapeConsumer {
	private Shape shape;
	double enteredSize = 0.0;
	public static ResourceBundle rb = 
		ResourceBundle.getBundle("net.croz.osd.edu.i18n.shape-message", new Locale(Configuration.locale));
	
	public ShapeConsumer(String shape, double size) throws ShapeException {
		enteredSize = size;
		this.shape = ShapeFactory.getShape(shape, size);
		if (this.shape == null) {
			System.out.println(MessageFormat.format(rb.getString("not.implemented"),shape));
			throw new ShapeException("");
		}
	}
	
	public void printShapeInfo() {
		double area = shape.area();
		double perimeter = shape.perimeter();
		
		System.out.println();
		System.out.println(rb.getString("entered.size") + enteredSize);
		System.out.println(rb.getString("instance") + shape);
		System.out.println(rb.getString("type") + shape.getType());
		System.out.println(rb.getString("area") + area);
		System.out.println(rb.getString("perimeter") + perimeter);
		if (!shape.getType().equals(ShapeType.CIRCLE))
			System.out.println(rb.getString("angle") + ((RegularPoligon) shape).angle());
	}
}
