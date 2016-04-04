package net.croz.osd.edu;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.croz.osd.edu.shapes.Circle;
import net.croz.osd.edu.shapes.RegularPoligon;
import net.croz.osd.edu.shapes.Shape;
import net.croz.osd.edu.util.config.ShapeConfig;

public class ShapeFactory {
	public static Logger logger = Logger.getLogger(ShapeFactory.class.getName());
	
	
	
	public static Shape getShape(String inShape, double dimension) {
		Shape shape = null;		
		
		for (String supportedShape : ShapeConfig.SUPPORTED_SHAPES) {
			supportedShape = supportedShape.substring(0, 1).toUpperCase() + supportedShape.substring(1);
			if (inShape.equalsIgnoreCase(supportedShape)) {
				try {
					shape = (Shape) Class.forName("net.croz.osd.edu.shapes." + supportedShape).newInstance();
					if (shape instanceof RegularPoligon)
						((RegularPoligon) shape).setSideLength(dimension);
					else 
						((Circle) shape).setRadius(dimension);
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					logger.log(Level.WARNING, "Failed to instance class: {0}", e.getMessage());
				}
			}
		}
		
		return shape;
	}
}
