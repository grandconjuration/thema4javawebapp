/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oncloud6.atd.maintenances;

import com.oncloud6.atd.mysql.MySQLConnection;
import com.oncloud6.atd.rights.GroupsEditServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
@WebServlet(name = "MaintenancesAddPartServlet", urlPatterns = {"/maintenancesaddpart"})
public class MaintenancesAddPartServlet extends HttpServlet {
    /**
     * Handles the HTTP <code>GET</code> method
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MySQLConnection DBConnection = new MySQLConnection();
        try {
            Connection connect = DBConnection.getConnection();
            PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM atd.onderhoud WHERE onderhoud_id = ?");
                    
            String id = request.getParameter("id");

            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (!resultSet.next()) {
                preparedStatement.close();
                connect.close();
                response.sendRedirect("maintenances");
            }else{
                request.setAttribute("id", id);
                preparedStatement.close();
                
                preparedStatement = connect.prepareStatement("select * from onderdeel order by onderdeel_naam asc");
                resultSet = preparedStatement.executeQuery();

                ArrayList<DropdownValues> values = new ArrayList<DropdownValues>();
                DropdownValues value;
                
                while (resultSet.next()) {
                    value = new DropdownValues();
                    value.key = resultSet.getString("onderdeel_id");
                    value.value = resultSet.getString("onderdeel_naam");
                    value.selected = false;
                    values.add(value);
                } 
                request.setAttribute("partlist", values);
                preparedStatement.close();
                connect.close();
                
                RequestDispatcher rd = null;
                HttpSession session = request.getSession(true);

                rd = request.getRequestDispatcher("maintenances/addpart.jsp");
                rd.forward(request, response);
            }

        } catch (Exception ex) {
            Logger.getLogger(MaintenancesEditServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Connection connect = DBConnection.getConnection();
            PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM atd.onderhoud WHERE onderhoud_id = ?");
            
            String id = request.getParameter("id");
            String part = request.getParameter("part");
            String amount = request.getParameter("amount");

            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (!resultSet.next()) {
                preparedStatement.close();
                connect.close();
                response.sendRedirect("maintenancesedit?id=" + id);
            }else{
                preparedStatement.close();
                   
                preparedStatement = connect.prepareStatement("INSERT INTO atd.onderhoud_onderdeel (onderhoud_id, onderdeel_id, onderhoud_onderdeel_hoeveelheid) VALUES (?,?,?)");
                
                String rightValue = request.getParameter("RightValue");

                preparedStatement.setString(1, id);
                preparedStatement.setString(2, part);
                preparedStatement.setString(3, amount);
                preparedStatement.executeUpdate();
                
                response.sendRedirect("maintenancesedit?id=" + id);
                connect.close();
            }

        } catch (Exception ex) {
            Logger.getLogger(GroupsEditServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
