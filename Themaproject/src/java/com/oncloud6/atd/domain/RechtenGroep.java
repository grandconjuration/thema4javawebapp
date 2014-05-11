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
public class RechtenGroep {
    public String waarde;
    public Rechten hetRecht;
    
    public RechtenGroep(String wrd){
        waarde = wrd;
    }

    public Rechten getHetRecht() {
        return hetRecht;
    }

    public void setHetRecht(Rechten nweRechten) {
        hetRecht = nweRechten;
    }
    
    
     public String getWaarde() {
        return waarde;
    }

    public void setWaarde(String wrd) {
        waarde = wrd;
    }
    
}
