package net.croz.osd.edu.jdbc;

import java.util.List;

import net.croz.osd.edu.domain.User;
import net.croz.osd.edu.domain.UserRole;

public interface UsersDao {

	List<User> getUsers();
	List<UserRole> getUserRoles(User user);
	
	void addUser(User user);
	void updateUser(User oldUser,User newUser);
	void deleteUser(User user);
	void addUserRoles(User user);
	void deleteUserRoles(User user);
	
}
