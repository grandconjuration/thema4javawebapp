/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oncloud6.atd.invoices;

import com.oncloud6.atd.domain.Factuur;
import com.oncloud6.atd.domain.Onderhoud;
import com.oncloud6.atd.hibernate.HibernateConnector;
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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Jelle
 */
@WebServlet(name = "InvoicesSourceServlet", urlPatterns = {"/invoicessource"})
public class InvoicesSourceServlet extends HttpServlet {
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
        SessionFactory factory = new HibernateConnector().getSessionFactory();
        Session hibernateSession = factory.openSession();
        Transaction tx = null;
        PrintWriter out = response.getWriter();
        
        if (request.getParameter("id") == null || request.getParameter("id").equals("") || request.getParameter("secret") == null || request.getParameter("secret").equals("")) {
            out.println("Invalid input!");
            return;
        }
        
        List factuurList = (List<Factuur>)hibernateSession.createQuery(""
				    + "FROM Factuur AS factuur "
				    + "WHERE factuur.id = :id "
				    + "AND factuur.secret = :secret")
				    .setParameter("id", Integer.parseInt(request.getParameter("id")))
				    .setParameter("secret", request.getParameter("secret"))
				    .list();
        Iterator ite = factuurList.iterator();
        if(!ite.hasNext()) {
            out.println("Invoice not found!");
            return;
        }
        Factuur factuur = (Factuur) ite.next();
        request.setAttribute("factuur", factuur);
        
        rd = request.getRequestDispatcher("invoices/source.jsp");
        rd.forward(request, response);
    }

}
