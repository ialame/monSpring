package com.pca.gui;

import com.pca.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;

import static javax.swing.JOptionPane.OK_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;

//https://bulbapedia.bulbagarden.net/wiki/Secret_Wonders_(TCG)
//https://bulbapedia.bulbagarden.net/wiki/BREAKthrough_(TCG)
public class Pokemon extends JPanel implements ActionListener {
    public Thread t,t2;
    public JPanel  pTree;
    public JButton bAjouter;
    public int id=0;


    public TitledBorder title;
    public JSlider jSlider1 =new JSlider(JSlider.HORIZONTAL,1,5,1);
    public JSlider jSlider2 =new JSlider(JSlider.HORIZONTAL,1,5,1);
    public JSlider jSlider3 =new JSlider(JSlider.HORIZONTAL,1,5,1);
    public JSlider jSlider4 =new JSlider(JSlider.HORIZONTAL,1,5,1);
    public JLabel jLabelBar1= new JLabel("1");
    public JLabel jLabelBar2= new JLabel("1");
    public JLabel jLabelBar3= new JLabel("1");
    public JLabel jLabelBar4= new JLabel("1");
    public JTabbedPane tabbedPane;
    public Map hm = new HashMap();
    public int seriejap_id;
    public String  nom=null;
    public JRadioButton concat,sousExtension;
    public Formulaire pColonne1;
    public JPanel pColonne2= new JPanel();;
    public JPanel pColonne3= new JPanel();;
    public Formulaire formExtensionUS,formExtensionJAP;
    public JPanel pConcat,pExtensionSimple,pExtensionSource;
    public ExtensionJAP extensionJAP=null;
    public ExtensionUS extensionUS;
    public JFrame f,jf;
    public ApplicationPM app;

    Pokemon(ApplicationPM app){
        this.app=app;
        app.pays=K.JAPON;
        setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();

        Box pMultiExtensionCorps = Box.createVerticalBox();
        pMultiExtensionCorps.setVisible(false);
        JPanel pExtensionJAP = new JPanel(new BorderLayout());
        String labelsJAP[] = {"serie_id", "idExtensionPCA","nom", "Promo","DateSortie","nomRaccourci","japName", "japTraductionName","SerieExtensionZebra"};

        pColonne1=new Formulaire(labelsJAP,"Nouvelle Extension","jap",app) ;
        String t2[] = {"nomTableauBulbapedia","type","nomType","sousType","nomSousType","PremierNumero","numSur","url"};;

        String t3[] = {"urlPokelector","locale","discriminator","nomExtensionSource","nomExtensionTraduit","source"};;
        pExtensionSimple = new JPanel();
        pExtensionSimple.add(pColonne1);
        pExtensionSimple.add(pColonne2);
        pExtensionSimple.add(pColonne3);

        String labels1[] = {"serie_id","extension_id","concat"};
        String labels2[] = {"serie_id","extension_id"};


        String labelsUS[] = {"serie_id","nom", "nomUS",  "idExtensionPCA"
                , "nomTableauBulbapedia","type", "nomType","sousType","nomSousType",  "nomFR","nomRaccourci", "SerieExtensionZebra", "URL", "PremierNumero", "DateSortie"};

        //pExtensionNonMulti = new Formulaire(labels, "Nouvelle Extension","jap");
        formExtensionJAP = new Formulaire(labelsJAP, "Nouvelle Extension","jap",app);
        formExtensionUS = new Formulaire(labelsUS, "Nouvelle Extension","us",app);
        tabbedPane.addTab("Multi-extension JAP", null, pMultiExtensionCorps, "Multi-extension");
        tabbedPane.addTab("Extension JAP", null,pExtensionJAP , "Extension JAP");//formExtensionJAP
        tabbedPane.addTab("Cartes JAP", null, new PageGui(app,"jap") , "Cartes JAP");//pCartesJAP
        tabbedPane.addTab("Extension US", null, formExtensionUS, "Extension US");
        tabbedPane.addTab("Cartes US", null, new PageGui(app,"us"),"Cartes US");// pCartesUS
        tabbedPane.addTab("BDD", null, pTree, "bdd");

        this.add(tabbedPane, BorderLayout.NORTH);
        bAjouter = new JButton("Parser la multi extension japonaise");
        //    B O U T O N
        JPanel pBouton = new JPanel();
        pBouton.add(bAjouter);
        bAjouter.setVisible(false);
        this.add(pBouton, BorderLayout.CENTER);

        bAjouter.addActionListener(this);
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
              switch (((JTabbedPane) e.getSource()).getSelectedIndex()) {
                  case 0:
                      bAjouter.setVisible(true);
                      bAjouter.setText("Parser la multi extension japonaise");
                      break;
                  case 1:
                      bAjouter.setVisible(true);
                      bAjouter.setText("Créer Extension japonaise");
                      break;
                  case 3:
                      bAjouter.setVisible(true);
                      bAjouter.setText("Créer Extension US");
                      break;
                  case 5:
                      bAjouter.setText("B D D");
                      break;
                  default:
                      bAjouter.setVisible(false);

              }
            }
        });



        //    T I T R E
        title = BorderFactory.createTitledBorder("Net2SQL JAP");
        this.setBorder(title);
        //    T A B L E U R

        //////////////////////    le pFrame     /////////////////////////////

        // Si la "Multi-extension" est choisie on developpe le choix dans pMultiExtension:
        //JPanel pMultiExtensionCorps = new JPanel();

        ButtonGroup gb = new ButtonGroup();

        concat = new JRadioButton("Concaténer");
        Font fonte = new Font("TimesRoman",Font.BOLD,16);
        concat.setFont(fonte);
        gb.add(concat);
        sousExtension = new JRadioButton("Sous Extension");
        sousExtension.setFont(fonte);
        gb.add(sousExtension);

        JPanel pNBConcatenation =new JPanel();

        pNBConcatenation.add(nbElements("Nb des concaténations",jSlider1,jLabelBar1));
        JButton jbtn1 = new JButton("ok");
        pConcat = new JPanel();// concatNouvellExtension();
        pConcat.add(concatExtensionExistant());
        pConcat.setVisible(concat.isSelected());
        jbtn1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            pConcat.removeAll();
            pConcat.add(concatExtensionExistant());
            pConcat.setVisible(concat.isSelected());
            app.pack();
            }
        });
        pNBConcatenation.add(jbtn1);
        title = BorderFactory.createTitledBorder("");
        pNBConcatenation.setBorder(title);

        JPanel pNBsousExtension =new JPanel();

        pNBsousExtension.add(nbElements("Nb des sous extensions",jSlider2,jLabelBar2));
        JButton jbtn2 = new JButton("ok");
        JPanel pSENE = new JPanel();// ssExtensionNouvellExtension();;
        pSENE.add(sousExtensionExtensionExistante());
        pSENE.setVisible(sousExtension.isSelected());
        jbtn2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pSENE.removeAll();
                pSENE.add(sousExtensionExtensionExistante());
                pSENE.setVisible(sousExtension.isSelected());
                app.pack();
            }
        });

        pNBsousExtension.add(jbtn2);
        title = BorderFactory.createTitledBorder("");
        pNBsousExtension.setBorder(title);

        concat.setAlignmentX(Component.LEFT_ALIGNMENT);
        pNBConcatenation.setAlignmentX(Component.LEFT_ALIGNMENT);
        pConcat.setAlignmentX(Component.LEFT_ALIGNMENT);
        pSENE.setAlignmentX(Component.LEFT_ALIGNMENT);
        pNBsousExtension.setAlignmentX(Component.LEFT_ALIGNMENT);
        sousExtension.setAlignmentX(Component.LEFT_ALIGNMENT);
        pMultiExtensionCorps.add(concat);
        pMultiExtensionCorps.add(pNBConcatenation);
        pMultiExtensionCorps.add(pConcat);
        pMultiExtensionCorps.add(sousExtension);
        pMultiExtensionCorps.add(pNBsousExtension);
        pMultiExtensionCorps.add(pSENE);
        pNBConcatenation.setVisible(false);
        pNBsousExtension.setVisible(false);

        concat.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                pNBConcatenation.setVisible(concat.isSelected());
                pConcat.setVisible(concat.isSelected());
                app.pack();
            }
        });
        sousExtension.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                pNBsousExtension.setVisible(sousExtension.isSelected());
                pSENE.setVisible(sousExtension.isSelected());
                app.pack();
            }
        });
        //////////////////////////////////////////////////////////////////////

        JPanel pNBExpansionBulbapedia =new JPanel();
        pNBExpansionBulbapedia.add(nbElements("Nb d'ExpansionsBulbapedia",jSlider3,jLabelBar3));
        JButton jbtn3 = new JButton("ok");

        JPanel pNBExtensionSource =new JPanel();
        pNBExtensionSource.add(nbElements("Nb d'ExtensionSources",jSlider4,jLabelBar4));
        JButton jbtn4 = new JButton("ok");

        JPanel pCurseurs = new JPanel(new BorderLayout());
        pCurseurs.add(pNBExpansionBulbapedia,BorderLayout.NORTH);
        pCurseurs.add(pNBExtensionSource,BorderLayout.SOUTH);

        pExtensionJAP.add(pCurseurs,BorderLayout.NORTH);
        //pSimpleExtensionCorps.add(pExpansionBulbapedia,BorderLayout.CENTER);
        pExtensionJAP.add(pExtensionSimple,BorderLayout.SOUTH);
        //pExpansionBulbapedia = new JPanel();// concatNouvellExtension();
        //pExpansionBulbapedia.add(expansionsBulbapedia());
        //ExpansionBulbapedia.setVisible(concat.isSelected());
        jbtn3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pColonne2.removeAll();
                pColonne1 = new Formulaire(labelsJAP,"Nouvelle Extension","jap",app);
                expansionsBulbapedia();
                pExtensionSimple.removeAll();

                pExtensionSimple.add(pColonne1);
                pExtensionSimple.add(pColonne2);
                pExtensionSimple.add(pColonne3);
                app.pack();
            }
        });
        jbtn4.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pColonne3.removeAll();
                pColonne1 = new Formulaire(labelsJAP,"Nouvelle Extension","jap",app);
                extensionSource();
                pExtensionSimple.removeAll();

                pExtensionSimple.add(pColonne1);
                pExtensionSimple.add(pColonne2);
                pExtensionSimple.add(pColonne3);
                app.pack();
            }
        });

        pNBExpansionBulbapedia.add(jbtn3);
        title = BorderFactory.createTitledBorder("");
        pNBExpansionBulbapedia.setBorder(title);

        //////////////////////////////////////////////////////////////////////

        pExtensionSource = new JPanel();// concatNouvellExtension();
        pExtensionSource.add(expansionsBulbapedia());
        //ExpansionBulbapedia.setVisible(concat.isSelected());


        pNBExtensionSource.add(jbtn4);
        title = BorderFactory.createTitledBorder("");
        pNBExtensionSource.setBorder(title);
        //////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////

    }
    void setTableur(JTablePok jTablePok){
        //JPanel tab = (JPanel) ((JTabbedPane) app.getContentPane().getComponent(0)).getSelectedComponent();
        JPanel tab = (JPanel) tabbedPane.getSelectedComponent();
        tab.add(jTablePok, BorderLayout.SOUTH);
        app.pack();
    }
  /*  void setTree(){
        pTree = new TreeJAP(ApplicationPM.DB.getSeriesJAP(),app);
    }*/


    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */

    class Ecouteur implements ChangeListener {
        JSlider jslider; JLabel jlabelBar;
        Ecouteur(JSlider jslider,JLabel jlabelBar){
            this.jslider=jslider;
            this.jlabelBar=jlabelBar;

        }
        @Override
        public void stateChanged(ChangeEvent e) {
            System.out.println("AdjustmentEvent ..."+e);
            jlabelBar.setText(jslider.getValue() + "");
            System.out.println(jslider.getValue());
            System.out.println(jlabelBar.getText());
            //System.out.println(jtextBar1.getText());
            app.pack();
        }
    }


    public JPanel nbElements(String titre,JSlider jslider,JLabel jLabelBar){
        jslider.setToolTipText("totot");
        jslider.addChangeListener(new Ecouteur(jslider,jLabelBar));
        //jLabelBar = new JTextField("1");
        JPanel pTextBar = new JPanel();
        pTextBar.add(jLabelBar);
        JPanel pNombreExtension = new JPanel();
        pNombreExtension.add(new JLabel(titre));
        pNombreExtension.add(pTextBar);
        pNombreExtension.add(jslider);
        return pNombreExtension;
    }


    public JPanel concatExtensionExistant(){
        JPanel pForm =new JPanel();
        System.out.println("toto toto");
        int n=jSlider1.getValue();
        System.out.println(" valeur="+n);

        String labels[] = {"serie_id","extension_id"};

        pColonne1 = new Formulaire(labels,"Extension principale","jap",app);
        pForm.add(pColonne1);

        pColonne2 = new JPanel();

        BoxLayout layout = new BoxLayout(pColonne2, BoxLayout.Y_AXIS);
        pColonne2.setLayout(layout);

        JPanel pOption1Concatenations[] = new JPanel[n];

        for(int k=0;k<n;k++){
            String t[] = {"nomTableauBulbapedia", "PremierNumero"};

            pOption1Concatenations[k] = new Formulaire(t,"Concaténation "+(k+1),"jap",app);
            //option1[k].setPreferredSize(new Dimension(100,20));
            pOption1Concatenations[k].setAlignmentY(Component.LEFT_ALIGNMENT);
            pColonne2.add(pOption1Concatenations[k]);
        }
        pForm.add(pColonne2);
        return pForm;
    }




    public JPanel expansionsBulbapedia(){
        int n2=jSlider3.getValue();
        System.out.println(" valeur="+n2);

        pColonne2 = new JPanel();

        BoxLayout layout = new BoxLayout(pColonne2, BoxLayout.Y_AXIS);
        pColonne2.setLayout(layout);

        JPanel pOption1expansionsBulbapedia[] = new JPanel[n2];

        for(int k=0;k<n2;k++){
            String t[] = {"nomTableauBulbapedia","type","nomType","sousType","nomSousType","PremierNumero","numSur","url"};;

            pOption1expansionsBulbapedia[k] = new Formulaire(t,"ExpansionBulbapedia  n° "+(k+1),"jap",app);
            //option1[k].setPreferredSize(new Dimension(100,20));
            pOption1expansionsBulbapedia[k].setAlignmentY(Component.LEFT_ALIGNMENT);
            pColonne2.add(pOption1expansionsBulbapedia[k]);
        }

        return pColonne2;
    }

    public JPanel extensionSource(){
        int n3=jSlider4.getValue();
        System.out.println(" valeur="+n3);
        pColonne3 = new JPanel();
        BoxLayout layout = new BoxLayout(pColonne3, BoxLayout.Y_AXIS);
        pColonne3.setLayout(layout);
        JPanel pOption1Source[] = new JPanel[n3];

        for(int k=0;k<n3;k++){
            String t[] = {"url","nomExtensionSource","source","nomExtensionTraduit"};;

            pOption1Source[k] = new Formulaire(t,"ExtensionSource  n° "+(k+1),"jap",app);
            //option1[k].setPreferredSize(new Dimension(100,20));
            pOption1Source[k].setAlignmentY(Component.LEFT_ALIGNMENT);
            pColonne3.add(pOption1Source[k]);
        }
        return pColonne3;
    }


    public JPanel sousExtensionExtensionExistante(){
        JPanel pForm =new JPanel();
        System.out.println("toto toto");
        int n=jSlider2.getValue();
        System.out.println(" valeur="+n);

        String labels[] = {"serie_id","extension_id"};

        pColonne1 = new Formulaire(labels,"Extension principale","jap",app);
        pForm.add(pColonne1);

        pColonne2 = new JPanel();

        BoxLayout layout = new BoxLayout(pColonne2, BoxLayout.Y_AXIS);
        pColonne2.setLayout(layout);

        JPanel pOption1Concatenations[] = new JPanel[n];


        for(int k=0;k<n;k++){
            String t[] = {"nomTableauBulbapedia", "PremierNumero","idExtensionPCA","SerieExtensionZebra","nomRaccourci","DateSortie"};

            pOption1Concatenations[k] = new Formulaire(t,"Sous Extension "+(k+1),"jap",app);
            pOption1Concatenations[k].setAlignmentY(Component.LEFT_ALIGNMENT);
            pColonne2.add(pOption1Concatenations[k]);
        }
        pForm.add(pColonne2);
        return pForm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton b = (JButton) hm.get("bAjouter");
        //if(e.getSource()==bAjouter){
             //extensionJAP = new ExtensionJAP();
            //jTablePok.getModele().clear();
            switch (tabbedPane.getSelectedIndex()){
                case 0:
                    app.pays=K.JAPON;
                    if(concat.isSelected()){
                        ExtensionJAP extensionJAP = (ExtensionJAP) pColonne1.saisir(K.EXTENSIONJAP);

                        for (Component x :pColonne2.getComponents()) {
                            Extension extension  = (ExtensionJAP)  ((Formulaire)x).saisir(K.EXTENSIONJAP);
                            ExtensionConcatenee extensionConcatenee  =  new ExtensionConcatenee();
                            extensionConcatenee.setPremierNumero(((Extension) extension).getPremierNumero());
                            // à verifier
                            //extensionConcatenee.setNomTableauBulbapedia(extension.getNomTableauBulbapedia());
                            extensionConcatenee.setExtensionJap_id(extensionJAP.getId());
                            extensionConcatenee.save();
                        }

                    }else if(sousExtension.isSelected()) {
                            System.out.println("Première colonne");
                            ExtensionJAP  extensionJAP0 =(ExtensionJAP) pColonne1.saisir(K.EXTENSIONJAP);

                            System.out.println("Deuxième colonne");

                            for (Component x :pColonne2.getComponents()) {
                                //ExtensionJAP extensionJAP=extensionJAP0.copy();
                                //extensionJAP.setId();
                                ExtensionJAP extensionJAP2=(ExtensionJAP) ((Formulaire)x).saisir(K.EXTENSIONJAP);
                                // à verifier
                                //extensionJAP.setNomTableauBulbapedia(extensionJAP2.getNomTableauBulbapedia());
                                extensionJAP.setPremierNumero(extensionJAP2.getPremierNumero());
                                extensionJAP.setNomRaccourci(extensionJAP2.getNomRaccourci());
                                extensionJAP.setIdPCA(extensionJAP2.getIdPCA());
                                extensionJAP.setSerieExtensionZEBRA(extensionJAP2.getSerieExtensionZEBRA());
                                extensionJAP.setDateSortie(extensionJAP2.getDateSortie());

                                //parser(extensionJAP);
                                new ParseExtensionPok(extensionJAP, app);

                            }
                    }
                    break;
                case 1:
                    app.pays=K.JAPON;
                    //////////////////////////////////////////////////////////
                    int i= showConfirmDialog(app,"Confirmer la création de l'extension !");
                    if(OK_OPTION!=i)
                        return;
                    ExtensionJAP extensionJAP = (ExtensionJAP) pColonne1.saisir(K.EXTENSIONJAP);
                    if(extensionJAP==null)return;

                    for (Component x :pColonne2.getComponents()) {
                        ExpansionBulbapedia expansionBulbapedia  = (ExpansionBulbapedia) ((Formulaire)x).saisir(K.EXPANSIONBULBAPEDIA);
                        //expansionBulbapedia.setPremierNumero(((Extension) extension).getPremierNumero());
                        // à verifier
                        //extensionConcatenee.setNomTableauBulbapedia(extension.getNomTableauBulbapedia());
                        expansionBulbapedia.setExtensionJAP(extensionJAP);
                        extensionJAP.getExpansionBulbapedias().add(expansionBulbapedia);
                        //expansionBulbapedia.save();
                    }
                    for (Component x :pColonne3.getComponents()) {
                        ExtensionSource extensionSource  = (ExtensionSource) ((Formulaire)x).saisir(K.EXTENSIONSOURCE);
                        //expansionBulbapedia.setPremierNumero(((Extension) extension).getPremierNumero());
                        // à verifier
                        //extensionConcatenee.setNomTableauBulbapedia(extension.getNomTableauBulbapedia());
                        extensionSource.getExtensionJAPs().add(extensionJAP);
                        extensionJAP.getExtensionSources().add(extensionSource);
                        //expansionBulbapedia.save();
                    }


                    //////////////////////////////////////////////////////////
                    // extensionJAP = (ExtensionJAP) formExtensionJAP.saisir();
                    try {

                        EntityManager entityManager = ApplicationPM.entityManager;
                        EntityTransaction trans = entityManager.getTransaction();
                        trans.begin();
                        entityManager.persist( extensionJAP );
                        System.out.println("--------------------------------------------------------------");
                        trans.commit();
                    } finally {
                        //if ( entityManager != null ) entityManager.close();
                        //if ( entityManagerFactory != null ) entityManagerFactory.close();
                    }
                    break;
                case 2:

                    break;
                case 3:
                    //app.pays=K.US;
                    extensionUS = (ExtensionUS) formExtensionUS.saisir(K.EXTENSIONJAP);
                    try {
                        EntityManager entityManager = ApplicationPM.entityManager;
                        EntityTransaction trans = entityManager.getTransaction();
                        trans.begin();
                        entityManager.persist( extensionUS );
                        System.out.println("--------------------------------------------------------------");
                        trans.commit();
                    } finally {
                        //if ( entityManager != null ) entityManager.close();
                        //if ( entityManagerFactory != null ) entityManagerFactory.close();
                    }
                    break;
                case 4:

                    break;
            }

        //}
        System.out.println("\n\n/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_\n");
        System.out.println("/^\\_/^\\_        C'EST LA FIN             _/^\\_/^\\_\n");
        System.out.println("/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_/^\\_");
    }

    public int getSeriejap_id() {
        return seriejap_id;
    }

    /**
     *
     * @return int
     */
    public static int getMaxIdExtension(){
        int idExtension=0;
        /*try {
            Statement statement = ApplicationPM.DB.getConnect().createStatement();
            String sql2 = "select max(id) as max from  ";// +com.pca.gui.ApplicationPM.getTable_ExtensionPokemon();
            ResultSet resultMax = statement.executeQuery(sql2);
            if (resultMax.next()) {
                idExtension=resultMax.getInt("max") ;
            }
            statement.close();
        } catch (Exception e1) {
            System.out.println("Dans Pokemon : "+e1);

        }*/
        return idExtension;
    }

}

