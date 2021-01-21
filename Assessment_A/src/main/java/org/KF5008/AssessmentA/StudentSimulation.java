/*
  * property of northumbria universty + you. 
 */
package org.KF5008.AssessmentA;


import java.util.*;


/**
 *   TO PROFILE YOUR CODE . 
 *    IN PROFILE MENU CLICK 
 * @author you 
 */
public class StudentSimulation extends BaseLineSimulation  
{
	// callback for resize
	interface ResizeCallback {
		void call(int minhoz, int minVert , int maxHoz , int maxVert);
	}
	
	protected ResizeCallback resizeCallback;

	private QuadTree quadtree;

    public   StudentSimulation(  )
    { 
		this( 500, 0,0 , 1024, 800   ); // don't change this line. 
    }
    //--------------------------------------------------------------------------
    public  StudentSimulation( int howMany  ,int minhoz, int minVert , int maxHoz , int maxVert )
    { 
       super( howMany  , minhoz,  minVert ,  maxHoz ,  maxVert ); 
       this.allPhones = new  ArrayList<MobilePhone>(  ); 
       communicatedGrowthData  = new LinkedList<>( ) ; 
       
	   this.quadtree = new QuadTree(new AABB(minhoz, minVert, maxHoz, maxVert), 1);

	   resizeCallback = (int a, int b, int c, int d) -> {
		   System.out.printf("Resize Called: %d,%d,%d,%d\n", a, b, c, d);
		   this.quadtree.resize(new AABB(a, b, c, d));
	   };

       generate( howMany  , minhoz,  minVert ,  maxHoz ,  maxVert ); // don't change this line. 
    }

    @Override
    public void generate( int howMany  ,int minhoz, int minVert , int maxHoz , int maxVert) 
    { 
		super.generate(howMany, minhoz, minVert, maxHoz, maxVert);
		if (resizeCallback != null) resizeCallback.call(minhoz, minVert, maxHoz,  maxVert);
    }
    //--------------------------------------------------------------------------
    /**
     *  detect if a message can be sent between any two randomly selected phones. 
     *  Part of an on going experiment to see if the system works as predicted. 
     *  @Author Giles Motgomary Smith 
     */
    void findMessageRoute( )
	{ 
		testforcomunication( ) ; 
		int phoneA = (int)( Math.random() * allPhones.size()) ; 
		int phoneB  ; 
		do
		{ 
			phoneB = (int) (Math.random() * allPhones.size()) ; 
			System.out.printf(" %d %d " , phoneA, phoneB ); 
		} while( phoneB == phoneA  ); 

		MobilePhone from = allPhones.get( phoneA ); 
		MobilePhone too = allPhones.get( phoneA ); 
		List< MobilePhone> innterSet = new ArrayList<> ( ) ; 
		List< MobilePhone> outerEdge = new ArrayList<> ( ) ; 

		for( MobilePhone it:allPhones  )it.setDepth( Integer.MAX_VALUE-10); 
		innterSet.add( from ); 
		from.setDepth(0);

		for( MobilePhone it: innterSet )
		{ 
			for( int a = 0 ; a < it.interactedPhones.size( ) ; a ++ )
			{  
				MobilePhone other =  (MobilePhone) it.interactedPhones.get(a); 

				if( other == it ) continue ; 
				System.out.println( "depth before  " +  other.depth + " " + (it.depth+1) ); 
				if( other.depth > ( it.depth+1 ) )
				{ 

					other.depth  = it.depth+1 ; 
					if( !outerEdge.contains(it)) 
					{ 
						outerEdge.add( it ) ;
						System.out.println( "adding " +  other.depth);
					}else 
						System.out.println( "contains " +  other.depth);
				}
				System.out.println( "depth after  " +  other.depth);
			}
		}
		System.out.println( "--------DEPTHS size = " + outerEdge.size()); 

		for( MobilePhone it: outerEdge ) 
		{ 
			System.out.println( it.depth ); 
		}
        
        
    }
         
    
    //--------------------------------------------------------------------------
    /****
     *  
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
		for (MobilePhone p : allPhones) {
			int x = p.getHoz(), y = p.getVert(), r = p.getCommuncationRadius();
			
			AABB searchBox = new AABB(x-r, y-r, x+r, y+r);
			ArrayList<MobilePhone> closePhones = quadtree.query(searchBox);
			for (MobilePhone other : closePhones) {
				if( p.isSperationLessThan( p.getHoz(), p.getVert() ,
						other.getHoz() , other.getVert() , p.getCommuncationRadius() ) )
				{
					p.communicate(other);
				}
			}
		}
	} 
     //--------------------------------------------------------------------------
    /**
     * Note adding at location 0 is not crital to operation. 
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
		int uninfectedCount = 0;
		int infectedCount = 0;
        double totalDist = 0 ;
        double unifectedDensity = 0 ; 
        double infectedDensity = 0 ; 
        double check ; 

		for (MobilePhone p : this.allPhones) 
		{
			infectedCount += p.hasMessage()? 1 : 0;
			uninfectedCount += p.hasMessage()? 0 : 1;
		}
        communicatedGrowthData.add(  infectedCount ); 
        if( false ) System.out.printf(" INFECTED %d UNiNFECTED %d density %g, %g \n", infectedCount,
                                                   uninfectedCount , 
                                                   ( totalDist/uninfectedCount ) , 
                                                   (infectedDensity/infectedCount)  );
        numberOfInfected = uninfectedCount;  
    }
    
    //--------------------------------------------------------------------------
    /*
    
    */
    @Override
    public void step()
    { 
        move(); 
		quadtree.construct(this.allPhones);
        testforcomunication() ;
        collectStatistics( ) ;
        removeTheDead( ) ; 
    }
    //--------------------------------------------------------------------------
    @Override
    public int hashCode() 
    {
        // DO NOT CHANGE THIS !!!!!! will break assessment code.  
       return super.hashCode() ; 
    }
    //--------------------------------------------------------------------------
    @Override
    public boolean equals(Object obj) 
    {
       return super.equals(obj); 
    }
   
    //--------------------------------------------------------------------------
    public static double run (  int loops , int targeSize, final int DIM  ) 
    { 
        StudentSimulation fastestVersion = new StudentSimulation() ; 
        double fastSystemInSeconds = Double.NaN; 
        long startTime =  System.nanoTime();
        MobilePhone.setSeed(0xFACE );
        fastestVersion.generate(targeSize , 0, 0, DIM , DIM );
        System.err.printf(" Doing move. \n "  )  ;
        fastestVersion.move();
          System.err.printf(" Doing MoveDone. \n "  )  ;
        fastestVersion.findMessageRoute( ) ; 
         System.err.printf(" Find done  . \n "  )  ;
        long studentHash = fastestVersion.hashCode();
        
        System.err.printf(" HASH OF STUDENT = %X \n " , fastestVersion.hashCode() )  ;
        for( int a = 0 ; a < loops; a++ )  fastestVersion.step();
        
        long endTime =  System.nanoTime(); 
        long diff = endTime - startTime ;
         fastSystemInSeconds = diff/(1_000_000_000.);
        
        System.err.println( " FAST  TIME "+ diff + " nano seconds (ns) = "  + fastSystemInSeconds + " seconds" );
        
        return fastSystemInSeconds;
     }

	public QuadTree getQuadTree()
	{
		return quadtree;
	}

    //--------------------------------------------------------------------------
    /* 
        this is a conveniance function -  you can use this to profile your code. 
    */
    public static void main(String args[])
    { 
        int loops =  20; 
        int targeSize = 5000; // was 2_100 
       
        System.out.println( " STARTING  RUN Sudent  " + loops + " " + targeSize );
        final int DIM = 500;
         
        double baseLineInSeconds = StudentSimulation.run(   loops ,  targeSize,   DIM  ) ;
        
        if( baseLineInSeconds < 0.5)
        { 
            System.out.println( "#########################################################################" ) ; 
            System.out.println( " YOUR CODE WAS TOO FAST - MAKE THE NUMBER OF LOOPS TWICE AS BIG in main()" ) ; 
            System.out.println( "#########################################################################" ) ; 
        }
        System.out.println( " END  RUN  " );
        System.out.println(" Student  TIME "  + baseLineInSeconds + " seconds" );
    }
    //--------------------------------------------------------------------------
}

