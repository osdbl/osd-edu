package net.croz.osd.edu.domain;

import java.util.Set;

public class User implements Comparable<User>{

	private String username;
	private String password;
	private boolean enabled;
	private Set<UserRole> userRole;
	
	public User(String username, String password, boolean enabled, Set<UserRole> userRole) {
		this.setUsername(username);
		this.setPassword(password);
		this.setEnabled(enabled);
		this.setRoles(userRole);
	}
	
	public User(){}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<UserRole> getRoles() {
		return userRole;
	}

	public void setRoles(Set<UserRole> userRole) {
		this.userRole = userRole;
	}
	
	@Override
	public int compareTo(User user) {
		return this.getUsername().compareTo(user.getUsername());
	}
	
}
