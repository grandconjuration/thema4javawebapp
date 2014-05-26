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
@Table(name = "monteur")
public class Monteur implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "monteur_id")
    private int id;

    @Column(name = "monteur_naam")
    public String naam;

    public Monteur() {
    }

    public Monteur(String nm) {
        naam = nm;
    }

    public int getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String nm) {
        naam = nm;
    }

}
