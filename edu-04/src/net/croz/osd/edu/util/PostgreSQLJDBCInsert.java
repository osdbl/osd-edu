package net.croz.osd.edu.util;

import java.sql.Connection;
import java.sql.Statement;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import net.croz.osd.edu.conf.JdbcConfig;

public class PostgreSQLJDBCInsert {
	   public static void insertUser(String username,String password,String role,boolean enabled )
	     {
	       Connection c = JdbcConfig.getConnection();
	       Statement stmt = null;
	       try {
	         stmt = c.createStatement();
	         BCryptPasswordEncoder bcpe=new BCryptPasswordEncoder();
	         String pass=bcpe.encode(password);
	         String sql = "INSERT INTO users (username,password,enabled) VALUES ('"+username+"','"+pass+"',"+enabled+");";
	         String sql1 = "INSERT INTO authorities (username,authority) VALUES ('"+username+"','"+role+"');";
	         stmt.executeUpdate(sql);
	         stmt.executeUpdate(sql1);
	         c.commit();
	         System.out.println("Operation done successfully");

	         stmt.close();
	         c.close();
	       } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	       }
	     }
	}