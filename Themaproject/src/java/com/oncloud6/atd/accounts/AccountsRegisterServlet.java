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
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = null;
        HttpSession session = request.getSession(true);
        rd = request.getRequestDispatcher("accounts/register.jsp");
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
         RequestDispatcher rd = null;
        HttpSession session = request.getSession(true);
        
        MySQLConnection DBConnection = new MySQLConnection();
        
        try {
            Connection connect = DBConnection.getConnection();
             
            String name = request.getParameter("username");
            String pass = request.getParameter("password");
            String chckpass = request.getParameter("chckpassword");
            
            if(!pass.equals(chckpass)) {
                response.sendRedirect("accountsregister");
                return;
            }
            
            PreparedStatement preparedStatement1 = connect.prepareStatement("INSERT INTO atd.gebruiker (gebruiker_username, gebruiker_password) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

            // waardes invullen
            preparedStatement1.setString(1, name);
            preparedStatement1.setString(2, pass);
            

            // query uitvoeren
            preparedStatement1.executeUpdate();

            // generated id ophalen
            ResultSet tableKeys = preparedStatement1.getGeneratedKeys();
            tableKeys.next();
            int userID = tableKeys.getInt(1);
            preparedStatement1.close();
            //nieuwe klant voorbereiding
            PreparedStatement preparedStatement2 = connect.prepareStatement("INSERT INTO atd.klant (klant_gebruiker_id, klant_naam, klant_adres, klant_geboortedatum) VALUES (?, ?, ?, ?)");

            String surname = request.getParameter("surname");
            String address = request.getParameter("address");
            String bday = request.getParameter("birthdate");

            //waardes invullen
            preparedStatement2.setInt(1, userID);
            preparedStatement2.setString(2, surname);
            preparedStatement2.setString(3, address);

            // datum string omzetten naar Date object
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(bday);

            //datum format omzetten om in de database te zetten
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateFormatted = sdf.format(date);

            preparedStatement2.setString(4, dateFormatted);

            // query uitvoeren
            preparedStatement2.executeUpdate();
            
            
            response.sendRedirect("");
           
            preparedStatement2.close();
            connect.close();

            
        } catch (Exception ex) {
            Logger.getLogger(AccountsRegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 }
