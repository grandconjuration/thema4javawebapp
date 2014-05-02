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
public class Klant {
    public String klantNaam;
    public String klantAdres;
    public Double korting;
    public Date geboorteDatum;
    
    private Klant(String kN, String kA, Double kor, Date gD){
        klantNaam = kN;
        klantAdres = kA;
        korting = kor;
        geboorteDatum = gD;
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
