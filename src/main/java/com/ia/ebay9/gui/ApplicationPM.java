package com.ia.ebay9.gui;//import jdk.nashorn.internal.runtime.regexp.joni.Config;cacapipi333  toto123???

import com.ia.ebay9.entity.*;
import com.ia.ebay9.entity.Pokemons;
import com.ia.ebay9.repository.SerieUSRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ia on 12/04/2019.
 */
public class ApplicationPM extends JFrame {
    // https://bulbapedia.bulbagarden.net/wiki/Team_Up_(TCG)
    //  https://bulbapedia.bulbagarden.net/wiki/Secret_Wonders_(TCG)  pas de correspondance !
    // https://bulbapedia.bulbagarden.net/wiki/XY-P_Promotional_cards_(TCG)  promo
    public static EntityManagerFactory entityManagerFactory = null;
    public static EntityManager entityManager = null;
    public static JFrame jFrameConflitJAP;
    public static Connection connect = null;
    public static Statement statement = null;

    JTabbedPane tabbedPane;
    JPanel resultatPane,triPane ;
    public JTablePok resulTable;
    public JTableMagic resulTableMagic;
    public JButton[] buttons = new JButton[7];
    public Pokemon pokemon;
    public It it;
    public Magic magic;
    public Ebay ebay;
    public static int pays;
    public static int typeCarte;
    //public static DB DB;
    public static List<SerieUS> seriesUS;
    public static List<SerieJAP> seriesJAP;
    public static List<Particularite> particularites;
    public static List<Crochet> crochets;
    public static List<Pokemons> pokemons;
    public static ArrayList<CartePokemonSource> cartePokemonSources = new ArrayList<CartePokemonSource>();
    ApplicationPM() {
        super("Application Pokemon Magic Pokemon version 1.0");
        //DB=new DB();
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());

        try {

            entityManagerFactory = Persistence.createEntityManagerFactory("alame");
            entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction trans = entityManager.getTransaction();
            trans.begin();
            //List<CartePokemon> cartePokemons = entityManager.createQuery( "from CartePokemon", CartePokemon.class ).getResultList();
            pokemons = entityManager.createQuery( "from Pokemons", Pokemons.class ).getResultList();
            List<CarteVendue> carteVendues = entityManager.createQuery( "from CarteVendue", CarteVendue.class ).getResultList();
            List<CarteEbay> carteEbays = entityManager.createQuery( "from CarteEbay", CarteEbay.class ).getResultList();
            //List<ExtensionJAP> extensionsJAP = entityManager.createQuery( "from ExtensionJAP", ExtensionJAP.class ).getResultList();
            List<CartePrixClient> cartesPrixClient = entityManager.createQuery( "from CartePrixClient", CartePrixClient.class ).getResultList();
            //List<Promo> promos = entityManager.createQuery( "from Promo", Promo.class ).getResultList();
            //List<Particularite> particularites = entityManager.createQuery( "from Particularite", Particularite.class ).getResultList();
            //List<Carte> Cartes = entityManager.createQuery( "from Carte", Carte.class ).getResultList();
            //List<Image_Carte> ImageCartes = entityManager.createQuery( "from Image_Carte", Image_Carte.class ).getResultList();
            List<ExtensionCN> ImageCartes = entityManager.createQuery( "from ExtensionCN", ExtensionCN.class ).getResultList();
            List<ExpansionBulbapedia>  expansion = entityManager.createQuery( "from ExpansionBulbapedia", ExpansionBulbapedia.class ).getResultList();
            crochets = entityManager.createQuery( "from Crochet", Crochet.class ).getResultList();
            seriesJAP = entityManager.createQuery( "from SerieJAP", SerieJAP.class ).getResultList();

            seriesUS = entityManager.createQuery( "from SerieUS", SerieUS.class ).getResultList();

            particularites= entityManager.createQuery( "from Particularite", Particularite.class ).getResultList();
            //Particularite p = particulariteRepository.findById(id).get();
            SerieUSRepositoryImpl serieUSRepository = new SerieUSRepositoryImpl(entityManager);
            SerieUS serieUS = serieUSRepository.getSerieUSById(Integer.valueOf(1));
            seriesJAP=seriesJAP;
            trans.commit();
            ////////////////////////////////
            Net2mysql net2mysql = new Net2mysql(null);
            net2mysql.run();

//////////////////////////////////////////////
        } finally {
            //if ( entityManager != null ) entityManager.close();
            //if ( entityManagerFactory != null ) entityManagerFactory.close();
        }





        tabbedPane =ihm();
        contentPane.add(tabbedPane, BorderLayout.CENTER);

        resultatPane = new JPanel();

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //////////////////////
    }

    public static void main(String[] str){
        // on spécifie le thème
        MetalLookAndFeel.setCurrentTheme(new JextMetalTheme());
        // on affiche la fenêtre du GUI
        new ApplicationPM();
    }

    public JTabbedPane ihm() {
        pokemon = new Pokemon(this);

        ebay = new Ebay();
        /*
        magic = new Magic(this);
        it = new It(this);
        Cmkt cmkt = new Cmkt();   il faut decommenter

        Panier panier = new Panier();
        */
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel pJap = new JPanel(new BorderLayout());
        pJap.add(pokemon,BorderLayout.CENTER);
        triPane = new JPanel();
        pJap.add(triPane, BorderLayout.EAST);
        triPane.setLayout(new GridLayout(7,1));



        JPanel pEbay = new JPanel(new BorderLayout());
        pEbay.add(ebay.ihm(),BorderLayout.CENTER);
        /*
        JPanel pMagic = new JPanel(new BorderLayout());
        pMagic.add(magic.ihm(),BorderLayout.CENTER);

        JPanel pIt = new JPanel(new BorderLayout());
        pIt.add(it.ihm(),BorderLayout.CENTER);


        JPanel pCmkt = new JPanel(new BorderLayout());
        pCmkt.add(cmkt.ihm(),BorderLayout.CENTER);
        JPanel pPanier = new JPanel(new BorderLayout());
        pPanier.add(panier.ihm(),BorderLayout.CENTER);
        */
        tabbedPane.addTab("Pokemon", null, pJap, "Formulaire");
        tabbedPane.addTab("Magic", null, null, "Formulaire");
        tabbedPane.addTab("DBS", null, null, "Formulaire");

        tabbedPane.addTab("Ebay", null, pEbay, "Formulaire");
        /*
        tabbedPane.addTab("Magic", null, pMagic, "Formulaire");

        tabbedPane.addTab("Italien/Allemand", null, pIt, "Formulaire");
        tabbedPane.addTab("CMKT", null, pCmkt, "Formulaire");
        tabbedPane.addTab("Panier", null, pPanier, "Formulaire");
        //tabbedPane.addTab("DBS", null, pJap, "Formulaire");
        */
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (tabbedPane.getSelectedIndex()==3){
                    ApplicationPM.pays= K.ITALIE;
                    //ModeleDynamiqueCarte.setTitles();   MIGRATION
                }
            }
        });

        return tabbedPane;

    }

    /**
     * ici le volet Pokemon
     */
    public JPanel pokemon() {
        JPanel pPokemon = new JPanel(new BorderLayout());

        return pPokemon;
    }
    public void setResulTableMagic(JTableMagic resulTableMagic) {
        this.resulTableMagic = resulTableMagic;
    }

    public void setResulTable(JTablePok resulTable) {
        this.resulTable = resulTable;
    }


    public static List<SerieUS> getSeriesUS() {
        return seriesUS;
    }

    public void setSeriesUS(List<SerieUS> seriesUS) {
        this.seriesUS = seriesUS;
    }

    public static List<SerieJAP> getSeriesJAP() {
        return seriesJAP;
    }

    public void setSeriesJAP(List<SerieJAP> seriesJAP) {
        this.seriesJAP = seriesJAP;
    }

    public static List<Pokemons> getPokemons() {
        return pokemons;
    }

    public static void setPokemons(List<Pokemons> pokemons) {
        ApplicationPM.pokemons = pokemons;
    }
}
