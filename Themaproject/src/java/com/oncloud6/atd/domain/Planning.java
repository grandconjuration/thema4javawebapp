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
public class Planning {
    public Date datumStart;
    public Date datumEind;
    public Onderhoud hetOnderhoud;
    public Monteur deMonteur;
    
public Planning(Date dS, Date dE){
    datumStart = dS;
    datumEind = dE;
   
}

    public Onderhoud getHetOnderhoud() {
        return hetOnderhoud;
    }

    public void setHetOnderhoud(Onderhoud nweO) {
        hetOnderhoud = nweO;
    }

    public Monteur getDeMonteur() {
        return deMonteur;
    }

    public void setDeMonteur(Monteur nweM) {
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
