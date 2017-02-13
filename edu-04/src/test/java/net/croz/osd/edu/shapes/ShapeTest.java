package net.croz.osd.edu.shapes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

public class ShapeTest {

	Circle circle = new Circle();
	Square square = new Square();
	Triangle triangle = new Triangle();
	
	
	@Test
	public void testCircleArea() {
		
		circle.setRadius(5.5);
		
		assertEquals(95.03, circle.area(), 0.01);
		
	}
	
	@Test
	public void testSquarePerimeter() {

		square.setSideLength(10);
		
		assertEquals(40, square.perimeter(), 0.01);
		
	}
	
	@Test
	public void testTrianglePerimeter() {

		assertEquals(60, triangle.angle(), 0.01);
		
	}
	
	@Test
	public void testAngles() {

		assertNotSame(square.angle(), triangle.angle());
		
	}
	
	@Test
	public void testNotNull() {

		assertNotNull(triangle.angle());
		
	}

}
