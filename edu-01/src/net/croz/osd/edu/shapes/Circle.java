package net.croz.osd.edu.shapes;


public class Circle extends AbstractShape {
	@Override
	public double area() {
		
		return dimension*dimension*Math.PI;
	}
}
