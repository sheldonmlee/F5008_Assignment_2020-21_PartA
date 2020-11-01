/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package org.KF5008.AssessmentA;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.Timer;

/**
 *
 * @author nickdalton
 */
public class PhoneSimApplication extends JFrame
{
	AnySimulationModel model ;
	Timer runReminderTimer =null  ;
	JButton stepButton ;
	VisualisationPanel drawingArea;
	PhoneSimApplication self ;
	
	class SizeAction implements ActionListener
	{
		int size = 10 ;
		String name ;
		JFrame app ;
		public SizeAction( int size , String name  )
		{
			super() ;
			this.size = size ;
			this.name = name.replace("New", ""+size+" ");
		}
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			newSize( size ) ;
			
			drawingArea.repaint();
			self.setTitle(name);
		}
		
	}
	//--------------------------------------------------------------------------
	class StepAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			model.step();
			drawingArea.repaint();
		}
	}
	//--------------------------------------------------------------------------
	class ExitAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.println(" Quit" );
			System.exit(0);
		}
	}
	//--------------------------------------------------------------------------
	class RunAction implements  ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.println(" Run"  );
			if( runReminderTimer == null )
			{
				RunStep rs = new RunStep();
				runReminderTimer = new Timer( 25 , rs   );
				runReminderTimer.start() ;
				//  runReminderTimer.scheduleAtFixedRate( rs , 100, 25);
			}
		}
	}
	//--------------------------------------------------------------------------
	class RunStep extends TimerTask implements   ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			//  System.out.println(" STEP " + this  );
			self.repaint() ;
		}
		@Override
		public void run()
		{
			System.out.println(" STEP 2  "    );
		}
	}
	//--------------------------------------------------------------------------
	/**
	 *
	 * @param outerFrame
	 */
	public  void makeMenu(  JFrame outerFrame  )
	{
		JMenuBar mb = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu m2 = new JMenu("Process");
		
		mb.add(fileMenu);
		mb.add(m2);
		
		JMenuItem m11 = new JMenuItem("Step");
		JMenuItem m22 = new JMenuItem("Exit");
		JMenuItem runMenu  = new JMenuItem("Run");
		
		addSizedMenu( fileMenu, "New Small", 10 ) ;
		addSizedMenu( fileMenu, "New Medium", 100 ) ;
		addSizedMenu( fileMenu, "New Venti", 500 ) ;
		addSizedMenu( fileMenu, "New Large", 1000 ) ;
		addSizedMenu( fileMenu, "New Grande", 2000 ) ;
		addSizedMenu( fileMenu, "New Super Grande", 4000 ) ;
		addSizedMenu( fileMenu, "New Very Large", 10_000 ) ;
		addSizedMenu( fileMenu, "New Village", 30_000 ) ;
		addSizedMenu( fileMenu, "New Town", 60_000 ) ;
		addSizedMenu( fileMenu, "New Urban Small", 100_0000 ) ;
		addSizedMenu( fileMenu, "New CPU Crusher", 200_0000 ) ;
		addSizedMenu( fileMenu, "New CPU Kiss of death", 1_000_0000 ) ;
		
		fileMenu.add(m22);
		m11.addActionListener (new StepAction( ) );
		m22.addActionListener(  new ExitAction( ) );
		runMenu.addActionListener( new RunAction( ) );
		m2.add( runMenu  );
		
		outerFrame.setJMenuBar( mb ) ;
	}
	//--------------------------------------------------------------------------
	protected void addSizedMenu(  JMenu fileMenu , String s,int size  )
	{
		assert s != null; // no null menu items.
		JMenuItem m = new JMenuItem(s);
		
		m.addActionListener(new SizeAction(size,s)  );
		fileMenu.add( m );
	}
	public PhoneSimApplication(String string) throws HeadlessException
	{
		super(string);
		model = makeModel( ) ;
		model.generate(0, 0,0 , 1024, 800);
		self = this ;
	}
	//--------------------------------------------------------------------------
	void newSize( int size )
	{
		assert size > 1 ;
		
		// int hoz = 100 + (int)(  size * 1.5  ); // working
		// int v = 100 +   (int)  ( size  * 1.5)   ;
		int hoz = 100 + (int)(  Math.sqrt(size) * 25  );
		int v = 100 +   (int)( Math.sqrt(size) * 25  ) ; // 50 bit big
		
		System.out.println("NEW MODEL SIZE ="+ size  + " " + hoz+ " +" + v  );
		model.generate( size , 0,0 , hoz, v);
	}
	//--------------------------------------------------------------------------
	void setupUI()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024,800);
		
		stepButton = new JButton("Step");
		stepButton.addActionListener( new StepAction( ) );
		
		drawingArea = new VisualisationPanel( model ) ;
		
		makeMenu( this ) ;
		
		this.getContentPane().add(BorderLayout.NORTH, stepButton); // Adds Button to content pane of frame
		this.getContentPane().add(BorderLayout.CENTER, drawingArea);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pack();
		model.generate(600, 0,0 , drawingArea.getWidth(), drawingArea.getHeight());
		model.getAllPhones().get(0).setHasMessage(true)  ;
		this.setVisible(true);
		
	}
	//--------------------------------------------------------------------------
	AnySimulationModel makeModel( )
	{
		return new  StudentSimulation ( 0, 0,0 , 1024, 800  ); // GeniousSimulationModel(0, 0,0 , 1024, 800  );
	}
	//--------------------------------------------------------------------------
	public static void main(String args[])throws HeadlessException
	{
		// example of being lazy and using an anommous interface/class to give method for agument.
		java.awt.EventQueue.invokeLater(new Runnable()
		{
			public void run() {
				PhoneSimApplication app = new PhoneSimApplication("Phone Simuation Student");
				app.setupUI();
			}
		});
	}
}
