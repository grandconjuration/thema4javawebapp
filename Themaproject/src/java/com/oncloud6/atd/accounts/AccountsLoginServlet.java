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
import java.sql.Statement;
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
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get dispatcher
        RequestDispatcher rd = null;
        // get session informatie
        HttpSession session = request.getSession(true);

        MySQLConnection DBConnection = new MySQLConnection();
        Connection connect = DBConnection.getConnection();
        try {
            
            PreparedStatement preparedStatement = connect.prepareStatement("SELECT gebruiker_id, gebruiker_username, gebruiker_groepen_id FROM gebruiker WHERE gebruiker_username = ? AND gebruiker_password = ?");

            String GivenUsername = request.getParameter("username");
            String GivenPassword = request.getParameter("password");

           
            preparedStatement.setString(1, GivenUsername);
            preparedStatement.setString(2, GivenPassword);

            
            ResultSet res = preparedStatement.executeQuery();

            if (res.next()) {
                int userID = res.getInt("gebruiker_id");
                String userName = res.getString("gebruiker_username");
                int groupID = res.getInt("gebruiker_groepen_id");

                session.setAttribute("userID", userID);
                session.setAttribute("userName", userName);
                session.setAttribute("groupID", groupID);

                response.sendRedirect("");
            } // login niet correct, act accordingly
            else {
                response.sendRedirect("");

            }

            preparedStatement.close();
            connect.close();

        } catch (Exception ex) {
            Logger.getLogger(AccountsLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}