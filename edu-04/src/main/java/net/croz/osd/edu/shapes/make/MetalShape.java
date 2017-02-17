package net.croz.osd.edu.shapes.make;

public class MetalShape extends MakeShape{
	
	
	protected void prepareMaterial() {
		System.out.println("Material prepared for MetalShape!");
	}

	protected void cutShape() {
		System.out.println("You can cut this MetalShape!!!");
	}
	
	@Override
	protected void drawShape() {
		//System.out.println("MetalShape is drawn!");
	}
	
}
