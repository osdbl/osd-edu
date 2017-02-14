package net.croz.osd.edu.shapes;

public class MetalShape extends MakeShape{
	
	@Override
	protected void prepareMaterial() {
		System.out.println("You have prepared color for PAINTING MetalShape!");
	}

	@Override
	protected void cutShape() {
		System.out.println("You can cut this MetalShape!!!");
	}
	@Override
	protected void paintShape(String color) {
		System.out.println("Paint it " + color + ".");
	}
	
}
