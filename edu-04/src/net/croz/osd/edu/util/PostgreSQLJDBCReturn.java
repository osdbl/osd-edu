package net.croz.osd.edu.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.croz.osd.edu.ui.element.CustomTable.Data;
import net.croz.osd.edu.ui.element.CustomTable.MyTableModel;

@Component
public class PostgreSQLJDBCReturn {
	@Autowired
	DataSource dataSource;

	public void getDatabase(MyTableModel model) {
		model.removeAll();
		Connection c = null;
		try {
			c = dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
		}
		Statement stmt = null;
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM users JOIN authorities ON (users.username=authorities.username);");

			while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				String role = rs.getString("authority");
				boolean enabled = rs.getBoolean("enabled");
				model.add(new Data(username, password, role, enabled, ""));
			}
			System.out.println("Operation done successfully");
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			// System.exit(0);
		}

	}
}
