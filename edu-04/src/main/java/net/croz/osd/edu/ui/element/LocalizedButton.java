package net.croz.osd.edu.ui.element;

import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import net.croz.osd.edu.ui.action.LanguageButtonActionListener;
import net.croz.osd.edu.ui.action.LocaleChangeListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class LocalizedButton extends JButton implements LocaleChangeListener {
	@Autowired ResourceBundleMessageSource messageSource;
	@Autowired LanguageButtonActionListener languageButtonActionListener;
	
	String txtKey;
	String toolTipKey;
	
	public JButton init(String txtKey, String toolTipKey, String imagePath) {
		this.txtKey = txtKey;
		this.toolTipKey = toolTipKey;
		if (txtKey != null) setText(messageSource.getMessage(txtKey, null, Locale.getDefault()));
		if (toolTipKey != null) setToolTipText(messageSource.getMessage(toolTipKey, null, Locale.getDefault()));
		if (imagePath != null) setIcon(new ImageIcon(imagePath));
		languageButtonActionListener.addLocaleChangeListener(this);
		return this;
	}
	@Override
	public void localeChange(Locale locale) {
		if (txtKey != null) setText(messageSource.getMessage(txtKey, null, locale));
		if (toolTipKey != null) setToolTipText(messageSource.getMessage(toolTipKey, null, locale));
	}

}
