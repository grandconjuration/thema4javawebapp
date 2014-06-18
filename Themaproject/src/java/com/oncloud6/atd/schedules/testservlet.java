/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncloud6.atd.schedules;

import com.oncloud6.atd.domain.Factuur;
import com.oncloud6.atd.domain.FactuurItem;
import com.oncloud6.atd.domain.Klant;
import com.oncloud6.atd.domain.Monteur;
import com.oncloud6.atd.hibernate.HibernateConnector;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Simon Whiteley
 */
@WebServlet(name = "testservlet", urlPatterns = {"/testservlet"})
public class testservlet extends HttpServlet {

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
	   
        SessionFactory factory = new HibernateConnector().getSessionFactory();
        Session hibernateSession = factory.openSession();
        Transaction tx = null;
        Integer factuurID = null;
        try {
            tx = hibernateSession.beginTransaction();
            
		  //nieuw factuur maken
		  Factuur fact = new Factuur();
		  //set fatuur nr
		  fact.setFactuurNummer(1525999);
		  
		  //load klant
		  Klant klant = new Klant();
            hibernateSession.load(klant, 1);
		  fact.setDeKlant(klant);

		  //set factuur info
		  fact.setFactuurDatum(new Date());
		  fact.setKlantNaam(klant);
		  fact.setKlantAdres(klant);
		  fact.setFactuurKorting(20.0);
		  fact.setSubTotaalBedrag(157.0);
		  fact.setBtwBedrag(22.0);
		  fact.setTotaalBedrag(10000.0);
		  
		  factuurID = (Integer) hibernateSession.save(fact);
		  
		  //nu kun je items adden (want er is een id :P)
		  
		  // factuur itempje maken
		  FactuurItem item1 = new FactuurItem();
		  item1.setFactuurItemHoeveelheid(10);
		  item1.setFactuurItemNaam("schroeven");
		  item1.setFactuurItemPrijs(15.50);
		  
		  //beetje cheaten
		  item1.setFactuur(fact);
		  
		  List FactuurItems = new ArrayList<FactuurItem>();
		  FactuurItems.add(item1);
		  
		  
		  //toevoegen 
		  fact.setDeFactuurItems(FactuurItems);
		  
		  hibernateSession.save(fact);
		  
		  tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            hibernateSession.close();
        }
	   
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
	   processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
	   return "Short description";
    }// </editor-fold>

}
