package net.croz.osd.edu;

import static net.croz.osd.edu.util.InputHandler.handleInput;
import static net.croz.osd.edu.util.InputHandler.inShape;
import static net.croz.osd.edu.util.InputHandler.inSize;

import java.util.logging.Logger;

import net.croz.osd.edu.shapes.ShapeException;
import net.croz.osd.edu.util.config.ShapeConfig;

public class ShapesApp {
	public static Logger logger = Logger.getLogger(ShapesApp.class.getName());
	
	public static void main(String[] args) {
		// Load configuration
		ShapeConfig.configure("conf/shape.properties");
		
		while (true) {
			// Read and validate console input
			handleInput();
			
			// Do something with shape
			try {
				ShapeConsumer consumer = new ShapeConsumer(inShape, inSize);
				consumer.printShapeInfo();
			} catch (ShapeException e) { continue; }		
		}
	}
}
