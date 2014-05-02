/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

/**
 *
 * @author Laura
 */
public class FactuurItem {
    public String factuurItemNaam;
    public Double factuurItemPrijs;
    public int factuurItemHoeveelheid;
    
    public FactuurItem(String fIN, Double fIP, int fIH){
        factuurItemNaam = fIN;
        factuurItemPrijs = fIP;
        factuurItemHoeveelheid = fIH;
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
    
    
    
}
