/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Requests;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import util.dbconnection;

/**
 *
 * @author Afiq
 */
public class RequestDao {

    public String check() {
        
        String value = "";
        
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT * from requests"))
        {
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                value="data_exist";
            }
        }
        catch(SQLException ex)
        {
            ex.getMessage();
        }
        
        return value;
    }

    public ArrayList getAll() {
        ArrayList allrequest = new ArrayList();
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT * from requests order by requestid asc"))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                String requestid = rs.getString("requestid");
                String userid = rs.getString("userid");
                String datetaken = rs.getString("datetaken");
                String datereturn = rs.getString("datereturn");
                String dateapprove = rs.getString("dateapprove");
                String daterequest = rs.getString("daterequest");
                String requeststatus = rs.getString("requeststatus");
                String requesterid = rs.getString("requesterid");
                
                Date datetakenconv = new Date();
                Date datereturnconv = new Date();
                Date dateapproveconv = new Date();
                Date daterequestconv = new Date();
                
                try {
                    datetakenconv = new SimpleDateFormat("yyyy-MM-dd").parse(datetaken);
                    datereturnconv = new SimpleDateFormat("yyyy-MM-dd").parse(datereturn);
                    dateapproveconv = new SimpleDateFormat("yyyy-MM-dd").parse(dateapprove);
                    daterequestconv = new SimpleDateFormat("yyyy-MM-dd").parse(daterequest);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                
                
                Requests req = new Requests(requestid,userid,datetakenconv,datereturnconv,dateapproveconv,daterequestconv,requeststatus,requesterid);
                allrequest.add(req);
            }
        }
        catch(SQLException ex)
        {
             ex.getMessage();
        }
        return allrequest;
    }

    public Date getDateTaken(String reqid) {
          System.out.println("DAO: date taken for req" + reqid);
        Date datetakenconv = new Date();
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT datetaken from requests where requestid = '"+reqid+"'"))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
               //stuck here
                String datetaken = rs.getString("datetaken");
                System.out.println("DAO: "+ datetaken);
                
                try {
                    datetakenconv = new SimpleDateFormat("yyyy-MM-dd").parse(datetaken);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }
            
        }
        catch(SQLException ex)
        {
            ex.getMessage();
        }
        return datetakenconv;
    }

    public Date getDateReturn(String reqid) {
         System.out.println("DAO: date return for req" + reqid);
        Date datereturnconv = new Date();
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT datereturn from requests where requestid = '"+reqid+"'"))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                String datereturn = rs.getString("datereturn");
                System.out.println("DAO: "+ datereturn);
                try {
                    datereturnconv = new SimpleDateFormat("yyyy-MM-dd").parse(datereturn);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            
        }
        catch(SQLException ex)
        {
            ex.getMessage();
        }
        return datereturnconv;
    }

    public String add(Requests req) {
        String userID = req.getUserID();
        String reqstatus = req.getRequestStatus();
        String requesterID = req.getRequesterID();
        
        Date datetaken = new Date();
        datetaken = req.getDateTaken();
        
        java.sql.Date datesqltaken = new java.sql.Date(datetaken.getTime());
        
        Date datereturn = new Date();
        datereturn = req.getDateReturn();
        java.sql.Date datesqlreturn = new java.sql.Date(datereturn.getTime());
        
        Date dateapprove = new Date();
        dateapprove = req.getDateApprove();
        java.sql.Date datesqlapprove = new java.sql.Date(dateapprove.getTime());
        
        Date daterequest = new Date();
        daterequest = req.getDateRequest();
        java.sql.Date datesqlrequest = new java.sql.Date(daterequest.getTime());
        
        Connection con = null;
        
        String sql = "INSERT INTO REQUESTS (requestid, userid,datetaken,datereturn,dateapprove,daterequest,requeststatus,requesterid) VALUES ('RQT'|| REQUESTID.NEXTVAL,'"+userID+"',to_date('"+datesqltaken+"','yyyy-mm-dd'),to_date('"+datesqlreturn+"','yyyy-mm-dd'),to_date('"+datesqlapprove+"','yyyy-mm-dd'),SYSDATE,'"+reqstatus+"','"+requesterID+"')";
        System.out.println(sql);
        try
        {
             con = dbconnection.createConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             pstmt.executeUpdate();
             String selectSql = "SELECT 'RQT' || REQUESTID.CURRVAL FROM DUAL";
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(selectSql);
             if (rs.next()) 
             {
                String requestID = rs.getString(1);
                return requestID;
            }

        }
        catch(SQLException ex)
        {
            ex.getMessage();
            System.out.println(ex.getMessage());
        }
        return ("ERROR AT DAO: Request; add");
    }

    public String updateStatus(String newstatus, String requestid) {
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("UPDATE REQUESTS SET  REQUESTSTATUS ='"+newstatus+"' WHERE REQUESTID = '"+requestid+"'"))
        {
             ResultSet rs = stmt.executeQuery();
             if(rs.next())
             {
               String result = "SUCCESS";  
               return result;
             }
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        
        return "ERROR AT DAO: Request; updateStatus";
    }

    public ArrayList find(String requesterid) {
        ArrayList allrequest = new ArrayList();
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT * from requests where requesterid='"+requesterid+"'"))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                String requestid = rs.getString("requestid");
                String userid = rs.getString("userid");
                String datetaken = rs.getString("datetaken");
                String datereturn = rs.getString("datereturn");
                String dateapprove = rs.getString("dateapprove");
                String daterequest = rs.getString("daterequest");
                String requeststatus = rs.getString("requeststatus");
                String reqid = rs.getString("requesterid");
                
                Date datetakenconv = new Date();
                Date datereturnconv = new Date();
                Date dateapproveconv = new Date();
                Date daterequestconv = new Date();
                
                try {
                    datetakenconv = new SimpleDateFormat("yyyy-MM-dd").parse(datetaken);
                    datereturnconv = new SimpleDateFormat("yyyy-MM-dd").parse(datereturn);
                    dateapproveconv = new SimpleDateFormat("yyyy-MM-dd").parse(dateapprove);
                    daterequestconv = new SimpleDateFormat("yyyy-MM-dd").parse(daterequest);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                
                
                Requests req = new Requests(requestid,userid,datetakenconv,datereturnconv,dateapproveconv,daterequestconv,requeststatus,reqid);
                allrequest.add(req);
            }
        }
        catch(SQLException ex)
        {
             ex.getMessage();
        }
        return allrequest;
    }
    
    public  List<Integer>findAvgDay(){
        List<Integer>avgquantity= new ArrayList();
        int i=0;
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT round (avg(datereturn-datetaken)) as \"average\" from requests group by requestid order by requestid asc"))
        {
            ResultSet rs = stmt.executeQuery();

            while(rs.next())
            {
                int num = Integer.parseInt(rs.getString("average"));
                avgquantity.add(num);
                System.out.println(rs.getString("average"));
                i++;
                System.out.println("i : " + i);
            }
        }
        catch(SQLException ex)
        {
           ex.getMessage();
           System.out.println(ex.getMessage());
        }
        
        return avgquantity;
    }
    
    //use for users
    public int[] findRequestbyUser(){
        int avgquantity[]={};
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT count(requestid) as \"num\"from requests group by requesterid order by requesterid asc"))
        {
            ResultSet rs = stmt.executeQuery();
            int i=0;
            while(rs.next())
            {
                avgquantity[i] = Integer.parseInt(rs.getString("num"));
                i++;
            }
        }
        catch(SQLException ex)
        {
           ex.getMessage();
           System.out.println(ex.getMessage());
        }
        
        return avgquantity;
    }
    
     public int findMaxRequestbyUser(){
        int maxquantity=0;
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT max(count(requestid)) as "
                + "\"num\" from requests group by requesterid order by requesterid asc"))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                maxquantity = Integer.parseInt(rs.getString("num"));
            }
        }
        catch(SQLException ex)
        {
           ex.getMessage();
           System.out.println(ex.getMessage());
        }
        
        return maxquantity;
    }
     
   public int findMinRequestbyUser(){
        int minquantity=0;
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT min(count(requestid)) as "
                + "\"num\" from requests group by requesterid order by requesterid asc"))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                minquantity = Integer.parseInt(rs.getString("num"));
            }
        }
        catch(SQLException ex)
        {
           ex.getMessage();
           System.out.println(ex.getMessage());
        }
        
        return minquantity;
    }
   
    public List<Integer> findTotalAssetPerRequest(){
        List<Integer> quantity= new ArrayList();
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT sum(quantity) as \"num\" "
                + "from requestedasset group by requestid order by requestid asc"))
        {
            int i=0;
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                quantity.add(Integer.parseInt(rs.getString("num")));
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
    
    public int findMaxAssetPerRequest(){
        int quantity=0;
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT max(sum(quantity)) as "
                + "\"num\" from requestedasset group by requestid order by requestid asc"))
        {
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                quantity = Integer.parseInt(rs.getString("num"));
                
            }
        }
        catch(SQLException ex)
        {
           ex.getMessage();
           System.out.println(ex.getMessage());
        }
        
        return quantity;
    }
    
    public String findAssetPerRequestMax()
    {
        String assetid = "";
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT assetid FROM requestedasset "
                + "GROUP BY assetid HAVING SUM(quantity) = (SELECT MAX(total_quantity)FROM "
                + "(SELECT assetid, SUM(quantity) AS total_quantity FROM requestedasset GROUP BY assetid))"))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                assetid = rs.getString("assetid");
            }
            
        }
        catch(SQLException ex)
        {
           ex.getMessage();
           System.out.println(ex.getMessage());
        }
        
        return assetid;
    }
    
     public int findMinAssetPerRequest(){
        int quantity=0;
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT min(sum(quantity)) as "
                + "\"num\" from requestedasset group by requestid order by requestid asc"))
        {
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                quantity = Integer.parseInt(rs.getString("num"));
                
            }
        }
        catch(SQLException ex)
        {
           ex.getMessage();
           System.out.println(ex.getMessage());
        }
        
        return quantity;
    }
     
    public String findAssetPerRequestMin()
    {
        String assetid = "";
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT assetid FROM requestedasset GROUP BY "
                + "assetid HAVING SUM(quantity) = (SELECT min(total_quantity)FROM "
                + "(SELECT assetid, SUM(quantity) AS total_quantity FROM requestedasset GROUP BY assetid))"))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                assetid = rs.getString("assetid");
            }
            
        }
        catch(SQLException ex)
        {
           ex.getMessage();
           System.out.println(ex.getMessage());
        }
        
        return assetid;
    }
    
    
    

    
    
}
