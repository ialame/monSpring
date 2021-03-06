package com.pca.gui;

import com.pca.model.ExtensionJAP;

import java.util.ArrayList;


public class ExtensionSource0 {
    private int id;
    private String url;
    private String locale;
    private String discriminator;
    private String nomExtensionSource;
    private String nomExtensionTraduit;
    private String source;
    private boolean isSansEquivalentChezPCA;
    private ArrayList<ExtensionJAP> extensionsJap = new ArrayList<>();
    private ArrayList<Integer> idExtensionsJap = new ArrayList<>();
    private ArrayList<ImageCarte> imagesCartes = new ArrayList<>();

    ExtensionSource0(int id) {

/*        try {
            Connection connect = DB.getConnect();
            Statement statement = connect.createStatement();

            String sql = "SELECT * FROM " + DB.getTable_ExtensionSource() + " where id=" + id;
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                this.id = rs.getInt("id");
                this.url = rs.getString("url");
                this.locale = rs.getString("locale");
                this.discriminator = rs.getString("discriminator");
                this.nomExtensionSource = rs.getString("nomExtensionSource");
                this.nomExtensionTraduit = rs.getString("nomExtensionTraduit");
                this.source = rs.getString("source");
                this.isSansEquivalentChezPCA = rs.getBoolean("isSansEquivalentChezPCA");
            }



            sql = "SELECT extensionjap_id FROM " + DB.getTable_Extensionjap_Extensionsource() + " where extensionsource_id='" + id + "'";
            ResultSet rs2 = statement.executeQuery(sql);
            while (rs2.next()) {
                int extensionjap_id = rs2.getInt("extensionjap_id");
                idExtensionsJap.add(extensionjap_id);

                *//*Statement statement1 = connect.createStatement();
                sql = "SELECT seriejap_id FROM " + DB.getTable_ExtensionJAP() + " where id='" + extensionjap_id + "'";
                ResultSet rs3 = statement1.executeQuery(sql);
                int seriejap_id = 0;
                if (rs3.next()) {
                    seriejap_id = rs3.getInt("seriejap_id");
                }
                ArrayList<SerieJAP> seriesJAP = DB.getSeriesJAP();
                for (SerieJAP sj : seriesJAP) {
                    if (sj.getId() == seriejap_id) {
                        for (ExtensionJAP ej : sj.getExtensionsJAP())
                            if (ej.getId() == extensionjap_id) {
                                extensionsJap.add(ej);
                                break;
                            }
                        break;
                    }
                }
                rs3.close();
                statement1.close();*//*
            }

            rs2.close();
            statement.close();


        } catch (Exception e) {
            System.out.println("Erreur dans constructeur ExtensionSource : " + e);
        }*/
    }

    void addImage(ImageCarte imageCarte) {
        imagesCartes.add(imageCarte);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    public String getNomExtensionSource() {
        return nomExtensionSource;
    }

    public void setNomExtensionSource(String nomExtensionSource) {
        this.nomExtensionSource = nomExtensionSource;
    }

    public String getNomExtensionTraduit() {
        return nomExtensionTraduit;
    }

    public void setNomExtensionTraduit(String nomExtensionTraduit) {
        this.nomExtensionTraduit = nomExtensionTraduit;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isSansEquivalentChezPCA() {
        return isSansEquivalentChezPCA;
    }

    public void setSansEquivalentChezPCA(boolean sansEquivalentChezPCA) {
        isSansEquivalentChezPCA = sansEquivalentChezPCA;
    }

    public ArrayList<ExtensionJAP> getExtensionsJap() {
        return extensionsJap;
    }

    public void setExtensionsJap(ArrayList<ExtensionJAP> extensionsJap) {
        this.extensionsJap = extensionsJap;
    }

    public ArrayList<ImageCarte> getImagesCartes() {
        return imagesCartes;
    }

    public void setImagesCartes(ArrayList<ImageCarte> imagesCartes) {
        this.imagesCartes = imagesCartes;
    }

    public ArrayList<Integer> getIdExtensionsJap() {
        return idExtensionsJap;
    }

    public void setIdExtensionsJap(ArrayList<Integer> idExtensionsJap) {
        this.idExtensionsJap = idExtensionsJap;
    }
}
