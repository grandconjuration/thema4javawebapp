/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncloud6.atd.invoices;

import com.oncloud6.atd.domain.Klant;
import com.oncloud6.atd.hibernate.HibernateConnector;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "InvoicesEditServlet", urlPatterns = {"/invoicesedit"})
public class InvoicesEditServlet extends HttpServlet {

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
	   List factuurList = null;

	   boolean idSet = false;
	   if (request.getParameter("id") == null || request.getParameter("id").equals("")) {
		  idSet = false;
	   } else {
		  idSet = true;
	   }

	   try {
		  tx = hibernateSession.beginTransaction();

		  if (!idSet) {
			 factuurList = hibernateSession.createQuery("FROM Factuur").list();
			 request.setAttribute("factuurList", factuurList);
		  } else {
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

	   request.setAttribute("idSet", idSet);

	   RequestDispatcher rd = null;
	   HttpSession session = request.getSession(true);

	   rd = request.getRequestDispatcher("invoices/add.jsp");
	   rd.forward(request, response);

    }
}
