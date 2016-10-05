package net.croz.osd.edu.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	private String username;
	private String password;
	private boolean enabled;
	private Set<UserRole> userRole;
	
	public User() {}
	
	public User(String username, String password, boolean enabled, Set<UserRole> userRole) {
		this.setUsername(username);
		this.setPassword(password);
		this.setEnabled(enabled);
		this.setRoles(userRole);
	}
	
	
	@Id @Column
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Column
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Column
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "userRolePK.username")
	public Set<UserRole> getRoles() {
		return userRole;
	}
	public void setRoles(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", enabled=" + enabled + ", userRole="
				+ userRole + "]";
	}
	
}
