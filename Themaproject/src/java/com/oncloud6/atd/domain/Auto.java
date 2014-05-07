/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oncloud6.atd.domain;

/**
 *
 * @author Laura
 */
public class Auto {
    public String merk;
    public String type;
    public String kenteken;
    public String chassisNummer;
    
    public Auto(String mk, String tp, String kt, String cN){
        merk = mk;
        type = tp;
        kenteken = kt;
        chassisNummer = cN;
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
