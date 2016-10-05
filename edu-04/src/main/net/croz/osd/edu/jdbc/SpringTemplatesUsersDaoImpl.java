package net.croz.osd.edu.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import net.croz.osd.edu.domain.User;
import net.croz.osd.edu.domain.UserRole;

@Repository
public class SpringTemplatesUsersDaoImpl implements UsersDao {
	private JdbcTemplate jdbcTemplate;
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void init(DataSource dataSource,SessionFactory sessionFactory) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	public List<User> getUsers() {
		return hibernateTemplate.loadAll(User.class);
	}

	
	/*
	@Override
	public List<User> getUsers() {
		return this.jdbcTemplate.query(
				SQL_1,
				new ResultSetExtractor<List<User>>() {
					@Override
					public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<User> users = new ArrayList<User>();
						String oldUsername = "";
						String newUsername;
						while (rs.next()) {
							newUsername = rs.getString("username");
							String password = rs.getString("password");
							boolean enabled = rs.getBoolean("enabled");
							String authority = rs.getString("authority");
							if (!oldUsername.equals(newUsername)) {
								User user = new User(newUsername, password, enabled, null);
								UserRole role = new UserRole(user, authority);
								Set<UserRole> set = new HashSet<UserRole>();
								set.add(role);
								user.setRoles(set);
								users.add(user);
								oldUsername = newUsername;
							} else {
								UserRole role = new UserRole(users.get(users.size() - 1), authority);
								users.get(users.size() - 1).getRoles().add(role);
							}

						}
						return users;
					}
				});
	}
	*/
	
	/*
	@Override
	public List<User> getUsersByStatus(boolean active) {
		return this.jdbcTemplate.query("SELECT username,enabled FROM users WHERE enabled= ?", new Object[] { active },
				new BeanPropertyRowMapper<User>(User.class));
	}


	@Override
	public User findUserByUsername(String username) {
		User user = new User();
		hibernateTemplate.load(user, "jsajlovic");
		return user;
	}
	 */
}
