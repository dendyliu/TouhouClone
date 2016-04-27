package View;
import javax.swing.*;
/**
 * HealthBar class 
 * This class will display health bar in frame
 * @author Dendy on 4/27/16
 */
public class HealthBar {
     private JLabel Health_Panel;
     private JProgressBar Health_Bar;
     /*
     * HealthBar Constructor
     */
     public HealthBar(){
         Health_Panel=new JLabel();
         Health_Bar = new JProgressBar();
         Health_Panel.setText("HP");
         Health_Bar.setValue(100);
   
         Health_Panel.setVisible(true);
         Health_Bar.setVisible(true);
     }
      /**
     * Return Health Bar
     * @return a JProgressBar containing of Health Bar 
     */
     public JProgressBar getBar(){
		 return Health_Bar;
	 }
     /**
     * Return Health Panel
     * @return a JPanel containing of Health Panel 
     */
     public JLabel getLabel(){
		 return Health_Panel;
	 }
     /*
      * Minus the Health Bar if player get hit
      */
     public void HealthMinus(){
         Health_Bar.setValue(Health_Bar.getValue()-5);
     }
     
}
