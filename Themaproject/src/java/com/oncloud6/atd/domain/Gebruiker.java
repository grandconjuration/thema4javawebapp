/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oncloud6.atd.domain;

/**
 *
 * @author Laura
 */
public class Gebruiker {
    private String username;
    private String password;
    private Klant deKlant;
    private Groep deGroep;
    private Monteur deMonteur;

    
    
    public Gebruiker(String un, String pw){
       username = un;
       password = pw;
    }

    public Monteur getDeMonteur() {
        return deMonteur;
    }

    public void setDeMonteur(Monteur deMonteur) {
        this.deMonteur = deMonteur;
    }

    
    public Groep getDeGroep() {
        return deGroep;
    }

    public void setDeGroep(Groep nweG) {
        deGroep = nweG;
    }
        
    
    public Klant getDeKlant() {
        return deKlant;
    }

    public void setDeKlant(Klant nweK) {
        deKlant = nweK;
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
