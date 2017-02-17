package net.croz.osd.edu.shapes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import net.croz.osd.edu.shapes.make.MakeShape;
import net.croz.osd.edu.shapes.make.MetalShape;
import net.croz.osd.edu.shapes.make.PaperShape;

public class MakeShapeTest {
	
	
public static void main(String[]args){
		
		MakeShape makeShape = new PaperShape();
		MakeShape makeShape1 = new MetalShape();
		makeShape.makeShape("crvena");
		System.out.println();
		makeShape1.makeShape("plava");
		
		}
	
	@Test
	public void testColorPaperShape() {
		MakeShape test=new PaperShape();
		((PaperShape) test).setColor("plava");
		assertEquals("plava", ((PaperShape) test).getColor());
	}

	
}
