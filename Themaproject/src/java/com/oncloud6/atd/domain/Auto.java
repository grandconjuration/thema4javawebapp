/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncloud6.atd.domain;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Laura, Simon
 */
@Entity
@Table(name = "auto")
public class Auto implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "auto_id")
    private int id;
    
    @Column(name = "auto_merk")
    public String merk;
    
    @Column(name = "auto_type")
    public String type;
    
    @Column(name = "auto_kenteken")
    public String kenteken;
    
    @Column(name = "auto_chassis_nummer")
    public String chassisNummer;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "auto_klant_id")
    public Klant klant;

    public Auto() {
    }

    public Auto(String mk, String tp, String kt, String cN) {
	   merk = mk;
	   type = tp;
	   kenteken = kt;
	   chassisNummer = cN;
    }
    
    public void setKlant(Klant kl){
        klant = kl;
    }
    
    public Klant getKlant() {
        return klant;
    }

    public String getMerk() {
	   return merk;
    }

    public void setMerk(String mk) {
	   merk = mk;
    }

    public String getType() {
	   return type;
    }

    public void setType(String tp) {
	   type = tp;
    }

    public String getKenteken() {
	   return kenteken;
    }

    public void setKenteken(String kt) {
	   kenteken = kt;
    }

    public String getChassisNummer() {
	   return chassisNummer;
    }

    public void setChassisNummer(String cN) {
	   chassisNummer = cN;
    }

}
