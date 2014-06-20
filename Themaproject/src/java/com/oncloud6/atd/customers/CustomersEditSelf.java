/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oncloud6.atd.customers;

import com.oncloud6.atd.domain.Klant;
import com.oncloud6.atd.hibernate.HibernateConnector;
import com.oncloud6.atd.rights.RightsControl;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author Laura
 */
@WebServlet(name = "CustomersEditSelf", urlPatterns = {"/CustomersEditSelf"})
public class CustomersEditSelf extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(true);
        RequestDispatcher rd = null;
   
         SessionFactory factory = new HibernateConnector().getSessionFactory();
        Session hibernateSession = factory.openSession();
        Transaction tx = null;

        if (request.getParameter("cid") != null) {
            
             try {
            tx = hibernateSession.beginTransaction();
            
            Klant klant = new Klant();
            hibernateSession.load(klant, Integer.parseInt(request.getParameter("cid")));
            String klantNaam = klant.getKlantNaam();
            String klantAdres = klant.getKlantAdres();
            
            Date klantGeboortedatum = klant.getGeboorteDatum();
            
            request.setAttribute("klant_naam", klantNaam);
            request.setAttribute("klant_adres", klantAdres);
            request.setAttribute("klant_geboortedatum", klantGeboortedatum);
            
            hibernateSession.update(klant);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            hibernateSession.close();
        }


        

        rd = request.getRequestDispatcher("customers/editself.jsp");
        rd.forward(request, response);

           

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          HttpSession session = request.getSession(true);
        RequestDispatcher rd = null;
    
          SessionFactory factory = new HibernateConnector().getSessionFactory();
        Session hibernateSession = factory.openSession();
        Transaction tx = null;
           try {
            tx = hibernateSession.beginTransaction();
            
            Klant klant = new Klant();
            hibernateSession.load(klant, Integer.parseInt(request.getParameter("cid")));
            
            // post variabelen uitzetten
            String customerName = request.getParameter("customername");
            String customerAddress = request.getParameter("customeraddress");
          
            Date customerDateofBirth = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateofbirth"));

            klant.setKlantNaam(customerName);
            klant.setKlantAdres(customerAddress);
           
            klant.setGeboorteDatum(customerDateofBirth);
            
            hibernateSession.update(klant);
           
            
            
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(CustomersEditServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            hibernateSession.close();
        }

      

    }

}
