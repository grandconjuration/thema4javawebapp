/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncloud6.atd.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Laura, Simon
 */
@Entity
@Table(name = "planning")
public class Planning implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "planning_id")
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "planning_datum_start")
    private Date datumStart;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "planning_datum_eind")
    private Date datumEind;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "planning_onderhoud_id")
    private Onderhoud hetOnderhoud;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "planning_monteur_id")
    private Monteur deMonteur;

    public Planning() {
    }

    public Planning(Date dS, Date dE) {
        datumStart = dS;
        datumEind = dE;

    }

    public Onderhoud getOnderhoud() {
        return hetOnderhoud;
    }

    public void setOnderhoud(Onderhoud nweO) {
        hetOnderhoud = nweO;
    }

    public Monteur getMonteur() {
        return deMonteur;
    }

    public void setMonteur(Monteur nweM) {
        deMonteur = nweM;
    }

    public Date getDatumStart() {
        return datumStart;
    }

    public void setDatumStart(Date dS) {
        datumStart = dS;
    }

    public Date getDatumEind() {
        return datumEind;
    }

    public void setDatumEind(Date dE) {
        datumEind = dE;
    }

}
