/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oncloud6.atd.accounts;

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
 * @author Niels
 */
@WebServlet(name = "AccountsLogoutServlet", urlPatterns = {"/accountslogout"})
public class AccountsLogoutServlet extends HttpServlet {


   

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
        HttpSession session = request.getSession(true);
        RequestDispatcher rd = null;
        if(!RightsControl.checkBoolean("accounts_logout", "true", session)) {
            rd = request.getRequestDispatcher("error/403error.jsp");
            rd.forward(request, response);
            return;
        }
        try {
            session.removeAttribute("userID");
            session.removeAttribute("userName");
            session.removeAttribute("groupID");
            session.invalidate();
            response.sendRedirect("");
        } catch (Exception ex) {
      
        }

    }

}
