
package View.MainMenu;

import Controller.Main;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Start Class
 * This class is a class that will display the Start Button
 * @author Alif on 4/27/16
 */
public class Start implements MouseListener {
    public JButton startx;
    /*
    * Start Constructor
    */
    public Start() {
        startx = new JButton("Start Game");
        startx.addMouseListener(this);
    }
    /*
    * Do some procedure when start button clicked
    * @param me mouse event
    */
    @Override
    public void mouseClicked(MouseEvent me) {
        java.awt.EventQueue.invokeLater(() -> {
            Main.startGame();
            Main.initBullets();
        });
    }
    
    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {

    }
}