package net.croz.osd.edu.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostgreSQLJDBCDelete {
	@Autowired
	DataSource dataSource;

	public void deleteUser(String username) {
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
			String sql = "DELETE from users where username='" + username + "';";
			// String sql1= "DELETE from authorities where
			// username='"+username+"';";
			// stmt.executeUpdate(sql1);
			stmt.executeUpdate(sql);
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
