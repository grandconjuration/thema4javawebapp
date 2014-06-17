/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncloud6.atd.maintenances;

import com.oncloud6.atd.maintenances.MaintenanceList;
import com.oncloud6.atd.maintenances.MaintenanceList;
import com.oncloud6.atd.mysql.MySQLConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        // Controleren of het Customer id veld is ingevuld
        boolean idSet = false;
        if (request.getParameter("cid") == null || request.getParameter("cid").equals("")) {
            idSet = false;
        } else {
            idSet = true;
        }

        // get dbconnection
        MySQLConnection DBConnection = new MySQLConnection();
        // try it out
        try {
            Connection connect = DBConnection.getConnection();

            // prepare query
            PreparedStatement preparedStatement;
            
            // Controleren of idSet geset is
            if (!idSet) {
                preparedStatement = connect.prepareStatement("SELECT * FROM atd.onderhoud INNER JOIN auto ON onderhoud.onderhoud_auto_id = auto.auto_id");
            } else {
                preparedStatement = connect.prepareStatement("SELECT * FROM atd.onderhoud INNER JOIN auto ON onderhoud.onderhoud_auto_id = auto.auto_id AND auto.auto_klant_id =?");
                int CustomerID = Integer.parseInt(request.getParameter("cid"));
                preparedStatement.setInt(1, CustomerID);
            }
            // voer de query uit en get result
            ResultSet result = preparedStatement.executeQuery();
            
            

            ArrayList<MaintenanceList> list = new ArrayList<MaintenanceList>();

            while (result.next()) {

                // Waarden ophalen uit de database en plaatsen in de list
                MaintenanceList maintenance = new MaintenanceList();
                maintenance.onderhoudId = result.getInt("onderhoud_id");
                maintenance.bedrijfsId = result.getInt("onderhoud_bedrijf_id");
                maintenance.autoId = result.getInt("onderhoud_auto_id");
                maintenance.merk = result.getString("auto_merk");
                maintenance.type = result.getString("auto_type");
                maintenance.kenteken = result.getString("auto_kenteken");
                maintenance.datum = result.getDate("onderhoud_datum");
                maintenance.beschrijving = result.getString("onderhoud_beschrijving");
                maintenance.status = result.getString("onderhoud_status");
                maintenance.manuur = result.getInt("onderhoud_manuur");

                // toevoegen aan de arraylist
                list.add(maintenance);

            }
            // request variabelen setten
            request.setAttribute("list", list);
            // afsluiten 
            preparedStatement.close();
            
            preparedStatement = connect.prepareStatement("select * from klant order by klant_naam asc");
            result = preparedStatement.executeQuery();

            ArrayList<DropdownValues> values = new ArrayList<DropdownValues>();
            DropdownValues value;

            value = new DropdownValues();
            value.key = "";
            value.value = "";
            value.selected = false;
            values.add(value);
            
            while (result.next()) {
                value = new DropdownValues();
                value.key = result.getString("klant_id");
                value.value = result.getString("klant_naam");
                value.selected = false;
                values.add(value);
            } 
            request.setAttribute("klantlist", values);
            preparedStatement.close();

            connect.close();

            rd = request.getRequestDispatcher("maintenances/home.jsp");
            rd.forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(MaintenancesIndexServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
