/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.KF5008.AssessmentA;

/**
 *
 * @author nickdalton
 */
public interface AnyMobilePhone 
{
    void bounceVert();
    void boundHoz();
   
    int getHoz();
    int getCommuncationRadius();
    int getVert();
    double gethVel();
    double getvVel();
   
    boolean hasMessage();
    void setHasMessage(boolean infected);

      @Override
    boolean equals(Object obj);
     @Override
    int hashCode();
    @Override
    String toString();  
}
