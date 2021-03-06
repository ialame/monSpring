package com.pca.gui; /**
 * Created by ia on 09/05/2019.
 */

import com.pca.model.Carte;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.sql.ResultSet;
import java.time.LocalDate;

public class CarteMKT {
    public int id;
    public String carteType = "MAGIC";
    public Carte carte;
    public int carte_id;
    public static String baseDDCarteEbay;
    public LocalDate date;
    public int size;
    public int effectif=0;
    public double offre=0;
    public int nbPages;


    CarteMKT(int  id){
        /*try {
            Statement statementLoc = ApplicationPM.DB.getConnect().createStatement();
            String sql="SELECT * FROM " + baseDDCarteEbay +" where id="+id;
            ResultSet  rs = statementLoc.executeQuery(sql);
            if(rs.next())
                getCarteEbay(rs);

        }catch(Exception e){
            System.out.println("probleme carteEbay 2 ..."+e);

        }*/
    }
    CarteMKT(Carte carte, String etat, String qualite, int pointFR, int maxCarteEbay){
        /*try {
            this.carte=carte;

            Statement statementLoc = ApplicationPM.DB.getConnect().createStatement();
            String sql="SELECT * FROM " + baseDDCarteEbay +" where carte_id="+carte.getId() ;
            ResultSet  rs = statementLoc.executeQuery(sql);
            if(rs.next()) {
                try {
                    this.id = rs.getInt("id");
                    this.carte_id = rs.getInt("carte_id");
                    this.offre = rs.getDouble("prixMoyen");
                    this.effectif = rs.getInt("effectif");
                    //int pos=rechercheEbay.indexOf(" ");
            *//*if(pos > -1)
                nomEBAY=rechercheEbay.substring(0,pos).trim();*//*

                } catch (Exception exc) {
                    System.out.println("probleme carteEbay ..." + exc);


                }
            }else{// il faut la créer !

                this.id = ++Net2mysql.maxCarteEbay;
                this.carte_id = carte.getId() ;
                save();
            }
            rs.close();
            statementLoc.close();


        }catch(Exception e){
            System.out.println("probleme carteEbay 2 ..."+e);

        }*/
    }

    void getCarteEbay(ResultSet rs){
        try{
            this.id = rs.getInt("id");
            this.carte_id = rs.getInt("carte_id");
            this.offre = rs.getDouble("prixMoyen");
            this.effectif = rs.getInt("effectif");
            //int pos=rechercheEbay.indexOf(" ");
            /*if(pos > -1)
                nomEBAY=rechercheEbay.substring(0,pos).trim();*/

        }catch(Exception exc){
            System.out.println("probleme carteEbay ..."+exc);


        }

    }



    public int getSize(){
        return this.size;
    }
    public void setNbPages(Document doc){
        Elements listePages = doc.select("li.x-pagination__li");
        if(listePages.size()!=0)
            this.nbPages=listePages.size();
    }
    public int getNbPages(){
        return this.nbPages;
    }


    protected void save(){
        /*try {
            Connection connect = ApplicationPM.DB.getConnect();
            Statement statement = connect.createStatement();
            String sql="insert into " + baseDDCarteEbay +"(id,carte_id,rechercheEbay,etat,qualite,site,url,prixMoyen,effectif)  values (?,?,?,?,?,?,?,?,?)";
            //System.out.print(sql);
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.setLong(2, carte.getId());
            preparedStatement.setDouble(8, this.offre);
            preparedStatement.setInt(9, this.effectif);


            preparedStatement.executeUpdate();
            statement.close();



        } catch (SQLException e) {
            e.printStackTrace();
        }

*/
    }
    protected void update(){
        /*try {
            Connection connect = ApplicationPM.DB.getConnect();
            Statement statement = connect.createStatement();
            String sql="update " + baseDDCarteEbay +" set prixMoyen=?, effectif=? where id="+id;
            //System.out.print(sql);
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setDouble(1, this.offre);
            preparedStatement.setInt(2, this.effectif);

            preparedStatement.executeUpdate();
            statement.close();



        } catch (SQLException e) {
            e.printStackTrace();
        }
*/

    }

    public static void creerBDD(){

        /*try {
            //baseDD = app.lstNet2SQL.getSelectedItem().toString();

            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = (Connection) ApplicationPM.DB.getConnect();
            Statement statement = connect.createStatement();

            DatabaseMetaData dbm = connect.getMetaData();
            // statement.executeUpdate("use FX2Adrien;");
            ResultSet tables = dbm.getTables(null, null, baseDDCarteEbay, null);
            if(tables.next()) {
                statement.executeUpdate("DROP TABLE "+baseDDCarteEbay);
            }
            String sql = "CREATE TABLE `" + baseDDCarteEbay + "` ( " +
                    "id int NOT NULL AUTO_INCREMENT," +
                    "`carte_id` int(11) DEFAULT NULL," +
                    "`vendeur` varchar(100) DEFAULT NULL," +
                    "`info` varchar(400) DEFAULT NULL," +
                    "`offre` double DEFAULT NULL," +
                    "`date` date DEFAULT NULL," +
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

}
