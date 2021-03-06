package com.ia.ebay9.gui;

import com.ia.ebay9.entity.*;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ExtensionConcatenee extends ExtensionJAP {
    public int extensionJap_id;
    ExtensionConcatenee(){}
    ExtensionConcatenee(int id,String idPCA,String nomTableauBulbapedia,String PremierNumero ,int extensionJap_id){
        /*this.id = id;
        this.idPCA = idPCA;
        this.nomTableauBulbapedia = nomTableauBulbapedia;
        this.PremierNumero = PremierNumero;
        this.extensionJap_id = extensionJap_id;
        this.nom=nomTableauBulbapedia;*/
    }
    ExtensionConcatenee(int id){
        /*try {
            this.id=id;
            Connection connect = com.ia.ebay9.gui.ApplicationPM.DB.getConnect();//
            Statement statement = connect.createStatement();
            String sql="SELECT * FROM `" + com.ia.ebay9.gui.ApplicationPM.DB.getTable_ExtensionConcatenee() + "` where id="+id;
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()){
                requeteExtensionJAP(rs);

            }
        }catch(Exception e){
            System.out.println("probleme extension  ..."+e);

        }*/
    }
    void requeteExtensionJAP(ResultSet  rs){
        /*try {
            id = rs.getInt("id");
            idPCA = rs.getString("idPCA");
            nomTableauBulbapedia = rs.getString("nomTableauBulbapedia");
            PremierNumero = rs.getString("premierNumero");
            extensionJap_id = rs.getInt("extensionJap_id");
        }catch(Exception e){
            System.out.println("probleme extensionJAP ......"+e);

        }*/
    }

    public int getExtensionJap_id() {
        return extensionJap_id;
    }

    public void setExtensionJap_id(int extensionJap_id) {
        this.extensionJap_id = extensionJap_id;
    }

    void save(){
        /*try{
            Connection connect = com.ia.ebay9.gui.ApplicationPM.DB.getConnect();//
            Statement statement = connect.createStatement();

            String sql2 = "select max(id) as max from  " + com.ia.ebay9.gui.ApplicationPM.DB.getTable_ExtensionConcatenee();
            ResultSet rs = statement.executeQuery(sql2);
            if (rs.next()) {
                this.id=rs.getInt("max")+1 ;
            }
            String sql = "insert into `" + com.ia.ebay9.gui.ApplicationPM.DB.getTable_ExtensionConcatenee() + "`(id,idPCA, extensionJap_id,nomTableauBulbapedia,premierNumero)  values (?,?,?,?,?)";
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.executeQuery("SET NAMES 'UTF8'");
            preparedStatement.executeQuery("SET CHARACTER SET 'UTF8'");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, idPCA);
            preparedStatement.setInt(3, extensionJap_id);
            preparedStatement.setBytes(4, nomTableauBulbapedia.getBytes("UTF-8"));
            preparedStatement.setString(5, PremierNumero);
            preparedStatement.executeUpdate();
            statement.close();
        } catch (Exception e1) {
            System.out.println(e1);
        }

*/

    }
    public static ArrayList<ExtensionConcatenee> getTableaux(int extensionJap_id){

        ArrayList<ExtensionConcatenee> AL = new ArrayList<>();
        /*try {
            Connection connect = ApplicationPM.DB.getConnect();//
            Statement statement = connect.createStatement();
            String sql="SELECT * FROM `" + ApplicationPM.DB.getTable_ExtensionConcatenee() + "` where extensionJap_id="+extensionJap_id;
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt("id");
                String idPCA = rs.getString("idPCA");
                String nomTableauBulbapedia = rs.getString("nomTableauBulbapedia");
                String PremierNumero = rs.getString("premierNumero");
                ExtensionConcatenee ec = new ExtensionConcatenee(id,idPCA,nomTableauBulbapedia, PremierNumero,extensionJap_id);
                AL.add(ec);
            }

        }catch(Exception e){
            System.out.println("probleme extension  aaa ..."+e);

        }*/
        return AL;
    }
}
