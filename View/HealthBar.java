package View;
import javax.swing.*;
import java.awt.*;

/**
 * HealthBar class 
 * This class will display health bar in frame
 * @author Dendy on 4/27/16
 */
public class HealthBar {
     private JLabel Health_Panel;
    private JProgressBar bossHealthBar;
    private JProgressBar playerHealthBar;
     /*
     * HealthBar Constructor
     */
     public HealthBar(int bossHealth, int playerHealth) {
         Health_Panel=new JLabel();
         Health_Panel.setText("HP");
         Health_Panel.setVisible(true);

         bossHealthBar = new JProgressBar(0, bossHealth);
         bossHealthBar.setValue(bossHealth);
         bossHealthBar.setVisible(true);
         bossHealthBar.setStringPainted(true);
         bossHealthBar.setForeground(Color.RED);
         bossHealthBar.setString("Boss HP: " + bossHealth);

         playerHealthBar = new JProgressBar(0, playerHealth);
         playerHealthBar.setValue(playerHealth);
         playerHealthBar.setVisible(true);
         playerHealthBar.setStringPainted(true);
         playerHealthBar.setString("Player HP: " + playerHealth);
     }
      /**
     * Return Health Bar
     * @return a JProgressBar containing of Health Bar 
     */
      public JProgressBar getBossBar() {
          return bossHealthBar;
      }

    public JProgressBar getPlayerBar() {
        return playerHealthBar;
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
         bossHealthBar.setValue(bossHealthBar.getValue() - 5);
     }
     
}
