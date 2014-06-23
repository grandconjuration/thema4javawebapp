/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oncloud6.atd.invoices;

import com.oncloud6.atd.domain.Factuur;
import com.oncloud6.atd.hibernate.HibernateConnector;
import com.oncloud6.atd.rights.RightsControl;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Jelle
 */
@WebServlet(name = "InvoicesShowServlet", urlPatterns = {"/invoicesshow"})
public class InvoicesShowServlet extends HttpServlet {

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
        
        boolean idSet = false;
        if (request.getParameter("id") == null || request.getParameter("id").equals("")) {
            response.sendRedirect("invoices");
        } else {
            try {
                Factuur gekozenFactuur = new Factuur();
                gekozenFactuur = (Factuur)hibernateSession.get(Factuur.class, Integer.parseInt(request.getParameter("id")));
                if(gekozenFactuur == null) {
                    response.sendRedirect("invoices");
                }else{
                    HttpSession session = request.getSession(true);
                    RequestDispatcher rd = null;
                    if(!RightsControl.checkGroup("invoices_show", "own", session, gekozenFactuur.getDeKlant().getGebruiker().getId())) {
                        rd = request.getRequestDispatcher("error/403error.jsp");
                        rd.forward(request, response);
                        return;
                    }
                    
                    response.addHeader("Content-Disposition", "attachment; filename=fac"+gekozenFactuur.getFactuurNummer()+".pdf");
                    Process process = Runtime.getRuntime().exec("c:\\rotativa\\wkhtmltopdf.exe -q -n --disable-smart-shrinking http://localhost:8080/Themaproject/invoicessource?id="+request.getParameter("id")+"&secret="+gekozenFactuur.getSecret()+" -");
                    response.setContentType("application/pdf");
                    try {
                        IOUtils.copy(process.getInputStream(), response.getOutputStream());
                    } finally {
                        process.getInputStream().close();
                        response.getOutputStream().close();
                    }
                }
            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            } finally {
                hibernateSession.close();
            }
        }
        
        
    }

}
