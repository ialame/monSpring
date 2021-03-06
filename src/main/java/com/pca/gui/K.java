package com.pca.gui;

import java.awt.*;

public class K {
    public final static int STATUS_CARTE_JAPPUR_DANSBASE = (5*2+0)*2+1;
    public final static int STATUS_CARTE_JAPPUR_NON_DANSBASE = (4*2+0)*2+1;
    public final static int STATUS_CARTE_USPUR_DANSBASE = (10*2+0)*2+1;
    public final static int STATUS_CARTE_USPUR_NON_DANSBASE = (8*2+0)*2+1;
    public final static int STATUS_CARTE_USJAP_SANS_US_DANSBASE = (13*2+0)*2+1;
    public final static int STATUS_CARTE_USJAP_SANS_JAP_DANSBASE = (14*2+0)*2+1;
    public final static int STATUS_CARTE_USJAP_DANSBASE = (15*2+0)*2+1;
    public final static int STATUS_CARTE_USJAP_NON_DANSBASE = (12*2+0)*2+1;
    public final static int STATUS_CARTE_USJAP_DANSBASE_SEPAREMENT = (15*2+1)*2+1;
    public final static int STATUS_CARTE_JAP_SANSPAGE2_DANSBASE = (5*2+0)*2+0;
    public final static int STATUS_CARTE_JAP_SANSPAGE2_NON_DANSBASE = (4*2+0)*2+0;
    public final static int STATUS_CARTE_PAGE_DOES_NOT_EXIST = 200;
    //public final static int  STATUS_CARTE_USJAP_JAP_NON_DANSBASE = 9;
    //public final static int  STATUS_CARTE_USJAP_US_NON_DANSBASE = 10;

    //public final static int  STATUS_CARTE_UNNUMBERED_DANSBASE = 9;
    //public final static int  STATUS_CARTE_UNNUMBERED_NON_DANSBASE = 10;
    public final static int EXTENSIONUS = 0;
    public final static int EXTENSIONJAP = 1;
    public final static int EXPANSIONBULBAPEDIA = 2;
    public final static int EXTENSIONSOURCE = 3;

    public final static int STATUS_CARTE_RESSEMBLANT_A_CARTE_EXISTANTE = 0;
    public final static int ITALIE = 0;
    public final static int ALLEMAGNE = 1;
    public final static int ESPAGNE = 2;
    public final static int PORTUGAL = 3;
    public final static int JAPON = 10;
    public final static int MAGIC = 20;
    public final static int STATUS_MAGIC_SPLIT = 21;
    public final static int STATUS_MAGIC_MELD = 22;
    public final static int STATUS_MAGIC_FLIP = 23;
    public final static int STATUS_MAGIC_PLANAR_SCHEME_VANGUARD = 24;
    public final static int STATUS_MAGIC_TRANSFORM = 25;
    public final static int STATUS_MAGIC_OVERSIZED = 26;
    public final static int STATUS_MAGIC_COLLECTORS_EDITION = 27;
    public final static int STATUS_POK_US_EXISTE_JAP_EXISTE = 31;
    public final static int STATUS_POK_US_EXISTE_JAP_NEXISTEPAS = 32;
    public final static int STATUS_POK_US_NOUVELLE_JAP_EXISTE = 33;
    public final static int STATUS_POK_US_NOUVELLE_JAP_NEXISTEPAS = 34;
    public final static Color[] couleur = {
            new Color(100, 30, 22  ),
            new Color(231, 76, 60  ),
            new Color(136, 78, 160),
            new Color(91, 44, 111),
            new Color(26, 82, 118),
            new Color(20, 143, 119),
            new Color(20, 90, 50),
            new Color(241, 196, 15),
            new Color(208, 211, 212),
            new Color(243, 156, 18),
            new Color(255, 0, 255),
            new Color(39, 174, 96),
            new Color(28, 40, 51  )
    };
    public final static Color[] couleur0 = {Color.black,Color.blue,Color.cyan,Color.darkGray,Color.gray,Color.green,Color.lightGray,
                                            Color.magenta,Color.orange,Color.pink,Color.red,Color.white,Color.yellow};
    public final static Color[] couleurInverse ={Color.yellow,Color.white,Color.red,Color.pink,Color.orange,Color.magenta,Color.lightGray,
            Color.green,Color.gray,Color.darkGray,Color.cyan,Color.blue,Color.black};
    public final static Color[] couleur2 = {Color.GREEN, Color.RED, Color.MAGENTA,
            Color.PINK, Color.YELLOW, Color.ORANGE, Color.CYAN, new Color(200, 200, 100),
            Color.WHITE, Color.lightGray,
            new Color(135, 249, 170), new Color(135, 211, 249), new Color(246, 198, 252 )};


    public final static String[] elements = {
            "Toutes les cartes",
            "(1) JAP Pure déjà en base chez PCA",
            "(2) JAP Pure qui n'est pas en base chez PCA",
            "(3) US Pure déjà en base chez PCA",
            "(4) US Pure qui n'est pas en base chez PCA",
            "(5) US JAP sur Bulbapedia sans US chez PCA",
            "(6) US JAP sur Bulbapedia sans JAP chez PCA",
            "(7) US JAP déjà en base chez PCA",
            "(8) US JAP sur Bulbapedia sans US ni JAP chez PCA",
            "(9) US JAP sur Bulbapedia enregistrées séparement chez PCA",
            "(10) JAP sans page 2 déjà en base chez PCA",
            "(11) JAP sans page 2 qui n'est pas en base chez PCA",
            "(12) JAP sans page 2 (page does not exist)"
    };
    public final static String[] statusPOK = {
            "Toutes les cartes",
            "(1) Cartes existantes avec partie jap existante",
            "(2) Cartes existantes avec partie jap non existante",
            "(3) Cartes nouvelles avec partie jap existante",
            "(4) Cartes nouvelles avec partie jap non existante",
    };
    public final static String[] statusMagic = {
            "Toutes les cartes",
            "(1) Split",
            "(2) Meld",
            "(3) Flip",
            "(4) Planar, Scheme, Vanguard",
            "(5) Transform",
            "(6) Over Sized",
            "(7) Collectors Edition"
    };
    public final static int couleurSimple(int c){
        switch (c){
            case STATUS_CARTE_JAPPUR_DANSBASE:
                return 1;
            case STATUS_CARTE_JAPPUR_NON_DANSBASE:
                return 2;
            case STATUS_CARTE_USPUR_DANSBASE:
                return 3;
            case STATUS_CARTE_USPUR_NON_DANSBASE:
                return 4;
            case STATUS_CARTE_USJAP_SANS_US_DANSBASE:
                return 5;
            case STATUS_CARTE_USJAP_SANS_JAP_DANSBASE:
                return 6;
            case STATUS_CARTE_USJAP_DANSBASE:
                return 7;
            case STATUS_CARTE_USJAP_NON_DANSBASE:
                return 8;
            case STATUS_CARTE_USJAP_DANSBASE_SEPAREMENT:
                return 9;
            case STATUS_CARTE_JAP_SANSPAGE2_DANSBASE:
                return 10;
            case STATUS_CARTE_JAP_SANSPAGE2_NON_DANSBASE:
                return 11;
            case STATUS_CARTE_PAGE_DOES_NOT_EXIST:
                return 12;
            default:
                return 0;

        }
    }

}
