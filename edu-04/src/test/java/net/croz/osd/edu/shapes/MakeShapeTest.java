package net.croz.osd.edu.shapes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MakeShapeTest {

	@Test
	public void testColorPaperShape() {
		PaperShape test=new PaperShape();
		test.setColor("plava");
		assertEquals("plava",test.getColor());
	}
	@Test
	public void testColorPlasticShape(){
		PlasticShape test=new PlasticShape();
		test.setColor("zelena");
		assertEquals("zelena",test.getColor());
	}
	@Test
	public void testThick(){
		PlasticShape test=new PlasticShape();
		test.setThick(5);
		assertEquals(5,test.getThick());
	}
}
