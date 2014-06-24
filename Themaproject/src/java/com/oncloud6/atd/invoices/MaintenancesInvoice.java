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
import com.oncloud6.atd.rights.RightsControl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
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
@WebServlet(name = "MaintenancesInvoice", urlPatterns = {"/maintenancesinvoice"})
public class MaintenancesInvoice extends HttpServlet {

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

	   HttpSession session = request.getSession(true);
	   RequestDispatcher rd = null;
	   if (!RightsControl.checkBoolean("invoices_add", "true", session)) {
		  rd = request.getRequestDispatcher("error/403error.jsp");
		  rd.forward(request, response);
		  return;
	   }

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
				
				Factuur fac = (Factuur) hibernateSession.createQuery(""
				    + "FROM Factuur fac ORDER BY fac.factuurNummer DESC")
				    .setMaxResults(1)
				    .uniqueResult();

				Factuur factuur = new Factuur();
				Date datum = new Date();
				factuur.setFactuurDatum(datum);
				factuur.setDeKlant(gekozenKlant);
				factuur.setKlantNaam(gekozenKlant);
				factuur.setKlantAdres(gekozenKlant);
				factuur.setFactuurKorting(gekozenKlant.getKorting());
				factuur.setSecret(UUID.randomUUID().toString());
				if(fac != null) {
				factuur.setFactuurNummer(fac.getFactuurNummer()+1);
				} else {
				    factuur.setFactuurNummer(0);
				}
				List<FactuurItem> factuurItems = new ArrayList<FactuurItem>();
				for (GebruiktOnderdeel onderdeel : gekozenOnderhoud.getGebruikteOnderdelen()) {
				    FactuurItem fi = new FactuurItem();
				    fi.setFactuurItemHoeveelheid(onderdeel.getHoeveelheid());
				    fi.setFactuurItemNaam(onderdeel.getOnderdeel().getNaam());
				    fi.setFactuurItemPrijs(onderdeel.getOnderdeel().getPrijs());
				    fi.setFactuur(factuur);
				    factuurItems.add(fi);
				}
                                //manuren
                                FactuurItem manUren = new FactuurItem();
                                manUren.setFactuurItemPrijs(30.00);
                                manUren.setFactuurItemHoeveelheid(gekozenOnderhoud.getManuur());
                                manUren.setFactuurItemNaam("Kosten " + gekozenOnderhoud.getManuur() + " uur arbeid");
                                factuurItems.add(manUren);
                                
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


	   rd = request.getRequestDispatcher("invoices/add.jsp");
	   rd.forward(request, response);
    }

}
