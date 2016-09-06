package net.croz.osd.edu.conf;

import java.sql.Connection;
import java.sql.DriverManager;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcConfig {
	private static Connection c=null;
	public static Connection connect(String url, String user, String password) {
		
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(url, user, password);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return null;
		}

		return c;
	}

	public static Connection getConnection() {

		return JdbcConfig.connect("jdbc:postgresql://localhost:5432/osd-edu", "osd", "osd");

	}
}
