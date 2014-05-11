/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oncloud6.atd.domain;

import java.util.ArrayList;

/**
 *
 * @author Laura
 */
public class Bedrijf {
    public String naam;
    public ArrayList<Onderdeel> alleOnderdelen;
    public ArrayList <Monteur> alleMonteurs;
    public ArrayList <Planning> allePlanningen;
    public ArrayList <Klant> alleKlanten;
    public ArrayList <Rechten> deRechten;
    public ArrayList <Groep> deGroepen;
    
    
    public Bedrijf(String nm){
        naam = nm;
    }

    public ArrayList<Onderdeel> getAlleOnderdelen() {
        return alleOnderdelen;
    }

    public void setAlleOnderdelen(ArrayList<Onderdeel> alleOnderdelen) {
        this.alleOnderdelen = alleOnderdelen;
    }

    public ArrayList<Monteur> getAlleMonteurs() {
        return alleMonteurs;
    }

    public void setAlleMonteurs(ArrayList<Monteur> alleMonteurs) {
        this.alleMonteurs = alleMonteurs;
    }

    public ArrayList<Planning> getAllePlanningen() {
        return allePlanningen;
    }

    public void setAllePlanningen(ArrayList<Planning> allePlanningen) {
        this.allePlanningen = allePlanningen;
    }

    public ArrayList<Klant> getAlleKlanten() {
        return alleKlanten;
    }

    public void setAlleKlanten(ArrayList<Klant> alleKlanten) {
        this.alleKlanten = alleKlanten;
    }

    public ArrayList<Rechten> getDeRechten() {
        return deRechten;
    }

    public void setDeRechten(ArrayList<Rechten> deRechten) {
        this.deRechten = deRechten;
    }

    public ArrayList<Groep> getDeGroepen() {
        return deGroepen;
    }

    public void setDeGroepen(ArrayList<Groep> deGroepen) {
        this.deGroepen = deGroepen;
    }


    
    
  public void setNaam(String nm){
      naam = nm;
  }
  
  public String getNaam(){
      return naam;
  }
    
}
