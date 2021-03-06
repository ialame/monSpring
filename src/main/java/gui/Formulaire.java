package com.pca.gui;

import com.pca.entity.*;
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
import java.util.ArrayList;
import java.util.Date;

public class Formulaire extends JPanel implements ActionListener {
    //public JTablePainterCarte jTablePainterCarte = new JTablePainterCarte();
    public JTablePok jTablePok; //jTablePokemon,
    public Thread t,t2;
    public JPanel tab= null;
    public ApplicationPM app;
    public ExtensionMagic extensionMagic;
    public JPanel pNouvelle;
    public JComboBox comboBoxSerie =null;
    public JComboBox comboBoxExtension =new JComboBox();
    public JComboBox comboBoxConcat=new JComboBox();
    public JComboBox comboBoxSource=new JComboBox();
    public JRadioButton ouiButton=null, nonButton=null;
    public JPanel  pTree;
    public JButton bAjouter;
    public int id=0;
    JComponent[] jcomboBox =new JComponent[24];// new JComboBox[12];
    JLabel[] jLabels =new JLabel[24];// new JComboBox[12];
    TitledBorder titre;
    int i = 0;
    public String labels[];
    public String  txtTitre;
    public String pays;
    Formulaire(String labels[], String txtTitre, String pays,ApplicationPM app) {
        this.labels = labels;
        this.txtTitre =txtTitre;
        this.pays=pays;
        this.app=app;
        this.setLayout(new GridLayout(labels.length,1));
        for (String x : labels) {
            jLabels[i] = new JLabel(x + "");
            this.add(jLabels[i]);
            if(x.equals("serie_id") || x.equals("extension_id")|| x.equals("concat")|| x.equals("source")) {
                jcomboBox[i] = new JComboBox();
            }else if(x.equals("DateSortie")){
                jcomboBox[i] = new JXDatePicker();
            }else if(x.equals("Promo")){
                jcomboBox[i] = new JCheckBox("");
            }else {
                jcomboBox[i] = new JTextField(15);
            }
            jcomboBox[i].setName(x);
            this.add(jcomboBox[i]);
            i++;
        }

        for(int j=0;j<labels.length;j++) {
            if (labels[j].equals("serie_id")) {
                switch (pays){
                    case "jap":
                        comboBoxSerie = (JComboBox<SerieJAP>) jcomboBox[j];
                        comboBoxSerie.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JComboBox jComboBox =(JComboBox) e.getSource();
                                comboBoxExtension.removeAllItems();
                                if(jComboBox.getSelectedItem()!=null) {
                                    for (ExtensionJAP extensionJAP : ((SerieJAP) jComboBox.getSelectedItem()).getExtensionsJAP()) { // A T T E N T I O N
                                        comboBoxExtension.addItem(extensionJAP);
                                    }
                                    //comboBoxExtension.setSelectedItem(null);
                                    comboBoxExtension.setSelectedIndex(0);
                                    app.pack();
                                }
                            }
                        });
                        for (SerieJAP serie : ApplicationPM.getSeriesJAP()) { // A T T E N T I O N
                            comboBoxSerie.addItem(serie);
                        }
                        comboBoxSerie.setSelectedItem(null);
                        break;
                    case "us":
                        comboBoxSerie = (JComboBox<SerieUS>) jcomboBox[j];
                        comboBoxSerie.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JComboBox jComboBox =(JComboBox) e.getSource();
                                comboBoxExtension.removeAllItems();
                                if(jComboBox.getSelectedItem()!=null) {
                                    for (ExtensionUS extensionUS : ((SerieUS) jComboBox.getSelectedItem()).getExtensionsUS()) { // A T T E N T I O N
                                        comboBoxExtension.addItem(extensionUS);
                                    }
                                    //comboBoxExtension.setSelectedItem(null);
                                    comboBoxExtension.setSelectedIndex(0);
                                    app.pack();
                                }
                            }
                        });
                        for (SerieUS serie : ApplicationPM.getSeriesUS()) { // A T T E N T I O N
                            comboBoxSerie.addItem(serie);
                        }
                        comboBoxSerie.setSelectedItem(null);
                        break;
                }

            } else if (labels[j].equals("extension_id")) {

                switch (pays) {
                    case "jap":
                        comboBoxExtension = (JComboBox<ExtensionJAP>) jcomboBox[j];
                        if (comboBoxSerie.getSelectedItem() != null){
                            for (ExtensionJAP extensionJAP : ((SerieJAP) comboBoxSerie.getSelectedItem()).getExtensionsJAP()) { // A T T E N T I O N
                                comboBoxExtension.addItem(extensionJAP);
                            }
                            comboBoxExtension.setSelectedIndex(0);
                        }

                        if ( j < labels.length-1 && labels[j+1].equals("concat") && pays.equals("jap")) {
                            if(comboBoxExtension.getSelectedItem()!=null) {
                                ArrayList<ExtensionConcatenee> AL = ExtensionConcatenee.getTableaux(((ExtensionJAP) comboBoxExtension.getSelectedItem()).getId());
                                comboBoxConcat = (JComboBox<ExtensionJAP>) jcomboBox[j + 1];
                                comboBoxConcat.setEnabled(false);
                                for (ExtensionJAP extensionJAP : AL) { // A T T E N T I O N
                                    comboBoxConcat.addItem(extensionJAP);
                                }
                                if (comboBoxConcat.getItemCount() > 0)
                                    comboBoxConcat.setSelectedIndex(0);
                            }
                            comboBoxExtension.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (comboBoxExtension.getItemCount() > 0) {
                                        ArrayList<ExtensionConcatenee> AL = ExtensionConcatenee.getTableaux(((ExtensionJAP) comboBoxExtension.getSelectedItem()).getId());
                                        if (AL.size() > 0) {
                                            comboBoxConcat.setEnabled(true);
                                            for (ExtensionJAP extensionJAP : AL) { // A T T E N T I O N
                                                comboBoxConcat.addItem(extensionJAP);
                                            }
                                            comboBoxConcat.setSelectedIndex(0);
                                        }else {
                                            comboBoxConcat.removeAllItems();
                                            comboBoxConcat.setEnabled(false);
                                        }
                                    }
                                }
                            });
                        }




                        break;
                    case "us":
                        comboBoxExtension = (JComboBox<ExtensionUS>) jcomboBox[j];
                        if(comboBoxExtension.getSelectedItem()!=null) {
                            if (comboBoxSerie.getSelectedItem() != null) {
                                for (ExtensionUS extensionUS : ((SerieUS) comboBoxSerie.getSelectedItem()).getExtensionsUS()) { // A T T E N T I O N
                                    comboBoxExtension.addItem(extensionUS);
                                }
                                comboBoxExtension.setSelectedIndex(0);
                            }
                        }
                        break;
                }

            } else if (labels[j].equals("idExtensionPCA")) {
                JTextField jtf = (JTextField) jcomboBox[j];
                jtf.setText((ExtensionUS.getMaxIdPCA() + 10) + "");
            }else if(labels[j].equals("DateSortie")) {
                JXDatePicker picker = (JXDatePicker) jcomboBox[j];
                picker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
            }else if(labels[j].equals("source")){
                comboBoxSource = (JComboBox) jcomboBox[j];
                comboBoxSource.addItem("tcgRepublic");
                comboBoxSource.addItem("pokellector");
                comboBoxSource.setSelectedIndex(0);
                for(int i=0;i<labels.length;i++){
                    if(labels[i].equals("nomExtensionTraduit")) {
                        jLabels[i].setVisible(false);
                        jcomboBox[i].setVisible(false);
                    }
                }
                comboBoxSource.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (comboBoxSource.getSelectedIndex()!=1){
                            for(int i=0;i<labels.length;i++){
                                if(labels[i].equals("nomExtensionTraduit")) {
                                    jLabels[i].setVisible(false);
                                    jcomboBox[i].setVisible(false);
                                }
                            }
                        }else{
                            for(int i=0;i<labels.length;i++){
                                jLabels[i].setVisible(true);
                                jcomboBox[i].setVisible(true);
                            }
                        }

                    }
                });
            }

        }

        titre = BorderFactory.createTitledBorder(txtTitre);
        this.setBorder(titre);

    }

    public Object saisir(int SELECTEUR){
        Extension extension = null;
        Extension extensionJAP = null;
        ExpansionBulbapedia expansionBulbapedia =null;
        ExtensionSource extensionSource =null;
        switch (SELECTEUR){
            case K.EXTENSIONUS:
                extension = new ExtensionUS();
                break;
            case K.EXTENSIONJAP:
                extensionJAP = new ExtensionJAP();
                break;
            case K.EXPANSIONBULBAPEDIA:
                expansionBulbapedia = new ExpansionBulbapedia();
                break;
            case K.EXTENSIONSOURCE:
                extensionSource = new ExtensionSource();
                break;
        }


        switch (pays){
            case "us":
                extension =  new ExtensionUS();
                expansionBulbapedia =new ExpansionBulbapedia("us");
                //expansionBulbapedia.setExtensionUS((ExtensionUS) extension);
                //((ExtensionUS) extension).getExpansionBulbapedias().add(expansionBulbapedia);
                break;
            case "jap":
                extension =  new ExtensionJAP();
                expansionBulbapedia =new ExpansionBulbapedia("jap");
                //expansionBulbapedia.setExtensionJAP((ExtensionJAP) extension);
                //((ExtensionJAP) extension).getExpansionBulbapedias().add(expansionBulbapedia);
                break;
        }


        //SerieUS serie =null;
        SerieJAP serieJAP=null;
        for (Component x : getComponents()) {
            if (x.getClass() == JComboBox.class || x.getClass() == JTextField.class || x.getClass() == JXDatePicker.class)
                switch (x.getName()) {
                    case "serie_id":
                        switch (pays) {
                            case "us":
                                SerieUS serie = (SerieUS) ((JComboBox) x).getSelectedItem();
                                ((ExtensionUS) extension).setSerieUS(serie);
                                //extension.setSerie_id(serie.getId());
                                serie.getExtensionsUS().add((ExtensionUS) extension);
                                break;
                            case "jap":
                                serieJAP = (SerieJAP) ((JComboBox) x).getSelectedItem();
                                ((ExtensionJAP) extension).setSerieJAP(serieJAP);
                                serieJAP.getExtensionsJAP().add((ExtensionJAP)extension);
                                break;
                        }
                        break;
                    case "extension_id":
                        switch (pays) {
                            case "us":
                                extension =(ExtensionUS) ((JComboBox) x).getSelectedItem();
                                break;
                            case "jap":
                                extension =(ExtensionJAP) ((JComboBox) x).getSelectedItem();
                                break;
                        }
                        break;
                    case "concat":
                        ExtensionConcatenee ec = (ExtensionConcatenee) ((JComboBox) x).getSelectedItem();
                        if(ec!=null) {
                            // à vérifier
                           // extension. setNomTableauBulbapedia(ec.getNomTableauBulbapedia());
                            extension.setPremierNumero(ec.getPremierNumero());
                        }
                        break;
                    case "nomUS":
                        String nomUS = ((JTextField) x).getText().trim();
                        extension.setNomUS(nomUS);
                        break;
                    case "nom":
                        String nom = ((JTextField) x).getText().trim();
                        extension.setNom(nom);
                        break;
                    case "idExtensionPCA":
                        String extensionPCA = ((JTextField) x).getText().trim();
                        try {
                            String idPCA = extensionPCA;
                            extension.setIdPCA(idPCA);
                        } catch (Exception ex) {
                            JOptionPane.showConfirmDialog(null, "idExtensionPCA doit être un nombre entier !!!");
                        }
                        break;
                    case "nomTableauBulbapedia":
                        String nomTableau = ((JTextField) x).getText().trim();
                        expansionBulbapedia.setNomTableau(nomTableau);
                        break;
                    case "type":
                        String type = ((JTextField) x).getText().trim();
                        expansionBulbapedia.setType(type);
                        break;
                    case "nomType":
                        String nomType = ((JTextField) x).getText().trim();
                        expansionBulbapedia.setNom(nomType);;
                        break;
                    case "sousType":
                        String sousType = ((JTextField) x).getText().trim();
                        expansionBulbapedia.setSousType(sousType);
                        break;
                    case "nomSousType":
                        String nomSousType = ((JTextField) x).getText().trim();
                        expansionBulbapedia.setSousNom(nomSousType);;
                        break;
                    case "PremierNumero":
                        String PremierNumero = ((JTextField) x).getText().trim();
                        expansionBulbapedia.setPremierNumero(PremierNumero);
                        break;
                    case "Promo":
                        boolean promo = ((JCheckBox) x).isSelected();
                        extension.setPromo(promo);
                        break;
                    case "SerieExtensionZebra":
                        String serieExtZEBRA = ((JTextField) x).getText().trim();
                        if(serieExtZEBRA.equals("")){
                            serieJAP.getExtensionsJAP().remove(extension);
                            // extension=null;
                            int option = JOptionPane.showConfirmDialog(app,"Le champ SerieExtensionZebra est obligatoir !");
                            return null;
                        }

                        extension.setSerieExtensionZEBRA(serieExtZEBRA);
                        break;
                    case "nomFR":
                        String nomFR = ((JTextField) x).getText().trim();
                        extension.setNomFR(nomFR);
                        break;
                    case "nomRaccourci":
                        String nomRaccourci = ((JTextField) x).getText().trim();
                        extension.setNomRaccourci(nomRaccourci);
                        break;
                    case "DateSortie":
                        if(!extension.isPromo()) {
                            JXDatePicker picker = (JXDatePicker) x;
                            Date d = picker.getDate();
                            Instant instant = Instant.ofEpochMilli(d.getTime());
                            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                            //extension.setDateSortie(localDateTime.toLocalDate());   MIGRATION
                        }
                        break;
                    case "numSur":
                        String numSur = ((JTextField) x).getText().trim();
                        extension.setNumSur(numSur);
                        break;
                    case "japName":
                        String japName = ((JTextField) x).getText().trim();
                        ((ExtensionJAP) extension).setJapName(japName);
                        break;
                    case "japTraductionName":
                        String japTraductionName = ((JTextField) x).getText().trim();
                        ((ExtensionJAP) extension).setJapTraductionName(japTraductionName);
                        break;
                        // Attension à implementer tout ça!
                    case "url":
                        String Url = ((JTextField) x).getText().trim();
                        if(extensionSource!=null)
                            extensionSource.setUrl(Url);
                        else if(expansionBulbapedia!=null)
                            expansionBulbapedia.setUrl(Url);
                        break;
                    case "locale":
                        String locale = ((JTextField) x).getText().trim();
                        extensionSource.setLocale(locale);
                        break;
                    case "discriminator":
                        String discriminator = ((JTextField) x).getText().trim();
                        extensionSource.setDiscriminator(discriminator);
                        break;
                    case "nomExtensionSource":
                        String nomExtensionSource = ((JTextField) x).getText().trim();
                        extensionSource.setNomExtensionSource(nomExtensionSource);
                        break;
                    case "nomExtensionTraduit":
                        String nomExtensionTraduit = ((JTextField) x).getText().trim();
                        extensionSource.setNomExtensionTraduit(nomExtensionTraduit);
                        break;
                    case "source":
                        String source =(String) ((JComboBox) x).getSelectedItem();
                        extensionSource.setSource(source);
                        break;
                }

        }


        switch (SELECTEUR){
            case K.EXTENSIONUS:
            case K.EXTENSIONJAP:
                return extension;
            case K.EXPANSIONBULBAPEDIA:
                return expansionBulbapedia;
            case K.EXTENSIONSOURCE:
                switch (pays) {
                    case "us":
                        extensionSource.setLocale("us");
                        break;
                    case "jap":
                        extensionSource.setLocale("jap");
                        break;
                }
                extensionSource.setDiscriminator("pok");
                return extensionSource;
            default:
                return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


}
