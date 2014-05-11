/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oncloud6.atd.accounts;

import com.oncloud6.atd.mysql.MySQLConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "AccountsLoginServlet", urlPatterns = {"/accountslogin"})
public class AccountsLoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   

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
        RequestDispatcher rd = null;
        HttpSession session = request.getSession(true);
        
        MySQLConnection DBConnection = new MySQLConnection();
        
        try {
            Connection connect = null;
            try {
                connect = DBConnection.getConnection();
            } catch (Exception ex) {
                Logger.getLogger(AccountsLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
    }

            PreparedStatement preparedStatement = connect.prepareStatement("SELECT gebruiker_id, gebruiker_username, gebruiker_groepen_id FROM gebruiker WHERE gebruiker_username = ? AND gebruiker_password = ?");
            
            String Username = request.getParameter("username");
            String Password = request.getParameter("password");
            
            preparedStatement.setString(1, Username);
            preparedStatement.setString(2, Password);
            
            ResultSet res = preparedStatement.executeQuery();
            
            if(res.next()) {
                int userID = res.getInt("gebruiker_id");
                String userName = res.getString("gebruiker_username");
                int groupID = res.getInt("gebruiker_groepen_id");
                
                session.setAttribute("userID", userID);
                session.setAttribute("userName", userName);
                session.setAttribute("groupID", groupID);
                
                response.sendRedirect("");
            }
            else {
                response.sendRedirect("");
            }
            preparedStatement.close();
            connect.close();
            
        }
        catch (SQLException ex) {
            Logger.getLogger(AccountsLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
