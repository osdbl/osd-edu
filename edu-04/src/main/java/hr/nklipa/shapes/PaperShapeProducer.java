package hr.nklipa.shapes;

import net.croz.osd.edu.shapes.producer.AbstractShapeProducer;

public class PaperShapeProducer extends AbstractShapeProducer {

	@Override
	protected void prepareMaterial() {
		System.out.println("PaperShapeProducer: Paper sheet is prepared.");
	}

	@Override
	protected void cutShape() {
		System.out.println("PaperShapeProducer: Take the scissors and cut the paper.");
	}
}
