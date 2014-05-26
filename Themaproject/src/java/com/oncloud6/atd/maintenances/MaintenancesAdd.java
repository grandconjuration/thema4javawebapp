/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncloud6.atd.maintenances;

import com.oncloud6.atd.domain.Auto;
import com.oncloud6.atd.domain.GebruiktOnderdeel;
import com.oncloud6.atd.domain.Onderdeel;
import com.oncloud6.atd.domain.Onderhoud;
import com.oncloud6.atd.hibernate.HibernateConnector;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
 * @author Simon Whiteley
 */
@WebServlet(name = "MaintenancesAdd", urlPatterns = {"/maintenancesadd"})
public class MaintenancesAdd extends HttpServlet {

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

        rd = request.getRequestDispatcher("maintenances/add.jsp");
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

        SessionFactory factory = new HibernateConnector().getSessionFactory();
        Session hibernateSession = factory.openSession();
        Transaction tx = null;
        Integer onderhoudID = null;
        try {
            tx = hibernateSession.beginTransaction();
            Onderhoud onderhoud = new Onderhoud();
            Auto auto = new Auto();
            hibernateSession.load(auto, Integer.parseInt(request.getParameter("auto")));
            onderhoud.setAuto(auto);

            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("datum"));

            onderhoud.setDatum(date);
            onderhoud.setStatus("added");
            onderhoud.setBeschrijving(request.getParameter("beschrijving"));
            onderhoudID = (Integer) hibernateSession.save(onderhoud);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(MaintenancesAdd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            hibernateSession.close();
            factory.close();
        }

        RequestDispatcher rd = null;
        HttpSession session = request.getSession(true);

        rd = request.getRequestDispatcher("maintenances/add.jsp");

        rd.forward(request, response);

    }
}
