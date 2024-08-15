/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Requester;
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
public class RequesterDao {

    public String add(Requester requester) {
        String requesterid = null;
        String phonenum = requester.getRequesterPhoneNum();
        String email = requester.getRequesterEmail();
        String name = requester.getRequesterName();
        String servid = requester.getServID();
         
        Connection con = null;
        
        System.out.println("Before try");
        String sql = "INSERT INTO REQUESTER (requesterphonenumber, requesteremail,requestername,servicesid,requesterid) VALUES ('"+phonenum+"','"+email+"','"+name+"','"+servid+"','REQT'|| REQUESTERID.NEXTVAL)";
        try
        {
            con = dbconnection.createConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.executeUpdate();
            String selectSql = "SELECT 'REQT' || REQUESTERID.CURRVAL FROM DUAL";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(selectSql);
            if (rs.next()) 
            {
                requesterid = rs.getString(1);
                System.out.println("RequesterID: "+requesterid);
                return requesterid;
            }

        }
        catch(SQLException ex)
        {
            ex.getMessage();
            System.out.println(ex.getMessage());
        }
        return "ERROR AT DAO: Requester; add";
    }

    public String find(String email,String phonenum) {
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM requester where requesteremail='"+email+"' AND requesterphonenumber='"+phonenum+"'"))
        {
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                String requesterid = rs.getString("requesterid");
                return requesterid;
            }
            else
            {
                return "norequester";
            }
            
        }
        catch(SQLException ex)
        {
           ex.getMessage();
           System.out.println(ex.getMessage());
        }
        return "ERRO AT DAO: Requester,add";
    }

    public ArrayList getAll() {
        ArrayList listrequest = new ArrayList();
        
         try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM requester"))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                String phonenum = rs.getString("requesterphonenumber");
                String email = rs.getString("requesteremail");
                String name = rs.getString("requestername");
                String servid = rs.getString("servicesid");
                String requesterid = rs.getString("requesterid");
                
                Requester requester = new Requester(requesterid, phonenum,email,name,servid);
                listrequest.add(requester);
            }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
         
        return listrequest;
    }
    
    public int [] findTotalAssetPerRequester(){
        int quantity[]={};
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT sum(ra.quantity) as \"num\" from requestedasset ra join requests rq on"
                + " ra.requestid = rq.requestid group by rq.requesterid order by rq.requesterid asc"))
        {
            
            ResultSet rs = stmt.executeQuery();
            int i=0;
            while(rs.next())
            {
                quantity[i] = Integer.parseInt(rs.getString("num"));
                i++;
            }
        }
        catch(SQLException ex)
        {
           ex.getMessage();
           System.out.println(ex.getMessage());
        }
        
        return quantity;
    }
    
    public int findMinAssetPerRequester(){
        int quantity=0;
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT min(sum(ra.quantity)) as \"num\" from requestedasset ra join "
                + "requests rq on ra.requestid = rq.requestid group by rq.requesterid order by rq.requesterid asc"))
        {
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next())
            {
                quantity= Integer.parseInt(rs.getString("num"));
            }
        }
        catch(SQLException ex)
        {
           ex.getMessage();
           System.out.println(ex.getMessage());
        }
        
        return quantity;
    }
    
    public int findMaxAssetPerRequester(){
        int quantity=0;
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT max(sum(ra.quantity)) as \"num\" from requestedasset ra join requests "
                + "rq on ra.requestid = rq.requestid group by rq.requesterid order by rq.requesterid asc"))
        {
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next())
            {
                quantity= Integer.parseInt(rs.getString("num"));
            }
        }
        catch(SQLException ex)
        {
           ex.getMessage();
           System.out.println(ex.getMessage());
        }
        
        return quantity;
    }
    
    public int[] findRequestPerRequester(){
        int quantity[]={};
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT count(requestid) as \"num\" "
                + "from requests group by requesterid order by requesterid asc"))
        {
            
            ResultSet rs = stmt.executeQuery();
            int i=0;
            while(rs.next())
            {
                quantity[i]= Integer.parseInt(rs.getString("num"));
                i++;
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
