package net.croz.osd.edu.ui.element;

import java.awt.event.KeyEvent;
import java.util.Locale;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import net.croz.osd.edu.ui.action.LanguageButtonActionListener;
import net.croz.osd.edu.ui.action.LocaleChangeListener;
import net.croz.osd.edu.ui.action.SignoutActionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class LocalizedMenuBar extends JMenuBar implements LocaleChangeListener {
	@Autowired ResourceBundleMessageSource messageSource;
	@Autowired LanguageButtonActionListener languageButtonActionListener;
	@Autowired @Lazy SignoutActionListener signoutActionListener;
	
	JMenu menu;
	JMenuItem usersMenuItem;
	JMenuItem signoutMenuItem;
	
	public JMenuBar init() {
		menu = new JMenu(messageSource.getMessage("menu", null, Locale.getDefault()));
		
		usersMenuItem = new JMenuItem(messageSource.getMessage("menu.users", null, Locale.getDefault()), KeyEvent.VK_T);
		menu.add(usersMenuItem);
		
		signoutMenuItem = new JMenuItem(messageSource.getMessage("menu.signOut", null, Locale.getDefault()), KeyEvent.VK_T);
		signoutMenuItem.addActionListener(signoutActionListener);
		menu.add(signoutMenuItem);
		
		add(menu);

		languageButtonActionListener.addLocaleChangeListener(this);
		
		return this;
	}

	@Override
	public void localeChange(Locale locale) {
		menu.setText(messageSource.getMessage("menu", null, locale));
		usersMenuItem.setText(messageSource.getMessage("menu.users", null, locale));
		signoutMenuItem.setText(messageSource.getMessage("menu.signOut", null, locale));
	}

}
