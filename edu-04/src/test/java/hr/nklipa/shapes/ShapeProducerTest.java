package hr.nklipa.shapes;

import hr.nklipa.shapes.MetalShapeProducer;
import hr.nklipa.shapes.PaperShapeProducer;
import net.croz.osd.edu.shapes.Circle;
import net.croz.osd.edu.shapes.Square;
import net.croz.osd.edu.shapes.producer.AbstractShapeProducer;

import org.junit.Test;

public class ShapeProducerTest {
	
	@Test
	public void metalShapeProducer() {
		AbstractShapeProducer producer = new MetalShapeProducer();
		producer.makeShape(new Circle(), "red");
	}
	
	@Test
	public void paperShapeProducer() {
		AbstractShapeProducer producer = new PaperShapeProducer();
		producer.makeShape(new Square(), "blue");
	}
}
