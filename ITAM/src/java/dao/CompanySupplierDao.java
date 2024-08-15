/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.CompanySupplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.dbconnection;

/**
 *
 * @author Afiq
 */
public class CompanySupplierDao {
    public ArrayList getAll()
    {
        ArrayList<CompanySupplier> allcompanies = new <CompanySupplier>ArrayList();
        
       try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM companysupplier"))
       {
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next())
           {
               String supplierid = rs.getString("companysupplierID");
               String suppliername = rs.getString("companysupplierName");
               
               CompanySupplier supplier = new CompanySupplier (supplierid, suppliername);
               
               allcompanies.add(supplier);
           }
       }
       catch(SQLException ex)
       {
           
       }
        
        
        return allcompanies;
    }
    
    public String addCompany(CompanySupplier company)
    {
        String companyname = company.getCompanySupplierName();
        System.out.println(companyname);
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        
        String insertsql = "INSERT INTO COMPANYSUPPLIER (companysupplierid,companysuppliername) VALUES ('CMP'||COMPANYSUPPLIERID.NEXTVAL,'"+companyname+"')";
        System.out.println(insertsql);
        try
        {
            con = dbconnection.createConnection();
            pstmt = con.prepareStatement(insertsql);
            pstmt.executeUpdate();
            String selectSql = "SELECT 'CMP' || COMPANYSUPPLIERID.CURRVAL FROM DUAL";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(selectSql);
            if (rs.next()) {
                String companyid = rs.getString(1);
                return companyid;
            }
        }
        catch(SQLException ex)
        {
            ex.getMessage();
        }
        return "FAILED";
        
    }

}
