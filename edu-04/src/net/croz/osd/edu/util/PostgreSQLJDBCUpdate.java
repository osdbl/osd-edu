package net.croz.osd.edu.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import net.croz.osd.edu.conf.JdbcConfig;
import net.croz.osd.edu.ui.element.CustomTable.Data;



public class PostgreSQLJDBCUpdate {
   public static void updateUser(Data old_user,Data new_user )
     {
       Connection c = JdbcConfig.getConnection();
       Statement stmt = null;
       try {
         stmt = c.createStatement();
         BCryptPasswordEncoder bcpe=new BCryptPasswordEncoder();
         String pass=bcpe.encode(new_user.getPassword());
         String sql = "UPDATE users set username ='"+new_user.getUsername()+"', password='"+pass+"',enabled="+new_user.isState()+" where username ='"+old_user.getUsername()+"';";
         String sql1= "UPDATE authorities set authority='"+new_user.getRole()+"' where username ='"+old_user.getUsername()+"' AND authority='"+old_user.getRole()+"';";
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
