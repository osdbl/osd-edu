package hr.nklipa.shapes;

import net.croz.osd.edu.shapes.producer.AbstractShapeProducer;

public class MetalShapeProducer extends AbstractShapeProducer{
	
	@Override
	protected void prepareMaterial() {
		System.out.println("MetalShapeProducer: Metal sheet is prepared.");
	}

	@Override
	protected void cutShape() {
		System.out.println("MetalShapeProducer: Take the hacksaw and cut the metal.");
	}		
}
