/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.PurchaseDetails;
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
public class PurchaseDetailDao {
    public String insertPurchaseDetails(PurchaseDetails purchasedetail)
    {
        String companyid = purchasedetail.getCompanysupplierID();
        String ponumber = purchasedetail.getPONumber();
        String invoicenumber = purchasedetail.getInvoiceNumber();
        String assetid = purchasedetail.getAssetID();
        int purchaseqty = purchasedetail.getPurchaseqty();
        
        Date datepurchase = purchasedetail.getDatePurchase();
        java.sql.Date datesql = new java.sql.Date(datepurchase.getTime());
        
        System.out.println(companyid + " " + ponumber + " "+ invoicenumber + " " + datesql + " " + assetid + " " + datepurchase);
        Connection con=null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        
        String insertsql = "INSERT INTO purchasedetail (purchaseid,ponumber,invoicenumber,datepurchase,assetid,companysupplierid,purchaseqty) VALUES ('PCS'||PURCHASEID.NEXTVAL,'"+ponumber+"','"+invoicenumber+"',to_date('"+datesql+"','yyyy-mm-dd'),'"+assetid+"','"+companyid+"','"+purchaseqty+"')";
        System.out.println(insertsql);
        try
        {
            con = dbconnection.createConnection();
            pstmt = con.prepareStatement(insertsql);
            pstmt.executeUpdate();
            return "SUCCESS";
        }
        catch(SQLException ex)
        {
            ex.getMessage();
        }
        return "FAILED";
    }
    
    public ArrayList getAll()
    {
        ArrayList <PurchaseDetails> allpdetails = new <PurchaseDetails> ArrayList();
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM purchasedetail order by purchaseid asc"))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                String purchaseid = rs.getString("purchaseid");
                String ponumber = rs.getString("ponumber");
                String invoicenum = rs.getString("invoicenumber");
                String datepurchase = rs.getString("datepurchase");
                String assetid = rs.getString("assetid");
                String companysupplierid = rs.getString("companysupplierid");
                int purchaseqty = Integer.parseInt(rs.getString("purchaseqty"));
                //String purchaseqty = rs.getString("purchaseqty");
                
                Date datepurchaseconv = new Date();
                try {
                    datepurchaseconv = new SimpleDateFormat("yyyy-MM-dd").parse(datepurchase);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                PurchaseDetails pdetail = new PurchaseDetails(purchaseid,companysupplierid,invoicenum,ponumber,datepurchaseconv,assetid, purchaseqty);
                allpdetails.add(pdetail);
            }
        }
        catch(SQLException ex)
        {
            ex.getMessage();
        }
        
        return allpdetails;
    }

    public PurchaseDetails getPdetail(String purchaseid) {
        System.out.println("in getpdetail");
        PurchaseDetails pdetail = new PurchaseDetails();
        System.out.println("SELECT * FROM purchasedetail where purchaseid = '"+purchaseid+"'");
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM purchasedetail where purchaseid = '"+purchaseid+"'"))
        
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                
                String ponumber = rs.getString("ponumber");
                String invoicenum = rs.getString("invoicenumber");
                String datepurchase = rs.getString("datepurchase");
                String assetid = rs.getString("assetid");
                String companysupplierid = rs.getString("companysupplierid");
                int purchaseqty =Integer.parseInt(rs.getString("purchaseqty"));
                Date datepurchaseconv = new Date();
                try {
                    datepurchaseconv = new SimpleDateFormat("yyyy-MM-dd").parse(datepurchase);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                
                pdetail = new PurchaseDetails(purchaseid,companysupplierid,invoicenum,ponumber,datepurchaseconv,assetid, purchaseqty);
            }
        }
        catch(SQLException ex)
        {
            ex.getMessage();
            System.out.println(ex.getMessage());
        }
        return pdetail;
    }

    public String updateDetails(PurchaseDetails purchasedetail) {
        String companyid = purchasedetail.getCompanysupplierID();
        String ponumber = purchasedetail.getPONumber();
        String invoicenumber = purchasedetail.getInvoiceNumber();
        String assetid = purchasedetail.getAssetID();
        int purchaseqty = purchasedetail.getPurchaseqty();
        String purchaseid = purchasedetail.getPurchaseID();
        Date datepurchase = purchasedetail.getDatePurchase();
        java.sql.Date datesql = new java.sql.Date(datepurchase.getTime());
        
        System.out.println(companyid + " " + ponumber + " "+ invoicenumber + " " + datesql + " " + assetid + " " + datepurchase);
        Connection con=null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        
        String insertsql = "UPDATE purchasedetail SET ponumber = '"+ponumber+"', invoicenumber ='"+invoicenumber+"', datepurchase=to_date('"+datesql+"','yyyy-mm-dd'), assetid='"+assetid+"', companysupplierid='"+companyid+"', purchaseqty='"+purchaseqty+"' where purchaseid = '"+purchaseid+"'";
        System.out.println(insertsql);
        
        try
        {
            con = dbconnection.createConnection();
            pstmt = con.prepareStatement(insertsql);
            pstmt.executeUpdate();
            return "SUCCESS";
        }
        catch(SQLException ex)
        {
            ex.getMessage();
        }
        return "FAILED";
    }
    
    public ArrayList getAllPdetail(String ponumber, String invnumber) {
        ArrayList<PurchaseDetails> listpdetails = new ArrayList();
        try(Connection con = dbconnection.createConnection(); 
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM purchasedetail where ponumber = '"+ponumber+"' and invoicenumber = '"+invnumber+"'"))
        {
            
            String sql = "SELECT * FROM purchasedetail where ponumber = '"+ponumber+"' and invoicenumber = '"+invnumber+"'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                String purchaseid = rs.getString("purchaseid");
                String ponumber1 = rs.getString("ponumber");
                String invoicenum = rs.getString("invoicenumber");
                String datepurchase = rs.getString("datepurchase");
                String assetid = rs.getString("assetid");
                String companysupplierid = rs.getString("companysupplierid");
                int purchaseqty =Integer.parseInt(rs.getString("purchaseqty"));
                Date datepurchaseconv = new Date();
                try {
                    datepurchaseconv = new SimpleDateFormat("yyyy-MM-dd").parse(datepurchase);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                
                PurchaseDetails pdetail = new PurchaseDetails(purchaseid,companysupplierid,invoicenum,ponumber1,datepurchaseconv,assetid, purchaseqty);
                listpdetails.add(pdetail);
            }
        }
        catch(SQLException ex)
        {
            ex.getMessage();
            System.out.println(ex.getMessage());
        }
        return listpdetails;
    }
    
}
