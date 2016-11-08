package net.croz.osd.edu.ui.element;

import java.util.Locale;

import javax.swing.JLabel;

import net.croz.osd.edu.ui.action.LanguageButtonActionListener;
import net.croz.osd.edu.ui.action.LocaleChangeListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class LocalizedLabel extends JLabel implements LocaleChangeListener {
	@Autowired ResourceBundleMessageSource messageSource;
	@Autowired LanguageButtonActionListener languageButtonActionListener;
	
	String messageKey;
	
	public JLabel init(String messageKey) {
		this.messageKey = messageKey;
		setText(messageSource.getMessage(messageKey, null, Locale.getDefault()));
		languageButtonActionListener.addLocaleChangeListener(this);
		return this;
	}
	
	@Override
	public void localeChange(Locale locale) {
		setText(messageSource.getMessage(messageKey, null, locale));
	}
}
