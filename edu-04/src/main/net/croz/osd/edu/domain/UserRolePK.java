package net.croz.osd.edu.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

//@Embeddable
public class UserRolePK implements Serializable{
	
	private User user;
	private String role;
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "authority")
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

}
