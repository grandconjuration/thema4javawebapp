/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncloud6.atd.accounts;

import com.oncloud6.atd.domain.Gebruiker;
import com.oncloud6.atd.domain.Groep;
import com.oncloud6.atd.hibernate.HibernateConnector;
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
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Niels
 */
@WebServlet(name = "AccountsLoginServlet", urlPatterns = {"/accountslogin"})
public class AccountsLoginServlet extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

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
        rd = request.getRequestDispatcher("theme/header.jsp");
        HttpSession session = request.getSession(true);

        String GivenUsername = request.getParameter("username");
        String GivenPassword = request.getParameter("password");

        if ((GivenUsername != null || !"".equals(GivenUsername))&& (GivenPassword != null || !"".equals(GivenPassword))) {
            SessionFactory factory = new HibernateConnector().getSessionFactory();
            Session hibernateSession = factory.openSession();
            Transaction tx = null;
            Integer GebruikerID = null;
            try {
                tx = hibernateSession.beginTransaction();
                Gebruiker gebruiker = new Gebruiker();
                hibernateSession.load(gebruiker, GivenUsername);

                if (GivenPassword.equals(gebruiker.getPassword())) {
                    GebruikerID = (Integer)(gebruiker.getId());
                    String username = gebruiker.getUsername();
                    Groep GroupID = gebruiker.getGroep();

                    session.setAttribute("userID", GebruikerID);
                    session.setAttribute("userName", username);
                    session.setAttribute("groupID", GroupID);

                    hibernateSession.save(gebruiker);
                    response.sendRedirect("");
                } else {
                    session.setAttribute("msgs", "gebruikersnaam en/of wachtwoord komen niet overeen");
                    response.sendRedirect("");
                }
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            } finally {
                hibernateSession.close();
            }
        } else {
            session.setAttribute("msgs", "geen gegevens ingevuld");
            response.sendRedirect("");
        }
        /*
         MySQLConnection DBConnection = new MySQLConnection();

         try {

         Connection connect = DBConnection.getConnection();

            
         PreparedStatement preparedStatement = connect.prepareStatement("SELECT gebruiker_id, gebruiker_username, gebruiker_groepen_id FROM gebruiker WHERE gebruiker_username = ? AND gebruiker_password = ?");

            

         preparedStatement.setString(1, GivenUsername);
         preparedStatement.setString(2, GivenPassword);

            
         ResultSet res = preparedStatement.executeQuery();

         if (res.next()) {
         int userID = res.getInt("gebruiker_id");
         String userName = res.getString("gebruiker_username");
         int groupID = res.getInt("gebruiker_groepen_id");

         session.setAttribute("userID", userID);
         session.setAttribute("userName", userName);
         session.setAttribute("groupID", groupID);

         response.sendRedirect("");
         } // login niet correct, act accordingly
         else {
         response.sendRedirect("");

         }

         preparedStatement.close();
         connect.close();

         } catch (Exception ex) {
         Logger.getLogger(AccountsLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
         */

    }

}
