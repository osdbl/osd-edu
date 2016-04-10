package net.croz.osd.edu;

import java.util.Arrays;
import java.util.Scanner;

import com.sun.xml.internal.ws.api.pipe.NextAction;

import net.croz.osd.edu.util.ExitStatus;
import net.croz.osd.edu.util.config.ShapeConfig;

public class ConsoleInputHandler {
	public static Scanner scanner = new Scanner(System.in);
	
	public static String inShape;
	public static double inSize;
		
	public static void handleInput() {
		while (true) {
			System.out.print("Enter shape " + getShapeMenu() + " or quit [q]: " );
			inShape = scanner.next();
			quitHandler(inShape);
			
			if (Arrays.asList(ShapeConfig.SUPPORTED_SHAPES).contains(inShape.toLowerCase())) {
				break;
			}
			else {
				System.out.println("Ivalid shape: " + inShape);
			}
		}
		
		while (true) {
			System.out.print("Enter shape size or quit [q]: ");
			String size = scanner.next();
			quitHandler(size);
			
			try {
				inSize = Double.parseDouble(size); 
				break;
			}
			catch (Exception e) {
				System.out.println("Ivalid size: " + size);
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
	
	private static void quitHandler(String input) {
		if (input.equalsIgnoreCase("q")) {
			System.out.println("Terminated!");
			System.exit(ExitStatus.USER_QUIT.status);
		}
	}
}
