package net.croz.osd.edu;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import net.croz.osd.edu.util.ExitStatus;
import net.croz.osd.edu.util.config.Configuration;
import net.croz.osd.edu.util.config.ShapeConfig;

public class ConsoleInputHandler {
	public static Scanner scanner = new Scanner(System.in);
	// TODO static rb consequences??
	static ResourceBundle rb = 
		ResourceBundle.getBundle("net.croz.osd.edu.i18n.shape-message", new Locale(Configuration.locale)); 
	
	public static String inShape;
	public static double inSize;
		
	public static void handleInput() {
		while (true) {
			System.out.print(MessageFormat.format(rb.getString("enter.shape"), getShapeMenu()));
			inShape = scanner.next();
			quitHandler(inShape);
			
			if (Arrays.asList(ShapeConfig.SUPPORTED_SHAPES).contains(inShape.toLowerCase())) {
				break;
			}
			else {
				System.out.println(rb.getString("invalid.shape") + inShape);
			}
		}
		
		while (true) {
			System.out.print(rb.getString("enter.size"));
			String size = scanner.next();
			quitHandler(size);
			
			try {
				inSize = Double.parseDouble(size); 
				break;
			}
			catch (Exception e) {
				System.out.println(rb.getString("invalid.size") + size);
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
			System.out.println(rb.getString("quit.app"));
			System.exit(ExitStatus.USER_QUIT.status);
		}
	}
}
