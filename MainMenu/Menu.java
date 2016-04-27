/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Alif
 */
public class Menu {
    JPanel panel1;
    public Menu(MouseListener startListener){
        panel1 = new JPanel();
        JFrame frame = new JFrame();
        JButton startButton = new JButton("Start Game");
        startButton.addMouseListener(startListener);

        frame.setVisible(true);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        panel1.add(startButton);
        panel1.setLayout(null);
        startButton.setBounds(175,200,100,50);
        frame.add(panel1);
        frame.setLocationRelativeTo(null);
    }
}
