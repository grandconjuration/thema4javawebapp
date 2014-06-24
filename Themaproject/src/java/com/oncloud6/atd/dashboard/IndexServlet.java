/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncloud6.atd.dashboard;

import com.oncloud6.atd.rights.RightsControl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Simon Whiteley
 */
@WebServlet(urlPatterns = {"/index.html"})
public class IndexServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        HttpSession session = request.getSession(true);
        RequestDispatcher rd = null;
        boolean login = false;
        if(RightsControl.checkBoolean("dashboard_index", "true", session)) {
            login = true;
        }
        
        request.setAttribute("login", login);
        
        rd = request.getRequestDispatcher("home.jsp");
        rd.forward(request, response);
        /*
         if(session.getAttribute("Username") != null) {
         request.setAttribute("name", session.getAttribute("Username"));
         rd = request.getRequestDispatcher("index.jsp");
         rd.forward(request, response);
         }else {
         //   response.sendRedirect("Inloggen");
         }
         */
    }

}
