/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oncloud6.atd.rights;

import com.oncloud6.atd.mysql.MySQLConnection;
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
@WebServlet(name = "GroupsRightsEditServlet", urlPatterns = {"/groupsrightsedit"})
public class GroupsRightsEditServlet extends HttpServlet {
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
        MySQLConnection DBConnection = new MySQLConnection();
        try {
            Connection connect = DBConnection.getConnection();
            PreparedStatement preparedStatement = connect.prepareStatement("SELECT atd.rechten.rechten_id as id_default, atd.rechten.rechten_key as recht_naam, atd.rechten.rechten_type as recht_type, atd.rechten.rechten_value as value_default, atd.rechten_groepen.rechten_id as id_group, atd.rechten_groepen.rechten_groepen_value as value_group FROM atd.rechten LEFT OUTER JOIN atd.rechten_groepen ON atd.rechten.rechten_id = atd.rechten_groepen.rechten_id AND atd.rechten_groepen.groepen_id = ? WHERE atd.rechten.rechten_id = ? ORDER BY atd.rechten.rechten_key ASC;");
                    
            String id = request.getParameter("id");
            String gid = request.getParameter("gid");

            preparedStatement.setString(1, gid);
            preparedStatement.setString(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (!resultSet.next()) {
                preparedStatement.close();
                connect.close();
                response.sendRedirect("groupsedit?id=" + gid);
            }else{
                RightsList rights = new RightsList();
                rights.id = resultSet.getString("id_default");
                rights.naam = resultSet.getString("recht_naam");
                rights.type = resultSet.getString("recht_type");
                rights.defaultValue = resultSet.getString("value_default");
                rights.value = resultSet.getString("value_group");
                preparedStatement.close();
                connect.close();
                   
                String selected = rights.defaultValue;
                if(rights.value != null) {
                    selected = rights.value;
                }
                
                ArrayList<DropdownValues> values = new ArrayList<DropdownValues>();
                DropdownValues value;
                if(rights.type == "boolean") {
                    value = new DropdownValues();
                    value.key = "true";
                    value.value = "true";
                    if(selected == "true") {
                        value.selected = true;
                    }else{
                        value.selected = false;
                    }
                    values.add(value);
                    value = new DropdownValues();
                    value.key = "false";
                    value.value = "false";
                    if(selected == "false") {
                        value.selected = true;
                    }else{
                        value.selected = false;
                    }
                    values.add(value);
                } else if (rights.type == "group") {
                    value = new DropdownValues();
                    value.key = "other";
                    value.value = "other";
                    if(selected == "other") {
                        value.selected = true;
                    }else{
                        value.selected = false;
                    }
                    values.add(value);
                    value = new DropdownValues();
                    value.key = "own";
                    value.value = "own";
                    if(selected == "own") {
                        value.selected = true;
                    }else{
                        value.selected = false;
                    }
                    values.add(value);
                    value = new DropdownValues();
                    value.key = "false";
                    value.value = "false";
                    if(selected == "false") {
                        value.selected = true;
                    }else{
                        value.selected = false;
                    }
                    values.add(value);
                }
                
                request.setAttribute("dropdown", values);
                request.setAttribute("rightlist", rights);
                
                RequestDispatcher rd = null;
                HttpSession session = request.getSession(true);

                rd = request.getRequestDispatcher("groups/rightsedit.jsp");
                rd.forward(request, response);
            }

        } catch (Exception ex) {
            Logger.getLogger(GroupsEditServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        
    }

}
