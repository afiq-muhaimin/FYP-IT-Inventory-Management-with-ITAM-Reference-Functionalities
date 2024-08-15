/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.RequestedAsset;
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
public class RequestedAssetDao {
    
    
    public ArrayList getAll()
    {
        ArrayList<RequestedAsset> listreqast = new ArrayList();
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT * from requestedasset"))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                String requestedID = rs.getString("requestedid");
                String requestID = rs.getString("requestid");
                String assetID= rs.getString("assetid");
                int quantity = Integer.parseInt(rs.getString("quantity"));
                
                
                RequestedAsset requestedasset = new RequestedAsset(requestedID,requestID,assetID,quantity);
                listreqast.add(requestedasset);
                
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        return listreqast;
    }
    
    public ArrayList getRequestedAsset(String assetid) {
        ArrayList<RequestedAsset> listreqast = new ArrayList();
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT * from requestedasset where assetid = '"+assetid+"'"))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                String requestedID = rs.getString("requestedid");
                String requestID = rs.getString("requestid");
                String assetID= rs.getString("assetid");
                int quantity = Integer.parseInt(rs.getString("quantity"));
                
                System.out.println(requestedID + " " + requestID + " " + assetID + " " + quantity);
                
                RequestedAsset requestedasset = new RequestedAsset(requestedID,requestID,assetID,quantity);
                listreqast.add(requestedasset);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return listreqast;
    }

    public int getSumQuantityReqAst(String assetid) {
        
        int sumquantity = 0;
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("select sum(quantity) AS sumquantity from requestedasset where assetid = '"+assetid+"'"))
                {
                    ResultSet rs = stmt.executeQuery();
                    if(rs.next())
                    {
                         sumquantity = Integer.parseInt(rs.getString("sumquantity"));
                    }
                }
                catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
        return sumquantity;
    }

    public String add(RequestedAsset reqast) {
        String requestID = reqast.getRequestID();
        String assetID = reqast.getAssetID();
        int quantity = reqast.getQuantity();
        
        Connection con = null;
        
        String result = "";
        
        String sql = "INSERT INTO REQUESTEDASSET (requestedid, requestid,assetid,quantity) VALUES ('RQAT'|| REQUESTEDASSETID.NEXTVAL,'"+requestID+"','"+assetID+"','"+quantity+"')";
         try
        {
             con = dbconnection.createConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             pstmt.executeUpdate();
             
             result = "SUCCESS";
             return result;

        }
        catch(SQLException ex)
        {
            ex.getMessage();
            System.out.println(ex.getMessage());
        }
        
         return "ERROR: Requested Asset; add";
    }

    public ArrayList find(String requestid) {
        ArrayList<RequestedAsset> listreqast = new ArrayList();
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT * from requestedasset where requestid = '"+requestid+"'"))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                String requestedID = rs.getString("requestedid");
                String requestID = rs.getString("requestid");
                String assetID= rs.getString("assetid");
                int quantity = Integer.parseInt(rs.getString("quantity"));
                
                System.out.println(requestedID + " " + requestID + " " + assetID + " " + quantity);
                
                RequestedAsset requestedasset = new RequestedAsset(requestedID,requestID,assetID,quantity);
                listreqast.add(requestedasset);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return listreqast;
    }

}
