/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.AssetLogs;
import bean.Assets;
import bean.RequestedAsset;
import bean.Requester;
import bean.Requests;
import bean.Services;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Afiq
 */
public class ManageRequestHandler extends HttpServlet {

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
            out.println("<title>Servlet ManageRequestHandler</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageRequestHandler at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        String source = request.getParameter("source");
        
        if(source.equals("openrequest"))
        {
            System.out.println("before");
            Services service = new Services();
            ArrayList<Services>allservices = new ArrayList();
            allservices = service.getAllServices();
            System.out.println("Size in handler " +allservices.size());
            for(int i=0;i<allservices.size();i++)
            {
                Services serv = allservices.get(i);
                System.out.println(serv.getServicesID());
                System.out.println(serv.getServicesName());
            }
           
            request.setAttribute("listServices",allservices);
            request.getRequestDispatcher("/request.jsp").forward(request, response);
        }
        else if(source.equals("sendrequest"))
        {
            
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String serviceid = request.getParameter("serviceid");
            String phonenum = request.getParameter("phonenum");
            String datetaken = request.getParameter("datetaken");
            String datereturn = request.getParameter("datereturn");
            
            session.setAttribute("name",name);
            session.setAttribute("email",email);
            session.setAttribute("serviceid",serviceid);
            session.setAttribute("phonenum",phonenum);
            session.setAttribute("datetaken",datetaken);
            session.setAttribute("datereturn",datereturn);
            Requests req = new Requests();
            String valuecheck = req.checkRequest();
            
            if(valuecheck.equals("data_exist"))
            {
                Assets asset = new Assets();
                ArrayList<Assets> allasset = new ArrayList();
                allasset = asset.getAllAsset();
                ArrayList<Boolean>resultasset = new ArrayList();
                ArrayList<Integer>qtyavailable = new ArrayList();
                //System.out.println("1");
                
                for(int i=0;i<allasset.size();i++)
                {
                    //ast18
                    asset = allasset.get(i);
                    String assetid = asset.getAssetID();
                    System.out.println(assetid);
                    
                    RequestedAsset reqast = new RequestedAsset();
                    ArrayList<RequestedAsset> listreqast = new ArrayList();
                    listreqast = reqast.getRequestedAsset(assetid);

                    ArrayList<Date> arrdatetaken = new ArrayList();
                    ArrayList<Date> arrdatereturn = new ArrayList();
                    ArrayList<Integer> reqastqtyarr = new ArrayList();
                    
                    for(int m=0;m<listreqast.size();m++)
                    {
                        reqast = listreqast.get(m);
                        System.out.println("REQID: "+reqast.getRequestID());
                        System.out.println("RQTAST: "+reqast.getRequestedID());
                        System.out.println("ASTID: "+reqast.getAssetID());
                        System.out.println("QTY: "+reqast.getQuantity());
                        String reqid = reqast.getRequestID();
                        Requests rqt = new Requests();
                        Date rqtdatetaken = rqt.getDateTaken(reqid);
                        Date rqtdatereturn = rqt.getDateReturn(reqid);
                        System.out.println(rqtdatetaken);
                        System.out.println(rqtdatereturn);
                        
                        //counterqty = counterqty + reqast.getQuantity();
                        reqastqtyarr.add(reqast.getQuantity());// store quantity by date request
                        arrdatetaken.add(rqtdatetaken);
                        arrdatereturn.add(rqtdatereturn);
                        
                    }
                    //reqastqtyarr[i] = counterqty ;//store quantity by asset
                    //System.out.println("Asset " + assetid);
                    //System.out.println("Quantity " + reqastqtyarr[i]);
                    
                    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
                    Date parsedtaken = new Date();
                    Date parsedreturn = new Date();
                    
                     try
                    {
                        parsedtaken = sdformat.parse(datetaken);
                        parsedreturn = sdformat.parse(datereturn);
                    } catch (ParseException ex) {
                        Logger.getLogger(ManageRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }

                  
                    ArrayList<Date> dateusedtaken = new ArrayList();
                    ArrayList<Date> dateusedreturn = new ArrayList();
                    ArrayList<Integer> qtyused = new ArrayList();
                    
                    for(int m=0;m<arrdatetaken.size();m++)
                    {
                        if(parsedreturn.compareTo(arrdatetaken.get(m)) > 0)
                        {
                            dateusedtaken.add(arrdatetaken.get(m));
                            dateusedreturn.add(arrdatereturn.get(m));
                            qtyused.add(reqastqtyarr.get(m));
                        }

                    }
                    
                    System.out.println("Asset ID: " + assetid);
                    for(int m=0;m<arrdatetaken.size();m++)
                    {
                        System.out.println("Has requested on these date: ");
                        System.out.println(arrdatetaken.get(m));
                        System.out.println(arrdatereturn.get(m));
                                           
                    }
                    
                    for(int m=0;m<reqastqtyarr.size();m++)
                    {
                        System.out.println(reqastqtyarr.get(m));
                    }
                    
                    System.out.println("Inside array of used: ");
                    
                    for (int m = 0; m < dateusedtaken.size(); m++) 
                    {
                        
                        System.out.println(dateusedtaken.get(m));
                        System.out.println(dateusedreturn.get(m));
                        System.out.println(qtyused.get(m));
                    
                    }
                    
                    dateusedtaken.add(parsedtaken);
                    dateusedreturn.add(parsedreturn);
                    
                    //sorting
                    System.out.println("Inside array of used: ");
                    for (int m = 0; m < dateusedtaken.size(); m++) 
                    {
                        
                        System.out.println(dateusedtaken.get(m));
                        System.out.println(dateusedreturn.get(m));
                        //System.out.println(qtyused.get(m));
                    
                    }
                    
                    /*
                    for (int m = 1; m < dateusedtaken.size(); m++) 
                    {
                        if(dateusedtaken.get(m).compareTo(dateusedreturn.get(m-1))>=0)
                        {
                           int astcrtqty = asset.getAssetOriginQty();
                           int reqastqty = reqastqtyarr[m-1];
                           
                           if(astcrtqty-reqastqty != 0)
                           {
                               resultasset[i] = "Available";
                           }
                           else
                           {
                               resultasset[i] = "Unavailable";
                           }
                           
                        }
                    }
                    */
                    System.out.println("Size date used: " + dateusedtaken.size());
                    System.out.println("Size date taken: " +dateusedreturn.size());
                    PriorityQueue<Date> pQueue = new PriorityQueue<>();
                    
                    int availability = 1;
                    int assetqty = asset.getAssetOriginQty();
                    
                    pQueue.add(dateusedreturn.get(0));
                    
                    boolean result = true;
                    int qtyassetused = 0;
                    for (int m = 1; m < dateusedtaken.size(); m++) {
                        if (dateusedtaken.get(m).compareTo(pQueue.peek()) > 0) 
                        {
                            pQueue.poll();
                            //assetqty = assetqty;
                            //qtyassetused = qtyassetused + qtyused.get(m-1);
                            pQueue.add(dateusedreturn.get(m));
                            assetqty = asset.getAssetOriginQty();
                        } 
                        else if(dateusedtaken.get(m).compareTo(pQueue.peek()) == 0)
                        {
                            //qtyassetused = qtyassetused + qtyused.get(m-1);
                            //assetqty = assetqty - qtyassetused;
                            assetqty = assetqty - qtyused.get(m-1);
                            if(assetqty <= 0)
                            {
                               pQueue.add(dateusedreturn.get(m));
                            }
                            else if(assetqty > 0)
                            {
                                pQueue.poll();
                                pQueue.add(dateusedreturn.get(m));
                            }
                        }
                        else if(dateusedtaken.get(m).compareTo(pQueue.peek()) < 0)
                        {
                            //qtyassetused = qtyassetused + qtyused.get(m-1);
                            //assetqty = assetqty - qtyassetused;
                            assetqty = assetqty - qtyused.get(m-1);
                            if(assetqty <= 0)
                            {
                                pQueue.add(dateusedreturn.get(m));
                            }
                            else if(assetqty > 0)
                            {
                                pQueue.poll();
                                pQueue.add(dateusedreturn.get(m));
                            }
                        }

                        if (pQueue.size() > availability) {
                            result = false;
                        }
                        //break;//use for testing only please remove after this
                    }
                    //pQueue.clear();
                    System.out.println("Result boolean: for Asset " +  assetid + " : "+ result);
                    System.out.println("Asset available to request: " + assetqty);
                    resultasset.add(result);
                    qtyavailable.add(assetqty);
                    
                    //break;//use for testing only please remove after this
                }
               request.setAttribute("resultasset",resultasset);
               request.setAttribute("qtyavailable",qtyavailable);
               request.setAttribute("listAssets",allasset);
               ArrayList<String> astreqbyuser = new ArrayList();
               ArrayList<Integer> astqtyreqbyuser = new ArrayList();
               
               session.setAttribute("astreqbyuser",astreqbyuser);
               session.setAttribute("astqtyreqbyuser",astqtyreqbyuser);
               request.getRequestDispatcher("/requestasset.jsp").forward(request, response);
            }
            else
            {
                //terus pergi page request asset page with value
                //System.out.println("2");
            }
            
        }
        else if(source.equals("accumulatereq"))
        {
            String assetidreceive = request.getParameter("assetid");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            
            String datetaken = (String) session.getAttribute("datetaken");
            String datereturn = (String)session.getAttribute("datereturn");
            
            System.out.println("Asset id: "+assetidreceive);
            System.out.println("quantity: "+quantity);
            ArrayList<String> assetrequested = (ArrayList<String>) session.getAttribute("astreqbyuser");
            ArrayList<Integer> qtyrequested = (ArrayList<Integer>) session.getAttribute("astqtyreqbyuser");
            
            assetrequested.add(assetidreceive);
            qtyrequested.add(quantity);
            System.out.println("Qty asset ID requested:" + assetrequested.size());
            System.out.println(qtyrequested.size());
            
            Assets asset = new Assets();
            ArrayList<Assets> allasset = new ArrayList();
            allasset = asset.getAllAsset();
            ArrayList<Boolean> resultasset = new ArrayList();
            ArrayList<Integer> qtyavailable = new ArrayList();
            //System.out.println("1");

            for (int i = 0; i < allasset.size(); i++) {
                //ast18
                asset = allasset.get(i);
                String assetid = asset.getAssetID();
                System.out.println(assetid);

                RequestedAsset reqast = new RequestedAsset();
                ArrayList<RequestedAsset> listreqast = new ArrayList();
                listreqast = reqast.getRequestedAsset(assetid);

                ArrayList<Date> arrdatetaken = new ArrayList();
                ArrayList<Date> arrdatereturn = new ArrayList();
                ArrayList<Integer> reqastqtyarr = new ArrayList();

                for (int m = 0; m < listreqast.size(); m++) {
                    reqast = listreqast.get(m);
                    System.out.println("REQID: " + reqast.getRequestID());
                    System.out.println("RQTAST: " + reqast.getRequestedID());
                    System.out.println("ASTID: " + reqast.getAssetID());
                    System.out.println("QTY: " + reqast.getQuantity());
                    String reqid = reqast.getRequestID();
                    Requests rqt = new Requests();
                    Date rqtdatetaken = rqt.getDateTaken(reqid);
                    Date rqtdatereturn = rqt.getDateReturn(reqid);
                    System.out.println(rqtdatetaken);
                    System.out.println(rqtdatereturn);

                    //counterqty = counterqty + reqast.getQuantity();
                    reqastqtyarr.add(reqast.getQuantity());// store quantity by date request
                    arrdatetaken.add(rqtdatetaken);
                    arrdatereturn.add(rqtdatereturn);

                }
                //reqastqtyarr[i] = counterqty ;//store quantity by asset
                //System.out.println("Asset " + assetid);
                //System.out.println("Quantity " + reqastqtyarr[i]);

                SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
                Date parsedtaken = new Date();
                Date parsedreturn = new Date();

                try {
                    parsedtaken = sdformat.parse(datetaken);
                    parsedreturn = sdformat.parse(datereturn);
                } catch (ParseException ex) {
                    Logger.getLogger(ManageRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }

                ArrayList<Date> dateusedtaken = new ArrayList();
                ArrayList<Date> dateusedreturn = new ArrayList();
                ArrayList<Integer> qtyused = new ArrayList();

                for (int m = 0; m < arrdatetaken.size(); m++) {
                    if (parsedreturn.compareTo(arrdatetaken.get(m)) > 0) {
                        dateusedtaken.add(arrdatetaken.get(m));
                        dateusedreturn.add(arrdatereturn.get(m));
                        qtyused.add(reqastqtyarr.get(m));
                    }

                }
                /*
                System.out.println("Asset ID: " + assetid);
                for (int m = 0; m < arrdatetaken.size(); m++) {
                    System.out.println("Has requested on these date: ");
                    System.out.println(arrdatetaken.get(m));
                    System.out.println(arrdatereturn.get(m));

                }

                for (int m = 0; m < reqastqtyarr.size(); m++) {
                    System.out.println(reqastqtyarr.get(m));
                }

                System.out.println("Inside array of used: ");

                for (int m = 0; m < dateusedtaken.size(); m++) {

                    System.out.println(dateusedtaken.get(m));
                    System.out.println(dateusedreturn.get(m));
                    System.out.println(qtyused.get(m));

                }
                */
                dateusedtaken.add(parsedtaken);
                dateusedreturn.add(parsedreturn);

                //sorting
                /*
                    for (int m = 1; m < dateusedtaken.size(); m++) 
                    {
                        if(dateusedtaken.get(m).compareTo(dateusedreturn.get(m-1))>=0)
                        {
                           int astcrtqty = asset.getAssetOriginQty();
                           int reqastqty = reqastqtyarr[m-1];
                           
                           if(astcrtqty-reqastqty != 0)
                           {
                               resultasset[i] = "Available";
                           }
                           else
                           {
                               resultasset[i] = "Unavailable";
                           }
                           
                        }
                    }
                 */
                PriorityQueue<Date> pQueue = new PriorityQueue<>();

                int availability = 1;
                
                int assetqty = asset.getAssetOriginQty();
                
                if(assetid.equals(assetidreceive))
                {
                    assetqty = assetqty-quantity;
                }

                pQueue.add(dateusedreturn.get(0));

                boolean result = true;
                int qtyassetused = 0;
                for (int m = 1; m < dateusedtaken.size(); m++) {
                    if (dateusedtaken.get(m).compareTo(pQueue.peek()) > 0) {
                        pQueue.poll();
                        //assetqty = assetqty;
                        //qtyassetused = qtyassetused + qtyused.get(m-1);
                        pQueue.add(dateusedreturn.get(m));
                    } else if (dateusedtaken.get(m).compareTo(pQueue.peek()) == 0) {
                        //qtyassetused = qtyassetused + qtyused.get(m - 1);
                        //assetqty = assetqty - qtyassetused;
                        assetqty = assetqty - qtyused.get(m - 1);
                        if (assetqty <= 0) {
                            pQueue.add(dateusedreturn.get(m));
                        } else if (assetqty > 0) {
                            pQueue.poll();
                            pQueue.add(dateusedreturn.get(m));
                        }
                    } else if (dateusedtaken.get(m).compareTo(pQueue.peek()) < 0) {

                        //qtyassetused = qtyassetused + qtyused.get(m - 1);
                        //assetqty = assetqty - qtyassetused;
                        assetqty = assetqty - qtyused.get(m - 1);
                        if (assetqty <= 0) {
                            pQueue.add(dateusedreturn.get(m));
                        } else if (assetqty > 0) {
                            pQueue.poll();
                            pQueue.add(dateusedreturn.get(m));
                        }
                    }

                    if (pQueue.size() > availability) {
                        result = false;
                    }

                }

                System.out.println("Result boolean: for Asset " + assetid + " : " + result);
                System.out.println("Asset available to request: " + assetqty);
                resultasset.add(result);
                qtyavailable.add(assetqty);
            }
            request.setAttribute("resultasset", resultasset);
            request.setAttribute("qtyavailable", qtyavailable);
            request.setAttribute("listAssets", allasset);

            request.getRequestDispatcher("/requestasset.jsp").forward(request, response);
        }
        else if(source.equals("viewrequest"))
        {
            String requestid = request.getParameter("requestid");
            ArrayList listrequest = new ArrayList();
            Requests requests = new Requests();
            listrequest = requests.getAllRequests();
            request.setAttribute("listrequest",listrequest);
            
            ArrayList listrequester = new ArrayList();
            Requester requester = new Requester();
            listrequester = requester.getAllRequester();
            request.setAttribute("listrequester", listrequester);
            
            ArrayList listservice = new ArrayList();
            Services service = new Services();
            listservice = service.getAllServices();
            request.setAttribute("listservice",listservice);
            
            request.setAttribute("requestid",requestid);
            request.getRequestDispatcher("/ViewRequestDetail.jsp").forward(request, response);
        }
        else if(source.equals("checkstatus"))
        {
            String email = request.getParameter("email");
            String phonenum = request.getParameter("phonenum");
            
            Requester requester = new Requester();
            String requesterid = requester.findRequester(email, phonenum);
            
            if(requesterid.equals("norequester"))
            {
                request.setAttribute("errmsg", "There is no request associated with the given requester detail");
                request.getRequestDispatcher("/request.jsp").forward(request, response);
            }
            else
            {
                ArrayList listrequest = new ArrayList();
                Requests req = new Requests();

                listrequest = req.findRequest(requesterid);

                ArrayList listasset = new ArrayList();
                Assets asset = new Assets();
                listasset = asset.getAllAsset();

                ArrayList listreqast = new ArrayList();
                RequestedAsset reqast = new RequestedAsset();
                listreqast = reqast.getAllRequested();

                request.setAttribute("listreqast", listreqast);
                request.setAttribute("listasset", listasset);
                request.setAttribute("listrequest", listrequest);
                
                request.getRequestDispatcher("/request.jsp").forward(request, response);
            }
           
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
        //processRequest(request, response);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String source = request.getParameter("source");
        
        if(source.equals("request"))
        {
            ArrayList<String>astrequested = (ArrayList<String>) session.getAttribute("astreqbyuser");
            ArrayList<Integer> qtyrequested = (ArrayList<Integer>) session.getAttribute("astqtyreqbyuser");
            
            System.out.println(astrequested.size());
            System.out.println(qtyrequested.size());
            
            System.out.println(session.getAttribute("name"));
            System.out.println(session.getAttribute("email"));
            System.out.println(session.getAttribute("serviceid"));
            System.out.println(session.getAttribute("phonenum"));
            System.out.println(session.getAttribute("datetaken"));
            System.out.println(session.getAttribute("datereturn"));
            
            
            String name = (String) session.getAttribute("name");
            String email = (String) session.getAttribute("email");
            String serviceid = (String) session.getAttribute("serviceid");
            String phonenum = (String) session.getAttribute("phonenum");
            String datetaken = (String)session.getAttribute("datetaken");
            String datereturn = (String)session.getAttribute("datereturn");
            //Requests req = new Requests(requestID,userID,);
            String requestID = null;
            String userID = "USER1";
            String requesterID = null;
            Requester requester = new Requester();
            String result = requester.findRequester(email, phonenum);
            
            if(result.equals("norequester"))
            {   ///System.out.println("Requester not exist");
                
                requester = new Requester(requesterID,phonenum,email,name,serviceid);
                requesterID = requester.addRequester(requester);
                
                System.out.println("Requester ID: " + requesterID);
            
                SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
                Date parsedtaken = new Date();
                Date parsedreturn = new Date();
                Date approvedate = new Date();
                Date requestdate = new Date();

                String requeststatus = "pending";

                try {
                    parsedtaken = sdformat.parse(datetaken);
                    parsedreturn = sdformat.parse(datereturn);
                } catch (ParseException ex) {
                    Logger.getLogger(ManageRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }

                Requests req = new Requests(requestID, userID, parsedtaken, parsedreturn, approvedate, requestdate, requeststatus, requesterID);
                requestID = req.addRequest(req);
                System.out.println("Request ID: " + requestID);

                result = "";
                for (int i = 0; i < astrequested.size(); i++) {
                    String requestedID = null;
                    String assetid = astrequested.get(i);
                    int quantity = qtyrequested.get(i);

                    System.out.println("Asset ID: " + assetid);
                    System.out.println("Quantity: " + quantity);
                    RequestedAsset reqast = new RequestedAsset(requestedID, requestID, assetid, quantity);
                    result = reqast.addRequestedAsset(reqast);
                }

                if (result.equals("SUCCESS")) {
                    session.removeAttribute("astreqbyuser");
                    session.removeAttribute("astqtyreqbyuser");
                    request.setAttribute("successmsg", "You request have been successfully accepted");
                    request.getRequestDispatcher("/request.jsp").forward(request, response);
                }


            }
            else
            {
                //System.out.println("Requester Exist");
                requesterID = result;
                System.out.println("Requester ID: " + requesterID);
            
                SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
                Date parsedtaken = new Date();
                Date parsedreturn = new Date();
                Date approvedate = new Date();
                Date requestdate = new Date();

                String requeststatus = "pending";

                try {
                    parsedtaken = sdformat.parse(datetaken);
                    parsedreturn = sdformat.parse(datereturn);
                } catch (ParseException ex) {
                    Logger.getLogger(ManageRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                }

                Requests req = new Requests(requestID, userID, parsedtaken, parsedreturn, approvedate, requestdate, requeststatus, requesterID);
                requestID = req.addRequest(req);
                System.out.println("Request ID: " + requestID);

                result = "";
                for (int i = 0; i < astrequested.size(); i++) {
                    String requestedID = null;
                    String assetid = astrequested.get(i);
                    int quantity = qtyrequested.get(i);

                    System.out.println("Asset ID: " + assetid);
                    System.out.println("Quantity: " + quantity);
                    RequestedAsset reqast = new RequestedAsset(requestedID, requestID, assetid, quantity);
                    result = reqast.addRequestedAsset(reqast);
                }

                if (result.equals("SUCCESS")) {
                    request.setAttribute("successmsg", "You request have been successfully accepted");
                    request.getRequestDispatcher("/request.jsp").forward(request, response);
                }
                
            }
       
            
            //test request module ni
        }
        else if(source.equals("updateapprove"))
        {
            String requestid = request.getParameter("requestid");
            System.out.println("requestid " + requestid);
            
            Requests requests = new Requests();
            String newstatus = "approve";
            
            String result = requests.updateRequestStatus(newstatus,requestid);
            System.out.println(result);
            
            if (result.equals("SUCCESS")) {
                request.setAttribute("successmsg", "The request have been successfully updated");
                request.getRequestDispatcher("/RequestedAsset.jsp").forward(request, response);
            }
            
        }
        else if(source.equals("updateassetout"))
        {
            String requestid = request.getParameter("requestid");
            System.out.println("requestid " + requestid);
            
            Requests requests = new Requests();
            String newstatus = "on_use";
            
            String result = requests.updateRequestStatus(newstatus,requestid);
                        
            RequestedAsset reqast = new RequestedAsset();
            ArrayList <RequestedAsset> listreqast = new ArrayList();
            listreqast = reqast.findRequestedAsset(requestid);
            
            result = "";
            for(int i=0; i<listreqast.size();i++)
            {
                reqast = listreqast.get(i);
                String assetid = reqast.getAssetID();
                int quantity = reqast.getQuantity();
                String userid = (String) session.getAttribute("userid");
                Date currentdate = new Date();
                String astlogID = null;
                String remarks = "Asset out for used for request ID: " + requestid;
                String updateOperation = "asset_out";
                AssetLogs astlogs = new AssetLogs(astlogID,assetid,userid,currentdate,quantity,remarks,updateOperation,requestid);
                result = astlogs.addAssetLogs(astlogs);
            }
            
            if(result.equals("SUCCESS"))
            {
                request.setAttribute("successmsg", "The request have been successfully updated");
                request.getRequestDispatcher("/RequestedAsset.jsp").forward(request, response);
            }
        }
        else if(source.equals("updatereturn"))
        {
            String requestid = request.getParameter("requestid");
            Requests requests = new Requests();
            String newstatus = "return";
            
            String result = requests.updateRequestStatus(newstatus,requestid);
            
            RequestedAsset reqast = new RequestedAsset();
            ArrayList <RequestedAsset> listreqast = new ArrayList();
            listreqast = reqast.findRequestedAsset(requestid);
            
            result = "";
            for(int i=0; i<listreqast.size();i++)
            {
                reqast = listreqast.get(i);
                String assetid = reqast.getAssetID();
                int quantity = reqast.getQuantity();
                String userid = (String) session.getAttribute("userid");
                Date currentdate = new Date();
                String astlogID = null;
                String remarks = "Asset returned after used for request ID: " + requestid;
                String updateOperation = "asset_return";
                AssetLogs astlogs = new AssetLogs(astlogID,assetid,userid,currentdate,quantity,remarks,updateOperation,requestid);
                result = astlogs.addAssetLogs(astlogs);
            }
            
            if(result.equals("SUCCESS"))
            {
                request.setAttribute("successmsg", "The request have been successfully updated");
                request.getRequestDispatcher("/RequestedAsset.jsp").forward(request, response);
            }
             
        }
        else if(source.equals("updatedecline"))
        {
            String requestid = request.getParameter("requestid");
            System.out.println("requestid " + requestid);
            
            Requests requests = new Requests();
            String newstatus = "decline";
            
            String result = requests.updateRequestStatus(newstatus,requestid);
            System.out.println(result);
            
            if(result.equals("SUCCESS"))
            {
                request.setAttribute("successmsg", "The request have been successfully updated");
                request.getRequestDispatcher("/RequestedAsset.jsp").forward(request, response);
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
