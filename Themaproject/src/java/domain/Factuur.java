/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

import java.util.Date;

/**
 *
 * @author Laura
 */
public class Factuur {
    public int factuurNummer;
    public Date factuurDatum;
    public Klant deKlant;
    public String klantNaam;
    public String klantAdres;
    public Double factuurKorting;
    public Double subTotaalBedrag;
    public Double btwBedrag;
    public Double totaalBedrag;
    
    public Factuur(int fN, Date fD,Double fK, double sTB, double btwB, double tB){
        factuurNummer = fN;
        factuurDatum = fD;
       // klantNaam = deKlant.getKlantNaam();
        //klantAdres = deKlant.getKlantAdres();
        factuurKorting = fK;
        subTotaalBedrag = sTB;
        btwBedrag = btwB;
        totaalBedrag = tB;
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
        return deKlant;
    }

    public void setDeKlant(Klant dezeKlant) {
        deKlant = dezeKlant;
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
        return totaalBedrag;
    }

    public void setTotaalBedrag(Double tB) {
        totaalBedrag = tB;
    }
    
    
}
