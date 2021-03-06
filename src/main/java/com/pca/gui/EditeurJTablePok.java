package com.pca.gui;

import com.pca.model.SerieUS;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class EditeurJTablePok extends AbstractCellEditor implements TableCellEditor {
    static CarteUsJap c =null;
    private JComboBox<SerieUS> jComboBoxSerie = new JComboBox();
    boolean cellEditingStopped = false;

    @Override
    public Object getCellEditorValue() {
        return jComboBoxSerie.getSelectedItem();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        ModeleTablePok mdc = (ModeleTablePok) table.getModel();
        CarteUsJap carte =(CarteUsJap) mdc.getCartes().get(row);

        if(column==0) {
            //System.out.println("value=" + value);
            c = (CarteUsJap) mdc.getRow(Integer.parseInt(String.valueOf(value))-1);
        }

        //setBackground(Color.LIGHT_GRAY);//com.pca.gui.ApplicationPM.couleur[c.getStatus()]);
        //setBackground(K.couleur[c.getStatus()]);
        //setBackground((row % 2 == 0) ? Color.LIGHT_GRAY : Color.WHITE);
        /*if (column == 13) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            cell.setBackground(K.couleur[c.getStatus()]);
        }*/
        JPanel jp=null;
        // MIGRATION
        /*switch (column){

            case 1:
                JComboBox<CartePokemon> jComboBoxCarte = new JComboBox();
                jComboBoxCarte.removeAllItems();
                if(carte==null || carte.us==null)return null;
                ExtensionUS extension = carte.us.getExtensionUS();
                ArrayList<CartePokemon> cartes = extension.getCartes();//.toString();
                for (CartePokemon cartePokemon : cartes)
                    jComboBoxCarte.addItem(cartePokemon);

                //jComboBox = new JComboBox(vector);
                jComboBoxCarte.setSelectedItem(value);
                //jComboBoxCarte.setEditable(true);
                jComboBoxCarte.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        CartePokemon cartePokemon = (CartePokemon) e.getItem();
                        carte.us=cartePokemon;
                        mdc.fireTableDataChanged();
                    }
                });
                return jComboBoxCarte;
            case 10:
                try {
                    Promos promos = (Promos) value;
                    jp = new JPanel();
                    JTextArea xe = new JTextArea(promos.size(), 30);
                    xe.setEditable(true);
                    for (Promo p : promos)
                        xe.append(p.bulbaPedia);
                    xe.setBackground(Color.blue);
                    //int lineCount = xe.getLineCount();

                    Font f = xe.getFont();
                    FontMetrics fm = xe.getFontMetrics(f);
                    int fheight = fm.getHeight();
                    int rowHeight = lineCount * fheight;
                    setRowHeight(row, rowHeight);
                    jp.add(xe);
                    jp.setMinimumSize(new Dimension(100, 100));
                    return (jp);
                }catch (Exception excep){
                    System.out.println("value"+value+"  exception: "+excep);
                }
                return null;
            case 13:
                jp = new JPanel();
                JLabel jl = new JLabel(value.toString());
                jp.setBackground(K.couleur[c.getStatus()]);
                jp.add(jl);
                return jp;
            default:
                return new JTextField();
                //return super.getCellEditor(row, column);
        }*/
        //}else
        //    return super.getTableCellEditor .getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    return null;  // MIGRATION
    }

    @Override
    public boolean stopCellEditing() {
        return cellEditingStopped;
    }
}

class Ecouteur implements ItemListener{

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}