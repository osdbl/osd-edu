package net.croz.osd.edu.util.config;

import java.util.Properties;

public class ShapeConfig extends Configuration {
	public static String[] SUPPORTED_SHAPES;
	
	public static void configure(String path) {
		Properties properties = loadConfiguration(path);
		String supportedShapes = properties.getProperty("supported.shapes");
		
		String[] shapes = supportedShapes.split(",");
		SUPPORTED_SHAPES = new String[shapes.length];
		
		for (int i = 0; i < shapes.length; i++) {
			SUPPORTED_SHAPES[i] = shapes[i].toLowerCase().trim();
		}
	}
}
