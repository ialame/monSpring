package com.ia.ebay9.entity;

import com.ia.ebay9.gui.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by fxc on 19/10/2017.
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name="discriminator",
        discriminatorType=DiscriminatorType.STRING
)
@Table( name="CarteVendue" )
public class CarteVendue  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer id;

    @ManyToOne
    @JoinColumn(name="carteEbay_id", nullable=true)
    public CarteEbay carteEbay;

    public String titre;
    public double prix;
    public long prixFinal;
    public int coeffPrix;
    public String vente;
    public String pays = null;
    public String qualiteParse;
    public int qualiteNum;
    public int indice;
    //public String urlDescription;
    //public String etatDescription;
    public boolean  actif;
    public String img;
    public String pca;
    public String psa;
    public String Quantity;
    public String langue;
    public String professionalGrader;
    public String grade;
    public String EtatDescription;
    //public String QualiteDescription;
    //public String Caracteristiques;
    //public String Description2;
    //public String CommentaireVendeur;
    //public String Commentaire;
    //public static String baseDDCarteVendue;
    //public String CardType;
    //public String Set;
    //public String Grade;
    //public String Features;
    //public String UPC;
    //public String ISBN;
    //public String Langue;
    public String sellerNotes; //
    //public String ItemLocation;
    //public String version;
    //public Vendeur vendeur;
    //public char monnaie = '\0';
    //public boolean existe = true;
    public LocalDateTime date;
    public short edition = 2;
    public short reverse = 0;
    //public String petitTitreSansNom="";
    ////////////////////////////////////////////////////////////////////////////////////////////////
    CarteVendue(){

    }
    CarteVendue(CarteEbay carteEbay){
        this.carteEbay=carteEbay;

    }
    protected void setPrix(String Prix) {
        StringBuffer sb = new StringBuffer();
        for (int k = 0; k < Prix.length(); k++) {
            char c = Prix.charAt(k);
            if (('0' <= c && c <= '9') || c == '.') sb.append(c);
            else if (Prix.indexOf("EUR") >= 0 && c == ',') sb.append('.');
        }
        if (sb.length() > 0)
            this.prix = Double.parseDouble(sb.toString());

        //char monnaie='\0';

        /*if (Prix.indexOf('$') >= 0) {
            this.monnaie = '$';
        } else if (Prix.indexOf("EUR") >= 0) {
            this.monnaie = '€';
        }*/

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setDate0(String LaDate) {// en argument String fileBDD,

        LocalDateTime dtTable = LocalDateTime.now();//.parse(dtFileBDD);
        LocalDateTime dtVente;

        LaDate = LaDate.replace(" ", "T");
        char c = LaDate.charAt(0);

        if ('0' <= c && c <= '9') {
            LaDate = LaDate.replace("janv.", "01").replace("févr.", "02").replace("mars", "03").replace("avr.", "04").replace("mai", "05").replace("juin", "06");
            LaDate = LaDate.replace("juil.", "07").replace("août", "08").replace("sept.", "09").replace("oct.", "10").replace("nov.", "11").replace("déc.", "12");
            String cc1 = LaDate.substring(0, 2), cc2 = LaDate.substring(3, 5);
            LaDate = "2000-" + cc2 + "-" + cc1 + LaDate.substring(5);
        } else {
            LaDate = LaDate.replace("Jan", "01").replace("Feb", "02").replace("Mar", "03").replace("Apr", "04").replace("May", "05").replace("Jun", "06");
            LaDate = LaDate.replace("Jul", "07").replace("Aug", "08").replace("Sep", "09").replace("Oct", "10").replace("Nov", "11").replace("Dec", "12");
            LaDate = "2000-" + LaDate;

        }

        dtVente = LocalDateTime.parse(LaDate);
        int moisTable = dtTable.getMonthValue();
        int moisVente = dtVente.getMonthValue();
        int anneeTable = dtTable.getYear();
        int anneeVente = anneeTable;
        if (moisTable < moisVente) anneeVente--;
        dtVente = dtVente.withYear(anneeVente);
        this.date = dtVente;

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    protected void setPays(String location) {
        String s = "";
        int pos = location.toUpperCase().indexOf("FROM");
        if (pos > -1)
            s = location.substring(pos + 5);
        this.pays = s.trim();

    }


    public void setLangue() {
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        // langues
        String langue = "";
        if (pays.indexOf("France")!=-1) langue = "Français";
        else langue = "Anglais";
        String nomEbay = this.carteEbay.getNomEBAY();
        String site = (this.carteEbay.pointCOM) ? ".com" : ".fr";
        String leTXT = Tools.removeCaracSpecial(Tools.removeAccent(titre)).toLowerCase();
        String leTXTavecEspace = Tools.removeCaracSpecialSaufEspace(Tools.removeAccent(titre)).toLowerCase();
        //String TXT=remplaceCaracSpecialParBlanc(txt.toLowerCase().replace(nomEbay.toLowerCase(), ""));

        String TXT0 = titre.toLowerCase().replace(nomEbay.toLowerCase(), "");
        String numeroSur = ((CartePokemon) carteEbay.getCarte()).Recherche;
        if (numeroSur != null)
            TXT0 = TXT0.replace(numeroSur.toLowerCase(), "");
        String TXT = Tools.removeCaracSpecial(TXT0);
        String TXTavecEspace = Tools.removeCaracSpecialSaufEspace(TXT0);

        //System.out.println("Je suis là 3");

        if (TXT.indexOf(" jap ") != -1 || TXTavecEspace.indexOf(" jap") == TXTavecEspace.length() - 5 || TXT.indexOf("japanese") != -1 || TXT.indexOf("giapponese") != -1 || TXT.indexOf("japanisch") != -1 || TXT.indexOf("japones") != -1)
            langue = "Japonais";
        else if (TXT.indexOf(" ita ") != -1 || TXT.indexOf("italien") != -1 || TXT.indexOf("italian") != -1 || TXT.indexOf("italiano") != -1 || TXT.indexOf("italianisch") != -1)
            langue = "Italien";
        else if (TXTavecEspace.indexOf(" ger ") != -1 || TXT.indexOf("german") != -1 || TXT.indexOf("allemand") != -1 || TXT.indexOf("tedesco") != -1 || TXT.indexOf("aleman") != -1)
            langue = "Allemand";
        else if (TXTavecEspace.indexOf(" us ") != -1 || TXTavecEspace.indexOf(" vo ") != -1 || TXTavecEspace.indexOf(" vo/") != -1 || TXTavecEspace.indexOf(" hp ") != -1 || TXTavecEspace.indexOf(" eng ") != -1 || TXTavecEspace.indexOf(" ing ") != -1 || TXT.indexOf("inglese") != -1 || TXT.indexOf("english") != -1 || TXT.indexOf("anglais") != -1 || TXT.indexOf("englisch") != -1)
            langue = "Anglais";//
        else if (TXT.indexOf("spanish") != -1 || TXT.indexOf("espagnol") != -1 || TXT.indexOf("espanol") != -1 || TXT.indexOf("spagnolo") != -1)
            langue = "Espagnol";
        else if (TXT.indexOf("korean") != -1 || TXT.indexOf("coreano") != -1 || TXT.indexOf("koreanisch") != -1)
            langue = "Coréen";
        else if (TXTavecEspace.indexOf(" fr") == TXTavecEspace.length() - 4 || TXTavecEspace.indexOf(" fr ") != -1 || TXTavecEspace.indexOf(" vf") == TXTavecEspace.length() - 4 || TXTavecEspace.indexOf(" vf ") != -1 || TXT.indexOf("francais") != -1 || TXT.indexOf("français") != -1 || TXT.indexOf("french") != -1 || TXT.indexOf("franzosisch") != -1 || TXT.indexOf("francese") != -1)
            langue = "Français";

        this.langue = langue;

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setDate(String LaDate) {// en argument String fileBDD,

        LocalDateTime dtTable = LocalDateTime.now();//.parse(dtFileBDD);
        LocalDateTime dtVente;

        LaDate = LaDate.replace("janv.", "01").replace("févr.", "02").replace("mars", "03").replace("avr.", "04").replace("mai", "05").replace("juin", "06");
        LaDate = LaDate.replace("juil.", "07").replace("août", "08").replace("sept.", "09").replace("oct.", "10").replace("nov.", "11").replace("déc.", "12");
        LaDate = LaDate.replace("Jan", "01").replace("Feb", "02").replace("Mar", "03").replace("Apr", "04").replace("May", "05").replace("Jun", "06");
        LaDate = LaDate.replace("Jul", "07").replace("Aug", "08").replace("Sep", "09").replace("Oct", "10").replace("Nov", "11").replace("Dec", "12");
        int pos = LaDate.indexOf(" ");
        String d = LaDate.substring(0,pos).trim();
        if(d.indexOf("-")==d.length()-2)
            d=d.replace("-","-0");
        String h = LaDate.substring(pos+1).trim();
        LaDate = "2000-" + d+"T"+h;

        //if (LaDate.equals("2000-05-6T18:37"))
        //    LaDate = LaDate;
        dtVente = LocalDateTime.parse(LaDate);
        int moisTable = dtTable.getMonthValue();
        int moisVente = dtVente.getMonthValue();
        int anneeTable = dtTable.getYear();
        int anneeVente = anneeTable;
        if (moisTable < moisVente) anneeVente--;
        dtVente = dtVente.withYear(anneeVente);
        this.date = dtVente;

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    protected void setVente(String vente) {
        if (vente.indexOf("Offre directe") != -1) vente = "Achat immédiat";
        if (vente.toLowerCase().indexOf("ench") != -1) vente = "Enchère";

        if (vente.indexOf("Buy It Now") != -1 || vente.indexOf("Best offer accepted") != -1) vente = "Achat immédiat";
        if (vente.indexOf("bid") != -1) vente = "Enchère";
        //-------------------------------------------------------------------------------------------------------
        if (vente.indexOf("Achat immédiat") != -1) vente = "Immédiat";
        this.vente = vente;
    }


    protected void save2() {
        saveDans("nullToto");
    }

    public static void creerBDD() {
        /*
        creerTable(baseDDCarteVendue+"SansNom");
        creerTable(baseDDCarteVendue+"Noms2");
        creerTable(baseDDCarteVendue+"Numeros2");
        creerTable(baseDDCarteVendue+"SansNumero");
        creerTable(baseDDCarteVendue+"Multiple");
        creerTable(baseDDCarteVendue+"Toto");
        creerTable(baseDDCarteVendue);
*/
    }
    protected void saveDans(String table) {
        /*if (this.pays == null)
            this.pays = (carteEbay.pointCOM) ? "United States" : "France";
        try {
            Connection connect = ApplicationPM.DB.getConnectLOC();

            String sql = "insert into " + table + "(idCarteEbay,titre,prix,prixFinal,vente,date,pays,img,langue,grade,professionalGrader,edition,reverse,qualiteParse,qualiteNum,etatDescription,sellerNotes)  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connect.prepareStatement(sql);

            preparedStatement.setLong(1, carteEbay.id);
            preparedStatement.setString(2, this.titre);
            preparedStatement.setDouble(3, this.prix);
            preparedStatement.setLong(4, this.prixFinal);
            preparedStatement.setString(5, this.vente);
            preparedStatement.setTimestamp(6, new Timestamp(this.date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));

            preparedStatement.setString(7, this.pays);
            preparedStatement.setString(8, this.img);
            preparedStatement.setString(9, this.langue);
            preparedStatement.setString(10, this.grade);
            preparedStatement.setString(11, this.professionalGrader);
            preparedStatement.setInt(12, this.edition);
            preparedStatement.setInt(13, this.reverse);
            preparedStatement.setString(14, this.qualiteParse);
            preparedStatement.setInt(15, this.qualiteNum);
            preparedStatement.setString(16, this.etatDescription);
            preparedStatement.setString(17, this.sellerNotes);
            preparedStatement.executeUpdate();
            //}
            //statement.close();

        } catch (Exception e) {
            System.out.println("probleme bdd ..."+e);

        }*/
    }


    public void setIndice(int indice) {
        this.indice = indice;
    }

    public String toString() {
        return "Carte :" + indice + " prix :" + prix + "monnaie" + " " + date + " ( " + carteEbay + ")";
    }


    public void setPays() {
        if (this.pays == null)
            this.pays = null;//(carteEbay.pointCOM) ? "United States" : "France";
    }


    public void setPrixFinal() {
        Random random = new Random();
        char monnaie = '\0';
        if (prix < 10) {
            prixFinal =(int) Math.ceil(prix);
        } else if (prix < 35) {
            prixFinal = Math.round(prix + random.nextDouble() * 2);
        } else {
            prixFinal = Math.round(prix / 5 + 0.5) * 5;
        }
    }

    public boolean titreContientDeuxNumeros() {
        int i;
        String g="",d="";
        String t=titre.toLowerCase().replaceAll(" ","");
        t=t.replace(((CartePokemon) carteEbay.getCarte()).Recherche.toLowerCase(),"");

        for (int c = 0; c < t.length(); c++) {
            if (t.charAt(c) == '/') {
                i = c + 1;
                while (0 <= i && i < t.length()) {
                    char cp1 = t.charAt(i);
                    if (0 <= cp1 - '0' && cp1 - '9' <= 0)
                        d = d + (cp1 - '0');
                    else
                        break;
                    i++;
                }

                i = c - 1;
                while (0 <= i && i < t.length()) {
                    char cm1 = t.charAt(i);
                    if (0 <= cm1 - '0' && cm1 - '9' <= 0)
                        g = (cm1 - '0') + g;
                    else
                        break;
                    i--;
                }
            }
        }
        return g.length()>0 && d.length()>0;
    }

    public int getMultiple() {
        int i ,n=0;
        String s="";
        String t=titre.toLowerCase();
        t=t.replace(((CartePokemon) carteEbay.getCarte()).Recherche.toLowerCase(),"");
        t=t.replace(((CartePokemon) carteEbay.getCarte()).getNomAnglaisZEBRA().toLowerCase(),"");
        //t=t.replace(carteEbay.carte.extension.nomFR.toLowerCase(),"");
        for (int c = 0; c < t.length(); c++) {
            if (t.charAt(c) == 'x') {
                i=c+1;
                while(0<=i && i < t.length()){
                    char cp1 = t.charAt(i);
                    while (cp1==' ' && (0<=i && i < t.length()-1)){
                        i++;
                        cp1 = t.charAt(i);
                    }
                    if (0 <= cp1 - '0' && cp1 - '9' <= 0)
                        s=s+(cp1 - '0');
                    else
                        break;
                    i++;
                }
                if(!s.equals(""))
                    return Integer.parseInt(s);
                i=c-1;
                s="";
                while(0<=i && i < t.length()){
                    char cm1 = t.charAt(i);
                    while (cm1==' '  && (0<i && i < t.length())){
                        i--;
                        cm1 = t.charAt(i);
                    }
                    if (0 <= cm1 - '0' && cm1 - '9' <= 0)
                        s=(cm1 - '0')+s;
                    else
                        break;
                    i--;
                }
                if(s.equals(""))
                    return 1;
                else
                    return Integer.parseInt(s);
            }
        }
        return n==0?1:n;

    }

    public void setProfessionalGrader() {
        grade=getGrad("pca");
        if(grade!=null){
            professionalGrader="PCA";
        }else{
            grade=getGrad("psa");
            if(grade!=null){
                professionalGrader="PSA";
            }
        }
    }

    public String getGrad(String motif) {
        String strPCA = "";
        String grad="";
        ///////////////////// attention TXT0
        String leTXT = Tools.removeCaracSpecial(Tools.removeAccent(titre)).toLowerCase();
        String leTXTavecEspace = Tools.removeCaracSpecialSaufEspace(Tools.removeAccent(titre)).toLowerCase();
        //String TXT=remplaceCaracSpecialParBlanc(txt.toLowerCase().replace(nomEbay.toLowerCase(), ""));
        String nomEbay = carteEbay.nomEBAY;
        String TXT0 = titre.toLowerCase().replace(nomEbay.toLowerCase(), "");
        String numeroSur = ((CartePokemon) carteEbay.getCarte()).Recherche;
        if (numeroSur != null)
            TXT0 = TXT0.replace(numeroSur.toLowerCase(), "");
        String TXT = Tools.removeCaracSpecial(TXT0);
        String TXTavecEspace = Tools.removeCaracSpecialSaufEspace(TXT0);

        ////////////////////////////

        int indexPCA = TXT0.indexOf(motif);// TXT0 = TXT sans le numéro
        if (indexPCA != -1) {
            strPCA = TXT0.substring(indexPCA + motif.length()).trim();
            for (int z = 0; z < 3 && z < strPCA.length(); z++) {
                if ((strPCA.charAt(z) - '0' >= 0 && strPCA.charAt(z) - '9' <= 0) || strPCA.charAt(z) == '.' || strPCA.charAt(z) == ',' || strPCA.charAt(z) == '+')
                    grad += strPCA.charAt(z);

            }
            if (grad.length() == 0 && indexPCA > 3) {
                strPCA = TXT0.substring(indexPCA - 3, indexPCA).trim();
                for (int z = 0; z < 3 && z < strPCA.length(); z++) {
                    if ((strPCA.charAt(z) - '0' >= 0 && strPCA.charAt(z) - '9' <= 0) || strPCA.charAt(z) == '.')
                        grad += strPCA.charAt(z);
                }

            }
        }
        return grad.equals("")?null:grad;
    }

    public boolean manqueLeNumero(){
        boolean b=false;
        String s=((CartePokemon) carteEbay.getCarte()).Recherche;
        String recherche = s;
        if(recherche.indexOf("/")==-1) {
            int c = 0;
            while ( c < recherche.length() && !(0 <= recherche.charAt(c) - '0' && recherche.charAt(c) - '9' <= 0) ) c++;
            if(c < recherche.length()) s=recherche.substring(0,c)+"/"+recherche.substring(c);
        }

        String[] tNumSur =s.toLowerCase().split("/");
        int i=0;
        String titre1 = Tools.removeAccent(titre.toLowerCase().replace("'", ""));
        while(i<tNumSur.length && titre1.indexOf(tNumSur[i])!=-1)i++;
        if(i<tNumSur.length) {

            b=true;
        }
        return b;
    }


    public void setEdition() {
        String leTXT = Tools.removeCaracSpecial(Tools.removeAccent(titre)).toLowerCase();
        ///////////////////////  édition et reverse
        edition = 2;
        // A T T E N T I O N  UUID
        /*if (carteEbay.getCarte().getId() <= 1050) {
            if (leTXT.indexOf("ed1") != -1 || leTXT.indexOf("1ed") != -1 || leTXT.indexOf("edition1") != -1 || leTXT.indexOf("1edition1") != -1 || leTXT.indexOf("1ereedition") != -1 || leTXT.indexOf("1eredition") != -1 || leTXT.indexOf("editionere1") != -1 || leTXT.indexOf("first") != -1 || leTXT.indexOf("1st") != -1)
                edition = 1;
        }*/
    }
    public void setReverse() {
        String leTXTavecEspace = Tools.removeCaracSpecialSaufEspace(Tools.removeAccent(titre)).toLowerCase();
        ///////////////////////  édition et reverse
        reverse = 0;
        if (leTXTavecEspace.indexOf(" reverse") != -1 || leTXTavecEspace.indexOf(" rev ") != -1 || leTXTavecEspace.indexOf("holo inverse") != -1 || leTXTavecEspace.indexOf("holo inv") != -1)
            reverse = 1;//edition="reverse";

    }
    public void setQualityNum() {
        switch (carteEbay.qualite) {  //
            case "Comme neuf":
                qualiteNum = 2; //
                break;
            case "Endommagé":
                qualiteNum = 6; //
                break;
            case "Moyennement usé":
                qualiteNum = 5; //
                break;
            case "Peu usé":
                qualiteNum = 4; //
                break;
            case "Très usé":
                qualiteNum = 7; //
                break;
            default:
                qualiteNum = 8; //
                break;
        }

        boolean q = qualiteNum();

        if (!q) {
            qualiteParse = "-";
            String qualite = this.carteEbay.qualite;
            if (qualite.equals("Non spécifié") || qualite.equals("Non spécifiée") || qualite.equals("Not specified"))
                switch (this.carteEbay.etat) {
                    case "New":
                    case "Neuf":
                        /*if (this.carteEbay.carte.extension.id < 60)
                            qualiteNum = 2;
                        else
                            qualiteNum = 1;
                        break;*/
                    case "Occasion":
                    case "Used":
                        /*if (this.carteEbay.carte.extension.id < 60)
                            qualiteNum = 5;
                        else
                            qualiteNum = 4;
                        break;*/
                    default:
                        qualiteNum = 8;
                }
        }


    }

    public boolean qualiteNum() {
        boolean q = true;
        String leTXT = Tools.removeCaracSpecial(Tools.removeAccent(titre)).toLowerCase();
        String leTXTavecEspace = Tools.removeCaracSpecialSaufEspace(Tools.removeAccent(titre)).toLowerCase();
        if (leTXT.indexOf("mauvaisetat") != -1 || leTXT.indexOf("etatmauvais") != -1 || leTXT.indexOf("mauvaiseetat") != -1 || leTXT.indexOf("etatmauvaise") != -1) {
            qualiteParse = "Mauvais état";
            qualiteNum = 7;
        } else if (leTXT.indexOf("etatcorrect") != -1 || leTXT.indexOf("moyen") != -1) {
            qualiteParse = "Etat correct";
            qualiteNum = 6;
        } else if (leTXT.indexOf("occas") != -1) {
            qualiteParse = "Occasion";
            qualiteNum = 5;
        } else if (leTXT.indexOf("tresbonetat") != -1 || leTXT.indexOf("tresbonneetat") != -1 || leTXTavecEspace.indexOf(" tbe") != -1) {
            qualiteParse = "Très bon état";
            qualiteNum = 4;
        } else if (leTXT.indexOf("bonetat") != -1 || leTXT.indexOf("etatbon") != -1) {
            qualiteParse = "Bon état";
            qualiteNum = 5;
        } else if (leTXT.indexOf("excellentetat") != -1) {
            qualiteParse = "Excellent état";
            qualiteNum = 3;
        } else if (leTXT.indexOf("procheduneuf") != -1
                || leTXT.indexOf("quasineuf") != -1
                || leTXT.indexOf("cneuf") != -1 || leTXT.indexOf("cneuve") != -1 || leTXT.indexOf("commeneuf") != -1 || leTXT.indexOf("commeneuve") != -1
                || leTXTavecEspace.indexOf("/nm") != -1 || leTXTavecEspace.indexOf(" nm") != -1
                ) {
            qualiteParse = "Proche du neuf";
            qualiteNum = 2;
        } else if (leTXT.indexOf("neuf") != -1 || leTXT.indexOf("neuve") != -1) {
            qualiteParse = "Neuf";
            qualiteNum = 1;
        } else if (leTXT.indexOf("parfaitétat") != -1) {
            qualiteParse = "Parfait état";
            qualiteNum = 3;
        } else if (leTXT.indexOf("gemmint") != -1 || leTXT.indexOf("gmmt") != -1 || leTXT.indexOf("gm-mt") != -1) {
            qualiteParse = "Gem mint";
            qualiteNum = 1;
        } else if (leTXT.indexOf("excellent") != -1 || leTXT.indexOf("exellent") != -1 || leTXT.indexOf("exelent") != -1) {
            qualiteParse = "Excellent";
            qualiteNum = 3;
        } else if (leTXT.indexOf("verrygood") != -1) {
            qualiteParse = "Verry good";
            qualiteNum = 4;
        } else if (leTXT.indexOf("Good") != -1) {
            qualiteParse = "Good";
            qualiteNum = 5;
        } else if (leTXT.indexOf("played") != -1) {
            qualiteParse = "Played";
            qualiteNum = 5;
        } else if (leTXT.indexOf("poor") != -1) {
            qualiteParse = "Poor";
            qualiteNum = 7;
        } else if (leTXT.indexOf("nm/mint") != -1 || leTXT.indexOf("nm/mt") != -1 || leTXT.indexOf("mt/nm") != -1 || leTXT.indexOf("nm/m") != -1 || leTXT.indexOf("m/nm") != -1 ||
                leTXT.indexOf("mint/nm") != -1 || leTXT.indexOf("mint-nm") != -1 || leTXT.indexOf("nm-mint") != -1 || leTXT.indexOf("nearmintmint") != -1 || leTXT.indexOf("mintnearmint") != -1 ||
                leTXT.indexOf("mint/nearmint") != -1 || leTXT.indexOf("nearmint/mint") != -1) {
            qualiteParse = "Near mint/Mint";
            qualiteNum = 2;
        } else if (leTXT.indexOf("nmmint") != -1 || leTXT.indexOf("nearmint") != -1 || leTXTavecEspace.indexOf(" nm") != -1) {
            qualiteParse = "Near mint";
            qualiteNum = 2;
        } else if (leTXT.indexOf("mint") != -1) {
            qualiteParse = "Mint";
            qualiteNum = 1;
        } else if (leTXT.indexOf("new") != -1) {
            qualiteParse = "Neuf";
            qualiteNum = 1;
        } else q = false;
        return q;
    }

    public boolean qualiteNum2(String titre) {
        boolean q = false;
        String leTXT = Tools.removeCaracSpecial(Tools.removeAccent(titre)).toLowerCase();
        String leTXTavecEspace = Tools.removeCaracSpecialSaufEspace(Tools.removeAccent(titre)).toLowerCase();
        String qP = "";
        int qN = 0, qK = 0;
        if (leTXT.indexOf("mauvaisetat") != -1 || leTXT.indexOf("etatmauvais") != -1 || leTXT.indexOf("mauvaiseetat") != -1 || leTXT.indexOf("etatmauvaise") != -1) {
            qK = 1;
            qualiteParse = "Mauvais état";
            qualiteNum = 7;
            q = true;

        }
        if (leTXT.indexOf("etatcorrect") != -1 || leTXT.indexOf("moyen") != -1) {
            if (qK == 1) {
                qualiteParse = "";
                qualiteNum = 0;
                return false;
            }
            qK = 1;
            qualiteParse = "Etat correct";
            qualiteNum = 6;
            q = true;
        }

        if (leTXT.indexOf("occas") != -1) {
            if (qK == 1) {
                qualiteParse = "";
                qualiteNum = 0;
                return false;
            }
            qK = 1;
            qualiteParse = "Occasion";
            qualiteNum = 5;
            q = true;
        }

        if (leTXT.indexOf("tresbonetat") != -1 || leTXT.indexOf("tresbonneetat") != -1 || leTXTavecEspace.indexOf(" tbe") != -1) {
            if (qK == 1) {
                qualiteParse = "";
                qualiteNum = 0;
                return false;
            }
            qK = 1;
            qualiteParse = "Très bon état";
            qualiteNum = 4;
            q = true;
        }
        if (leTXT.indexOf("bonetat") != -1 || leTXT.indexOf("etatbon") != -1) {
            if (qK == 1) {
                qualiteParse = "";
                qualiteNum = 0;
                return false;
            }
            qK = 1;
            qualiteParse = "Bon état";
            qualiteNum = 5;
            q = true;
        }
        if (leTXT.indexOf("excellentetat") != -1) {
            if (qK == 1) {
                qualiteParse = "";
                qualiteNum = 0;
                return false;
            }
            qK = 1;
            qualiteParse = "Excellent état";
            qualiteNum = 3;
            q = true;
        }

        if (leTXT.indexOf("procheduneuf") != -1
                || leTXT.indexOf("quasineuf") != -1
                || leTXT.indexOf("cneuf") != -1 || leTXT.indexOf("cneuve") != -1 || leTXT.indexOf("commeneuf") != -1 || leTXT.indexOf("commeneuve") != -1
                || leTXTavecEspace.indexOf("/nm") != -1 || leTXTavecEspace.indexOf(" nm") != -1
                ) {
            if (qK == 1) {
                qualiteParse = "";
                qualiteNum = 0;
                return false;
            }
            qK = 1;
            qualiteParse = "Proche du neuf";
            qualiteNum = 2;
            q = true;
        }

        if (leTXT.indexOf("neuf") != -1 || leTXT.indexOf("neuve") != -1) {
            if (qK == 1) {
                qualiteParse = "";
                qualiteNum = 0;
                return false;
            }
            qK = 1;
            qualiteParse = "Neuf";
            qualiteNum = 1;
            q = true;
        }

        if (leTXT.indexOf("parfaitétat") != -1) {
            if (qK == 1) {
                qualiteParse = "";
                qualiteNum = 0;
                return false;
            }
            qK = 1;
            qualiteParse = "Parfait état";
            qualiteNum = 3;
            q = true;
        }

        if (leTXT.indexOf("gemmint") != -1 || leTXT.indexOf("gmmt") != -1 || leTXT.indexOf("gm-mt") != -1) {
            if (qK == 1) {
                qualiteParse = "";
                qualiteNum = 0;
                return false;
            }
            qK = 1;
            qualiteParse = "Gem mint";
            qualiteNum = 1;
            q = true;
        }

        if (leTXT.indexOf("excellent") != -1 || leTXT.indexOf("exellent") != -1 || leTXT.indexOf("exelent") != -1) {
            if (qK == 1) {
                qualiteParse = "";
                qualiteNum = 0;
                return false;
            }
            qK = 1;
            qualiteParse = "Excellent";
            qualiteNum = 3;
            q = true;
        }

        if (leTXT.indexOf("verrygood") != -1) {
            if (qK == 1) {
                qualiteParse = "";
                qualiteNum = 0;
                return false;
            }
            qK = 1;
            qualiteParse = "Verry good";
            qualiteNum = 4;
            q = true;
        }

        if (leTXT.indexOf("Good") != -1) {
            if (qK == 1) {
                qualiteParse = "";
                qualiteNum = 0;
                return false;
            }
            qK = 1;
            qualiteParse = "Good";
            qualiteNum = 5;
            q = true;
        }
        if (leTXT.indexOf("played") != -1) {
            if (qK == 1) {
                qualiteParse = "";
                qualiteNum = 0;
                return false;
            }
            qK = 1;
            qualiteParse = "Played";
            qualiteNum = 5;
            q = true;
        }

        if (leTXT.indexOf("poor") != -1) {
            if (qK == 1) {
                qualiteParse = "";
                qualiteNum = 0;
                return false;
            }
            qK = 1;
            qualiteParse = "Poor";
            qualiteNum = 7;
            q = true;
        }

        if (leTXT.indexOf("nm/mint") != -1 || leTXT.indexOf("nm/mt") != -1 || leTXT.indexOf("mt/nm") != -1 || leTXT.indexOf("nm/m") != -1 || leTXT.indexOf("m/nm") != -1 ||
                leTXT.indexOf("mint/nm") != -1 || leTXT.indexOf("mint-nm") != -1 || leTXT.indexOf("nm-mint") != -1 || leTXT.indexOf("nearmintmint") != -1 || leTXT.indexOf("mintnearmint") != -1 ||
                leTXT.indexOf("mint/nearmint") != -1 || leTXT.indexOf("nearmint/mint") != -1) {
            if (qK == 1) {
                qualiteParse = "";
                qualiteNum = 0;
                return false;
            }
            qualiteParse = "Near mint/Mint";
            qualiteNum = 2;
            q = true;
        }
        if (leTXT.indexOf("nmmint") != -1 || leTXT.indexOf("nearmint") != -1 || leTXTavecEspace.indexOf(" nm") != -1) {
            if (qK == 1) {
                qualiteParse = "";
                qualiteNum = 0;
                return false;
            }
            qK = 1;
            qualiteParse = "Near mint";
            qualiteNum = 2;
            q = true;
        }

        if (leTXT.indexOf("mint") != -1) {
            if (qK == 1) {
                qualiteParse = "";
                qualiteNum = 0;
                return false;
            }
            qK = 1;
            qualiteParse = "Mint";
            qualiteNum = 1;
            q = true;
        }
        if (leTXT.indexOf("new") != -1) {
            if (qK == 1) {
                qualiteParse = "";
                qualiteNum = 0;
                return false;
            }
            qK = 1;
            qualiteParse = "Neuf";
            qualiteNum = 1;
            q = true;
        }
        return q;
    }

    public boolean titreContientNomDeLaCarte() {
        String petitTitre = Tools.removeAccent(titre.toLowerCase());
        String petitNom = Tools.removeAccent(carteEbay.nomEBAY.toLowerCase());
        //petitTitreSansNom = petitTitre.replace(petitNom,"");
        return petitTitre.indexOf(petitNom) != -1;
    }

    public static void creerTable(String table) {

        /*
        CREATE TABLE c1ajoutCartesJava.CarteVendue (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `idCarteEbay` int(11) DEFAULT NULL,
                        `titre` varchar(200) DEFAULT NULL,
                        `prix` varchar(90) DEFAULT NULL,
                        `prixFinal` int(11) DEFAULT 1,
                        `vente` varchar(45) DEFAULT NULL,
                        `date` varchar(45) DEFAULT NULL,
                        `pays` varchar(45) DEFAULT NULL,
                        `img` varchar(100) DEFAULT NULL,
                        `Quantity` varchar(45) DEFAULT NULL,
                        `langue` varchar(45) DEFAULT NULL,
                        `professionalGrader` varchar(45) DEFAULT NULL,
                        `grade` varchar(45) DEFAULT NULL,
                        `edition` int(1) DEFAULT NULL,
                        `reverse` int(1) DEFAULT NULL,
                        `qualiteParse` varchar(45) DEFAULT NULL,
                        `qualiteNum` int(2) DEFAULT NULL,
                        `etatDescription` varchar(45) DEFAULT NULL,
                        `sellerNotes` varchar(450) DEFAULT NULL,
                        PRIMARY KEY (`id`)
                        ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
         */

        /*try {

            Statement statement = ApplicationPM.DB.getConnectLOC().createStatement();

            DatabaseMetaData dbm = ApplicationPM.DB.getConnectLOC().getMetaData();


            ResultSet tables = dbm.getTables(null, null, table, null);
            if (!tables.next()) {
                String sql = "CREATE TABLE `" + table + "` ( " +
                        "id int NOT NULL AUTO_INCREMENT," +
                        "`idCarteEbay` int(11) DEFAULT NULL," +
                        "`titre` varchar(200) DEFAULT NULL," +
                        "`prix` varchar(90) DEFAULT NULL," +
                        "`prixFinal` int(11) DEFAULT 1," +
                        "`vente` varchar(45) DEFAULT NULL," +
                        "`date` varchar(45) DEFAULT NULL," +
                        "`pays` varchar(45) DEFAULT NULL," +
                        "`img` varchar(100) DEFAULT NULL," +
                        "`Quantity` varchar(45) DEFAULT NULL," +
                        "`langue` varchar(45) DEFAULT NULL," +
                        "`professionalGrader` varchar(45) DEFAULT NULL," +
                        "`grade` varchar(45) DEFAULT NULL," +
                        "`edition` int(1) DEFAULT NULL," +
                        "`reverse` int(1) DEFAULT NULL," +
                        "`qualiteParse` varchar(45) DEFAULT NULL," +
                        "`qualiteNum` int(2) DEFAULT NULL," +
                        "`etatDescription` varchar(45) DEFAULT NULL," +
                        "`sellerNotes` varchar(450) DEFAULT NULL," +
                        "PRIMARY KEY (`id`)" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";

                statement.executeUpdate(sql);//
                //app.remplirLesListes();
            }

        } catch (Exception ex) {
            System.out.println("probleme  ..."+ex);

            ex.printStackTrace();

        }

*/
    }

    public boolean titreContient2Noms(ArrayList<Pokemons> pokemons){
        ArrayList<String> pkm = null;//(this.carteEbay.pointCOM) ? pokemons.us : pokemons.fr;
        boolean b=false;
        for(String s:pkm){
            b=true;//petitTitreSansNom.indexOf(s.toLowerCase())!=-1;
            if(b){
                System.out.println("permier nom: " +"this.carteEbay.nomEBAY" + " deuxième nom: "+s);
                break;
            }
        }

        return b;
    }

    public void save() {// les cartes energy ne sont pas enregistrées pour le moment, à voir ...
        EntityManager entityManager = null;
        try {
            try {
                entityManager = ApplicationPM.entityManager;
                EntityTransaction trans = entityManager.getTransaction();
                trans.begin();

                entityManager.persist(this);

                trans.commit();

            } finally {
                //if ( entityManager != null ) entityManager.close();
                //if ( entityManagerFactory != null ) entityManagerFactory.close();
            }
        }catch (Exception e){
            System.out.println("Dans save CarteVendue : " + e);
        }

    }


}

class VendeurException extends Exception{
    public VendeurException(){
        System.out.println("PAS DE VENDEUR !");
    }
}




