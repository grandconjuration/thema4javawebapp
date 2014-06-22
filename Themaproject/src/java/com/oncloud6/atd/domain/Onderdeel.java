/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncloud6.atd.domain;

import java.io.Serializable;
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
@Table(name = "onderdeel")
public class Onderdeel implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "onderdeel_id")
    private int id;
    
    @Column(name = "onderdeel_naam")
    private String naam;
    
    @Column(name = "onderdeel_hoeveelheid")
    private int hoeveelheid;
    
    @Column(name = "onderdeel_prijs")
    private double prijs;

    public Onderdeel() {
    }

    public Onderdeel(String oN, int hoe, double prijz) {
	//   artikelNummer = aN;
	   naam = oN;
	   hoeveelheid = hoe;
	   prijs = prijz;
    }

    public String getNaam() {
        return naam;
    }
    
    public void setNaam(String nm){
        naam = nm;
    }
    
    public int getHoeveelheid() {
        return hoeveelheid;
    }
    
    public void setHoeveelheid(int hoe){
        hoeveelheid = hoe;
    }
    
    public double getPrijs() {
        return prijs;
    }
    
    public void setPrijs(double pr) {
        prijs = pr;
    }
}
