package net.croz.osd.edu.shapes;

public abstract class AbstractShape implements Shape {
	protected double dimension;
	
	@Override
	public void setDimension(double dimension) {
		this.dimension = dimension;
	}

	@Override
	public abstract double area();
}
