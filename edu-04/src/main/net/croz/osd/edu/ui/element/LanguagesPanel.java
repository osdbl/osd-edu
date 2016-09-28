package net.croz.osd.edu.ui.element;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class LanguagesPanel extends JPanel {
	@Autowired LanguageButton langBa;
	@Autowired LanguageButton langGb;
	@Autowired LanguageButton langHr;
	@Autowired LanguageButton langRs;
	
	public JPanel init() {
		setLayout(new FlowLayout(/*FlowLayout.RIGHT*/));
		//setBorder(new BevelBorder(BevelBorder.RAISED));
		setPreferredSize(new Dimension(Integer.MIN_VALUE,35));
		
		langBa.setToolTipText("ba");
		add(langBa.init("icons/ba.gif"));
		
		langGb.setToolTipText("gb");
		add(langGb.init("icons/gb.gif"));
		
		langHr.setToolTipText("hr");
		add(langHr.init("icons/hr.gif"));
		
		langRs.setToolTipText("rs");
		add(langRs.init("icons/rs.gif"));
		
		return this;
	}
}
