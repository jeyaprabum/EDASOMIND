package de.xabe.fuzzy;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import javax.swing.*;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


/**
 * @author max
 *
 */
public class UniversityChooser extends Applet {
   
   // members
   private static final long serialVersionUID = 1L;
   private FIS fis;
   private FunctionBlock fb;
   private JFrame myFrame;
   
   private JSlider contact;
   private JSlider reputation;
   private JSlider costsofliving;
   
   private JButton BT_calculate;
   private JButton BT_verbose;
   private JButton BT_reload;
    
   /**
    * @param args
    * @throws Exception
    */
   public static void main(String[] args) throws Exception {
      UniversityChooser tc = new UniversityChooser();
      // load FCL
      tc.loadFCL();
      // Start Gui
      tc.initGUI();
   }
    
	/**
	 * Loads FCL-File, if needed also for reload
	 * @throws Exception
	 */
	public void loadFCL() throws Exception{
	   // Load File
      fis = FIS.load(getClass().getResourceAsStream("tipping.FCL"), true);
      // Get function block
      fb = fis.getFunctionBlock("UniversityDecision");
	}

	/**
	 * Create GUI with Inputs
	 */
	private void initGUI(){
	   // FRAME
		myFrame = new JFrame("Choose your University");
		myFrame.setSize(650,350);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// BUTTONS
		BT_calculate = new JButton("Calculation Decision-AID");        
		BT_calculate.addActionListener(new Calculator());
		
		BT_verbose = new JButton("How is it calculated?");        
		BT_verbose.addActionListener(new VerboseCalculator());		
		
		BT_reload = new JButton("Reload FCL");        
		BT_reload.addActionListener(new Reloader());		
		
		// PANEL / GRID
		JPanel panel = new JPanel();
		GridLayout layout = new GridLayout(5,2);
		panel.setLayout(layout);

		// Label
		myFrame.getContentPane().add(new JLabel("Welcome. Please enter the ratings of the university:"),BorderLayout.NORTH);
		
		// Slider
		panel.add(new JLabel("How is the contact to the professors?"));
		contact=getSlider(0,100, 10, 10);
		panel.add(contact);
		
		panel.add(new JLabel("How is the reputation?"));
		reputation=getSlider(0,100, 10, 10);
		panel.add(reputation);
		
		panel.add(new JLabel("How are the costs of living?"));
		costsofliving=getSlider(200,600, 50, 50);
		panel.add(costsofliving);
		
		// Add Buttons
		JPanel buttons = new JPanel();
		buttons.add(BT_calculate);
		buttons.add(BT_verbose);
		buttons.add(BT_reload);
		
		// Options
		myFrame.getContentPane().add(buttons,BorderLayout.SOUTH);
		myFrame.getContentPane().add(panel);
		myFrame.setVisible(true);
	}
	
	class Calculator implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			// Set inputs
			fb.setVariable("contact2students", contact      .getValue());
			fb.setVariable("reputation"      , reputation   .getValue());
			fb.setVariable("costofliving"    , costsofliving.getValue());
			fb.evaluate();
			// Get Decision
			Double decision = fb.getVariable("decision").defuzzify();
			// Show Result
			JOptionPane.showMessageDialog(myFrame, "the decision is "+decision.intValue() +" % yes");
		}
	}
	
	class VerboseCalculator implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
		   // Set Inputs
         fb.setVariable("contact2students", contact      .getValue());
         fb.setVariable("reputation"      , reputation   .getValue());
         fb.setVariable("costofliving"    , costsofliving.getValue());
			fb.evaluate();
         // Get Decision
         Double decision = fb.getVariable("decision").defuzzify();

         // Create Analysis
         String output = "The decision should be " + decision + "\n\n";
			       output += "The membership values of the fuzzy variables are: \n";
			       output += fb.getVariable("contact2students")+"\n";
			       output += fb.getVariable("reputation")+"\n";
			       output += fb.getVariable("costofliving")+"\n";	
			       output += fb.getVariable("decision")+"\n";	
			// Show result
			JOptionPane.showMessageDialog(myFrame, output);
		}
	}
	
	class Reloader implements ActionListener{
	   public void actionPerformed(ActionEvent ae) {
	      try {
	         loadFCL();
         } catch (Exception e) {
            e.printStackTrace();
         }
	   }
	}
	
	/***********************************************************************
	 * HELPER
	 ***********************************************************************
	 */

   /**
    * Creates a slider with necessary options
    * @param min
    * @param max
    * @param spacingminor
    * @param spacingmajor
    * @return
    */
   private JSlider getSlider (int min, int max, int spacingminor, int spacingmajor){
      JSlider slider = new JSlider(min, max);
      slider.setMajorTickSpacing(spacingmajor);
      slider.setMinorTickSpacing(spacingminor);
      slider.setPaintTicks(true);
      slider.setPaintLabels(true);
      return slider;
   }
   
	
}
