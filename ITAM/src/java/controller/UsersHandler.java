/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Users;
import dao.UsersDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class UsersHandler extends HttpServlet {

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
            out.println("<title>Servlet UsersHandler</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsersHandler at " + request.getContextPath() + "</h1>");
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
       
        String source = request.getParameter("source");
        
        if(source.equals("thisuseronly"))
        {
            String userid = request.getParameter("userid");
            System.out.println(userid);

            Users user = new Users();
            UsersDao usersdao = new UsersDao();
            user = usersdao.getUsers(userid);

            request.setAttribute("user", user);
            request.getRequestDispatcher("/Profile.jsp").forward(request, response);
        }
        else if(source.equals("logout"))
        {
            HttpSession session = request.getSession();
        
            session.removeAttribute("userid");
            session.removeAttribute("useremail");
            session.removeAttribute("usercat");
            session.invalidate();
            request.setAttribute("errMessage", "You have logged out successfully");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
            requestDispatcher.forward(request,response);
            System.out.println("Logged out");
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
        
        if(source.equals("login"))
        {
            try
            {
                String userID = null;
                String userName = null;
                String userPassword = request.getParameter("password");
                String userEmail =  request.getParameter("email");
                String userFullName = null;
                String userPhoneNum = null;
                String serviceID = null;
                String usercat= null;
                
                
                Users user = new Users(userID, userName, userPassword, userEmail, userFullName, userPhoneNum,serviceID,usercat);
                UsersDao userdao = new UsersDao();
                
                user = userdao.findAccount(user);
                
                
                userID = user.getUserID();
                userEmail = user.getUserEmail();
                usercat = user.getUserCategories();
                
                String userValidate = userdao.verifyUser(user);
                
                
                if(userValidate.equals("SUCCESS"))
                {
                    if(usercat.equals("CAT1"))
                    {
                        session.setAttribute("userid",userID);
                        session.setAttribute("useremail",userEmail);
                        session.setAttribute("usercat",usercat);
                        request.setAttribute("welcomemsg","Welcome back to the KPJ AssetWise");
                        request.getRequestDispatcher("/DashboardAdmin.jsp").forward(request,response);
                    }
                    else if(usercat.equals("CAT2"))
                    {
                        session.setAttribute("userid",userID);
                        session.setAttribute("useremail",userEmail);
                        session.setAttribute("usercat",usercat);
                        request.setAttribute("welcomemsg","Welcome back to the KPJ AssetWise");
                        request.getRequestDispatcher("/Dashboard.jsp").forward(request,response);
                    }
                    else if(usercat.equals("CAT3"))
                    {
                        session.setAttribute("userid",userID);
                        session.setAttribute("useremail",userEmail);
                        session.setAttribute("usercat",usercat);
                        request.setAttribute("welcomemsg","Welcome back to the KPJ AssetWise");
                        request.getRequestDispatcher("/DashboardTechnician.jsp").forward(request,response);
                    }
                    
                }
                else
                {
                    request.setAttribute("errMessage", userValidate);
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
 
            }
            catch(IOException | ServletException ex)
            {
                out.println(ex.getMessage());
            }
        }
        else if(source.equals("update"))
        {
            String userID = request.getParameter("userid");
            String userName = request.getParameter("username");
            String userPassword = request.getParameter("userpass");
            String userEmail = request.getParameter("useremail");
            String userFullName = request.getParameter("userfullname");
            String userPhoneNum = request.getParameter("userphonenum");
            String serviceID = null;
            String usercat = (String) session.getAttribute("usercat");
            
            System.out.println(userID + " in handler");
            //verify account 
            //test update account
            
            Users user = new Users(userID, userName, userPassword, userEmail, userFullName, userPhoneNum,serviceID,usercat);
            ArrayList<String> listchange = user.checkUpdateDiff(userID,userName, userPassword, userEmail, userFullName, userPhoneNum);
            String updatelist = " ";
            for (String list : listchange) {
                System.out.println(list);
                updatelist = updatelist + list + ", ";
            }
            String result = user.userUpdate(user);
          
            if(result.equals("SUCCESS"))
            {
                //for email that is not existed
                
                 if(usercat.equals("CAT1"))
                    {
                        session.setAttribute("userid",userID);
                        session.setAttribute("useremail",userEmail);
                        session.setAttribute("usercat",usercat);
                        request.setAttribute("successmsg","Your account has been updated. These are the details that has been changed: " + updatelist);
                        request.getRequestDispatcher("/DashboardAdmin.jsp").forward(request,response);
                    }
                    else if(usercat.equals("CAT2"))
                    {
                        session.setAttribute("userid",userID);
                        session.setAttribute("useremail",userEmail);
                        session.setAttribute("usercat",usercat);
                        request.setAttribute("successmsg","Your account has been updated. These are the details that has been changed: " + updatelist);
                        request.getRequestDispatcher("/Dashboard.jsp").forward(request,response);
                    }
                    else if(usercat.equals("CAT3"))
                    {
                        session.setAttribute("userid",userID);
                        session.setAttribute("useremail",userEmail);
                        session.setAttribute("usercat",usercat);
                        request.setAttribute("successmsg","Your account has been updated. These are the details that has been changed: " + updatelist);
                        request.getRequestDispatcher("/DashboardTechnician.jsp").forward(request,response);
                    }
            }
            else
            {
                //for email that has been existed, need to have message at the form telling that the email is already existed
                 if(usercat.equals("CAT1"))
                    {
                        session.setAttribute("userid",userID);
                        session.setAttribute("useremail",userEmail);
                        session.setAttribute("usercat",usercat);
                        request.setAttribute("errormsg","The email given is exists but data isn't saved. Please provide a new email.");
                        request.getRequestDispatcher("/DashboardAdmin.jsp").forward(request,response);
                    }
                    else if(usercat.equals("CAT2"))
                    {
                        session.setAttribute("userid",userID);
                        session.setAttribute("useremail",userEmail);
                        session.setAttribute("usercat",usercat);
                        request.setAttribute("errormsg", "The email given is exists but data isn't saved. Please provide a new email.");
                        request.getRequestDispatcher("/Dashboard.jsp").forward(request,response);
                    }
                    else if(usercat.equals("CAT3"))
                    {
                        session.setAttribute("userid",userID);
                        session.setAttribute("useremail",userEmail);
                        session.setAttribute("usercat",usercat);
                        request.setAttribute("errormsg", "The email given is exists but data isn't saved. Please provide a new email.");
                        request.getRequestDispatcher("/DashboardTechnician.jsp").forward(request,response);
                    }
            }
            
        }
        else if(source.equals("adminupdatepass"))
        {
            //admin update password only
            //update users password when user request
            String userid = request.getParameter("userid");
            String newpassword = request.getParameter("newpassword");
            Users user = new Users();
            String result = user.userUpdatePass(userid, newpassword);
            if(result.equals("SUCCESS"))
            {
                request.setAttribute("successmsg", "The new user password has been updated");
                request.getRequestDispatcher("/EditUsersPassword.jsp").forward(request,response);
            }
            
        }
        else if(source.equals("adminupdatecat"))
        {
            String userid = request.getParameter("userid");
            String catid = request.getParameter("catid");
            
            Users user = new Users();
            String result = user.userUpdateCat(userid,catid);
            if(result.equals("SUCCESS"))
            {
                request.setAttribute("successmsg", "The new user categories has been updated");
                request.getRequestDispatcher("/EditUsersPassword.jsp").forward(request,response);
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
