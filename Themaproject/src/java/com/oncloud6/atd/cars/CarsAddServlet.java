/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncloud6.atd.cars;

import com.oncloud6.atd.domain.Auto;
import com.oncloud6.atd.domain.Gebruiker;
import com.oncloud6.atd.domain.Klant;
import com.oncloud6.atd.hibernate.HibernateConnector;
import com.oncloud6.atd.mysql.MySQLConnection;
import com.oncloud6.atd.rights.RightsControl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
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
@WebServlet(name = "CarsAddServlet", urlPatterns = {"/carsadd"})
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
        HttpSession session = request.getSession(true);
        RequestDispatcher rd = null;
        if(!RightsControl.checkBoolean("cars_add", "true", session)) {
            rd = request.getRequestDispatcher("error/403error.jsp");
            rd.forward(request, response);
            return;
        }

        rd = request.getRequestDispatcher("cars/add.jsp");
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
        RequestDispatcher rd = null;
        if(!RightsControl.checkBoolean("cars_add", "true", session)) {
            rd = request.getRequestDispatcher("error/403error.jsp");
            rd.forward(request, response);
            return;
        }

        SessionFactory factory = new HibernateConnector().getSessionFactory();
        Session hibernateSession = factory.openSession();
        Transaction tx = null;

        String brand = request.getParameter("brand");
        String type = request.getParameter("type");
        int userID = (int) session.getAttribute("userID");
        String licenseplate = request.getParameter("licenseplate");

        try {
            tx = hibernateSession.beginTransaction();
            Auto auto = new Auto();
            auto.setType(type);
            auto.setKenteken(licenseplate);
            auto.setMerk(brand);

            Gebruiker user = new Gebruiker();
            hibernateSession.load(user, userID);

            Klant klant = (Klant) hibernateSession
                    .createQuery("FROM Klant klant WHERE klant.deGebruiker = :userid")
                    .setParameter("userid", user).uniqueResult();

            auto.setKlant(klant);
            
            hibernateSession.save(auto);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            hibernateSession.close();
        }

        // forward naar zichzelf, pagina waarop je je al bevindt
        rd = request.getRequestDispatcher("cars/add.jsp");
        rd.forward(request, response);

    }
}
