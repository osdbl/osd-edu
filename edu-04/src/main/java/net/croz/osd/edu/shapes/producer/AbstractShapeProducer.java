package net.croz.osd.edu.shapes.producer;

import net.croz.osd.edu.shapes.Shape;


public abstract class AbstractShapeProducer {
	
	public final void makeShape(Shape shape, String color) {
		System.out.println("\nMake " + shape.getType().name() + ":");
		prepareMaterial();
		drawShape();
		cutShape();
		paintShape(color);
	}
	
	protected abstract void prepareMaterial();
	
	protected void drawShape() {
		System.out.println("AbstractShapeProducer: Shape is drawn.");
	}
	
	protected abstract void cutShape();
	
	protected void paintShape(String color) {
		
		System.out.println("AbstractShapeProducer: Shape is painted " + color + ".");
	}
	
}

