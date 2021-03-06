package com.ia.ebay9.gui;//import javafx.print.Collation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by ia on 11/04/2019.
 */
public class FiltterMagic {

    public static Connection connect = null;
    /**
     *     Regle 1
     */
    public static Statement statement = null;
    public static String   table_ExtensionMagic, table_CarteMagic, table_CarteMagicRuling,table_TokenMagic;
    public static  String strConnexion ;
    public int id=0;
    public int NbCartes,NbImages;

    /**
     *     Regle 1
     */


    public static void split(ArrayList<CarteMagic> cartesMagic){

       for(int i=0;i<cartesMagic.size();i++){
           CarteMagic carteMagic =cartesMagic.get(i);
           String number = cartesMagic.get(i).getNumber();
           if(carteMagic.getLayout().equals("split")){
              carteMagic.setCertifiable(false);
              carteMagic.setFiltre(1);
              CarteMagic cm =  carteMagic.copy();
              cm.setFiltre(1);
              cm.setCertifiable(true);
              while(cartesMagic.get(i+1).getNumber().equals(number)){
                  CarteMagic rs = cartesMagic.get(i+1);
                  rs.setFiltre(1);
                  rs.setCertifiable(false);
                  //cm.setName(cm.getName()+" // "+ rs.getName());
                  //cm.setNom(cm.getNom()+" // "+ rs.getNom());
                  cm.setNomUS(cm.getNomUS()+" // "+ rs.getNomUS());
                  if(cm.getNomFR()!=null && !cm.getNomFR().equals(""))
                      cm.setNomFR(cm.getNomFR()+" // "+ rs.getNomFR());
                  if(cm.getNomJAP()!=null && !cm.getNomJAP().equals(""))
                      cm.setNomJAP(cm.getNomJAP()+" // "+ rs.getNomJAP());
                  if(cm.getNomDE()!=null && !cm.getNomDE().equals(""))
                      cm.setNomDE(cm.getNomDE()+" // "+ rs.getNomDE());
                  if(cm.getNomIT()!=null && !cm.getNomIT().equals(""))
                      cm.setNomIT(cm.getNomIT()+" // "+ rs.getNomIT());
                  if(cm.getNomES()!=null && !cm.getNomES().equals(""))
                      cm.setNomES(cm.getNomES()+" // "+ rs.getNomES());
                  if(cm.getNomJAP()!=null && !cm.getNomJAP().equals(""))
                      cm.setNomJAP(cm.getNomJAP()+" // "+ rs.getNomJAP());
                  if(cm.getColors()!=null && !cm.getColors().equals(""))
                      cm.setColors(cm.getColors()+" // "+ rs.getColors());
                  if(cm.getSide()!=null && !cm.getSide().equals(""))
                      cm.setSide(cm.getSide()+" // "+ rs.getSide());
                  i++;

              }
              i++;
              cartesMagic.add(i,cm);
           }


       }
    }
    /**
     *     Regle 2
     */

    public static void meld(ArrayList<CarteMagic> cartesMagic){
        //Collections.sort(cartesMagic);
        for(int i=0;i<cartesMagic.size();i++) {
            CarteMagic carteMagic = cartesMagic.get(i);
            if(!carteMagic.getLayout().equals("meld")) continue;
            String number = cartesMagic.get(i).getNumber();
            int nb = valNum(number);

            carteMagic.setCertifiable(true);
            carteMagic.setFiltre(2);
            while (nb==valNum(cartesMagic.get(i + 1).getNumber()) ) {
                CarteMagic rs = cartesMagic.get(i + 1);
                rs.setFiltre(2);
                if (carteMagic.getSide().equals("c")) {
                    carteMagic.setCertifiable(false);
                } else
                    rs.setCertifiable(false);
                i++;
            }
        }
    }

    /**
     *     Regle 3 = 1 ?
     */

    public static void flip(ArrayList<CarteMagic> cartesMagic) {
        for (int i = 0; i < cartesMagic.size(); i++) {
            CarteMagic carteMagic = cartesMagic.get(i);
            String number = cartesMagic.get(i).getNumber();
            if (carteMagic.getLayout().equals("flip")) {
                carteMagic.setCertifiable(false);
                carteMagic.setFiltre(3);
                CarteMagic cm = carteMagic.copy();
                cm.setCertifiable(true);
                cm.setFiltre(3);
                while (cartesMagic.get(i + 1).getNumber().equals(number)) {
                    CarteMagic rs = cartesMagic.get(i + 1);
                    rs.setFiltre(3);
                    rs.setCertifiable(false);
                    //cm.setName(cm.getName() + " // " + rs.getName());
                    //cm.setNom(cm.getNom() + " // " + rs.getNom());
                    cm.setNomUS(cm.getNomUS() + " // " + rs.getNomUS());
                    if (cm.getNomFR() != null && !cm.getNomFR().equals(""))
                        cm.setNomFR(cm.getNomFR() + " // " + rs.getNomFR());
                    if (cm.getNomJAP() != null && !cm.getNomJAP().equals(""))
                        cm.setNomJAP(cm.getNomJAP() + " // " + rs.getNomJAP());
                    if (cm.getColors() != null && !cm.getColors().equals(""))
                        cm.setColors(cm.getColors() + " // " + rs.getColors());
                    if (cm.getSide() != null && !cm.getSide().equals(""))
                        cm.setSide(cm.getSide() + " // " + rs.getSide());
                    i++;

                }
                i++;
                cartesMagic.add(i, cm);
            }
        }
    }


    /**
     *     Regle 4  in ('planar','scheme','vanguard')
     */

    public static void planarSchemeVanguard(ArrayList<CarteMagic> cartesMagic){
        for(int i=0;i<cartesMagic.size();i++) {
            CarteMagic carteMagic = cartesMagic.get(i);
            String number = cartesMagic.get(i).getNumber();
            if (carteMagic.getLayout().equals("planar") || carteMagic.getLayout().equals("scheme")|| carteMagic.getLayout().equals("vanguard")) {
                carteMagic.setFiltre(4);
                carteMagic.setCertifiable(false);
            }
        }

    }

    /**
     *     Regle 5 = 3 = 1 ?
     */

    public static void transform(ArrayList<CarteMagic> cartesMagic){
        for(int i=0;i<cartesMagic.size();i++) {
            CarteMagic carteMagic = cartesMagic.get(i);
            String number = cartesMagic.get(i).getNumber();
            if (carteMagic.getLayout().equals("transform")) {
                carteMagic.setCertifiable(false);
                carteMagic.setFiltre(5);
                CarteMagic cm = carteMagic.copy();
                cm.setFiltre(5);
                cm.setCertifiable(true);
                while (cartesMagic.get(i + 1).getNumber().equals(number)) {
                    CarteMagic rs = cartesMagic.get(i + 1);
                    rs.setFiltre(5);
                    rs.setCertifiable(false);
                    //cm.setName(cm.getName() + " // " + rs.getName());
                    //cm.setNom(cm.getNom() + " // " + rs.getNom());
                    cm.setNomUS(cm.getNomUS() + " // " + rs.getNomUS());
                    if (cm.getNomFR() != null && !cm.getNomFR().equals(""))
                        cm.setNomFR(cm.getNomFR() + " // " + rs.getNomFR());
                    if (cm.getNomJAP() != null && !cm.getNomJAP().equals(""))
                        cm.setNomJAP(cm.getNomJAP() + " // " + rs.getNomJAP());
                    if (cm.getColors() != null && !cm.getColors().equals(""))
                        cm.setColors(cm.getColors() + " // " + rs.getColors());
                    if (cm.getSide() != null && !cm.getSide().equals(""))
                        cm.setSide(cm.getSide() + " // " + rs.getSide());
                    i++;

                }
                i++;
                cartesMagic.add(i, cm);
            }


        }

    }

    /**
     *     Regle D1 = 6
     */

    public static void isOversized(ArrayList<CarteMagic> cartesMagic){
        for(int i=0;i<cartesMagic.size();i++) {
            CarteMagic carteMagic = cartesMagic.get(i);
            String number = cartesMagic.get(i).getNumber();
            if (carteMagic.getIsOversized()) {
                carteMagic.setFiltre(6);
                carteMagic.setCertifiable(false);
            }
        }

    }

    /**
     *     Regle D2 = 7
     */

    public static void collectorsEdition(ArrayList<CarteMagic> cartesMagic){
        for(int i=0;i<cartesMagic.size();i++) {
            CarteMagic carteMagic = cartesMagic.get(i);
            String number = cartesMagic.get(i).getNumber();
            if (carteMagic.getExtension().getId()==10 || carteMagic.getExtension().getId()==12 ) {
                carteMagic.setFiltre(7);
                carteMagic.setCertifiable(false);
            }
        }

    }

    public static void imagesDesCartesFiltrees() {
        /*ExtensionMagic em = new ExtensionMagic();
        try {

            Statement statement = connect.createStatement();

            File repertoireSerie = new File("img/cards/magic/US");
            String listeSerie[] = repertoireSerie.list();
            System.out.println("\n");
            String sql2 = "SELECT c.id, e.code , year(e.releaseDate) annee ,c.layout ,c.side ,c.fusionPCA   FROM " + ApplicationPM.DB.getTable_CarteMagic() +" c ,"+ ApplicationPM.DB.getTable_ExtensionMagic() + " e where c.extensionMagic_id=e.id and c.layout <> 'normal' ;";
            ResultSet result = statement.executeQuery(sql2);
            while (result.next()) {
                String id = result.getString("id");
                String code = result.getString("code");
                String annee = result.getString("annee");
                String layout = result.getString("layout");
                String side = result.getString("side");
                String fusionPCA = result.getString("fusionPCA");

                System.out.println("1)  id="+id +"    img/cards/magic/US" + "/" + annee + "/" + code + "/" + id + ".jpg");
                File source = null,destina = null;
                source = new File("img/cards/magic/US" + "/" + annee + "/" + code + "/" + id + ".jpg");
                if(layout.equals("split") || layout.equals("flip"))
                    destina = new File("img/cards/magic/US" + "/" + annee + "/" + code + "/" + fusionPCA + ".jpg");
                else if(layout.equals("transform"))
                    destina = new File("img/cards/magic/US" + "/" + annee + "/" + "code" + "/" + fusionPCA +side+ ".jpg");
                copyFile(source, destina);
            }

            statement.close();
        } catch (Exception e1) {
            System.out.println(e1);
        }*/
    }

    /** copie le fichier source dans le fichier resultat
     * retourne vrai si cela réussit
     */
    public static boolean copyFile(File source, File dest){
        try{
            // Declaration et ouverture des flux
            java.io.FileInputStream sourceFile = new java.io.FileInputStream(source);

            try{
                FileOutputStream destinationFile = null;

                try{
                    destinationFile = new FileOutputStream(dest);

                    // Lecture par segment de 0.5Mo
                    byte buffer[] = new byte[512 * 1024];
                    int nbLecture;

                    while ((nbLecture = sourceFile.read(buffer)) != -1){
                        destinationFile.write(buffer, 0, nbLecture);
                    }
                } finally {
                    destinationFile.close();
                }
            } finally {
                sourceFile.close();
            }
        } catch (IOException e){
            e.printStackTrace();
            return false; // Erreur
        }

        return true; // Résultat OK
    }
public static int  valNum(String number){
    char c;
    StringBuffer sb = new StringBuffer();
    for (int k = 0; k < number.length(); k++) {
        c = number.charAt(k);
        if ('0' <= c && c <= '9')
            sb.append(c);
    }
    int nb=Integer.parseInt(sb.toString());
    return nb;

}


}
