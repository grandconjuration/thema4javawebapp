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
@WebServlet(name = "GroupsRightsRestoreServlet", urlPatterns = {"/groupsrightsrestore"})
public class GroupsRightsRestoreServlet extends HttpServlet {

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
        RightsControl.initRequest(request, response);
        int userId = Integer.parseInt(session.getAttribute("groupID").toString());
        if(!RightsControl.checkBoolean("groups_rights_restore", "true", userId)) {
            rd = request.getRequestDispatcher("error/403error.jsp");
            rd.forward(request, response);
            return;
        }
        try {
            Connection connect = DBConnection.getConnection();
            PreparedStatement preparedStatement = connect.prepareStatement("DELETE FROM atd.rechten_groepen WHERE rechten_id = ? AND groepen_id = ?");

            String id = request.getParameter("id");
            String gid = request.getParameter("gid");

            preparedStatement.setString(1, id);
            preparedStatement.setString(2, gid);
            preparedStatement.executeUpdate();

            //niet vergeten om alles te sluiten :)
            preparedStatement.close();
            connect.close();

            response.sendRedirect("groupsedit?id=" + gid);

        } catch (Exception ex) {
            Logger.getLogger(GroupsRightsRestoreServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
