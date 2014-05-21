/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncloud6.atd.domain;

import java.util.ArrayList;
import java.util.Date;
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
@Table(name = "klant")
public class Klant {

    @Id
    @GeneratedValue
    @Column(name = "klant_id")
    public int id;
    
    @Column(name = "klant_naam")
    public String klantNaam;
    
    @Column(name = "klant_adres")
    public String klantAdres;
    
    @Column(name = "klant_korting")
    public Double korting;
    
    @Column(name = "klant_geboortedatum")
    public Date geboorteDatum;
    
    public ArrayList<Factuur> alleFacturen = new ArrayList<>();
    public ArrayList<Auto> alleAutos = new ArrayList<>();
    
    public Klant(){
	   
    }

    private Klant(String kN, String kA, Double kor, Date gD) {
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
