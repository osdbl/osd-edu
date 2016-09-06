package net.croz.osd.edu.util;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;

import net.croz.osd.edu.conf.JdbcConfig;
import net.croz.osd.edu.ui.element.CustomTable.Data;
import net.croz.osd.edu.ui.element.CustomTable.MyTableModel;


public class PostgreSQLJDBCReturn {
   
	public static void getDatabase(MyTableModel model)
     {
       Connection c = JdbcConfig.getConnection();
       Statement stmt = null;
       try {
         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM users JOIN authorities ON (users.username=authorities.username);" );
         
         while ( rs.next() ) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String password=rs.getString("password"); 
            String role = rs.getString("authority");
            boolean enabled  =rs.getBoolean("enabled");
            model.add(new Data(id, username,password,role, enabled,""));
         }
         System.out.println("Operation done successfully");
         rs.close();
         stmt.close();
         c.close();
       } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         //System.exit(0);
       }

     }
}
