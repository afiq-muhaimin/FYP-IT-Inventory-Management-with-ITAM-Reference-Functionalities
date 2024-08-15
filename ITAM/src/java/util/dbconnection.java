/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Afiq
 */
public class dbconnection {
    public static Connection createConnection()
    {   String driver = "org.apache.derby.jdbc.ClientDriver";
        String connectionString = "jdbc:oracle:thin:@DESKTOP-CI7HVFT:1521:XE";
        try
        {
            Class.forName(driver);
            System.out.println("Connected");
            return DriverManager.getConnection(connectionString,"ITAM_FYP","123");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    } 
}
