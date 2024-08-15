/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.AssetLogs;
import bean.Assets;
import bean.CompanySupplier;
import bean.PurchaseDetails;
import bean.Types;
import bean.Users;
import dao.AssetDao;
import dao.AssetLogsDao;
import dao.CompanySupplierDao;
import dao.PurchaseDetailDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.opencsv.CSVWriter; 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.nio.file.Paths;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;\

//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




/**
 *
 * @author Afiq
 */
@MultipartConfig
public class ManageAssetHandler extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManageAssetHandler</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageAssetHandler at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String source = request.getParameter("source");
        String assetid = request.getParameter("assetid");
        
        System.out.println(assetid);
        
        Assets asset = new Assets();
        asset = asset.getAsset(assetid);
        request.setAttribute("asset",asset);
        
        
        ArrayList <Assets> listAssets = new ArrayList();
        listAssets = asset.getAllAsset();
        request.setAttribute("listAssets",listAssets);
        
        Types type = new Types();
        ArrayList<Types> listTypes = new ArrayList();
        listTypes = type.getAllTypes();
        
        for(int i=0; i<listTypes.size();i++)
        {
            type = listTypes.get(i);
            System.out.println(type.getTypesID());
            System.out.println(type.getTypesDetails());
        }
        
        request.setAttribute("listTypes",listTypes);
        
        ArrayList listpurchase = new ArrayList();
        PurchaseDetails pdetails = new PurchaseDetails();
        listpurchase = pdetails.getAll();
        
        request.setAttribute("listPurchase",listpurchase);
        
        if(source.equals("view"))
        {
            request.getRequestDispatcher("/ViewAssetDetail.jsp").forward(request, response);
        }
        else if(source.equals("update"))
        {
            request.getRequestDispatcher("/EditAssetDetail.jsp").forward(request, response);
        }
        else if(source.equals("viewlogs"))
        {
            String assetlogid = request.getParameter("assetlogid");
            AssetLogs astlogs = new AssetLogs(); 
            astlogs = astlogs.findAssetLogs(assetlogid);
            request.setAttribute("astlogs",astlogs);
            
            Users user = new Users();
            String usrfullname=user.getUserFullName(astlogs.getUserID());
            request.setAttribute("usrfullname", usrfullname);
            request.getRequestDispatcher("/ViewAssetLogsDetails.jsp").forward(request, response);
        }
        else if(source.equals("editassetimage"))
        {
            request.getRequestDispatcher("/EditAssetImage.jsp").forward(request, response);
        }
       
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String source = request.getParameter("source");
        System.out.println("Source value: " + source);
        if(source.equals("addasset"))
        { System.out.println("1");
            try
            {
                String companyid = request.getParameter("companyid");//companyid
                String newcompany = request.getParameter("newcompanyname");
                
                String invoice = request.getParameter("invoice");
                String ponumber = request.getParameter("ponumber");
                String assetname = request.getParameter("assetname");
                String assettype = request.getParameter("assettype");
                String assetremarks = request.getParameter("assetremarks");
                String userid = request.getParameter("userid");
                int assetqty = Integer.parseInt(request.getParameter("assetqty"));
                System.out.println(companyid);
                String usercat = request.getParameter("usercat");
                
                String datepurchase= request.getParameter("datepurchase");
                Date datepurchaseconv = new Date();
                try {
                    datepurchaseconv = new SimpleDateFormat("yyyy-MM-dd").parse(datepurchase);
                } catch (Exception e) {
                    out.println(e.getMessage());
                }
                
                if(companyid.equals("newcompany"))
                {
                    System.out.println("2");
                    
                    String newcompanyid = null;
                    CompanySupplier company = new CompanySupplier(newcompanyid,newcompany);
                    CompanySupplierDao companydao = new CompanySupplierDao();
                    companyid = companydao.addCompany(company);
                    
                    System.out.println("3");
                    String assetID;
                    if(assetname.equals("newassetname"))
                    {
                        System.out.println("3.1");
                        String newassetname = request.getParameter("newassetname");
                        assetname = newassetname;
                        String assetid = null;
                        Assets asset = new Assets(assetid, assetname, assetremarks, assetqty, assettype);
                        AssetDao assetdao = new AssetDao();
                        assetID = assetdao.addNewAsset(asset);
                        System.out.println("3.2");
                    }
                    else
                    {
                        System.out.println("3.3");
                        String assetid = assetname;
                        assetname = null;
                        Assets asset = new Assets(assetid, assetname, assetremarks, assetqty, assettype);
                        AssetDao assetdao = new AssetDao();
                        assetID = assetdao.addCurrentAsset(asset);
                        System.out.println("3.4");
                    }

                    System.out.println("4");
                    
                    String assetlogid = null;

                    //SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
                    Date systemdate = new Date();
                    systemdate = null;
                    /*
                String systemupdate = sdformat.format(systemdate);
                Date dateupdate = new Date();
                try {
                    dateupdate = new SimpleDateFormat("yyyy-MM-dd").parse(systemupdate);
                } catch (ParseException ex) {
                    Logger.getLogger(ManageAssetHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                     */
                    //current date need to find so that boleh insert dekat dalam asset logs db
                    
                    String updateOperation = "asset_first_in";
                    String requestID = null;
                    
                    AssetLogs assetlog = new AssetLogs(assetlogid, assetID, userid, systemdate, assetqty, assetremarks, updateOperation, requestID);
                    AssetLogsDao assetlogdao = new AssetLogsDao();
                    String resultlogs = assetlogdao.updateLogs(assetlog);
                    
                    System.out.println("5");
                    
                    String purchaseID = null;
                    PurchaseDetails pdetail = new PurchaseDetails(purchaseID, companyid, invoice, ponumber, datepurchaseconv, assetID, assetqty);
                    PurchaseDetailDao pdetaildao = new PurchaseDetailDao();
                    String resultdetails = pdetaildao.insertPurchaseDetails(pdetail);
                    
                    System.out.println("6");
                    //continue at dao
                    //operation = asset inserted
                    if (assetID != null) {
                        if (resultlogs.equals("SUCCESS")) {
                            if (resultdetails.equals("SUCCESS")) {
                                if(usercat.equals("CAT1"))
                                {
                                    request.setAttribute("successmsg","The new asset detail and the purchase detail has been inserted");
                                    request.getRequestDispatcher("/DashboardAdmin.jsp").forward(request, response);
                                }
                                else if(usercat.equals("CAT2"))
                                {
                                    request.setAttribute("successmsg","The new asset detail and the purchase detail has been inserted");
                                    request.getRequestDispatcher("/Dashboard.jsp").forward(request, response);
                                }
                            }
                        }
                    }
                }
                else
                {
                    String assetID;
                    if(assetname.equals("newassetname"))
                    {
                        System.out.println("3.1");
                        String newassetname = request.getParameter("newassetname");
                        assetname = newassetname;
                        String assetid = null;
                        Assets asset = new Assets(assetid, assetname, assetremarks, assetqty, assettype);
                        AssetDao assetdao = new AssetDao();
                        assetID = assetdao.addNewAsset(asset);
                        System.out.println("3.2");
                    }
                    else
                    {
                        System.out.println("3.3");
                        String assetid = assetname;
                        assetname = null;
                        Assets asset = new Assets(assetid, assetname, assetremarks, assetqty, assettype);
                        AssetDao assetdao = new AssetDao();
                        assetID = assetdao.addCurrentAsset(asset);
                        System.out.println("3.4");
                        
                    }

                    System.out.println("4");
                    
                    String assetlogid = null;

                    //SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
                    Date systemdate = new Date();
                    systemdate = null;
                    /*
                String systemupdate = sdformat.format(systemdate);
                Date dateupdate = new Date();
                try {
                    dateupdate = new SimpleDateFormat("yyyy-MM-dd").parse(systemupdate);
                } catch (ParseException ex) {
                    Logger.getLogger(ManageAssetHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                     */
                    //current date need to find so that boleh insert dekat dalam asset logs db
                    
                    String updateOperation = "asset_first_in";
                    String requestID = null;
                    
                    AssetLogs assetlog = new AssetLogs(assetlogid, assetID, userid, systemdate, assetqty, assetremarks, updateOperation, requestID);
                    AssetLogsDao assetlogdao = new AssetLogsDao();
                    String resultlogs = assetlogdao.updateLogs(assetlog);
                    
                    System.out.println("5");
                    
                    String purchaseID = null;
                    PurchaseDetails pdetail = new PurchaseDetails(purchaseID, companyid, invoice, ponumber, datepurchaseconv, assetID,assetqty);
                    PurchaseDetailDao pdetaildao = new PurchaseDetailDao();
                    String resultdetails = pdetaildao.insertPurchaseDetails(pdetail);
                    
                    System.out.println("6");
                    //continue at dao
                    //operation = asset inserted
                    if (assetID != null) {
                        if (resultlogs.equals("SUCCESS")) 
                        {
                            if (resultdetails.equals("SUCCESS")) 
                            {
                                if(usercat.equals("CAT1"))
                                {
                                    request.setAttribute("successmsg","The new asset detail and the purchase detail has been inserted");
                                    request.getRequestDispatcher("/DashboardAdmin.jsp").forward(request, response);
                                }
                                else if(usercat.equals("CAT2"))
                                {
                                    request.setAttribute("successmsg","The new asset detail and the purchase detail has been inserted");
                                    request.getRequestDispatcher("/Dashboard.jsp").forward(request, response);
                                }
                                
                            }
                        }
                    }
                }
                
                

            }
            catch(IOException | ServletException ex)
            {
                out.println(ex.getMessage());
            }
        }
        else if(source.equals("update"))
        {
        
            String assetID = request.getParameter("assetid");
            String assetname = request.getParameter("assetname");
            String oldassetname = request.getParameter("oldassetname");
            String assettype = request.getParameter("assettype");
            String oldassettype = request.getParameter("oldassettype");
            String assetremarks = request.getParameter("assetremarks");
            String oldassetremarks = request.getParameter("oldassetremarks");
            String userid = request.getParameter("userid");
            String usercat = request.getParameter("usercat");
            int assetqty = Integer.parseInt(request.getParameter("assetqty"));
            
            Assets asset = new Assets();
            ArrayList <Assets>listasset = asset.getAllAsset();
            
            boolean resultname = false;
            int counter =0;
           
            
            System.out.println(counter);
            String updateOperation = "asset_detail_updated";
            if (!assetname.equals(oldassetname)) 
            {
                System.out.println("1");
                for (int i = 0; i < listasset.size(); i++) 
                {
                    asset = listasset.get(i);
                    String nameretrieved = asset.getAssetName();

                    String[] wordsretrieved = nameretrieved.split("\\s+");

                    String[] wordsassetname = assetname.split("\\s+");

                    for (String wordretrieved : wordsretrieved) {
                        for (String wordassetname : wordsassetname) {
                            if (wordretrieved.equalsIgnoreCase(wordassetname)) {
                                counter++;
                            }
                        }
                    }
                }
                
                if(counter<=3)
                {
                    String result = " ";
                    String resultlogs = " ";

                    asset = new Assets(assetID, assetname, assetremarks, assetqty, assettype);
                    result = asset.updateAssetDetail(asset);

                    String assetlogid = null;
                    Date systemdate = new Date();
                    systemdate = null;

                    String requestID = null;
                    AssetLogs assetlog = new AssetLogs(assetlogid, assetID, userid, systemdate, assetqty, assetremarks, updateOperation, requestID);
                    AssetLogsDao assetlogdao = new AssetLogsDao();
                    resultlogs = assetlogdao.updateLogs(assetlog);

                    if (result.equals("SUCCESS")) {
                        if (resultlogs.equals("SUCCESS")) {
                            if (usercat.equals("CAT1")) {
                                request.setAttribute("successmsg", "The asset detail has been updated");
                                request.getRequestDispatcher("/DashboardAdmin.jsp").forward(request, response);
                            } else if (usercat.equals("CAT2")) {
                                request.setAttribute("successmsg", "The asset detail has been updated");
                                request.getRequestDispatcher("/Dashboard.jsp").forward(request, response);
                            }

                        }
                    }
                }
                else
                {
                    asset = asset.getAsset(assetID);
                    request.setAttribute("asset",asset);

                    Types type = new Types();
                    ArrayList<Types> listTypes = new ArrayList();
                    listTypes = type.getAllTypes();
                    request.setAttribute("listTypes",listTypes);

                    request.setAttribute("notsuccessmsg", "The asset name given is same with current asset name in database");
                    request.getRequestDispatcher("/EditAssetDetail.jsp").forward(request, response);
                    //name is same with current asset in database
                }
               
            } 
            else if(assetname.equals(oldassetname)&&assettype.equals(oldassettype)&&assetremarks.equals(oldassetremarks))//everything not change
            {
                System.out.println("2");
                System.out.println(assetID);
                asset = asset.getAsset(assetID);
                request.setAttribute("asset",asset);
                
                Types type = new Types();
                ArrayList<Types> listTypes = new ArrayList();
                listTypes = type.getAllTypes();
                request.setAttribute("listTypes",listTypes);
                
                request.setAttribute("notsuccessmsg", "There is no changes comitted");
                request.getRequestDispatcher("/EditAssetDetail.jsp").forward(request, response);
            }
            else//only change remarks and type 
            {
                System.out.println("3");
                
                String result = " ";
                    String resultlogs = " ";

                    asset = new Assets(assetID, assetname, assetremarks, assetqty, assettype);
                    result = asset.updateAssetDetail(asset);

                    String assetlogid = null;
                    Date systemdate = new Date();
                    systemdate = null;

                    String requestID = null;
                    AssetLogs assetlog = new AssetLogs(assetlogid, assetID, userid, systemdate, assetqty, assetremarks, updateOperation, requestID);
                    AssetLogsDao assetlogdao = new AssetLogsDao();
                    resultlogs = assetlogdao.updateLogs(assetlog);

                    if (result.equals("SUCCESS")) {
                        if (resultlogs.equals("SUCCESS")) {
                            if (usercat.equals("CAT1")) {
                                request.setAttribute("successmsg", "The asset detail has been updated");
                                request.getRequestDispatcher("/DashboardAdmin.jsp").forward(request, response);
                            } else if (usercat.equals("CAT2")) {
                                request.setAttribute("successmsg", "The asset detail has been updated");
                                request.getRequestDispatcher("/Dashboard.jsp").forward(request, response);
                            }

                        }
                    }
            }
            /*
            else if(!assetname.equals(oldassetname)&assettype.equals(oldassettype)&&oldassetremarks.equals(oldassetremarks))//only change name
            {
               
            }
            else if (!assettype.equals(oldassettype)) 
            {
                
            } 
            
            
            
           
            /*
            only change the asset name with new not with the current asset name 
                - asset id akan lain tapi data sama 
                - so redundacy of asset 
            nak tukar nama asset to another name kena tukar dekat purchase detail
            
            new asset name -> change current name ke new name (replace)
            - receive value selected name 
            - check whether value = newassetname 
                - if value newassetname
                - retrive new name 
                - update dekat db 
          
            retrieve name from form
            compare name received dengan old
            */ //if new name tak sama dengan old name 
            
                
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
