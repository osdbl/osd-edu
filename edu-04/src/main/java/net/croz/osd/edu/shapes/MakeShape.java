package net.croz.osd.edu.shapes;

public abstract class MakeShape {
	
	public void makeShape(String color) {
		prepareMaterial();
		drawShape();
		cutShape();
		paintShape(color);
	}
	
	protected abstract void prepareMaterial();
	
	protected void drawShape() {
		System.out.println("Shape is drawn!");
	}
	
	protected abstract void cutShape();
	
	protected void paintShape(String color) {
		System.out.println("Piaint it " + color + ".");
	}
}
