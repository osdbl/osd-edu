package net.croz.osd.edu.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.croz.osd.edu.domain.User;
import net.croz.osd.edu.domain.UserRole;

@Repository
public class BasicUsersDaoImpl implements UsersDao {
	@Autowired DataSource dataSource;

	Connection c;

	@Override
	public List<User> loadUsers() {
		return new ArrayList<User>();
		/*
		List<User> users = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM users;");
			while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				boolean enabled = rs.getBoolean("enabled");
				User user = new User(username, password, enabled, null);
				List<UserRole> roles = getUserRoles(user);
				Set<UserRole> set = new HashSet<UserRole>(roles);
				user.setRoles(set);
				users.add(user);
			}

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			try {
				rs.close();
				stmt.close();
				c.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block

			}
		}

		return users;
		*/
	}

	@Override
	@Transactional
	public void saveUsers(Collection<User> users) {
		// TODO Auto-generated method stub
		
	}


}
