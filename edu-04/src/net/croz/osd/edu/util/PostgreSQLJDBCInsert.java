package net.croz.osd.edu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import net.croz.osd.edu.ui.element.CustomTable.Data;

public class PostgreSQLJDBCInsert {
	   public static void insertUser(String username,String password,String role,boolean enabled )
	     {
	       Connection c = null;
	       Statement stmt = null;
	       try {
	       Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/postgres",
	            "postgres", "postgres");
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         String sql = "INSERT INTO db (username,password,role,enabled) VALUES ('"+username+"','"+password+"','"+role+"',"+enabled+");";
	         stmt.executeUpdate(sql);
	         c.commit();

	         stmt.close();
	         c.close();
	       } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	       }
	       System.out.println("Operation done successfully");
	     }
	}