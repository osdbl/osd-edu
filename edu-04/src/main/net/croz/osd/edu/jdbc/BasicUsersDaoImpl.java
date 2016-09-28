package net.croz.osd.edu.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
import org.springframework.stereotype.Repository;

import net.croz.osd.edu.domain.User;
import net.croz.osd.edu.domain.UserRole;

@Repository
public class BasicUsersDaoImpl implements UsersDao {

	@Autowired
	DataSource dataSource;

	Connection c;

/*	@Override
	public List<User> getUsers() {
		List<User> users = new ArrayList<>();
		getConnection();
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
	}*/
	
	@Override
	public List<User> getUsersByStatus(boolean active) {
		List<User> users = new ArrayList<>();
		
		
		
		try {
			c=dataSource.getConnection();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		PreparedStatement preparedStatement = null;

		String query="SELECT username,enabled FROM users WHERE enabled=?;";

		try {
			preparedStatement = c.prepareStatement(query);
			preparedStatement.setBoolean(1, active);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				User user=new User();
				
				String username = rs.getString("username");
				boolean enabled = rs.getBoolean("enabled");
				
				users.add(user);
			}
		

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			try {
				
				preparedStatement.close();
				c.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block

			}
		}

		return users;
	}
	
	@Override
	public List<User> getUsers() {
		List<User> users = new ArrayList<>();
		getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery(SQL_1);
			String oldUsername="";
			String newUsername;
			while (rs.next()) {
				newUsername = rs.getString("username");
				String password = rs.getString("password");
				boolean enabled = rs.getBoolean("enabled");
				String authority=rs.getString("authority");
				if(!oldUsername.equals(newUsername)){
					User user = new User(newUsername, password, enabled, null);
					UserRole role=new UserRole(user,authority);
					Set<UserRole> set = new HashSet<UserRole>();
					set.add(role);
					user.setRoles(set);
					users.add(user);
					oldUsername=newUsername;
				}
				else{
					UserRole role=new UserRole(users.get(users.size()-1),authority);
					users.get(users.size()-1).getRoles().add(role);
				}
	
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
	}

	@Override
	public List<UserRole> getUserRoles(User user) {

		List<UserRole> roles = new ArrayList<UserRole>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT authority FROM authorities WHERE username='" + user.getUsername() + "';");
			while (rs.next()) {
				String role = rs.getString("authority");
				UserRole userRole = new UserRole(user, role);
				roles.add(userRole);
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			try {
				rs.close();
				stmt.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block

			}
		}

		return roles;
	}

	@Override
	public void addUser(User user) {
		
		getConnection();

		Statement stmt = null;
		try {
			stmt = c.createStatement();
			String sql = "INSERT INTO users VALUES ('" + user.getUsername() + "','" + user.getPassword() + "','"
					+ user.isEnabled() + "');";
			stmt.executeUpdate(sql);
			c.commit();
			if (user.getRoles() != null)
				addUserRoles(user);
			c.commit();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			try {
				stmt.close();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block

			}
		}
	}

	@Override
	public void updateUser(User oldUser, User newUser) {
		
		getConnection();

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

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());

		} finally {
			try {
				stmt.close();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block

			}
		}
	}

	@Override
	public void deleteUser(User user) {

		getConnection();
		Statement stmt = null;
		try {
			stmt = c.createStatement();
			String sql = "DELETE from users where username='" + user.getUsername() + "';";
			stmt.executeUpdate(sql);
			c.commit();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			try {
				stmt.close();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block

			}
		}
	}

	@Override
	public void addUserRoles(User user) {

		Statement stmt = null;
		try {
			stmt = c.createStatement();
			List<UserRole> list = new ArrayList<UserRole>(user.getRoles());
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getRole() != "") {
					String sql = "INSERT INTO authorities VALUES ('" + user.getUsername() + "','"
							+ list.get(i).getRole() + "');";
					stmt.executeUpdate(sql);
					c.commit();
				}
			}

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			try {
				stmt.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block

			}
		}
	}

	@Override
	public void deleteUserRoles(User user) {

		Statement stmt = null;
		try {
			stmt = c.createStatement();
			String sql = "DELETE from authorities where username='" + user.getUsername() + "';";
			stmt.executeUpdate(sql);
			c.commit();
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			try {
				stmt.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block

			}
		}
	}

	public void getConnection() {

		try {
            
			c = dataSource.getConnection();
			c.setAutoCommit(false);

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
		}

	}

	@Override
	public User findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
