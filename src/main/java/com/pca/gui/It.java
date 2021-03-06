package com.pca.gui; /**
 * Created by fxc on 17/06/2019.
 */

import com.pca.model.ExtensionUS;
import com.pca.model.SerieUS;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

//https://wiki.pokemoncentral.it/Sintonia_Mentale_(GCC)
//https://wiki.pokemoncentral.it/Set_Base_(GCC)
public class It extends JFrame implements ActionListener {
    public JPanel pFrame,pExcel;
    public JButton bAjouter;
    public JProgressBar bar;
    public TitledBorder title;

    public JComponent[] japComboBox =new JComponent[12];// new JComboBox[12];
    public JComboBox comboBoxSerie = null, comboBoxExtension =null;
    private JTabbedPane tabbedPane;
    public JComboBox comboBoxITDE;
    private JPanel pExtension,pChoix;
    private  Map hm = new HashMap();
    private int serie_id;
    private  String  nom=null, nomDossier=null, nomTableauBulbapedia=null, SerieExtensionZEBRA=null, URL=null, PremierNumero=null;
    public ExtensionUS extension;
    //public JTablePainterCarte jTablePainterCarte = new JTablePainterCarte();
    // MIGRATION
    public JTablePok jTableCarte;
    public Thread t,t2;
    public JPanel tab= null;

    ApplicationPM app;

    It(ApplicationPM app){
        this.app=app;
        // MIGRATION
        //comboBoxSerie = SerieUS0.getComboBoxSerie();

        comboBoxExtension =new JComboBox();
        comboBoxSerie.setName("serie_id");
        comboBoxExtension.setName("extension_id");
        comboBoxSerie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBoxExtension.removeAllItems();
                SerieUS serie =(SerieUS) comboBoxSerie.getSelectedItem();//.toString();
                if(serie==null)serie=(SerieUS) comboBoxSerie.getItemAt(0);
                for(ExtensionUS extension : serie.getExtensionsUS())
                    comboBoxExtension.addItem(extension);
            }
        });
        //////////////////////////////
        extension = new ExtensionUS();


    }



    public  JPanel ihm(){
        JPanel pAjoutJAP = new JPanel(new BorderLayout());
        //this.getContentPane().add(pAjoutJAP, BorderLayout.NORTH);
        // Dans la partie SUD on ajoute un panel pAjoutJAP

        tabbedPane = new JTabbedPane();

        pFrame = new JPanel(new BorderLayout());
        pChoix = new JPanel(new GridLayout(3,1));
        title = BorderFactory.createTitledBorder("Extension(s)");
        pChoix.setBorder(title);
        comboBoxITDE = new JComboBox(new String[]{"Italien","Allemand", "Espagnol"});
        pExtension = monCadreListes("Nouvelle Extension");
        pFrame.add(pExtension,BorderLayout.EAST);
        comboBoxITDE.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationPM.pays = comboBoxITDE.getSelectedIndex();
                // MIGRATION
                //jTableCarte.setModele(new ModeleDynamiqueCarte(jTableCarte.getModele().getCartes(),app));
                jTableCarte.revalidate();
                jTableCarte.repaint();
                if(ApplicationPM.pays==K.ITALIE)
                    bAjouter.setText("Parser Extension italienne");
                else if(ApplicationPM.pays==K.ALLEMAGNE)
                    bAjouter.setText("Parser Extension Allemande");
                else if(ApplicationPM.pays==K.ESPAGNE)
                    bAjouter.setText("Parser Extension Espagnole");

            }
        });

        ButtonGroup bg=new ButtonGroup();
        pChoix.add(comboBoxITDE);

        pFrame.add(pChoix,BorderLayout.WEST);
        //pExtension = new JPanel();

        pAjoutJAP.add(pFrame, BorderLayout.NORTH);
        pAjoutJAP.add(bAjouter = new JButton("Ajouter Extension italienne"), BorderLayout.CENTER);
        bAjouter.addActionListener(this);
        pAjoutJAP.add(bar = new JProgressBar(), BorderLayout.SOUTH);
        title = BorderFactory.createTitledBorder("Net2SQL JAP");
        pAjoutJAP.setBorder(title);

        //////////////////////    le pFrame     /////////////////////////////
        // l'entête à deux CheckBoxs au NORTH




        return pAjoutJAP;
    }

    private JPanel monCadreListes(String txtTitre) {
        String labels[] = {"nomTableauWiki", "SerieExtensionZEBRA", "url"};
        JPanel jp = new JPanel(new GridLayout(labels.length+3, 1));
        TitledBorder titre;
        int i = 0;
        jp.add(new JLabel("serie_id"));
        jp.add(comboBoxSerie);
        jp.add(new JLabel("extension_id"));
        jp.add(comboBoxExtension);
        comboBoxSerie.setSelectedIndex(0);

        for (String x : labels) {
            jp.add(new JLabel(x + ""));
            japComboBox[i] = new JTextField(15);
            japComboBox[i].setName(x);
            jp.add(japComboBox[i]);
            i++;
        }
        jp.add(new JLabel("DateSortie"));
        JXDatePicker picker = new JXDatePicker();
        picker.setDate(Calendar.getInstance().getTime());
        picker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        picker.setName("DateSortie");
        jp.add(picker);

        titre = BorderFactory.createTitledBorder(txtTitre);
        jp.setBorder(titre);

        return jp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String serie=null;
        JButton b = (JButton) hm.get("bAjouter");
        if(e.getSource()==bAjouter){
            int width = getWidth();
            if(tab==null) {
                // MIGRATION
                //jTableCarte = new JTablePok(extension.getCartes(),app);
                jTableCarte.setSize(width, (width * 3) / 4);
                tab = (JPanel) ((JTabbedPane) app.getContentPane().getComponent(0)).getSelectedComponent();
                tab.add(jTableCarte, BorderLayout.SOUTH);
                app.pack();
            }else{
                tab.remove(jTableCarte);
                // MIGRATION
                //jTableCarte = new JTablePok(extension.getCartes(),app);
                jTableCarte.setSize(width, (width * 3) / 4);
                tab.add(jTableCarte, BorderLayout.SOUTH);
                app.pack();

            }
                for (Component x : pExtension.getComponents()) {
                    if (x.getClass() == JComboBox.class || x.getClass() == JTextField.class || x.getClass()==JXDatePicker.class)
                        switch (x.getName()) {
                            case "serie_id":
                                serie = ((JComboBox) x).getSelectedItem().toString();
                                /*
                                String serieid = serie.substring(0, serie.indexOf(" "));
                                serie_id = Integer.parseInt(serieid);
                                extension.setSerie(new Serie(serie_id));
                                */
                                break;
                            case "extension_id":
                                extension = (ExtensionUS) ((JComboBox) x).getSelectedItem();
                                //String extensionid = extension.toString().substring(0,extension.toString().indexOf(" "));
                                break;
                            case "nomTableauWiki":
                                if(ApplicationPM.pays==K.ITALIE) {
                                    String nomTableauWikiIT = ((JTextField) x).getText();
                                    extension.setNomTableauWikiIT(nomTableauWikiIT);
                                }else if(ApplicationPM.pays==K.ALLEMAGNE) {
                                    String nomTableauWikiDE = ((JTextField) x).getText();
                                    extension.setNomTableauWikiDE(nomTableauWikiDE);
                                }else if(ApplicationPM.pays==K.ESPAGNE) {
                                    String nomTableauWikiES = ((JTextField) x).getText();
                                    extension.setNomTableauWikiES(nomTableauWikiES);
                                }
                                break;

                            case "SerieExtensionZEBRA":
                                /*
                                if(com.pca.gui.ApplicationPM.pays==com.pca.gui.ApplicationPM.ITALIE) {
                                    String serieExtensionZEBRAit = ((JTextField) x).getText();
                                    extension.setSerieExtensionZEBRAit(serieExtensionZEBRAit);
                                }else if(com.pca.gui.ApplicationPM.pays==com.pca.gui.ApplicationPM.ALLEMAGNE) {
                                    String serieExtensionZEBRAde = ((JTextField) x).getText();
                                    extension.setSerieExtensionZEBRAde(serieExtensionZEBRAde);
                                }else if(com.pca.gui.ApplicationPM.pays==com.pca.gui.ApplicationPM.ESPAGNE) {
                                    String serieExtensionZEBRAes = ((JTextField) x).getText();
                                    extension.setSerieExtensionZEBRAes(serieExtensionZEBRAes);
                                }
                                */
                                break;
                            case "url":
                                if(ApplicationPM.pays==K.ITALIE) {
                                    String urlit = ((JTextField) x).getText();
                                    extension.setUrlIT(urlit);
                                }else if(ApplicationPM.pays==K.ALLEMAGNE) {
                                    String urlde = ((JTextField) x).getText();
                                    extension.setUrlDE(urlde);
                                }else if(ApplicationPM.pays==K.ESPAGNE) {
                                    String urles = ((JTextField) x).getText();
                                    extension.setUrlES(urles);
                                }
                                break;
                            case "DateSortie":
                                JXDatePicker picker = (JXDatePicker) x;
                                Date d = picker.getDate();
                                Instant instant = Instant.ofEpochMilli(d.getTime());
                                LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                                if(ApplicationPM.pays==K.ITALIE) {
                                    //extension.setDateSortieIT(localDateTime.toLocalDate());
                                }else if(ApplicationPM.pays==K.ALLEMAGNE) {
                                    //extension.setDateSortieDE(localDateTime.toLocalDate());
                                }else if(ApplicationPM.pays==K.ESPAGNE) {
                                    //extension.setDateSortieES(localDateTime.toLocalDate());
                                }
                                break;
                        }

                }

                //////////////////////////////////////////////////////////////////////////////////////////////


                String nomBulbapedia = nom;

                // MIGRATION
                //jTableCarte.getModele().clearCartes();
                //jTablePainterCarte.jt = jTableCarte; //Fill in with the bar you want painted
                t = new Thread(jTableCarte);
                t.start();
                ArrayList<ExtensionUS> extensions = new ArrayList<>();
                extensions.add(extension);
                // MIGRATION
                //ParseExtension parseExtension = new ParseExtension(extensions, app);
                //parseExtension.jt = jTableCarte;
                //t2 = new Thread(parseExtension);
                t2.start();

                    (new Thread(){
                        @Override
                        public void run(){
                            try{
                            t2.join();
                            JOptionPane.showMessageDialog(null,"Parsing terminé !");
                            }catch (InterruptedException e1){
                                System.out.println("Dans It :"+ e1);
                            }

                        }
                    }).run();


                //extension.saveCartes(); // Attension ici il faut décommenter
                //////////////////////////////////////////////////////////////////////////////////////////////////////////////


        }
        System.out.println("\n\n/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_\n");
        System.out.println("/^\\_/^\\_        C'EST LA FIN             _/^\\_/^\\_\n");
        System.out.println("/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_");


    }



}
