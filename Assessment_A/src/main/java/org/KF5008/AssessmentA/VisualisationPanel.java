/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.KF5008.AssessmentA;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author nickdalton
 */
public class VisualisationPanel extends JPanel implements MouseWheelListener 
{
   AnySimulationModel model ; 
   double fZoomFactor  = 0.5; 
    protected double fMaxZoom = 10.0; 
    protected double fMinZoom = 1.e-4; 
    int    fScrollFactor = 0; 
    final double fIncrement = 1.05;
    final float kVerticalSpacesing = 60; 
    double fOffSetX= 0 ; 
    double fOffSetY = 0; 
    //--------------------------------------------------------------------------
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) 
    { String message = null; 
      String newline = "\n"  ; 
      int notches = e.getWheelRotation();
       if (notches < 0) 
       {
           message = "Mouse wheel moved UP "
                        + -notches + " notch(es)" + newline;
           this.zoomIn(e.getX() , e.getY());
           this.repaint( ); 
       } else if(notches!= 0 ) 
       {
           message = "Mouse wheel moved ****DOWN**** "
                        + notches + " notch(es)" + newline;
           this.zoomOut(e.getX() , e.getY());
            this.repaint( ); 
       }
       System.out.println( message);
    }

    //--------------------------------------------------------------------------
    /**
     * 
     */
    static class LeftAction implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            System.out.println(" DO STUFF" );
        }
    }
    class MouseClickingThing extends  MouseAdapter
    { 
        int pmouseX = 0 ,pmouseY = 0 ; 
         public void mousePressed(MouseEvent e) 
         {
            
             pmouseX = e.getX() ; 
             pmouseY = e.getY(); 
         }
         @Override
         public void mouseDragged( MouseEvent e)
         { 
            double diffX = ( e.getX()- pmouseX); 
            double diffY = ( e.getY() - pmouseY); 

             fOffSetX += (diffX*2);//(fZoomFactor*fZoomFactor))  ; 
                fOffSetY += (diffY*2);//(fZoomFactor*fZoomFactor))  ;
             pmouseX = e.getX() ; 
             pmouseY = e.getY(); 
             repaint() ; 
         }
    }

    public VisualisationPanel(AnySimulationModel simulation ) 
    {
        super(); 
        this.model = simulation;
        fOffSetX= this.getWidth()/2 ; 
        fOffSetY =  this.getHeight() /2; 
        MouseClickingThing thing = new MouseClickingThing( ) ;
        this.addMouseListener( thing ); 
        this.addMouseMotionListener( thing  );
        this.addMouseWheelListener(this);
        /*  LATER 
          addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                moveSquare(e.getX(),e.getY());
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                moveSquare(e.getX(),e.getY());
            }
        });
        
        
        */
    }
    
   //---------------------------------------------------------------------
    
    @Override 
     protected void paintComponent(Graphics g) 
     {
        super.paintComponent(g);
        assert g instanceof Graphics2D ; 
       
       Graphics2D g2 = (Graphics2D)g; 
        g2.setColor(Color.white);
     
       g2.fillRect(0,0,this.getWidth(),this.getHeight());
       g2.translate( fOffSetX, fOffSetY);
       g2.scale(fZoomFactor, fZoomFactor);
      // g.setColor(Color.red);
     //  g.drawLine(10,10, 100,100 );
       g.setColor(Color.red);
       g.drawLine(  0,0, model.getMaxHoz(), 0  ); 
       
       for( MobilePhone phone: model.getAllPhones()) 
       {
           phone.draw(g2);
       }

	   if (model instanceof StudentSimulation) {
		   // draw quadtree
			model.getQuadTree().draw(g2);
	   }
       model.step();
       Toolkit.getDefaultToolkit().sync();
         g2.scale(1,1);
         g2.translate( -fOffSetX,  -fOffSetY);
    }  
     
    
    public Dimension getPreferredSize() 
    {
        return new Dimension(1024,800);
    }
//---------------------------------------------------------------------  
public void zoomIn( int x, int y ) 
{ 
  double mapX, mapY ;

  mapX =  (x- fOffSetX)/fZoomFactor ; 
  mapY =  (y- fOffSetY)/fZoomFactor ; 

  double newscale  = fZoomFactor*fIncrement ; 
  if ( newscale >= fMaxZoom)
  { 
    maxZoomReached() ;  
    return ;
  } //
  
  double newOffX =  ((mapX *fZoomFactor)+fOffSetX)- (mapX *newscale); 
  double newOffY =  ((mapY *fZoomFactor)+fOffSetY)- (mapY *newscale); 
  fOffSetX = newOffX; 
  fOffSetY = newOffY   ; 

  fZoomFactor = newscale ;
} 
//---------------------------------------------------------------------
public void maxZoomReached( ) 
{ 
    
}
//---------------------------------------------------------------------
public void zoomOut(int x, int y )  
{ 
  double mapX, mapY ;
  mapX =  (x- fOffSetX)/fZoomFactor ; 
  mapY =  (y- fOffSetY)/fZoomFactor ; 

  double newscale  = fZoomFactor/fIncrement ; 
  if (newscale <= fMinZoom) { 
    minZoomReached(); 
    return ;
  } 
  double newOffX =  ((mapX *fZoomFactor)+fOffSetX)- (mapX *newscale); 
  double newOffY =  ((mapY *fZoomFactor)+fOffSetY)- (mapY *newscale); 
  fOffSetX = newOffX; 
  fOffSetY = newOffY   ; 

  fZoomFactor = newscale ;
}
public void minZoomReached()
{ 
    
    
}
//---------------------------------------------------------------------
public void zoomOut()
{
  if (fZoomFactor < fMinZoom) 
  { 
    minZoomReached(); 
    return ;
  } 
  fZoomFactor *= 1.0/fIncrement ;
  //repaint();
} 
//---------------------------------------------------------------------
public void zoomHome()
{ 
  fZoomFactor = 1.0;
}
//---------------------------------------------------------------------
public double convertWindowToMapCoordX( int x ) 
{ 
  return (x -  fOffSetX)/fZoomFactor ;
}
//---------------------------------------------------------------------
public double convertWindowToMapCoordY( int y ) 
{ 
  return (y -  fOffSetY)/fZoomFactor ;
}
//---------------------------------------------------------------------
public double convertMapCoordToWindowX( double xmap ) { 
  return (xmap *fZoomFactor)+fOffSetX;
} 
//---------------------------------------------------------------------
public double  convertMapCoordToWindowY( double  ymap ) { 
  return (ymap *fZoomFactor)+fOffSetY;
} 

    
}//===================END OF CLASS ==================================



 //--------------------------------------------------------------------------
    /**
     * 
     * @param outerFrame 
     */
   /*  public static void makeMenu(  JFrame outerFrame  )
    { 
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("File");
        JMenu m2 = new JMenu("Process");
 
        mb.add(m1);
        mb.add(m2);
        
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);
        m11.addActionListener (new LeftAction( ) );
        
        outerFrame.setJMenuBar( mb ) ;
        
    }
     public static void main(String args[])
     {
       model = new SimulationModel( ); 
       JFrame frame = new JFrame("My First GUI");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(800,600);
       
       JButton button = new JButton("Step");
    
       VisualisationPanel drawingArea = new VisualisationPanel() ; 
       
       makeMenu( frame ) ; 
    //   frame.getContentPane().add(BorderLayout.CENTER, ta);
       frame.getContentPane().add(BorderLayout.NORTH, button); // Adds Button to content pane of frame
       frame.getContentPane().add(BorderLayout.CENTER, drawingArea);   
       frame.setVisible(true);
    }*/ 
     
   /* public void paint(Graphics g)
    { 
       g.setColor(Color.white);
     
       g.fillRect(0,0,this.getWidth(),this.getHeight());
      // g.setColor(Color.red);
     //  g.drawLine(10,10, 100,100 );
       for( MobilePhone phone: model.getAllPhones()) 
       {
           phone.draw(g);
       }
       model.step();
       
    }*/ 