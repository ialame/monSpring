package com.pca.gui;//CTRL + SHIFT + O pour générer les imports

//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;

public class Connect {

    public static void main(String[] args) {}
    public ArrayList<Champ> champsjap(int idJap) {
        ArrayList<Champ> ChampsJAP = new ArrayList<>();
 /*       try {

            Connection conn = ApplicationPM.DB.getConnect();

            //Création d'un objet Statement
            Statement state = conn.createStatement();
            //L'objet ResultSet contient le résultat de la requête SQL
            ResultSet result = state.executeQuery("SELECT * FROM cartePokemon where id="+idJap);
            //On récupère les MetaData
            ResultSetMetaData resultMeta = result.getMetaData();

            System.out.println("\n**********************************");
            //On affiche le nom des colonnes
            for (int i = 1; i <= resultMeta.getColumnCount(); i++) {
                if (resultMeta.getColumnName(i).toUpperCase().indexOf("JAP") != -1
                        || resultMeta.getColumnName(i).equals("enNum")
                        || resultMeta.getColumnName(i).equals("enExpansion")
                        || resultMeta.getColumnName(i).equals("HP")
                        || resultMeta.getColumnName(i).equals("Card")
                        ) {
                    ChampsJAP.add(new Champ(i, resultMeta.getColumnName(i)));
                }
            }
            for (Champ c : ChampsJAP)
                System.out.print("\t" + c.titre + "\t *");
            System.out.println("\n**********************************");

            while (result.next()) {
                for (Champ c : ChampsJAP) {
                    c.setValeur(result.getObject(c.col));
                    System.out.print(c);
                }
                System.out.println("\n---------------------------------");

            }

            //result.close();
            state.close();

        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return ChampsJAP;
    }






}