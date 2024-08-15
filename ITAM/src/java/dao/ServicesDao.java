/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.dbconnection;

/**
 *
 * @author Afiq
 */
public class ServicesDao {

    public ArrayList getAll() {
        ArrayList <Services> allservice = new ArrayList();
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM Services"))
       {
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next())
           {
               String servid = rs.getString("servicesID");
               String servname = rs.getString("servicesName");
               String deptid = rs.getString("deptID");
               System.out.println(servid + " " + servname + " " + deptid);
               Services serv = new Services(servid,servname,deptid);
               allservice.add(serv);
           }
       }
       catch(SQLException ex)
       {
          ex.getMessage();
       }
       
        return allservice;
    }
    
}
