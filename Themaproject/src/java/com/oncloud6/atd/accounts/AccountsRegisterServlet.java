/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oncloud6.atd.accounts;

import com.oncloud6.atd.customers.CustomersAddServlet;
import com.oncloud6.atd.mysql.MySQLConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
 * @author Niels
 */
@WebServlet(name = "AccountsRegisterServlet", urlPatterns = {"/accountsregister"})
public class AccountsRegisterServlet extends HttpServlet {
    
    
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
        Connection connect = DBConnection.getConnection();
        try {
             
            String name = request.getParameter("username");
            String pass = request.getParameter("password");
            String chckpass = request.getParameter("chckpassword");
            String email = request.getParameter("email");
            String surname = request.getParameter("surname");
            String address = request.getParameter("address");
            String zip = request.getParameter("zipcode");
            String bday = request.getParameter("birthdate");
            
            PreparedStatement preparedStatement1 = connect.prepareStatement("INSERT INTO atd.gebruiker (gebruiker_username, gebruiker_password) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

            // waardes invullen
            preparedStatement1.setString(1, name);
            preparedStatement1.setString(2, pass);
            preparedStatement1.setString(3, chckpass);
            preparedStatement1.setString(4, email);
            preparedStatement1.setString(5, surname);
            preparedStatement1.setString(6, address);
            preparedStatement1.setString(7, zip);
            preparedStatement1.setString(8, bday);

            // query uitvoeren
            preparedStatement1.executeUpdate();

            // generated id ophalen
            ResultSet tableKeys = preparedStatement1.getGeneratedKeys();
            tableKeys.next();
            int userID = tableKeys.getInt(1);

            //nieuwe klant voorbereiding
            PreparedStatement preparedStatement2 = connect.prepareStatement("INSERT INTO atd.klant (klant_gebruiker_id, klant_naam, klant_adres, klant_korting, klant_geboortedatum) VALUES (?, ?, ?, ?, ?)");

            String naam = request.getParameter("naam");
            String adres = request.getParameter("adres");
            String korting = request.getParameter("korting");
            String geboortedatum = request.getParameter("geboortedatum");

            //waardes invullen
            preparedStatement2.setInt(1, userID);
            preparedStatement2.setString(2, naam);
            preparedStatement2.setString(3, adres);
            preparedStatement2.setString(4, korting);

            // datum string omzetten naar Date object
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(geboortedatum);

            //datum format omzetten om in de database te zetten
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateFormatted = sdf.format(date);

            preparedStatement2.setString(5, dateFormatted);

            // query uitvoeren
            preparedStatement2.executeUpdate();

            request.setAttribute("msg", "De klant \"" + naam + "\" is succesvol toegevoegd!");

            //niet vergeten om alles te sluiten :)
            preparedStatement2.close();
            connect.close();

            rd = request.getRequestDispatcher("accounts/register.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AccountsRegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 }
