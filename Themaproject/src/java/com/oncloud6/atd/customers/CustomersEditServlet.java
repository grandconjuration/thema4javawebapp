/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncloud6.atd.customers;

import com.oncloud6.atd.domain.Klant;
import com.oncloud6.atd.hibernate.HibernateConnector;
import com.oncloud6.atd.mysql.MySQLConnection;
import com.oncloud6.atd.rights.RightsControl;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.reflect.Array.set;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
import static sun.misc.MessageUtils.where;

/**
 *
 * @author Simon Whiteley <simonwhiteley@hotmail.com>
 */
@WebServlet(name = "CustomersEditServlet", urlPatterns = {"/customersedit"})
public class CustomersEditServlet extends HttpServlet {

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
        
        HttpSession session = request.getSession(true);
        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("customers/update.jsp");
       
      
   
        // Connecten met hibernate
         SessionFactory factory = new HibernateConnector().getSessionFactory();
        Session hibernateSession = factory.openSession();
        Transaction tx = null;
        
        // Controleren of er wel een customer is om te editen

        if (request.getParameter("id") != null) {
            
             try {
            tx = hibernateSession.beginTransaction();
            
            // "Nieuwe" klant aanmaken.
            Klant klant = new Klant();
            // Klant de gegevens geven van de klant in de database met de parameter CustomerId
            hibernateSession.load(klant, Integer.parseInt(request.getParameter("id")));
            String klantNaam = klant.getKlantNaam();
            String klantAdres = klant.getKlantAdres();
            Double klantKorting = klant.getKorting();
            Date klantGeboortedatum = klant.getGeboorteDatum();
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            String geboorteDatum = df.format(klantGeboortedatum);
            String klantPostcode = klant.getPostcode();
            String klantWoonplaats = klant.getWoonplaats();
            String klantWachtwoord = klant.getGebruiker().getPassword();
            
            // Gegevens klant als attribuut zetten zodat ze in de tekstvelden worden geplaatst
            request.setAttribute("klant_naam", klantNaam);
            request.setAttribute("klant_adres", klantAdres);
            request.setAttribute("klant_korting", klantKorting);
            request.setAttribute("klant_geboortedatum", geboorteDatum);
             request.setAttribute("klant_postcode", klantPostcode);
            request.setAttribute("klant_woonplaats", klantWoonplaats);
            request.setAttribute("klant_wachtwoord", klantWachtwoord);
            
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


        

       
        rd.forward(request, response);

           

        }
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
          HttpSession session = request.getSession(true);
        RequestDispatcher rd = null;
           rd = request.getRequestDispatcher("customers/update.jsp");
      
        // Connecten met hibernate
          SessionFactory factory = new HibernateConnector().getSessionFactory();
        Session hibernateSession = factory.openSession();
        Transaction tx = null;
        String customerName = request.getParameter("customername");
            String customerAddress = request.getParameter("customeraddress");
            String customerDiscount = request.getParameter("discount");
            String customerDateofBirth = request.getParameter("dateofbirth");
            String customerPostcode = request.getParameter("customerpostcode");
            String customerPlace = request.getParameter("customerplace");
            String customerPassword = request.getParameter("password");
           try {
            tx = hibernateSession.beginTransaction();
            
            request.setAttribute("klant_naam", customerName);
            request.setAttribute("klant_adres", customerAddress);
            request.setAttribute("klant_korting", customerDiscount);
            request.setAttribute("klant_geboortedatum", customerDateofBirth);
             request.setAttribute("klant_postcode", customerPostcode);
            request.setAttribute("klant_woonplaats", customerPlace);
            request.setAttribute("klant_wachtwoord", customerPassword);
            
            // post variabelen uitzetten
            
            
               if(customerName== null || customerName.equals("")) {
               request.setAttribute("message", "U heeft geen naam ingevuld!");
               rd.forward(request, response); 
            }
            else if(customerPostcode== null || customerPostcode.equals("")) {
               request.setAttribute("message", "U heeft geen postcode ingevuld!");
               rd.forward(request, response); 
            }
             else if(customerAddress== null || customerAddress.equals("")) {
               request.setAttribute("message", "U heeft geen adres ingevuld!");
               rd.forward(request, response); 
            }
             else if(customerPlace== null || customerPlace.equals("")) {
               request.setAttribute("message", "U heeft geen plaatsnaam ingevuld!");
               rd.forward(request, response); 
            }
             else if(customerDiscount== null || customerDiscount.equals("")) {
               request.setAttribute("message", "U heeft geen username ingevuld!");
               rd.forward(request, response); 
            }
           
             else if(customerDateofBirth== null || customerDateofBirth.equals("")) {
               request.setAttribute("message", "U heeft geen adres ingevuld!");
               rd.forward(request, response); 
            }
              else if(customerPassword== null || customerPassword.equals("")) {
               request.setAttribute("message", "U heeft geen wachtwoord ingevuld!");
               rd.forward(request, response); 
            }
             
             else{
                  Klant klant = new Klant();
            // "Nieuwe" klant aanmaken met de gegevens van de klant met parameter CustomerId
            hibernateSession.load(klant, Integer.parseInt(request.getParameter("id")));
            // "Nieuwe" waarden aan de klant geven
            klant.setKlantNaam(customerName);
            klant.setKlantAdres(customerAddress);
            klant.setKorting(Double.parseDouble(customerDiscount));
          Date dateofBirth = new SimpleDateFormat("dd-MM-yyyy").parse(customerDateofBirth);
                 klant.setWoonplaats(customerPlace);
            klant.setGeboorteDatum(dateofBirth);
            klant.getGebruiker().setPassword(customerPassword);
            // Klant opslaan
            hibernateSession.update(klant);
             
               String klantNaam = klant.getKlantNaam();
            String klantAdres = klant.getKlantAdres();
            Double klantKorting = klant.getKorting();
             Date klantGeboortedatum = klant.getGeboorteDatum();
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
             String geboorteDatum = df.format(klantGeboortedatum);
            String klantPostcode = klant.getPostcode();
            String klantWoonplaats = klant.getWoonplaats();
            String klantWachtwoord = klant.getGebruiker().getPassword();
            // Gegevens klant als attribuut zetten zodat ze in de tekstvelden worden geplaatst
            request.setAttribute("klant_naam", klantNaam);
            request.setAttribute("klant_adres", klantAdres);
            request.setAttribute("klant_korting", klantKorting);
            request.setAttribute("klant_geboortedatum", geboorteDatum);
             request.setAttribute("klant_postcode", klantPostcode);
            request.setAttribute("klant_woonplaats", klantWoonplaats);
            request.setAttribute("klant_wachtwoord", klantWachtwoord);
            
           
            rd.forward(request, response); 
            
            
             }
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
