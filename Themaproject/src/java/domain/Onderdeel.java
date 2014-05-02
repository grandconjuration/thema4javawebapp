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
public class Onderdeel {
    public String artikelNummer;
    
    public Onderdeel(String aN){
        artikelNummer = aN;
    }

    public String getArtikelNummer() {
        return artikelNummer;
    }

    public void setArtikelNummer(String aN) {
        artikelNummer = aN;
    }
    
    
}
