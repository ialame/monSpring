package com.ia.ebay9.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class MultiLignesRenderer extends DefaultTableCellRenderer
{
    private Font police = getFont();
    private FontMetrics fm = getFontMetrics(police);
    private int hauteurLigne = fm.getHeight();


    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int col)
    {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        int largeur = table.getColumnModel ().getColumn (col).getWidth();
        String s = ((String) value).replace("\n","<br>");
        int hauteur = calculeHauteur(s, largeur,table);
        if (hauteur > table.getRowHeight(row)) table.setRowHeight(row, hauteur);
        label.setAlignmentY(JLabel.BOTTOM_ALIGNMENT);
        label.setText("<html>" + s + "</html>");
        return label;
    }

    private int calculeHauteur(String s, int largeur, JTable t)
    {
        String[] lignes = s.split("<br>");
        int nbLignes = lignes.length;
        for (String ligne : lignes)
        {
            nbLignes += (int) Math.floor(t.getFontMetrics(getFont()).stringWidth(ligne)/largeur);
        }
        return nbLignes * hauteurLigne;
    }

}