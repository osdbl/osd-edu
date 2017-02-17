package net.croz.osd.edu.shapes.make;

public class PaperShape extends MakeShape{
	
	String color;
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	protected void cutShape(){
		System.out.println("You cut the paper in two PaperShapes!");
	}
	
	@Override
	protected void drawShape(){
		super.drawShape();
		System.out.println("Congratulations!You draw PaperShape!");
	}
	protected void prepareMaterial() {
		System.out.println("Material prepared for PaperShape!");
	}
	
	@Override
	public void paintShape(String color) {
		super.paintShape(color);
		System.out.println("Paper is painted in " + color + ".");
	}
	
}
