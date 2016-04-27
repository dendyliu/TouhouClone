/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import javax.swing.*;

/**
 *
 * @author Asus X550ZE
 */
public class MainMenu extends JPanel{
	public final int width;
	public final int height;
	private JFrame frame;
	private JButton Start;
	private JLabel Logo;
	private JButton About;
	
	public MainMenu(){
		width = 600;
		height = 800;
		frame = new JFrame("Touhou Clone");
		frame.setSize(600,800);
		frame.setResizable(false);
		frame.setVisible(true);
		Start = new JButton("Start");
		Logo= new JLabel();
		About= new JButton("About");
        }
                
     private void StartActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
    }   
    
      private void AboutActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
    }   
}
