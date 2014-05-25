/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncloud6.atd.maintainance;

import com.oncloud6.atd.domain.Auto;
import com.oncloud6.atd.domain.GebruiktOnderdeel;
import com.oncloud6.atd.domain.Onderdeel;
import com.oncloud6.atd.domain.Onderhoud;
import com.oncloud6.atd.hibernate.HibernateConnector;
import java.io.IOException;
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
@WebServlet(name = "MaintenanceAdd", urlPatterns = {"/maintenanceadd"})
public class MaintenanceAdd extends HttpServlet {

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
	   try {
		  // begin transactie
		  tx = hibernateSession.beginTransaction();

		  // maak nieuw onderhoud
		  Onderhoud onderhoud = new Onderhoud();

		  //nieuwe test auto maken
		  Auto testAuto = new Auto("Kia", "Karenz", "12345KZ", "987654321");

		  onderhoud.setAuto(testAuto);

		  List<GebruiktOnderdeel> gebruikteOnderdelen = new ArrayList<GebruiktOnderdeel>();

		  // ff nieuw onderdeel maken
		  Onderdeel nwOnderdeel = new Onderdeel("Uitlaat", 14, 35);
	//	  GebruiktOnderdeel gbrOnderdeel = new GebruiktOnderdeel(1, nwOnderdeel);
	//	  hibernateSession.save(gbrOnderdeel);

		 // gebruikteOnderdelen.add(new GebruiktOnderdeel(1, nwOnderdeel));
		  GebruiktOnderdeel gBO = new GebruiktOnderdeel(1, nwOnderdeel);
		//  hibernateSession.save(gBO);
		//  gebruikteOnderdelen.add(gBO);
		//  onderhoud.setGebruikteOnderdelen(gebruikteOnderdelen);

		  // set huidige datum
		  onderhoud.setDatum(new Date());
		  //set beschrijving
		  onderhoud.setBeschrijving("hallo laura");
		  //aantal manuren
		  onderhoud.setManuur(24);
		  //set status
		  onderhoud.setStatus("Bruine Anjer");
		  // get generated id terug
		//  onderhoudID = (Integer) hibernateSession.save(onderhoud);
		  
		  System.out.println(" 1: " +onderhoud.getGebruikteOnderdelen().toString());
		  gebruikteOnderdelen.add(gBO);
		  onderhoud.setGebruikteOnderdelen(gebruikteOnderdelen);

		  hibernateSession.save(onderhoud);
		  System.out.println(" 2:"+ onderhoud.getGebruikteOnderdelen().toString());
		  // commit transactie
		  tx.commit();
	   } catch (HibernateException e) {
		  if (tx != null) {
			 // Exception? roll alles terug
			 tx.rollback();
		  }
		  e.printStackTrace();
	   } finally {
		  // finally close
		  hibernateSession.close();
		  factory.close();
	   }
    }

}
