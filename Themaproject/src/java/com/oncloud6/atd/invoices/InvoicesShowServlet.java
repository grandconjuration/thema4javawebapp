/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oncloud6.atd.invoices;

import com.oncloud6.atd.domain.Factuur;
import com.oncloud6.atd.hibernate.HibernateConnector;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
                    Process process = Runtime.getRuntime().exec("c:\\rotativa\\wkhtmltopdf.exe -q -n --disable-smart-shrinking http://localhost/themaproject/invoicessource?id="+request.getParameter("id")+"&secret="+gekozenFactuur.getSecret()+" -");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    OutputStream out = response.getOutputStream();
                    response.setContentType("application/pdf");
                    int bytes;
                    while ((bytes = reader.read()) != -1) {
                        out.write(bytes);
                    }

                    reader.close();
                    out.close();
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
