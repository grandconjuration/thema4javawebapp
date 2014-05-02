/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

/**
 *
 * @author Laura
 */
public class Gebruiker {
    private String username;
    private String password;
    
    public Gebruiker(String un, String pw){
       username = un;
       password = pw;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String un) {
        username = un;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pw) {
        password = pw;
    }
    
}
