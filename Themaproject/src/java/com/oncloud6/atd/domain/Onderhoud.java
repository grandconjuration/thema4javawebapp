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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Laura, Simon
 */
@Entity
@Table(name = "onderhoud")
public class Onderhoud implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "onderhoud_id")
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(name = "onderhoud_datum")
    private Date datum;

    @Column(name = "onderhoud_beschrijving")
    private String beschrijving;

    @Column(name = "onderhoud_status")
    private String status;

    @Column(name = "onderhoud_manuur")
    private int manuur;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "onderhoud_auto_id", nullable = false)
    public Auto deAuto;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="onderhoud") 
    public List<GebruiktOnderdeel> alleGebruikteOnderdelen = new ArrayList<GebruiktOnderdeel>();

    public Onderhoud() {
    }

    public Onderhoud(Date dt, String besch, String st, int mu) {
	   datum = dt;
	   beschrijving = besch;
	   status = st;
	   manuur = mu;

    }
    
    public int getId() {
        return id;
    }

    public Auto getAuto() {
	   return deAuto;
    }

    public void setAuto(Auto newAuto) {
	   deAuto = newAuto;
    }

    public List<GebruiktOnderdeel> getGebruikteOnderdelen() {
	   return alleGebruikteOnderdelen;
    }

    public void setGebruikteOnderdelen(List<GebruiktOnderdeel> alleGebruikteOnderdelen) {
	   this.alleGebruikteOnderdelen = alleGebruikteOnderdelen;
    }

    public Date getDatum() {
	   return datum;
    }

    public void setDatum(Date dt) {
	   datum = dt;
    }

    public String getBeschrijving() {
	   return beschrijving;
    }

    public void setBeschrijving(String besch) {
	   beschrijving = besch;
    }

    public String getStatus() {
	   return status;
    }

    public void setStatus(String st) {
	   status = st;
    }

    public int getManuur() {
	   return manuur;
    }

    public void setManuur(int mu) {
	   manuur = mu;
    }

}
