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
import java.sql.ResultSet;
import java.util.ArrayList;
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
@WebServlet(name = "GroupsIndexServlet", urlPatterns = {"/groups"})
public class GroupsIndexServlet extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        RequestDispatcher rd = null;
        RightsControl.initRequest(request, response);
        int userId = Integer.parseInt(session.getAttribute("groupID").toString());
        if(!RightsControl.checkBoolean("groups_index", "true", userId)) {
            rd = request.getRequestDispatcher("error/403error.jsp");
            rd.forward(request, response);
            return;
        }
        try {
            Connection connect = DBConnection.getConnection();
            PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM atd.groepen");
            ResultSet resultSet = preparedStatement.executeQuery();
            
            ArrayList<GroupsList> values = new ArrayList<GroupsList>();
            
            while (resultSet.next()) {
                GroupsList group = new GroupsList();
                group.id = resultSet.getString("groepen_id");
                group.naam = resultSet.getString("groepen_naam");
                
                values.add(group);
            } 
            request.setAttribute("grouplist", values);
            
            preparedStatement.close();
            connect.close();

            rd = request.getRequestDispatcher("groups/home.jsp");
            rd.forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(GroupsIndexServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
