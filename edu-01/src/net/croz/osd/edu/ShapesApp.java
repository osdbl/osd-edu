package net.croz.osd.edu;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.croz.osd.edu.shapes.Shape;
import net.croz.osd.edu.shapes.ShapeFactory;
import net.croz.osd.edu.util.ExitStatus;
import net.croz.osd.edu.util.InputHandler;
import net.croz.osd.edu.util.config.ShapeConfig;

public class ShapesApp {
	public static Logger logger = Logger.getLogger(ShapesApp.class.getName());
	
	public static void main(String[] args) throws Exception {
		// Load configuration
		ShapeConfig.configure("conf/shape.properties");
		
		while (true) {
			// Read and validate console input
			InputHandler.handleInput();
			
			// Create Shape instance based on input parameters
			Shape shape = ShapeFactory.getShape(InputHandler.inShape, InputHandler.inDimension);
			
			if (shape == null) {
				System.out.println("Shape " +  InputHandler.inShape + " is not implemented yet!");
				continue;
			}
			
			//
			ShapeConsumer consumer = new ShapeConsumer(shape);
			consumer.shapeInfo();
		}

		
	}
}
