package net.croz.osd.edu.ui.element;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.Locale;

import javax.swing.JFrame;

import net.croz.osd.edu.ui.GuiService;
import net.croz.osd.edu.ui.action.LanguageButtonActionListener;
import net.croz.osd.edu.ui.action.LocaleChangeListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class MainFrame extends JFrame implements LocaleChangeListener {
	@Autowired ResourceBundleMessageSource messageSource;
	@Autowired LanguageButtonActionListener languageButtonActionListener;
	
	@Autowired CardLogin cardLogin;
	@Autowired CardShapes cardShapes;
	@Autowired CustomTable table;
 
	
	
	String messageKey;
	
	public void init(String messageKey) {
		this.messageKey = messageKey;
		setTitle(messageSource.getMessage(messageKey, null, Locale.getDefault()));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(1024, 768));
		setMinimumSize(new Dimension(1024, 768));
		getContentPane().setLayout(new CardLayout());
		getContentPane().add(cardLogin.init(), GuiService.LOGIN_PANEL);
		getContentPane().add(cardShapes.init(), GuiService.SHAPE_PANEL);
		getContentPane().add(table.init(), GuiService.USER_PANEL);
	
	    
	
		pack();
        setVisible(true);
        languageButtonActionListener.addLocaleChangeListener(this);
    }

	@Override
	public void localeChange(Locale locale) {
		setTitle(messageSource.getMessage(messageKey, null, locale));
	}
}
