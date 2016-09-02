package net.croz.osd.edu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import net.croz.osd.edu.ui.element.CustomTable.Data;
import net.croz.osd.edu.ui.element.CustomTable.MyTableModel;


public class PostgreSQLJDBCReturn {
   
	public static void getDatabase(MyTableModel model)
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
         ResultSet rs = stmt.executeQuery( "SELECT * FROM db" );
         while ( rs.next() ) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String password=rs.getString("password"); 
            String role = rs.getString("role");
            boolean enabled  =rs.getBoolean("enabled");
            Data d=new Data(id, username,password,role, enabled,"");
            model.add(new Data(id, username,password,role, enabled,""));
            
         }
         rs.close();
         stmt.close();
         c.close();
       } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
       }
       System.out.println("Operation done successfully");
     }
}
