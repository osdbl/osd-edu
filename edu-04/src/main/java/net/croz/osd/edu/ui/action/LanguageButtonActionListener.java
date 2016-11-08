package net.croz.osd.edu.ui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Component;

@Component
public class LanguageButtonActionListener implements ActionListener {
	List<LocaleChangeListener> listeners = new ArrayList<LocaleChangeListener>();
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		String language = command.substring(command.indexOf("/") + 1, command.indexOf("."));
		notifyAllListeners(new Locale(language));
	}
	
	
	public void addLocaleChangeListener(LocaleChangeListener listener){
		listeners.add(listener);		
	}
	
	private void notifyAllListeners(Locale locale) {
		for (LocaleChangeListener listener : listeners) {
	         listener.localeChange(locale);
	    }
	} 		
}
