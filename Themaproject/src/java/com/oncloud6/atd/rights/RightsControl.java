/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oncloud6.atd.rights;

/**
 *
 * @author Jelle
 */
public class RightsControl {
    public static boolean checkBoolean(String rightName, String reqValue, int userGroupId) {
        return true;
    }
    
    public static boolean checkGroup(String rightName, String reqValue, int userGroupId, int itemGroupId) {
        return true;
    }
    
    public static String GetRightGroup(String rightName, int userGroupId) {
        return "own";
    }
}
