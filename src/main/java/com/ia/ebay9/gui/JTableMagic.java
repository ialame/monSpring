package com.ia.ebay9.gui;

import com.ia.ebay9.entity.CartePokemon;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by fxc on 26/11/2019.
 */
public class JTableMagic extends JPanel{
    public static int N=0;// nombre des cartes dans parse...
    private ModeleTableMagic modele;// = new ModeleDynamiqueObjet();
    private JTable tableau;
    private ArrayList cartes;
    private ApplicationPM app;
    public JButton boutonSave=null;
    public JComboBox filtres =null;
    TableRowSorter<ModeleTableMagic> sorter;
    public JComboBox comboBoxSerie = null, comboBoxExtension =null,comboBoxCarte =null;
    public JTableMagic(ArrayList cartes, ApplicationPM app) {//ArrayList<CarteJAP>
        this.app=app;
        this.cartes=cartes;
        modele = new ModeleTableMagic(cartes,app);
        setLayout(new BorderLayout());
        tableau = new JTable(modele){
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Integer.class;
                    case 10:
                    case 14:
                        return Boolean.class;
                    default:
                        return Object.class;
                }
            }
            public TableCellEditor getCellEditor(int row, int column)
            {
                return super.getCellEditor(row, column);
            }
        };

        modele.setTableau(tableau);
        ///////////////////////////////////
        tableau.setDefaultRenderer(Object.class, new RendererJTablePok());

        ////////////////////////////////////////
        add(new JScrollPane(tableau), BorderLayout.CENTER);

        JPanel boutons = new JPanel();
        JButton x;
        boutonSave=new JButton(new SaveAction());

        boutons.add(new JButton(new AddAction()));
        boutons.add(new JButton(new RemoveAction()));
        boutons.add(new JButton(new ClearAction()));
        boutons.add(new JButton(new ApplicationFilters()));
        boutons.add(new JButton(new SaveImages()));
        boutons.add(boutonSave);



        JLabel[] colorListe = new JLabel[K.statusMagic.length];
        Color[] colorsList = K.couleur;
        ColorComboRendererMagic rendererFiltres = new ColorComboRendererMagic();
        for (int i = 0; i < K.statusMagic.length; i++) {
            colorListe[i] = new JLabel(K.statusMagic[i]);
            colorListe[i].setBackground(colorsList[i]);
            colorListe[i].setForeground(Color.BLACK);

        }

        filtres = new JComboBox(colorListe);
        filtres.setRenderer(rendererFiltres);
        boutons.add(filtres);
        filtres.addActionListener(new FilterAction());
        add(boutons, BorderLayout.SOUTH);

        RowFilter<ModeleTableMagic,Integer> ageFilter = new RowFilter<ModeleTableMagic,Integer>() {
            public boolean include(Entry<? extends ModeleTableMagic, ? extends Integer> entry) {
                ModeleTableMagic personModel = entry.getModel();
                CartePokemon carte = personModel.getRow(entry.getIdentifier());
                //if (carte.getId() > 3565) {
                // Returning true indicates this row should be shown.
                return true;
                //}
                // Age is <= 20, don't show it.
                //return false;
            }
        };
        ModeleTableMagic model = getModele();
        sorter = new TableRowSorter<ModeleTableMagic>(model);
        sorter.setRowFilter(ageFilter);
        tableau.setRowSorter(sorter);
        tableau.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );

        JScrollPane pane = new JScrollPane(tableau);
        add(pane);




    }

    public static void main(String[] args) {
        // new JTableCarteJAP().setVisible(true);
    }

    private class AddAction extends AbstractAction {
        private AddAction() {
            super("Ajouter une ligne");
        }

        public void actionPerformed(ActionEvent e) {
            modele.addCarte(new CarteMagic());
        }
    }
    private class ClearAction extends AbstractAction {
        private ClearAction() {
            super("Tout effacer");
        }

        public void actionPerformed(ActionEvent e) {

            int N = tableau.getRowCount();

            for(int i = N - 1; i >= 0; i--){
                modele.removeCarte(i);
            }
            JTablePok.N=0;
            //app.pokemon.bAjouter.setEnabled(true);
        }
    }

    private class RemoveAction extends AbstractAction {
        private RemoveAction() {
            super("Supprimer la ligne sélectionée");
        }


        public void actionPerformed(ActionEvent e) {
            int[] selection = tableau.getSelectedRows();
            int[] modelIndexes = new int[selection.length];

            for(int i = 0; i < selection.length; i++){
                modelIndexes[i] = tableau.getRowSorter().convertRowIndexToModel(selection[i]);
            }

            Arrays.sort(modelIndexes);

            for(int i = modelIndexes.length - 1; i >= 0; i--){
                modele.removeCarte(modelIndexes[i]);
            }

        }
    }
    private class SaveAction extends AbstractAction {
        private SaveAction() {
            super("Sauvegarder");
        }


        public void actionPerformed(ActionEvent e) {
            try {
                //((CarteMagic) modele.getCartes().get(0)).getExtensionMagic().save(com.ia.ebay9.gui.ApplicationPM.getTable_ExtensionMagic());
                for (Object c : modele.getCartes()) {
                    CarteMagicComplete carte = (CarteMagicComplete) c;
                        carte.save();
                }

            } catch (Exception ex) {
                System.out.println("Dans JTableMagic : " + ex);
            }
        }
    }

    private class FilterAction extends AbstractAction {
        private FilterAction() {
            super("Filter");
        }

        public void actionPerformed(ActionEvent e) {
            RowFilter<ModeleTableMagic,Integer> ageFilter = new RowFilter<ModeleTableMagic,Integer>() {
                public boolean include(Entry<? extends ModeleTableMagic, ? extends Integer> entry) {
                    ModeleTableMagic personModel = entry.getModel();
                    CarteMagic carte = personModel.getRow(entry.getIdentifier());
                    JComboBox c = (JComboBox) e.getSource();
                    int item = c.getSelectedIndex();
                    c.getRenderer().notify();
                    //c..setText(com.ia.ebay9.gui.ApplicationPM.statusMagic[item]);
                    /*if(item>0 && carte.getStatus()==item+com.ia.ebay9.gui.ApplicationPM.MAGIC) {
                        switch (item + com.ia.ebay9.gui.ApplicationPM.MAGIC) {
                            case com.ia.ebay9.gui.ApplicationPM.STATUS_MAGIC_SPLIT:
                                ArrayList<CarteMagic> liste = modele.getCartes();
                                for(CarteMagic cm: liste){
                                    // A completer ici
                                }
                                break;
                        }

                        return true;
                    }else if(item==0)
                        return true;*/

                    return false;
                }
            };
            ModeleTableMagic model = getModele();
            //TableRowSorter<ModeleDynamiqueCarte> sorter = new TableRowSorter<ModeleDynamiqueCarte>(model);
            sorter.setRowFilter(ageFilter);

            tableau.setDefaultRenderer(Object.class, new RendererJTablePok());

        }
    }

    private class ApplicationFilters extends AbstractAction {
        private ApplicationFilters() {
            super("Appliquer les filtres");
        }

        public void actionPerformed(ActionEvent e) {

///////////////////////////////////////////////////////////////////////////////////////
                        try {
                            //em.imagesDesCartesFiltrees();

                            FiltterMagic.split(modele.getCartes());
                            FiltterMagic.meld(modele.getCartes());


                                FiltterMagic.flip(modele.getCartes());
                                FiltterMagic.planarSchemeVanguard(modele.getCartes());
                                FiltterMagic.transform(modele.getCartes());
                                FiltterMagic.isOversized(modele.getCartes());
                                FiltterMagic.collectorsEdition(modele.getCartes());

///////////////////////////////////////////////////////////////////////////////////////
                        } catch (Exception e1) {
                            System.out.println(e1);
                        }



            };
    }

    private class SaveImages extends AbstractAction {
        private SaveImages() {
            super("Sauvegarder les images");
        }

        public void actionPerformed(ActionEvent e) {

        }
    }


    public ModeleTableMagic getModele() {
        return modele;
    }

    public void setModele(ModeleTableMagic modele) {
        this.modele = modele;
    }

    public JTable getTableau() {
        return tableau;
    }

    public void setTableau(JTable tableau) {
        this.tableau = tableau;
    }
////////////////////////////////////////////////////////////////

    public void redimColonnes(){
        for (int column = 0; column < tableau.getColumnCount(); column++)
        {
            TableColumn tableColumn = tableau.getColumnModel().getColumn(column);
            int preferredWidth = tableColumn.getMinWidth()*12/10;
            int maxWidth = tableColumn.getMaxWidth();
            for (int row = 0; row < tableau.getRowCount(); row++)
            {
                TableCellRenderer cellRenderer = tableau.getCellRenderer(row, column);
                Component c = tableau.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + tableau.getIntercellSpacing().width;
                width = width*12/10;
                preferredWidth = Math.max(preferredWidth, width);
// We've exceeded the maximum width, no need to check other rows
                if (preferredWidth >= maxWidth)
                {
                    preferredWidth = maxWidth;
                    break;
                }
            }
            tableColumn.setPreferredWidth( preferredWidth );
        }
    }


////////////////////////////////////////////////////////////////

}

class ColorComboRendererMagic extends DefaultListCellRenderer {

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        ColorComboRendererMagic c = (ColorComboRendererMagic) (super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus));
        if (index == -1) {

            c.setText("Filtrer les status");
            c.setBackground(Color.BLACK);
            c.setForeground(Color.BLACK);
        } else {
            c.setText(K.statusMagic[index]);
            c.setBackground(((JLabel) value).getBackground());
            c.setForeground(((JLabel) value).getForeground());
        }
        return c;
    }

}
