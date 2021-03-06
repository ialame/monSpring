package com.ia.ebay9.gui;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by fxc on 07/12/2018.
 */
public class CarteMKTmagicVendue {
    public int id;
    public CarteCMKTmagic carteMKTmagic;
    public int carteMKTmagic_id;
    public String extension="";
    public String rarete="";
    public String qualite="";
    public String num="";
    public String nameUS="";
    public String nameFR="";
    public String pays="";
    public String langue="";
    public String edition="";
    public String commentaire="";
    public float prixMoyen;
    public float prixUnitaire;
    public int effectif;
    public String vendeur="";
    public String href="";
    public String graphe="";
    public static String baseDDCarteMkt = "carteMkt_MagicAVendre";

    public static String strConnexion="";
    private static PreparedStatement preparedStatement = null;
    CarteMKTmagicVendue(){
    }

    CarteMKTmagicVendue(ResultSet rs){
        requeteCarteMKT(rs);
    }
    CarteMKTmagicVendue(int id){
        try {
            this.id=id;
            Statement statement = (Statement) Cmkt2Magic.connect.createStatement();
            String sql="SELECT * FROM "+ Cmkt2Magic.table_CarteMKTmagicVendue + " where id="+id;
            ResultSet  rs = statement.executeQuery(sql);
            if (rs.next()){
                requeteCarteMKT(rs);
                statement.close();
            }
        }catch(Exception e){
            System.out.println("probleme extension  ..."+e);

        }
    }

    void requeteCarteMKT(ResultSet  rs){
        try {
            carteMKTmagic_id = rs.getInt("carte_id");
            //carteMKTmagic = new CarteMKTmagic(carteMKTmagic_id);

            extension = rs.getString("extension");
            rarete = rs.getString("rarete");
            qualite = rs.getString("qualite");

            num = rs.getString("num");
            nameUS = rs.getString("nameUS");
            nameFR = rs.getString("nameFR");
            pays = rs.getString("pays");
            langue = rs.getString("langue");
            commentaire = rs.getString("commentaire");
            prixMoyen = rs.getFloat("prixMoyen");
            prixUnitaire = rs.getFloat("prixUnitaire");
            effectif = rs.getInt("effectif");
            vendeur = rs.getString("vendeur");

        }catch(Exception e){
            System.out.println("probleme extension0 ..."+e);

        }
    }
    public void setExtension(String extension){ this.extension=extension; }
    public String getExtension(){  return extension; }
    public void setId(int id){ this.id=id;}
    public int getId(){ return id;}
    public void setCarteMKTmagic_id(int carte_id){ this.carteMKTmagic_id=carte_id;}
    public int getCarteMKTmagic_id(){ return carteMKTmagic_id;}
    public void setEffectif(int effectif){ this.effectif=effectif;}
    public int getEffectif(){ return effectif;}
    public void setRarete(String rarete){ this.rarete=rarete; }
    public String getRarete(){ return rarete; }
    public void setQualite(String qualite){ this.qualite=qualite; }
    public String getQualite(){ return qualite; }
    public void setNum(String enNum){  this.num=num; }
    public String getNum(){  return num; }
    public void setNameUS(String nameUS){ this.nameUS=nameUS; }
    public String getNameUS(){ return nameUS; }
    public void setNameFR(String nameFR){ this.nameFR=nameFR; }
    public String getNameFR(){ return nameFR; }
    public void setPays(String pays){ this.pays=pays; }
    public String getPays(){ return pays; }

    public void setLangue(String langue){ this.langue=langue; }
    public String getLangue(){ return langue; }
    public void setEdition(String edition){ this.edition=edition; }
    public String getEdition(){ return edition; }
    public void setCommentaire(String commentaire){ this.commentaire=commentaire; }
    public String getCommentaire(){ return commentaire; }
    public void setPrixMoyen(float prixMoyen){ this.prixMoyen=prixMoyen; }
    public float getPrixMoyen(){ return prixMoyen; }
    public void setPrixUnitaire(float prixUnitaire){ this.prixUnitaire=prixUnitaire; }
    public float getPrixUnitaire(){ return prixUnitaire; }


    public void setVendeur(String vendeur){ this.vendeur=vendeur; }
    public String getVendeur(){ return vendeur; }

    public void setHref(String href){ this.href=href; }
    public String getHref(){ return href; }

    void save(){
        try{
            Statement statement = Cmkt2Magic.connect.createStatement();

            id = this.getMaxId()+1;
            String sql = "insert into `" + Cmkt2Magic.table_CarteMKTmagicVendue + "`(id,carteMKTmagic_id,extension,rarete,qualite,num," +
                    " nameUS,nameFR,pays,langue,commentaire,prixMoyen,prixUnitaire,effectif,vendeur,href)  " +//,graphe
                    "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = Cmkt2Magic.connect.prepareStatement(sql);
            preparedStatement.executeQuery("SET NAMES 'UTF8'");
            preparedStatement.executeQuery("SET CHARACTER SET 'UTF8'");
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, carteMKTmagic_id);

            preparedStatement.setBytes(3, extension.getBytes("UTF-8"));
            preparedStatement.setBytes(4, rarete.getBytes("UTF-8"));

            preparedStatement.setBytes(5, qualite.getBytes("UTF-8"));
            preparedStatement.setBytes(6, num.getBytes("UTF-8"));
            preparedStatement.setBytes(7, nameUS.getBytes("UTF-8"));
            preparedStatement.setBytes(8, nameFR.getBytes("UTF-8"));
            preparedStatement.setBytes(9, pays.getBytes("UTF-8"));
            preparedStatement.setBytes(10, langue.getBytes("UTF-8"));
            preparedStatement.setBytes(11, commentaire.getBytes("UTF-8"));
            preparedStatement.setFloat(12, prixMoyen);
            preparedStatement.setFloat(13, prixUnitaire);
            preparedStatement.setFloat(14, effectif);
            preparedStatement.setString(15, vendeur);
            preparedStatement.setString(16, href );
            //preparedStatement.setString(17, graphe );
            preparedStatement.executeUpdate();
            statement.close();

        } catch (Exception e1) {
            System.out.println(e1);
        }
    }


    public static void creerBDD(){

        try {

            Statement statement = Cmkt2Magic.connect.createStatement();

            DatabaseMetaData dbm = Cmkt2Magic.connect.getMetaData();
            // statement.executeUpdate("use FX2Adrien;");
            ResultSet tables = dbm.getTables(null, null, Cmkt2Magic.table_CarteMKTmagicVendue, null);
            if(tables.next()) {
                statement.executeUpdate("DROP TABLE "+Cmkt2Magic.table_CarteMKTmagicVendue);
            }


            String sql = "CREATE TABLE `" + Cmkt2Magic.table_CarteMKTmagicVendue + "` ( " +
                    "id int NOT NULL AUTO_INCREMENT," +
                    "`carteMKTmagic_id` int(11) DEFAULT NULL," +
                    "`nameUS` varchar(100) DEFAULT NULL," +
                    "`nameFR` varchar(100) DEFAULT NULL," +
                    "`extension` varchar(100) DEFAULT NULL," +
                    "`rarete` varchar(45) DEFAULT NULL," +
                    "`qualite` varchar(45) DEFAULT NULL," +
                    "`num` varchar(45) DEFAULT NULL," +
                    "`pays` varchar(45) DEFAULT NULL," +
                    "`langue` varchar(45) DEFAULT NULL," +
                    "`edition` varchar(45) DEFAULT NULL," +
                    "`commentaire` varchar(145) DEFAULT NULL," +
                    "`prixMoyen` double DEFAULT NULL," +
                    "`prixUnitaire` double DEFAULT NULL," +
                    "`effectif` int(4) DEFAULT NULL," +
                    "`vendeur` varchar(100) DEFAULT NULL," +
                    "`href` varchar(100) DEFAULT NULL," +
                    //"`graphe` varchar(15000) DEFAULT NULL," +
                    "PRIMARY KEY (`id`)" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=`utf8` COLLATE=`utf8_general_ci` ROW_FORMAT=COMPRESSED;";


            statement.executeUpdate(sql);//
            statement.close();


            //Cumulation.execute("extarction"+laDateDExecution);
        } catch (Exception ex) {
            ex.printStackTrace();

        }



    }


    public String toString(){
        return nameFR + " "+extension + " " + num;
    }


    public void determination(){


    }


    public static int getMaxId(){
        int idCarte=0;
        try {

            Statement statement = Cmkt2Magic.connect.createStatement();
            String sql2 = "select max(id) as max from  " + Cmkt2Magic.table_CarteMKTmagicVendue;
            ResultSet resultMax = statement.executeQuery(sql2);
            if (resultMax.next()) {
                idCarte=resultMax.getInt("max") ;
            }
            statement.close();

        } catch (Exception e1) {
            System.out.println(e1);

        }
        return idCarte;
    }






}

