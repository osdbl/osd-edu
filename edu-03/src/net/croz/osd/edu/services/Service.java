package net.croz.osd.edu.services;

public interface Service {
	boolean doAuthenticate(String username, String password);
	void hello();
}
