/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

/**
 *
 * @author Laura
 */
public class Rechten {
    public String naam;
    public String waarde;
    public String type;
    
    public Rechten(String nm, String wrd, String tp){
        naam = nm;
        waarde = wrd;
        type = tp;        
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String nm) {
        naam = nm;
    }

    public String getWaarde() {
        return waarde;
    }

    public void setWaarde(String wrd) {
        waarde = wrd;
    }

    public String getType() {
        return type;
    }

    public void setType(String tp) {
        type = tp;
    }
    
}
