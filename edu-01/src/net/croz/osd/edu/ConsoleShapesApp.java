package net.croz.osd.edu;

import static net.croz.osd.edu.ConsoleInputHandler.handleInput;
import static net.croz.osd.edu.ConsoleInputHandler.inShape;
import static net.croz.osd.edu.ConsoleInputHandler.inSize;

import java.util.logging.Logger;

import net.croz.osd.edu.shapes.ShapeException;
import net.croz.osd.edu.util.config.ShapeConfig;

public class ConsoleShapesApp {
	public static Logger logger = Logger.getLogger(ConsoleShapesApp.class.getName());
	
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
			} catch (ShapeException e) { /*continue;*/ }		
		}
	}
}
