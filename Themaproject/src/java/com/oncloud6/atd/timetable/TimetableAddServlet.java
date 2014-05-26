/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncloud6.atd.timetable;

import com.oncloud6.atd.domain.Monteur;
import com.oncloud6.atd.domain.Onderhoud;
import com.oncloud6.atd.domain.Planning;
import com.oncloud6.atd.hibernate.HibernateConnector;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * @author Simon Whiteley <simonwhiteley@hotmail.com>
 */
@WebServlet(name = "TimetableAddServlet", urlPatterns = {"/timetableadd"})
public class TimetableAddServlet extends HttpServlet {

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

        SessionFactory factory = new HibernateConnector().getSessionFactory();
        Session hibernateSession = factory.openSession();
        Transaction tx = null;
        Integer onderhoudID = null;
        List onderhoudList = null;
        List monteurList = null;
        try {
            tx = hibernateSession.beginTransaction();
            onderhoudList = hibernateSession.createQuery("FROM Onderhoud").list();
            monteurList = hibernateSession.createQuery("FROM Monteur").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            hibernateSession.close();
        }

        RequestDispatcher rd = null;
        HttpSession session = request.getSession(true);

        request.setAttribute("onderhoudList", onderhoudList);
        request.setAttribute("monteurList", monteurList);

        rd = request.getRequestDispatcher("timetables/add.jsp");
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
        Integer planningID = null;
        try {
            tx = hibernateSession.beginTransaction();
            Planning planning = new Planning();
            Onderhoud onderhoud = new Onderhoud();
            hibernateSession.load(onderhoud, Integer.parseInt(request.getParameter("onderhoud")));
            planning.setOnderhoud(onderhoud);

            Monteur monteur = new Monteur();
            hibernateSession.load(monteur, Integer.parseInt(request.getParameter("monteur")));
            planning.setMonteur(monteur);

            Date dateStart = new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("datum_start"));
            Date dateEinde = new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("datum_einde"));

            planning.setDatumStart(dateStart);
            planning.setDatumEind(dateEinde);

            planningID = (Integer) hibernateSession.save(planning);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(TimetableAddServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            hibernateSession.close();
            factory.close();
        }

        RequestDispatcher rd = null;
        HttpSession session = request.getSession(true);

        rd = request.getRequestDispatcher("timetable/add.jsp");

        rd.forward(request, response);

    }
}
