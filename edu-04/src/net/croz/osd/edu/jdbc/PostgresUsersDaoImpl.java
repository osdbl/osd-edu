package net.croz.osd.edu.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import net.croz.osd.edu.domain.User;
import net.croz.osd.edu.domain.UserRole;

@Component
public class PostgresUsersDaoImpl implements UsersDao {

	@Autowired
	DataSource dataSource;

	@Override
	public List<User> getUsers() {
		List<User> users = new ArrayList<>();
		Connection c = null;
		try {
			c = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
		}
		Statement stmt = null;
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users;");
			while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				boolean enabled = rs.getBoolean("enabled");
				User user=new User(username, password, enabled, null);
				List<UserRole> roles = getUserRoles(user);
				Set<UserRole> set = new HashSet<UserRole>(roles);
				user.setRoles(set);
				users.add(user);
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return users;
	}

	@Override
	public List<UserRole> getUserRoles(User user) {
		Connection c = null;
		try {
			c = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
		}
		List<UserRole> roles = new ArrayList<UserRole>();
		Statement stmt = null;
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT authority FROM authorities WHERE username='" + user.getUsername() + "';");
			while (rs.next()) {
				String role = rs.getString("authority");
				UserRole userRole=new UserRole(user,role);
				roles.add(userRole);
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return roles;
	}

	@Override
	public void addUser(User user) {
		Connection c = null;
		try {
			c = dataSource.getConnection();
			c.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
		}
		Statement stmt = null;
		try {
			stmt = c.createStatement();
			String sql = "INSERT INTO users VALUES ('" + user.getUsername() + "','" + user.getPassword() + "','"
					+ user.isEnabled() + "');";
			stmt.executeUpdate(sql);
			c.commit();
			if(user.getRoles()!=null)
				addUserRoles(user);
			c.commit();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	@Override
	public void updateUser(User oldUser, User newUser) {
		Connection c = null;
		try {
			c = dataSource.getConnection();
			c.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
		}
		Statement stmt = null;
		try {
			stmt = c.createStatement();
			BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
			String pass = bcpe.encode(newUser.getPassword());
			String sql;
			if (!oldUser.getPassword().equals(newUser.getPassword())) {
				sql = "UPDATE users set username ='" + newUser.getUsername() + "',password='" + pass + "',enabled="
						+ newUser.isEnabled() + " where username ='" + oldUser.getUsername() + "';";
			} else {
				sql = "UPDATE users set username ='" + newUser.getUsername() + "',enabled=" + newUser.isEnabled()
						+ " where username ='" + oldUser.getUsername() + "';";
			}
			deleteUserRoles(oldUser);
			stmt.executeUpdate(sql);
			c.commit();
			addUserRoles(newUser);
			c.commit();
			System.out.println("Operation done successfully");

			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	@Override
	public void deleteUser(User user) {
		Connection c = null;
		try {
			c = dataSource.getConnection();
			c.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
		}
		Statement stmt = null;
		try {
			stmt = c.createStatement();
			String sql = "DELETE from users where username='" + user.getUsername() + "';";
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	@Override
	public void addUserRoles(User user) {
		Connection c = null;
		try {
			c = dataSource.getConnection();
			c.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
		}
		Statement stmt = null;
		try {
			stmt = c.createStatement();
			List<UserRole> list = new ArrayList<UserRole>(user.getRoles());
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getRole()!=""){
				String sql = "INSERT INTO authorities VALUES ('" + user.getUsername() + "','" + list.get(i).getRole() + "');";
				stmt.executeUpdate(sql);
				c.commit();
				}
			}
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	@Override
	public void deleteUserRoles(User user) {
		Connection c = null;
		try {
			c = dataSource.getConnection();
			c.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
		}
		Statement stmt = null;
		try {
			stmt = c.createStatement();
			String sql = "DELETE from authorities where username='" + user.getUsername() + "';";
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

}
