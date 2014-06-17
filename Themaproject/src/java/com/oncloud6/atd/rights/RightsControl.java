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
    public static void initRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        if(session.getAttribute("groupID") == null) {
            session.setAttribute("groupID", 4);
        }
    }
    
    public static boolean checkBoolean(String rightName, String reqValue, int userGroupId) {
        boolean returnCode = false;
        MySQLConnection DBConnection = new MySQLConnection();
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
    
    public static boolean checkGroup(String rightName, String reqValue, int userGroupId, int itemGroupId) {
        boolean returnCode = false;
        MySQLConnection DBConnection = new MySQLConnection();
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

                String selected = rights.defaultValue;
                if(rights.value != null) {
                    selected = rights.value;
                }
                
                if(selected.equals("other")) {
                    returnCode = true;
                }
                else if (selected.equals("own") && selected.equals(reqValue) && userGroupId == itemGroupId)
                {
                    returnCode = true;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(GroupsEditServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnCode;
    }
    
    public static String GetRightGroup(String rightName, int userGroupId) {
        String returnCode = "false";
        MySQLConnection DBConnection = new MySQLConnection();
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
