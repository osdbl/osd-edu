package net.croz.osd.edu.ui;

import java.awt.event.ActionEvent;

import org.springframework.security.access.prepost.PreAuthorize;

public interface GuiService {
	static final String LOGIN_PANEL = "Card with login form";
	static final String SHAPE_PANEL = "Card with shapes";
	static final String USER_PANEL = "Card some card";

	void createAndShowGUI();
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	void doUserAction(ActionEvent e);
}
