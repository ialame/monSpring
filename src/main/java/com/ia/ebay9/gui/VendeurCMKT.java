package com.ia.ebay9.gui;

import java.sql.*;
import java.time.LocalDate;

/**
 * Created by ia on 31/01/2019.
 */

public class VendeurCMKT {
    public String raisonSociale,rue,ville,telephone;
    public String name;
    public String adresse;
    public String cp;
    public String pays;
    public String tel;
    public String mail;
    public String boutique;
    public String actif,date,disponibles;
    public String derniereLigne2;
    public int achats,ventes,articlesVendus;
    public int nbCartes;
    public LocalDate enregistreDepuis;


    public VendeurCMKT() {
    }
    public VendeurCMKT(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public static void creerBDD(){

        try {

            Statement statement = Cmkt2Magic.connect.createStatement();

            DatabaseMetaData dbm = Cmkt2Magic.connect.getMetaData();
            // statement.executeUpdate("use FX2Adrien;");
            ResultSet tables = dbm.getTables(null, null, Cmkt2Magic.table_VendeurMKTmagic, null);
            if(tables.next()) {
                statement.executeUpdate("DROP TABLE "+Cmkt2Magic.table_VendeurMKTmagic);
            }


            String sql = "CREATE TABLE `" + Cmkt2Magic.table_VendeurMKTmagic + "` ( " +
                    "id int NOT NULL AUTO_INCREMENT," +
                    "`raisonSociale` varchar(100) DEFAULT NULL," +
                    "`enregistreDepuis` varchar(100) DEFAULT NULL," +
                    "`name` varchar(100) DEFAULT NULL," +
                    "`rue` varchar(100) DEFAULT NULL," +
                    "`ville` varchar(45) DEFAULT NULL," +
                    "`pays` varchar(45) DEFAULT NULL," +
                    "`telephone` varchar(45) DEFAULT NULL," +
                    "`mail` varchar(45) DEFAULT NULL," +
                    "`achats` int DEFAULT NULL," +
                    "`ventes` int DEFAULT NULL," +
                    "`articlesVendus` int DEFAULT NULL," +
                    "`nbCartes` int DEFAULT NULL," +
                    "PRIMARY KEY (`id`)" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=`utf8` COLLATE=`utf8_general_ci` ROW_FORMAT=COMPRESSED;";


            statement.executeUpdate(sql);//
            statement.close();


            //Cumulation.execute("extarction"+laDateDExecution);
        } catch (Exception ex) {
            ex.printStackTrace();

        }

    }

    void save(){
        try{
            Statement statement = Cmkt2Magic.connect.createStatement();


            String sql = "insert into `" + Cmkt2Magic.table_VendeurMKTmagic + "`(raisonSociale,name,rue,ville,pays," +
                    " telephone,mail,achats,ventes, articlesVendus, nbCartes,enregistreDepuis)  values (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = Cmkt2Magic.connect.prepareStatement(sql);
            preparedStatement.executeQuery("SET NAMES 'UTF8'");
            preparedStatement.executeQuery("SET CHARACTER SET 'UTF8'");
            //preparedStatement.setInt(1, id);
            preparedStatementOriental(preparedStatement,1, raisonSociale);

            preparedStatementOriental(preparedStatement,2, name);
            preparedStatementOriental(preparedStatement,3, rue);

            preparedStatementOriental(preparedStatement,4, ville);
            preparedStatementOriental(preparedStatement,5, pays);
            preparedStatementOriental(preparedStatement,6, telephone);
            preparedStatementOriental(preparedStatement,7, mail);
            preparedStatement.setInt(8, achats);
            preparedStatement.setInt(9, ventes);
            preparedStatement.setInt(10, articlesVendus);
            preparedStatement.setInt(11, nbCartes);
            preparedStatement.setDate(12, Date.valueOf(enregistreDepuis));

            //preparedStatement.setString(17, graphe );
            preparedStatement.executeUpdate();
            statement.close();

        } catch (Exception e1) {
            System.out.println(e1);
        }
    }

    public static int existeDansBDD(String raisonSociale){
        int idVendeur=-1;
        try {
            Statement statement = Cmkt2Magic.connect.createStatement();
            String sql2 = "select id from  " + Cmkt2Magic.table_VendeurMKTmagic +" where raisonSociale = '"+ raisonSociale+"';";
            ResultSet result = statement.executeQuery(sql2);
            if (result.next()) {
                idVendeur=result.getInt("id") ;
            }
            statement.close();

        } catch (Exception e1) {
            System.out.println(e1);

        }
        return idVendeur;
    }


    void preparedStatementOriental(PreparedStatement preparedStatement, int i, String value) {
        try {
            if (value == null)
                preparedStatement.setString(i, value);
            else
                preparedStatement.setBytes(i, value.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString(){
        return "Raison sociale : " +raisonSociale+ " Nom : " + name +  "Rue : "+ rue +" Ville : "+ville+  " pays : "+ pays  +"   " + actif + " depuis : "+ date + " ventes : "+ ventes+ " nbCartes : "+nbCartes + " disponibles : " + disponibles+ " Téléphone : "+telephone;
    }

}


