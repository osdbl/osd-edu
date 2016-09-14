package net.croz.osd.edu.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import net.croz.osd.edu.ui.element.CustomTable.Data;

@Component
public class PostgreSQLJDBCUpdate {
	@Autowired
	DataSource dataSource;

	public void updateUser(Data old_user, Data new_user) {
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
			String pass = bcpe.encode(new_user.getPassword());
			String sql;
			if (!old_user.getPassword().equals(new_user.getPassword())) {
				sql = "UPDATE users set username ='" + new_user.getUsername() + "', password='" + pass + "',enabled="+ new_user.isState() + " where username ='" + old_user.getUsername() + "';";
			} else {
				sql = "UPDATE users set username ='" + new_user.getUsername() + "',enabled=" + new_user.isState()+ " where username ='" + old_user.getUsername() + "';";
			}
			stmt.executeUpdate(sql);
			String sql1;
			sql1 = "UPDATE authorities set authority='" + new_user.getRole() + "' where username ='"
					+ old_user.getUsername() + "' AND authority='" + old_user.getRole() + "';";
			stmt.executeUpdate(sql1);
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
