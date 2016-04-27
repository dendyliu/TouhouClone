/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import javax.swing.*;
/**
 *
 * @author Dendy
 */
public class HealthBar {
     private JLabel Health_Panel;
     private JProgressBar Health_Bar;
     
     public HealthBar(){
         Health_Panel=new JLabel();
         Health_Bar = new JProgressBar();
         Health_Panel.setText("HP");
         Health_Bar.setValue(100);
   
         Health_Panel.setVisible(true);
         Health_Bar.setVisible(true);
     }
     public JProgressBar getBar(){
		 return Health_Bar;
	 }
	 public JLabel getLabel(){
		 return Health_Panel;
	 }
     public void HealthMinus(){
         Health_Bar.setValue(Health_Bar.getValue()-5);
     }
	 public void HealthPlus(){
		 Health_Bar.setValue(Health_Bar.getValue()+5);
	 }
}
