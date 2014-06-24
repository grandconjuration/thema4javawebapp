/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oncloud6.atd.rights;

import com.oncloud6.atd.mysql.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jelle
 */
public class RightsControl {
    private static int defaultGroup = 4;
    
    public static boolean checkBoolean(String rightName, String reqValue, HttpSession session) {
        boolean returnCode = false;
        MySQLConnection DBConnection = new MySQLConnection();
        int userGroupId = defaultGroup;
        if(session.getAttribute("groupID") != null) {
            userGroupId = Integer.parseInt(session.getAttribute("groupID").toString());
        }
        
        try {
            Connection connect = DBConnection.getConnection();
            PreparedStatement preparedStatement = connect.prepareStatement("SELECT atd.rechten.rechten_id as id_default, atd.rechten.rechten_key as recht_naam, atd.rechten.rechten_type as recht_type, atd.rechten.rechten_value as value_default, atd.rechten_groepen.rechten_id as id_group, atd.rechten_groepen.rechten_groepen_value as value_group FROM atd.rechten LEFT OUTER JOIN atd.rechten_groepen ON atd.rechten.rechten_id = atd.rechten_groepen.rechten_id AND atd.rechten_groepen.groepen_id = ? WHERE atd.rechten.rechten_key = ? AND atd.rechten.rechten_type = 'boolean' ORDER BY atd.rechten.rechten_key ASC;");

            preparedStatement.setInt(1, userGroupId);
            preparedStatement.setString(2, rightName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                preparedStatement.close();
                connect.close();
            }else{
                RightsList rights = new RightsList();
                rights.id = resultSet.getString("id_default");
                rights.naam = resultSet.getString("recht_naam");
                rights.type = resultSet.getString("recht_type");
                rights.defaultValue = resultSet.getString("value_default");
                rights.value = resultSet.getString("value_group");
                preparedStatement.close();
                connect.close();

                System.out.println(rights.id + "-" + rights.naam + "-" + rights.type + "-" + rights.defaultValue + "-" + rights.value);
                
                String selected = rights.defaultValue;
                if(rights.value != null) {
                    selected = rights.value;
                }
                
                if(selected.equals(reqValue)) {
                    returnCode = true;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(GroupsEditServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnCode;
    }
    
    public static boolean checkGroup(String rightName, String reqValue, HttpSession session, int itemUserId) {
        boolean returnCode = false;
        MySQLConnection DBConnection = new MySQLConnection();
        int userGroupId = defaultGroup;
        int userUserId = 0;
        if(session.getAttribute("groupID") != null) {
            userGroupId = Integer.parseInt(session.getAttribute("groupID").toString());
        }
        if(session.getAttribute("userID") != null) {
            userUserId = Integer.parseInt(session.getAttribute("userID").toString());
        }
        try {
            Connection connect = DBConnection.getConnection();
            PreparedStatement preparedStatement = connect.prepareStatement("SELECT atd.rechten.rechten_id as id_default, atd.rechten.rechten_key as recht_naam, atd.rechten.rechten_type as recht_type, atd.rechten.rechten_value as value_default, atd.rechten_groepen.rechten_id as id_group, atd.rechten_groepen.rechten_groepen_value as value_group FROM atd.rechten LEFT OUTER JOIN atd.rechten_groepen ON atd.rechten.rechten_id = atd.rechten_groepen.rechten_id AND atd.rechten_groepen.groepen_id = ? WHERE atd.rechten.rechten_key = ? AND atd.rechten.rechten_type = 'group' ORDER BY atd.rechten.rechten_key ASC;");

            preparedStatement.setInt(1, userGroupId);
            preparedStatement.setString(2, rightName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                preparedStatement.close();
                connect.close();
            }else{
                RightsList rights = new RightsList();
                rights.id = resultSet.getString("id_default");
                rights.naam = resultSet.getString("recht_naam");
                rights.type = resultSet.getString("recht_type");
                rights.defaultValue = resultSet.getString("value_default");
                rights.value = resultSet.getString("value_group");
                preparedStatement.close();
                connect.close();

                System.out.println(rights.id + "-" + rights.naam + "-" + rights.type + "-" + rights.defaultValue + "-" + rights.value);

                String selected = rights.defaultValue;
                if(rights.value != null) {
                    selected = rights.value;
                }
                
                if(selected.equals("other")) {
                    returnCode = true;
                }
                else if (selected.equals("own") && selected.equals(reqValue) && userUserId == itemUserId)
                {
                    returnCode = true;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(GroupsEditServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnCode;
    }
    
    public static String GetRightGroup(String rightName, HttpSession session) {
        String returnCode = "false";
        MySQLConnection DBConnection = new MySQLConnection();
        int userGroupId = defaultGroup;
        if(session.getAttribute("groupID") != null) {
            userGroupId = Integer.parseInt(session.getAttribute("groupID").toString());
        }
        try {
            Connection connect = DBConnection.getConnection();
            PreparedStatement preparedStatement = connect.prepareStatement("SELECT atd.rechten.rechten_id as id_default, atd.rechten.rechten_key as recht_naam, atd.rechten.rechten_type as recht_type, atd.rechten.rechten_value as value_default, atd.rechten_groepen.rechten_id as id_group, atd.rechten_groepen.rechten_groepen_value as value_group FROM atd.rechten LEFT OUTER JOIN atd.rechten_groepen ON atd.rechten.rechten_id = atd.rechten_groepen.rechten_id AND atd.rechten_groepen.groepen_id = ? WHERE atd.rechten.rechten_key = ? AND atd.rechten.rechten_type = 'group' ORDER BY atd.rechten.rechten_key ASC;");

            preparedStatement.setInt(1, userGroupId);
            preparedStatement.setString(2, rightName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                preparedStatement.close();
                connect.close();
            }else{
                RightsList rights = new RightsList();
                rights.id = resultSet.getString("id_default");
                rights.naam = resultSet.getString("recht_naam");
                rights.type = resultSet.getString("recht_type");
                rights.defaultValue = resultSet.getString("value_default");
                rights.value = resultSet.getString("value_group");
                preparedStatement.close();
                connect.close();

                System.out.println(rights.id + "-" + rights.naam + "-" + rights.type + "-" + rights.defaultValue + "-" + rights.value);

                returnCode = rights.defaultValue;
                if(rights.value != null) {
                    returnCode = rights.value;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(GroupsEditServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnCode;
    }
}
