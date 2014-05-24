/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oncloud6.atd.maintenances;

import com.oncloud6.atd.mysql.MySQLConnection;
import com.oncloud6.atd.rights.GroupsEditServlet;
import com.oncloud6.atd.rights.RightsList;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "MaintenancesEditServlet", urlPatterns = {"/maintenancesedit"})
public class MaintenancesEditServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
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
                request.setAttribute("description", resultSet.getString("onderhoud_beschrijving"));
                request.setAttribute("manhour", resultSet.getString("onderhoud_manuur"));
                String status = resultSet.getString("onderhoud_status");
                preparedStatement.close();
                
                ArrayList<DropdownValues> values = new ArrayList<DropdownValues>();
                DropdownValues value;
                
                value = new DropdownValues();
                value.key = "added";
                value.value = "toegevoegd";
                if(status.equals("added")) {
                    value.selected = true;
                }else{
                    value.selected = false;
                }
                values.add(value);
                
                value = new DropdownValues();
                value.key = "planned";
                value.value = "ingeplanned";
                if(status.equals("planned")) {
                    value.selected = true;
                }else{
                    value.selected = false;
                }
                values.add(value);
                
                value = new DropdownValues();
                value.key = "working";
                value.value = "wordt aan gewerkt";
                if(status.equals("working")) {
                    value.selected = true;
                }else{
                    value.selected = false;
                }
                values.add(value);
                
                value = new DropdownValues();
                value.key = "done";
                value.value = "afgerond";
                if(status.equals("done")) {
                    value.selected = true;
                }else{
                    value.selected = false;
                }
                values.add(value);
                
                request.setAttribute("status", values);
                
                /*preparedStatement = connect.prepareStatement("SELECT atd.rechten.rechten_id as id_default, atd.rechten.rechten_key as name, atd.rechten.rechten_type as type, atd.rechten.rechten_value as value_default, atd.rechten_groepen.rechten_id as id_group, atd.rechten_groepen.rechten_groepen_value as value_group FROM atd.rechten LEFT OUTER JOIN atd.rechten_groepen ON atd.rechten.rechten_id = atd.rechten_groepen.rechten_id AND atd.rechten_groepen.groepen_id = ? ORDER BY atd.rechten.rechten_key ASC;");
                preparedStatement.setString(1, id);
                resultSet = preparedStatement.executeQuery();

                ArrayList<RightsList> values = new ArrayList<RightsList>();

                while (resultSet.next()) {
                    RightsList rights = new RightsList();
                    rights.id = resultSet.getString("id_default");
                    rights.naam = resultSet.getString("name");
                    rights.defaultValue = resultSet.getString("value_default");
                    rights.value = resultSet.getString("value_group");

                    values.add(rights);
                } 
                request.setAttribute("rightlist", values);
                preparedStatement.close();
                connect.close();*/
                
                RequestDispatcher rd = null;
                HttpSession session = request.getSession(true);

                rd = request.getRequestDispatcher("maintenances/edit.jsp");
                rd.forward(request, response);
            }

        } catch (Exception ex) {
            Logger.getLogger(MaintenancesEditServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
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
        processRequest(request, response);
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
            PreparedStatement preparedStatement = connect.prepareStatement("UPDATE atd.onderhoud SET onderhoud_beschrijving = ?, onderhoud_manuur = ?, onderhoud_status = ? WHERE onderhoud_id = ?");

            String description = request.getParameter("description");
            String manhour = request.getParameter("manhour");
            String status = request.getParameter("status");
            String id = request.getParameter("id");

            preparedStatement.setString(1, description);
            preparedStatement.setString(2, manhour);
            preparedStatement.setString(3, status);
            preparedStatement.setString(4, id);
            preparedStatement.executeUpdate();

            request.setAttribute("msg", "De groep  is succesvol toegevoegd!");

            //niet vergeten om alles te sluiten :)
            preparedStatement.close();
            connect.close();

            RequestDispatcher rd = null;
            HttpSession session = request.getSession(true);

            processRequest(request, response);

        } catch (Exception ex) {
            Logger.getLogger(MaintenancesEditServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
