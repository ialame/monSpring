package com.ia.ebay9.entity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.persistence.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by fxc on 19/10/2017.
 */

@Entity
@Table( name="CarteEbay" )
public class CarteEbay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @ManyToOne
    @JoinColumn(name = "carte_id", nullable = true)
    private Carte carte;

    @OneToMany(mappedBy = "carteEbay", orphanRemoval = true)
    private List<CarteVendue> cartesVendues = new ArrayList<>();
    public String nomEBAY;
    public String rechercheEbay;
    public boolean pointFR=false;
    public boolean pointCOM=true;
    public String etat;
    public String qualite;
    public String codeEtat;
    public String codeQualite;
    public String url;
    public String site;
    public Integer size;

    public int effectif = 0;
    public double prixMoyen = 0;
    //public int nbPages;
    //public boolean existe=true;
    public CarteEbay() {}

    public CarteEbay(CartePokemon carte, String etat, String qualite, boolean pointFR) {

        this.carte=carte;
        this.pointFR=pointFR;
        this.pointCOM = !pointFR;
        setEtatQualite(etat,qualite);
        setNom();
        setURL();
        //setURL();
    }

    public CarteEbay(CartePokemon carte) {
        this.carte = carte;
    }

    public void setNom() {
        CartePokemon cp =(CartePokemon) this.carte;
        nomEBAY=(pointCOM)? cp.getNomAnglaisEBAY():cp.getNomFrancaisEBAY();
        //nomZEBRA=(pointCOM)?cp.getNomAnglaisEBAY():this.carte.nomFrancaisZEBRA;

        rechercheEbay = nomEBAY+" "+cp.Recherche;
    }

    public void setPointFR(int pointFR) {
        //this.pointFR = (pointFR == 1);
        //this.pointCOM = !this.pointFR;
        setNom();
    }

    public void setEtatQualite(String etat, String qualite) {
        String codeEtat = etat;
        String codeQualite = qualite;
        //setURL();
        //boolean pointFR=true;
        if (codeEtat.equals("")) {

            return;
        }
        if (pointFR) {
            switch (etat) {
                case "1000":
                    this.etat = "Neuf";
                    break;
                case "3000":
                    this.etat = "Occasion";
                    break;
                default:
                    this.etat = "Non spécifié";
                    break;
            }
            switch (qualite.charAt(0)) {  //
                case 'C':
                    this.qualite = "Comme neuf"; //
                    break;
                case 'E':
                    this.qualite = "Endommagé"; //
                    break;
                case 'M':
                    this.qualite = "Moyennement usé"; //
                    break;
                case 'P':
                    this.qualite = "Peu usé"; //
                    break;
                case 'T':
                    this.qualite = "Très usé"; //
                    break;
                default:
                    this.qualite = "Non spécifié"; //
                    break;
            }
        } else {
            switch (etat) {
                case "1000":
                    this.etat = "New";
                    break;
                case "3000":
                    this.etat = "Used";
                    break;
                default:
                    this.etat = "Not specified";
                    break;
            }

            this.qualite = "Not specified";

        }
        //setURL();
    }


    protected void setURL(){

        String xi="\""+this.nomEBAY+"\"";
        //String HTTP0 ="https://www.ebay.com/sch/i.html?_from=R40&_sacat=0&LH_Sold=1&_udlo&_udhi&LH_ItemCondition=XXXX&_samilow&_samihi&_sop=13&_dmd=1&LH_Complete=1&_nkw=AAAA&rt=nc&LH_PrefLoc=2&_ipg=200";
        //String HTTP1  ="https://www.ebay.fr/sch/i.html?LH_Complete=1&LH_Sold=1&_from=R40&_sacat=0&%25C3%2589tat%2520de%2520la%2520carte=YYYY&_nkw=AAAA&LH_PrefLoc=1&_dcat=2611&rt=nc&LH_ItemCondition=XXXX&_trksid=p2045573.m1684&_ipg=200";
        String HTTP0 ="https://www.ebay.com/sch/i.html?_from=R40&_sacat=0&LH_Sold=1&_udlo&_udhi&_samilow&_samihi&_sop=13&_dmd=1&LH_Complete=1&rt=nc&LH_PrefLoc=2&_ipg=200";
        String HTTP1  ="https://www.ebay.fr/sch/i.html?LH_Complete=1&LH_Sold=1&_from=R40&LH_PrefLoc=1&_dcat=2611&rt=nc&_trksid=p2045573.m1684&_ipg=200";

        //https://www.ebay.fr/sch/i.html?LH_Complete=1&LH_Sold=1&_from=R40&_sacat=0&%25C3%2589tat%2520de%2520la%2520carte=%21&_nkw=Blastoise+2%2F102&LH_PrefLoc=1&_dcat=2611&rt=nc&LH_ItemCondition=10&_trksid=p2045573.m1684&_ipg=200
        //https://www.ebay.fr/sch/i.html?%C3%89tat+de+la+carte=%21&_from=R40&_nkw=Alakazam+1%2F102&_in_kw=4&_ex_kw=&_sacat=0&LH_Sold=1&_udlo=&_udhi=&_samilow=&_samihi=&_sadis=15&_stpos=&_fsradio2=%26LH_PrefLoc%3D1&_sargn=-1%26saslc%3D2&_salic=71&LH_SubLocation=1&_sop=13&_dmd=1&_ipg=200&LH_Complete=1
        //if (this.carte.nomAnglaisEBAY!=null && this.carte.nomAnglaisEBAY.indexOf(" Star") == -1)
            xi +=  " \"" + ((CartePokemon) this.getCarte()).Recherche+"\"";
        String xiprime="";
        try {
            xiprime = URLEncoder.encode(xi, "UTF-8");
        }catch(Exception e){
            System.out.println("probleme carteEbay3 ..."+e);

        }
        //boolean pointCOM=true;
        if(pointCOM) {

            url = HTTP0 + "&_nkw=" + xiprime;

            if (codeEtat!=null && !codeEtat.equals(""))
                url += "&LH_ItemCondition=" + codeEtat;
            //this.url=HTTP0.replace("AAAA", xiprime).replace("XXXX", codeEtat);
        }else {
            this.url = HTTP1+"&_nkw=" + xiprime;

            if (codeEtat!=null && !codeEtat.equals(""))
                url += "&LH_ItemCondition=" + codeEtat;
            if (codeQualite!=null && !codeQualite.equals(""))
                url += "&_sacat=0&%25C3%2589tat%2520de%2520la%2520carte=" + codeQualite;


            //this.url = HTTP1.replace("AAAA", xiprime).replace("XXXX", codeEtat).replace("YYYY", codeQualite);

        }
    }


    public String getURL() {
        setURL();
        return this.url;
    }

    public Document getDoc() {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (Exception e) {
            System.out.println("probleme carteEbay5 ..." + e);

        }
        setSize(doc);
        setNbPages(doc);
        return doc;
    }

    public void setSize(Document doc) {
        //Document doc=getDoc();

        int nbCartes = 0, posEspace = -1;
        try {
            if (doc.select("h1.srp-controls__count-heading").size() != 0) {
                String nb = doc.select("h1.srp-controls__count-heading").text().trim();
                if (!nb.equals("")) {
                    posEspace = nb.indexOf(" ");
                    if (posEspace > 0)
                        this.size = Integer.parseInt(nb.substring(0, posEspace));
                }
            } else if (doc.select("span.rcnt").size() != 0)
                this.size = Integer.parseInt(doc.select("span.rcnt").text());
            else {
                System.out.println("\nnbCartes=" + 0 + "  url:" + url);
            }
        } catch (Exception e) {
            System.out.println("probleme carteEbay7 ..." + e);

            System.out.println("\nnbCartes=" + 0 + "  url:" + url);
        }


    }

    public int getSize() {
        return this.size;
    }

    public void setNbPages(Document doc) {
        Elements listePages = doc.select("li.x-pagination__li");
        //if(listePages.size()!=0)
        //    this.nbPages=listePages.size();
    }

    public int getNbPages() {
        //return this.nbPages;
        return 0;
    }


    protected void save() {
        /*try {
            Statement statement = ApplicationPM.DB.getConnectLOC().createStatement();
            String sql="insert into " + baseDDCarteEbay +"(id,carte_id,rechercheEbay,etat,qualite,site,url,prixMoyen,effectif)  values (?,?,?,?,?,?,?,?,?)";
            //System.out.print(sql);
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.setLong(2, carte.getId());
            preparedStatement.setString(3, rechercheEbay);

            preparedStatement.setString(4, this.etat);
            preparedStatement.setString(5, this.qualite);
            preparedStatement.setString(6, (this.pointCOM) ? ".com" : ".fr");
            preparedStatement.setString(7, this.url);
            preparedStatement.setDouble(8, this.prixMoyen);
            preparedStatement.setInt(9, this.effectif);


            preparedStatement.executeUpdate();
            statement.close();



        } catch (SQLException e) {
            e.printStackTrace();
        }*/


    }

    protected void update() {
        /*try {
            Connection connect = ApplicationPM.DB.getConnectLOC();
            Statement statement = connect.createStatement();
            String sql="update " + baseDDCarteEbay +" set prixMoyen=?, effectif=? where id="+id;
            //System.out.print(sql);
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setDouble(1, this.prixMoyen);
            preparedStatement.setInt(2, this.effectif);

            preparedStatement.executeUpdate();
            statement.close();



        } catch (SQLException e) {
            e.printStackTrace();
        }*/


    }

    public ArrayList<CarteVendue> getCartesVendues() {
        return (ArrayList<CarteVendue>) cartesVendues;
    }

    public void addCarteVendue(CarteVendue carteVendue) {
        cartesVendues.add(carteVendue);
    }

    public String toString() {
        return rechercheEbay + " etat: " + etat + " qualité: " + qualite;
    }

    public void setId(int id) {
        this.id = id;
    }


    public static void creerBDD() {
        /*
        CREATE TABLE c1ajoutCartesJava.CarteEbay ( `id` int NOT NULL AUTO_INCREMENT,
                    `carte_id` int(11) DEFAULT NULL,
                    `rechercheEbay` varchar(100) DEFAULT NULL,
                    `etat` varchar(45) DEFAULT NULL,
                    `qualite` varchar(45) DEFAULT NULL,
                    `site` varchar(4) DEFAULT NULL,
                    `url` varchar(400) DEFAULT NULL,
                    `prixMoyen` double DEFAULT NULL,
                    `effectif` int(4) DEFAULT NULL,

                    PRIMARY KEY (`id`)
                    ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
         */

        /*try {
            //baseDD = app.lstNet2SQL.getSelectedItem().toString();

            Class.forName("com.mysql.jdbc.Driver");
            connect = (Connection) ApplicationPM.DB.getConnectLOC();
            statement = connect.createStatement();

            DatabaseMetaData dbm = connect.getMetaData();
            // statement.executeUpdate("use FX2Adrien;");
            ResultSet tables = dbm.getTables(null, null, baseDDCarteEbay, null);
            if(tables.next()) {
                statement.executeUpdate("DROP TABLE "+baseDDCarteEbay);
            }
            String sql = "CREATE TABLE `" + baseDDCarteEbay + "` ( " +
                    "id int NOT NULL AUTO_INCREMENT," +
                    "`carte_id` int(11) DEFAULT NULL," +
                    "`rechercheEbay` varchar(100) DEFAULT NULL," +
                    "`etat` varchar(45) DEFAULT NULL," +
                    "`qualite` varchar(45) DEFAULT NULL," +
                    "`site` varchar(4) DEFAULT NULL," +
                    "`url` varchar(400) DEFAULT NULL," +
                    "`prixMoyen` double DEFAULT NULL," +
                    "`effectif` int(4) DEFAULT NULL," +

                    "PRIMARY KEY (`id`)" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";

            statement.executeUpdate(sql);//
            statement.close();


            //Cumulation.execute("extarction"+laDateDExecution);
        } catch (Exception ex) {
            ex.printStackTrace();

        }
*/


    }
/*
    public String getNomEBAY() {
        int pos = rechercheEbay.indexOf(" ");
        if (pos > -1)
            nomEBAY = rechercheEbay.substring(0, pos).trim();
        return nomEBAY;
    }


 */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Carte getCarte() {
        return carte;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    public void setCartesVendues(List<CarteVendue> cartesVendues) {
        this.cartesVendues = cartesVendues;
    }
/*
    public void setNomEBAY(String nomEBAY) {
        this.nomEBAY = nomEBAY;
    }
*/
    public String getRechercheEbay() {
        return rechercheEbay;
    }

    public void setRechercheEbay(String rechercheEbay) {
        this.rechercheEbay = rechercheEbay;
    }



    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getQualite() {
        return qualite;
    }

    public void setQualite(String qualite) {
        this.qualite = qualite;
    }


    public String getUrl() {
        return url;
    }



    public void setUrl(String url) {
        this.url = url;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getEffectif() {
        return effectif;
    }

    public void setEffectif(int effectif) {
        this.effectif = effectif;
    }

    public double getPrixMoyen() {
        return prixMoyen;
    }

    public void setPrixMoyen(double prixMoyen) {
        this.prixMoyen = prixMoyen;
    }

    public String getNomEBAY() {
        return nomEBAY;
    }

    public void setNomEBAY(String nomEBAY) {
        this.nomEBAY = nomEBAY;
    }

    public String getCodeEtat() {
        return codeEtat;
    }

    public void setCodeEtat(String codeEtat) {
        this.codeEtat = codeEtat;
    }

    public String getCodeQualite() {
        return codeQualite;
    }

    public void setCodeQualite(String codeQualite) {
        this.codeQualite = codeQualite;
    }

    public boolean isPointFR() {
        return pointFR;
    }

    public void setPointFR(boolean pointFR) {
        this.pointFR = pointFR;
    }

    public boolean isPointCOM() {
        return pointCOM;
    }

    public void setPointCOM(boolean pointCOM) {
        this.pointCOM = pointCOM;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}


