package net.croz.osd.edu;

import net.croz.osd.edu.shapes.Shape;

public class ShapeConsumer {
	private Shape shape;
	
	public ShapeConsumer(Shape shape) {
		this.shape = shape;
	}
	
	public void shapeInfo() {
		System.out.println("Shape:" + shape);
		System.out.println("Area:" + shape.area());
	}
}
