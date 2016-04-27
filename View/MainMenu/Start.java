/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.MainMenu;

import Controller.Main;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Alif
 */
public class Start implements MouseListener {
    public JButton startx;

    public Start() {
        startx = new JButton("Start Game");
        startx.addMouseListener(this);
    }

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