package com.pca.gui; /**
 * Created by ia on 07/03/2020.
 */
import com.pca.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class ModeleTablePok extends AbstractTableModel {//implements TableModelListener {
    private final List<CarteUsJap> Cartes;// = new ArrayList<CarteUsJap>();// = new ArrayList<CartePokemon>();
    private final static String[] columnNames = {"N°",  "carteUS", "carteJAP", "Card", "englishExpansion", "enNum", "japExpansion", "japNum", "promos", "japRarity", "Hit Points", "status","u1","j1","u2","j2","sp","p2",
            "Particularité","Crochet"};
    private final Class[] columnClass = new Class[] {
            Integer.class,CartePokemon.class, CarteJAP.class, String.class, String.class,String.class,String.class,String.class, ArrayList.class, String.class,String.class, Color.class,Icon.class,Icon.class,Icon.class,Icon.class,Icon.class,Color.class,
            //Boolean.class,Boolean.class,
            String.class,String.class
    };

    public ModeleTablePok(List<CarteUsJap> Cartes) {
       this.Cartes = Cartes;
    }

    public CarteUsJap getRow(int row) {
        return null;
    }

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClass[columnIndex];
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public int getRowCount()
    {
        return Cartes.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        CarteUsJap carte =  Cartes.get(rowIndex);
        int n;
        try {
            switch (columnIndex) {
                case 0://"N°"
                    return  carte.getN();
                case 1: // "CarteUS",
                    return carte.usBDD;
                case 2: // "CarteUS",
                    return carte.japBDD;
                case 3: // "Card"
                    return carte.getCard();
                case 4://"englishExpansion",
                    return carte.jap.getEnExpansion();
                case 5://"enNum",
                    return carte.jap.getEnNum();
                case 6://"japExpansion",
                    if (carte.jap.getExtensionJAP()!=null)
                        return carte.jap.getExtensionJAP().getNom();
                    break;
                case 7: // "japNum",
                    return carte.jap.getJapNum();
                case 8://"Promos",
                    return carte.jap.getPromos();
                case 9: //"japRarity",
                    return carte.jap.getJapRarity();
                case 10: //"Hit Points",
                    return carte.jap.getHP();
                case 11: //"status",
                    return K.couleur[K.couleurSimple(carte.getStatus())];
                case 12:// us
                    n=carte.getStatus()/32;
                    return n%2==0?null:new ImageIcon("usa2.png");
                case 13:// jap
                    n=carte.getStatus()/16;
                    return n%2==0?null:new ImageIcon("jap2.png");
                case 14:// us
                    n=carte.getStatus()/8;
                    return n%2==0?null:new ImageIcon("blue2.png");
                case 15:// jap
                    n=carte.getStatus()/4;
                    return n%2==0?null:new ImageIcon("red2.png");

                case 16:// séparées
                    n=carte.getStatus()/2;
                    return n%2==0?null:new ImageIcon("merge1.png");
                case 17:// page 2
                    return carte.getStatus()%2==0?Color.gray:null;
                case 18:
                    if(carte.extension instanceof ExtensionUS)
                        if(carte.us.getParticularites().size()==0)
                            return null;
                        else
                            return carte.us.getParticularites()+"";
                    else if(carte.extension instanceof ExtensionJAP)
                        if(carte.jap.getParticularites().size()==0)
                            return null;
                        else
                            return carte.jap.getParticularites()+"";
                case 19:
                    if(carte.extension instanceof ExtensionUS)
                        if(carte.us.getCrochets().get(0)==null)
                            return null;
                        else
                            return carte.us.getCrochets();
                    else if(carte.extension instanceof ExtensionJAP)
                        if(carte.jap.getCrochets().get(0)==null)
                            return null;
                        else
                            return carte.jap.getCrochets();
                default:
                    return null; //Ne devrait jamais arriver
            }


        } catch (Exception ex) {
            System.out.println("Exception dans getValueAt");
        }
        return null;
    }

    public void removeCarte(int rowIndex) {
        Cartes.remove(rowIndex);

        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    public void clear(){
        int N = getRowCount();

        for (int i = N - 1; i >= 0; i--) {
            removeCarte(i);
        }
        JTablePok.N = 0;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        CarteUsJap c =  Cartes.get(rowIndex);
        return true; //Toutes les cellules éditables
    }

    public Object getValueAtRow(int row) {
        CarteUsJap c = (CarteUsJap) Cartes.get(row);
        return c;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (aValue != null) {
            CarteUsJap carte =  Cartes.get(rowIndex);

            switch (columnIndex) {
                case 0: // "N°",
                    //carte.setN(Integer.parseInt((String) aValue));
                    break;
                case 1: // "CarteUS",
                    //carte.us.setRecherche((String) aValue);
                    break;
                case 2: //  "CarteJAP",
                    break;
                case 3: // "Card",
                    //carte.us = (CartePokemon) aValue;
                    break;
                case 4: // "englishExpansion",
                    //carte.jap.setEnExpansion((String) aValue);
                    break;

                case 5: //  "enNum",
                    //carte.jap.setEnNum((String) aValue);
                    break;


                case 6: //"japExpansion",
                    //carte.jap.getExtensionJAP().setNom((String) aValue);
                    break;
                case 7:  // "japNum",
                    //carte.jap.setJapNum((String) aValue);
                    break;
                case 8://  "FA",
                    //Promos promos= new Promos();
                    //promos.add(new Promo((String) aValue,"jap"));
                    //carte.setPromos(promos)  ;
                    break;
                case 9:  //  "japRarity",
                    //carte.jap.setJapRarity((String) aValue);
                    break;
                case 10:  //  "Hit Points",
                    //carte.jap.setHP((String) aValue);
                    break;
                case 11:  //  "status",  // "Reset",
                    break;
                case 18://  "particularite"
                    break;
                case 19://  "Crochet"
                    break;
            }
        }
    }

    public List<CarteUsJap> getCartes() {
        return Cartes;
    }

    public void clearCartes() {
        if (Cartes == null) return;
        int N = Cartes.size();
        if (N > 0) {
            Cartes.clear();
            fireTableRowsDeleted(0, N - 1);
        }
    }

    public void addCarte(CarteUsJap carte) {
        JTablePok.N++;
        carte.setN(JTablePok.N);
        ////////////////////
        try {
            if (carte.extension instanceof ExtensionJAP) {
                int nbZeros = 3 - (int) Math.log10(carte.getN());
                String zeros = "";
                for (int i = 0; i < nbZeros; i++)
                    zeros += "0";
                carte.jap.setIdPrimJap((carte.jap.getExtensionJAP().getIdPCA() + "00" + (zeros + carte.getN() * 100)));
            }
        }catch(Exception e){
            System.out.println( "Dnas ModeleTablePok: setIdPrimJap");
        }
        Cartes.add(carte);
        fireTableRowsInserted(Cartes.size() - 1, Cartes.size() - 1);
        this.fireTableDataChanged();
    }
    void remove() {// les cartes energy ne sont pas enregistrées pour le moment, à voir ...
        EntityManager entityManager = null;
        CartePokemon cp;
        try {


            try {
                entityManager = ApplicationPM.entityManager;
                EntityTransaction trans = entityManager.getTransaction();
                trans.begin();
                for (CarteUsJap c : getCartes()) {
                    /*
                    if(cpok.getParticularites().size()>0)
                        for(Particularite p: cpok.getParticularites())
                            entityManager.remove(p);

                     */
                    CartePokemon cpok = c.japBDD.getCartePokemon();
                    if(cpok!=null) {
                        //entityManager.refresh(cpok);
                        entityManager.remove(cpok);
                    }
                }
                trans.commit();
            } finally {
            if ( entityManager != null ) entityManager.close();
            //if ( entityManagerFactory != null ) entityManagerFactory.close();
        }
    }catch (Exception e){
        System.out.println("Dans modèle : " + e);
    }

    }
    void save() {// les cartes energy ne sont pas enregistrées pour le moment, à voir ...
        EntityManager entityManager = null;
        CartePokemon cp;
        try {


        try {
            entityManager = ApplicationPM.entityManager;
            EntityTransaction trans = entityManager.getTransaction();
            trans.begin();
            for(CarteUsJap c :getCartes()){
                //if(c.getNomGeneralise().nomCarte.equals("Galarian Sirfetch'd"))
                //    c=c;
                //if(c.jap!=null && c.jap.getCard().equals("Galarian Sirfetch'd"))
                //    c=c;
                if(c.extension instanceof ExtensionJAP) {
                    switch (c.getStatus()) {
                        case K.STATUS_CARTE_USJAP_SANS_JAP_DANSBASE:
                            c.usBDD.ajoutePartieJapDe(c.jap);
                            cp = c.usBDD;
                            try{
                                if(cp.getJapCrochet()!=null)
                                    entityManager.persist(cp.getJapCrochet());
                                for(Promo p: cp.getPromos())
                                    entityManager.persist(p);//FK_B0139AFBCA157C84  //FK_42B62EDD19B47A20
                                //Cannot delete or update a parent row: a foreign key constraint fails (`c1ajoutCartesJava`.`cartePokemon`, CONSTRAINT `FK_42B62EDD6BF7B61B` FOREIGN KEY (`distributionMere_id`) REFERENCES `cartePokemon` (`id`))
                            }catch (Exception exception){
                                System.out.println("Promo ou particularité  déjà persistent");
                            }
                            entityManager.merge(cp);
                            break;
                        case K.STATUS_CARTE_USJAP_NON_DANSBASE:
                        case K.STATUS_CARTE_JAPPUR_NON_DANSBASE:
                        case K.STATUS_CARTE_JAP_SANSPAGE2_NON_DANSBASE:
                            c.jap.setJAP(true);
                            c.jap.setUS(false);
                            cp = c.jap;
                            entityManager.persist(cp);
                            break;
                        case K.STATUS_CARTE_USJAP_SANS_US_DANSBASE:
                        case K.STATUS_CARTE_JAPPUR_DANSBASE:
                            c.jap.setJAP(true);
                            c.jap.setUS(false);
                            cp = c.japBDD.getCartePokemon();
                            //cp.setParticularites(c.jap.getParticularites());
                            /*
                            try{
                                for(Promo p: cp.getPromos())
                                    entityManager.persist(p);
                            }catch (Exception exception){
                                System.out.println("Promo déjà persistent");
                            }
                            entityManager.merge(cp);
                            */

                            break;
                        case K.STATUS_CARTE_USJAP_DANSBASE:
                            //if (c.us.getExtensionUS() != null)
                                //c.us.getExtensionUS().getCartes().remove(c.us);
                            //if (c.jap.getExtensionJAP() != null)
                                //c.jap.getExtensionJAP().getCartes().remove(c.jap);
                    }
                    //if(true)break;
                }else if(c.extension instanceof ExtensionUS) {
                    switch (c.getStatus()) {
                        case K.STATUS_CARTE_USJAP_SANS_US_DANSBASE:
                            c.japBDD.getCartePokemon().ajoutePartieUsDe(c.us);
                            cp = c.japBDD.getCartePokemon();
                            entityManager.merge(cp);
                            break;

                        case K.STATUS_CARTE_USJAP_NON_DANSBASE:
                        case K.STATUS_CARTE_USPUR_NON_DANSBASE:
                            c.us.setJAP(true);
                            c.us.setUS(true);
                            cp = c.us;
                            entityManager.persist(cp);
                            break;
                        case K.STATUS_CARTE_USJAP_SANS_JAP_DANSBASE:
                        case K.STATUS_CARTE_USPUR_DANSBASE:
                        case K.STATUS_CARTE_JAPPUR_DANSBASE:
                            break;
                        case K.STATUS_CARTE_USJAP_DANSBASE:
                            //if (c.us.getExtensionUS() != null) c.jap.getExtensionUS().getCartes().remove(c.us);
                            //if (c.jap.getExtensionJAP() != null) c.jap.getExtensionJAP().getCartes().remove(c.jap);
                    }
                }
            }
            trans.commit();

        } finally {
            //if ( entityManager != null ) entityManager.close();
            //if ( entityManagerFactory != null ) entityManagerFactory.close();
        }
        }catch (Exception e){
            System.out.println("Dans modèle : " + e);
        }

    }

    void merge() {
        EntityManager entityManager = null;
        try {
            try {
                entityManager = ApplicationPM.entityManager;
                EntityTransaction trans = entityManager.getTransaction();
                if(!trans.isActive())
                    trans.begin();
                for(CarteUsJap c :getCartes()) {
                    if (c.status == K.STATUS_CARTE_USJAP_DANSBASE_SEPAREMENT) {
                        CartePokemon cpj= c.japBDD.getCartePokemon();
                        CartePokemon cpu= c.usBDD;
                        cpu.ajoutePartieJapDe(cpj);
                        //  CartCert
                        for(CartCert cc : cpj.getCartcerts()){
                            cc.setCarte(cpu);
                            cpu.getCartcerts().add(cc);
                        }
                        cpj.getCartcerts().clear();
                        //  Particularités
                        while(cpj.getParticularites().size()>0){
                            Particularite p = cpj.getParticularites().get(0);
                            p.getCartePokemons().remove(cpj);
                            cpj.getParticularites().remove(p);
                            p.getCartePokemons().add(cpu);
                            cpu.getParticularites().add(p);
                        }
                        //  Crochets
                        while(cpj.getCrochets().size()>0){
                            Crochet crochet = cpj.getCrochets().get(0);
                            crochet.getCartePokemons().remove(cpj);
                            cpj.getCrochets().remove(crochet);
                            crochet.getCartePokemons().add(cpu);
                            cpu.getCrochets().add(crochet);
                        }
                        //  Promos
                        while(cpj.getPromos().size()>0){
                            Promo promo = cpj.getPromos().get(0);
                            cpj.getPromos().remove(promo);
                            promo.setCartePokemon(cpu);
                            cpu.getPromos().add(promo);
                        }
                        //  Promosused
                        while(cpj.getPromosused().size()>0){
                            Promo promo = cpj.getPromosused().get(0);
                            promo.getCartePokemons().remove(cpj);
                            cpj.getPromosused().remove(promo);
                            promo.getCartePokemons().add(cpu);
                            cpu.getPromosused().add(promo);
                        }

                        //  japCarteMère
                        CartePokemon cpm = cpj.getJapCarteMere();
                        cpm.getJapCarteFilles().remove(cpj);
                        cpm.getJapCarteFilles().add(cpu);
                        cpu.setJapCarteMere(cpm);

                        while(cpj.getJapCarteFilles().size()>0){
                            CartePokemon cpf = cpj.getJapCarteFilles().get(0);
                            cpj.getJapCarteFilles().remove(cpf);
                            cpf.setJapCarteMere(cpu);
                            cpu.getJapCarteFilles().add(cpf);
                        }
                        //  japDistributionMère
                        CartePokemon cpdm = cpj.getJapDistributionMere();
                        cpm.getJapDistributionFilles().remove(cpj);
                        cpm.getJapDistributionFilles().add(cpu);
                        cpu.setJapDistributionMere(cpdm);

                        while(cpj.getJapDistributionFilles().size()>0){
                            CartePokemon cpdf = cpj.getJapCarteFilles().get(0);
                            cpj.getJapCarteFilles().remove(cpdf);
                            cpdf.setJapCarteMere(cpu);
                            cpu.getJapCarteFilles().add(cpdf);
                        }
                        // CartePokemonSource
                        CartePokemonSource cps = cpj.getSource();
                        cpu.setSource(cps);
                        cps.getCartePokemons().remove(cpj);
                        cps.getCartePokemons().add(cpu);

                        entityManager.remove(cpj);
                        c.status=K.STATUS_CARTE_USJAP_DANSBASE;
                        break;
                    }
                }

                trans.commit();

            } finally {
                //if ( entityManager != null ) entityManager.close();
                //if ( entityManagerFactory != null ) entityManagerFactory.close();
            }
        }catch (Exception e){
            System.out.println("Dans modèle : " + e);
        }

    }

}
