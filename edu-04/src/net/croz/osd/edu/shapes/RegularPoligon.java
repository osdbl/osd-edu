package net.croz.osd.edu.shapes;

public abstract class RegularPoligon implements Shape {
	protected double sideLength;
	
	public final void setSideLength(double sideLength) {
		this.sideLength = sideLength;
	}

	@Override
	public ShapeType getType() {
		return ShapeType.REGULAR_POLIGON;
	}
	
	@Override
	public double area() {
		return sideLength*sideLength;
	}
	
	public abstract double angle();
}
