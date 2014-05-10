/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oncloud6.atd.rights;

import com.oncloud6.atd.mysql.MySQLConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * @author Jelle
 */
@WebServlet(name = "GroupsAddServlet", urlPatterns = {"/groupsadd"})
public class GroupsAddServlet extends HttpServlet {

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

        rd = request.getRequestDispatcher("groups/add.jsp");
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
            PreparedStatement preparedStatement = connect.prepareStatement("INSERT INTO atd.groepen (groepen_naam) VALUES (?)");

            String naam = request.getParameter("groupname");

            preparedStatement.setString(1, naam);
            preparedStatement.executeUpdate();

            request.setAttribute("msg", "De groep \"" + naam + "\" is succesvol toegevoegd!");

            //niet vergeten om alles te sluiten :)
            preparedStatement.close();
            connect.close();

            RequestDispatcher rd = null;
            HttpSession session = request.getSession(true);

            response.sendRedirect("groups");

        } catch (Exception ex) {
            Logger.getLogger(GroupsAddServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
