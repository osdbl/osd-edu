package net.croz.osd.edu.util;

import java.io.PrintStream;

import javax.swing.JTextArea;

public class JTextPrintStream extends PrintStream {
	private JTextArea textArea;
	
	public JTextPrintStream(JTextArea textArea) {
		super(System.out);
		this.textArea = textArea;
	}
	
	@Override
	public void println(String txt) {
		textArea.append(txt + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength());
	}
}
