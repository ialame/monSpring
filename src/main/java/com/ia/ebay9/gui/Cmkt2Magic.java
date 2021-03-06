package com.ia.ebay9.gui; /**
 * Created by ia on 28/01/2019.
 */

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


public class Cmkt2Magic implements Runnable{
    public Cmkt app;
    public int id = 0;

    public int NbCartes, NbImages;


    public static Connection connect = null;
    public static Statement statement = null;

    public static String table_ExtensionMKTmagic, table_CarteMKTmagic,table_CarteMKTmagicVendue,table_VendeurMKTmagic,table_CarteMKTmagicVendeur;
    public static String strConnexionLoc;
    //public static ArrayList<VendeurCMKT> vendeurs = new ArrayList<>();
    public static ArrayList<String> LesVendeurs = new ArrayList<>();
    public static ArrayList<String> LesVendeursDansBDD = new ArrayList<>();
    Cmkt2Magic(Cmkt app) {
        this.app =app;
    }


    public static void initialisation() {


        Constructor constructor = new Constructor(Config.class);
        Yaml yaml = new Yaml(constructor);

        InputStream input = null;
        try {
            input = new FileInputStream(new File("config/parameters.yml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Config config = yaml.loadAs(input, Config.class);
        String database_driver = config.getParameters().get("database_driver");// pdo_mysql
        String database_host = config.getParameters().get("database_host");// vps511329.ovh.net
        String database_port = config.getParameters().get("database_port");// c1Ecommerce2
        String database_name = config.getParameters().get("database_name");// c1brancheDevelop2 # c1brancheDevelop2 # c1Ecommerce2 #c1brancheCarteCadeau
        String database_user = config.getParameters().get("database_user");// c1userdev  # c1ia #c1userdev
        String database_password = config.getParameters().get("database_password");// oxAnn123321 #oxAnn123321 # ASestFolle?!

        table_ExtensionMKTmagic = config.getParameters().get("table_ExtensionMKTmagic");
        table_CarteMKTmagic = config.getParameters().get("table_CarteMKTmagic");
        table_CarteMKTmagicVendue = config.getParameters().get("table_CarteMKTmagicVendue");
        table_VendeurMKTmagic = config.getParameters().get("table_VendeurMKTmagic");
        table_CarteMKTmagicVendeur = config.getParameters().get("table_CarteMKTmagicVendeur");


        //strConnexionLoc = "jdbc:mysql://" + host + ":3306/c1brancheDevelop2?user=c1userdev&password=oxAnn123321&characterEncoding=UTF-8";
        strConnexionLoc = database_driver + "://" + database_host + ":" + database_port + "/" + database_name + "?user=" + database_user + (database_password == null ? "" : "&password=" + database_password) + "&characterEncoding=UTF-8";

        try {
            connect = DriverManager.getConnection(strConnexionLoc);
        } catch (Exception exp) {
            System.out.println("problème ici " + exp);
        }
        try {
            Statement statement = Cmkt2Magic.connect.createStatement();
            String sql2 = "select raisonsociale from  TousLesVendeurs where name is not null;";
            ResultSet result = statement.executeQuery(sql2);
            while (result.next()) {
                LesVendeursDansBDD.add(result.getString("raisonsociale")) ;
            }
            Collections.sort(LesVendeursDansBDD);
            statement.close();

        } catch (Exception e1) {
            System.out.println(e1);

        }


    }





    public void run(){ // getExtensions() {
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
        Iterator<Element> DivSECTIONS = doc.select("div#ExpansionList").first().children().iterator();// c'est un BIG dans TABLE
        doc = null;

        Element divSection = null;

        while (DivSECTIONS.hasNext()) {
            divSection = DivSECTIONS.next();
            String annee = divSection.attr("id");
            Element extensions = divSection.select("div#collapse" + annee).first();
            Iterator<Element> DivEXTENSIONS = extensions.children().iterator();// c'est un BIG dans TABLE
            Element divExtension = null;
            while (DivEXTENSIONS.hasNext()) {
                divExtension = DivEXTENSIONS.next();
                Iterator<Element> DivProprietes = divExtension.children().iterator();// c'est un BIG dans TABLE
                Element divPropriete = null;

                divPropriete = DivProprietes.next();
                divPropriete = DivProprietes.next();
                divPropriete = DivProprietes.next();
                String href = divExtension.select("a").attr("href");
                href="https://www.cardmarket.com"+href.replace("Expansions","Products/Singles")+"?perSite=50&site=";
                String nom = divPropriete.text();
                divPropriete = DivProprietes.next();
                String nb = divPropriete.text();
                divPropriete = DivProprietes.next();
                String date = divPropriete.text();
                ExtensionCMKTmagic extensionCMKTmagic = new ExtensionCMKTmagic();
                extensionCMKTmagic.setName(nom);
                int size =Integer.parseInt(nb.substring(0,nb.indexOf(" ")).trim()) ;
                extensionCMKTmagic.setTotalSetSize(size);
                extensionCMKTmagic.setAnnee(Integer.parseInt(annee));
                System.out.println("\n///////////////////////////////////////////////////////////////////////////////");
                System.out.println("\nExtension: "+ extensionCMKTmagic);
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
                extensionCMKTmagic.setReleaseDate(LocalDate.of(year, month, day));

                extensionCMKTmagic.save();

                if(size>0) {
                    extensionCMKTmagic.getCartes(href);
                }

            }
        }

    }

    public static String parsePrix(String prix){
        StringBuffer sb = new StringBuffer();
        for(int j=0; j<prix.length();j++){
            char c=prix.charAt(j);
            if(('0'<=c && c<= '9') || c=='.')
                sb.append(c);
            else if(c==',')
                sb.append('.');
        }
        return sb.toString();
    }


    public void getVendeurs(){
        String hrefPage = "https://www.cardmarket.com/fr/Magic/Users?username=&country=LV";
        Document docPage=null,doc=null;

        while(docPage ==null || docPage.text().equals("")) {
            try {
                docPage = Jsoup.connect(hrefPage).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                        .get();
            } catch (Exception ee) {
                System.out.println("probleme internet 1, je recommence !");
                continue;
            }
        }
        doc = Jsoup.parseBodyFragment(docPage.toString());

        Iterator<Element> TR = null;

        try {
            if(doc.select("div.table-body").size()>0)
                TR = doc.select("div.table-body").first().children().iterator();// c'est un BIG dans TABLE
        }catch(Exception exception){
            System.out.println("div.table-body   1   : "+ hrefPage);

        }
        if(TR==null) return;
        Element tr = null,td = null;
        Iterator<Element> TD =null;
        while (TR.hasNext()) {
            tr = TR.next();
            TD = tr.children().iterator();
            td = TD.next();// case invisible
            td = TD.next();// qualité vendeur
            td = TD.next();// le reste
            Element username_and_info=td.child(0).children().first();
            Iterator<Element> username_and_info_Elements = username_and_info.children().iterator();
            Element username=username_and_info_Elements.next();
            String raisonSociale=username.select("a").first().text();

            //if(!existeDansBDD(raisonSociale)) {
            if(VendeurCMKT.existeDansBDD(raisonSociale)==-1) {
                ajouteVendeur(raisonSociale);
            }



        }
        tr=null;TR=null;td=null;TD=null;

        while(LesVendeurs.size()>0){
            String mot=LesVendeurs.get(0);
            LesVendeurs.remove(0);
            getVendeur(mot);
            System.out.println("size()="+LesVendeurs.size());
        }

    }


    public void completeVendeurs(){
        for(String v : LesVendeursDansBDD) {
            //if (!v.equals("biyikli1308") && !v.equals("manatrust"))
            getVendeur(v);
        }
    }


    public void getVendeur(String raisonSociale){
        VendeurCMKT vendeur = new VendeurCMKT(raisonSociale);
        Document docNouveau=null;
        while(docNouveau ==null || docNouveau.text().equals("")) {
            try {
                docNouveau = Jsoup.connect("https://www.cardmarket.com/fr/Magic/Users/"+raisonSociale).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                        .get();
            } catch (Exception ee) {
                System.out.println("probleme internet 2, je recommence !");
                continue;
            }
        }
        docNouveau = Jsoup.parseBodyFragment(docNouveau.toString());

        Iterator<Element> ROW = null;

        try {
            if(docNouveau.select("table.sellerInfo-table").size()>0)
                ROW = docNouveau.select("table.sellerInfo-table").first().child(1).children().iterator();// c'est un BIG dans TABLE
        }catch(Exception exception){
            System.out.println("table.sellerInfo-table   1   : Problème ("+raisonSociale+")");

        }
        if(ROW==null) return;
        Element row = null;

        while (ROW.hasNext()) {
            row = ROW.next();
            String propriete = row.children().first().text();
            switch(propriete){
                case "Compte" :
                    String compte = row.children().last().text();
                    break;
                case "Enregistré depuis" :
                    try {
                        String enregistreDepuis = row.children().last().text();
                        String[] ed = enregistreDepuis.split("\\.");
                        int year = Integer.parseInt(ed[2]);
                        int month = Integer.parseInt(ed[1]);
                        int day = Integer.parseInt(ed[0]);
                        vendeur.enregistreDepuis = LocalDate.of(year, month, day);//18.01.2013
                    }catch(Exception ex){
                        System.out.println("date enregistreDepuis: " + ex);
                    }
                    break;
                case "Nom" :
                    vendeur.name = row.children().last().text();
                    break;
                case "Rue" :
                    vendeur.rue = row.children().last().text();
                    break;
                case "Ville" :
                    vendeur.ville = row.children().last().text();
                    break;
                case "Pays" :
                    vendeur.pays = row.children().last().text();
                    break;
                case "Téléphone" :
                    vendeur.telephone = row.children().last().text();
                    break;
                case "Adresse mail" :
                    vendeur.mail = row.children().last().text();
                    break;
                case "Achats" :
                    try {
                        int n = Integer.parseInt(row.children().last().text().trim()) ;
                        vendeur.achats = n;
                    }catch(Exception ex){
                        System.out.println("achats: " + ex);
                    }

                    break;
                case "Ventes" :
                    try {
                        int n = Integer.parseInt(row.children().last().text().trim()) ;
                        vendeur.ventes = n;
                    }catch(Exception ex){
                        System.out.println("ventes: " + ex);
                    }
                    //vendeur.ventes = row.children().last().text();
                    break;
                case "Articles vendus" :
                    try {
                        int n = Integer.parseInt(row.children().last().text().trim()) ;
                        vendeur.articlesVendus = n;
                    }catch(Exception ex){
                        System.out.println("articlesVendus: " +ex);
                    }
                    //vendeur.articlesVendus = row.children().last().text();
                    break;
            }
        }

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //     ici les cartes d'un vendeur
        //catArticles-list
        String hrefCARTES="";
        int nbCartes=0;
        try {
            hrefCARTES = docNouveau.select("ul.catArticles-list").first().select("a").attr("href");
            int pos1= hrefCARTES.indexOf("?");
            int pos2= hrefCARTES.indexOf("&");
            String idCategory=hrefCARTES.substring(pos1+1,pos2);
            String idUser =hrefCARTES.substring(pos2+1).trim();
            idCategory=idCategory.substring(idCategory.indexOf("=")+1);
            idUser=idUser.substring(idUser.indexOf("=")+1);
            String CartesNbCartes = docNouveau.select("ul.catArticles-list").first().select("a").text();
            pos1= CartesNbCartes.indexOf("(");
            pos2= CartesNbCartes.indexOf(")");
            nbCartes= Integer.parseInt(CartesNbCartes.substring(pos1+1,pos2));
            vendeur.nbCartes=nbCartes;
        }catch(Exception exception){
            System.out.println("table.sellerInfo-table   1   : Problème : pas de cartes pour ("+raisonSociale+")");
        }

        vendeur.save();//  attention !!!!

        vendeur=null;


        if(true){
            return;
        }


        //////////////////////////

        try {
            ROW = docNouveau.select("table.sellerEval-table").first().child(1).children().iterator();// c'est un BIG dans TABLE
        }catch(Exception exception){
            System.out.println("Page vide ! ");
            return;
        }
        docNouveau=null;
        if(!ROW.hasNext()) return;
        row = null;

        while (ROW.hasNext()) {
            row = ROW.next();
            raisonSociale =row.children().first().text();
            if(row.select("a").size()==0) continue;
            //if(!existeDansBDD(raisonSociale)) {
            if(VendeurCMKT.existeDansBDD(raisonSociale)==-1){
                ajouteVendeur(raisonSociale);

            }

        }
        row=null;ROW=null;
    }



    public void getCartesVendeur(String url){
        //String hrefNouvellePage = "https://www.cardmarket.com/fr/Magic/Users/"+vendeur.raisonSociale;
        String hrefNouvellePage = "https://www.cardmarket.com"+url;//fr/Magic/MainPage/browseUserProducts?idCategory=1&idUser=1066387";
        Document docNouveau=null;
        while(docNouveau ==null || docNouveau.text().equals("")) {
            try {
                docNouveau = Jsoup.connect(hrefNouvellePage).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                        .get();
                docNouveau=Jsoup.parse(docNouveau.text(), "", Parser.htmlParser());
                org.jsoup.Connection.Response response = Jsoup.connect("https://www.cardmarket.com").method(org.jsoup.Connection.Method.GET).execute();
                response = Jsoup.connect("https://www.cardmarket.com/fr/Magic/MainPage/browseUserProducts?idCategory=1&idUser=1066387")
                        .data("ctl00$ContentPlaceHolder1$ctl00$Login1$UserName", "CompteTest")
                        .data("ctl00$ContentPlaceHolder1$ctl00$Login1$Password", "PipiCaca123")
                        .cookies(response.cookies())
                        .method(org.jsoup.Connection.Method.POST)
                        .execute();

                Document homePage = Jsoup.connect("https://www.cardmarket.com/fr/Magic/MainPage/browseUserProducts?idCategory=1&idUser=1066387")
                        .cookies(response.cookies())
                        .get();
                homePage=homePage;
            } catch (Exception ee) {
                System.out.println("probleme page !");
                continue;
            }
        }





        docNouveau = Jsoup.parseBodyFragment(docNouveau.toString());

        Iterator<Element> ROW = null;

        try {
            if(docNouveau.select("table.MKMSortable").size()>0)
                ROW = docNouveau.select("table.MKMSortable").first().child(1).children().iterator();// c'est un BIG dans TABLE
        }catch(Exception exception){
            System.out.println("table.sellerInfo-table   1   : Problème ");

        }
        docNouveau=null;
        if(ROW==null) return;
        Element row = null;

        while (ROW.hasNext()) {
            row = ROW.next();
            System.out.println(row);
        }


    }


    public void ajouteVendeur(String mot){

        if (LesVendeurs.size()==0)
            LesVendeurs.add(mot);
        int k=0;
        for(;k<LesVendeurs.size();k++) {
            if(mot.compareTo(LesVendeurs.get(k))>0) {
                LesVendeurs.add(k, mot);
                break;
            }else if(mot.equals(LesVendeurs.get(k))) break;
        }
        if(k==LesVendeurs.size() && !mot.equals(LesVendeurs.get(k-1)))
            LesVendeurs.add(k, mot);

    }
    public static boolean existeDansBDD(String raisonSociale){
        if (LesVendeursDansBDD.size()==0) {
            LesVendeursDansBDD.add(raisonSociale);
            return false;
        }
        int k=0;
        for(;k<LesVendeursDansBDD.size();k++) {
            if(k==6795)
                k=k;
            if(raisonSociale.compareTo(LesVendeursDansBDD.get(k))>0) {
                LesVendeursDansBDD.add(k, raisonSociale);
                return false;
            }else if(raisonSociale.equals(LesVendeursDansBDD.get(k)))
                return true;
        }
        if(k==LesVendeursDansBDD.size() && !raisonSociale.equals(LesVendeursDansBDD.get(k-1))) {
            LesVendeursDansBDD.add(k, raisonSociale);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        //Cmkt2Magic em = new Cmkt2Magic();
        initialisation();

        //ExtensionCMKTmagic.creerBDD();
        //CarteCMKTmagic.creerBDD();
        //CarteMKTmagicVendue.creerBDD();


        //em.getExtensions();
        VendeurCMKT.creerBDD();
        //em.getVendeurs();//em.getVendeurs();
        //em.getVendeur(new VendeurCMKT("AlexMartorell10"));juvesak
        //em.getVendeur("kurilind");
        //em.completeVendeurs();

    }


}


