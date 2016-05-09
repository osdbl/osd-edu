package net.croz.osd.edu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class ServiceImpl implements Service{
	@Autowired
	private AuthenticationManager authManager;
	
	@Override
	public boolean doAuthenticate(String username, String password) {
		try {
			Authentication authRequest = new UsernamePasswordAuthenticationToken(username, password);
			Authentication authResult = authManager.authenticate(authRequest);
			SecurityContextHolder.getContext().setAuthentication(authResult);
		} catch(AuthenticationException e) {
			System.out.println("Authentication failed: " + e.getMessage());
			return false;
		}
		System.out.println("Successfully authenticated. Security context contains: " +
				SecurityContextHolder.getContext().getAuthentication());
		return true;
	}

	@Override
	public void hello() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username;
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
			System.out.println("Instance of UserDetails:" + username);
		} else {
			username = principal.toString();
			System.out.println(principal + ": " + username);
		}
		
		System.out.println("Hello " + username + "!");
	}
}
