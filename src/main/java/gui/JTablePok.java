package com.pca.gui; /**
 * Created by fxc on 18/06/2019.
 */

import com.pca.entity.*;
import com.pca.repository.CartePokemonRepository;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ia on 07/03/2019.
 */
public class JTablePok extends JPanel implements Runnable{
    public static int N = 0;// nombre des cartes dans parse...
    private ModeleTablePok modele;// = new ModeleDynamiqueObjet();
    private JTable table;
    private List cartes;
    private PageGui page;
    public JButton boutonSave = null;
    public JButton boutonSupprime = null;

    TableRowSorter<ModeleTablePok> sorter;
    public JProgressBar bar;


    public JTablePok(List<CarteUsJap> cartes, PageGui page) {//ArrayList<CartePokemon>
        this.page = page;
        this.cartes = cartes;
        setLayout(new BorderLayout());
        ///////////////
        page.app.setResulTable(this);
        setLayout(new BorderLayout());

        modele = new ModeleTablePok(cartes);
        table = new JTable(modele);
        // Attension il faut peut être le refaire c'est l'éditeur de la cellule cartePokemone
        //table.setDefaultEditor(CartePokemon.class, new CartePokemonEditeur());
        table.setDefaultRenderer(Color.class, new CouleurRenderer(true));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        //table.getColumnModel().getColumn(8).setPreferredWidth(300);
        for(int i=12;i<18;i++)
            table.getColumnModel().getColumn(i).setPreferredWidth(20);
        table.setAutoCreateRowSorter(true);

        JPanel boutons = new JPanel();
        JButton x;
        boutonSave = new JButton(new SaveAction());
        boutonSupprime = new JButton(new SupprimeAction());

        boutons.add(new JButton(new AddAction()));
        boutons.add(new JButton(new RemoveAction()));
        boutons.add(new JButton(new ClearAction()));
        boutons.add(new JButton(new MergeUSJAPAction()));
        boutons.add(boutonSave);
        boutons.add(boutonSupprime);
        boutons.add(x = new JButton(new UpdateAction()));
        x.setVisible(false);


        JLabel[] colorListe = new JLabel[K.elements.length];
        Color[] colorsList = K.couleur;
        ColorComboRendererPok rendererFiltres = new ColorComboRendererPok();
        for (int i = 0; i < K.elements.length; i++) {
            colorListe[i] = new JLabel(K.elements[i]);
            colorListe[i].setBackground(colorsList[i]);
            colorListe[i].setForeground(Color.BLACK);

        }

        JComboBox filtres = new JComboBox(colorListe);
        filtres.setRenderer(rendererFiltres);
        boutons.add(filtres);
        filtres.addActionListener(new FilterAction());

        add(boutons, BorderLayout.SOUTH);

        RowFilter<ModeleTablePok, Integer> ageFilter = new RowFilter<ModeleTablePok, Integer>() {
            public boolean include(Entry<? extends ModeleTablePok, ? extends Integer> entry) {
                ModeleTablePok personModel = entry.getModel();
                CarteUsJap carte = personModel.getRow(entry.getIdentifier());
                return true;
            }
        };

        ModeleTablePok model =(ModeleTablePok) table.getModel();
        sorter = new TableRowSorter<ModeleTablePok>(model);
        sorter.setRowFilter(ageFilter);
        table.setRowSorter(sorter);

        JScrollPane pane = new JScrollPane(table);
        add(pane,BorderLayout.NORTH);
        //    B A R
        this.add(bar = new JProgressBar(), BorderLayout.CENTER);
        // JADID
        table.setDefaultRenderer(Object.class, new RendererJTablePok());
    }

    public void run(){
        while(true){
            try {
                Thread.sleep(50);
                repaint();
            } catch (InterruptedException ex) {
                break;
            }
        }
    }


    public void actualier(){

        CartePokemonRepository cr = new CartePokemonRepository(ApplicationPM.entityManager);

        for(CarteUsJap c : modele.getCartes()){
            if(c.us!=null && c.us.getId()!=null){
            //if(c.usBDD!=null && c.usBDD.getId()!=null){
                // A T T E N T I O N  UUID
                //c.usBDD = cr.getCartePokemonById(c.usBDD.getId());
                if(c.usBDD!=null){
                    switch (c.status){
                        case K.STATUS_CARTE_USPUR_NON_DANSBASE:
                            c.status =K.STATUS_CARTE_USPUR_DANSBASE;
                            break;
                        case K.STATUS_CARTE_USJAP_SANS_US_DANSBASE:
                            if(c.usBDD==c.japBDD.getCartePokemon())
                                c.status =K.STATUS_CARTE_USJAP_DANSBASE;
                            else
                                c.status = K.STATUS_CARTE_USJAP_DANSBASE_SEPAREMENT;
                            break;
                        case K.STATUS_CARTE_USJAP_NON_DANSBASE:
                            c.status = K.STATUS_CARTE_USJAP_SANS_JAP_DANSBASE;
                            break;
                    }
                }
           }if(c.jap!=null && c.jap.getId()!=null ){
            //}if(c.japBDD!=null &&  c.japBDD.getCartePokemon()!=null && c.japBDD.getCartePokemon().getId()!=null ){
                // A T T E N T I O N  UUID
                //c.japBDD = new CarteJAP(cr.getCartePokemonById(c.japBDD.getCartePokemon().getId()));
                if(c.japBDD!=null){
                    switch (c.status){
                        case K.STATUS_CARTE_JAPPUR_NON_DANSBASE:
                            c.status =K.STATUS_CARTE_JAPPUR_DANSBASE;
                            break;
                        case K.STATUS_CARTE_USJAP_SANS_JAP_DANSBASE:
                            if(c.usBDD==c.japBDD.getCartePokemon())
                                c.status =K.STATUS_CARTE_USJAP_DANSBASE;
                            else
                                c.status = K.STATUS_CARTE_USJAP_DANSBASE_SEPAREMENT;
                            break;
                        case K.STATUS_CARTE_USJAP_NON_DANSBASE:
                            c.status = K.STATUS_CARTE_USJAP_SANS_US_DANSBASE;
                            break;
                        case K.STATUS_CARTE_JAP_SANSPAGE2_NON_DANSBASE:
                            c.status = K.STATUS_CARTE_JAP_SANSPAGE2_DANSBASE;
                            break;
                    }
                }
            }
        }
        modele= new ModeleTablePok(modele.getCartes());
        modele.fireTableDataChanged();
    }

    public static void main(String[] args) {
        // new JTableCartePokemon().setVisible(true);
    }

    private class AddAction extends AbstractAction {
        private AddAction() {
            super("Ajouter une ligne");
        }

        public void actionPerformed(ActionEvent e) {
            modele.addCarte(new CarteUsJap());
        }
    }

    private class ClearAction extends AbstractAction {
        private ClearAction() {
            super("Tout effacer");
        }

        public void actionPerformed(ActionEvent e) {

            int N = table.getRowCount();

            for (int i = N - 1; i >= 0; i--) {
                modele.removeCarte(i);
            }
            JTablePok.N = 0;
            page.bouton.setEnabled(true);
        }
    }

    private class RemoveAction extends AbstractAction {
        private RemoveAction() {
            super("Supprimer la ligne sélectionée");
        }

        public void actionPerformed(ActionEvent e) {
            int[] selection = table.getSelectedRows();
            int[] modelIndexes = new int[selection.length];

            for (int i = 0; i < selection.length; i++) {
                modelIndexes[i] = table.getRowSorter().convertRowIndexToModel(selection[i]);
            }

            Arrays.sort(modelIndexes);

            for (int i = modelIndexes.length - 1; i >= 0; i--) {
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
                modele.save();
                actualier();
                modele.fireTableDataChanged();
                JOptionPane.showMessageDialog(page, "sauvegarde terminée !");
            } catch (Exception ex) {

            }
        }
    }
    private class SupprimeAction extends AbstractAction {
        private SupprimeAction() {
            super("supprimer");
        }
        public void actionPerformed(ActionEvent e) {
            try {
                modele.remove();
            }catch (Exception ex){
                System.out.println("Dans supprime: " + ex);
            }
        }

    }
    private class MergeUSJAPAction extends AbstractAction {
        private MergeUSJAPAction() {
            super("Merge US-JAP");
        }

        public void actionPerformed(ActionEvent e) {
            try {
                modele.merge();
                /*
                for (Object c : modele.getCartes()) {
                    CarteUsJap carte = (CarteUsJap) c;
                    if (carte.getStatus() == K.STATUS_CARTE_USJAP_DANSBASE_SEPAREMENT) {
                        //carte.usBDD.copieJAPdansUS(carte.getCarteMergeJAP());
                        //carte.usBDD.copieCartCertJAPdansUS(carte.getCarteMergeJAP());
                        //carte.getCarteMergeJAP().delete();
                    }
                }

                 */

            } catch (Exception ex) {

            }
        }
    }





    private class UpdateAction extends AbstractAction {
        private UpdateAction() {
            super("Update");
        }


        public void actionPerformed(ActionEvent e) {

        }
    }


    private class FilterAction extends AbstractAction {
        private FilterAction() {
            super("Filter");
        }

        public void actionPerformed(ActionEvent e) {
            RowFilter<ModeleTablePok, Integer> ageFilter = new RowFilter<ModeleTablePok, Integer>() {
                public boolean include(Entry<? extends ModeleTablePok, ? extends Integer> entry) {
                    ModeleTablePok personModel = entry.getModel();
                    CarteUsJap carte = personModel.getRow(entry.getIdentifier());
                    int item = ((JComboBox) e.getSource()).getSelectedIndex();
                    if (item > 0 && carte.getStatus() == item) {
                        return true;
                    } else if (item == 0) {
                        return true;
                    }
                    return false;
                }
            };
            sorter.setRowFilter(ageFilter);
            table.setDefaultRenderer(Object.class, new RendererJTablePok());

        }
    }

    public ModeleTablePok getModele() {
        return modele;
    }

    public void setModele(ModeleTablePok modele) {
        this.modele = modele;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public List getCartes() {
        return cartes;
    }

    public void setCartes(List cartes) {
        this.cartes = cartes;
    }
}

class ColorComboRendererPok extends DefaultListCellRenderer {

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        ColorComboRendererPok c = (ColorComboRendererPok) (super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus));
        if (index == -1) {
            c.setText("Filtrer les status");
            c.setBackground(Color.BLACK);
            c.setForeground(Color.BLACK);
        } else {
            c.setText(K.elements[index]);
            c.setBackground(((JLabel) value).getBackground());
            c.setForeground(((JLabel) value).getForeground());
        }
        return c;
    }

}


