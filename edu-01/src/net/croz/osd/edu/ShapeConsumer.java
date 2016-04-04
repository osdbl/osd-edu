package net.croz.osd.edu;

import net.croz.osd.edu.shapes.RegularPoligon;
import net.croz.osd.edu.shapes.Shape;
import net.croz.osd.edu.shapes.ShapeType;

public class ShapeConsumer {
	private Shape shape;
	
	public ShapeConsumer(Shape shape) {
		this.shape = shape;
	}
	
	public void shapeInfo() {
		double area = shape.area();
		double perimeter = shape.perimeter();
		
		System.out.println("	Shape instance: " + shape);
		System.out.println("	Shape type: " + shape.getType());
		System.out.println("	Area = " + area);
		System.out.println("	Perimeter = " + perimeter);
		if (!shape.getType().equals(ShapeType.CIRCLE))
			System.out.println("	Perimeter = " + ((RegularPoligon) shape).angle());
	}
}
