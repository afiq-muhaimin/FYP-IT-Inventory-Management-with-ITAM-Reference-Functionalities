/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.AssetLogs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import util.dbconnection;


/**
 *
 * @author Afiq
 */
public class AssetLogsDao {
    public String updateLogs(AssetLogs assetlog)
    {
        String assetID = assetlog.getAssetID();
        String userID = assetlog.getUserID();
        java.util.Date dateupdate = assetlog.getDateupdate();
        //java.sql.Date dateupdateSQL = new java.sql.Date(dateupdate.getTime());
        int quantityinv = assetlog.getQuantityInvolve();
        String remarks = assetlog.getRemarks();
        String updateOperation = assetlog.getUpdateOperation();
        String requestID = assetlog.getRequestID();
        
        
        System.out.println(assetID + " " + userID +" "+ quantityinv + " " + remarks + " " + updateOperation + " " + requestID);

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        
        if(requestID == null)
        {
            String insertsql = "INSERT INTO assetlogs (assetlogsid,assetid,userid,dateupdate,quantityinvolve,remarks,updateoperation,requestid) VALUES ('ASL'||ASSETLOGSID.nextval,'" + assetID + "','" + userID + "',SYSDATE,'" + quantityinv + "','" + remarks + "','" + updateOperation + "',null)";
            System.out.println(insertsql);
            try {
                con = dbconnection.createConnection();
                pstmt = con.prepareStatement(insertsql);
                pstmt.executeQuery();
                return "SUCCESS";

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else
        {
            String insertsql = "INSERT INTO assetlogs (assetlogsid,assetid,userid,dateupdate,quantityinvolve,remarks,updateoperation,requestid) VALUES ('ASL'||ASSETLOGSID.nextval,'" + assetID + "','" + userID + "',SYSDATE,'" + quantityinv + "','" + remarks + "','" + updateOperation + "','"+requestID+"')";
            System.out.println(insertsql);
            try {
                con = dbconnection.createConnection();
                pstmt = con.prepareStatement(insertsql);
                pstmt.executeQuery();
                return "SUCCESS";

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        
        return "FAILED";
    }

    public String add(AssetLogs assetlog) {
        String resultadd = "";
        String assetID = assetlog.getAssetID();
        String userID = assetlog.getUserID();
        java.util.Date dateupdate = assetlog.getDateupdate();
        //java.sql.Date dateupdateSQL = new java.sql.Date(dateupdate.getTime());
        int quantityinv = assetlog.getQuantityInvolve();
        String remarks = assetlog.getRemarks();
        String updateOperation = assetlog.getUpdateOperation();
        String requestID = assetlog.getRequestID();
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        
        if(requestID == null)
        {
            String insertsql = "INSERT INTO assetlogs (assetlogsid,assetid,userid,dateupdate,quantityinvolve,remarks,updateoperation,requestid) VALUES ('ASL'||ASSETLOGSID.nextval,'" + assetID + "','" + userID + "',SYSDATE,'" + quantityinv + "','" + remarks + "','" + updateOperation + "',null)";
            System.out.println(insertsql);
            try {
                con = dbconnection.createConnection();
                pstmt = con.prepareStatement(insertsql);
                pstmt.executeQuery();
                
                resultadd="SUCCESS";
                return resultadd;

            } catch (SQLException ex) {
                ex.getMessage();
                System.out.println(ex.getMessage());
            }
        }
        return "ERROR: AssetLogs; add";
    }

    public ArrayList find(String mvtoperation,String assetidreceive) {
        ArrayList listastlogs = new ArrayList();
        AssetLogs astlogs = new AssetLogs();
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM assetlogs where updateoperation = '"+mvtoperation+"' and assetid = '"+assetidreceive+"'"))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                String assetlogsid = rs.getString("assetlogsid");
                String assetid = rs.getString("assetid");
                String userid = rs.getString("userid");
                String dateupdate = rs.getString("dateupdate");
                int qtyinvolves = Integer.parseInt(rs.getString("quantityinvolve"));
                String remarks = rs.getString("remarks");
                String updateoperation = rs.getString("updateoperation");
                String requestid = rs.getString("requestid");
                
                Date dateupdateconv = new Date();
                try
                {
                    dateupdateconv = new SimpleDateFormat("yyyy-MM-dd").parse(dateupdate);
                }
                catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
                
                astlogs = new AssetLogs(assetlogsid,assetid,userid,dateupdateconv,qtyinvolves,remarks,updateoperation,requestid);
                listastlogs.add(astlogs);
            }
        }
        catch(SQLException ex)
        {
            ex.getMessage();
            System.out.println(ex.getMessage());
        }
        
       return listastlogs;
    }

    public ArrayList getAll() {
        ArrayList listastlogs = new ArrayList();
        AssetLogs astlogs = new AssetLogs();
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM assetlogs order by assetlogsid asc"))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                String assetlogsid = rs.getString("assetlogsid");
                String assetid = rs.getString("assetid");
                String userid = rs.getString("userid");
                String dateupdate = rs.getString("dateupdate");
                int qtyinvolves = Integer.parseInt(rs.getString("quantityinvolve"));
                String remarks = rs.getString("remarks");
                String updateoperation = rs.getString("updateoperation");
                String requestid = rs.getString("requestid");
                
                Date dateupdateconv = new Date();
                try
                {
                    dateupdateconv = new SimpleDateFormat("yyyy-MM-dd").parse(dateupdate);
                }
                catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
                
                astlogs = new AssetLogs(assetlogsid,assetid,userid,dateupdateconv,qtyinvolves,remarks,updateoperation,requestid);
                listastlogs.add(astlogs);
            }
        }
        catch(SQLException ex)
        {
            ex.getMessage();
            System.out.println(ex.getMessage());
        }
        
       return listastlogs;
    }

    public AssetLogs find(String astlogid) {
        //ArrayList listastlogs = new ArrayList();
        AssetLogs astlogs = new AssetLogs();
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM assetlogs where assetlogsid = '"+astlogid+"'"))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                String assetlogsid = rs.getString("assetlogsid");
                String assetid = rs.getString("assetid");
                String userid = rs.getString("userid");
                String dateupdate = rs.getString("dateupdate");
                int qtyinvolves = Integer.parseInt(rs.getString("quantityinvolve"));
                String remarks = rs.getString("remarks");
                String updateoperation = rs.getString("updateoperation");
                String requestid = rs.getString("requestid");
                
                //For testing only
                System.out.println(dateupdate);
                Date dateupdateconv = new Date();
                try
                {
                    dateupdateconv = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateupdate);
                }
                catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
                System.out.println(dateupdateconv);
                astlogs = new AssetLogs(assetlogsid,assetid,userid,dateupdateconv,qtyinvolves,remarks,updateoperation,requestid);
            }
        }
        catch(SQLException ex)
        {
            ex.getMessage();
            System.out.println(ex.getMessage());
        }
        return astlogs;
    }

    public int findMax() {
        int quantity=0;
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT max(quantityinvolve) as \"minimum\" FROM assetlogs"))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                quantity = Integer.parseInt(rs.getString("minimum"));
            }
        }
        catch(SQLException ex)
        {
           ex.getMessage();
           System.out.println(ex.getMessage());
        }
        
        return quantity;
    }
    
     public int findMin() {
        int quantity=0;
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT min(quantityinvolve) as \"max\" FROM assetlogs"))
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
     
    public double findAvg() {
        double quantity=0;
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT avg(quantityinvolve) as \"average\" FROM assetlogs"))
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
}
