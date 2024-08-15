/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Assets;
import bean.Types;
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
public class AssetDao {
    public String addNewAsset(Assets asset)
    {
        String assetname = asset.getAssetName();
        String assetremarks = asset.getAssetRemarks();
        int assetoriginqty = asset.getAssetOriginQty();
        String assettype = asset.getTypeID();
        
        Connection con = null;
        
        String sql = "INSERT INTO ASSETS (assetid, assetname,assetoriginqty,assetremarks,typeid) VALUES ('AST'|| ASSETID.NEXTVAL,'"+assetname+"','"+assetoriginqty+"','"+assetremarks+"','"+assettype+"')";
        
        try
        {
             con = dbconnection.createConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             pstmt.executeUpdate();
             String selectSql = "SELECT 'AST' || ASSETID.CURRVAL FROM DUAL";
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(selectSql);
             if (rs.next()) 
             {
                String assetid = rs.getString(1);
                return assetid;
            }

        }
        catch(SQLException ex)
        {
            ex.getMessage();
            System.out.println(ex.getMessage());
        }
        
        return "FAILED";
    }
    
    public String addCurrentAsset(Assets asset)
    {
        String assetid = asset.getAssetID();
        int assetoriginqty = asset.getAssetOriginQty();
        Connection con = null;
        
        String sql = "UPDATE ASSETS SET ASSETORIGINQTY = ASSETORIGINQTY + "+assetoriginqty+" WHERE ASSETID = '"+assetid+"'";
        
        try
        {
             con = dbconnection.createConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             pstmt.executeUpdate();
             return assetid;

        }
        catch(SQLException ex)
        {
            ex.getMessage();
            System.out.println(ex.getMessage());
        }
        
        return "FAILED";
    }
    
    
    public ArrayList getAll()
    {
        ArrayList<Assets> allasset = new <Assets>ArrayList();
        
       try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM Assets order by assetID asc"))
       {
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next())
           {
               String assetid = rs.getString("assetID");
               String assetname = rs.getString("assetname");
               int assetqty = Integer.parseInt(rs.getString("assetoriginqty"));
               String assetremarks = rs.getString("assetremarks");
               String typeid = rs.getString("typeid");
               String assetimage = rs.getString("assetimage");
               
               Assets asset = new Assets (assetid, assetname,assetremarks,assetqty,typeid,assetimage);
               
               allasset.add(asset);
           }
       }
       catch(SQLException ex)
       {
           ex.getMessage();
           System.out.println(ex.getMessage());
       }

        return allasset;
    }

    public String getTypeID(String assetID) {
        String typeid = "";
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT typeid FROM Assets where assetID = '"+assetID+"'"))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                typeid = rs.getString("typeid");
            }
        }
        catch(SQLException ex)
        {
           ex.getMessage();
           System.out.println(ex.getMessage());
        }
        return typeid;
    }

    public Assets getAsset(String assetID) 
    {
        Assets asset = new Assets();
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM Assets where assetID = '"+assetID+"'"))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
           {
               String assetid = rs.getString("assetID");
               String assetname = rs.getString("assetname");
               int assetqty = Integer.parseInt(rs.getString("assetoriginqty"));
               String assetremarks = rs.getString("assetremarks");
               String typeid = rs.getString("typeid");
               String assetimage = rs.getString("assetimage");
               
               asset = new Assets (assetid, assetname,assetremarks,assetqty,typeid,assetimage);
           }
            
        }
        catch(SQLException ex)
        {
            ex.getMessage();
            System.out.println(ex.getMessage());
        }
        
        return asset;
    }

    public String updateAssetQty(String oldasset, int oldqty, int newqty) {
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("UPDATE assets SET assetoriginqty = assetoriginqty - "+oldqty+" + "+newqty+" where assetid = '"+oldasset+"' "))
        {
            ResultSet rs = stmt.executeQuery();
           
            return "SUCCESS";
            
        }
        catch(SQLException ex)
        {
            ex.getMessage();
            System.out.println(ex.getMessage());
        }
        
        return "FAILED at AssetDao";
    }

    public String updateAssetQty2(String newasset,String oldasset, int oldqty, int newqty) {
        //change at the oldasset
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("UPDATE assets SET assetoriginqty = assetoriginqty - "+oldqty+" where assetid = '"+oldasset+"' "))
        {
            stmt.executeQuery();
            String sqlupdate = "UPDATE assets SET assetoriginqty = assetoriginqty + "+newqty+" where assetid = '"+newasset+"'";
            Statement stmt1 = con.createStatement();
          
            ResultSet rs = stmt1.executeQuery(sqlupdate);
            return "SUCCESS";

        }
        catch(SQLException ex)
        {
            ex.getMessage();
            System.out.println(ex.getMessage());
        }
        
        return "FAILED at AssetDao";
    }

    public String updateAssetQty3(String oldassetid, int oldqty) {
       try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("UPDATE assets SET assetoriginqty = assetoriginqty - "+oldqty+" where assetid = '"+oldassetid+"' "))
        {
            ResultSet rs = stmt.executeQuery();
            return "SUCCESS";
            
        }
        catch(SQLException ex)
        {
            ex.getMessage();
            System.out.println(ex.getMessage());
        }
        
        return "FAILED at AssetDao";
    }

    public String updateAssetDetail(Assets asset) 
    {
        String assetname = asset.getAssetName();
        int assetqty = asset.getAssetOriginQty();
        String assetremarks = asset.getAssetRemarks();
        String assettype = asset.getTypeID();
        String assetid = asset.getAssetID();
        
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("UPDATE assets SET assetname='"+assetname+"',assetoriginqty='"+assetqty+"',assetremarks='"+assetremarks+"',typeid='"+assettype+"' where assetid = '"+assetid+"' "))
        {
            ResultSet rs = stmt.executeQuery();
            return "SUCCESS";
        }
        catch(SQLException ex)
        {
            ex.getMessage();
            System.out.println(ex.getMessage());
        }
        
        return "FAILED at AssetDao";
    }
    
    public int findMax(){
        int quantity=0;
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT max(assetoriginqty) as \"max\" FROM assets"))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                quantity = Integer.parseInt(rs.getString("max"));
            }
        }
        catch(SQLException ex)
        {
           ex.getMessage();
           System.out.println(ex.getMessage());
        }
        
        return quantity;
    }
    
    public int findMin(){
        int quantity=0;
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT min(assetoriginqty) as \"min\" FROM assets"))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                quantity = Integer.parseInt(rs.getString("min"));
            }
        }
        catch(SQLException ex)
        {
           ex.getMessage();
           System.out.println(ex.getMessage());
        }
        
        return quantity;
    }
    
    public double findAvg(){
        double quantity=0;
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT avg(assetoriginqty) as \"average\" FROM assets"))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                quantity = Double.parseDouble(rs.getString("average"));
            }
        }
        catch(SQLException ex)
        {
           ex.getMessage();
           System.out.println(ex.getMessage());
        }
        
        return quantity;
    }

    public String insertimage(String assetid, String assetimage) {
        try (Connection con = dbconnection.createConnection();
        PreparedStatement stmt = con.prepareStatement("UPDATE assets SET assetimage = '" + assetimage + "' where assetid = '"+ assetid +"'"))
        {
            ResultSet rs = stmt.executeQuery();
            return "SUCCESS";
        }
        catch(SQLException ex)
        {
            ex.getMessage();
            System.out.println(ex.getMessage());
        }
        return "FAILED at AssetDao";
    }
    
}
