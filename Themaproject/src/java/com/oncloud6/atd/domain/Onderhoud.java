/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oncloud6.atd.domain;

import java.util.Date;

/**
 *
 * @author Laura
 */
public class Onderhoud {
    public Date datum;
    public String beschrijving;
    public String status;
    public int manuur;
    
    public Onderhoud(Date dt, String besch, String st, int mu){
        datum = dt;
        beschrijving = besch;
        status = st;
        manuur = mu;
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
