package com.ia.ebay9.gui;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;


/**
 * Created by ia on 11/09/2019.
 */
public class Etat2Detail {
    public static Connection connect = null ;
    public static Statement statement1 = null,statement2 = null;
    int id;
    /*public LocalDateTime paye, attentePaypal,receptionne,recupere,note,certifie,prepare,envoye,descellee,receptionClient;
    public boolean mail_paye,mail_receptionne,mail_note,mail_certifie,mail_envoye;
    public LocalDateTime paye2,certifie2,voir;
    public int sav_id;
    public boolean mail_recupere;
    public int noteur1_id,noteur2_id,noteur_id;
    public LocalDateTime note1,note2;
    public int recuperateur_id,receptionneur_id,certificateur_id,envoyeur_id,voyeur_id,certificateur2_id,descelleur_id,preparateur_id;
*///////////////////////////////////////////////////
    //public int cmd_id;
    //int sav_id;
    public int user_id;
    public Date date;
    public int status,statusCmd=0;
    public int nbCartes;
    public int cartCert_id;


    final int  STATUS_A_RECEP = 1; // Payée => client
    final int  STATUS_A_RECUP = 10; // Réceptionnée => receptionneur
    final int  STATUS_A_NOTER = 2; // Récupérée => recupérateur
    final int  STATUS_A_NOTER2 = 20; // Note 1 => noteur1
    final int  STATUS_A_NOTER3 = 21; // Note 2 => noteur2
    final int  STATUS_A_CERTIFIER = 3; // Notée => noteur
    final int  STATUS_A_PREPARER = 4; // Certifiée => certificateur, certificateur2
    final int  STATUS_A_DISTRIBUER = 41; // Préparée => preparateur, si parkage
    final int  STATUS_A_ENVOYER = 42; // Préparée => preparateur,  sinon
    final int  STATUS_ENVOYE = 5; // Envoyée
    final int  STATUS_A_VOIR = 6; // Mise dans 'A voir' ; voir => voyeur
    final int  STATUS_A_DESCELLER = 7; // Mise dans 'A desceller'; descellee => descelleur
    final int  STATUS_DESCELLER = 71; // N'exite pas
    final int  STATUS_RECU_CLIENT = 8; // receptionClient par client_id
    final String  database_driver="jdbc:mysql"; // jdbc:mysql
    final String  database_host="vps511329.ovh.net";// # vps511329.ovh.net # 192.168.1.21 # 127.0.0.1
    final String  database_port="3306";// # c1Ecommerce2 # 3306
    final String  database_name="c1Ecommerce2";// #c1brancheDevelop3 #c1brancheMagic  # c1Ecommerce2  # FX2Adrien # c1brancheDevelop2
    final String  database_user="c1ia";// # c1userdev  # c1ia #c1userdev root
    final String  database_password= "ASestFolle?!";// # oxAnn123321 # ASestFolle?!
    String strConnexion = database_driver+"://" + database_host + ":"+database_port+"/"+database_name+"?user="+database_user+(database_password==null?"":"&password="+database_password)+"&characterEncoding=UTF-8";



    Etat2Detail(){
    }

    void etat(){
        try {
            connect = DriverManager.getConnection(strConnexion);  // A T T E N T I O N   loc ou pas loc ?
            Statement statement = connect.createStatement();
            Statement statement2 = connect.createStatement();
            ResultSet rs = statement.executeQuery("select * from Etat");
            while (rs.next()) {
                LocalDateTime paye=null, attentePaypal=null,receptionne=null,recupere=null,
                        note=null,certifie=null,prepare=null,envoye=null,descellee=null,receptionClient=null;

                boolean mail_paye,mail_receptionne,mail_note,mail_certifie,mail_envoye;
                LocalDateTime paye2=null,certifie2=null,voir=null;
                int cmd_id;
                int sav_id;
                boolean mail_recupere;
                int noteur1_id,noteur2_id,noteur_id;
                LocalDateTime note1=null,note2=null;
                int recuperateur_id,receptionneur_id,certificateur_id,envoyeur_id,voyeur_id,certificateur2_id,descelleur_id,preparateur_id;

                LocalDateTime date = LocalDateTime.MIN;
                statusCmd=0;


                cmd_id=rs.getInt("cmd_id");
                //if (cmd_id!=1667)continue;

                sav_id=rs.getInt("sav_id");




                if(rs.getString("paye")!=null)
                    paye = rs.getTimestamp("paye").toLocalDateTime();
                //attentePaypal =rs.getTimestamp("attentePaypal").toLocalDateTime();
                if(rs.getString("receptionne")!=null)
                    receptionne = rs.getTimestamp("receptionne").toLocalDateTime();
                if(rs.getString("recupere")!=null)
                    recupere = rs.getTimestamp("recupere").toLocalDateTime();
                if(rs.getString("note")!=null)
                    note = rs.getTimestamp("note").toLocalDateTime();
                if(rs.getString("certifie")!=null)
                    certifie = rs.getTimestamp("certifie").toLocalDateTime();
                if(rs.getString("prepare")!=null)
                    prepare = rs.getTimestamp("prepare").toLocalDateTime();
                if(rs.getString("envoye")!=null)
                    envoye = rs.getTimestamp("envoye").toLocalDateTime();
                if(rs.getString("receptionClient")!=null)
                    receptionClient = rs.getTimestamp("receptionClient").toLocalDateTime();
                mail_paye = rs.getBoolean("mail_paye");
                mail_receptionne = rs.getBoolean("mail_receptionne");
                mail_note = rs.getBoolean("mail_note");
                mail_certifie = rs.getBoolean("mail_certifie");
                mail_envoye = rs.getBoolean("mail_envoye");
                if(rs.getString("descellee")!=null)
                    descellee = rs.getTimestamp("descellee").toLocalDateTime();
                if(rs.getString("paye2")!=null)
                    paye2 = rs.getTimestamp("paye2").toLocalDateTime();
                if(rs.getString("certifie2")!=null)
                    certifie2 = rs.getTimestamp("certifie2").toLocalDateTime();
                if(rs.getString("voir")!=null)
                    voir = rs.getTimestamp("voir").toLocalDateTime();

                //sav_id=rs.getInt("sav_id");
                mail_recupere = rs.getBoolean("mail_recupere");
                noteur1_id=rs.getInt("noteur1_id");
                noteur2_id=rs.getInt("noteur2_id");
                noteur_id=rs.getInt("noteur_id");
                if(rs.getString("note1")!=null)
                    note1 = rs.getTimestamp("note1").toLocalDateTime();
                if(rs.getString("note2")!=null)
                    note2 = rs.getTimestamp("note2").toLocalDateTime();

                recuperateur_id=rs.getInt("recuperateur_id");
                receptionneur_id=rs.getInt("receptionneur_id");
                certificateur_id=rs.getInt("certificateur_id");
                envoyeur_id=rs.getInt("envoyeur_id");
                voyeur_id=rs.getInt("voyeur_id");
                certificateur2_id=rs.getInt("certificateur2_id");
                descelleur_id=rs.getInt("descelleur_id");
                preparateur_id=rs.getInt("preparateur_id");

                ResultSet rs2=null;
                if(sav_id==0)
                    rs2 = statement2.executeQuery("select * from Cmd where id="+cmd_id);
                else
                    rs2 = statement2.executeQuery("select * from sav where id="+sav_id);

                while (rs2.next()) {
                    user_id = rs2.getInt("client_id");
                }

                if(user_id == 0)continue;
                rs2 = statement2.executeQuery("select * from client where id="+user_id);

                if(!rs2.next()) continue;

                if(sav_id==0)
                    rs2 = statement2.executeQuery("select * from CartCert where cmd_id="+cmd_id);
                else
                    rs2 = statement2.executeQuery("select * from CartCert where sav_id="+sav_id);

                ArrayList<Integer> cartcerts = new ArrayList<>();
                while (rs2.next()) {
                    id = rs2.getInt("id");
                    cartcerts.add(id);
                }
                String sql = "insert into DetailCmd(cmd_id, user_id ,date, status, nbCartes, cartCert_id, sav_id) " +
                        " values (?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = connect.prepareStatement(sql);

                if(cmd_id==0) {
                    preparedStatement.setString(1, null);
                    preparedStatement.setInt(7, sav_id);
                }else {
                    preparedStatement.setInt(1, cmd_id);
                    preparedStatement.setString(7, null);
                }



                preparedStatement.setString(2, null);

                preparedStatement.setInt(5, cartcerts.size());
                preparedStatement.setString(6, null);
                if(cmd_id==1)
                    cmd_id=cmd_id;

                if(paye!=null){
                    status = STATUS_A_RECEP;
                    //preparedStatement.setInt(2, user_id);
                    preparedStatement.setTimestamp(3, Timestamp.valueOf(paye));
                    preparedStatement.setInt(4, status);
                    preparedStatement.setInt(5, cartcerts.size());
                    preparedStatement.setString(6, null);
                    //if(paye.isAfter(date)){
                        date=paye;
                        statusCmd=status;
                    //}
                    preparedStatement.executeUpdate();

                }
                if(receptionne!=null){
                    status = STATUS_A_RECUP;
                    if(receptionneur_id!=0)
                        preparedStatement.setInt(2, receptionneur_id);
                    preparedStatement.setTimestamp(3, Timestamp.valueOf(receptionne));
                    preparedStatement.setInt(4, status);
                    preparedStatement.setInt(5, cartcerts.size());
                    preparedStatement.setString(6, null);
                    //if(receptionne.isAfter(date)){
                        date=receptionne;
                        statusCmd=status;
                    //}
                    preparedStatement.executeUpdate();

                }
                if(recupere!=null){
                    status = STATUS_A_NOTER;
                    if(recuperateur_id!=0)
                        preparedStatement.setInt(2, recuperateur_id);
                    preparedStatement.setTimestamp(3, Timestamp.valueOf(recupere));
                    preparedStatement.setInt(4, status);
                    preparedStatement.setInt(5, cartcerts.size());
                    preparedStatement.setString(6, null);
                    //if(recupere.isAfter(date)){
                        date=recupere;
                        statusCmd=status;
                    //}
                    preparedStatement.executeUpdate();

                }
                if(note1!=null){
                    status = STATUS_A_NOTER2;
                    if(noteur1_id!=0)
                        preparedStatement.setInt(2, noteur1_id);
                    preparedStatement.setTimestamp(3, Timestamp.valueOf(note1));
                    preparedStatement.setInt(4, status);
                    preparedStatement.setInt(5, cartcerts.size());
                    preparedStatement.setString(6, null);
                    preparedStatement.executeUpdate();
                    preparedStatement.setString(5, null);
                    //if(note1.isAfter(date)){
                        date=note1;
                        statusCmd=status;
                    //}
                    for(int cs : cartcerts) {
                        preparedStatement.setInt(6, cs);
                        preparedStatement.executeUpdate();
                    }
                }

                if(note2!=null){
                    status = STATUS_A_NOTER3;
                    if(noteur2_id!=0)
                        preparedStatement.setInt(2, noteur2_id);
                    preparedStatement.setTimestamp(3, Timestamp.valueOf(note2));
                    preparedStatement.setInt(4, status);
                    preparedStatement.setInt(5, cartcerts.size());
                    preparedStatement.setString(6, null);
                    preparedStatement.executeUpdate();
                    preparedStatement.setString(5, null);
                   // if(note2.isAfter(date)){
                        date=note2;
                        statusCmd=status;
                   // }
                    for(int cs : cartcerts) {
                        preparedStatement.setInt(6, cs);
                        preparedStatement.executeUpdate();
                    }
                }

                if(note!=null){
                    status = STATUS_A_CERTIFIER;
                    if(noteur_id!=0)
                        preparedStatement.setInt(2, noteur_id);
                    preparedStatement.setTimestamp(3, Timestamp.valueOf(note));
                    preparedStatement.setInt(4, status);
                    preparedStatement.setInt(5, cartcerts.size());
                    preparedStatement.setString(6, null);
                    //if(note.isAfter(date)){
                        date=note;
                        statusCmd=status;
                    //}
                    preparedStatement.executeUpdate();
                    preparedStatement.setString(5, null);
                    for(int cs : cartcerts) {
                        preparedStatement.setInt(6, cs);
                        preparedStatement.executeUpdate();
                    }
                    //preparedStatement.executeUpdate();

                }
                if(certifie!=null){
                    status = STATUS_A_PREPARER;
                    if(certificateur_id!=0)
                        preparedStatement.setInt(2, certificateur_id);
                    preparedStatement.setTimestamp(3, Timestamp.valueOf(certifie));
                    preparedStatement.setInt(4, status);
                    preparedStatement.setInt(5, cartcerts.size());
                    //if(certifie.isAfter(date)){
                        date=certifie;
                        statusCmd=status;
                    //}
                    preparedStatement.setString(6, null);
                    preparedStatement.executeUpdate();

                }

                if(certifie2!=null){
                    status = STATUS_A_DISTRIBUER; // ou STATUS_A_ENVOYER
                    if(certificateur2_id!=0)
                        preparedStatement.setInt(2, certificateur2_id);
                    preparedStatement.setTimestamp(3, Timestamp.valueOf(certifie2));
                    preparedStatement.setInt(4, status);
                    preparedStatement.setInt(5, cartcerts.size());
                    preparedStatement.setString(6, null);
                    //if(prepare.isAfter(date)){
                    date=certifie2;
                    statusCmd=status;
                    //}
                    preparedStatement.executeUpdate();
                }

                if(prepare!=null){
                    status = STATUS_A_ENVOYER; // ou STATUS_A_ENVOYER
                    if(preparateur_id!=0)
                        preparedStatement.setInt(2, preparateur_id);
                    preparedStatement.setTimestamp(3, Timestamp.valueOf(prepare));
                    preparedStatement.setInt(4, status);
                    preparedStatement.setInt(5, cartcerts.size());
                    preparedStatement.setString(6, null);
                    //if(prepare.isAfter(date)){
                        date=prepare;
                        statusCmd=status;
                    //}
                    preparedStatement.executeUpdate();

                }
                if(envoye!=null){
                    status = STATUS_ENVOYE;
                    if(envoyeur_id!=0)
                        preparedStatement.setInt(2, envoyeur_id);
                    preparedStatement.setTimestamp(3, Timestamp.valueOf(envoye));
                    preparedStatement.setInt(4, status);
                    preparedStatement.setInt(5, cartcerts.size());
                    preparedStatement.setString(6, null);
                    //if(envoye.isAfter(date)){
                        date=envoye;
                        statusCmd=status;
                    //}
                    preparedStatement.executeUpdate();

                }
                if(voir!=null){
                    status = STATUS_A_VOIR;
                    if(voyeur_id!=0)
                        preparedStatement.setInt(2, voyeur_id);
                    preparedStatement.setTimestamp(3, Timestamp.valueOf(voir));
                    preparedStatement.setInt(4, status);
                    preparedStatement.setInt(5, cartcerts.size());
                    preparedStatement.setString(6, null);
                    //if(voir.isAfter(date)){
                        date=voir;
                        statusCmd=status;
                   // }
                    preparedStatement.executeUpdate();
                }

                if(descellee!=null){
                    status = STATUS_A_DESCELLER;
                    if(descelleur_id!=0)
                        preparedStatement.setInt(2, descelleur_id);
                    preparedStatement.setTimestamp(3, Timestamp.valueOf(descellee));
                    preparedStatement.setInt(4, status);
                    preparedStatement.setInt(5, cartcerts.size());
                    preparedStatement.setString(6, null);
                   // if(descellee.isAfter(date)){
                        date=descellee;
                        statusCmd=status;
                    //}
                    preparedStatement.executeUpdate();
                }

                if(receptionClient!=null){
                    status = STATUS_RECU_CLIENT;
                    preparedStatement.setInt(2, user_id);
                    preparedStatement.setTimestamp(3, Timestamp.valueOf(receptionClient));
                    preparedStatement.setInt(4, status);
                    preparedStatement.setInt(5, cartcerts.size());
                    preparedStatement.setString(6, null);

                    //statusCmd=status;
                    //if(receptionClient.isAfter(date)){
                        date=receptionClient;
                        statusCmd=status;
                    //}
                    preparedStatement.executeUpdate();
                }

                if(statusCmd!=0) {
                    // On met les commmandes recues par le client avec un cmd.status=5 afin qu elles ne s'affichent pas sur les cmds à réceptionner.
                    // A modifier par la suite, quand l'affichage sera réglé
                    if (statusCmd==8){
                        statusCmd=5;
                    }
                    if(sav_id==0)
                        sql = "update Cmd set status= " + statusCmd + " where id=" + cmd_id;
                    else
                        sql = "update sav set status= " + statusCmd + " where id=" + sav_id;

                    statement2.executeUpdate(sql);
                }

                if(statusCmd==7 & envoye!=null) {
                    sql ="update Cmd set status=5 where id=" + cmd_id;
                    statement2.executeUpdate(sql);
                }




            }



        }catch(Exception exp){
            System.out.println("problème ici " +exp);
        }
    }
    public static void main(String[] str){
        Etat2Detail ed = new Etat2Detail();
        ed.etat();
    }
}
