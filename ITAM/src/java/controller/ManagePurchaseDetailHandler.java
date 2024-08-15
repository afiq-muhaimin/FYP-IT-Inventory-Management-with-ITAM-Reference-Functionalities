/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
//test this servlet
import bean.AssetLogs;
import bean.Assets;
import bean.CompanySupplier;
import bean.PurchaseDetails;
import bean.Types;
import dao.AssetDao;
import dao.AssetLogsDao;
import dao.CompanySupplierDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Afiq
 */
public class ManagePurchaseDetailHandler extends HttpServlet {

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
            out.println("<title>Servlet ManagePurchaseDetailHandler</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagePurchaseDetailHandler at " + request.getContextPath() + "</h1>");
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
         //retrieve data about the selected purchase detail
         
         System.out.println("in get");
         String purchaseid = request.getParameter("purchaseid");
         PurchaseDetails pdetail = new PurchaseDetails();
         System.out.println(purchaseid);
         pdetail = pdetail.getPdetail(purchaseid);
         request.setAttribute("pdetail",pdetail);
         System.out.println(pdetail.toString());
         
         String ponumber = request.getParameter("ponumber");
         String invnumber = request.getParameter("invnum");
         //System.out.println("Invoice Number: "+invnumber);
         ArrayList<PurchaseDetails> listpdetails = new ArrayList();
         listpdetails = pdetail.getPurchaseDetails(ponumber, invnumber);
         request.setAttribute("listpdetails", listpdetails);
         
         System.out.println(listpdetails.size());
         for(int i=0;i<listpdetails.size();i++)
         {
            pdetail = listpdetails.get(i);
            System.out.println(pdetail.getAssetID());
         }
         
         CompanySupplier company = new CompanySupplier();
         ArrayList<CompanySupplier> listCompanies = new ArrayList();
         listCompanies = company.getAllCompanySupplier();
         request.setAttribute("listCompanies",listCompanies);
         
         Assets asset = new Assets();
         ArrayList<Assets> listAssets = new ArrayList();
         listAssets = asset.getAllAsset();
         request.setAttribute("listAssets",listAssets);
         
        Types type = new Types();
        ArrayList<Types> listTypes = new ArrayList();
        listTypes = type.getAllTypes();
        request.setAttribute("listTypes",listTypes);
        
        String source = request.getParameter("source");
        if(source.equals("view"))
        {
            request.getRequestDispatcher("/ViewPurchaseDetail.jsp").forward(request, response);
        }
        else if(source.equals("update"))
        {
            request.getRequestDispatcher("/EditPurchaseDetail.jsp").forward(request, response);
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
        
        String source = request.getParameter("source");
        //retrieve new data to update the selected purchase detail
        
        if(source.equals("updatepurchase"))
        {
            String userid = request.getParameter("userid");
            String usercat = request.getParameter("usercat");
            String companyid = request.getParameter("companyid");
            String newcompany = request.getParameter("newcompanyname");
            String datepurchase= request.getParameter("datepurchase");
            Date datepurchaseconv = new Date();
            
            try 
            {
                datepurchaseconv = new SimpleDateFormat("yyyy-MM-dd").parse(datepurchase);
            } 
            catch (Exception e) 
            {
                out.println(e.getMessage());
            }
            
            String invoice = request.getParameter("invoice");
            String ponumber = request.getParameter("ponumber");
            String oldassetid = request.getParameter("oldassetid");
            String purchaseid = request.getParameter("purchaseID");
            String assetid = request.getParameter("assetid");
            String newassetname = request.getParameter("newassetname");
            String assettype = request.getParameter("assettype");
            String assetremarks = request.getParameter("assetremarks");
            String oldassetname = request.getParameter("oldassetname");
            int assetqty = Integer.parseInt(request.getParameter("assetqty"));
            int oldassetqty = Integer.parseInt(request.getParameter("oldassetqty"));
            // System.out.println(userid+ " " + companyid + " " + newcompany + " " +datepurchase);
            
            //System.out.println(invoice + " " + ponumber + " " + oldassetid + " " + purchaseid + " " + assetid + " " + newassetname + " " + assettype + " " + assetremarks + " " + oldassetname + " " + assetqty + " " + oldassetqty );
            
            if(companyid.equals("newcompany"))//changing company
            {
                String newcompanyid = null;
                CompanySupplier company = new CompanySupplier(newcompanyid, newcompany);
                CompanySupplierDao companydao = new CompanySupplierDao();
                companyid = companydao.addCompany(company);
                
                //assetname = assetid
                if(assetid.equals(oldassetid))
                {
                    
                    Assets asset = new Assets();
                    String result1 = asset.updateAssetQty(oldassetid, oldassetqty, assetqty);
                    
                    
                    PurchaseDetails pdetail = new PurchaseDetails(purchaseid, companyid, invoice, ponumber, datepurchaseconv, oldassetid, assetqty);
                    String result2 = pdetail.updatePurchaseDetail(pdetail);

                    String updateOperation = "asset_details_updated";
                    String requestID = null;
                    String assetlogid = null;
                    Date systemdate = new Date();
                    systemdate = null;
                    String updateremarks = "The quantity of the asset has been change from " + oldassetqty + " to " + assetqty + " based on the changes that has been made in the purchase detail ID = " + purchaseid + "";

                    AssetLogs assetlog = new AssetLogs(assetlogid, assetid, userid, systemdate, oldassetqty, updateremarks, updateOperation, requestID);
                    AssetLogsDao assetlogdao = new AssetLogsDao();
                    String resultlogs = assetlogdao.updateLogs(assetlog);
                    
                    if(result1.equals("SUCCESS")&&result2.equals("SUCCESS")&&resultlogs.equals("SUCCESS"))
                    {
                        if (usercat.equals("CAT1")) {
                            
                            request.setAttribute("successmsg", "The purchase detail has been updated");
                            request.getRequestDispatcher("/DashboardAdmin.jsp").forward(request, response);
                        } else if (usercat.equals("CAT2")) {
                            request.setAttribute("successmsg", "The purchase detail has been updated");
                            request.getRequestDispatcher("/Dashboard.jsp").forward(request, response);
                        }
                    }
                    /*
                    if(assetqty > oldassetqty) //asset sama qty lebih -> affect purchase detail
                    {
                        //old asset will be changed the value dekat db
                        //oldassetid only
                        //hantar dekat dao oldid,oldqty,newqty 
                        Assets asset = new Assets(oldassetid, assetid, assetremarks, assetqty, assettype);
                        String result1 = asset.updateAssetQty(oldassetid, assetqty,assetqty);
                        //assetqty meaning newassetqty
                        PurchaseDetails pdetail = new PurchaseDetails(purchaseid,companyid,invoice,ponumber,datepurchaseconv,assetid,assetqty);
                        String result2 = pdetail.updatePurchaseDetail(pdetail);
                        
                        String updateOperation = "asset_details_updated";
                        String requestID = null;
                        String assetlogid = null;
                        Date systemdate = new Date();
                        systemdate = null;
                        String updateremarks = "The quantity of the asset has been change from "+oldassetqty+" to "+assetqty+" based on the changes that has been made in the purchase detail ID = "+purchaseid+"";
                        
                        AssetLogs assetlog = new AssetLogs(assetlogid,assetid,userid,systemdate,oldassetqty,updateremarks,updateOperation,requestID);
                        AssetLogsDao assetlogdao = new AssetLogsDao();
                        String resultlogs = assetlogdao.updateLogs(assetlog);
                        
                    }
                    else if(assetqty < oldassetqty) //asset sama qty kurang 
                    {
                        
                    }
                    else //asset qty sama
                    {
                        //
                    }*/
                    
                }
                else //assetid changes
                {    //changing company, changing asset - create new assetid
                    if(assetid.equals("newassetname"))
                    {
                        System.out.println("3.1");
                        
                        assetid = null;
                        Assets asset = new Assets(assetid, newassetname, assetremarks, assetqty, assettype);
                        AssetDao assetdao = new AssetDao();
                        assetid = assetdao.addNewAsset(asset);
                        System.out.println("3.2");
                        
                        String result1 = asset.updateAssetQty3(oldassetid, oldassetqty);

                        PurchaseDetails pdetail = new PurchaseDetails(purchaseid, companyid, invoice, ponumber, datepurchaseconv, assetid, assetqty);
                        String result2 = pdetail.updatePurchaseDetail(pdetail);

                        String updateOperation = "asset_details_updated";
                        String requestID = null;
                        String assetlogid = null;
                        Date systemdate = new Date();
                        systemdate = null;
                        String updateremarks = "New asset with new quantity change the old asset quantity";

                        AssetLogs assetlog = new AssetLogs(assetlogid, assetid, userid, systemdate, oldassetqty, updateremarks, updateOperation, requestID);
                        AssetLogsDao assetlogdao = new AssetLogsDao();
                        String resultlogs = assetlogdao.updateLogs(assetlog);

                        if (result1.equals("SUCCESS") && result2.equals("SUCCESS") && resultlogs.equals("SUCCESS")) {
                            if (usercat.equals("CAT1")) {
                                request.setAttribute("successmsg", "The purchase detail has been updated");
                                request.getRequestDispatcher("/DashboardAdmin.jsp").forward(request, response);
                            } else if (usercat.equals("CAT2")) {
                                request.setAttribute("successmsg", "The purchase detail has been updated");
                                request.getRequestDispatcher("/Dashboard.jsp").forward(request, response);
                            }
                        }
                    }
                    else
                    {   //changing company, changing asset - use old
                        Assets asset = new Assets();
                        String result1 = asset.updateAssetQty2(assetid, oldassetid, oldassetqty, assetqty);

                        PurchaseDetails pdetail = new PurchaseDetails(purchaseid, companyid, invoice, ponumber, datepurchaseconv, assetid, assetqty);
                        String result2 = pdetail.updatePurchaseDetail(pdetail);

                        String updateOperation = "asset_details_updated";
                        String requestID = null;
                        String assetlogid = null;
                        Date systemdate = new Date();
                        systemdate = null;
                        String updateremarks = "The quantity of the asset has been change from " + oldassetqty + " to " + assetqty + " based on the changes that has been made in the purchase detail ID = " + purchaseid + " for asset ID = " + assetid + "";

                        AssetLogs assetlog = new AssetLogs(assetlogid, assetid, userid, systemdate, oldassetqty, updateremarks, updateOperation, requestID);
                        AssetLogsDao assetlogdao = new AssetLogsDao();
                        String resultlogs = assetlogdao.updateLogs(assetlog);

                        if (result1.equals("SUCCESS") && result2.equals("SUCCESS") && resultlogs.equals("SUCCESS")) {
                            if (usercat.equals("CAT1")) {
                                request.setAttribute("successmsg", "The purchase detail has been updated");
                                request.getRequestDispatcher("/DashboardAdmin.jsp").forward(request, response);
                            } else if (usercat.equals("CAT2")) {
                                request.setAttribute("successmsg", "The purchase detail has been updated");
                                request.getRequestDispatcher("/Dashboard.jsp").forward(request, response);
                            }
                        }
                    }
                    /*
                    if(assetqty > oldassetqty) //asset tukar qty lebih
                    {
                        //if new asset
                        //else old asset
                        //assetlogs pakai id lama
                        Assets asset = new Assets();
                        String result1 = asset.updateAssetQty2(assetid, oldassetid, oldassetqty, assetqty);
                        
                        PurchaseDetails pdetail = new PurchaseDetails(purchaseid, companyid, invoice, ponumber, datepurchaseconv, assetid, assetqty);
                        String result2 = pdetail.updatePurchaseDetail(pdetail);
                        
                        String updateOperation = "asset_details_updated";
                        String requestID = null;
                        String assetlogid = null;
                        Date systemdate = new Date();
                        systemdate = null;
                        String updateremarks = "The quantity of the asset has been change from " + oldassetqty + " to " + assetqty + " based on the changes that has been made in the purchase detail ID = " + purchaseid + " for asset ID = "+assetid+"";
                        
                        AssetLogs assetlog = new AssetLogs(assetlogid, assetid, userid, systemdate, oldassetqty, updateremarks, updateOperation, requestID);
                        AssetLogsDao assetlogdao = new AssetLogsDao();
                        String resultlogs = assetlogdao.updateLogs(assetlog);
                        
                        if (result1.equals("SUCCESS") && result2.equals("SUCCESS") && resultlogs.equals("SUCCESS")) 
                        {
                            request.getRequestDispatcher("/Dashboard.jsp").forward(request, response);
                        }
                            
                    }
                    */
                    /*
                    else if(assetqty < oldassetqty) //asset tukar qty kurang 
                    {
                        
                    }
                    else // asset tukar qty sama
                    {
                        Assets asset = new Assets(oldassetid, assetid, assetremarks, assetqty, assettype);
                    }
                    */
                }
                
                /*if(assetname.equals("newassetname"))//existing asset not add new asset but update the quantity of the asset
                {
                    String newassetname = request.getParameter("newassetname");
                    assetname = newassetname;
                    String assetid = null;
                    Assets asset = new Assets(assetid, assetname, assetremarks, assetqty, assettype);
                    AssetDao assetdao = new AssetDao();
                    assetID = assetdao.addNewAsset(asset);
                    //reduce the asset qty for id yang salah
                    asset.reduceAsset(oldasset,assetqty);
                }
                else
                {
                    //receive 2 id and 2 quantity from jsp 
                    //jsp hantar 2 assetid and 2 quantity 
                    //1 newassetid, 1 oldassetid
                    //1 newquantity, 1 oldquantity
                }
                */

                   
            }
            else
            {   //asset id not changes
                if(assetid.equals(oldassetid))
                {
                    //not changing company, not change asset id
                    //Assets asset = new Assets(oldassetid, oldassetname, assetremarks, assetqty, assettype);
                    Assets asset = new Assets();
                    String result1 = asset.updateAssetQty(oldassetid, oldassetqty, assetqty);
                    
                    //assetqty meaning newassetqty
                    PurchaseDetails pdetail = new PurchaseDetails(purchaseid, companyid, invoice, ponumber, datepurchaseconv, oldassetid, assetqty);
                    String result2 = pdetail.updatePurchaseDetail(pdetail);

                    String updateOperation = "asset_details_updated";
                    String requestID = null;
                    String assetlogid = null;
                    Date systemdate = new Date();
                    systemdate = null;
                    String updateremarks = "Asset quantity change from " + oldassetqty + " to " + assetqty + " for purchase detail ID = " + purchaseid + "";

                    AssetLogs assetlog = new AssetLogs(assetlogid, assetid, userid, systemdate, oldassetqty, updateremarks, updateOperation, requestID);
                    AssetLogsDao assetlogdao = new AssetLogsDao();
                    String resultlogs = assetlogdao.updateLogs(assetlog);
                    
                    if(result1.equals("SUCCESS")&&result2.equals("SUCCESS")&&resultlogs.equals("SUCCESS"))
                    {
                        if (usercat.equals("CAT1")) {
                            request.setAttribute("successmsg", "The purchase detail has been updated");
                            request.getRequestDispatcher("/DashboardAdmin.jsp").forward(request, response);
                        } else if (usercat.equals("CAT2")) {
                            request.setAttribute("successmsg", "The purchase detail has been updated");
                            request.getRequestDispatcher("/Dashboard.jsp").forward(request, response);
                        }
                    }
                }
                else //assetid changes
                {   //not changing company, change asset id- create new asset
                    // check whether assetid -> assetnewname or assetid that is exisiting - create new asset
                    if(assetid.equals("newassetname"))
                    {
                        System.out.println("3.1");
                        
                        assetid = null;
                        Assets asset = new Assets(assetid, newassetname, assetremarks, assetqty, assettype);
                        AssetDao assetdao = new AssetDao();
                        assetid = assetdao.addNewAsset(asset);
                        System.out.println("3.2");
                        
                        String result1 = asset.updateAssetQty3(oldassetid, oldassetqty);

                        PurchaseDetails pdetail = new PurchaseDetails(purchaseid, companyid, invoice, ponumber, datepurchaseconv, assetid, assetqty);
                        String result2 = pdetail.updatePurchaseDetail(pdetail);

                        String updateOperation = "asset_details_updated";
                        String requestID = null;
                        String assetlogid = null;
                        Date systemdate = new Date();
                        systemdate = null;
                        String updateremarks = "New asset with new quantity change the old asset quantity";

                        AssetLogs assetlog = new AssetLogs(assetlogid, assetid, userid, systemdate, oldassetqty, updateremarks, updateOperation, requestID);
                        AssetLogsDao assetlogdao = new AssetLogsDao();
                        String resultlogs = assetlogdao.updateLogs(assetlog);

                        if (result1.equals("SUCCESS") && result2.equals("SUCCESS") && resultlogs.equals("SUCCESS")) {
                            if (usercat.equals("CAT1")) {
                                request.setAttribute("successmsg", "The purchase detail has been updated");
                                request.getRequestDispatcher("/DashboardAdmin.jsp").forward(request, response);
                            } else if (usercat.equals("CAT2")) {
                                request.setAttribute("successmsg", "The purchase detail has been updated");
                                request.getRequestDispatcher("/Dashboard.jsp").forward(request, response);
                            }
                        }
                    }
                    else
                    {
                        //not changing company, change asset id - use old asset
                        Assets asset = new Assets();
                        String result1 = asset.updateAssetQty2(assetid, oldassetid, oldassetqty, assetqty);

                        PurchaseDetails pdetail = new PurchaseDetails(purchaseid, companyid, invoice, ponumber, datepurchaseconv, assetid, assetqty);
                        String result2 = pdetail.updatePurchaseDetail(pdetail);

                        String updateOperation = "asset_details_updated";
                        String requestID = null;
                        String assetlogid = null;
                        Date systemdate = new Date();
                        systemdate = null;
                        String updateremarks = "The quantity of the asset has been change from " + oldassetqty + " to " + assetqty + " based on the changes that has been made in the purchase detail ID = " + purchaseid + " for asset ID = " + assetid + "";

                        AssetLogs assetlog = new AssetLogs(assetlogid, assetid, userid, systemdate, oldassetqty, updateremarks, updateOperation, requestID);
                        AssetLogsDao assetlogdao = new AssetLogsDao();
                        String resultlogs = assetlogdao.updateLogs(assetlog);

                        if (result1.equals("SUCCESS") && result2.equals("SUCCESS") && resultlogs.equals("SUCCESS")) {
                            if (usercat.equals("CAT1")) {
                                request.setAttribute("successmsg", "The purchase detail has been updated");
                                request.getRequestDispatcher("/DashboardAdmin.jsp").forward(request, response);
                            } else if (usercat.equals("CAT2")) {
                                request.setAttribute("successmsg", "The purchase detail has been updated");
                                request.getRequestDispatcher("/Dashboard.jsp").forward(request, response);
                            }
                        }
                    }
                    
                    
                }
            }
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
