package com.ia.ebay9.gui;
/**
 * Created by ia on 08/01/2019.
 */

import com.ia.ebay9.entity.CartePokemon;
import com.ia.ebay9.entity.Extension;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class ExtensionMagic extends Extension {
    public int annee;
    public String code;
    public LocalDate releaseDate;
    public String tcgplayerGroupId;
    public int typeMagic_id;
    public String mtgoCode;
    public String baseSetSize;
    public String boosterV3;
    public String version;
    public String block;
    public int totalSetSize;
    public SerieMagic serieMagic = null;
    protected ArrayList<CarteMagic> cartes = new ArrayList();
    //private ArrayList<TokenMagic> tokens = new ArrayList<>();
    public static JSONObject jsonObject = null;

    public ArrayList<CarteMagic> getCartes() {
        return cartes;
    }

    public void setCartes(ArrayList<CarteMagic> cartes) {
        this.cartes = cartes;
    }

    public JSONArray JsonCartes=null;
    public JSONArray JsonTokens=null;

    public void setCards(ArrayList<CarteMagic> cards) {
        this.cartes = cards;
    }

    ExtensionMagic(){

    }
    ExtensionMagic(int id) {
        this.setId(id);
    }
    /*    try {
            //this.id=id;
            Connection  connectDev = DriverManager.getConnection(Json2Magic.strConnexionLoc);//
            Statement statementDev =  connectDev.createStatement();
            String sql="SELECT * FROM extensionMagic where id="+id;
            ResultSet  resultDev = statementDev.executeQuery(sql);
            if (resultDev.next()){
                name = resultDev.getString("name");
                int serie_id = resultDev.getInt("serieMagic_id");
                serie = new SerieMagic(serie_id);
            }
            statementDev.close();
        }catch(Exception e){
            System.out.println("probleme extension  ..."+e);

        }

    }*/
    ExtensionMagic(ResultSet  rs){
        mySQL2ExtensionMagic(rs);
    }
    ExtensionMagic(String code) {
        /*try {
            Connection connect = ApplicationPM.DB.getConnect();//
            Statement statement = connect.createStatement();
            String sql = "SELECT * FROM `" + ApplicationPM.DB.getTable_ExtensionMagic() + "` where code='" + code + "';";
            ResultSet resultDev = statement.executeQuery(sql);
            if (resultDev.next()) {
                mySQL2ExtensionMagic(resultDev);
            }

        }catch(Exception e){
            System.out.println("probleme ExtensionMagicCode ..." + e);
        }*/
    }
    void mySQL2ExtensionMagic(ResultSet  rs){
        /*try {
            Statement statementCarte = com.ia.ebay9.gui.ApplicationPM.DB.getConnect().createStatement();


            int id = rs.getInt("id");
            this.setId(id);



            this.setSerieABC(new SerieMagic(serie_id));
            this.setName(rs.getString("name"));
            this.setCode(rs.getString("code"));
            //this.setNomFR(rs.getString("nomFR"));
            //this.setNomUS(rs.getString("nomUS"));
            //em.setAnnee(resultDev.getInt("annee"));
            this.setReleaseDate(rs.getDate("releaseDate").toLocalDate());
            this.setType(rs.getInt("typeMagic_id"));
            this.setDateSortie(rs.getDate("dateSortie").toLocalDate());
            int serie_id = rs.getInt("serieMagic_id");
            *//*
                int idSerie = 0;
                String sql = "select * from "+ com.ia.ebay9.gui.ApplicationPM.getTable_SerieMagic() + " where id  = " + serie_id ;

                ResultSet resultSerie = statementCarte.executeQuery(sql);
                SerieMagic serie = new SerieMagic();
                serie.setId(serie_id);
                serie.setNom(this.getAnnee()+"");
                if (resultSerie.next()) {
                    idSerie = resultSerie.getInt("id");
                }
                if(idSerie==0)
                    serie.save(com.ia.ebay9.gui.ApplicationPM.getTable_SerieMagic());
                this.setSerie(serie);
            *//*
            statementCarte = com.ia.ebay9.gui.ApplicationPM.DB.getConnect().createStatement();
            String sql = "select * from "+ com.ia.ebay9.gui.ApplicationPM.DB.getTable_CarteMagic() +" where extensionMagic_id  = " + id ;
            ResultSet resultCarte = statementCarte.executeQuery(sql);
            resultCarte = statementCarte.executeQuery(sql);

            while (resultCarte.next()) {
                CarteMagic cm = new CarteMagic(resultCarte);
                cm.setExtension(this);
                cartes.add(cm);
            }
            //this.setCartesMagic(cartes);
        }catch(Exception exception){
            System.out.println(exception);
        }*/
    }


    public void setAnnee(int annee) {
        this.annee = annee;
    }


    public int getAnnee(){ return releaseDate.getYear();}
    public void setCode(String code){ this.code=code; }

    public int getType() {
        return typeMagic_id;
    }

    public void setType(int type) {
        this.typeMagic_id = type;
    }

    public String getCode(){ return code; }
    public void setReleaseDate(LocalDate releaseDate){  this.releaseDate=releaseDate; }
    public LocalDate getReleaseDate(){  return releaseDate; }
    public void setTcgplayerGroupId(String tcgplayerGroupId){ this.tcgplayerGroupId=tcgplayerGroupId; }
    public String getTcgplayerGroupId(){ return tcgplayerGroupId; }
    public void setMtgoCode(String mtgoCode){ this.mtgoCode=mtgoCode; }
    public String getMtgoCode(){ return mtgoCode; }
    public void setBaseSetSize(String baseSetSize){ this.baseSetSize=baseSetSize; }
    public String getBaseSetSize(){ return baseSetSize; }
    public void setBoosterV3(String boosterV3){ this.boosterV3=boosterV3; }
    public String getBoosterV3(){ return boosterV3; }

    public void setVersion(String version){ this.version=version; }
    public String getVersion(){ return version; }
    public void setBlock(String block){ this.block=block; }
    public String getBlock(){ return block; }
    public void setTotalSetSize(int totalSetSize){ this.totalSetSize=totalSetSize; }
    public int getTotalSetSize(){ return totalSetSize; }
    public void addCarte(CarteMagic carte){
        cartes.add(carte);
    }


    public static void creerBDD(String tableExtension) {

        /*try {
            Connection connection = ApplicationPM.DB.getConnect();
            Statement statement = connection.createStatement();
            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet tables = dbm.getTables(null, null, tableExtension, null);
            if (tables.next()) {
                statement.executeUpdate("DROP TABLE " + tableExtension);
            }

            String sql = "CREATE TABLE `" + tableExtension + "` ( " +
                    "id int NOT NULL AUTO_INCREMENT," +
                    "`name`  varchar(100) DEFAULT NULL," +
                    "`code` varchar(100) DEFAULT NULL," +
                    "`releaseDate` varchar(100) DEFAULT NULL," +
                    "`tcgplayerGroupId` varchar(100) DEFAULT NULL," +
                    "`typeMagic_id` int DEFAULT NULL," +
                    "`mtgoCode` varchar(400) DEFAULT NULL," +
                    "`baseSetSize`  int DEFAULT NULL," +
                    "`boosterV3`  varchar(500) DEFAULT NULL," +
                    "`dateSortie` varchar(100) DEFAULT NULL," +
                    "`version` varchar(100) DEFAULT NULL," +
                    "`block` varchar(100) DEFAULT NULL," +
                    "`totalSetSize` varchar(400) DEFAULT NULL," +
                    "`NbCartes` int DEFAULT NULL," +
                    "`NbImages` int DEFAULT NULL," +
                    "`nomUS` varchar(100) DEFAULT NULL," +
                    "`serieMagic_id` int DEFAULT NULL," +
                    "PRIMARY KEY (`id`)" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";

            statement.executeUpdate(sql);//

            statement.close();

        } catch (Exception ex) {
            ex.printStackTrace();

        }

*/
    }

    void save(String tableExtension){

        /*try{
            Connection connection = com.ia.ebay9.gui.ApplicationPM.DB.getConnect();
            Statement statement =connection.createStatement();

            String sql = "insert into `" + tableExtension + "`(id, name, code, releaseDate, tcgplayerGroupId, " +
                    " typeMagic_id, mtgoCode, baseSetSize, boosterV3, dateSortie, version, block, totalSetSize,NbCartes, " +
                    " nomUS,serieMagic_id,idPCA) " +
                    " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
            preparedStatement.setInt(6, typeMagic_id);//typeMagic_id =1  ?????
            preparedStatement.setString(7, mtgoCode);
            preparedStatement.setString(8, baseSetSize);
            preparedStatement.setString(9, boosterV3);
            if(dateSortie==null)
                preparedStatement.setDate(10, null);
            else
                preparedStatement.setDate(10, Date.valueOf(dateSortie));
            preparedStatement.setString(11, version);
            preparedStatement.setString(12, block);
            preparedStatement.setInt(13, totalSetSize);
            preparedStatement.setInt(14, nbCartes);
            preparedStatement.setString(15, nomUS);
            preparedStatement.setInt(16, getSerieABC().getId());
            preparedStatement.setString(17, idPCA);
            preparedStatement.executeUpdate();
            statement.close();

        } catch (Exception e1) {
            System.out.println(e1);
        }*/
    }


    public void add(CarteMagic carte){
        cartes.add(carte);
    }
    public String toString(){
        return code+" : "+ this.getNomRaccourci();
    }




    ///////////////////////////////////////////////////
    //
    //                   i n i t
    //
    //////////////////////////////////////////////////

    public void init(JSONObject extension) {
        Set<Object> Proprietes = extension.keySet();
        int nbCartes =((JSONArray)extension.get("cards")).size();
        Iterator<Object> iterProprietes = Proprietes.iterator();


        while (iterProprietes.hasNext()) {
            Object prop = iterProprietes.next();
            if(extension.get(prop)==null) continue;
            switch (prop.toString()){
                case "name":
                    String name=extension.get(prop).toString();
                    String nom=extension.get(prop).toString();
                    String nomUS=extension.get(prop).toString();
                    break;
                case "code":
                    this.setCode(extension.get(prop).toString());
                    break;
                case "releaseDate":
                    this.setReleaseDate(LocalDate.parse(extension.get(prop).toString()));
                    int annee = getAnnee();
                    SerieMagic serie = null;
                    /*try{
                        Connection connect = ApplicationPM.DB.getConnect();
                        Statement statement =  connect.createStatement();
                        String sql = "SELECT * FROM "+ ApplicationPM.DB.getTable_SerieMagic() + " where nom = "+annee;
                        ResultSet  resultDev = statement.executeQuery(sql);
                        if (resultDev.next()){
                            serie = new SerieMagic(resultDev);
                        }else{
                            serie = new SerieMagic();
                            serie.init(annee);
                            serie.save(ApplicationPM.DB.getTable_SerieMagic());
                        }
                        //this.setSerieABC(serie);  MIGRATION

                        statement.close();
                    }catch(Exception ex){

                    }*/

                    break;
                case "tcgplayerGroupId":
                    this.setTcgplayerGroupId(extension.get(prop).toString());
                    break;
                case "type":
                    int idType = 20;
                    String Type=extension.get(prop).toString();
                    /*try {
                        Statement statement = ApplicationPM.DB.getConnect().createStatement();
                        String sql2 = "select id from  " + ApplicationPM.DB.getTable_TypeMagic() +" where type='"+Type +"';";
                        ResultSet resultMax = statement.executeQuery(sql2);
                        if (resultMax.next()) {
                            idType=resultMax.getInt("id") ;//https://scryfall.com/card/ktk/1/abzan_battle_priest
                        }
                        statement.close();
                    } catch (Exception e1) {
                        System.out.println(e1);

                    }*/
                    this.setType(idType);
                    break;

                case "mtgoCode":
                    this.setMtgoCode(extension.get(prop).toString());
                    break;
                case "baseSetSize":
                    this.setBaseSetSize(extension.get(prop).toString());
                    break;
                case "boosterV3":
                    this.setBoosterV3(extension.get(prop).toString());
                    break;

                case "block":
                    this.setBlock(extension.get(prop).toString());
                    break;
                case "totalSetSize":
                    this.setTotalSetSize(Integer.parseInt(extension.get(prop).toString()));
                    break;
                case "cards":
                    JsonCartes=(JSONArray) extension.get(prop);
                    break;
                case "tokens":
                    JsonTokens=(JSONArray) extension.get(prop);
                    break;
                case "meta":
                    JSONObject meta = (JSONObject) extension.get(prop);
                    Set<Object> titres = meta.keySet();
                    Iterator<Object> iter = titres.iterator();

                    while (iter.hasNext()) {
                        Object titre = iter.next();
                        switch (titre.toString()) {
                            case "date":
                                this.setDateSortie(LocalDateTime.parse(meta.get(titre).toString()));
                                break;
                            case "version":
                                this.setVersion(meta.get(titre).toString());
                                break;
                        }

                    }
                    break;
            }
            //System.out.println(prop.toString() + "\t" + carte.get(prop));
        }


    }

    /**
     *
     * @return ExtensionMagic
     */
    public static ExtensionMagic getExtensionByCode(String code){
        ExtensionMagic em = new ExtensionMagic();
        /*try {
            Statement statementDev =  ApplicationPM.DB.getConnect().createStatement();
            String sql="SELECT * FROM extensionMagic where code='"+code+"';";
            ResultSet  resultDev = statementDev.executeQuery(sql);
            if (resultDev.next()){
                int id = resultDev.getInt("id");
                em.setId(id);
                int serie_id = resultDev.getInt("serieMagic_id");
                //em.setSerieABC(new SerieMagic(serie_id));
                //em.setName(resultDev.getString("name"));
                em.setCode(resultDev.getString("code"));
                em.setNomFR(resultDev.getString("nomFR"));
                em.setNomUS(resultDev.getString("nomUS"));
                //em.setAnnee(resultDev.getInt("annee"));
                em.setReleaseDate(resultDev.getDate("releaseDate").toLocalDate());
                em.setType(resultDev.getInt("typeMagic_id"));
                //em.setDateSortie(resultDev.getDate("date").toLocalDate());

                ArrayList<CarteMagic> cards = new ArrayList<>();
                Statement statementCarte = ApplicationPM.DB.getConnect().createStatement();
                ResultSet resultCarte = statementCarte.executeQuery("select * from carteMagic where extensionMagic_id  = " + id + "  ORDER BY id ASC");

                while (resultCarte.next()) {
                    CarteMagic cm = new CarteMagic(resultCarte);
                    cards.add(cm);
                }
                em.setCards(cards);
            }
            statementDev.close();
        }catch(Exception e){
            System.out.println("probleme extension  ..."+e);

        }*/
        return em;
    }


    public static int getMaxId(){
        int idExtension=0;
        /*try {
            Statement statement = ApplicationPM.DB.getConnect().createStatement();
            String sql2 = "select max(id) as max from  " +ApplicationPM.DB.getTable_ExtensionMagic() ;
            ResultSet resultMax = statement.executeQuery(sql2);
            if (resultMax.next()) {
                idExtension=resultMax.getInt("max") ;
            }
            statement.close();
        } catch (Exception e1) {
            System.out.println(e1);

        }*/
        return idExtension;
    }


    public void getImages(){


        PrintWriter log = null;

//récupère l'extension à partir de Textfield

        //SerieMagic sm =(SerieMagic) this.getSerieABC();

        String dossier = "img/cards/magic/US";
        try {
            log = new PrintWriter(dossier + "/magic.log");
        }catch(Exception ex){
            ex.printStackTrace();
        }
        File dir = new File(dossier);
        boolean isCreated = dir.mkdirs();

        //SerieMagic s = extensionMagic.getSerie();
        //dir = new File(dossier + "/" + sm.getNom());
        isCreated = dir.mkdirs();
        //dir = new File(dossier + "/" + sm.getNom() + "/" + getCode());
        isCreated = dir.mkdirs();


        for (CartePokemon cm : getCartes()) {
            if (((CarteMagic) cm).getIsOversized()) continue;
            ((CarteMagic) cm).getImage();
        }

        System.out.println("  F I N   D E    L ' E X T E N S I O N ");


    }

    public void netMKT2Cartes(String hrefNouvellesPage) {
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
                    CarteMagic carteMagic = new CarteMagic(tr);
                    carteMagic.save();
                }

            }

        }

    }

    public SerieMagic getSerieMagic() {
        return serieMagic;
    }

    public void setSerieMagic(SerieMagic serieMagic) {
        this.serieMagic = serieMagic;
    }



    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public void setId(Integer id) {

    }

    @Override
    public void setNomFR(String nomFR) {

    }

    @Override
    public void setNomUS(String nomUS) {

    }

    @Override
    public void setNom(String nom) {

    }

    @Override
    public void setPromo(boolean promo) {

    }

    @Override
    public boolean isPromo() {
        return false;
    }

}

