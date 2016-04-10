package net.croz.osd.edu;

import net.croz.osd.edu.shapes.RegularPoligon;
import net.croz.osd.edu.shapes.Shape;
import net.croz.osd.edu.shapes.ShapeException;
import net.croz.osd.edu.shapes.ShapeType;
import net.croz.osd.edu.util.ShapeFactory;

public class ShapeConsumer {
	private Shape shape;
	
	public ShapeConsumer(String shape, double size) throws ShapeException {
		this.shape = ShapeFactory.getShape(shape, size);
		if (this.shape == null) {
			System.out.println("Shape " +  shape + " is not implemented yet!");
			throw new ShapeException("");
		}
	}
	
	public void printShapeInfo() {
		double area = shape.area();
		double perimeter = shape.perimeter();
		
		System.out.println("	Shape instance: " + shape);
		System.out.println("	Shape type: " + shape.getType());
		System.out.println("	Area = " + area);
		System.out.println("	Perimeter = " + perimeter);
		if (!shape.getType().equals(ShapeType.CIRCLE))
			System.out.println("	Angle = " + ((RegularPoligon) shape).angle());
	}
}
