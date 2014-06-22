/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oncloud6.atd.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Laura
 */
public class KlantTest {
    
    public KlantTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPostcode method, of class Klant.
     */
    @Test
    public void testGetPostcode() {
        System.out.println("getPostcode");
        Klant instance = new Klant();
        instance.setPostcode("1234AB");
        String expResult = "1234AB";
        String result = instance.getPostcode();
        assertEquals(expResult, result);
        System.out.println(expResult + " = " + result );
                 
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setPostcode method, of class Klant.
     */
    @Test
    public void testSetPostcode() {
        System.out.println("setPostcode");
        String pc = "1234AB";
        Klant instance = new Klant();
        instance.setPostcode(pc);
        String postcode = instance.getPostcode();
        assertEquals(postcode, pc);
        System.out.println(postcode + " = " + pc );
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setWoonplaats method, of class Klant.
     */
    @Test
    public void testSetWoonplaats() {
        System.out.println("setWoonplaats");
        String woon = "Utrecht";
        Klant instance = new Klant();
        instance.setWoonplaats(woon);
        String woonplaats = "Utrecht";
        assertEquals(woon, woonplaats);
        System.out.println(woon + " = " + woonplaats);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of getWoonplaats method, of class Klant.
     */
    @Test
    public void testGetWoonplaats() {
        System.out.println("getWoonplaats");
        Klant instance = new Klant();
        instance.setWoonplaats("Utrecht");
        String expResult = "Utrecht";
        String result = instance.getWoonplaats();
        assertEquals(expResult, result);
        System.out.println(expResult + " = " + result );
        // TODO review the generated test code and remove the default call to fail.
      //  fail("The test case is a prototype.");
    }

    /**
     * Test of getKlantNaam method, of class Klant.
     */
    @Test
    public void testGetKlantNaam() {
        System.out.println("getKlantNaam");
        Klant instance = new Klant();
        instance.setKlantNaam("Jan Jansen");
        String expResult = "Jan Jansen";
        String result = instance.getKlantNaam();
        assertEquals(expResult, result);
        System.out.println(expResult + " = " + result );
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of setKlantNaam method, of class Klant.
     */
    @Test
    public void testSetKlantNaam() {
        System.out.println("setKlantNaam");
        String kN = "Jan Jansen";
        Klant instance = new Klant();
        instance.setKlantNaam(kN);
        String klantnaam = "Jan Jansen";
        assertEquals(kN, klantnaam);
        System.out.println(kN + " = " + klantnaam );
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getKlantAdres method, of class Klant.
     */
    @Test
    public void testGetKlantAdres() {
        System.out.println("getKlantAdres");
        Klant instance = new Klant();
        instance.setKlantAdres("Diepenbrockdreef 8");
        String expResult = "Diepenbrockdreef 8";
        String result = instance.getKlantAdres();
        assertEquals(expResult, result);
        System.out.println(expResult + " = " + result );
        // TODO review the generated test code and remove the default call to fail.
      //  fail("The test case is a prototype.");
    }

    /**
     * Test of setKlantAdres method, of class Klant.
     */
    @Test
    public void testSetKlantAdres() {
        System.out.println("setKlantAdres");
        String kA = "Diepenbrockdreef 8";
        Klant instance = new Klant();
        instance.setKlantAdres(kA);
        String klantAdres = "Diepenbrockdreef 8";
        assertEquals(kA, klantAdres);
        System.out.println(kA + " = " + klantAdres);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of getKorting method, of class Klant.
     */
    @Test
    public void testGetKorting() {
        System.out.println("getKorting");
        Klant instance = new Klant();
        Double expResult = 12.3;
        instance.setKorting(12.3);
        Double result = instance.getKorting();
        assertEquals(expResult, result);
        System.out.println(expResult + " = " + result );
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of setKorting method, of class Klant.
     */
    @Test
    public void testSetKorting() {
        System.out.println("setKorting");
        Double kor = 12.3;
        Klant instance = new Klant();
        instance.setKorting(kor);
        Double korting = 12.3;
        assertEquals(kor, korting);
        System.out.println(kor + " = " + korting );
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of getGeboorteDatum method, of class Klant.
     */
    @Test
    public void testGetGeboorteDatum() throws ParseException {
        System.out.println("getGeboorteDatum");
        Klant instance = new Klant();
        instance.setGeboorteDatum(new SimpleDateFormat("dd-MM-yyyy").parse("13-11-1994"));
        Date expResult = new SimpleDateFormat("dd-MM-yyyy").parse("13-11-1994");
        Date result = instance.getGeboorteDatum();
        assertEquals(expResult, result);
        System.out.println(expResult + " = " + result );
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of setGeboorteDatum method, of class Klant.
     */
    @Test
    public void testSetGeboorteDatum() throws ParseException {
        System.out.println("setGeboorteDatum");
        Date gD = new SimpleDateFormat("dd-MM-yyyy").parse("13-11-1994");
        Klant instance = new Klant();
        instance.setGeboorteDatum(gD);
         Date expResult = new SimpleDateFormat("dd-MM-yyyy").parse("13-11-1994");
         assertEquals(gD, expResult);
         System.out.println(gD + " = " + expResult );
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
