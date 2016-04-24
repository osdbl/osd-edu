package net.croz.osd.edu.util;

import java.util.Vector;

import net.croz.osd.edu.GuiShapesApp;
import net.croz.osd.edu.util.config.ShapeConfig;

public class ShapeComboModel {
	public static Vector<Item> getModel() {
		Vector<Item> model = new Vector<Item>();
		for (String shape : ShapeConfig.SUPPORTED_SHAPES) {
			model.add(new Item(GuiShapesApp.rb.getString(shape), shape));
		}
		return model;
	}
	
	public static class Item {
		public String label;
		public String value;
		
		public Item(String label, String value) {
			super();
			this.label = label;
			this.value = value;
		}
		
		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
}
