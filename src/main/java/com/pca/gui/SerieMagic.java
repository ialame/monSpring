package com.pca.gui;

import com.pca.model.SerieUS;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by ia on 11/03/2019.
 */
public class SerieMagic extends SerieUS {

    protected ArrayList extensions = new ArrayList<>();
    SerieMagic(){}
    SerieMagic(ResultSet rs){
        mySQL2SerieMagic(rs);
    }

    SerieMagic(int id){
        this.setId(id);
        /*try {
            Connection connect = ApplicationPM.DB.getConnect();
            Statement statement =  connect.createStatement();
            String sql="SELECT * FROM serieMagic where id="+id;
            ResultSet  resultDev = statement.executeQuery(sql);
            if (resultDev.next()){
                mySQL2SerieMagic(resultDev);
            }
            statement.close();
        }catch(Exception e){
            System.out.println("probleme serieMagic ..."+e);

        }*/
    }
    void mySQL2SerieMagic(ResultSet resultDev){
        /*try {
            //id = resultDev.getInt("id");
            //nom = resultDev.getString("nom");
            //name = nom;
            //nomUS = resultDev.getString("nomUS");
            //nomDossier = resultDev.getString("nomDossier");

            Statement statementExtension = ApplicationPM.DB.getConnect().createStatement();
            ResultSet resultExtension = statementExtension.executeQuery("select * from "+ ApplicationPM.DB.getTable_ExtensionMagic() +" where serieMagic_id  = " + getId() + "  ORDER BY id ASC");

            while (resultExtension.next()) {
                ExtensionMagic e = new ExtensionMagic(resultExtension);
                e.setSerieMagic(this);
                extensions.add(e);
            }
            //this.setExtensionsMagic(extensionsMagic);
        }catch(Exception e){
            System.out.println("probleme mySQL2SerieMagic ..."+e);
        }*/
    }

    public void init(int annee){
        String NOM= annee+"";
        this.setId(annee);
        this.setNomFR(NOM);
        //this.setNom(NOM);
        this.setNomUS(NOM);
    }

    public void addExtension(ExtensionMagic extension){
        extensions.add(extension);
    }
    public String toString(){
        return getId()+ " " ;
    }


    void save(String tableSerie){

        /*try{


            Connection connection = ApplicationPM.DB.getConnect();
            Statement statement = connection.createStatement();

            String sql = "insert into `" + tableSerie + "`(id,nom)  values (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeQuery("SET NAMES 'UTF8'");
            preparedStatement.executeQuery("SET CHARACTER SET 'UTF8'");
            //preparedStatement.setInt(1, id);
            //preparedStatement.setString(2, nom);
            //preparedStatement.setString(3, name);
            //preparedStatement.setString(4, nomFR);
            //preparedStatement.setString(5, nomUS);
            //preparedStatement.setString(6, nomDossier);
            //preparedStatement.setInt(7, NbExtensions);
            //preparedStatement.setString(8, Image);

            preparedStatement.executeUpdate();
            statement.close();

        } catch (Exception e1) {
            System.out.println(e1);
        }*/
    }
    public static ArrayList<SerieMagic> getSeriesMagic() {
        ArrayList<SerieMagic> series = new ArrayList<>();
        /*try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = ApplicationPM.DB.getConnect();//
            Statement statement = connect.createStatement();
            String sql = "SELECT id,nom  FROM serieMagic ";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                series.add(new SerieMagic(rs.getInt("id")));
            }

        } catch (Exception exp) {

        }*/
        return series;
    }
    //public String getName(){
    //    return nom;
    //}
}

