package com.pca.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;


public class Titre extends JLabel {
    public Titre() {
        setBackground(Color.white);
        setSize(new Dimension(400, 100));
    }

    public void paint(Graphics g) {
        Graphics2D g2D;
        g2D = (Graphics2D) g;
        FontRenderContext frc = g2D.getFontRenderContext();
        Font font1 = new Font("LingWai SC", Font.BOLD, 50);//Herculanum Apple Chancery  Druk Wide
        String str1 = new String("Cartes Pokemon JAP");
        TextLayout tl = new TextLayout(str1, font1, frc);
        g2D.setColor(Color.gray);
        tl.draw(g2D, 30, 50);
    }

    public static void main(String s[]) {
        JFrame frame1 = new JFrame("2D Text");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame1.getContentPane().add("Center", new Titre());
        frame1.pack();
        //frame1.setSize(new Dimension(400, 100));
        frame1.setVisible(true);
    }
}