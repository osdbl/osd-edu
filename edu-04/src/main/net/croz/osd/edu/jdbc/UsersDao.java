package net.croz.osd.edu.jdbc;

import java.util.List;

import net.croz.osd.edu.domain.User;
import net.croz.osd.edu.domain.UserRole;

public interface UsersDao {

	static final String SQL_1 = 
		"SELECT u.username,u.password,u.enabled,a.authority FROM users u JOIN authorities a ON u.username=a.username";
	List<User> getUsers();
	
	/*
	List<UserRole> getUserRoles(User user);
	List<User> getUsersByStatus(boolean active);
	User findUserByUsername(String username);
	void addUser(User user);
	void updateUser(User oldUser,User newUser);
	void deleteUser(User user);
	void addUserRoles(User user);
	void deleteUserRoles(User user);
	*/
}
