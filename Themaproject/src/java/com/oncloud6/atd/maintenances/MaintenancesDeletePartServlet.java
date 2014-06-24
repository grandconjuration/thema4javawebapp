/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oncloud6.atd.maintenances;

import com.oncloud6.atd.mysql.MySQLConnection;
import com.oncloud6.atd.rights.GroupsRightsRestoreServlet;
import com.oncloud6.atd.rights.RightsControl;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
@WebServlet(name = "MaintenancesDeletePartServlet", urlPatterns = {"/maintenancesdeletepart"})
public class MaintenancesDeletePartServlet extends HttpServlet {
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
        MySQLConnection DBConnection = new MySQLConnection();HttpSession session = request.getSession(true);
        RequestDispatcher rd = null;
        if(!RightsControl.checkBoolean("maintenances_delete", "true", session)) {
            rd = request.getRequestDispatcher("error/403error.jsp");
            rd.forward(request, response);
            return;
        }
        try {
            Connection connect = DBConnection.getConnection();
            PreparedStatement preparedStatement = connect.prepareStatement("DELETE FROM atd.onderhoud_onderdeel WHERE onderdeel_id = ? AND onderhoud_id = ?");

            String id = request.getParameter("id");
            String mid = request.getParameter("mid");

            preparedStatement.setString(1, id);
            preparedStatement.setString(2, mid);
            preparedStatement.executeUpdate();

            //niet vergeten om alles te sluiten :)
            preparedStatement.close();
            connect.close();

            response.sendRedirect("maintenancesedit?id=" + mid);

        } catch (Exception ex) {
            Logger.getLogger(GroupsRightsRestoreServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
