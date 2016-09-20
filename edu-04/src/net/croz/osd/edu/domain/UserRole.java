package net.croz.osd.edu.domain;

public class UserRole implements Comparable<UserRole>{

	private User user;
	private String role;
	
	public UserRole(User user,String role){
		this.setUser(user);
		this.setRole(role);
	}
	
	public UserRole(){}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public String toString(){
		return role;
	}

	@Override
	public int compareTo(UserRole userRole) {
		return this.toString().compareTo(userRole.toString());
	}
	
	
}
