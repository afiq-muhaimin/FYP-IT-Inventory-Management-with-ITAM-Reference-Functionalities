/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.AssetLogs;
import bean.Assets;
import bean.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Afiq
 */
public class ManageAssetMovementHandler extends HttpServlet {

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
            out.println("<title>Servlet ManageAssetMovementHandler</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageAssetMovementHandler at " + request.getContextPath() + "</h1>");
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
        
        Assets asset = new Assets();
        asset = asset.getAsset(assetid);
        request.setAttribute("asset",asset);
        
        AssetLogs astlogs = new AssetLogs();
        String mvtoperation ="asset_movement";
        ArrayList listmovement = new ArrayList();
        listmovement = astlogs.getAssetMovement(mvtoperation,assetid);
        
        request.setAttribute("listastmovement",listmovement);
        
        if(source.equals("addmovement"))
        {
            request.getRequestDispatcher("/AddAssetMovement.jsp").forward(request, response);
        }
        else if(source.equals("viewmovement"))
        {
            request.getRequestDispatcher("/AssetMovementDetails.jsp").forward(request, response);
        }
        else if(source.equals("viewdetailsmovement"))
        {
            
            String assetlogid = request.getParameter("assetlogid");
            String assetremarks = request.getParameter("assetremarks");
            String userid = request.getParameter("userid");
            Users user = new Users();
            String name = user.getUserFullName(userid);
            request.setAttribute("userfullname",name);
            request.setAttribute("assetlogid",assetlogid);
            request.setAttribute("assetid",assetid);
            request.setAttribute("assetremarks",assetremarks);
            request.getRequestDispatcher("/ViewAssetMovementDetails.jsp").forward(request, response);
            
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
        
        if(source.equals("insertmovement"))
        {
            String assetid = request.getParameter("assetid");
            String servicedetails = request.getParameter("servicedetails");
            int qtymovement = Integer.parseInt(request.getParameter("qtymovement"));
            String userid = (String)session.getAttribute("userid");
            String remarks = request.getParameter("movementremarks");
            remarks = remarks + "\n" + "Asset movement for service: " + servicedetails;
            String updateoperation = "asset_movement";
            Date dateupdate = new Date();
            String assetlogsid = null;
            String reqid=null;
            AssetLogs astlogs = new AssetLogs(assetlogsid,assetid,userid,dateupdate,qtymovement,remarks,updateoperation,reqid);
            
            String result = astlogs.addAssetMovement(astlogs);
            System.out.println(result);
            
            if(result.equals("SUCCESS"))
            {
                Assets asset = new Assets();
                result = asset.updateAssetQty3(assetid,qtymovement);
                
                if(result.equals("SUCCESS"))
                {
                    request.setAttribute("successmsg", "The new asset movement has been created");
                    request.getRequestDispatcher("/AssetMovement.jsp").forward(request, response);
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
