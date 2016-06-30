package net.croz.osd.edu.ui.model;

import java.util.Locale;

import javax.swing.table.DefaultTableModel;

import net.croz.osd.edu.ui.action.LanguageButtonActionListener;
import net.croz.osd.edu.ui.action.LocaleChangeListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageAwareOutTableModel extends DefaultTableModel implements LocaleChangeListener {
	@Autowired ResourceBundleMessageSource messageSource;
	@Autowired LanguageButtonActionListener languageButtonActionListener;
	
	public DefaultTableModel init() {		
		setColumnIdentifiers(getColumnNames(Locale.getDefault()));
		languageButtonActionListener.addLocaleChangeListener(this);
		
		return this;
	}
	
	@Override
	public void localeChange(Locale locale) {
		setColumnIdentifiers(getColumnNames(locale));
	}

	private String[] getColumnNames(Locale locale) {
		return new String[] {
			messageSource.getMessage("type", null, locale),
			messageSource.getMessage("angle", null, locale),
			messageSource.getMessage("instance", null, locale),
			messageSource.getMessage("entered.size", null, locale),
			messageSource.getMessage("area", null, locale),
			messageSource.getMessage("perimeter", null, locale)
		};
	}
}
