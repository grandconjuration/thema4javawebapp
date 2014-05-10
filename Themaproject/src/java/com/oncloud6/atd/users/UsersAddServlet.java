/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncloud6.atd.users;

import com.oncloud6.atd.mysql.MySQLConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
 * @author Simon Whiteley <simonwhiteley@hotmail.com>
 */
@WebServlet(name = "UsersAddServlet", urlPatterns = {"/usersadd"})
public class UsersAddServlet extends HttpServlet {

    private PreparedStatement preparedStatement;

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
        RequestDispatcher rd = null;
        HttpSession session = request.getSession(true);

        rd = request.getRequestDispatcher("users/add.jsp");
        rd.forward(request, response);
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
        MySQLConnection DBConnection = new MySQLConnection();
        try {
            Connection connect = DBConnection.getConnection();
            preparedStatement = connect.prepareStatement("INSERT INTO atd.gebruiker (gebruiker_username, gebruiker_password) VALUES (?, ?)");

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            preparedStatement.executeUpdate();
            
            request.setAttribute("msg", "De gebruiker \"" + username + "\" is succesvol toegevoegd!");
            
            
            //niet vergeten om alles te sluiten :)
            preparedStatement.close();
            connect.close();

            RequestDispatcher rd = null;
            HttpSession session = request.getSession(true);

            rd = request.getRequestDispatcher("users/add.jsp");
            rd.forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(UsersAddServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
