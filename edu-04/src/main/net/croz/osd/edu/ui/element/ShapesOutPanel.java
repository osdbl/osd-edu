package net.croz.osd.edu.ui.element;

import javax.swing.JScrollPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShapesOutPanel extends JScrollPane {
	@Autowired LocalizedTable localizedTable;
	
	public JScrollPane init() {
		setViewportView(localizedTable.init());
		return this;
	}
}
