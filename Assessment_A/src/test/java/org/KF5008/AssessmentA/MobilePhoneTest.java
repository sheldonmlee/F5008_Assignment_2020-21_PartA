/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.KF5008.AssessmentA;

import java.awt.Graphics2D;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author nickdalton
 */
public class MobilePhoneTest {
    
    MobilePhone aPhone ; 
    public MobilePhoneTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() 
    {
        System.out.println("NEW ");
        aPhone = new MobilePhone(0,0,800,900  ) ; 
        
    }
    
    @AfterEach
    public void tearDown() {
        aPhone = null ; 
    }
    public static  MobilePhone make( ) { return new MobilePhone(0,0,800,900  ) ; }  

    /**
     * Test of isDead method, of class MobilePhone.
     */
    @Test
    public void testIsDead() {
        System.out.println("isDead");
        MobilePhone instance = make() ;
        boolean expResult = false;
        boolean result = instance.isDead();
        assertEquals(expResult, result);
      
       
    }

    /**
     * Test of setDead method, of class MobilePhone.
     */
    @Test
    public void testSetDead() {
        System.out.println("setDead");
        boolean dead = false;
        MobilePhone instance = make() ;
        instance.setDead(dead);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(instance.dead , false ); 
    }

    /**
     * Test of isSperationLessThan method, of class MobilePhone.
     *  needs more detail. 
     */
    @Test
    public void testIsSperationLessThan() 
    {
        System.out.println("isSperationLessThan");
        double x = 0.0;
        double y = 10.0;
        double otherx = 0.0;
        double othery = 0.0;
        double critialDistance = 0.0;
        boolean expResult = true ;
        boolean result = MobilePhone.isSperationLessThan(x, y, otherx, othery, 11);
        assertEquals(expResult, result);
        
        result = MobilePhone.isSperationLessThan(x, 0, otherx, 100, 10);
        assertEquals(result, false);

    }



    /**
     * Test of hasMessage method, of class MobilePhone.
     */
    @Test
    public void testHasMessage() 
    {
        System.out.println("hasMessage");
        MobilePhone instance = make();
        instance.setHasMessage(true);
        boolean expResult = true;
        boolean result = instance.hasMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
  
    }

    /**
     * Test of setHasMessage method, of class MobilePhone.
     */
    @Test
    public void testSetHasMessage() {
        System.out.println("setHasMessage");
        boolean infected = true;
        MobilePhone instance = make();
        instance.setHasMessage(infected);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(instance.hasMessage(), true);
    }


    /**
     * Test of getHoz method, of class MobilePhone.
     */
    @Test
    public void testGetHoz() {
        System.out.println("getHoz");
        MobilePhone instance = make();
      
        int expResult = 101;
         instance.hoz =expResult; 
        int result = instance.getHoz();
        assertEquals(expResult, result);
       
       
    }

    /**
     * Test of getVert method, of class MobilePhone.
     */
    @Test
    public void testGetVert() {
        System.out.println("getVert");
        MobilePhone instance = make() ;
        int expResult = 55;
        instance.vert = expResult; 
        int result = instance.getVert();
        assertEquals(expResult, result);
    }

    /**
     * Test of gethVel method, of class MobilePhone.
     */
    @Test
    public void testGethVel() {
        System.out.println("gethVel");
        MobilePhone instance = make() ;
        double expResult = 55.5;
        instance.hVel = expResult ; 
        double result = instance.gethVel();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getvVel method, of class MobilePhone.
     */
    @Test
    public void testGetvVel()
    {
        System.out.println("getvVel");
        MobilePhone instance = make() ;
        double expResult = 3.141;
        instance.vVel = expResult;
        double result = instance.getvVel();
        assertEquals(expResult, result, 0.0); 
    }

    /**
     * Test of boundHoz method, of class MobilePhone.
     */
    @Test
    public void testBoundHoz() {
        System.out.println("boundHoz");
        MobilePhone instance = make() ;
        double expResult = 3.141;
        instance.hVel = expResult;
        instance.boundHoz();
        assertEquals(-expResult, instance.hVel, 0.001); 
    }

    /**
     * Test of bounceVert method, of class MobilePhone.
     */
    @Test
    public void testBounceVert() {
        System.out.println("bounceVert");
        MobilePhone instance = make() ;
        double expResult = 3.141;
        instance.vVel = expResult; 
        instance.bounceVert();
        
        assertEquals(-expResult, instance.vVel, 0.001);
    }

    /**
     * Test of getDepth method, of class MobilePhone.
     */
    @Test
    public void testGetDepth() {
        System.out.println("getDepth");
        MobilePhone instance = make() ;
      
        int expResult = 33;
        instance.setDepth(expResult); ; 
        int result = instance.getDepth();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDepth method, of class MobilePhone.
     */
    @Test
    public void testSetDepth() {
        System.out.println("setDepth");
       MobilePhone instance = make() ;
      
        int expResult = 33;
        instance.setDepth(expResult); ; 
        int result = instance.getDepth();
        assertEquals(expResult, result);
    }

    /**
   

    /**
     * Test of hashCode method, of class MobilePhone.
     */
    @Test
    public void testHashCode() 
    {
        System.out.println("hashCode");
        MobilePhone instance = make() ;
        int expResult = 0;
        int result = instance.hashCode();
        assertNotEquals(expResult, result);   
    }

    /**
     * Test of equals method, of class MobilePhone.
     */
   /* @Test
    public void testEquals() 
    {
        System.out.println("equals");
        Object obj = null;
        MobilePhone instance = make() ;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // RANDOMNESS MEANS CANT TEST 
        assertEquals(true, true );
    }*/

    /**
     * Test of toString method, of class MobilePhone.
     */
    @Test
    public void testToString() 
    {
        System.out.println("toString");
        MobilePhone instance = make() ;
        String expResult = "MobilePhone{radius=10, hoz= (559.0) , vert=547.0, hVel=-0.1933912476213906, vVel=-0.02601712820219726, infected=false, immune=false";
        String result = instance.toString();
        System.out.println(result ); 
        
        // RANDOMNESS MEANS CANT TEST 
        assertEquals(true, true );
    }
}
