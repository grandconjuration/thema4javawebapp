/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oncloud6.atd.domain;

import java.io.Serializable;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Laura, Simon
 */
@Entity
@Table(name = "onderhoud_onderdeel")
public class GebruiktOnderdeel implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "onderhoud_onderdeel_hoeveelheid")
    private int hoeveelheid;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "onderdeel_id")
    private Onderdeel onderdeel;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "onderhoud_id")        
    private Onderhoud onderhoud;

    public GebruiktOnderdeel() {
    }

    public GebruiktOnderdeel(int hh, Onderdeel hetOnderd, Onderhoud onderh) {
        hoeveelheid = hh;
        onderdeel = hetOnderd;
        onderhoud = onderh;
    }
    
    public void setOnderhoud(Onderhoud onderh) {
        onderhoud = onderh;
    }
    
    public Onderhoud getOnderhoud() {
        return onderhoud;
    }

    public Onderdeel getOnderdeel() {
        return onderdeel;
    }

    public void setOnderdeel(Onderdeel onderd) {
        onderdeel = onderd;
    }

    public int getHoeveelheid() {
        return hoeveelheid;
    }

    public void setHoeveelheid(int hh) {
        hoeveelheid = hh;
    }

}
