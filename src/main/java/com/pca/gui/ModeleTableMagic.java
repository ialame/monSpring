package com.pca.gui; /**
 * Created by ia on 18/06/2019.
 */

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;


public class ModeleTableMagic extends AbstractTableModel implements TableModelListener{
    private final ArrayList<CarteMagic> Cartes;// = new ArrayList<CarteJAP>();
    private final ArrayList<CarteMagic> Cartes0 = new ArrayList();
    private JTable tableau;
    public ApplicationPM app;
    private final static String[] entetes = {"N°", "recherche", "Nom", "serie", "extension", "layout", "side", "number", "FusionPCA","filtre", "certifiable","isToken", "rarity","status","Reset","Enregistrable"};

    public ModeleTableMagic(ArrayList<CarteMagic> Cartes, ApplicationPM app) {
        super();
        this.Cartes = Cartes;
        this.app=app;
        setTitles();
    }

    public static void setTitles() {
        /*
        entetes[0] = "N°";
        entetes[1] = "recherche";
        entetes[2] = "Nom";
        entetes[3] = "serie";
        entetes[4] = "extension";

            entetes[5] = "layout";
            entetes[6] = "side";
            entetes[7] = "number";
            entetes[8] = "isToken";
            entetes[9] = "rarity";
            entetes[10] = "status";
            */
    }




    public ArrayList<CarteMagic> getCartes0() {
        return Cartes0;
    }


    public int getRowCount() {
        if(Cartes==null)
            return 0;
        else
            return Cartes.size();
    }

    public CarteMagic getRow(int row) {
        if(Cartes.size()>0)
            return (CarteMagic) Cartes.get(row);
        return null;
    }

    public int getColumnCount() {
        return entetes.length;
    }

    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }


    public void removeCarte(int rowIndex) {
        Cartes.remove(rowIndex);

        fireTableRowsDeleted(rowIndex, rowIndex);
    }


    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        CarteMagic c =  Cartes.get(rowIndex);
        /*if(columnIndex==15) {
            if(c.getStatus()==com.pca.gui.ApplicationPM.STATUS_CARTE_SANS_EXTENSION_US || c.getStatus()==com.pca.gui.ApplicationPM.STATUS_CARTE_EXTENSION_US_INEXISTANTE) {
                return true;
            }else {
                return false;
            }
        }else*/
            return true; //Toutes les cellules éditables
    }

    public Object getValueAtRow(int row) {
        CarteMagic c =  Cartes.get(row);
        return c;
    }


    public ArrayList<CarteMagic> getCartes() {
        return Cartes;
    }

    public void clearCartes() {
        if(Cartes==null)return;
        int N = Cartes.size();
        if(N>0) {
            Cartes.clear();
            fireTableRowsDeleted(0, N - 1);
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        if (e.getType() == TableModelEvent.UPDATE) {
            final int row = e.getFirstRow();
            final int column = e.getColumn();
            final Object value = ((ModeleTableMagic) e.getSource()).getValueAt(row, column);

        }
    }

    public JTable getTableau() {
        return tableau;
    }

    public void setTableau(JTable tableau) {
        this.tableau = tableau;
    }




    public void addCarte(CarteMagic carte){
        JComboBox comboBoxDoubles = new JComboBox();
        //comboBoxDoubles.removeAllItems();
        JTablePok.N++;
        //carte.setN(JTablePok.N);
        CarteMagic cj = new CarteMagic();
        carte.copy(cj);
        Cartes0.add(cj);
        //carte.setUndo(Cartes0.size()-1);
        //cj.setUndo(Cartes0.size()-1);
        Cartes.add(carte);
        fireTableRowsInserted(Cartes.size() -1, Cartes.size() -1);
        this.fireTableDataChanged();
    }
    public Object getValueAt(int rowIndex, int columnIndex) {
        CarteMagic carteMagic =  Cartes.get(rowIndex);
        try {
            switch (columnIndex) {
                case 0:
                    return rowIndex;// carteMagic.getN();
                case 1:
                    return carteMagic.getRecherche();
                case 2:
                    return carteMagic.getNomUS();
                case 3:
                    if (carteMagic.getExtension()!=null)
                        //return carteMagic.getExtension().getSerieABC();
                    break;
                case 4:
                    return carteMagic.getExtension();
                case 5:
                    return carteMagic.getLayout();
                case 6:
                    return carteMagic.getSide();
                case 7:
                    return carteMagic.getNumber();
                case 8:
                    return carteMagic.getFusionPCA();
                case 9:
                    return carteMagic.getFiltre();
                case 10:
                    return carteMagic.getCertifiable();
                case 11:
                    return carteMagic.isToken;
                case 12:
                    return carteMagic.getRarity();
                case 13:
                    //return carteMagic.getStatus();
                case 14:
                    //return carteMagic.isEnregistrable();
                default:
                    return null; //Ne devrait jamais arriver
            }
            entetes[5] = "layout";




        }catch (Exception ex){
            System.out.println("Exception dans getValueAt");
        }
        return null;
    }
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        if(aValue != null){
            CarteMagic carte =  Cartes.get(rowIndex);
            switch(columnIndex) {
                case 0:
                    //carte.setN(rowIndex );
                    break;
                case 1:
                    carte.setRecherche((String)aValue);
                    break;
                case 2:
                    carte.setNomFR((String) aValue);
                    break;
                case 3:
                    if(carte.getExtension()!=null)
                        carte.getExtension().setNomFR((String) aValue);
                    break;
                case 4:
                    //carte.getExtension().getSerieABC().setNomFR((String) aValue);
                    break;
                case 5:
                    carte.setLayout((String)aValue);
                case 6:
                    carte.setSide((String)aValue);
                    break;
                case 7:
                    carte.setNumber((String) aValue);
                    break;
                case 8:
                    carte.setFusionPCA(Integer.parseInt ((String) aValue));
                    break;
                case 9:
                    carte.setFiltre(Integer.parseInt ((String) aValue));
                    break;
                case 10:
                    carte.setCertifiable((Boolean) aValue);
                    break;
                case 11:
                    carte.isToken=(Boolean) aValue;
                    break;
                case 12:
                    carte.setRarity((String)aValue);
                    break;
                case 13:
                    ///**/carte.setStatus(Integer.parseInt ((String)aValue));
                    break;
                case 14:
                    /*carte.setEnregistrable((Boolean) aValue);
                    boolean b =true;
                    for(CarteMagic c: getCartes()){
                        if(!c.isEnregistrable()){
                            b=false;
                            break;
                        }
                    }
                    app.magic.jTableMagic.boutonSave.setEnabled(b);*/
                    //boutonSave.setEnabled(b);
                    break;
            }
        }
    }

}



