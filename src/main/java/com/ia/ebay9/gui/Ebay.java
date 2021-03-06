package com.ia.ebay9.gui;

import com.ia.ebay9.entity.Extension;
import com.ia.ebay9.entity.SerieUS;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DatabaseMetaData;
import java.time.LocalDateTime;

/**
 * Created by ia on 12/04/2019.
 */
public class Ebay {
    protected Net2mysql net2sql;
    public Thread t1;
    public JProgressBar bar1;
    protected JButton bNet2sql;
    public JComboBox lstNet2SQL;
    JRadioButton allExts, newExt;
    JComboBox comboBoxSerie = new JComboBox();
    JComboBox comboBoxExtension = new JComboBox();
    public static String baseDD ="";
    public JPanel ihm() {
        JPanel pPokemon = new JPanel(new BorderLayout());
        JButton bJson2sql, bImages, bFiltres;
        ExtensionMagic extensionMagic = null;

/////////////////////   N O R D   ////////////////////
        DatabaseMetaData dbmdFX = null, dbmdAD = null;
        String[] types = {"TABLE"};
        LocalDateTime currentTime = LocalDateTime.now();

        baseDD = currentTime.toString();
        int pos = baseDD.indexOf(".");
        if (pos != -1) {
            baseDD = baseDD.substring(0, pos);
        }
        baseDD = "Net2SQL" + baseDD.replaceFirst(":", "h").replaceFirst(":", "mn").replace("-", "_") + "s";
        net2sql = new Net2mysql(this);

        JPanel pNord = new JPanel();

        JPanel pNet2SQL = new JPanel(new BorderLayout());
        pNet2SQL.add(bNet2sql = new JButton("Exécuter Net2SQL"), BorderLayout.NORTH);
        pNet2SQL.add(bar1 = new JProgressBar(), BorderLayout.SOUTH);
        JLabel lblTab1 = new JLabel("Nouvelle table : ");
        lblTab1.setForeground(Color.GRAY);
        pNet2SQL.add(lblTab1, BorderLayout.WEST);

        lstNet2SQL = new JComboBox();
        lstNet2SQL.setName("net2sql");

        pNet2SQL.add(lstNet2SQL, BorderLayout.CENTER);

        TitledBorder title;
        title = BorderFactory.createTitledBorder("Net2SQL");
        pNet2SQL.setBorder(title);
        pNord.add(pNet2SQL);

        /*try {
            dbmdFX = ApplicationPM.DB.getConnectLOC().getMetaData();
            ResultSet rs = dbmdFX.getTables(null, null, "N%s", types);
            int k = 0;
            if (k++ == 0) {
                lstNet2SQL.removeAllItems();
                lstNet2SQL.addItem(baseDD);//"Nouvelle Table");
            }
            while (rs.next()) {
                lstNet2SQL.addItem(rs.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        lstNet2SQL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseDD = lstNet2SQL.getSelectedItem().toString();
            }
        });

        //console = new JTextArea("ici la sortie\n");

        bNet2sql.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                //baseDD = lstNet2SQL.getSelectedItem().toString();
                try {
                    t1 = new Thread(net2sql);
                    //CarteEbay.baseDDCarteEbay = "CarteEbay";
                    //CarteVendue.baseDDCarteVendue = baseDD;// + "CarteVendue";
                    //CarteVendue.creerBDD();
                    net2sql.run();
                } catch (Exception e1) {
                    System.out.println(e1);
                }

            }
        });
        pPokemon.add(pNord, BorderLayout.NORTH);

/////////////////////   C E N T R E  ////////////////////

        JPanel pCentre = new JPanel(new BorderLayout());

        JPanel pChoix = new JPanel(new BorderLayout());
        ButtonGroup gb = new ButtonGroup();
        JPanel pNouvelle = new JPanel(new BorderLayout());

        newExt = new JRadioButton("Extension");
        newExt.setSelected(true);
        newExt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBoxExtension.setEnabled(true);
                comboBoxSerie.setEnabled(true);
            }
        });
        pNouvelle.add(newExt,BorderLayout.WEST);
        pNouvelle.add(comboBoxSerie,BorderLayout.CENTER);
        pNouvelle.add(comboBoxExtension,BorderLayout.EAST);


        //comboBoxSerie.addActionListener(this);
        /*try {
            Statement statement = ApplicationPM.DB.getConnect().createStatement();
            String sql = "SELECT id  FROM "+ ApplicationPM.DB.getTable_Serie();//+ " where id=1";
            ResultSet resultSerie = statement.executeQuery(sql);
            //comboBoxSerie.addItem("choisir la série ..");
            while (resultSerie.next()) {
                // MIGRATION
                //comboBoxSerie.addItem(new SerieUS0(resultSerie.getInt("id")));
            }
            statement.close();
        } catch(Exception exp){

        }*/
        /*try {
            Statement statement = ApplicationPM.DB.getConnect().createStatement();
            SerieUS serie =(SerieUS) comboBoxSerie.getSelectedItem();
            for(Object o :serie.getExtensionsUS()){
                ExtensionUS e = (ExtensionUS) o;
                comboBoxExtension.addItem(e);
            }
        } catch(Exception exp){

        }*/
        comboBoxSerie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*try {
                    Statement statement = ApplicationPM.DB.getConnectLOC().createStatement();
                    SerieUS serie =(SerieUS) comboBoxSerie.getSelectedItem();
                    for(Object o :serie.getExtensionsUS()) {
                        ExtensionUS ext = (ExtensionUS) o;
                        comboBoxExtension.addItem(ext);
                    }
                } catch(Exception exp){

                }*/
            }
        });






        gb.add(newExt);
        allExts = new JRadioButton("Toutes les extensions");
        allExts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBoxExtension.setEnabled(false);
                comboBoxSerie.setEnabled(false);
            }
        });
        JPanel pToutes = new JPanel(new FlowLayout());
        pToutes.add(allExts,FlowLayout.LEFT);
        gb.add(allExts);



        JPanel pImages = new JPanel(new FlowLayout());
        JButton btn = new JButton("Choisir un dossier !");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int ret = jfc.showOpenDialog(null); // ne te rend la main que si tu ferme
                if(ret == JFileChooser.APPROVE_OPTION) { // validation
                    System.out.println("Selected dir : " + jfc.getSelectedFile());
                }
            }
        });

        pImages.add(btn);


        pImages.add(bImages = new JButton("Sauvegarder les images"));

        //TitledBorder title;
        title = BorderFactory.createTitledBorder("Images");
        pCentre.setBorder(title);

        pChoix.add(pNouvelle,BorderLayout.NORTH);
        pChoix.add(pToutes,BorderLayout.SOUTH);
        pCentre.add(pChoix,BorderLayout.NORTH);
        pCentre.add(pImages, BorderLayout.SOUTH);

        pPokemon.add(pCentre, BorderLayout.CENTER);

        /////////////////////   S U D  ////////////////////
        JPanel pSud = new JPanel(new FlowLayout());

        JPanel pFiltres = new JPanel(new FlowLayout());
        pFiltres.add(bFiltres = new JButton("Appliquer les filtres"));

        //TitledBorder title;
        title = BorderFactory.createTitledBorder("Filtres");
        pFiltres.setBorder(title);
        pSud.add(pFiltres);
        pPokemon.add(pSud, BorderLayout.SOUTH);


        bImages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SerieUS serie= null;
                if(allExts.isSelected()) {// Toute les extension
                    for(int i=0;i<comboBoxSerie.getItemCount();i++) {
                        serie = (SerieUS) comboBoxSerie.getItemAt(i);
                        // serie.getImages();  MIGRATION
                    }
                }else {
                    Extension extension = (Extension) comboBoxExtension.getSelectedItem();
                    //extension.getImages();
                }
            }
        });

        bFiltres.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                try {
                    //em.imagesDesCartesFiltrees();
/*
                    FiltterMagic.carteSplit();
                    FiltterMagic.carteMeld();
                    FiltterMagic.carteFlip();
                    FiltterMagic.cartePlanarSchemeVanguard();
                    FiltterMagic.carteTransform();
                    FiltterMagic.carteIsOversized();
                    FiltterMagic.carteCollectorsEdition();

*/
                } catch (Exception e1) {
                    System.out.println(e1);
                }

            }
        });



        return pPokemon;
    }

}
