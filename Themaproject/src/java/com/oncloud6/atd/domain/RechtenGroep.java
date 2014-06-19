/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oncloud6.atd.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Laura, Simon
 */
@Entity
@Table(name = "rechten_groep")
public class RechtenGroep {
    
    @Column(name = "rechten_groepen_value")
    private String waarde;
    
    @JoinColumn(name = "rechten_id")
    private Rechten hetRecht;
    
    @JoinColumn(name = "groepen_id")
    private Groep groep;
    
    public RechtenGroep(String wrd){
        waarde = wrd;
        
    }
    
    public Groep getGroup() {
	   return groep;
    }
    
    public void setGroep(Groep group){
	   groep = group;
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
