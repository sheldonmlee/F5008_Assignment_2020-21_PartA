/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.KF5008.AssessmentA;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author nickdalton
 */
public class MobilePhone implements AnyMobilePhone
{
   static Random dice = new Random( ) ; 
   final int radius = 10 ;
   public static  final int communicationRadius = 35 ; 
   double  hoz, vert ;
   double  hVel , vVel= 1; // velocity. 
   boolean hasMessage = false ; 
   boolean immune = false ;
   boolean dead  = false ; 
   int  depth; 
   int infectedTime = -1 ; 
   List<AnyMobilePhone>  interactedPhones ;  
   boolean bounced = false  ; 

   
    //--------------------------------------------------------------------------
    public boolean isDead() {     return dead; }
    public void setDead(boolean dead) {  this.dead = dead;   }
    //--------------------------------------------------------------------------
    public static boolean isSperationLessThan ( double x, double y , double otherx,
            double othery ,   double critialDistance)
    { 
        double squr = critialDistance * critialDistance ;  
        double v = x - otherx  ; 
        double h = y - othery  ; 
        double d = ( v* v ) + ( h * h ); 
        return d < squr ;        
       // return Math.hypot( x - otherx, y - othery ) <= critialDistance ; 
    }
    //--------------------------------------------------------------------------
    public static  void setSeed( long seed )
    {  
        assert dice != null ; 
        dice.setSeed(  seed );
    }
    //--------------------------------------------------------------------------
    @Override
    public boolean hasMessage() 
    {
        return hasMessage;
    }

    @Override
    public void setHasMessage(boolean infected) 
    {
        if( immune == true )return ; // cannot be hasMessage if immune. 
        
        if( infected == true  )
        { 
            infectedTime = 19 ; 
        } 
        this.hasMessage = infected;
    }
    
    @Override
    public int getCommuncationRadius( )
    { 
        return communicationRadius;
    }
    
   
    @Override
    public int getHoz() 
    {
        return (int) hoz;
    }

    @Override
    public int getVert() 
    {
        return (int) vert;
    }

    @Override
    public double gethVel() 
    {
        return  hVel;
    }

    @Override
    public double getvVel() 
    {
        return  vVel;
    }
    @Override
    public void boundHoz()
    { 
       hVel *= -1. ; 
       bounced = true  ; 
    }
    @Override
    public void bounceVert()
    { 
        vVel *= -1.  ;bounced = true  ;  
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) 
    {
        this.depth = depth;
    }
  
    MobilePhone(  int minhoz, int minVert , int maxHoz , int maxVert  )
    { 
        assert dice != null ; 
        assert  maxHoz > minhoz ; 
        assert  maxVert > minVert; 
        int h = minhoz + (int)( dice.nextDouble() * ( maxHoz - minhoz )); 
        int v = minVert + (int)( dice.nextDouble() * (maxVert -minVert )) ; 
        this.hoz = h ; 
        this.vert = v ; 
        
        this.hVel = ( (dice.nextDouble()- 0.5)   + (dice.nextDouble() - 0.5)  )  ; 
        this.vVel = ( (dice.nextDouble() - 0.5)   + (dice.nextDouble() - 0.5)  )  ; 
       // System.out.println(  this.hVel + " " + this.vVel );
        assert h >= 0 ; 
        assert v >= 0 ; 
        
        interactedPhones = new LinkedList<AnyMobilePhone>( ); 
            
    }
   /* private MobilePhone( int h , int v  )
    { 
        assert dice != null ; 
       
        this.hoz = h ; 
        this.vert = v ; 
        
        this.hVel = ( (dice.nextDouble()- 0.5)   + (dice.nextDouble() - 0.5)  )  ; 
        this.vVel = ( (dice.nextDouble() - 0.5)   + (dice.nextDouble() - 0.5)  )  ; 
       // System.out.println(  this.hVel + " " + this.vVel );
        assert h >= 0 ; 
        assert v >= 0 ; 
        
        interactedPhones = new ArrayList<AnyMobilePhone>( 10); 
    }*/ 
   //---------------------------------------------------------------------------
   void draw( Graphics2D g)
   {
       if( bounced) 
       { 
            g.setColor(Color.CYAN);
             bounced = false ; 
       }else 
       g.setColor(Color.LIGHT_GRAY);
       g.fillOval((int)(hoz-radius), (int) (vert-radius), 2* radius , 2* radius);
	   //g.drawLine((int)(hoz-radius), (int) (vert-radius), (int)(hoz+radius), (int)(vert+radius));
	   //g.drawLine((int)(hoz+radius), (int) (vert-radius), (int)(hoz-radius), (int)(vert+radius));
       
       if( this.hasMessage == true )
       {
            g.setColor(Color.red);
       }else 
       {
            g.setColor(Color.BLACK);
       }
       g.fillOval( (int)(hoz-3),(int)(vert-3) , 5 ,5 );
	   //g.drawLine((int)hoz, (int)(vert-radius/2), (int)hoz, (int)(vert+radius/2));
	   //g.drawLine((int)(hoz-radius/2), (int)vert, (int)(hoz+radius/2), (int)vert);
       
      
       for( AnyMobilePhone p: this.interactedPhones )
       { 
          if( isSperationLessThan(hoz, vert, p.getHoz(), p.getVert(), communicationRadius)  ) 
          {  
            g.drawLine((int)(hoz), (int) (vert) , (int)p.getHoz(), (int) p.getVert()); 
          }
       }
       
   }
   //---------------------------------------------------------------------------
   /**
    *  move one step in direction of street. 
    */
   void step() 
   { 
      this.hoz += this.hVel ; 
      this.vert += this.vVel ; 
      
      if( hasMessage == true )
      { 
          infectedTime -= 1; 
            if( infectedTime  <= 0  )// tim is up 
            { 
                hasMessage = false ; 
                infectedTime = -1 ; 
                immune = true ; 
            }
            else
            { 
                
            }
       } 
   }
   //---------------------------------------------------------------------------
   void communicate( MobilePhone other )
   { 
       //other person infects me. 
       assert  interactedPhones != null : "Null list"; 
       if( ! interactedPhones.contains( other ) )
        { 
         this.interactedPhones.add( other);// add at end 
        } 
        if(interactedPhones.size() > 10  )
        { 
            interactedPhones.remove(0); // remove oldest
        }
       /*if( other.hasMessage && this.hasMessage == false )
       { 
            this.hasMessage = true ; 
            if( ! interactedPhones.contains( other ) )
            { 
            this.interactedPhones.add( other);// add at end 
            } 
            if(interactedPhones.size() > 10  )
            { 
                interactedPhones.remove(0); // remove oldest
            }
       }*/
   }
   //---------------------------------------------------------------------------
    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 43 * hash + this.radius;
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.hoz) ^ (Double.doubleToLongBits(this.hoz) >>> 32));
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.vert) ^ (Double.doubleToLongBits(this.vert) >>> 32));
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.hVel) ^ (Double.doubleToLongBits(this.hVel) >>> 32));
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.vVel) ^ (Double.doubleToLongBits(this.vVel) >>> 32));
        hash = 43 * hash + (this.hasMessage ? 1 : 0);
        hash = 43 * hash + (this.immune ? 1 : 0);
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
        final MobilePhone other = (MobilePhone) obj;
        if (this.radius != other.radius) {
            return false;
        }
        if (Double.doubleToLongBits(this.hoz) != Double.doubleToLongBits(other.hoz)) {
            return false;
        }
        if (Double.doubleToLongBits(this.vert) != Double.doubleToLongBits(other.vert)) {
            return false;
        }
        if (Double.doubleToLongBits(this.hVel) != Double.doubleToLongBits(other.hVel)) {
            return false;
        }
        if (Double.doubleToLongBits(this.vVel) != Double.doubleToLongBits(other.vVel)) {
            return false;
        }
        if (this.hasMessage != other.hasMessage) {
            return false;
        }
        if (this.immune != other.immune) {
            return false;
        }
        return true;
    }
   
   
   //---------------------------------------------------------------------------

    @Override
    public String toString() {
        return "MobilePhone{" + "radius=" + radius + ", hoz= (" + hoz + ") , vert=" +
                vert + ", hVel=" + hVel + ", vVel=" + vVel + ", infected=" + hasMessage + ", immune=" + immune + '}';
    }
    
     public static void main(String args[])
    { 
        System.out.println(  isSperationLessThan ( 0,0,10,10,1000) ) ; 
    
    
    }
}
