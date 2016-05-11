package net.croz.osd.edu.services;

import org.springframework.security.access.prepost.PreAuthorize;

public interface Service {
	boolean doAuthenticate(String username, String password);
	void helloUser();
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	String encode(String password);
}
