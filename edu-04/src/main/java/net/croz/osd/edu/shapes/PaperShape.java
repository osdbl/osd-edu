package net.croz.osd.edu.shapes;

public class PaperShape extends MakeShape{
	
	String boja;
	
	protected void setColor(String s){
		boja=s;
	}
	protected String getColor(){
		return boja;
	}
	
	protected void makeShape(){
		prepareMaterial();
		cutShape();
		drawShape();
		paintShape(boja);
	}
	
	protected void cutShape(){
		System.out.println("You have now two PaperShapes!");
	}
	
	protected void drawShape(){
		System.out.println("Congratulations! You draw PaperShape!");
	}

	@Override
	protected void prepareMaterial() {
		System.out.println("Material prepared!");
		
	}
}
