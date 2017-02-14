package net.croz.osd.edu.shapes;

public abstract class MakeShape {
	
	public void makeShape(String color) {
		prepareMaterial();
		drawShape();
		cutShape();
		paintShape(color);
	}
	
	protected abstract void prepareMaterial();
	
	protected void drawShape() {
		System.out.println("Shape is drawn!");
	}
	
	protected abstract void cutShape();
	
	protected void paintShape(String color) {
		System.out.println("Paint it " + color + ".");
	}
	public static void main(String[]args){
		MetalShape test1=new GoldShape();
		MetalShape test2=new SilverShape();
		test1.makeShape("plava");
		System.out.println();
		test2.makeShape("crvena");
		System.out.println();
		MetalShape test3=new IronShape();
		test3.makeShape("zelena");
		System.out.println();
		PaperShape test4=new PaperShape();
		test4.makeShape();
		System.out.println();
		PlasticShape test5=new PlasticShape();
		test5.makeShape(6, "plava");
	}
}

