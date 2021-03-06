package com.pca.gui;

import com.pca.entity.CartePokemon;
import com.pca.entity.ExtensionUS;
import com.pca.entity.SerieUS;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CartePokemonEditeur  extends AbstractCellEditor implements TableCellEditor, ActionListener {
    public CartePokemon carte;
    public ExtensionUS extension;
    public SerieUS serie;
    public JButton button, boutonOK;
    public JFrame jf ;
    public JComboBox<SerieUS> comboBoxSerie = new JComboBox<>();
    public JComboBox<ExtensionUS> comboBoxExtension = new JComboBox<>();
    public JComboBox<CartePokemon> comboBoxCarte = new JComboBox<>();
    ModeleTablePok mdc ;
    CarteUsJap carteUsJap;
    protected static final String EDIT = "edit";

        public CartePokemonEditeur() {
            boutonOK = new JButton("OK");
            button = new JButton();
            button.setActionCommand(EDIT);
            button.addActionListener(this);
            button.setBorderPainted(false);

            jf=new JFrame("Editeur de Cartes Pokemons");
            Container container = jf.getContentPane();
            container.setLayout(new BorderLayout());
            JPanel jPanel = new JPanel(new GridLayout(3,2));
            jPanel.add(new JLabel("Serie :"));
            jPanel.add(comboBoxSerie = new JComboBox());
            jPanel.add(new JLabel("Extension :"));
            jPanel.add(comboBoxExtension = new JComboBox());
            jPanel.add(new JLabel("Carte :"));
            jPanel.add(comboBoxCarte = new JComboBox());
            comboBoxSerie.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(comboBoxSerie.getItemCount()>0) {
                        SerieUS serieChoisie = (SerieUS) comboBoxSerie.getSelectedItem();
                        comboBoxExtension.removeAllItems();
                        for (ExtensionUS extensionUS : serieChoisie.getExtensionsUS())
                            comboBoxExtension.addItem(extensionUS);

                        comboBoxCarte.removeAllItems();
                        extension = (ExtensionUS) comboBoxExtension.getSelectedItem();
                        for (CartePokemon cartePokemon : extension.getCartes())
                            comboBoxCarte.addItem(cartePokemon);
                    }
                }
            });
            container.add(jPanel,BorderLayout.NORTH);
            JPanel pBouton = new JPanel();
            pBouton.add(boutonOK);
            container.add(pBouton,BorderLayout.SOUTH);
            //jf.pack();
            jf.setSize(300,200);
            boutonOK.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    carte =(CartePokemon) comboBoxCarte.getSelectedItem();
                    carteUsJap.usBDD=carte;
                    mdc.fireTableDataChanged();
                    jf.dispose();
                }
            });
           //
        }

        /**
         * Handles events from the editor button and from
         * the dialog's OK button.
         */
        public void actionPerformed(ActionEvent e) {
            if (EDIT.equals(e.getActionCommand())) {
                //The user has clicked the cell, so
                //bring up the dialog.
                jf.setVisible(true);

                //Make the renderer reappear.
                fireEditingStopped();

            } else { //User pressed dialog's "OK" button.
                carte =(CartePokemon) comboBoxCarte.getSelectedItem();
            }
        }

        //Implement the one CellEditor method that AbstractCellEditor doesn't.
        public CartePokemon getCellEditorValue() {
            return carte;
        }

        //Implement the one method defined by TableCellEditor.
        public Component getTableCellEditorComponent(JTable table,
                                                     Object value,
                                                     boolean isSelected,
                                                     int row,
                                                     int column) {
            mdc = (ModeleTablePok) table.getModel();
            carteUsJap =(CarteUsJap) mdc.getCartes().get(row);

            carte = (CartePokemon) value;
            ExtensionUS extension = carte.getExtensionUS();
            SerieUS serie = extension.getSerieUS();
            comboBoxSerie.removeAllItems();
            for(SerieUS s : ApplicationPM.getSeriesUS()){
                comboBoxSerie.addItem(s);
            }
            comboBoxSerie.setSelectedItem(serie);
            comboBoxExtension.removeAllItems();
            for(ExtensionUS e : serie.getExtensionsUS()){
                comboBoxExtension.addItem(e);
            }
            comboBoxExtension.setSelectedItem(extension);
            comboBoxCarte.removeAllItems();
            for(CartePokemon c : extension.getCartes()){
                comboBoxCarte.addItem(c);
            }
            comboBoxCarte.setSelectedItem(carte);
            return button;
        }
    }