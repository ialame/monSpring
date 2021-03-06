package com.ia.ebay9.gui;

import com.ia.ebay9.entity.*;
import org.jetbrains.annotations.NotNull;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class ImageCarte implements Comparable {
    private int id;
    private Extension extension;
    private ExtensionSource0 extensionSource;
    private int extensionjap_id;
    private String extensionKr_id;
    private String langue;
    private String caracteristiques;
    private int statut;
    private String infos;
    private int extensionSource_id;
    public String imageLocal;
    public String idCartes;
    public ArrayList<CartePokemon> cartes = new ArrayList<>();
    public String strCarte;
    public int carte_id = 0;
    public Image image;
    public String Card, japName, japNum, title, original_title;
    public String domaine, site;
    public int qq;
    Document docPage = null, doc = null;
    public static final String dossierVPS = "images/new/cards/pokemon/jap";
    public static String dossierLocal = "N'importe quoi !";//   A T T E N T I O N   DB.getDossierImages();

    ImageCarte() {

    }

    ImageCarte(Image image) {
        this.image = image;
    }

    public void save() {
/*
        try {
            Connection connect = DB.getConnect();
            String sql = "insert into " + DB.getTable_Image_Carte() +
                    "(carte_id,cartes,extensionJap_id, fichier,langue,statut,infos,extensionSource_id,downloaded_at,tailleImg,caracteristiques, src) " +
                    " values (?,?,?,?,?,?,?,?,?,?,?, ?)";

            PreparedStatement preparedStatement = connect.prepareStatement(sql);

            idCartes = "";
            for (CartePokemon cp : getCartes()) {
                idCartes += cp.getId() + " ; ";
                extensionjap_id = cp.getExtensionJAP().getId();
            }
            if (!idCartes.equals(""))
                idCartes = idCartes.substring(0, idCartes.length() - 3);

            switch (statut) {
                case 0:
                case 4:
                case 7:
                    if (!idCartes.equals("") && idCartes.indexOf(";") == -1) {
                        preparedStatement.setObject(1, idCartes);
                        preparedStatement.setObject(2, null);
                    } else {
                        preparedStatement.setObject(1, null);
                        preparedStatement.setString(2, idCartes);
                    }
                    break;
                case 5:
                    preparedStatement.setObject(1, null);
                    preparedStatement.setString(2, idCartes);
                    break;
                case 6:
                    preparedStatement.setObject(1, null);
                    preparedStatement.setObject(2, null);
                    break;

            }
            ExtensionJAP ej;
            if (extensionjap_id == 0) {
                if (extensionSource.getExtensionsJap().size() == 1) {
                    extensionjap_id = this.extensionSource.getExtensionsJap().get(0).getId();
                    preparedStatement.setInt(3, extensionjap_id);
                } else
                    preparedStatement.setObject(3, null);
            } else
                preparedStatement.setInt(3, extensionjap_id);

            preparedStatement.setString(4, image.getImageVPS());
            preparedStatement.setString(5, langue);
            preparedStatement.setInt(6, statut);
            preparedStatement.setString(7, infos);

            preparedStatement.setInt(8, getExtensionSource().getId());

            java.util.Date downloaded_at = image.getDownloaded_at();
            Object param = new java.sql.Timestamp(downloaded_at.getTime());
            preparedStatement.setObject(9, param);
            preparedStatement.setString(10, image.getTailleImg());
            preparedStatement.setString(11, "{}");
            preparedStatement.setString(12, image.getSrc());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println("save de imageCarte");
        }
*/

    }

    void parseImg(Element li) {
        /*try {

            String href = domaine + li.select("a").attr("href");
            docPage = Jsoup.connect(href).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                    .get();
            doc = Jsoup.parseBodyFragment(docPage.toString());

            String src = null;
            String json = "{";
            Elements trs = null;
            if (site.equals("tcgrepublic")) {
                String additional_title = "";
                if (doc.select(".page_title_box").first() != null) {
                    Element en_tete = doc.select(".page_title_box").first();
                    additional_title = en_tete.select(".amber-text").first().text().trim();
                }
                Element Container = doc.select("div#product_main_area").first();
                Element elementImage = Container.select(".main_image").first().select("img").first();
                //String title0 = elementImage.attr("title");
                src = "https://tcgrepublic.com" + elementImage.attr("src");
                Element tableau = Container.select("div.product_description").first();
                trs = tableau.select(".product_description").first().select("tbody").first().children();


                String card_game = null, version = null, originalversion = null, cardnumber = null;

                for (Element tr : trs) {
                    Iterator<Element> tds = tr.children().iterator();
                    while (tds.hasNext()) {
                        Element td = tds.next();
                        switch (td.text()) {
                            case "title":
                                title = tds.next().text().trim();
                                json += "\"title\": \"" + title + "\",";
                                break;
                            case "original title":
                                original_title = tds.next().text().trim();
                                json += "\"original_title\": \"" + original_title + "\",";
                                if (original_title.equals("ギャンブラー"))
                                    original_title = original_title;
                                break;
                            case "card game":
                                card_game = tds.next().text().trim();
                                json += "\"card_game\": \"" + card_game + "\",";
                                break;
                            case "version":
                                version = tds.next().text().trim();
                                json += "\"version\": \"" + version + "\",";
                                break;
                            case "original version":
                                originalversion = tds.next().text().trim();
                                json += "\"original_version\": \"" + originalversion + "\",";
                                break;
                            case "card number":
                                cardnumber = tds.next().text().trim();
                                json += "\"card_number\": \"" + cardnumber + "\",";
                                japNum = cardnumber;
                                break;
                            case "JPN:":
                                String jpn = ((Element) trs.get(0).childNodes().get(3)).text().trim();
                                break;
                            case "Rarity:":
                                //String Rarity = ((Element) tds.childNodes().get(3)).text();
                                break;
                        }

                    }
                }


                if (additional_title.length() > 0) {
                    json += "\"additional_title\":\"" + additional_title + "\",";
                }
                json = json.substring(0, json.length() - 1);
                json = json + "}";
                infos = json;

                japName = original_title;//"リザードン LV.76";
                int pos = japName.indexOf("LV");
                if (pos > 0)
                    japName = japName.substring(0, pos - 1).trim();
                if (japNum != null) {
                    japNum = japNum.trim();
                    pos = japNum.indexOf(" ");
                    if (pos > 0)
                        japNum = japNum.substring(0, pos);
                }
            } else if (site.equals("jp.pokellector")) {
                japName = "";
                title = doc.select("h1.icon.set").first().text();
                int position = title.indexOf("#");
                title = position > 1 ? title.substring(0, position - 1).trim() : title;
                src = doc.select(".card img").attr("src");
                if (src.equals("")) return;
                if (doc.select(".infoblurb").first() == null) return;
                trs = doc.select(".infoblurb").first().children();
                json = "{\"title\": \"" + title + "\",";
                for (Element e : trs) {//doc.select(".infoblurb").first().children().get(3).text().split(":")
                    String[] T = e.text().split(":");
                    json += "\"" + T[0].trim() + "\": \"" + T[1].trim() + "\",";
                    if (T[0].equals("JPN"))
                        japName = T[1].trim();
                    else if (T[0].equals("Card")) {
                        japNum = T[1].trim();
                    }
                }
                json = json.substring(0, json.length() - 1);
                json = json + "}";
                infos = json;
            }
            Image image = new Image(src);
            setImage(image);
            ParseExtensionSource.ns++;
            int pos = src.lastIndexOf(".");
            String type = src.substring(pos + 1);

            image.setImageLocal(dossierLocal + "/" + extensionSource.getId() + "/" + ParseExtensionSource.ns + "." + type);
            image.setImageVPS(dossierVPS + "/" + extensionSource.getId() + "/" + ParseExtensionSource.ns + "." + type);
            image.setType(type);
            //image.save();
            ArrayList<ExtensionJAP> extensionsJap = extensionSource.getExtensionsJap();
            for (ExtensionJAP extensionJap : extensionsJap) {  // les extensions jap équivalentes
                int extensionjap_id = extensionJap.getId();
                //equivalence(extensionJap); MIGRATION
                System.out.println("====================================================");
            }
        } catch (Exception e) {
            System.out.println("Problème dans ImageCarte.parse(): " + e);
        }*/
    }


    void equivalence(ExtensionJAP extensionJap) {
        /*int extensionjap_id = extensionJap.getId();
        japName = japName.replace(" ", "");
        japName = japName.replace("\"", "");
        if (title != null)
            title = title.replace(" ", "");
        boolean isLatin = false;
        if (!japName.equals("")) {
            char c = japName.toUpperCase().charAt(0);
            if ('A' <= c && c <= 'Z')
                isLatin = true;
        } else {
            isLatin = true;
        }
        String sql;
        System.out.println("Extension source  " + extensionSource.getId() + " Extension jap equivalente :" + extensionjap_id + " japName=" + japName + " japNum=" + japNum);
        String[] japNums = null;
        if (japNum != null) {
            japNums = japNum.split("/");
        }
        try {
            Connection connect = DB.getConnect();//  DB.getTable_ExtensionJaps_ExtensionSources()
            Statement statement = connect.createStatement();

            if (japNums != null && japNums.length == 2) {
                int japNumBisSRC = 0;
                int japSurBisSRC = 0;
                try {
                    japNumBisSRC = Integer.parseInt(japNums[0]);
                    japSurBisSRC = Integer.parseInt(japNums[1]);
                } catch (Exception ex) {
                    System.out.println("JapNum frac n'est pas numérique !");
                }
                if (isLatin) {
                    if (japNumBisSRC != 0 && japSurBisSRC != 0) {
                        sql = "SELECT id  FROM " + DB.getTable_CartePokemon() + " where extensionjap_id=" + extensionjap_id +
                                "  and ((japNumBis is null and REPLACE(REPLACE(Card,'-',''),' ','')=\"" + title + "\")  OR ( CAST(japNumBis AS INT) = '" + japNumBisSRC + "'  and ( japSurBis is null or  CAST(japSurBis AS INT) = 0  or CAST(japSurBis AS INT) = '" + japSurBisSRC + "')))";
                    } else {
                        sql = "SELECT id  FROM " + DB.getTable_CartePokemon() + " where extensionjap_id=" + extensionjap_id +
                                "  and REPLACE(REPLACE(Card,'-',''),' ','')=\"" + title + "\" and  japNum = \" " + japNum + "\"";
                    }
                } else {
                    if (japNumBisSRC != 0 && japSurBisSRC != 0) {
                        sql = "SELECT id  FROM " + DB.getTable_CartePokemon() + " where extensionjap_id=" + extensionjap_id +
                                "  and ((japNumBis is null and REPLACE(REPLACE(japName,'-',''),' ','')=\"" + japName + "\")  OR ( CAST(japNumBis AS INT) = '" + japNumBisSRC + "'  and ( japSurBis is null or  CAST(japSurBis AS INT) = 0  or CAST(japSurBis AS INT) = '" + japSurBisSRC + "')))";
                    } else if(japNumBisSRC != 0 ){
                        sql = "SELECT id  FROM " + DB.getTable_CartePokemon() + " where extensionjap_id=" + extensionjap_id +
                                "  and ((japNumBis is null and REPLACE(REPLACE(japName,'-',''),' ','')=\"" + japName + "\")  OR  CAST(japNumBis AS INT) = '" + japNumBisSRC + "' ";
                    } else {
                        sql = "SELECT id  FROM " + DB.getTable_CartePokemon() + " where extensionjap_id=" + extensionjap_id +
                                " and (REPLACE(REPLACE(japName,'＆','&'),' ','')=\"" + japName + "\" and  japNum = \" " + japNum + "\"";
                    }
                }

            } else if(japNums != null && japNums.length == 1){
                if (isLatin) {
                    sql = "SELECT id FROM " + DB.getTable_CartePokemon() + " where extensionjap_id=" + extensionjap_id + "  and REPLACE(REPLACE(Card,'-',''),' ','')=\"" + title +  "\")  AND   (japNum is null or japNum ='—' OR  japNum = \" " + japNum + "\")";
                } else {
                    sql = "SELECT id FROM " + DB.getTable_CartePokemon() + " where extensionjap_id=" + extensionjap_id + "  " +
                            " and (REPLACE(REPLACE(japName,'＆','&'),' ','')=\"" + japName + "\" OR  REPLACE(REPLACE(Card,'＆','&'),' ','')=\"" + title +  "\")  AND   (japNum is null or japNum ='—' OR  japNum = \" " + japNum + "\")";
                }
            }else {
                if (isLatin) {
                    sql = "SELECT id FROM " + DB.getTable_CartePokemon() + " where extensionjap_id=" + extensionjap_id + "  and REPLACE(REPLACE(Card,'-',''),' ','')=\"" + title + "\"";
                } else {
                    sql = "SELECT id FROM " + DB.getTable_CartePokemon() + " where extensionjap_id=" + extensionjap_id + "  " +
                            " and (REPLACE(REPLACE(japName,'＆','&'),' ','')=\"" + japName + "\" OR  REPLACE(REPLACE(Card,'＆','&'),' ','')=\"" + title + "\")";
                }
            }

            ResultSet rs = statement.executeQuery(sql);
            int id;
            while (rs.next()) {
                id = rs.getInt("id");
                for (CartePokemon cp : extensionJap.getCartes()) {
                    if (cp.getId() == id) {
                        //addCarte(cp); MIGRATION
                        //cp.getJapNumImagesCartes().add(japName);
                    }
                }
            }
            rs.close();
            statement.close();

        } catch (Exception exception) {
            System.out.println("Problème de requete dans équivalence dans ImageCarte");
        }


        setLangue("jap");
        extensionSource.addImage(this);
*/

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Extension getExtension() {
        return extension;
    }

    public void setExtension(Extension extension) {
        this.extension = extension;
    }

    public int getExtensionjap_id() {
        return extensionjap_id;
    }

    public void setExtensionjap_id(int extensionjap_id) {
        this.extensionjap_id = extensionjap_id;
    }

    public String getExtensionKr_id() {
        return extensionKr_id;
    }

    public void setExtensionKr_id(String extensionKr_id) {
        this.extensionKr_id = extensionKr_id;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getCaracteristiques() {
        return caracteristiques;
    }

    public void setCaracteristiques(String caracteristiques) {
        this.caracteristiques = caracteristiques;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }

    public int getExtensionSource_id() {
        return extensionSource_id;
    }

    public void setExtensionSource_id(int extensionSource_id) {
        this.extensionSource_id = extensionSource_id;
    }


    public String getImageLocal() {
        return imageLocal;
    }

    public void setImageLocal(String imageLocal) {
        this.imageLocal = imageLocal;
    }

    public String getStrCarte() {
        return strCarte;
    }

    public void setStrCarte(String strCarte) {
        this.strCarte = strCarte;
    }

    public int getCarte_id() {
        return carte_id;
    }

    public void setCarte_id(int carte_id) {
        this.carte_id = carte_id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getJapName() {
        return japName;
    }

    public void setJapName(String japName) {
        this.japName = japName;
    }

    public String getJapNum() {
        return japNum;
    }

    public void setJapNum(String japNum) {
        this.japNum = japNum;
    }

    public String getCard() {
        return Card;
    }

    public void setCard(String card) {
        Card = card;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
        int pos1 = domaine.indexOf("//");
        int pos2 = domaine.lastIndexOf(".");
        if (pos1 != -1 && pos2 != -1)
            site = domaine.substring(pos1 + 2, pos2);
    }

    public ExtensionSource0 getExtensionSource() {
        return extensionSource;
    }

    public void setExtensionSource(ExtensionSource0 extensionSource) {
        this.extensionSource = extensionSource;
    }

    public ArrayList<CartePokemon> getCartes() {
        return cartes;
    }

    public void setCartes(ArrayList<CartePokemon> cartes) {
        this.cartes = cartes;
    }

    public void addCarte(CartePokemon cartePokemon) {
        cartes.add(cartePokemon);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    @Override
    public int compareTo(@NotNull Object o) {
        ImageCarte imageCarte = (ImageCarte) o;
        if(japName.compareTo(imageCarte.getJapName())!=0)
            return japName.compareTo(imageCarte.getJapName());
        if (japNum != null && imageCarte.getJapNum() != null)
            return japNum.compareTo(imageCarte.getJapNum());
        if (japName != null)
            return -1;
        else
            return 1;
    }

    public String toString() {
        return japNum + "  " + japName + "  " + title + "  " + original_title;
    }

}
