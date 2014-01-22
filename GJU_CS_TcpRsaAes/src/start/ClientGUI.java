package start;


import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import start.client.PlainTCPClient;
import start.client.SecureTCPClient;


/**
 * @author max
 *
 */
public class ClientGUI extends Applet {
   
   // members
   private static final long serialVersionUID = 1L;
   private JFrame myFrame;
   
   private JButton BT_SEND_SAFE;
   private JButton BT_SEND_PLAIN;
   
   private JTextField textfieldOutput;
   private JTextField textfieldInput;
    
   /**
    * @param args
    * @throws Exception
    */
   public static void main(String[] args) throws Exception {
      ClientGUI tc = new ClientGUI();
      // Start Gui
      tc.initGUI();
   }
    
	/**
	 * Create GUI with Inputs
	 */
	private void initGUI(){
	   // FRAME
		myFrame = new JFrame("Send Data over TCP");
		myFrame.setSize(650,350);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// BUTTONS
		BT_SEND_SAFE = new JButton("Send encrypted");        
		BT_SEND_SAFE.addActionListener(new SendSafe());
		
		BT_SEND_PLAIN = new JButton("Send plain");        
		BT_SEND_PLAIN.addActionListener(new SendPlain());		
		
		
		// PANEL / GRID
		JPanel panel = new JPanel();
		GridLayout layout = new GridLayout(5,2);
		panel.setLayout(layout);

		// Label
		myFrame.getContentPane().add(new JLabel("Welcome. Please enter the text you want to send to the server"),BorderLayout.NORTH);
		
		// Slider
		panel.add(new JLabel("Input Text"));
		textfieldInput = new JTextField();
		panel.add(textfieldInput);
		
		panel.add(new JLabel("Output Text"));
      textfieldOutput = new JTextField();
      panel.add(textfieldOutput);
		
		// Add Buttons
		JPanel buttons = new JPanel();
		buttons.add(BT_SEND_SAFE);
		buttons.add(BT_SEND_PLAIN);
		
		// Options
		myFrame.getContentPane().add(buttons,BorderLayout.SOUTH);
		myFrame.getContentPane().add(panel);
		myFrame.setVisible(true);
	}
	
	class SendSafe implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
		   //textfieldOutput.setText(new SecureTCPClient().handleSecureMessage(textfieldInput.getText()));
		}
	}
	
	class SendPlain implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
		   textfieldOutput.setText(new PlainTCPClient().send(textfieldInput.getText()));
		}
	}
}
