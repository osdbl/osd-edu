package net.croz.osd.edu.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {
	@Autowired
	UserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authRequest) throws AuthenticationException {
		UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getName());
		
		if (authRequest.getCredentials().equals(userDetails.getPassword())) {
			return new UsernamePasswordAuthenticationToken(userDetails, null);
		}
		
		throw new BadCredentialsException("Bad Credentials");
	}
}
