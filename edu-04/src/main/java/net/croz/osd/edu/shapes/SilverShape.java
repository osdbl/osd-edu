package net.croz.osd.edu.shapes;

public class SilverShape extends MetalShape{
	
	protected void prepareMaterial(){
		System.out.println("You have not material for SilverShape");
	}
	
	protected void cutShape() {
		System.out.println("SilverShape!!! You are not allowed to cut.");
	}
	
	protected void paintShape(String color) {
		System.out.println("You are trying to paint SilverShape. Error!");
	}
}
