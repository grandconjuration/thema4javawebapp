/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncloud6.atd.invoices;

import com.oncloud6.atd.domain.Auto;
import com.oncloud6.atd.domain.Factuur;
import com.oncloud6.atd.domain.FactuurItem;
import com.oncloud6.atd.domain.GebruiktOnderdeel;
import com.oncloud6.atd.domain.Klant;
import com.oncloud6.atd.domain.Onderhoud;
import com.oncloud6.atd.hibernate.HibernateConnector;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
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
 * @author Simon Whiteley
 */
@WebServlet(name = "InvoicesAddServlet", urlPatterns = {"/invoicesadd"})
public class InvoicesAddServlet extends HttpServlet {

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
        List klantList = null;
        List onderhoudList = null;
        Klant gekozenKlant = null;

        boolean idSet = false;
        if (request.getParameter("id") == null || request.getParameter("id").equals("")) {
            idSet = false;
        } else {
            idSet = true;
        }
        boolean onderhoudIdSet = false;
        if (request.getParameter("onderhoudId") == null || request.getParameter("onderhoudId").equals("")) {
            onderhoudIdSet = false;
        } else {
            onderhoudIdSet = true;
        }
        boolean saveInvoiceSet = false;
        if (request.getParameter("saveInvoice") == null || request.getParameter("saveInvoice").equals("")) {
            saveInvoiceSet = false;
        } else {
            saveInvoiceSet = true;
        }
        request.setAttribute("idSet", idSet);
        request.setAttribute("onderhoudIdSet", onderhoudIdSet);
        request.setAttribute("saveInvoiceSet", saveInvoiceSet);

        try {
            tx = hibernateSession.beginTransaction();

            if (!idSet) {
                klantList = hibernateSession.createQuery("FROM Klant").list();
                request.setAttribute("klantList", klantList);
            } else {
                gekozenKlant = new Klant();
                hibernateSession.load(gekozenKlant, Integer.parseInt(request.getParameter("id")));
                request.setAttribute("gekozenKlant", gekozenKlant);

                onderhoudList = (List<Onderhoud>) hibernateSession.createQuery(""
                        + "FROM Onderhoud AS onderhoud "
                        + "INNER JOIN onderhoud.deAuto AS auto "
                        + "WHERE auto.klant.id = :klantid")
                        .setParameter("klantid", gekozenKlant.getId())
                        .list();

                Iterator ite = onderhoudList.iterator();
                List<Onderhoud> newOnderhoudList = new ArrayList<Onderhoud>();
                while (ite.hasNext()) {
                    //deze lijst bevat zowel onderhoud en autos omdat je ze joint
                    Object[] objects = (Object[]) ite.next();
                    Onderhoud onderhoud = (Onderhoud) objects[0];
                    //              Auto auto = (Auto) objects[1];
                    newOnderhoudList.add(onderhoud);
                }
                onderhoudList = newOnderhoudList;
                request.setAttribute("onderhoudList", onderhoudList);

                Onderhoud gekozenOnderhoud = null;
                if (onderhoudIdSet) {
                    double bedrag = 0;
                    for (Onderhoud onderhoud : newOnderhoudList) {
                        if (onderhoud.getId() == Integer.parseInt(request.getParameter("onderhoudId"))) {
                            gekozenOnderhoud = onderhoud;
                        }
                    }
                    request.setAttribute("gekozenOnderhoud", gekozenOnderhoud);

                    Factuur factuur = new Factuur();
                    Date datum = new Date();
                    factuur.setFactuurDatum(datum);
                    factuur.setDeKlant(gekozenKlant);
                    factuur.setKlantNaam(gekozenKlant);
                    factuur.setKlantAdres(gekozenKlant);
                    factuur.setFactuurKorting(gekozenKlant.getKorting());
                    factuur.setSecret("hoi");

                    List<FactuurItem> factuurItems = new ArrayList<FactuurItem>();
                    for (GebruiktOnderdeel onderdeel : gekozenOnderhoud.getGebruikteOnderdelen()) {
                        FactuurItem fi = new FactuurItem();
                        fi.setFactuurItemHoeveelheid(onderdeel.getHoeveelheid());
                        fi.setFactuurItemNaam(onderdeel.getOnderdeel().getNaam());
                        fi.setFactuurItemPrijs(onderdeel.getOnderdeel().getPrijs());
                        fi.setFactuur(factuur);
                        factuurItems.add(fi);
                    }
                    factuur.setDeFactuurItems(factuurItems);
                    factuur.berekenTotaalBedrag();
                    request.setAttribute("factuur", factuur);

                    if (saveInvoiceSet) {
                        hibernateSession.save(factuur);
                        request.setAttribute("message", "Factuur is succesvol opgeslagen!");
                    }

                }
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

        RequestDispatcher rd = null;
        HttpSession session = request.getSession(true);

        rd = request.getRequestDispatcher("invoices/add.jsp");
        rd.forward(request, response);
    }

}
