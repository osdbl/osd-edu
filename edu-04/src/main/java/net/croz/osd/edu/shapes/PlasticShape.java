package net.croz.osd.edu.shapes;

public class PlasticShape extends MakeShape {
	int thick;
	String boja;
	
	protected void setColor(String s){
		boja=s;
	}
	protected String getColor(){
		return boja;
	}
	
	protected void setThick(int a){
		thick=a;
	}
	
	protected int  getThick(){
		return thick;
	}
	
	@Override
	protected void prepareMaterial() {
		if(thick==0){
			System.out.println("You must set thickness of Your PlasticShape.");
		}
		else{
			System.out.println("You set thicknes="+thick+". Now You have Your material prepared");
		}
		
	}

	@Override
	protected void cutShape() {
		if(thick>5){
			System.out.println("You can not cut PlasticShape with thickness>5!");
		}
		else{
			System.out.println("PlasticShape cutted");
		}
	}
	
	protected void makeShape(int a,String s){
		setColor(s);
		setThick(a);
		prepareMaterial();
		cutShape();
		drawShape();
		paintShape(boja);
		
	}
}
