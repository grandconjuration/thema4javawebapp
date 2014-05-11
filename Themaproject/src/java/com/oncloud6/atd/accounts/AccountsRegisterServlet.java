/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oncloud6.atd.accounts;

import com.oncloud6.atd.customers.CustomersAddServlet;
import com.oncloud6.atd.mysql.MySQLConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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

/**
 *
 * @author Niels
 */
@WebServlet(name = "AccountsRegisterServlet", urlPatterns = {"/accountsregister"})
public class AccountsRegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        
        boolean registerSuccess = false;
        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        String chckpass = request.getParameter("chckpassword");
        String email = request.getParameter("email");
        if(name != null || pass != null) {
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con = DriverManager.getConnection(DatabaseConnectionString.connectionUrl);
                
                preparedStatement = con.prepareStatement("SELECT * from dbo.login where username = ?");
                preparedStatement.setString(1, name);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.isBeforeFirst()) {
                    preparedStatement = con.prepareStatement("insert into  dbo.login (username, password, email) values (?, ?, ?)");
                    // "myuser, webpage, datum, summary, COMMENTS from FEEDBACK.COMMENTS");
                    // parameters start with 1
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, pass);
                    preparedStatement.setString(3, email);
                    preparedStatement.executeUpdate();
                    registerSuccess = true;
                }else{
                    request.setAttribute("msgs", "Username bestaad al!");
                    registerSuccess = false;
                }
            }
            catch(SQLException e) {
                request.setAttribute("msgs", "fout met database");
            }
            catch(ClassNotFoundException e) {
                request.setAttribute("msgs", "kan jdbc niet vinden");
            }
        }
        
        request.setAttribute("name", name);
        
        if(registerSuccess) {
            response.sendRedirect("accountslogin");
        }else{
            rd = request.getRequestDispatcher("accounts/register.jsp");
            rd.forward(request, response);
        }
    }
 }
