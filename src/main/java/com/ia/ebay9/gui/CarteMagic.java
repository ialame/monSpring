package com.ia.ebay9.gui; /**
 * Created by ia on 19/12/2018.
 */

import com.ia.ebay9.entity.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

public class CarteMagic extends CartePokemon {
    ///////////////////////////////////////////////////////////////////////////////////////

    public int extensionMagic_id;
    public boolean isFoilOnly;
    public boolean isOnlineOnly;
    public boolean isOversized;
    public boolean isTimeshifted;
    public String side;
    public String number;
    public boolean hasFoil;
    public boolean hasNonFoil;
    public String rarity;
    public String colorIdentity;
    public String types;
    public String colors;
    public String layout;
    public boolean certifiable=true;
    public boolean isToken =false;
    public int fusionPCA;
    public int filtre;
    public boolean hasImg = false;
    public ExtensionMagic extension;

    CarteMagic() {
    }
    //CarteMagic(String table){
    //    super(table);
    //}

    CarteMagic(int id){
        //super(id);

        /*try {
            Connection connect = ApplicationPM.getConnect();//
            Statement statementDev =connect.createStatement();
            String sql="SELECT * FROM carteMagic where id="+id;
            this.id=id;
            ResultSet  rs = statementDev.executeQuery(sql);
            if (rs.next()){
                mySql2CarteMagic(rs);
            }
            rs.close();
            statementDev.close();
            //connectDev.close();
        }catch(Exception e){
            System.out.println("probleme carte2 ..."+e);

        }*/
    }

    CarteMagic(ResultSet rs) {
        //super(rs);
        mySql2CarteMagic(rs);
    }
    CarteMagic(Element tr){

        Iterator<Element> TD = tr.children().iterator();

        int k = 1;
        Element td = null;

        td = TD.next();// case invisible
        td = TD.next();// caméra
        td = TD.next();// extension
        String extension = td.select("span").attr("data-original-title");
        td = TD.next();// Nom,nombre,rareté
        Element tdNom = td.child(0).children().first();
        Element aNomFR = tdNom.children().first();
        this.setNomFR(aNomFR.text());

        String href = "https://www.cardmarket.com" + aNomFR.attr("href");
        Element divNomUS = tdNom.children().last();

        this.setNomUS(divNomUS.text());
        //CarteMkt carteMkt = new CarteMkt(nameFR, nameUS, extension, "rarete", "graphe");

        Element tdNombre = td.child(0).children().get(1);
        Element tdRarete = td.child(0).children().get(2);
        this.setNumber(tdNombre.text());
        this.setRarity(tdRarete.text());
        td = TD.next();// disponible
        String disponible = td.text();
        td = TD.next();// de
        String prix1 = td.text();

        td = TD.next();// disponible2
        String disponible2 = td.text();
        td = TD.next();// De (Foil)
        String prix2 = td.text();


    }



    private void mySql2CarteMagic(ResultSet rs){

        try {
            //name = rs.getString("name");
            colorIdentity = rs.getString("colorIdentity");
            types = rs.getString("types");
            number = rs.getString("number");
            extensionMagic_id = rs.getInt("extensionMagic_id");
            colors = rs.getString("colors");
            layout = rs.getString("layout");
            hasFoil = rs.getBoolean("hasFoil");
            hasNonFoil = rs.getBoolean("hasNonFoil");
            isFoilOnly = rs.getBoolean("isFoilOnly");
            isOnlineOnly = rs.getBoolean("isOnlineOnly");
            isOversized = rs.getBoolean("isOversized");
            certifiable = rs.getBoolean("certifiable");
            isToken = rs.getBoolean("isToken");
            hasImg = rs.getBoolean("hasImg");
            rarity = rs.getString("rarity");
            side = rs.getString("side");
            fusionPCA = rs.getInt("fusionPCA");
            certifiable = rs.getBoolean("certifiable");

        } catch (Exception e) {
            System.out.println("probleme carte ..." + e);

        }

    }

    public void setFusionPCA(int fusionPCA) {
        this.fusionPCA = fusionPCA;
    }

    public int getFusionPCA() {
        return fusionPCA;
    }

    public void setColorIdentity(String colorIdentity) {
        this.colorIdentity = colorIdentity;
    }

    public String getColorIdentity() {
        return colorIdentity;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getTypes() {
        return types;
    }



    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getColors() {
        return colors;
    }



    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getLayout() {
        return layout;
    }


    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }



    public void setHasFoil(boolean hasFoil) {
        this.hasFoil = hasFoil;
    }

    public boolean gethasFoil() {
        return hasFoil;
    }



    public void setHasNonFoil(boolean hasNonFoil) {
        this.hasNonFoil = hasNonFoil;
    }

    public boolean getHasNonFoil() {
        return hasNonFoil;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getRarity() {
        return rarity;
    }

    public void setIsFoilOnly(boolean isFoilOnly) {
        this.isFoilOnly = isFoilOnly;
    }
    public boolean getIsFoilOnly() {return isFoilOnly;}
    public void setIsOnlineOnly(boolean isOnlineOnly) {
        this.isOnlineOnly = isOnlineOnly;
    }
    public boolean getIsOnlineOnly() {return isOnlineOnly;}
    public void setIsOversized(boolean isOversized) {
        this.isOversized = isOversized;
    }
    public boolean getIsOversized() {return isOversized;}
    public void setSide(String side) {
        this.side = side;
    }
    public String getSide() {
        return side;
    }
    public void setCertifiable(boolean certifiable) {
        this.certifiable = certifiable;
    }
    public boolean getCertifiable() {
        return this.certifiable;
    }

    public boolean isTimeshifted() {
        return isTimeshifted;
    }

    public void setTimeshifted(boolean timeshifted) {
        isTimeshifted = timeshifted;
    }

    protected void save() {
        //super.save();
        try {


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public CarteMagic copy() {
        CarteMagic cm = new CarteMagic();
        super.copy(cm);
        cm.extensionMagic_id=extensionMagic_id;
        cm.isFoilOnly=isFoilOnly;
        cm.isOnlineOnly=isOnlineOnly;
        cm.isOversized=isOversized;
        cm.isTimeshifted=isTimeshifted;
        cm.side=side;
        cm.number=number;
        cm.hasFoil=hasFoil;
        cm.hasNonFoil=hasNonFoil;
        cm.rarity=rarity;
        cm.colorIdentity=colorIdentity;
        cm.types=types;
        cm.colors=colors;
        cm.layout=layout;
        cm.certifiable=certifiable;
        cm.isToken =isToken;
        cm.fusionPCA=fusionPCA;
        cm.hasImg = hasImg;
        cm.extension = extension;
        return cm;
    }
    public void copy(CarteMagic cm) {
        super.copy(cm);
        cm.extensionMagic_id=extensionMagic_id;
        cm.isFoilOnly=isFoilOnly;
        cm.isOnlineOnly=isOnlineOnly;
        cm.isOversized=isOversized;
        cm.isTimeshifted=isTimeshifted;
        cm.side=side;
        cm.number=number;
        cm.hasFoil=hasFoil;
        cm.hasNonFoil=hasNonFoil;
        cm.rarity=rarity;
        cm.colorIdentity=colorIdentity;
        cm.types=types;
        cm.colors=colors;
        cm.layout=layout;
        cm.certifiable=certifiable;
        cm.isToken =isToken;
        cm.fusionPCA=fusionPCA;
        cm.hasImg = hasImg;
        cm.extension = extension;
    }




    public void getImage(){
        boolean recto = true;
        String transform1 = "";
        String urlimage = "https://scryfall.com/card/" +((ExtensionMagic) getExtension()).getCode().toLowerCase() + "/" + getNumber();
        if (getLayout() != null && getLayout().equals("transform"))
            if (recto) {
                //transform1 = getName();
                recto = false;
            } else {
                //transform1 = (transform1 + "-" + getName()).replace(" ", "-").replace("'", "");
                urlimage += "/" + transform1.toLowerCase() + "?back";
                transform1 = "";
            }
        //if(isToken && id!=273 && id!=419 && id!=414   && id!=371)
        //    urlimage = "https://scryfall.com/card/t"+((ExtensionMagic) getExtension()).getCode().toLowerCase() +"/"+getNumber();

        Document doc = null;
        int k = 0;
        while ((doc == null || doc.html().isEmpty()) && k++ < 100)
            try {
                String optionNavigateur = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";
                doc = Jsoup.connect(urlimage).userAgent(optionNavigateur).get();
                doc = Jsoup.parseBodyFragment(doc.toString());
            } catch (Exception ex) {
                System.out.println("Erreur internet !");
                /*
                log.println("carte: " + getId());
                log.println("Erreur internet !: " + ex);
                log.print(" k= : " + k);
                log.flush();
                */
            }
        //if (k == 100) continue;
        String img = doc.select("div.card-image-front").first().select("img").attr("src");// c'est un BIG dans TABLE


        try {

            URL url2 = new URL(img);
            BufferedImage image = ImageIO.read(url2);
            //Thread.sleep(3000);
            String fichier = "n'importe quoi ! "; // A T T E N T I O N  ApplicationPM.DB.getDossier_Magic();// + "/" + this.getExtension().getSerieABC().getNom() + "/" + ((ExtensionMagic) getExtension()).getCode() + "/" + getId() + ".jpg";
            File outputfile = new File(fichier);
            ImageIO.write(image, "jpg", outputfile);

            //log.println("carte: " + cm.getId());
            //log.flush();
            System.out.println("carte: " + getId());
        } catch (Exception excep) {
            excep.printStackTrace();
            /*
            log.println("carte: " + getId());
            log.println("erreur: " + excep);
            log.flush();
            */

        }

    }


    public CarteMagic appliqueSplit(ArrayList<CarteMagic> cmcs){

        int idCM= 0;//  A T T E N T I O N    maxId();

        CarteMagic cm = cmcs.get(0).copy();
        //cm.setTable(com.ia.ebay9.gui.ApplicationPM.DB.getTable_CarteMagic());
        //cm.setId(idCM+1);  /// ???????  id?
        cm.setExtension(getExtension());
        cm.setCertifiable(true);
        for(int i =1; i<cmcs.size();i++){
            CarteMagic  rs =cmcs.get(i);
            rs.setCertifiable(false);
            /*
            cm.setName(cm.getName()+" // "+ rs.getName());
            //cm.setNom(cm.getNom()+" // "+ rs.getNom());
            cm.setNomUS(cm.getNomUS()+" // "+ rs.getNomUS());
            if(cm.getNomFR()!=null && !cm.getNomFR().equals(""))
                cm.setNomFR(cm.getNomFR()+" // "+ rs.getNomFR());
            if(cm.getNomJAP()!=null && !cm.getNomJAP().equals(""))
                cm.setNomJAP(cm.getNomJAP()+" // "+ rs.getNomJAP());
            if(cm.getColors()!=null && !cm.getColors().equals(""))
                cm.setColors(cm.getColors()+" // "+ rs.getColors());

             */

            rs.setFusionPCA(idCM+1);
            rs.setFiltre(1);
        }
        return cm;
    }


    public int getFiltre() {
        return filtre;
    }

    public void setFiltre(int filtre) {
        this.filtre = filtre;
    }

    public int getExtensionMagic_id() {
        return extensionMagic_id;
    }

    public void setExtensionMagic_id(int extensionMagic_id) {
        this.extensionMagic_id = extensionMagic_id;
    }

    public ExtensionMagic getExtension() {
        return extension;
    }

    public void setExtension(ExtensionMagic extensionMagic) {
        this.extension = extensionMagic;
    }




    public boolean isCertifiable() {
        return certifiable;
    }

    public int compareTo(Carte o) {
        CarteMagic cm = (CarteMagic)o;
        return this.number.compareTo(cm.number);
    }
}

