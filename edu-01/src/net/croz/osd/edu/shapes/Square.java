package net.croz.osd.edu.shapes;


public class Square extends RegularPoligon {
	@Override
	public double perimeter() {
		return 4*sideLength;
	}

	@Override
	public double angle() {
		return 90;
	}
}
