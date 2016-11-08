package net.croz.osd.edu.ui.element;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class MenuPanel extends JPanel {
	@Autowired LocalizedMenuBar localizedMenuBar;
	@Autowired LanguagesPanel languagesPanel;
	
	public JPanel init(boolean showMenu) {
		setLayout(new BorderLayout());
		setBorder(new BevelBorder(BevelBorder.RAISED));
		setMaximumSize(new Dimension(Integer.MAX_VALUE,20));
		
		if (showMenu) add(localizedMenuBar.init(), BorderLayout.WEST);
		add(languagesPanel.init(), BorderLayout.CENTER);
		
		return this;
	}
}
