package net.croz.osd.edu.shapes;

public class IronShape extends MetalShape {
		
		protected void prepareMaterial(){
			System.out.println("You are ready to cut,draw,paint IronShape!");
		}
		
		protected void paintShape(String color){
			System.out.println("You paint IronShape as:"+color);		
		}
}
