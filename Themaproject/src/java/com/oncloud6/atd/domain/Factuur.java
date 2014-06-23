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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Laura, Simon
 */
@Entity
@Table(name = "factuur")
public class Factuur implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "factuur_id")
    private int id;

    @Column(name = "factuur_nummer")
    private int factuurNummer;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "factuur_datum")
    private Date factuurDatum;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "factuur_klant_id")
    private Klant klant;

    @Column(name = "factuur_klant_naam")
    private String klantNaam;

    @Column(name = "factuur_klant_adres")
    private String klantAdres;

    @Column(name = "factuur_korting")
    private Double factuurKorting;

    @Column(name = "factuur_bedrag")
    private Double subTotaalBedrag;

    @Column(name = "factuur_bedrag_btw")
    private Double btwBedrag;

    @Column(name = "factuur_bedrag_totaal")
    private Double totaalBedrag;
    
    @Column(name = "factuur_secret")
    private String secret;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "factuur")
    private List<FactuurItem> deFactuurItems = new ArrayList<FactuurItem>();
   
    @Column(name = "factuur_btw_percentage")
    private Double btwpercent = 1.21;

    public Factuur() {
    }

    public Factuur(int fN, Date fD, Double fK, double sTB, double btwB, double tB, Klant nweK, String kN, String kA) {
        factuurNummer = fN;
        factuurDatum = fD;
        factuurKorting = fK;
        subTotaalBedrag = sTB;
        btwBedrag = btwB;
        totaalBedrag = tB;
        klant = nweK;
        klantNaam = kN;
        klantAdres = kA;

    }
    
    public void setSecret(String s){
        secret = s;
    }
    
    public String getSecret() {
        return secret;
    }

    public List<FactuurItem> getDeFactuurItems() {
        return deFactuurItems;
    }

    public void setDeFactuurItems(List<FactuurItem> deFactuurItems) {
        this.deFactuurItems = deFactuurItems;
    }

    public int getFactuurNummer() {
        return factuurNummer;
    }

    public void setFactuurNummer(int fN) {
        factuurNummer = fN;
    }

    public Date getFactuurDatum() {
        return factuurDatum;
    }

    public void setFactuurDatum(Date fD) {
        factuurDatum = fD;
    }

    public Klant getDeKlant() {
        return klant;
    }

    public void setDeKlant(Klant dezeKlant) {
        klant = dezeKlant;
    }

    public String getKlantNaam() {
        return klantNaam;
    }

    public void setKlantNaam(Klant deKlant) {
        klantNaam = deKlant.getKlantNaam();
    }

    public String getKlantAdres() {
        return klantAdres;
    }

    public void setKlantAdres(Klant deKlant) {
        klantAdres = deKlant.getKlantAdres();
    }

    public Double getFactuurKorting() {
        return factuurKorting;
    }

    public void setFactuurKorting(Double fK) {
        factuurKorting = fK;
    }

    public Double getSubTotaalBedrag() {
        berekenSubTotaalBedrag();
        return subTotaalBedrag;
    }

    public void setSubTotaalBedrag(Double sTB) {
        subTotaalBedrag = sTB;
    }

    public Double getBtwBedrag() {
        return btwBedrag;
    }

    public void setBtwBedrag(Double btwB) {
        btwBedrag = btwB;
    }

    public Double getTotaalBedrag() {
        berekenTotaalBedrag();
        return totaalBedrag;
    }

    public void setTotaalBedrag(Double tB) {
        totaalBedrag = tB;
    }
    
    public Double getBtwPercentage(){
	   return btwpercent;
    }

    public void berekenSubTotaalBedrag() {
        List<FactuurItem> factuurItems = getDeFactuurItems();
        double bedrag = 0;
        for (FactuurItem fi : factuurItems) {
            fi.berekenSubtotaal();
            double prijs = fi.getFactuurItemSubtotaal();
            bedrag = bedrag + prijs;
        }
	   setSubTotaalBedrag(bedrag);
    }

    public void berekenTotaalBedrag() {
        berekenSubTotaalBedrag();
        Double bedrag = getSubTotaalBedrag();
        Double totaal = bedrag * getBtwPercentage();
	   this.setBtwBedrag(totaal - bedrag);
	   setTotaalBedrag(totaal);
	   
    }

}
