/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oncloud6.atd.domain;

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
@Table(name = "factuur_item")
public class FactuurItem {
    @Id
    @GeneratedValue
    @Column(name = "factuur_item_id")
    private int id;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "factuur_item_factuur_id")
    private Factuur factuur;
    
    @Column(name = "factuur_item_naam")
    private String factuurItemNaam;
    
    @Column(name = "factuur_item_prijs")
    private Double factuurItemPrijs;
    
    @Column(name = "factuur_item_hoeveelheid")
    private int factuurItemHoeveelheid;
    
    @Column(name = "factuur_item_subtotaal")
    private Double factuurItemSubtotaal;
    
    public FactuurItem() {
	   
    }
    
    public FactuurItem(String fIN, Double fIP, int fIH, Double fIS){
        factuurItemNaam = fIN;
        factuurItemPrijs = fIP;
        factuurItemHoeveelheid = fIH;
        factuurItemSubtotaal = fIS;
    }
    
    public void setFactuur(Factuur fact){
	   factuur = fact;
    }
    
    public Factuur getFactuur(){
	   return factuur;
    }

    public String getFactuurItemNaam() {
        return factuurItemNaam;
    }

    public void setFactuurItemNaam(String fIN) {
        factuurItemNaam = fIN;
    }

    public Double getFactuurItemPrijs() {
        return factuurItemPrijs;
    }

    public void setFactuurItemPrijs(Double fIP) {
        factuurItemPrijs = fIP;
    }

    public int getFactuurItemHoeveelheid() {
        return factuurItemHoeveelheid;
    }

    public void setFactuurItemHoeveelheid(int fIH) {
        factuurItemHoeveelheid = fIH;
    }

    public Double getFactuurItemSubtotaal() {
        return factuurItemSubtotaal;
    }

    public void setFactuurItemSubtotaal(Double fIS) {
        factuurItemSubtotaal = fIS;
    }
    
    
    
}
