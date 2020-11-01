/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.KF5008.AssessmentA;

import java.util.*;

import org.KF5008.AssessmentA.AnySimulationModel;
import org.KF5008.AssessmentA.MobilePhone;
//======================================================================================
/**
 *
 * @author nickdalton
 */
public class BaseLineSimulation  implements AnySimulationModel 
{
    int minhoz; 
    int minVert ; 
    int maxHoz ; 
    int maxVert ; 
    int numberOfInfected = 0 ;  ; 
    
    List<MobilePhone> allPhones; 
    List<Integer> communicatedGrowthData ; 
    int things = 24 ; 
    
    public  BaseLineSimulation(  )
    { 
       this.allPhones = new  LinkedList<MobilePhone>(  ); 
       communicatedGrowthData  = new LinkedList<>( ) ; 
       // this.allPhones = new ArrayList< MobilePhone >( 100 ) :
       generate( 100 , 0 , 0 , 800, 600 ); 
       
    }
    //--------------------------------------------------------------------------
    public  BaseLineSimulation( int howMany  ,int minhoz, int minVert , int maxHoz , int maxVert )
    { 
       this.allPhones = new  LinkedList<MobilePhone>(  ); 
       this. communicatedGrowthData  = new LinkedList<>( ) ; 
       // this.allPhones = new ArrayList< MobilePhone >( 100 ) :
       generate( howMany  , minhoz,  minVert ,  maxHoz ,  maxVert ); 
       
    }
    @Override public String getSimuationName( ) { return "Base line simuation"; } 
    @Override  public  int getMaxHoz( ) 
    { 
        return  maxHoz; 
    }
    //--------------------------------------------------------------------------
    /**
     * 
     * @return list of phones 
     */

    @Override
    public List<MobilePhone> getAllPhones() 
    {
        return allPhones;
    }
    //--------------------------------------------------------------------------
    @Override
    public void generate( int howMany  ,int minhoz, int minVert , int maxHoz , int maxVert  ) 
    { 
        System.out.println(" **BASE LINE** GENERATE " + howMany  );
        assert  maxHoz > minhoz ; 
        assert  maxVert > minVert; 
        assert howMany > 0 ; 
        this.minhoz = minhoz ; 
        this.maxHoz = maxHoz ; 
        this.minVert = minVert;
        this.maxVert = maxVert ; 
        this.allPhones = new  ArrayList<MobilePhone>(  ); 
         
        MobilePhone mx = null ; 
        for( int a = 0 ; a < howMany ;a++)
        { 
            //int h = minhoz + (int)( Math.random() * ( maxHoz - minhoz )); 
            //int v = minVert + (int)( Math.random() * (maxVert -minVert )) ; 
            
            mx = new MobilePhone( minhoz,  minVert ,  maxHoz ,  maxVert); 
             
            allPhones.add( (int) allPhones.size()/2 , mx ); 
            assert  mx.getHoz()  <=  maxHoz; 
            assert  mx.getHoz()  >=  minhoz; 
            assert  mx.getVert()  <=  maxVert;
            assert  mx.getVert()  >=  minVert;
        }
       if( howMany > 0 )
       { 
            // assert mx != null  : "MX IS NULL "; 
            // if( mx != null )  mx.setHasMessage(true);else {  System.out.println("no MX"); }
            assert allPhones.get(0) != null ; 
            allPhones.get(0).setHasMessage(true);
            System.out.println(" END OF generate " + howMany + " " + allPhones.size() 
                    + " \n" + allPhones.get(0)  + "\n" +allPhones.get(1) + "\n");
            assert( allPhones.get(0).hasMessage() ==true  ); 
            assert howMany == allPhones.size() ; 
        } 
    }
    //--------------------------------------------------------------------------
    /****
     *  ake this slower - use some thing to slow down. 
     */
    @Override
    public void move()
    { 
        double check ; 
        for( MobilePhone p: this.allPhones)
        { 
           p.step();
           check = Math.hypot(p.getHoz(), p.getVert());
           
           if( ( p.getHoz() >= maxHoz)  || ( p.getHoz() <= minhoz )    )
           { 
               p.boundHoz();
           }
           if( ( p.getVert() >=  maxVert)  || ( p.getVert()<= minVert )  )
           { 
               p.bounceVert();
           }
        }
    }
    //--------------------------------------------------------------------------
    /** 
     * Slow version for students. 
     *  Test each phon  
     */
    @Override
    public void  testforcomunication()
    { 
       for( int a = 0 ; a < allPhones.size() ; a++  )
        { 
            MobilePhone p = allPhones.get(a); // 7756 
         
           for( int b = 0 ; b <  allPhones.size() ; b++  )
           { 
               MobilePhone other = allPhones.get(b); // 36,597
               if( other == p )continue ; 
               if( p.isSperationLessThan( p.getHoz(), p.getVert() , 
                       other.getHoz() , other.getVert() , p.getCommuncationRadius() ) )
               { 
                    p.communicate(other);
               }
                       
               // assumes mathematical knowlege... to speed up... unfair unless I show them. 
               /* double dist =  Math.hypot( p.getVert() - other.getVert() , p.getHoz() - other.getHoz() ); 
              
               if( dist <= p.getCommuncationRadius())
               { 
                   p.communicate(other);
               }
               */ 
               /* if( other == p )continue ;
                double squr = p.getCommuncationRadius() * p.getCommuncationRadius(); 
                double v = p.getVert() - other.getVert() ; 
                double h = p.getHoz() - other.getHoz() ; 
                double d = ( v* v ) + ( h * h ); 

                if(  d <=  squr )
                { 
                    p.communicate(other);
                }*/ 
            }
        }
    }
     //--------------------------------------------------------------------------
    /**
     * Note adding at location 0 is not critical to operation. 
     */
    public void  removeTheDead( )
    {
       double check = 0 ; 
       int countOfDead = 0 ; 
       List<MobilePhone>  theDead = new LinkedList< >( ) ; 
       for( int a = 0 ; a < getAllPhones().size() ; a++ )
       { 
             MobilePhone  who = getAllPhones().get(a);
             check += Math.hypot(who.getHoz(), who.getVert());
             if( who.isDead())
             { 
                 theDead.add(theDead.size()/2, who);
                 countOfDead++ ; 
             }
       } 
       //System.out.println( check ); 
       check = 0 ; 
       int countOfDeadRemoved = 0 ; 
       for(  int b = 0 ; b <  theDead.size();b++)
       { 
            MobilePhone  who = theDead.get(b);
            for( int a = 0 ; a < getAllPhones().size() ; a++ )
            { 
              MobilePhone  it = getAllPhones().get(a);
               check += Math.hypot(who.getHoz(), who.getVert());
              if( it == who ) 
              { 
                  getAllPhones().remove(a); 
                  countOfDeadRemoved ++ ; 
                  break ; 
              }
            }
       }
       assert countOfDeadRemoved == countOfDead ; 
    }
    //--------------------------------------------------------------------------
    /*
      - do something. which is very inefficent but easy to replace. 
        - perhaps buildin up set of infected. 
    
    Student: you have to ask your self is is everything here nessasry ? 
    could it be done in a more efficent manner  ? 
    
    feel free to remove stuff and see if the unit tests don't fail. 
    */
    @Override 
    public void  collectStatistics( )
    { 
        List<MobilePhone>  uninfectedCount = new LinkedList< >( ) ; 
        List<MobilePhone>  infectedCount = new LinkedList< >( ) ; 
        double totalDist = 0 ;
        double unifectedDensity = 0 ; 
        double infectedDensity = 0 ; 
        double check ; 

        for( MobilePhone p: this.allPhones)
        { 
            if( p.hasMessage( ) == false )// ADD TO THE FALSE LIST 
            { 
                 // only add to list if not added already in. 
                boolean found  = false ; 
                for( int a = 0 ; a < uninfectedCount.size() ; a++ )
                { 
                    MobilePhone  other = uninfectedCount.get(a);
                    //check = Math.hypot(other.getHoz()-p.getHoz(), other.getVert()-p.getVert()); 
                    if( p == other )   found = true;
                }
                if(found == false )
                {  
                     uninfectedCount.add(uninfectedCount.size( ) /2, p);
                    // totalDist += Math.hypot( p.getVert()  , p.getHoz()  );
                     
                    for( int b = 0 ;  b <  infectedCount.size( ) ; b++)
                    { 
                         MobilePhone  other =  infectedCount.get( b); 
                         //check = Math.hypot(other.getHoz()-p.getHoz(), other.getVert()-p.getVert()); 
                         if( other == p )
                         { 
                             System.out.println("Illegal cross contamination"); 
                             assert false ; 
                         }
                    }
                }
            }else 
            { 
                // only add to list if not added already in. 
                boolean found  = false ; 
                for( int a = 0 ; a < infectedCount.size() ; a++ )
                { 
                    MobilePhone  other = infectedCount.get(a); 
                   // check = Math.hypot(other.getHoz()-p.getHoz(), other.getVert()-p.getVert()); 
                    if( p == other )   found = true;
                }
                if(found == false )
                {  
                    infectedCount.add(infectedCount.size( ) /2, p);
                    //infectedDensity += Math.hypot( p.getVert()  , p.getHoz()  );
                    
                    for( int b = 0 ;  b <  uninfectedCount.size( ) ; b++)
                    { 
                         MobilePhone  other =  uninfectedCount.get( b); 
                         if( other == p )
                         { 
                             System.out.println("Illegal cross contamination"); 
                             assert false ; 
                         }
                    }
                } 
            }// end if p.hasMessage( ) == false
        }
        communicatedGrowthData.add(  infectedCount.size( ) ); 
        if( false ) System.out.printf(" INFECTED %d UNiNFECTED %d density %g, %g \n", infectedCount.size( ),
                                                   uninfectedCount.size() , 
                                                   ( totalDist/uninfectedCount.size() ) , 
                                                   (infectedDensity/infectedCount.size( ))  );
        numberOfInfected = uninfectedCount.size();  
    }
    //--------------------------------------------------------------------------
    /*
    
    */
    @Override
    public void step()
    { 
        move(); 
        testforcomunication() ;
        collectStatistics( ) ; 
        removeTheDead( ) ; 
    }
    //--------------------------------------------------------------------------
    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.allPhones);
        return hash;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BaseLineSimulation other = (BaseLineSimulation) obj;
        if (!Objects.equals(this.allPhones, other.allPhones)) {
            return false;
        }
        return true;
    }
    
    
}

