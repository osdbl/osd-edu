package net.croz.osd.edu.ui.model;

import java.util.Locale;
import java.util.Vector;

import net.croz.osd.edu.conf.ShapeConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class ShapeComboModel extends MessageAwareComboModel {
	@Autowired ResourceBundleMessageSource messageSource;
	
	@Override
	public Vector<Item> getModel(Locale locale) {
		Vector<Item> model = new Vector<Item>();
		
		for (String shape : ShapeConfig.SUPPORTED_SHAPES) {
			model.add(new Item(messageSource.getMessage(shape, null, locale), shape));
		}
		
		return model;
	}
}
