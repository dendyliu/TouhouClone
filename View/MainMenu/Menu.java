/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.MainMenu;

import javax.swing.*;

/**
 * Menu class
 * This class is a class that will display the starting menu
 * @author Alif on 4/27/16
 */
public class Menu {
    JPanel panel1;
    /*
     * Menu Constructor
     */
    public Menu() {
        panel1 = new JPanel();
        JFrame frame = new JFrame();
        Start s = new Start();
        JButton start = s.startx;
        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel1.add(start);
        panel1.setLayout(null);
        start.setBounds(175, 200, 100, 50);
        frame.add(panel1);
        frame.setLocationRelativeTo(null);
    }
}