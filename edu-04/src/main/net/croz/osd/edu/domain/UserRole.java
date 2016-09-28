package net.croz.osd.edu.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
//@IdClass(UserRolePK.class)
public class UserRole implements Comparable<UserRole>, Serializable {

	/*private UserRolePK userRolePK;

	@EmbeddedId
	public UserRolePK getUserRolePK() { return userRolePK; }
	  
	 public void setUserRolePK(UserRolePK userRolePK) { this.userRolePK =
	  userRolePK; }*/
	 
	private int id;
	private User user;
	private String role;

	public UserRole(User user, String role) {
		this.setUser(user);
		this.setRole(role);
	}

	public UserRole() {
	}
	
	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	//@Id
	//@Column(name = "username")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Id
	@Column(name = "authority")
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String toString() {
		return role;
	}

	@Override
	public int compareTo(UserRole userRole) {
		return this.toString().compareTo(userRole.toString());
	}

}
