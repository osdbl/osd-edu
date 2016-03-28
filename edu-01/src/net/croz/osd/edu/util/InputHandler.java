package net.croz.osd.edu.util;

import java.util.Arrays;
import java.util.Scanner;

import net.croz.osd.edu.util.config.ShapeConfig;

public class InputHandler {
	public static Scanner scanner = new Scanner(System.in);
	
	public static String inShape;
	public static double inDimension;
		
	public static void handleInput() {
		while (true) {
			System.out.println("Enter shape " + getShapeMenu() + " or quit [q]:" );
			inShape = scanner.next();
			if (inShape.equalsIgnoreCase("q")) {
				System.out.println("Terminated!");
				System.exit(ExitStatus.USER_QUIT.status);
			}
			if (Arrays.asList(ShapeConfig.SUPPORTED_SHAPES).contains(inShape.toLowerCase())) {
				break;
			}
			else {
				System.out.println("Ivalid shape: " + inShape);
			}
		}
		
		System.out.println("Enter shape dimension:");
		while (true) {
			try {
				inDimension = Double.parseDouble(scanner.next()); 
				// NK Pitanje: Zašto ne radi nextDouble()
				break;
			}
			catch (Exception e) {
				System.out.println("Ivalid shape dimension: " + inDimension);
			}
		}		
	}
	
	private static String getShapeMenu() {
		String shapeMenu = "[";
		for (String s : ShapeConfig.SUPPORTED_SHAPES) {
			shapeMenu += s + "|"; 
		}
		shapeMenu = shapeMenu.substring(0, shapeMenu.length()-1);
		shapeMenu += "]";
		
		return shapeMenu;
	}
}
