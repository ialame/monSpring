package com.ia.ebay9.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

class RendererJTablePok extends DefaultTableCellRenderer {
    static CarteUsJap c = null;
    private Font police = getFont();
    private FontMetrics fm = getFontMetrics(police);
    private int hauteurLigne = fm.getHeight();
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value,isSelected, hasFocus, row, column);
/*
        int largeur = table.getColumnModel ().getColumn (8).getWidth();
        String s ="";
        ArrayList<Promo> promos = (ArrayList) table.getModel().getValueAt(row,8);

        if(promos.size()==1) {
            s = "<ul style='list-style-type:none;margin:0;padding: 0;'>";

            s+= "<li>"+ promos.get(0).getNomBulbapedia()+"</li></ul>";
            s+= "</ul>";
        }else if (promos.size()>1) {
            s = "<ol style='margin-top: 0;padding-top: 0;'>";
            for (Promo str: promos)
                s +="<li>"+ str.getNomBulbapedia()+"</li>";
            s +="</ol>";
        }
        //String s = ((String) value).replace("\n","<br>");
        int hauteur = calculeHauteur(s, largeur,table,promos.size());
        if (hauteur > table.getRowHeight(row)) {
            table.setRowHeight(row, hauteur);
            System.out.println(column);
        }

*/
        switch (column){
            case 1:
            case 3:
            case 4:
            case 5:
                Color bg = Color.lightGray, fg = Color.blue;
                cell.setBackground(bg);
                cell.setForeground(fg);
                return cell;
            case 2:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                Color bg2 = Color.lightGray, fg2 = Color.RED;
                cell.setBackground(bg2);
                cell.setForeground(fg2);
                return cell;
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
                //cell.setPreferredSize(new Dimension( 15,15));
                return cell;
            case 18:
            case 19:
                cell.setForeground(Color.BLACK);
                cell.setBackground(Color.lightGray );
                return cell;
                /*
            case 8:
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                //table.getColumnModel ().getColumn(8).setPreferredWidth(largeur);
                label.setAlignmentY(JLabel.TOP_ALIGNMENT);// BOTTOM_ALIGNMENT);
                label.setText("<html>" + s + "</html>");
                return label;
                */

            default:

                Color bg3 = Color.WHITE, fg3 = Color.BLACK;
                cell.setBackground(bg3);
                cell.setForeground(fg3);
                return cell;
        }

    }
    private int calculeHauteur(String s, int largeur, JTable t,int taille)
    {
        String[] lignes = s.split("<li>");
        int nbLignes = lignes.length;
        for (String ligne : lignes)
        {
            nbLignes += (int) Math.ceil((getFm().stringWidth(ligne)+taille>1?40:0 )/largeur);
        }
        if(taille==1)
            return (nbLignes) * hauteurLigne;
        else
            return nbLignes * hauteurLigne;
    }

    public FontMetrics getFm() {
        return fm;
    }

    public void setFm(FontMetrics fm) {
        this.fm = fm;
    }
}

