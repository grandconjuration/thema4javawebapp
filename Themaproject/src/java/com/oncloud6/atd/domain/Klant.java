/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oncloud6.atd.domain;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Laura
 */
public class Klant {
    public String klantNaam;
    public String klantAdres;
    public Double korting;
    public Date geboorteDatum;
    public ArrayList <Factuur> alleFacturen;
    public ArrayList<Auto> alleAutos;

    
    private Klant(String kN, String kA, Double kor, Date gD){
        klantNaam = kN;
        klantAdres = kA;
        korting = kor;
        geboorteDatum = gD;
    }

    public ArrayList<Factuur> getAlleFacturen() {
        return alleFacturen;
    }

    public void setAlleFacturen(ArrayList<Factuur> alleFacturen) {
        this.alleFacturen = alleFacturen;
    }

    public ArrayList<Auto> getAlleAutos() {
        return alleAutos;
    }

    public void setAlleAutos(ArrayList<Auto> alleAutos) {
        this.alleAutos = alleAutos;
    }
  

    
    public String getKlantNaam() {
        return klantNaam;
    }

    public void setKlantNaam(String kN) {
        klantNaam = kN;
    }

    public String getKlantAdres() {
        return klantAdres;
    }

    public void setKlantAdres(String kA) {
        klantAdres = kA;
    }

    public Double getKorting() {
        return korting;
    }

    public void setKorting(Double kor) {
        korting = kor;
    }

    public Date getGeboorteDatum() {
        return geboorteDatum;
    }

    public void setGeboorteDatum(Date gD) {
        geboorteDatum = gD;
    }
    
    
    
}
