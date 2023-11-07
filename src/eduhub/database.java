/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eduhub;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jcarl
 */
public class database {
      public static Connection getConnection() throws ClassNotFoundException{
        Connection conn;
        try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eduhub", "root", "");
           return conn;
        } catch (SQLException e) {
        
            e.printStackTrace();
        }
        return null;
    }
}
