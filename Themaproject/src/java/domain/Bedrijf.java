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
public class Bedrijf {
    public String naam;
    
    public Bedrijf(String nm){
        naam = nm;
    }
    
  public void setNaam(String nm){
      naam = nm;
  }
  
  public String getNaam(){
      return naam;
  }
    
}
