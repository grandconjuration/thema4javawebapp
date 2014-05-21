/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oncloud6.atd.domain;

import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Laura
 */
@Entity
@Table(name = "groepen")
public class Groep {
    @Id
    @GeneratedValue
    @Column(name = "groepen_id")
    public int id;
    
    @Column(name = "groepen_naam")
    public String groepNaam;
    
    public Groep(){}
      
    public Groep(String gN){
        groepNaam = gN;
     }
    
    public int getGroepId(){
	   return id;
    }
    
    public void setGroepId(int i){
	   id = i;
    }
     
    public String getGroepNaam() {
        return groepNaam;
    }

    public void setGroepNaam(String gN) {
        groepNaam = gN;
    }
    
    
}
