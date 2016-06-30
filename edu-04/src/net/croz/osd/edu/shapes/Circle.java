package net.croz.osd.edu.shapes;


public class Circle implements Shape {
	double radius;
	
	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public double area() {
		return radius*radius*Math.PI;
	}

	@Override
	public double perimeter() {
		return 2*Math.PI*radius;
	}

	@Override
	public ShapeType getType() {
		return ShapeType.CIRCLE;
	}

	
}
