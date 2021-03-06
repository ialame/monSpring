package com.pca.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JColorComboBox extends JComboBox implements ActionListener {
    public Color[] colorsList;

    public JColorComboBox(Color[] cl) {
        super();
        colorsList = cl;
        ColorComboRenderer renderer = new ColorComboRenderer();
        for (int i = 0; i < cl.length; i++) {
            JLabel lbl = new JLabel("COLOR");
            lbl.setBackground(cl[i]);
            lbl.setForeground(cl[i]);
            this.addItem(lbl);
        }
        this.addActionListener(this);
        this.setRenderer(renderer);
    }


    public class ColorComboRenderer extends DefaultListCellRenderer {

        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            ColorComboRenderer c = (ColorComboRenderer) (super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus));
            if (index == -1) {
                c.setText("SELECT");
                c.setBackground(Color.BLACK);
                c.setForeground(Color.BLACK);
            } else {
                c.setText("COLOR");
                c.setBackground(((JLabel) value).getBackground());
                c.setForeground(((JLabel) value).getBackground());
            }
            return c;
        }

    }

    public void actionPerformed(ActionEvent e) {

        //((JLabel)this.getEditor().getEditorComponent()).setBackground(colorsList[this.getSelectedIndex()]);
        //((JLabel)this.getEditor().getEditorComponent()).setForeground(colorsList[this.getSelectedIndex()]);
    }

}

