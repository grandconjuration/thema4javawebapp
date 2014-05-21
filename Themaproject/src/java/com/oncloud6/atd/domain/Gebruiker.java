/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncloud6.atd.domain;

import com.oncloud6.atd.domain.Groep;
import com.oncloud6.atd.domain.Klant;
import com.oncloud6.atd.domain.Monteur;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Laura, Simon
 */
@Entity
@Table(name = "gebruiker")
public class Gebruiker {

    @Id
    @GeneratedValue
    @Column(name = "gebruiker_id")
    private int id;
    
    @Column(name = "gebruiker_username")
    private String username;
    
    @Column(name = "gebruiker_password")
    private String password;
    
 //   private Klant deKlant;
 //   private Groep deGroep;
 //   private Monteur deMonteur;
    
    public Gebruiker(){}

    public Gebruiker(String un, String pw) {
	   username = un;
	   password = pw;

    }

  /*  public Monteur getDeMonteur() {
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
    }*/
    
    public int getId() {
	   return id;
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
