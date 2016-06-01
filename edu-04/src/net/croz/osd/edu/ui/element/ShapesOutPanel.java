package net.croz.osd.edu.ui.element;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.croz.osd.edu.util.JTextPrintStream;

import org.springframework.stereotype.Component;

@Component
public class ShapesOutPanel extends JScrollPane {
	
	public JScrollPane init() {
		JTextArea textArea = new JTextArea(20, 50);
        textArea.setEditable(false);
        System.setOut(new JTextPrintStream(textArea));
		setViewportView(textArea);
		return this;
	}
}
