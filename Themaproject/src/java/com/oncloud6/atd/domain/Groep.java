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
public class Groep {
    public String groepNaam;
    public ArrayList<RechtenGroep> deRechten;
    
    public Groep(String gN){
        groepNaam = gN;
    }

    public ArrayList<RechtenGroep> getDeRechten() {
        return deRechten;
    }

    public void setDeRechten(ArrayList<RechtenGroep> deRechten) {
        this.deRechten = deRechten;
    }
    
    public String getGroepNaam() {
        return groepNaam;
    }

    public void setGroepNaam(String gN) {
        groepNaam = gN;
    }
    
    
}
