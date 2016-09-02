package net.croz.osd.edu.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import net.croz.osd.edu.ui.element.CustomTable.Data;



public class PostgreSQLJDBCUpdate {
   public static void updateUser(Data user )
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
         String sql = "UPDATE db set username ='"+user.getUsername()+"', password='"+user.getPassword()+"', role='"+user.getRole()+"',enabled="+user.isState()+" where id ="+user.getId()+";";
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
