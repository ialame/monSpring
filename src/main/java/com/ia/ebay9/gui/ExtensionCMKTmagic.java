package com.ia.ebay9.gui; /**
 * Created by ia on 28/01/2019.
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;


public class ExtensionCMKTmagic {
    public int id=0;
    public String name;
    public String code;
    public LocalDate releaseDate;
    public String tcgplayerGroupId;
    public String type;
    public String mtgoCode;
    public String baseSetSize;
    public String boosterV3;
    public LocalDate date;
    public String version;
    public String block;
    public int totalSetSize;
    public int NbCartes,NbImages;
    public int annee;
    protected ArrayList<CarteCMKTmagic> cards = new ArrayList<>();



    ExtensionCMKTmagic(){

    }
    ExtensionCMKTmagic(int id){
        this.id=id;

    }


    public void setId(int id){ this.id=id;}
    public int getId(){ return id;}

    public void setName(String name){ this.name=name; }
    public String getName(){ return name; }
    public void setCode(String code){ this.code=code; }
    public String getCode(){ return code; }
    public void setReleaseDate(LocalDate releaseDate){  this.releaseDate=releaseDate; }
    public LocalDate getReleaseDate(){  return releaseDate; }
    public void setTcgplayerGroupId(String tcgplayerGroupId){ this.tcgplayerGroupId=tcgplayerGroupId; }
    public String getTcgplayerGroupId(){ return tcgplayerGroupId; }
    public void setType(String type){ this.type=type; }
    public String getType(){ return type; }
    public void setMtgoCode(String mtgoCode){ this.mtgoCode=mtgoCode; }
    public String getMtgoCode(){ return mtgoCode; }
    public void setBaseSetSize(String baseSetSize){ this.baseSetSize=baseSetSize; }
    public String getBaseSetSize(){ return baseSetSize; }
    public void setBoosterV3(String boosterV3){ this.boosterV3=boosterV3; }
    public String getBoosterV3(){ return boosterV3; }
    public void setDate(LocalDate date){  this.date=date; }
    public LocalDate getDate(){  return date; }
    public void setVersion(String version){ this.version=version; }
    public String getVersion(){ return version; }
    public void setBlock(String block){ this.block=block; }
    public String getBlock(){ return block; }

    public void setAnnee(int annee){ this.annee=annee; }
    public int getAnnee(){ return annee; }

    public void setTotalSetSize(int totalSetSize){ this.totalSetSize=totalSetSize; }
    public int getTotalSetSize(){ return totalSetSize; }

    public static void creerBDD() {

        /*try {

            Statement statement = ApplicationPM.DB.getConnect().createStatement();

            DatabaseMetaData dbm = ApplicationPM.DB.getConnect().getMetaData();
            // statement.executeUpdate("use FX2Adrien;");
            ResultSet tables = dbm.getTables(null, null, Cmkt2Magic.table_ExtensionMKTmagic, null);
            if (tables.next()) {
                statement.executeUpdate("DROP TABLE " + Cmkt2Magic.table_ExtensionMKTmagic);
            }

            String sql = "CREATE TABLE `" + Cmkt2Magic.table_ExtensionMKTmagic + "` ( " +
                    "id int NOT NULL AUTO_INCREMENT," +
                    "`name`  varchar(100) DEFAULT NULL," +
                    "`code` varchar(100) DEFAULT NULL," +
                    "`releaseDate` varchar(100) DEFAULT NULL," +
                    "`tcgplayerGroupId` varchar(100) DEFAULT NULL," +
                    "`type` varchar(500) DEFAULT NULL," +
                    "`mtgoCode` varchar(400) DEFAULT NULL," +
                    "`baseSetSize`  int DEFAULT NULL," +
                    "`boosterV3`  varchar(500) DEFAULT NULL," +
                    "`date` varchar(100) DEFAULT NULL," +
                    "`version` varchar(100) DEFAULT NULL," +
                    "`block` varchar(100) DEFAULT NULL," +
                    "`totalSetSize` int DEFAULT NULL," +
                    "`NbCartes` int DEFAULT NULL," +
                    "`NbImages` int DEFAULT NULL," +
                    "PRIMARY KEY (`id`)" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";

            statement.executeUpdate(sql);//

            statement.close();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
*/

    }

    void save(){

        /*try{
            Statement statement = ApplicationPM.DB.getConnect().createStatement();

            String sql = "insert into `" + Cmkt2Magic.table_ExtensionMKTmagic + "`(id, name, code, releaseDate, tcgplayerGroupId, type, mtgoCode, baseSetSize, boosterV3, date, version, block, totalSetSize,NbCartes)  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = Cmkt2Magic.connect.prepareStatement(sql);
            preparedStatement.executeQuery("SET NAMES 'UTF8'");
            preparedStatement.executeQuery("SET CHARACTER SET 'UTF8'");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, code);
            if(releaseDate==null)
                preparedStatement.setDate(4, null);
            else
                preparedStatement.setDate(4, Date.valueOf(releaseDate));
            preparedStatement.setString(5, tcgplayerGroupId);
            preparedStatement.setString(6, type);
            preparedStatement.setString(7, mtgoCode);
            preparedStatement.setString(8, baseSetSize);
            preparedStatement.setString(9, boosterV3);
            if(date==null)
                preparedStatement.setDate(10, null);
            else
                preparedStatement.setDate(10, Date.valueOf(date));
            preparedStatement.setString(11, version);
            preparedStatement.setString(12, block);
            preparedStatement.setInt(13, totalSetSize);
            preparedStatement.setInt(14, NbCartes);
            preparedStatement.executeUpdate();
            statement.close();

        } catch (Exception e1) {
            System.out.println(e1);
        }*/
    }
    public ArrayList<CarteCMKTmagic> getCards(){
        return cards;
    }
    public void add(CarteCMKTmagic card){
        cards.add(card);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////
    public void getCartes(String hrefNouvellesPage) {
        String CARTES = "Magic";//"Magic";//"Pokemon"
        //String hrefNouvellesPage="https://www.cardmarket.com/fr/"+CARTES+"/Products/Cartes?perSite=50&site=";
        //String hrefNouvellesPage="https://www.cardmarket.com/fr/"+CARTES+"/Products/Singles?resultsPage=";
        String nameFR="",nameUS="",graphe="";

        int iii = 0;
        Document docPage = null, doc = null;

        if (!hrefNouvellesPage.equals("")) {

            while(docPage==null || docPage.text().equals("")) {
                try {

                    String URL = "https://www.cardmarket.com/fr/Magic/Products/Cartes/Ultimate-Masters?mode=&idCategory=1&idExpansion=2397&searchString=Pointe+de+Lave&idRarity=0&sortBy=sellVolume_desc&perSite=50";
                    //
                    docPage = Jsoup.connect(hrefNouvellesPage + 1).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                            .get();
                    doc = Jsoup.parseBodyFragment(docPage.toString());
                } catch (Exception httpExcep) {/////////// ICI ICI ici
                    System.out.println("ERREUR    =" + httpExcep + "   ------>   doc = " + doc);
                    //System.exit(1);
                }

            }


            Iterator<Element> e = doc.select("select").iterator();
            Element elem = doc.select("span.mx-1").first();

            String fin = elem.attr("href");
            int pos = elem.text().indexOf("of");
            int N = 1;
            if(pos!=-1) N=Integer.parseInt(elem.text().substring(pos+2).trim());

            for(int i=1;i<=N;i++){
                //Thread.sleep(5000);
                System.out.println("\n-------------------------------  Page "+i+"  -------------------------------" );
                docPage=null;
                while(docPage ==null || docPage.text().equals("")) {
                    try {
                        docPage = Jsoup.connect(hrefNouvellesPage + i).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                                .get();
                    } catch (Exception ee) {
                        System.out.println("probleme page !");
                        continue;
                    }
                }
                doc = Jsoup.parseBodyFragment(docPage.toString());

                Iterator<Element> TR = null;
                if((hrefNouvellesPage+i).equals("https://www.cardmarket.com/en/Magic/Products/Singles/Core-2019?perSite=50&site=3"))
                    i=i;
                try {
                    TR = doc.select("div.table-body").first().children().iterator();// c'est un BIG dans TABLE
                }catch(Exception exception){
                    System.out.println("div.table-body   1   : "+ hrefNouvellesPage+i);

                }
                if(!TR.hasNext()) return;
                Element tr = null;

                while (TR.hasNext()) {
                    tr = TR.next();
                    Iterator<Element> TD = tr.children().iterator();

                    int k = 1;
                    Element td = null;

                    td = TD.next();// case invisible
                    td = TD.next();// caméra
                    td = TD.next();// extension
                    String extension = td.select("span").attr("data-original-title");
                    td = TD.next();// Nom,nombre,rareté
                    Element tdNom = td.child(0).children().first();
                    Element aNomFR = tdNom.children().first();
                    nameFR = aNomFR.text();
                    String href = "https://www.cardmarket.com" + aNomFR.attr("href");
                    Element divNomUS = tdNom.children().last();
                    nameUS = divNomUS.text();

                    //CarteMkt carteMkt = new CarteMkt(nameFR, nameUS, extension, "rarete", "graphe");

                    Element tdNombre = td.child(0).children().get(1);
                    Element tdRarete = td.child(0).children().get(2);
                    String number = tdNombre.text();
                    String rarete = tdRarete.text();

                    td = TD.next();// disponible
                    String disponible = td.text();
                    td = TD.next();// de
                    String prix1 = td.text();



                    td = TD.next();// disponible2
                    String disponible2 = td.text();
                    td = TD.next();// De (Foil)
                    String prix2 = td.text();





                    Document docSousPage=null;

                    while(docSousPage ==null || docSousPage.text().equals("")) {
                        try {
                            //href="https://www.cardmarket.com/fr/Magic/Products/Cartes/Ultimate-Masters/Pointe-de-Lave";
                            docSousPage = Jsoup.connect(href).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                                    .get();
                            docSousPage = Jsoup.parseBodyFragment(docSousPage.toString());
                        } catch (Exception ee) {
                            System.out.println("probleme sous page !");
                            continue;
                        }



                    }
                    Element DL  = docSousPage.select("dl.labeled").first();


                    /// Entête


                    Iterator<Element> DT = DL.select("dt").iterator();
                    Iterator<Element> DD = DL.select("dd").iterator();

/*
                    Element dt = null, dd = null;



                    while (DT.hasNext() && DD.hasNext()) {
                        dt = DT.next();
                        dd = DD.next();
                        switch (dt.text()) {
                            case "Rareté":
                                rarete = dd.select("span").attr("data-original-title");
                                break;
                            case "Edité dans":
                                extension = dd.text();
                                break;
                            case "Number":
                                number = dd.text();
                                break;
                            case "Réimpressions":
                            case "Articles disponibles":
                        }

                    }
                    Element Graphe = docSousPage.select("div.chart-wrapper").first();
                    Element script = Graphe.select("script").first();
                    //graphe = script.html();

*/


                    CarteCMKTmagic carteCMKTmagic = new CarteCMKTmagic(getMaxId(),nameUS,nameFR,rarete,number,graphe);
                    /*
                    if(nameUS.equals("Teysa, Orzhov Scion"))
                        nameFR=nameFR;
                    */
                    carteCMKTmagic.save();

                    System.out.print("\nCarte : " + carteCMKTmagic+"\n         ");

                    if(docSousPage.select("div.table-body").first().text().equals("Sorry, no matches for your query"))
                        continue;
                    Iterator<Element>  divsRowsTable = docSousPage.select("div.table-body").first().children().iterator();
                    Element divRow = null,divTd=null;
                    //System.out.println("ERREUR  N=10");
                    while(divsRowsTable.hasNext()){

                        CarteMKTmagicVendue carteMKTmagicVendue = new CarteMKTmagicVendue();
                        carteMKTmagicVendue.setCarteMKTmagic_id(0);// A T T E N T I O N    carteCMKTmagic.getMaxId());

                        divRow = divsRowsTable.next();
                        Iterator<Element> DIV_TDs=null;
                        try {
                            DIV_TDs = divRow.children().iterator();
                            Element d_none = DIV_TDs.next();// invisible
                        }catch(Exception ee){
                            System.out.println("Problème DIV_TDs");
                        }

                        Element colSellerProductInfo = DIV_TDs.next();// vendeur information produit

                        Element colSeller = colSellerProductInfo.child(0).children().first();//vendeur
                        Element colProduct = colSellerProductInfo.child(0).children().last();//information produit

                        Element divOffer = DIV_TDs.next();// offre


                        Element sellerName = colSeller.child(0).child(0);
                        Iterator<Element> sapnsSeller = sellerName.children().iterator();
                        Element spanSeller = null;


                        spanSeller=sapnsSeller.next();// vente vendeur
                        spanSeller=sapnsSeller.next();

                        String titre = spanSeller.attr("title");
                        if(!titre.equals("")){
                            pos = titre.indexOf(":");
                            carteMKTmagicVendue.setPays(titre.substring(pos+1).trim());
                        }
                        spanSeller=sapnsSeller.next();// le nom
                        carteMKTmagicVendue.setVendeur(spanSeller.text());


                        String qualite,langue,edition;
                        Element rowProduct = colProduct.child(0);
                        Iterator<Element> sapnProductAttributes = rowProduct.child(0).children().iterator();
                        qualite= sapnProductAttributes.next().select("span").attr("data-original-title");
                        carteMKTmagicVendue.setQualite(qualite);
                        langue= sapnProductAttributes.next().attr("data-original-title");
                        carteMKTmagicVendue.setLangue(langue);

                        if(sapnProductAttributes.hasNext()) {
                            edition = sapnProductAttributes.next().attr("data-original-title");
                            carteMKTmagicVendue.setEdition(edition);
                        }
                        String commentaire = rowProduct.child(1).child(0).text();
                        carteMKTmagicVendue.setCommentaire(commentaire);
                        String prix = divOffer.child(0).text();
                        String prixUnitaire="";
                        pos=prix.indexOf("PU:");

                        if(pos!=-1) {
                            prixUnitaire = prix.substring(pos + 3);
                            carteMKTmagicVendue.setPrixUnitaire(Float.parseFloat(Cmkt2Magic.parsePrix(prixUnitaire)));
                            prix = prix.substring(0, pos);
                        }

                        carteMKTmagicVendue.setPrixMoyen(Float.parseFloat(Cmkt2Magic.parsePrix(prix)));
                        String nombre = divOffer.child(1).text();
                        carteMKTmagicVendue.setEffectif(Integer.parseInt(nombre));
                        carteMKTmagicVendue.save();
                        System.out.print(carteMKTmagicVendue.getPrixMoyen()+"-");


                    }

                }

            }

        }

    }


    public static int getMaxId(){
        int idExtension=0;
        try {

            Statement statement = Cmkt2Magic.connect.createStatement();
            String sql2 = "select max(id) as max from  " +Cmkt2Magic.table_ExtensionMKTmagic;
            ResultSet resultMax = statement.executeQuery(sql2);
            if (resultMax.next()) {
                idExtension=resultMax.getInt("max") ;
            }
            statement.close();

        } catch (Exception e1) {
            System.out.println(e1);

        }
        return idExtension;
    }


    public void update(){
        try{
            Statement statement = Cmkt2Magic.connect.createStatement();

            String sqlUpdate = "UPDATE " + Cmkt2Magic.table_ExtensionMKTmagic + " SET NbImages = ? where id=" + this.id;

            PreparedStatement ppdStatementUpdate = Cmkt2Magic.connect.prepareStatement(sqlUpdate);
            ppdStatementUpdate.setInt(1, NbImages);
            ppdStatementUpdate.executeUpdate();
            statement.close();

        } catch (Exception e1) {
            System.out.println(e1);
        }

    }
    public String toString(){
        return annee+"  " + name + " " + totalSetSize + " " + date;
    }

}

