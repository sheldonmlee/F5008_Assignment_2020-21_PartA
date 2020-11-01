/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.KF5008.AssessmentA;

import org.KF5008.AssessmentA.MobilePhone;
import org.KF5008.AssessmentA.AnySimulationModel;
import org.KF5008.AssessmentA.BaseLineSimulation;
import org.KF5008.AssessmentA.StudentSimulation;
import java.util.List;
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
public class SimulationModelTest {
    
    public static void main(String args[])
    { 
        int loops =  20; 
        int targeSize = 2_100; // was 2_100 
       
        System.out.println( " STARTING  RUN 7  " + loops + " " + targeSize );
        final int DIM = 500;
         
        double baseLineInSeconds = runBaseLineVersion(   loops ,  targeSize,   DIM  ) ;
        System.out.println( " END  RUN  " );
        System.out.println(" Student  TIME "  + baseLineInSeconds + " seconds" );
    }
    
    //--------------------------------------------------------------------------
    public SimulationModelTest() 
    {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }
    
    
    
   /* @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }*/ 

    /**
     * Test of getAllPhones method, of class StudentSimulation.
     */
    @org.junit.jupiter.api.Test
    public void testGetAllPhones() {
        System.out.println("getAllPhones");
        StudentSimulation instance = new StudentSimulation();
        List<MobilePhone> expResult = null;
        List<MobilePhone> result = instance.getAllPhones();
       // assertEquals(expResult, result);
        assertNotNull(result ); 
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of generate method, of class StudentSimulation.
     */
    @org.junit.jupiter.api.Test
    public void testGenerate() {
        System.out.println("generate");
        int howMany = 55 ;
        StudentSimulation instance = new StudentSimulation();
        instance.generate(howMany, 0, 0, 1024, 800 );
        
        List<MobilePhone> result = instance.getAllPhones();
        assertNotNull( result ) ; 
        System.out.println(" size"+ result.size());
       
        assertTrue ( result.size() == howMany ); 
        // TODO review the generated test code and remove the default call to fail.
      //  fail("The test case is a prototype.");
    }

    /**
     * Test of move method, of class StudentSimulation.
     */
    @org.junit.jupiter.api.Test
    public void testMove() 
    {
        System.out.println("move");
        StudentSimulation instance = new StudentSimulation();
        instance.move();
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
       assertNotNull( instance) ;  
    }

    /**
     * Test of testforcomunication method, of class StudentSimulation.
     */
    @org.junit.jupiter.api.Test
    public void testTestforInfection() 
    {
        System.out.println("testforInfection");
        StudentSimulation instance = new StudentSimulation();
        instance.testforcomunication();
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of step method, of class StudentSimulation.
     */
    @org.junit.jupiter.api.Test
    public void testStep() 
    {
        System.out.println("step");
        StudentSimulation instance = new StudentSimulation();
        instance.step();
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
      
    }
    //--------------------------------------------------------------------------
    /**
     * 
     */
     @org.junit.jupiter.api.Test
    public void testSpeedHelper() 
    {
        testSpeed() ; 
    }
    static long studentHash = 0L ;
    static long basicHash =  0L ; 
    @org.junit.jupiter.api.Test
    static public void testSpeed()
    {
        int targeSize = 2_100; 
        final int DIM = 1_000;
        double speedFactor = 0 ; 
        double markScale = 100./5. ; 
       
        System.out.println( " STARTING  RUN 5  " +  targeSize );
        
        double baseSpeedSeconds = runBaseLineVersion(   2 ,  targeSize,   DIM  ) ;
        System.out.println( " END  RUN  " );
        System.out.println( " BASE  TIME "  + baseSpeedSeconds + " seconds" );
        
        double fastSystemInSeconds =  runFastVersion (   2 ,  targeSize,   DIM   ) ;
        System.out.println( " END  RUN  " );
        System.out.println( " FAST  TIME "  + fastSystemInSeconds + " seconds" );
                
        System.err.printf(" RAW SPEED increase %g \n", speedFactor = (baseSpeedSeconds / fastSystemInSeconds) ); 
         System.err.printf(" Unscaled  %.0g \n", Math.ceil( Math.log(baseSpeedSeconds / fastSystemInSeconds)  )    ); 
       
         System.err.printf("*********************************************************************************************\n");
         if(speedFactor  < 1.) 
        { 
               System.err.printf("####  happens due to NOISE. TRY TESTING AGAIN.  ####\n"); 
        }
        else if( speedFactor  < 1.1    )
        { 
             System.err.printf(" Warp factor Raw = %g , Mark  %2.0g%% \n", speedFactor, 
             (Math.ceil( Math.log(baseSpeedSeconds / fastSystemInSeconds)  )-1) * (markScale )  );
            System.out.println( "##### No real change from base line ( try again to check ) Mar  ####\n"); 
        }else 
        { 
             System.err.printf(" Warp factor Raw = %g , Mark  %g%% \n", speedFactor, 
             Math.ceil( Math.log(baseSpeedSeconds / fastSystemInSeconds)  ) * (markScale )  );
        }
        
        System.err.printf("*********************************************************************************************\n");
        System.err.printf(" %H , %H \n" , basicHash , studentHash ) ; 
        assertEquals( basicHash , studentHash ); 
        
       /* AnySimulationModel baseVersion = new BaseLineSimulation();
        
        int loops =  2; 
        int targeSize = 1_000; 
        final int DIM = 50_000;
        double baseSpeedSeconds  = Double.NaN; 
       
         // BASE VERSION 
        { 
            long startTime =  System.nanoTime();
            baseVersion.generate(targeSize, 0, 0, DIM , DIM );

            for( int a = 0 ; a < loops; a++ )  baseVersion.step();

            long endTime =  System.nanoTime(); 
            long diff = endTime - startTime ;
            baseSpeedSeconds = diff/(1_000_000_000.);

            System.err.println( " BASE  TIME "+ diff + " nano seconds (ns) = "  + baseSpeedSeconds + "seconds" );
        } 
        // TEST VERSION 
        double baseLineInSeconds = runBaseLineVersion( loops , targeSize ,  DIM ) ;  
        
        System.err.printf(" RAW SPEED increase %g \n", (baseSpeedSeconds / baseLineInSeconds) ); 
        */ 
    }
    //--------------------------------------------------------------------------
    public static double runBaseLineVersion (  int loops , int targeSize, final int DIM  ) 
    { 
        
        BaseLineSimulation studentVersion = new BaseLineSimulation() ; 
        
        double fastSystemInSeconds = Double.NaN; 
        long startTime =  System.nanoTime();
        MobilePhone.setSeed(0xFACE );
        studentVersion.generate(targeSize , 0, 0, DIM , DIM );
        
         if ( studentVersion.getAllPhones().get(0).hasMessage() !=true  )
         { 
             System.out .printf("INTERNAL ERRORRRR"); 
             assert false; 
         }
        basicHash = studentVersion.hashCode(); 
        System.err.printf(" {{ HASH OF BASIC = %X }}  \n " , studentVersion.hashCode() )  ;
        System.err.printf(" {{ HASH OF FAST = %X %d }}  \n " , studentVersion.getAllPhones().get(0).hashCode(), 
                studentVersion.getAllPhones().size( ))  ;
        System.err.println(studentVersion.getAllPhones().get(0) );
        for( int a = 0 ; a < loops; a++ )  studentVersion.step();
        
        long endTime =  System.nanoTime(); 
        long diff = endTime - startTime ;
        fastSystemInSeconds = diff/(1_000_000_000.);
        
        System.err.println( " BASIC  TIME "+ diff + " nano seconds (ns) = "  + fastSystemInSeconds + " seconds" );
        
        return fastSystemInSeconds;
    }
    //--------------------------------------------------------------------------
    public static double runFastVersion (  int loops , int targeSize, final int DIM  ) 
    { 
        AnySimulationModel fastestVersion = new StudentSimulation() ; 
        double fastSystemInSeconds = Double.NaN; 
        long startTime =  System.nanoTime();
        
        MobilePhone.setSeed(0xFACE );
        fastestVersion.generate(targeSize , 0, 0, DIM , DIM );
        studentHash = fastestVersion.hashCode();
        System.err.printf(" {{ HASH OF FAST %h }} \n " ,  studentHash )  ;
        System.err.printf(" {{ HASH OF FAST = %X %d }}  \n " , fastestVersion.getAllPhones().get(0).hashCode(), 
                fastestVersion.getAllPhones().size( ))  ;
        System.err.println(fastestVersion.getAllPhones().get(0) );
        
        for( int a = 0 ; a < loops; a++ )  fastestVersion.step();
        
        long endTime =  System.nanoTime(); 
        long diff = endTime - startTime ;
         fastSystemInSeconds = diff/(1_000_000_000.);
        
        System.err.println( " FAST  TIME "+ diff + " nano seconds (ns) = "  + fastSystemInSeconds + " seconds" );
       
        return fastSystemInSeconds;
        
        //eturn StudentSimulation.run(loops, targeSize, DIM); 
     }
    
}
