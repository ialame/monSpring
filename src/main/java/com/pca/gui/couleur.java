package com.pca.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Created by fxc on 28/10/2019.
 */
public class couleur extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent
            (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        Component cell = super.getTableCellRendererComponent
                (table,value,isSelected,hasFocus,row,column);
        if ((value != null) && (value.equals("4")) && row==13) {
            cell.setBackground(Color.GREEN);
        }
        /*
        else {
            if ((value != null) && (value.equals("4"))) {
                cell.setBackground(Color.RED);
            }
            else {
                cell.setBackground(Color.white);
            }
        }
        */
        return cell;
    }
}
