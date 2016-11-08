package net.croz.osd.edu.shapes;

public class Triangle extends RegularPoligon {
	@Override
	public double area() {
		return super.area()*Math.sqrt(3)/4;
	}
	
	@Override
	public double perimeter() {
		return 3*sideLength;
	}

	@Override
	public ShapeType getType() {
		return ShapeType.EQUILATERAL_TRIANGLE;
	}

	@Override
	public double angle() {
		return 60;
	}

}
