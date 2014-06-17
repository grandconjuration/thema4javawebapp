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
@WebServlet(name = "GroupsEditServlet", urlPatterns = {"/groupsedit"})
public class GroupsEditServlet extends HttpServlet {

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
        MySQLConnection DBConnection = new MySQLConnection();HttpSession session = request.getSession(true);
        RequestDispatcher rd = null;
        RightsControl.initRequest(request, response);
        int userId = Integer.parseInt(session.getAttribute("groupID").toString());
        if(!RightsControl.checkBoolean("groups_edit", "true", userId)) {
            rd = request.getRequestDispatcher("error/403error.jsp");
            rd.forward(request, response);
            return;
        }
        try {
            Connection connect = DBConnection.getConnection();
            PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM atd.groepen WHERE groepen_id = ?");
                    
            String id = request.getParameter("id");

            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (!resultSet.next()) {
                preparedStatement.close();
                connect.close();
                response.sendRedirect("groups");
            }else{
                request.setAttribute("id", id);
                request.setAttribute("name", resultSet.getString("groepen_naam"));
                preparedStatement.close();
                
                preparedStatement = connect.prepareStatement("SELECT atd.rechten.rechten_id as id_default, atd.rechten.rechten_key as name, atd.rechten.rechten_type as type, atd.rechten.rechten_value as value_default, atd.rechten_groepen.rechten_id as id_group, atd.rechten_groepen.rechten_groepen_value as value_group FROM atd.rechten LEFT OUTER JOIN atd.rechten_groepen ON atd.rechten.rechten_id = atd.rechten_groepen.rechten_id AND atd.rechten_groepen.groepen_id = ? ORDER BY atd.rechten.rechten_key ASC;");
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
                connect.close();

                rd = request.getRequestDispatcher("groups/edit.jsp");
                rd.forward(request, response);
            }

        } catch (Exception ex) {
            Logger.getLogger(GroupsEditServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        MySQLConnection DBConnection = new MySQLConnection();HttpSession session = request.getSession(true);
        RequestDispatcher rd = null;
        RightsControl.initRequest(request, response);
        int userId = Integer.parseInt(session.getAttribute("groupID").toString());
        if(!RightsControl.checkBoolean("groups_edit", "true", userId)) {
            rd = request.getRequestDispatcher("error/403error.jsp");
            rd.forward(request, response);
            return;
        }
        try {
            Connection connect = DBConnection.getConnection();
            PreparedStatement preparedStatement = connect.prepareStatement("UPDATE atd.groepen SET groepen_naam = ? WHERE groepen_id = ?");

            String naam = request.getParameter("groupname");
            String id = request.getParameter("id");

            preparedStatement.setString(1, naam);
            preparedStatement.setString(2, id);
            preparedStatement.executeUpdate();

            request.setAttribute("msg", "De groep \"" + naam + "\" is succesvol toegevoegd!");

            //niet vergeten om alles te sluiten :)
            preparedStatement.close();
            connect.close();

            processRequest(request, response);

        } catch (Exception ex) {
            Logger.getLogger(GroupsAddServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
