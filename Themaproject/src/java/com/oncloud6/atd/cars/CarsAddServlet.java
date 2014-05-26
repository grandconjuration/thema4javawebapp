/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oncloud6.atd.cars;

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
@WebServlet(name = "CarsAddServlet", urlPatterns = {"/carsaddcar"})
public class CarsAddServlet extends HttpServlet {
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
        
        rd = request.getRequestDispatcher("cars/addcar.jsp");
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
        HttpSession session = request.getSession(true);
        MySQLConnection DBConnection = new MySQLConnection();
        
        try {
            Connection connect = DBConnection.getConnection(); 
            
            String brand = request.getParameter("brand");
            String type = request.getParameter("type");
            Object userID = session.getAttribute("userID");
            String licenseplate = request.getParameter("licenseplate");
            
            PreparedStatement preparedStatement = connect.prepareStatement("INSERT INTO atd.auto (auto_klant_id, auto_merk, auto_type, auto_kenteken) VALUES (?, ?, ?, ?)");
               
                // waardes invullen
                preparedStatement.setObject(1, userID);
                preparedStatement.setString(2, brand);
                preparedStatement.setString(3, type);
                preparedStatement.setString(4, licenseplate);
 
                // query uitvoeren
                preparedStatement.executeUpdate();
                
                // confirmatie dat toevoegen is geslaagd
                request.setAttribute("msg", "U heeft succesvol uw auto \"" + brand + " " + type + "\" toegevoegd"); 
                // connectie met database sluiten
                preparedStatement.close();
                connect.close();
                
                RequestDispatcher rd = null;
                // forward naar zichzelf, pagina waarop je je al bevindt
                rd = request.getRequestDispatcher("cars/addcar.jsp");
                rd.forward(request, response);
                
        }
        catch(Exception ex) {
            Logger.getLogger(CarsAddServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
