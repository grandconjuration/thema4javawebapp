/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncloud6.atd.invoices;

import com.oncloud6.atd.domain.Factuur;
import com.oncloud6.atd.domain.Gebruiker;
import com.oncloud6.atd.domain.Klant;
import com.oncloud6.atd.hibernate.HibernateConnector;
import com.oncloud6.atd.rights.RightsControl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
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
@WebServlet(name = "InvoicesIndexServlet", urlPatterns = {"/invoices"})
public class InvoicesIndexServlet extends HttpServlet {

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

        SessionFactory factory = new HibernateConnector().getSessionFactory();
        Session hibernateSession = factory.openSession();
        Transaction tx = null;
        boolean idSet = false;
        int customerId = 0;
        try {
            tx = hibernateSession.beginTransaction();
            
            if(session.getAttribute("userID") == null) {
                rd = request.getRequestDispatcher("error/403error.jsp");
                rd.forward(request, response);
                return;
            }

            List klantListTemp = (List<Klant>)hibernateSession.createQuery(""
                                        + "FROM Klant AS klant "
                                        + "WHERE klant.deGebruiker.id = :id")
                                        .setParameter("id", Integer.parseInt(session.getAttribute("userID").toString()))
                                        .list();
            Iterator iteTemp = klantListTemp.iterator();
            if(iteTemp.hasNext()) {
                Klant klantTijdelijk = (Klant) iteTemp.next();

                customerId = klantTijdelijk.getId();
            }
            
            String right = RightsControl.GetRightGroup("maintenances_index", session);
            
            request.setAttribute("right", right);
            
            if(right.equals("false")) {
                rd = request.getRequestDispatcher("error/403error.jsp");
                rd.forward(request, response);
                return;
            }
            
            if(right.equals("other")) {
                idSet = false;
            }else{
                idSet = true;
            }
            
            List<Factuur> factuurList = null;
            if (!idSet) {
                factuurList = (List<Factuur>) hibernateSession.createQuery("FROM Factuur fac ORDER BY fac.factuurDatum DESC").list();
            }else{
                factuurList = (List<Factuur>) hibernateSession.createQuery("FROM Factuur fac WHERE fac.klant.id = :klantid ORDER BY fac.factuurDatum DESC").setParameter("klantid", customerId).list();
            }
            request.setAttribute("factuurList", factuurList);
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            hibernateSession.close();
        }
        rd = request.getRequestDispatcher("invoices/home.jsp");
        rd.forward(request, response);
    }
}
