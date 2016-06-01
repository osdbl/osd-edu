package net.croz.osd.edu.ui.element;

import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import net.croz.osd.edu.ui.action.LanguageButtonActionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class LanguageButton extends JButton {
	@Autowired @Lazy LanguageButtonActionListener languageButtonActionListener;

	public JButton init(String iconPath) {
		setIcon(new ImageIcon(iconPath));
		setMargin(new Insets(0, 0, 0, 0));
		setActionCommand(iconPath);
		addActionListener(languageButtonActionListener);
		return this;
	}
}
