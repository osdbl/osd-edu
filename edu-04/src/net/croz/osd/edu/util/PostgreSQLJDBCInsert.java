package net.croz.osd.edu.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostgreSQLJDBCInsert {
	@Autowired
	DataSource dataSource;

	public void insertUser(String username, String password, String role, boolean enabled) {
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
			String sql = "INSERT INTO users (username,password,enabled) VALUES ('" + username + "','" + password + "',"
					+ enabled + ");";
			String sql1 = "INSERT INTO authorities (username,authority) VALUES ('" + username + "','ROLE_USER');";
			stmt.executeUpdate(sql);
			stmt.executeUpdate(sql1);
			if(role=="ROLE_ADMIN"){
				String sql2="INSERT INTO authorities (username,authority) VALUES ('" + username + "','ROLE_ADMIN');";
				stmt.executeUpdate(sql2);
			}
			c.commit();
			System.out.println("Operation done successfully");

			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
}