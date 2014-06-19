/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncloud6.atd.maintenances;

import com.oncloud6.atd.domain.Klant;
import com.oncloud6.atd.domain.Onderhoud;
import com.oncloud6.atd.hibernate.HibernateConnector;

import com.oncloud6.atd.mysql.MySQLConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
 * @author Laura van den Heuvel
 */
@WebServlet(name = "MaintenancesIndexServlet", urlPatterns = {"/maintenances"})
public class MaintenancesIndexServlet extends HttpServlet {

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
        List onderhoudList = null;
        List<Klant> klantList = null;
        ArrayList<DropdownValues> values = null;
        // Controleren of het Customer id veld is ingevuld
        boolean idSet = false;
        if (request.getParameter("cid") == null || request.getParameter("cid").equals("")) {
            idSet = false;
        } else {
            idSet = true;
        }
        
         SessionFactory factory = new HibernateConnector().getSessionFactory();
        Session hibernateSession = factory.openSession();
        Transaction tx = null;
           
             try {
            tx = hibernateSession.beginTransaction();
            
      
            
            onderhoudList = hibernateSession.createQuery("FROM Onderhoud").list();
            klantList = hibernateSession.createQuery("FROM Klant").list();
            
            
          values = new ArrayList<DropdownValues>();
            DropdownValues value;

            value = new DropdownValues();
            value.key = "";
            value.value = "";
            value.selected = false;
            values.add(value);
            
            for(Klant klant : klantList) {
                value = new DropdownValues();
                value.key = Integer.toString(klant.getId());
                value.value = klant.getKlantNaam();
                value.selected = false;
                values.add(value);
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
               request.setAttribute("list", onderhoudList);
               request.setAttribute("klantlist", values);
               rd = request.getRequestDispatcher("maintenances/home.jsp");
            rd.forward(request, response);
        
        }}

