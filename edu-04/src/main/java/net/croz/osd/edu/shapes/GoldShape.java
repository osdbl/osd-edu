package net.croz.osd.edu.shapes;

public class GoldShape extends MetalShape {
	
	protected void prepareMaterial(){
		System.out.println("You have not material for GoldShape");
	}
	
	protected void drawShape(){
		System.out.println("You can not draw GoldShape!");
	}
	
	protected void paintShape(String color) {
		System.out.println("You are trying to paintGoldShape. Error!");
	}
	protected void cutShape() {
		System.out.println("GoldShape!!! You are not allowed to cut.");
	}

}
