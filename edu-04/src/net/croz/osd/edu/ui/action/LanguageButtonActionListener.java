package net.croz.osd.edu.ui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.stereotype.Component;

@Component
public class LanguageButtonActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		String language = command.substring(command.indexOf("/") + 1, command.indexOf("."));
		System.out.println(language);
	}
}
