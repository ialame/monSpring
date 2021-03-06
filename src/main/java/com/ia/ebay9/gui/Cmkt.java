package com.ia.ebay9.gui;//import com.sun.xml.internal.ws.developer.MemberSubmissionEndpointReference;

import com.ia.ebay9.entity.CarteVendue;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DatabaseMetaData;
import java.time.LocalDateTime;

/**
 * Created by ia on 23/04/2019.
 */
public class Cmkt {
    protected Cmkt2Magic cmkt2magic;
    public Thread t1;
    public JProgressBar bar1;
    protected JButton bCmkt2SQL;
    public JComboBox lstCmkt2SQL;
    JRadioButton allExts, newExt;
    JComboBox comboBoxSerie = new JComboBox();
    JComboBox comboBoxExtension = new JComboBox();
    public static String baseDD = "";

    public JPanel ihm() {
        JPanel pPokemon = new JPanel(new BorderLayout());
        JButton bJson2sql, bExtensions, bFiltres;
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
        baseDD = "Cmkt2SQL" + baseDD.replaceFirst(":", "h").replaceFirst(":", "mn").replace("-", "_") + "s";
        cmkt2magic = new Cmkt2Magic(this);

        JPanel pNord = new JPanel();

        JPanel pCmkt2SQL = new JPanel(new BorderLayout());
        pCmkt2SQL.add(bCmkt2SQL = new JButton("Exécuter Cmkt2SQL"), BorderLayout.NORTH);
        pCmkt2SQL.add(bar1 = new JProgressBar(), BorderLayout.SOUTH);
        JLabel lblTab1 = new JLabel("Nouvelle table : ");
        lblTab1.setForeground(Color.GRAY);
        pCmkt2SQL.add(lblTab1, BorderLayout.WEST);

        lstCmkt2SQL = new JComboBox();
        lstCmkt2SQL.setName("net2sql");

        pCmkt2SQL.add(lstCmkt2SQL, BorderLayout.CENTER);

        TitledBorder title;
        title = BorderFactory.createTitledBorder("Cmkt2SQL");
        pCmkt2SQL.setBorder(title);
        pNord.add(pCmkt2SQL);

        /*try {
            dbmdFX = ApplicationPM.DB.getConnect().getMetaData();
            ResultSet rs = dbmdFX.getTables(null, null, "N%s", types);
            int k = 0;
            if (k++ == 0) {
                lstCmkt2SQL.removeAllItems();
                lstCmkt2SQL.addItem(baseDD);//"Nouvelle Table");
            }
            while (rs.next()) {
                lstCmkt2SQL.addItem(rs.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        lstCmkt2SQL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseDD = lstCmkt2SQL.getSelectedItem().toString();
            }
        });

        //console = new JTextArea("ici la sortie\n");

        bCmkt2SQL.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                baseDD = lstCmkt2SQL.getSelectedItem().toString();
                try {
                    t1 = new Thread(cmkt2magic);
                    //CarteEbay.baseDDCarteEbay = "CarteEbay";
                    //CarteVendue.baseDDCarteVendue = baseDD;// + "CarteVendue";
                    CarteVendue.creerBDD();
                    cmkt2magic.run();
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
        pNouvelle.add(newExt, BorderLayout.WEST);
        pNouvelle.add(comboBoxSerie, BorderLayout.CENTER);
        pNouvelle.add(comboBoxExtension, BorderLayout.EAST);


        //comboBoxSerie.addActionListener(this);
        /*try {
            Statement statement = ApplicationPM.DB.getConnect().createStatement();
            String sql = "SELECT id  FROM " + ApplicationPM.DB.getTable_SerieMagic();//+ " where id=1";
            ResultSet resultSerie = statement.executeQuery(sql);
            //comboBoxSerie.addItem("choisir la série ..");
            while (resultSerie.next()) {

                comboBoxSerie.addItem(new SerieMagic(resultSerie.getInt("id")));
            }
            statement.close();
        } catch (Exception exp) {

        }*/
        /*try {
            Statement statement = ApplicationPM.DB.getConnect().createStatement();
            SerieMagic serie = (SerieMagic) comboBoxSerie.getSelectedItem();
            *//*for (Extension e : serie.getExtensions())
                comboBoxExtension.addItem(e);*//*
        } catch (Exception exp) {

        }*/
        comboBoxSerie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*try {
                    Statement statement = ApplicationPM.DB.getConnect().createStatement();
                    SerieMagic serie = (SerieMagic) comboBoxSerie.getSelectedItem();
                    *//*for (Extension ext : serie.getExtensions())
                        comboBoxExtension.addItem(ext);*//*

                } catch (Exception exp) {

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
        pToutes.add(allExts, FlowLayout.LEFT);
        gb.add(allExts);


        JPanel pImages = new JPanel(new FlowLayout());
        JButton btn = new JButton("Choisir un dossier !");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int ret = jfc.showOpenDialog(null); // ne te rend la main que si tu ferme
                if (ret == JFileChooser.APPROVE_OPTION) { // validation
                    System.out.println("Selected dir : " + jfc.getSelectedFile());
                }
            }
        });

        pImages.add(btn);


        pImages.add(bExtensions = new JButton("Sauvegarder les extensions"));

        //TitledBorder title;
        title = BorderFactory.createTitledBorder("Extensions");
        pCentre.setBorder(title);

        pChoix.add(pNouvelle, BorderLayout.NORTH);
        pChoix.add(pToutes, BorderLayout.SOUTH);
        pCentre.add(pChoix, BorderLayout.NORTH);
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


        bExtensions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ReadExcelDemo red = new ReadExcelDemo();
                String[] toto ={"toto"};
                try{
                    //red.ext2ext();
                    //red.main(toto);
                }catch (Exception ex){

                }

                getExtensions();

                /*
                Serie serie = null;
                if (allExts.isSelected()) {// Toute les extension
                    for (int i = 0; i < comboBoxSerie.getItemCount(); i++) {
                        serie = (Serie) comboBoxSerie.getItemAt(i);
                        serie.getImages();
                    }
                } else {
                    Extension extension = (Extension) comboBoxExtension.getSelectedItem();
                    extension.getImages();
                }
                */
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

    public void getExtensionsUN() {
        /*String CARTES = "Magic";//"Magic";//"Pokemon"
        //String hrefNouvellesPage="https://www.cardmarket.com/fr/"+CARTES+"/Products/Cartes?perSite=50&site=";
        //String hrefNouvellesPage="https://www.cardmarket.com/fr/"+CARTES+"/Products/Singles?resultsPage=";
        String nameFR = "", nameUS = "", graphe = "";

        int iii = 0;
        Document docPage = null, doc = null;
        while (docPage == null || docPage.text().equals("")) {
            try {

                String URL = "https://www.cardmarket.com/fr/Magic/Expansions";
                //
                docPage = Jsoup.connect(URL).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                        .get();
                doc = Jsoup.parseBodyFragment(docPage.toString());
            } catch (Exception httpExcep) {/////////// ICI ICI ici
                System.out.println("ERREUR    =" + httpExcep + "   ------>   doc = " + doc);
                //System.exit(1);
            }

        }


        Iterator<Element> series = doc.select("div#ExpansionList").first().children().iterator();
        Element s = null, e = null, c = null;
        String nom;
        int idSerie=1,idExtension=1,idCarte=1;
        //SerieMagic.creerBDD(com.ia.ebay9.gui.ApplicationPM.getTable_SerieMagic());
        ExtensionMagic.creerBDD(ApplicationPM.DB.getTable_ExtensionMagic());
        series.next();// A T T E N T I O N  la série 2019 à mettre à jour !
        while (series.hasNext()) {
            s = series.next();
            String annee = s.attr("id"); // collapse2017
            SerieMagic serie = new SerieMagic();
            serie.setId(idSerie++);
            //serie.setName(annee);
            serie.setNomFR(annee);
            //serie.setNom(annee);
            serie.save(ApplicationPM.DB.getTable_SerieMagic());
            System.out.println("  "+annee);
            Iterator<Element> extensions = doc.select("div#collapse"+annee).first().children().iterator();
            while (extensions.hasNext()) {
                e = extensions.next();
                nom = e.attr("data-local-name");
                int nbCartes =0;
                try {
                    String S =e.select("div.col-2").first().text();
                    nbCartes = Integer.parseInt(S.substring(0,S.indexOf(" ")));
                    String date = e.select("div.col-3").first().text();
                    System.out.println("              "+nom +" n="+nbCartes+" date="+date);
                }catch(Exception ex){
                    System.out.println(" nbCartes dans Cmkt.java "+ex);

                }

                ExtensionMagic extension = new ExtensionMagic();
                extension.setId(idExtension++);
                //extension.setSerieABC(serie);
                serie.addExtension(extension);
                extension.setNomFR(nom);
                //extension.setName(nom);

                //extension.setReleaseDate(date);
                //extension.setNbCartes(nbCartes);
                extension.save(ApplicationPM.DB.getTable_ExtensionMagic());



            }


        }
*/
    }

    public void getExtensions() {//ZERO
        //CarteMagicMKT.creerBDD();   ATTENSION I C I    ATTENSION I C I    ATTENSION I C I    ATTENSION I C I    ATTENSION I C I
/*
        int idSerie=1, idExtension =1, idCarte = 1;
        String hrefNouvellesCartes = "https://www.cardmarket.com/en/Magic/Expansions";
        Document doc = null;
        ArrayList<String>[] NameAngJap = new ArrayList[5];

        for (int i = 0; i < 5; i++) NameAngJap[i] = new ArrayList<String>();

        while(doc==null || doc.text().equals("")) {
            try {


                doc = Jsoup.connect(hrefNouvellesCartes).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                        .get();
                doc = Jsoup.parseBodyFragment(doc.toString());
                ///////////////////////////////////////////////////////


            } catch (Exception httpExcep) {/////////// ICI ICI ici
                System.out.println("ERREUR    =" + httpExcep + "   ------>  + URL ");
                continue;

            }

        }

        // On cherche la bonne table
        Iterator<Element> series = doc.select("div#ExpansionList").first().children().iterator();// c'est un BIG dans TABLE
        doc = null;

        Element s = null;

        while (series.hasNext()) {
            s = series.next();
            String annee = s.attr("id");
            SerieMagic serie = new SerieMagic();
            serie.setId(idSerie++);
            //serie.setName(annee);
            serie.setNomFR(annee);
            //serie.setNom(annee);
            serie.save(ApplicationPM.DB.getTable_SerieMagic());
            Element elemExtensions = s.select("div#collapse" + annee).first();
            Iterator<Element> extensions = elemExtensions.children().iterator();// c'est un BIG dans TABLE
            Element e = null;
            while (extensions.hasNext()) {
                e = extensions.next();
                Iterator<Element> DivProprietes = e.children().iterator();// c'est un BIG dans TABLE
                Element divPropriete = null;

                divPropriete = DivProprietes.next();
                divPropriete = DivProprietes.next();
                divPropriete = DivProprietes.next();
                String href = e.select("a").attr("href");
                href="https://www.cardmarket.com"+href.replace("Expansions","Products/Singles")+"?perSite=50&site=";
                String nom = divPropriete.text();
                divPropriete = DivProprietes.next();
                String nb = divPropriete.text();
                divPropriete = DivProprietes.next();
                String date = divPropriete.text();
                // faire la correspondance ici, ne traite que les extensions en base MKT
                Connection connection = ApplicationPM.DB.getConnect();
                int id =0;
                ExtensionMagic extensionmagicMKT=null;
                try {
                    Statement statement1 = connection.createStatement();
                    Statement statement2 = connection.createStatement();
                    String sql = "select * from extensionMagicMKT where name=\""+nom+"\";";
                    ResultSet rs = statement1.executeQuery(sql);
                    if (!rs.next()) {
                        continue;
                    }
                    id = rs.getInt("id");
                    sql = "select extensionmagic_id from extensionmagicmkt_extensionmagic where extensionmagicmkt_id=\""+id+"\";";
                    rs = statement1.executeQuery(sql);
                    if (!rs.next()) {
                        continue;
                    }
                    id = rs.getInt("extensionmagic_id");
                    sql = "select * from extensionMagic where id="+id+";";
                    rs = statement1.executeQuery(sql);
                    extensionmagicMKT = new ExtensionMagic(rs);
                }catch(Exception ex){
                    System.out.println(ex);
                }


                //extensionmagicMKT.setId(idExtension++);
                //extensionmagicMKT.setSerieABC(serie);
                //extensionmagicMKT.setName(nom);
                int size =Integer.parseInt(nb.substring(0,nb.indexOf(" ")).trim()) ;
                extensionmagicMKT.setTotalSetSize(size);
                extensionmagicMKT.setAnnee(Integer.parseInt(annee));
                System.out.println("\n///////////////////////////////////////////////////////////////////////////////");
                System.out.println("\nExtension: "+ extensionmagicMKT);
                //"7th December, 2018"
                String date0 = date.substring(0,date.indexOf(" "));
                int pos1 = date0.indexOf("th");
                if(pos1==-1)
                    pos1 = date0.indexOf("st");
                if(pos1==-1)
                    pos1 = date0.indexOf("nd");
                if(pos1==-1)
                    pos1 = date0.indexOf("rd");

                int pos2 = date.indexOf(",");
                int day =Integer.parseInt(date.substring(0,pos1).trim()) ;
                int month=0;
                String moi= date.substring(pos1+3,pos2).trim();
                switch(moi){
                    case "January":
                        month=1;
                        break;
                    case "February":
                        month=2;
                        break;
                    case "March":
                        month=3;
                        break;
                    case "April":
                        month=4;
                        break;
                    case "May":
                        month=5;
                        break;
                    case "June":
                        month=6;
                        break;
                    case "July":
                        month=7;
                        break;
                    case "August":
                        month=8;
                        break;
                    case "September":
                        month=9;
                        break;
                    case "October":
                        month=10;
                        break;
                    case "November":
                        month=11;
                        break;
                    case "December":
                        month=12;
                        break;
                }
                int year = Integer.parseInt(date.substring(pos2+2).trim()) ;
                extensionmagicMKT.setReleaseDate(LocalDate.of(year, month, day));

                extensionmagicMKT.save(ApplicationPM.DB.getTable_ExtensionMagic());

                if(size>0) {
                    extensionmagicMKT.netMKT2Cartes(href);
                }

            }
        }
*/
    }



}

