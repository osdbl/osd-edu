package net.croz.osd.edu.ui.model;

import java.util.Locale;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;

public abstract class MessageAwareComboModel {
	@Autowired ResourceBundleMessageSource messageSource;
	
	public abstract Vector<Item> getModel(Locale locale);
	
	public static class Item {
		public String label;
		public String value;
		
		public Item(String label, String value) {
			this.label = label;
			this.value = value;
		}
	}
}
