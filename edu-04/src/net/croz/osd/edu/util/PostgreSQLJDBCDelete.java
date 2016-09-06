package net.croz.osd.edu.util;

import java.sql.Connection;
import java.sql.Statement;

import net.croz.osd.edu.conf.JdbcConfig;


public class PostgreSQLJDBCDelete {
   public static void deleteUser(String username)
     {
       Connection c = JdbcConfig.getConnection();
       Statement stmt = null;
       try {
         stmt = c.createStatement();
         String sql = "DELETE from users where username='"+username+"';";
         String sql1= "DELETE from authorities where username='"+username+"';";
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

