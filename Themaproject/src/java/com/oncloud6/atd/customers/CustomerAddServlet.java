/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncloud6.atd.customers;

import com.oncloud6.atd.mysql.MySQLConnection;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Simon Whiteley <simonwhiteley@hotmail.com>
 */
@WebServlet(name = "CustomerAddServlet", urlPatterns = {"/customeradd"})
public class CustomerAddServlet extends HttpServlet {

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

        rd = request.getRequestDispatcher("customers/add.jsp");
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
            PreparedStatement preparedStatement = connect.prepareStatement("INSERT INTO atd.klant (klant_naam, klant_adres, klant_korting, klant_geboortedatum) VALUES (?, ?, ?, ?)");

            String naam = request.getParameter("naam");
            String adres = request.getParameter("adres");
            String korting = request.getParameter("korting");
            String geboortedatum = request.getParameter("geboortedatum");

            preparedStatement.setString(1, naam);
            preparedStatement.setString(2, adres);
            preparedStatement.setString(3, korting);

            // datum string omzetten naar Date object
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(geboortedatum);
            
            //datum format omzetten om in de database te zetten
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateFormatted = sdf.format(date);
            
            preparedStatement.setString(4, dateFormatted);
            preparedStatement.executeUpdate();

            request.setAttribute("msg", "De klant \"" + naam + "\" is succesvol toegevoegd!");

            //niet vergeten om alles te sluiten :)
            preparedStatement.close();
            connect.close();

            RequestDispatcher rd = null;
            HttpSession session = request.getSession(true);

            rd = request.getRequestDispatcher("customers/add.jsp");
            rd.forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(CustomerAddServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
