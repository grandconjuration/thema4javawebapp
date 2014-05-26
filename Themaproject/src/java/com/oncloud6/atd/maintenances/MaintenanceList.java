/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oncloud6.atd.maintenances;

import java.util.Date;

/**
 *
 * @author Laura
 */
public class MaintenanceList {
    public Date datum;
    public String beschrijving;
    public String status;
    public String kenteken;
    public String merk;
    public String type;
    public int manuur;
    public int onderhoudId;
    public int autoId;
    public int bedrijfsId;
    
   
    
    public MaintenanceList(){
        
    }

    public MaintenanceList(Date dt, String bs, String st, int mu, int oI, int aI, int bI){
        datum = dt;
        beschrijving = bs;
        status = st;
        manuur = mu;
        onderhoudId = oI;
        autoId = aI;
        bedrijfsId = bI;
    }

    

}


