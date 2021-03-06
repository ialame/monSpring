package com.ia.ebay9.gui;

import javax.swing.*;
import java.awt.*;

public class StatutCouleur extends JPanel{
    StatutCouleur() {
        this.setLayout(new GridLayout(K.elements.length, 1));
        for (int i = 0; i < K.elements.length; i++) {
            JPanel panel = new JPanel();
            panel.add(new JLabel(K.elements[i]));
            panel.setForeground(K.couleurInverse[i]);
            panel.setBackground(K.couleur[i]);
            panel.setPreferredSize(new Dimension(400, 40));
            add(panel);
        }
    }
}
