/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncloud6.atd.customers;

import com.oncloud6.atd.mysql.MySQLConnection;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.reflect.Array.set;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static sun.misc.MessageUtils.where;

/**
 *
 * @author Simon Whiteley <simonwhiteley@hotmail.com>
 */
@WebServlet(name = "CustomersUpdateServlet", urlPatterns = {"/customersupdate"})
public class CustomersUpdateServlet extends HttpServlet {

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

        if (request.getParameter("cid") != null) {

            // get dbconnection
            MySQLConnection DBConnection = new MySQLConnection();
            // try it out
            try {
                Connection connect = DBConnection.getConnection();

                // prepare query
                PreparedStatement preparedStatement = connect.prepareStatement("SELECT klant_naam, klant_adres, klant_korting, klant_geboortedatum FROM atd.klant WHERE klant_id = ?");

                int CustomerID = Integer.parseInt(request.getParameter("cid"));

                // waarde invullen
                preparedStatement.setInt(1, CustomerID);

                // voer de query uit en get result
                ResultSet result = preparedStatement.executeQuery();

                // result int variabelen zetten
                result.next();
                String klantNaam = result.getString("klant_naam");
                String klantAdres = result.getString("klant_adres");
                String klantKorting = result.getString("klant_korting");
                String klantGeboortedatum = result.getString("klant_geboortedatum");

                // request variabelen setten
                request.setAttribute("klant_naam", klantNaam);
                request.setAttribute("klant_adres", klantAdres);
                request.setAttribute("klant_korting", klantKorting);
                request.setAttribute("klant_geboortedatum", klantGeboortedatum);

                // afsluiten 
                preparedStatement.close();
                connect.close();

                rd = request.getRequestDispatcher("customers/update.jsp");
                rd.forward(request, response);

            } catch (Exception ex) {
                Logger.getLogger(CustomersUpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        MySQLConnection DBConnection = new MySQLConnection();
        try {

            //nieuwe connectie
            Connection connect = DBConnection.getConnection();

            // post variabelen uitzetten
            String customerName = request.getParameter("customername");
            String customerAddress = request.getParameter("customeraddress");
            String customerDiscount = request.getParameter("discount");
            String customerDateofBirth = request.getParameter("dateofbirth");

            // get customer ID from url
            int CustomerID = Integer.parseInt(request.getParameter("cid"));

            //prepare query
            PreparedStatement preparedStatement = connect.prepareStatement("UPDATE atd.klant SET klant_naam = ?, klant_adres = ?, klant_korting = ?, klant_geboortedatum = ? WHERE klant_id = ?");

            // Waarden invullen
            preparedStatement.setString(1, customerName);
            preparedStatement.setString(2, customerAddress);
            preparedStatement.setString(3, customerDiscount);
            preparedStatement.setString(4, customerDateofBirth);
            preparedStatement.setInt(5, CustomerID);

            // Query uitvoeren
            preparedStatement.executeUpdate();

            // Afsluiten 
            preparedStatement.close();
            connect.close();

            RequestDispatcher rd = null;
            HttpSession session = request.getSession(true);

            doGet(request, response);

        } catch (Exception ex) {
            Logger.getLogger(CustomersUpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
