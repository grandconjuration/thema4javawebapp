/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncloud6.atd.users;

import com.oncloud6.atd.domain.Gebruiker;
import com.oncloud6.atd.domain.Groep;
import com.oncloud6.atd.hibernate.HibernateConnector;
import java.io.IOException;
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
import org.hibernate.cfg.AnnotationConfiguration;


/**
 *
 * @author Simon Whiteley <simonwhiteley@hotmail.com>
 */
@WebServlet(name = "UsersAddServlet", urlPatterns = {"/usersadd"})
public class UsersAddServlet extends HttpServlet {

    private SessionFactory factory;

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
	   RequestDispatcher rd = null;
	   HttpSession session = request.getSession(true);

	   rd = request.getRequestDispatcher("users/add.jsp");
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
	   
	   factory = new HibernateConnector().getSessionFactory();
	   Session hibernateSession = factory.openSession();
	   Transaction tx = null;
	   Integer gebruikerID = null;
	   try {
		  tx = hibernateSession.beginTransaction();
		  Gebruiker gebruiker = new Gebruiker();
		  gebruiker.setUsername(request.getParameter("username"));
		  gebruiker.setPassword(request.getParameter("password"));
		  Groep groep = new Groep();
		  groep.setGroepId(2);
		  gebruiker.setGroep(groep);
		  gebruikerID = (Integer) hibernateSession.save(gebruiker);
		  tx.commit();
	   } catch (HibernateException e) {
		  if (tx != null) {
			 tx.rollback();
		  }
		  e.printStackTrace();
	   } finally {
		  hibernateSession.close();
		  factory.close();
	   }

	   RequestDispatcher rd = null;
	   HttpSession session = request.getSession(true);

	   rd = request.getRequestDispatcher("users/add.jsp");

	   rd.forward(request, response);

    }
}
