package com.pca.gui; /**
 * Created by fxc on 02/01/2017.
 */
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;

import com.pca.entity.*;
import com.pca.repository.CarteEbayRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.swing.*;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

//import java.util.Date;
public class Net2mysql  implements Runnable{
    public ArrayList<SerieUS> seriesUS;// = new ArrayList<SerieUS>();
    public ArrayList<Pokemons>  pokemons;
    public static int M = 0;//99319;//3295;//3196;
    public static int k = 0, kkk = 0;
    int id = 1;
    public JTextArea console;
    public Ebay app;
    public static int maxCarteEbay;
    //public static Date aujourdhui = new Date();
    // Get the current date and time
    LocalDateTime currentTime = LocalDateTime.now();

    public static DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, new Locale("FR", "fr"));
    int a = currentTime.getYear();
    int m = currentTime.getMonthValue();
    int j = currentTime.getDayOfMonth();
    int h = currentTime.getHour();
    int min = currentTime.getMinute();
    int sec = currentTime.getSecond();
    public String baseDD, baseDDCarteEbay, baseDDCarteVendue;
    private String baseDDVendeur;
    public String fileBDD;
    public String fileBDDVendeur;
    int indiceCarte = 0;

    public Net2mysql(Ebay app) {
        this.app =app;
        try {
            System.out.println("bidaya");
            // en France uniquement Ventes réussies
            // Neuf  ______ 1000
            // Occasion _____ 3000
            // non spécifiée  ____ 10
            String http;
            String[] etatTab = {"1000", "3000", "10"};//{"3","4","10"};%25C3%2589tat%2520de%2520la%2520carte=Comme%2520neuf
            String[] qualiteTab = {"Comme%2520neuf", "Endommag%25C3%25A9", "Moyennement%2520us%25C3%25A9", "Peu%2520us%25C3%25A9", "Tres%2520us%25C3%25A9", "%21"};//{"Bonne%252C%2520Good", "Correcte%252C%2520Fine","Excellente","Mauvaise%252C%2520Poor","Tr%25C3%25A8s%2520bonne%252C%2520Near%2520mint","%21"};

            ///////////////////////////////   Chargement de la BDD
            //MySql2Java m = new MySql2Java();

            //    MIGRATION
            seriesUS =(ArrayList<SerieUS>) com.pca.gui.ApplicationPM.getSeriesUS();
            pokemons = (ArrayList<Pokemons>) com.pca.gui.ApplicationPM.getPokemons();


            ////////////////////////////////////////////////////////
            //CarteVendue.creerBDD();


        } catch (Exception e) {
            System.out.println("probleme ici ici  " + e);
        }
    }
    public void setFileBDD(String fileBDD){

        this.fileBDD=fileBDD;

    }
    public String getFileBDD(){

        return fileBDD;
    }
    public void run()  { // extraire
        /*
        app.bNet2sql.setEnabled(false);
        app.bar1.setMaximum(200000);
        app.bar1.setMinimum(0);
        app.bar1.setStringPainted(true);
        ////////////////////////////////////////////////////////030117_14h22Parse
        app.bar1.setValue(1);

         */
        double prix1 = 0, prix2 = 0;
        boolean b=true;
        try {
            // en France uniquement Ventes réussies
            // Neuf  ______ 1000  ==> 3
            // Occasion _____ 3000  ==> 4
            // non spécifiée  ____ 10  ==> 1
            String http;
            String[] etatTab = {"1000", "3000", "10"};//{"3","4","10"};%25C3%2589tat%2520de%2520la%2520carte=Comme%2520neuf
            String[] qualiteTab = {"Comme%2520neuf", "Endommag%25C3%25A9", "Moyennement%2520us%25C3%25A9", "Peu%2520us%25C3%25A9", "Tres%2520us%25C3%25A9", "%21"};//{"Bonne%252C%2520Good", "Correcte%252C%2520Fine","Excellente","Mauvaise%252C%2520Poor","Tr%25C3%25A8s%2520bonne%252C%2520Near%2520mint","%21"};
            //String[] qualiteTab = {"Comme neuf", "Endommagé", "Moyennement usé", "Peu usé", "Tres usé", "%21"};//{"Bonne%252C%2520Good", "Correcte%252C%2520Fine","Excellente","Mauvaise%252C%2520Poor","Tr%25C3%25A8s%2520bonne%252C%2520Near%2520mint","%21"};

            int idMaxCarteEbay = 0;//,maxCarteEbay=201267;
            int idSerieDebut = 0;
            int idExtensionDebut = 0;
            int idCarteDebut = 0;
            int idCarteEbayDebut = 0;
            String sql = "SELECT max(id) as m FROM CarteEbay;";
            //ResultSet resultMax = statementLOC.executeQuery(sql);
            //int maxCarteEbay =0;
            /*
            if (resultMax.next()) {
                maxCarteEbay = resultMax.getInt("m");
            }

             */
            //app.bar1.setMaximum(maxCarteEbay);
            //sql = "SELECT max(idCarteEbay) as m FROM " + CarteVendue.baseDDCarteVendue;
            //resultMax = statementLOC.executeQuery(sql);
            /*
            while (resultMax.next()) {
                idMaxCarteEbay = resultMax.getInt("m");
                if(idMaxCarteEbay==0)continue;
                //CarteEbay cEbay = new CarteEbay(idMaxCarteEbay);
                //idCarteDebut=cEbay.carte_id;
                CartePokemon crt = new CartePokemon();//idCarteDebut);
                /* Extension ext = crt.extension;
                idSerieDebut = ext.serie.id;
                idExtensionDebut = ext.id;                  //  A T T E N S I O N
                idCarteDebut = crt.getId();
                //idCarteEbayDebut = cEbay.id;
            }
        */
            for (SerieUS serie : seriesUS) {
                //if(true) break;
                //if(serie.id!=102)continue;
                //if(serie.id<idSerieDebut)continue;
                System.out.println(serie);
                for (ExtensionUS extension: serie.getExtensionsUS()) {
                    //if(extension.id<99050) continue;
                    //if(serie.id==idSerieDebut && extension.id<idExtensionDebut)continue;
                    System.out.println(extension);
                    for (CartePokemon carte : extension.getCartes()) {
                        //if(extension.id==idExtensionDebut && carte.id<idCarteDebut)continue;
                        System.out.print(carte);
                        if(carte.getNomAnglaisEBAY()==null || carte.getNomFrancaisEBAY()==null)
                            continue;



                        ///////////////////////////////////////////////////////
                        /////////      Par CarteEbay  /////////////////////////
                        ///////////////////////////////////////////////////////
                        for (int pointFR = 0; pointFR < 2; pointFR++) {//pointFR=0 (.com)  pointFR=1 (.fr)
                            if ((!carte.isFR() && pointFR == 1)) continue;// 1=FR
                            if ((!carte.isUS() && pointFR == 0)) continue;// 0=US
                            //maxCarteEbay++;

                            System.out.println(carte);
                            CarteEbay carteEbay0 = new CarteEbay(carte);
                            carteEbay0.setPointFR(pointFR);
                            carteEbay0.setEtatQualite("", "");
                            http = carteEbay0.getURL();
                            Document doc0=null;
                            do {
                                try {
                                    doc0 = carteEbay0.getDoc();  // la chaine de recherche dans google
                                } catch (Exception e) {
                                    System.out.println("Problème internet");
                                }
                            }while(doc0==null);
                            /*
                                int nbCartes0 = carteEbay0.getSize();// Nombre d'exemplaires de carte trouvées
                                if (nbCartes0 == 0){
                                    continue;
                                }
                            */
                            CarteEbayRepository cer = new CarteEbayRepository(ApplicationPM.entityManager);
                            //ExpansionBulbapediaRepository ebr = new ExpansionBulbapediaRepository(ApplicationPM.entityManager);
                            for (String etat : etatTab) {
                                for (String qualite : qualiteTab) {

                                    CarteEbay carteEbay = cer.getCarteEbayByCardEtatQualitePointFR(carte,etat,qualite,pointFR==1);
                                    if (carteEbay==null)
                                        carteEbay = new CarteEbay(carte,etat,qualite,pointFR==1);

/*
                                    app.bar1.setValue(carteEbay.id);
                                    app.bar1.setMaximum(maxCarteEbay);
                                    */

                                    //if(carteEbay.id<idCarteEbayDebut)
                                    //    continue;

                                    //carteEbay.carte=carte;
                                    //carteEbay.setNom();
                                    http = carteEbay.getURL();
                                    //System.out.println(http);

                                    //////////////////////////////////////////////////////////////////////////
                                    //CarteEbay carteEbay1 = getCarteEbayByCardEtatQualite()
                                    if (carteEbay.getId()==null) {
                                        EntityManager entityManager = null;
                                        try {
                                            try {
                                                entityManager = ApplicationPM.entityManager;
                                                EntityTransaction trans = entityManager.getTransaction();
                                                trans.begin();

                                                entityManager.persist(carteEbay);

                                                trans.commit();

                                            } finally {
                                                //if ( entityManager != null ) entityManager.close();
                                                //if ( entityManagerFactory != null ) entityManagerFactory.close();
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Dans save CarteVendue : " + e);
                                        }
                                    }
                                    //////////////////////////////////////////////////////////////////////////



                                    Document doc=null;
                                    do {
                                        try {
                                            doc = carteEbay.getDoc();  // la chaine de recherche dans google
                                        } catch (Exception e) {
                                            System.out.println("Problème internet");
                                        }
                                    }while(doc==null);
                                    int nbCartes = carteEbay.getSize();// Nombre d'exemplaires de carte trouvées
                                    if (nbCartes == 0){
                                        if(pointFR==0)break;
                                        else
                                            continue;
                                    }

                                    // Nombre des pages trouvées
                                    int NbPages = carteEbay.getNbPages();

                                    int page = 0;
                                    int nbCartesVendues=0;
                                    int sumPrix=0;

                                    do { // boucle sur les pages
                                        page++;
                                        //System.out.println("\npage=" + page);
                                        try {
                                            if (page > 1) { // on ne calcule pas la première page
                                                String http2 = http + "&_pgn=" + page;
                                                System.out.println("\nurl=" + http2);
                                                doc = Jsoup.connect(http2).get();
                                            }
                                        } catch (Exception exc) {
                                            System.out.println("Erreur de pagination");
                                        }

                                        Iterator<Element> liste;
                                        liste = doc.select(".srp-results li.s-item").iterator();

                                        //if (!liste.hasNext()) {
                                        //    liste = doc.select("li.s-item").iterator();
                                        //}
                                        ///////////////////////////////////////////////////////
                                        /////////      Boucle CarteVendu  /////////////////////////
                                        ///////////////////////////////////////////////////////
                                        while (liste.hasNext()) {
                                            Element x = liste.next();
                                            if(x.text().length()<75) continue;
                                            indiceCarte++;
                                            if (indiceCarte == nbCartes + 1) break;
                                            k++;
                                            CarteVendue cv = null;

                                            try {
                                                if (pointFR==0)
                                                    cv = new CarteVendueUS(x,carteEbay);
                                                else
                                                    cv = new CarteVendueFR(x,carteEbay);
                                            } catch (Exception e) {
                                                System.out.println("probleme cv= new ..."+e);
                                                break;
                                            }

                                            //if (!cv.existe) break;
                                            if (cv.titre == null) break;
                                            //cv.setPrixFinal();
                                            if(indiceCarte==1)
                                                indiceCarte=indiceCarte;
                                            System.out.println("indiceCarte : "+indiceCarte);
                                            cv.setLangue();/////////////////   ICI ICI ICI ICI
                                            cv.setProfessionalGrader();
                                            cv.setEdition();
                                            cv.setReverse();
                                            System.out.println("(manque le numéro): "+cv);
/*
                                            if(((CartePokemon) cv.carteEbay.carte).isPokemon){
                                                if(cv.manqueLeNumero()){
                                                    cv.saveSansNumero();

                                                    System.out.println("(manque le numéro): "+cv.titre);
                                                    continue;
                                                }
                                            }else{
                                                if(cv.titre.indexOf(cv.carteEbay.carte.recherche)==-1){
                                                    cv.saveSansNumero();

                                                    System.out.println("(manque le numéro): "+cv.titre);
                                                    continue;
                                                }
                                            }
*/
                                            int n=cv.getMultiple();
                                            System.out.println("(n="+n+")");
                                            if(n>1){
                                                //cv.saveMultiple();
                                                System.out.println("(carte multiple): "+cv.titre);
                                                continue;

                                            }
                                            if(cv.titreContient2Noms(pokemons)){
                                                //cv.saveDeuxNoms();
                                                continue;
                                            }
                                            if(cv.titreContientDeuxNumeros()){
                                                //cv.saveDeuxNumeros();
                                                continue;
                                            }
                                            cv.qualiteNum();
                                            cv.setIndice(indiceCarte);
                                            // le titre doit contenir le nom Tools.removeAccent(titre)
                                            if(!cv.titreContientNomDeLaCarte()) {
                                                //cv.saveSansNom();
                                                System.out.println("(!nom): "+cv+"     "+cv.titre);

                                                continue;
                                            }
                                            cv.setPrixFinal();
                                            sumPrix+=cv.prixFinal;
                                            nbCartesVendues++;
                                            carteEbay.addCarteVendue(cv);
                                            ///////////////////////////////////////////////////
                                            //cv.save();
                                            //System.out.println(cv);
                                            EntityManager entityManager0 = null;
                                            try {
                                                try {
                                                    entityManager0 = ApplicationPM.entityManager;
                                                    EntityTransaction trans = entityManager0.getTransaction();
                                                    trans.begin();

                                                    entityManager0.persist(cv);

                                                    trans.commit();

                                                } finally {
                                                    //if ( entityManager != null ) entityManager.close();
                                                    //if ( entityManagerFactory != null ) entityManagerFactory.close();
                                                }
                                            }catch (Exception e){
                                                System.out.println("Dans save CarteVendue : " + e);
                                            }



                                        }// Boucle CarteVendue
                                    } while (page < NbPages);// for des pages

                                    if(nbCartesVendues!=0)
                                        carteEbay.prixMoyen=(carteEbay.prixMoyen*carteEbay.effectif+ sumPrix)/(nbCartesVendues+carteEbay.effectif);
                                    carteEbay.effectif += nbCartesVendues;

                                    //carteEbay.update();
                                    // ici se termine une carteEbay
                                    if (pointFR == 0) break;
                                }// boucles  qualité
                            }// boucles etat

                        }//boucle langue


                    }//boucle Carte


                }// boucle extension
            }// boucle serie
            // ICI L'INTEGRATION DANS LA BDD


            System.out.println("VOICI LES DOUBLONS");
            /*
            String SQL="SELECT   COUNT(*) AS nbr_doublon, idCarteEbay, titre, prix,date,langue " //, etat,qualite
                    +" FROM "+ CarteVendue.baseDDCarteVendue
                    +" GROUP BY idCarteEbay, titre, prix,date,langue "// #, etat,qualite
                    +" HAVING   COUNT(*) > 1;";
            ResultSet result = statementLOC.executeQuery(SQL);
            while (result.next()) {
                int nbr_doublon = result.getInt("nbr_doublon");
                int idCarteEbay  = result.getInt("idCarteEbay");
                String titre = result.getString("titre");
                float prix = result.getFloat("prix");
                String date = result.getString("date");
                String langue = result.getString("langue");
                System.out.println(nbr_doublon+"x idCarteEbay = "+idCarteEbay+" titre = "+titre+" prix = "+prix+" date = "+date+" langue = "+langue);
            }
            // supprime les doublons dans Net2mySQL
            SQL="DELETE "+ CarteVendue.baseDDCarteVendue  + " FROM "+ CarteVendue.baseDDCarteVendue
                    +" LEFT OUTER JOIN "
                    +" (SELECT MIN(id) as id, idCarteEbay, titre, prix,date,langue "
                    +" FROM "+ CarteVendue.baseDDCarteVendue
                    +" GROUP BY idCarteEbay, titre, prix,date,langue ) as t1 "
                    +" ON "+ CarteVendue.baseDDCarteVendue+".id = t1.id "
                    +" WHERE t1.id IS NULL ";
            statementLOC.executeUpdate(SQL);
            System.out.println("DOUBLONS SUPPRIMES DE LA TABLE COURANTE");
            // Ajoute à la base principale
            SQL="INSERT INTO TotalCartesVendues (idCarteEbay , titre , prix , prixFinal ,vente ,date , pays,"
                    +" langue , professionalGrader , grade , edition , reverse , qualiteParse , qualiteNum)"
                    +" SELECT idCarteEbay,titre,prix,prixFinal,vente,date,pays,"
                    +" langue , professionalGrader , grade , edition , reverse , qualiteParse , qualiteNum "
                    +" FROM "+ CarteVendue.baseDDCarteVendue+" where idCarteEbay is not null;";
            statementLOC.executeUpdate(SQL);
            System.out.println("OK POUR L'INTEGRATION");
            // supprime les doublons dans TotalCartesVendues
            SQL="DELETE TotalCartesVendues FROM TotalCartesVendues "
                    +" LEFT OUTER JOIN "
                    +" (SELECT MIN(id) as id, idCarteEbay, titre, prix,date,langue "
                    +" FROM TotalCartesVendues "
                    +" GROUP BY idCarteEbay, titre, prix,date,langue ) as t1 "
                    +" ON TotalCartesVendues.id = t1.id "
                    +" WHERE t1.id IS NULL ";
            statementLOC.executeUpdate(SQL);
            System.out.println("DOUBLONS SUPPRIMES DE LA TABLE TotalCartesVendues");

            System.out.println("C'EST LA FIN !");
*/
        } catch (Exception excCV) {
            System.out.println("probleme Fin ..."+excCV);
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) throws Exception {
        /*
        Net2mysql nmsql =new Net2mysql();


        CarteVendue.creerBDD();
        nmsql.extraire();
        */
    }
}

