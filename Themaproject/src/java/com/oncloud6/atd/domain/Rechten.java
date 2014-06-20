/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oncloud6.atd.domain;

import java.io.Serializable;
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
@Table(name = "rechten")
public class Rechten implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "rechten_id")
    private int id;
    
    @Column(name = "rechten_key")
    private String naam;
    
    @Column(name = "rechten_value")
    private String waarde;
    
    @Column(name = "rechten_type")
    private String type;
    
    public Rechten() {
	   
    }
    
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
