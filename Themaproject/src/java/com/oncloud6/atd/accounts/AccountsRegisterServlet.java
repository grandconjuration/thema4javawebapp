/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncloud6.atd.accounts;

import com.oncloud6.atd.customers.CustomersAddServlet;
import com.oncloud6.atd.domain.Gebruiker;
import com.oncloud6.atd.domain.Groep;
import com.oncloud6.atd.domain.Klant;
import com.oncloud6.atd.hibernate.HibernateConnector;
import com.oncloud6.atd.mysql.MySQLConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * @author Niels
 */
@WebServlet(name = "AccountsRegisterServlet", urlPatterns = {"/accountsregister"})
public class AccountsRegisterServlet extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
		  throws ServletException, IOException {
	   RequestDispatcher rd = null;
	   HttpSession session = request.getSession(true);
	   rd = request.getRequestDispatcher("accounts/register.jsp");
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
	   RequestDispatcher rd = null;
	   HttpSession session = request.getSession(true);

	   SessionFactory factory = new HibernateConnector().getSessionFactory();
	   Session hibernateSession = factory.openSession();
	   Transaction tx = null;

	   rd = request.getRequestDispatcher("accounts/register.jsp");

	   String name = request.getParameter("username");
	   String pass = request.getParameter("password");
	   String chckpass = request.getParameter("chckpassword");
	   //nieuwe klant voorbereiding
	   String surname = request.getParameter("surname");
	   String address = request.getParameter("address");
	   String bday = request.getParameter("birthdate");
	   String postcode = request.getParameter("zip");
	   String woonplaats = request.getParameter("city");

	   try {
		  tx = hibernateSession.beginTransaction();

		  if (name == null && pass == null && chckpass == null) {
			 request.setAttribute("message", "U heeft geen gegevens ingevuld");
			 rd.forward(request, response);

		  } else if (name == null || name.equals("")) {
			 request.setAttribute("message", "Het invullen van een gebruikersnaam is verplicht");
			 rd.forward(request, response);
		  } else if (name.length() <= 5) {
			 request.setAttribute("message", "De gebruikersnaam \"" + name + "\", voldoet niet aan de gestelde eisen. "
				    + "De gebruikersnaam moet minimaal 6 karakters lang zijn");
			 rd.forward(request, response);
		  } else if (pass == null) {
			 request.setAttribute("message", "Het invullen van een wachtwoord is verplicht");
			 rd.forward(request, response);
		  } else if (chckpass == null) {
			 request.setAttribute("message", "Type het wachtwoord nogmaals ter bevestiging");
			 rd.forward(request, response);
		  } else if (!pass.equals(chckpass)) {
			 request.setAttribute("message", "De ingevoerde wachtwoorden komen niet overeen");
			 rd.forward(request, response);
		  } else if (surname == null || surname.equals("")) {
			 request.setAttribute("message", "Het invullen van uw volledige naam is verplicht");
			 rd.forward(request, response);
		  } else if (address == null) {
			 request.setAttribute("message", "Het invullen van uw woonadres is verplicht");
			 rd.forward(request, response);
		  } else if (bday == null) {
			 request.setAttribute("message", "Het invullen van uw geboortedatum is verplicht");
			 rd.forward(request, response);
		  } else if (postcode == null || postcode.equals("")) {
			 request.setAttribute("message", "Het invullen van uw postcode is verplicht");
			 rd.forward(request, response);
		  } else if (woonplaats == null || woonplaats.equals("")) {
			 request.setAttribute("message", "Het invullen van uw woonplaats is verplicht");
			 rd.forward(request, response);
		  } else {

			 Gebruiker newGebruiker = new Gebruiker();
			 newGebruiker.setUsername(name);
			 newGebruiker.setPassword(pass);
			 
			 Groep memberGroep = new Groep();
			 hibernateSession.load(memberGroep, 3);
			 newGebruiker.setGroep(memberGroep);

			 Klant newKlant = new Klant();
			 newKlant.setKlantAdres(address);
			 newKlant.setKlantNaam(surname);
			 // datum string omzetten naar Date object
			 Date date = new SimpleDateFormat("yyyy-MM-dd").parse(bday);
			 newKlant.setGeboorteDatum(date);
			 newKlant.setPostcode(postcode);
			 newKlant.setGebruiker(newGebruiker);
			 newKlant.setWoonplaats(woonplaats);

			 hibernateSession.save(newKlant);

			 rd.forward(request, response);
		  }

		  tx.commit();
	   } catch (HibernateException e) {
		  if (tx != null) {
			 tx.rollback();
		  }
		  e.printStackTrace();
	   } catch (ParseException ex) {
		  Logger.getLogger(AccountsRegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
	   } finally {
		  hibernateSession.close();
	   }

	   /*   try {

	    {
			 
	    PreparedStatement preparedStatement1 = connect.prepareStatement("INSERT INTO atd.gebruiker (gebruiker_username, gebruiker_password) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
	    // waardes invullen
	    preparedStatement1.setString(1, name);
	    preparedStatement1.setString(2, pass);
 
	    // query uitvoeren
	    preparedStatement1.executeUpdate();
                
	    // generated id ophalen
	    ResultSet tableKeys = preparedStatement1.getGeneratedKeys();
	    tableKeys.next();
	    int userID = tableKeys.getInt(1);
	    preparedStatement1.close();
 
	    PreparedStatement preparedStatement2 = connect.prepareStatement("INSERT INTO atd.klant (klant_gebruiker_id, klant_naam, klant_adres, klant_geboortedatum) VALUES (?, ?, ?, ?)");

	    //waardes invullen
	    preparedStatement2.setInt(1, userID);
	    preparedStatement2.setString(2, surname);
	    preparedStatement2.setString(3, address);

	    // datum string omzetten naar Date object
	    Date date = new SimpleDateFormat("dd-MM-yyyy").parse(bday);

	    //datum format omzetten om in de database te zetten
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateFormatted = sdf.format(date);

	    preparedStatement2.setString(4, dateFormatted);

	    // query uitvoeren
	    preparedStatement2.executeUpdate();

	    request.setAttribute("message", "U bent succesvol registreerd");
	    preparedStatement2.close(); 
	    connect.close();

	    rd = request.getRequestDispatcher("accounts/register.jsp");
	    rd.forward(request, response);
			  
	    }
	    } catch (Exception ex) {
	    Logger.getLogger(AccountsRegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
	    }*/
    }
}
