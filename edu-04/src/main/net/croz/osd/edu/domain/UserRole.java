package net.croz.osd.edu.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import net.croz.osd.edu.domain.embeddable.UserRolePK;

@Entity
@Table(name = "authorities")
public class UserRole {
	private UserRolePK userRolePK;

	public UserRole() {}
	
	public UserRole(User user, String role) {
		userRolePK = new UserRolePK();
		userRolePK.setUsername(user.getUsername());
		userRolePK.setRole(role);
	}

	
	@EmbeddedId
	public UserRolePK getUserRolePK() { 
		return userRolePK; 
	}
	public void setUserRolePK(UserRolePK userRolePK) { 
		this.userRolePK = userRolePK; 
	}

	@Override
	public String toString() {
		return userRolePK.toString();
	}
}
