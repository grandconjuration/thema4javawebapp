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
public class Voorraad {
    public String naam;
    public int hoeveelheid;
    public Double prijs;
    
    public Voorraad(String nm, int hh, Double p){
        naam = nm;
        hoeveelheid = hh;
        prijs = p;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String nm) {
        naam = nm;
    }

    public int getHoeveelheid() {
        return hoeveelheid;
    }

    public void setHoeveelheid(int hh) {
        hoeveelheid = hh;
    }

    public Double getPrijs() {
        return prijs;
    }

    public void setPrijs(Double p) {
        prijs = p;
    }
    
    
    
}
