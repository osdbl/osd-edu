package net.croz.osd.edu.jdbc;

import java.util.Collection;
import java.util.List;

import net.croz.osd.edu.domain.User;
import net.croz.osd.edu.domain.UserRole;

public interface UsersDao {
	static final String SQL_1 = 
		"SELECT u.username, u.password, u.enabled, a.authority FROM users u JOIN authorities a ON u.username=a.username";
	
	List<User> loadUsers();
	public void saveUsers(Collection<User> users);
}
