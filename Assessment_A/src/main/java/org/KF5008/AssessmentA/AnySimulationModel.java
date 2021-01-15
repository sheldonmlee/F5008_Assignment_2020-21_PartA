/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.KF5008.AssessmentA;

import java.util.List;


/**
 *
 * @author nickdalton
 */
public interface AnySimulationModel
{
    public List<MobilePhone> getAllPhones() ; 
    
    void generate( int howMany , int minhoz, int minVert , int maxHoz , int maxVert); 
    void move();
    void testforcomunication();
    void collectStatistics( ); 
    void step();
    
    int getMaxHoz( ) ; 
    String getSimuationName( ) ; 

	// custom
	public QuadTree getQuadTree();
    
    @Override 
    int hashCode(); 
    
    
}
