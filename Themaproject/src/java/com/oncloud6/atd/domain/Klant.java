/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncloud6.atd.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Laura, Simon
 */
@Entity
@Table(name = "klant")
public class Klant implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "klant_id")
    private int id;

    @Column(name = "klant_naam")
    private String klantNaam;

    @Column(name = "klant_adres")
    private String klantAdres;
    
    @Column(name = "klant_postcode")
    private String klantPostcode;

    @Column(name = "klant_korting")
    private Double korting;

    @Column(name = "klant_geboortedatum")
    private Date geboorteDatum;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "klant")
    private List<Factuur> alleFacturen = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "klant")
    private List<Auto> alleAutos = new ArrayList<>();
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "klant_gebruiker_id")
    private Gebruiker deGebruiker;
    
    public Klant() {
    }

    private Klant(String kN, String kA, Double kor, Date gD) {
        klantNaam = kN;
        klantAdres = kA;
        korting = kor;
        geboorteDatum = gD;

    }
    
    public String getPostcode(){
        return klantPostcode;
    }
    
    public void setPostcode(String pc){
        klantPostcode = pc;
    }
    
    public Gebruiker getGebruiker(){
        return deGebruiker;
    }
    
    public void setGebruiker(Gebruiker gebr){
        deGebruiker = gebr;
    }
    
    public int getId(){
        return id;
    }

    public List<Factuur> getAlleFacturen() {
        return alleFacturen;
    }

    public void setAlleFacturen(List<Factuur> alleFacturen) {
        this.alleFacturen = alleFacturen;
    }

    public List<Auto> getAlleAutos() {
        return alleAutos;
    }

    public void setAlleAutos(List<Auto> alleAutos) {
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
