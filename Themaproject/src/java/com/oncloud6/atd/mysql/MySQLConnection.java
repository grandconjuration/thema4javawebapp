/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncloud6.atd.mysql;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author Simon Whiteley
 */
public class MySQLConnection {
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;

  public Connection getConnection() throws Exception {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      connect = DriverManager.getConnection("jdbc:mysql://pma.jelleluteijn.com:3306/atd?user=atd&password=Geheim1@");

     return connect;

    
    } catch (Exception e) {
      throw e;
    }

  }

} 