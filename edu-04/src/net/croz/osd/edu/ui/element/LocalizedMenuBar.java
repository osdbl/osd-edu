package net.croz.osd.edu.ui.element;

import java.awt.event.KeyEvent;
import java.util.Locale;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import net.croz.osd.edu.ui.action.LanguageButtonActionListener;
import net.croz.osd.edu.ui.action.LocaleChangeListener;
import net.croz.osd.edu.ui.action.ShapesActionListener;
import net.croz.osd.edu.ui.action.SignoutActionListener;
import net.croz.osd.edu.ui.action.UserActionListener;

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
	@Autowired @Lazy UserActionListener userActionListener;
	@Autowired @Lazy ShapesActionListener shapesActionListener;
	
	
	JMenu menu;
	JMenuItem usersMenuItem;
	JMenuItem signoutMenuItem;
	JMenuItem shapesMenuItem;
	public static String message1;
	public static String title;
	
	public JMenuBar init() {
		menu = new JMenu(messageSource.getMessage("menu", null, Locale.getDefault()));
		
		usersMenuItem = new JMenuItem(messageSource.getMessage("menu.users", null, Locale.getDefault()), KeyEvent.VK_T);
		usersMenuItem.addActionListener(userActionListener);
		menu.add(usersMenuItem);

		shapesMenuItem = new JMenuItem(messageSource.getMessage("menu.shapes", null, Locale.getDefault()), KeyEvent.VK_T);
		shapesMenuItem.addActionListener(shapesActionListener);
		menu.add(shapesMenuItem);
		
		signoutMenuItem = new JMenuItem(messageSource.getMessage("menu.signOut", null, Locale.getDefault()), KeyEvent.VK_T);
		signoutMenuItem.addActionListener(signoutActionListener);
		menu.add(signoutMenuItem);
		
		message1 = messageSource.getMessage("acc.denied", null, Locale.getDefault());
		title = messageSource.getMessage("message1.title", null, Locale.getDefault());
		add(menu);

		languageButtonActionListener.addLocaleChangeListener(this);
		
		return this;
	}

	@Override
	public void localeChange(Locale locale) {
		menu.setText(messageSource.getMessage("menu", null, locale));
		usersMenuItem.setText(messageSource.getMessage("menu.users", null, locale));
		signoutMenuItem.setText(messageSource.getMessage("menu.signOut", null, locale));
		shapesMenuItem.setText(messageSource.getMessage("menu.shapes", null, locale));
		message1 = messageSource.getMessage("acc.denied", null, locale);
		title = messageSource.getMessage("message1.title", null, locale);
	}

}
