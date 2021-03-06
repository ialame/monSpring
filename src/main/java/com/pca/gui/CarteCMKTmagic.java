package com.pca.gui; /**
 * Created by ia on 28/01/2019.
 */

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class CarteCMKTmagic {

    public String colorIndicator;
    public String duelDeck;
    public String faceConvertedManaCost;
    public String frameEffect;
    public String hand;
    public boolean isAlternative;
    public boolean isFoilOnly;
    public boolean isOnlineOnly;
    public boolean isOversized;
    public boolean isTimeshifted;
    public String life;
    public String loyalty;
    public String names;
    public LocalDate date;
    public String side;
    public boolean starter;
    public String tcgplayerPurchaseUrl;
    public String variations;
    public String watermark;


    public int id;
    public int extensionMagic_id;
    public String flavorText;
    public String borderColor;
    public String originalType;
    public String artist;
    public String number;
    public String power;
    public String toughness;
    public int multiverseId;
    public boolean hasFoil;
    public boolean isReserved;
    public String frameVersion;
    public String originalText;
    public boolean hasNonFoil;
    //public String foreignData;
    public String rarity;


    public String colorIdentity;
    public String types;
    public String tcgplayerProductId;
    public String legalities;
    public String scryfallId;
    public ArrayList<Ruling> rulings = new ArrayList<>();
    public int idRulings = 0;
    public String type;
    public String uuid;
    public String colors;
    public String subtypes;
    public String layout;
    public String printings;
    public String name;
    //public String foreignData;
    public String text;
    public String manaCost;
    public String convertedManaCost;
    public String supertypes;


    public String nameEnglish = "";
    public String flavorTextEnglish = "";
    public String textEnglish = "";
    public String typeEnglish = "";
    public int multiverseIdEnglish;

    public String nameSpanish = "";
    public String flavorTextSpanish = "";
    public String textSpanish = "";
    public String typeSpanish = "";
    public int multiverseIdSpanish;

    public String nameFrench = "";
    public String flavorTextFrench = "";
    public String textFrench = "";
    public String typeFrench = "";
    public int multiverseIdFrench;

    public String nameGerman = "";
    public String flavorTextGerman = "";
    public String textGerman = "";
    public String typeGerman = "";
    public int multiverseIdGerman;

    public String nameItalian = "";
    public String flavorTextItalian = "";
    public String textItalian = "";
    public String typeItalian = "";
    public int multiverseIdItalian;

    public String namePortuguese = "";
    public String flavorTextPortuguese = "";
    public String textPortuguese = "";
    public String typePortuguese = "";
    public int multiverseIdPortuguese;

    public String nameJapanese = "";
    public String flavorTextJapanese = "";
    public String textJapanese = "";
    public String typeJapanese = "";
    public int multiverseIdJapanese;

    public String nameKorean = "";
    public String flavorTextKorean = "";
    public String textKorean = "";
    public String typeKorean = "";
    public int multiverseIdKorean;

    public String nameRussian = "";
    public String flavorTextRussian = "";
    public String textRussian = "";
    public String typeRussian = "";
    public int multiverseIdRussian;

    public String nameSimplifiedChinese = "";
    public String flavorTextSimplifiedChinese = "";
    public String textSimplifiedChinese = "";
    public String typeSimplifiedChinese = "";
    public int multiverseIdSimplifiedChinese;

    public String nameTraditionalChinese = "";
    public String flavorTextTraditionalChinese = "";
    public String textTraditionalChinese = "";
    public String typeTraditionalChinese = "";
    public int multiverseIdTraditionalChinese ;
    public String graphe;


    CarteCMKTmagic() {
    }

    CarteCMKTmagic(String name) {
        this.name = name;
    }

    CarteCMKTmagic(int extensionMagic_id,String name,String nameFrench,String rarity,String number,String graphe) {
        this.name = name;
        this.extensionMagic_id = extensionMagic_id;
        this.rarity = rarity;
        this.number=number;
        this.graphe = graphe;
    }
    CarteCMKTmagic(ResultSet rs) {

        try {
            id = rs.getInt("id");
            colorIdentity = rs.getString("colorIdentity");
            types = rs.getString("types");
            tcgplayerProductId = rs.getString("tcgplayerProductId");
            legalities = rs.getString("legalities");
            scryfallId = rs.getString("scryfallId");
            //rulings = rs.getString("rulings");
            uuid = rs.getString("uuid");
            colors = rs.getString("colors");
            subtypes = rs.getString("subtypes");
            layout = rs.getString("layout");
            printings = rs.getString("printings");
            name = rs.getString("name");
            //foreignData = rs.getString("foreignData");
            text = rs.getString("text");
            originalText = rs.getString("originalText");
            manaCost = rs.getString("manaCost");
            convertedManaCost = rs.getString("convertedManaCost");
            side = rs.getString("side");
            variations = rs.getString("variations");
            supertypes = rs.getString("supertypes");
            watermark = rs.getString("watermark");

        } catch (Exception e) {
            System.out.println("probleme carte ..." + e);

        }
    }

    public String toString() {
        return name+" \t "+number;


    }

    //@Override
    public int compareTo(CarteCMKTmagic o) {
        if (this.id < o.id) return -1;
        if (this.id > o.id) return 1;
        return 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setExtensionMagic_id(int extensionMagic_id) {
        this.extensionMagic_id = extensionMagic_id;
    }

    public int getExtensionMagic_id() {
        return extensionMagic_id;
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

    public void setTcgplayerProductId(String tcgplayerProductId) {
        this.tcgplayerProductId = tcgplayerProductId;
    }

    public String getTcgplayerProductId() {
        return tcgplayerProductId;
    }

    public void setLegalities(String legalities) {
        this.legalities = legalities;
    }

    public String getLegalities() {
        return legalities;
    }

    public void setScryfallId(String scryfallId) {
        this.scryfallId = scryfallId;
    }

    public String getScryfallId() {
        return scryfallId;
    }

    public void setRulings(ArrayList<Ruling> rulings) {
        this.rulings = rulings;
    }

    public ArrayList<Ruling> getRulings() {
        return rulings;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getColors() {
        return colors;
    }

    public void setSubtypes(String subtypes) {
        this.subtypes = subtypes;
    }

    public String getSubtypes() {
        return subtypes;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getLayout() {
        return layout;
    }

    public void setPrintings(String printings) {
        this.printings = printings;
    }

    public String getPrintings() {
        return printings;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }

    public String getManaCost() {
        return manaCost;
    }

    public void setConvertedManaCost(String convertedManaCost) {
        this.convertedManaCost = convertedManaCost;
    }

    public String getConvertedManaCost() {
        return convertedManaCost;
    }

    public void setSupertypes(String supertypes) {
        this.supertypes = supertypes;
    }

    public String getSupertypes() {
        return supertypes;
    }


    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setOriginalType(String originalType) {
        this.originalType = originalType;
    }

    public String getOriginalType() {
        return originalType;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getPower() {
        return power;
    }

    public void setToughness(String toughness) {
        this.toughness = toughness;
    }

    public String getToughness() {
        return toughness;
    }

    public void setMultiverseId(int multiverseId) {
        this.multiverseId = multiverseId;
    }

    public int getMultiverseId() {
        return multiverseId;
    }

    public void setHasFoil(boolean hasFoil) {
        this.hasFoil = hasFoil;
    }

    public boolean gethasFoil() {
        return hasFoil;
    }

    public void setIsReserved(boolean isReserved) {
        this.isReserved = isReserved;
    }

    public boolean getIsReserved() {
        return isReserved;
    }

    public void setFrameVersion(String frameVersion) {
        this.frameVersion = frameVersion;
    }

    public String getFrameVersion() {
        return frameVersion;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public String getOriginalText() {
        return originalText;
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


    public void setColorIndicator(String colorIndicator) {
        this.colorIndicator = colorIndicator;
    }
    public String getColorIndicator() {
        return colorIndicator;
    }

    public void setDuelDeck(String duelDeck) {
        this.duelDeck = duelDeck;
    }
    public String getDuelDeck() {
        return duelDeck;
    }

    public void setFaceConvertedManaCost(String faceConvertedManaCost) {
        this.faceConvertedManaCost = faceConvertedManaCost;
    }
    public String getFaceConvertedManaCost() {
        return faceConvertedManaCost;
    }

    public void setFrameEffect(String frameEffect) {
        this.frameEffect = frameEffect;
    }
    public String getFrameEffect() {
        return frameEffect;
    }

    public void setHand(String hand) {
        this.hand = hand;
    }
    public String getHand() {
        return hand;
    }

    public void setIsAlternative(boolean isAlternative) {
        this.isAlternative = isAlternative;
    }
    public boolean getIsAlternative() {
        return isAlternative;
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

    public void setIsTimeshifted(boolean isTimeshifted) {
        this.isTimeshifted = isTimeshifted;
    }
    public boolean getIsTimeshifted() {return isTimeshifted;}

    public void setLife(String life) {
        this.life = life;
    }
    public String getLife() {
        return life;
    }

    public void setLoyalty(String loyalty) {
        this.loyalty = loyalty;
    }
    public String getLoyalty() {
        return loyalty;
    }

    public void setNames(String names) {
        this.names = names;
    }
    public String getNames() {
        return names;
    }

    public void setSide(String side) {
        this.side = side;
    }
    public String getSide() {
        return side;
    }

    public void setStarter(boolean starter) {
        this.starter = starter;
    }
    public boolean getStarters() {
        return starter;
    }

    public void setTcgplayerPurchaseUrl(String tcgplayerPurchaseUrl) {
        this.tcgplayerPurchaseUrl = tcgplayerPurchaseUrl;
    }
    public String getTcgplayerPurchaseUrl() {
        return tcgplayerPurchaseUrl;
    }

    public void setVariations(String variations) {
        this.variations = variations;
    }
    public String getVariations() {
        return variations;
    }

    public void setWatermark(String watermark) {
        this.watermark = watermark;
    }
    public String getWatermark() {
        return watermark;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public LocalDate getDate() {
        return date;
    }

    public static void creerBDD() {

        try {

            Statement statement = Cmkt2Magic.connect.createStatement();

            DatabaseMetaData dbm = Cmkt2Magic.connect.getMetaData();
            // statement.executeUpdate("use FX2Adrien;");
            ResultSet tables = dbm.getTables(null, null, Cmkt2Magic.table_CarteMKTmagic, null);
            if (tables.next()) {
                statement.executeUpdate("DROP TABLE " + Cmkt2Magic.table_CarteMKTmagic);
            }

            String sql = "CREATE TABLE `" + Cmkt2Magic.table_CarteMKTmagic + "` ( " +
                    "id int NOT NULL AUTO_INCREMENT," +
                    "extensionMagic_id int NOT NULL ," +
                    "`name`  varchar(142) DEFAULT NULL," +
                    "`colorIdentity` varchar(100) DEFAULT NULL," +
                    "`types` varchar(100) DEFAULT NULL," +
                    "`tcgplayerProductId` varchar(100) DEFAULT NULL," +
                    "`legalities` varchar(300) DEFAULT NULL," +
                    "`scryfallId` varchar(100) DEFAULT NULL," +
                    "`idRulings`  int DEFAULT NULL," +
                    "`type`  varchar(100) DEFAULT NULL," +
                    "`uuid` varchar(100) DEFAULT NULL," +
                    "`colors` varchar(100) DEFAULT NULL," +
                    "`subtypes` varchar(100) DEFAULT NULL," +
                    "`layout` varchar(100) DEFAULT NULL," +
                    "`printings` varchar(400) DEFAULT NULL," +

                    "`flavorText`  varchar(800) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL," +
                    "`borderColor` varchar(100) DEFAULT NULL," +
                    "`originalType` varchar(100) DEFAULT NULL," +
                    "`artist` varchar(50) DEFAULT NULL," +
                    "`number` varchar(50) DEFAULT NULL," +
                    "`power` varchar(100) DEFAULT NULL," +
                    "`toughness` varchar(100) DEFAULT NULL," +
                    "`multiverseId` int DEFAULT NULL," +
                    "`hasFoil` tinyint(1) DEFAULT NULL," +
                    "`isReserved` tinyint(1) DEFAULT NULL," +
                    "`frameVersion` varchar(100) DEFAULT NULL," +
                    "`originalText`  varchar(800) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL," +
                    "`hasNonFoil` tinyint(1) DEFAULT NULL," +
                    "`rarity` varchar(100) DEFAULT NULL," +


                    "`nameSpanish`  varchar(200) DEFAULT NULL," +
                    "`flavorTextSpanish`  varchar(500) DEFAULT NULL," +
                    "`textSpanish`   varchar(800) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL," +
                    "`typeSpanish`  varchar(200) DEFAULT NULL," +

                    "`nameFrench`  varchar(200) DEFAULT NULL," +
                    "`flavorTextFrench`  varchar(500) DEFAULT NULL," +
                    "`textFrench`   varchar(800) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL," +
                    "`typeFrench`  varchar(200) DEFAULT NULL," +

                    "`nameGerman`  varchar(200) DEFAULT NULL," +
                    "`flavorTextGerman`  varchar(500) DEFAULT NULL," +
                    "`textGerman`   varchar(800) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL," +
                    "`typeGerman`  varchar(200) DEFAULT NULL," +

                    "`nameItalian`  varchar(200) DEFAULT NULL," +
                    "`flavorTextItalian`  varchar(500) DEFAULT NULL," +
                    "`textItalian`   varchar(800) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL," +
                    "`typeItalian`  varchar(200) DEFAULT NULL," +

                    "`namePortuguese`  varchar(200) DEFAULT NULL," +
                    "`flavorTextPortuguese`  varchar(500) DEFAULT NULL," +
                    "`textPortuguese`   varchar(800) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL," +
                    "`typePortuguese`  varchar(200) DEFAULT NULL," +

                    "`nameRussian`   varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL," +
                    "`flavorTextRussian`    varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL," +
                    "`textRussian`    varchar(800) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL," +
                    "`typeRussian`   varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL," +

                    "`nameJapanese`   varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL," +
                    "`flavorTextJapanese`   varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL," +
                    "`textJapanese`    varchar(800) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL," +
                    "`typeJapanese`    varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL," +


/*
                    "`nameKorean`   varchar(400) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL," +
                    "`flavorTextKorean`    varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL," +
                    "`textKorean`    varchar(800) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL," +
                    "`typeKorean`   varchar(400) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL," +

                    "`nameSimplifiedChinese`   varchar(400) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL," +
                    "`flavorTextSimplifiedChinese`    varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL," +
                    "`textSimplifiedChinese`    varchar(800) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL," +
                    "`typeSimplifiedChinese`    varchar(400) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL," +

                    "`nameTraditionalChinese`    varchar(400) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL," +
                    "`flavorTextTraditionalChinese`    varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL," +
                    "`textTraditionalChinese`     varchar(800) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL," +
                    "`typeTraditionalChinese`    varchar(400) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL," +
*/
                    "`text`  varchar(800) CHARACTER SET utf8 COLLATE utf8_unicode_ci  DEFAULT NULL," +
                    "`manaCost` varchar(100) DEFAULT NULL," +
                    "`convertedManaCost` varchar(100) DEFAULT NULL," +
                    "`supertypes` varchar(100) DEFAULT NULL," +

                    "`colorIndicator` varchar(100) DEFAULT NULL," +
                    "`duelDeck` varchar(100) DEFAULT NULL," +
                    "`faceConvertedManaCost` varchar(100) DEFAULT NULL," +
                    "`frameEffect` varchar(100) DEFAULT NULL," +
                    "`hand` varchar(100) DEFAULT NULL," +
                    "`isAlternative` tinyint(1) DEFAULT NULL," +
                    "`isFoilOnly` tinyint(1) DEFAULT NULL," +
                    "`isOnlineOnly` tinyint(1) DEFAULT NULL," +
                    "`isOversized` tinyint(1) DEFAULT NULL," +
                    "`isTimeshifted` tinyint(1) DEFAULT NULL," +
                    "`life` varchar(100) DEFAULT NULL," +
                    "`loyalty` varchar(100) DEFAULT NULL," +
                    "`names` varchar(100) DEFAULT NULL," +
                    "`side` varchar(100) DEFAULT NULL," +
                    "`starter` tinyint(1) DEFAULT NULL," +
                    "`tcgplayerPurchaseUrl` varchar(100) DEFAULT NULL," +
                    "`variations` varchar(3000) DEFAULT NULL," +//3000
                    "`watermark` varchar(100) DEFAULT NULL," +

                    "PRIMARY KEY (`id`)" +
                    ") ENGINE=InnoDB " +
                    "  DEFAULT CHARSET=`utf8` COLLATE=`utf8_general_ci` ;";

            statement.executeUpdate(sql);//

            statement.close();

        } catch (Exception ex) {
            ex.printStackTrace();

        }


    }

    protected void save() {
        try {

            Statement statement = Cmkt2Magic.connect.createStatement();
            String sql="SET NAMES 'utf8'";
            statement.execute(sql);
            sql = "insert into " + Cmkt2Magic.table_CarteMKTmagic + "( name, colorIdentity,types,tcgplayerProductId," +
                    " legalities,scryfallId,idRulings,type," +
                    " uuid, colors, subtypes, layout, " +
                    "printings, text, manaCost, convertedManaCost, supertypes," +

                    "flavorText, borderColor, originalType, artist, number, power," +
                    " toughness, multiverseId, hasFoil, isReserved, frameVersion," +
                    " originalText, hasNonFoil, rarity," +

                    "nameSpanish, flavorTextSpanish, textSpanish, typeSpanish, " +
                    "nameFrench,flavorTextFrench,textFrench, typeFrench, " +
                    "nameGerman, flavorTextGerman, textGerman, typeGerman, " +
                    "nameItalian, flavorTextItalian, textItalian, typeItalian, " +
                    "namePortuguese, flavorTextPortuguese, textPortuguese, typePortuguese, " +
                    "nameRussian, flavorTextRussian, textRussian, typeRussian, " +
                    "nameJapanese, flavorTextJapanese, textJapanese, typeJapanese, " +

                    /*
                    "nameKorean, flavorTextKorean, textKorean, typeKorean, " +
                    "nameSimplifiedChinese, flavorTextSimplifiedChinese, textSimplifiedChinese, typeSimplifiedChinese, " +
                    "nameTraditionalChinese, flavorTextTraditionalChinese, textTraditionalChinese, typeTraditionalChinese" +
                    */

                    " extensionMagic_id,colorIndicator, duelDeck,faceConvertedManaCost,frameEffect, hand, isAlternative," +
                    " isFoilOnly, isOnlineOnly, isOversized, isTimeshifted, life, loyalty, names, side, starter, " +
                    "tcgplayerPurchaseUrl, variations, watermark)" +

                    " values (?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?)";
                            /*
                    ",?,?,?,?,?,?,?,?,?,?" +
                    ",?,?,?,?,?,?,?,?,?,?)";
                    */
            //System.out.print(sql);

            PreparedStatement preparedStatement = Cmkt2Magic.connect.prepareStatement(sql);
            preparedStatement.executeQuery("SET NAMES 'UTF8'");
            preparedStatement.executeQuery("SET CHARACTER SET 'UTF8'");
            //preparedStatement.setLong(1, id);
            preparedStatementOriental(preparedStatement,1, name);
            if (name.length() > 50)
                System.out.println(name + " " + name.length());
            preparedStatementOriental(preparedStatement,2, colorIdentity);
            preparedStatementOriental(preparedStatement,3, types);

            preparedStatementOriental(preparedStatement,4, tcgplayerProductId);
            preparedStatementOriental(preparedStatement,5, legalities);
            preparedStatementOriental(preparedStatement,6, scryfallId);
            if (idRulings == 0)
                preparedStatement.setString(7, null);//
            else
                preparedStatement.setInt(7, idRulings);//

            preparedStatementOriental(preparedStatement,8, type);
            preparedStatementOriental(preparedStatement,9, uuid);
            preparedStatementOriental(preparedStatement,10, colors);
            preparedStatementOriental(preparedStatement,11, subtypes);
            preparedStatementOriental(preparedStatement,12, layout);
            preparedStatementOriental(preparedStatement,13, printings);

            preparedStatementOriental(preparedStatement, 14, text);
            preparedStatementOriental(preparedStatement,15, manaCost);
            preparedStatementOriental(preparedStatement,16, convertedManaCost);
            preparedStatementOriental(preparedStatement,17, supertypes);
            ////////////////////////////////////////
            preparedStatementOriental(preparedStatement, 18, flavorText);
            preparedStatementOriental(preparedStatement,19, borderColor);
            preparedStatementOriental(preparedStatement,20, originalType);
            preparedStatementOriental(preparedStatement,21, artist);
            preparedStatementOriental(preparedStatement,22, number);
            preparedStatementOriental(preparedStatement,23, power);

            preparedStatementOriental(preparedStatement,24, toughness);
            preparedStatement.setInt(25, multiverseId);
            preparedStatement.setBoolean(26, hasFoil);
            preparedStatement.setBoolean(27, isReserved);

            preparedStatementOriental(preparedStatement,28, frameVersion);
            preparedStatementOriental(preparedStatement, 29, originalText);
            preparedStatement.setBoolean(30, hasNonFoil);
            preparedStatementOriental(preparedStatement,31, rarity);
            ////////////////////////////////////
            preparedStatementOriental(preparedStatement, 32, nameSpanish);
            preparedStatementOriental(preparedStatement, 33, flavorTextSpanish);
            preparedStatementOriental(preparedStatement, 34, textSpanish);
            preparedStatementOriental(preparedStatement, 35, typeSpanish);

            preparedStatementOriental(preparedStatement, 36, nameFrench);
            preparedStatementOriental(preparedStatement, 37, flavorTextFrench);
            preparedStatementOriental(preparedStatement, 38, textFrench);
            preparedStatementOriental(preparedStatement, 39, typeFrench);

            preparedStatementOriental(preparedStatement, 40, nameGerman);
            preparedStatementOriental(preparedStatement, 41, flavorTextGerman);
            preparedStatementOriental(preparedStatement, 42, textGerman);
            preparedStatementOriental(preparedStatement, 43, typeGerman);

            preparedStatementOriental(preparedStatement, 44, nameItalian);
            preparedStatementOriental(preparedStatement, 45, flavorTextItalian);
            preparedStatementOriental(preparedStatement, 46, textItalian);
            preparedStatementOriental(preparedStatement, 47, typeItalian);

            preparedStatementOriental(preparedStatement, 48, namePortuguese);
            preparedStatementOriental(preparedStatement, 49, flavorTextPortuguese);
            preparedStatementOriental(preparedStatement, 50, textPortuguese);
            preparedStatementOriental(preparedStatement, 51, typePortuguese);

            preparedStatementOriental(preparedStatement, 52, nameRussian);
            preparedStatementOriental(preparedStatement, 53, flavorTextRussian);
            preparedStatementOriental(preparedStatement, 54, textRussian);
            preparedStatementOriental(preparedStatement, 55, typeRussian);

            preparedStatementOriental(preparedStatement, 56, nameJapanese);
            preparedStatementOriental(preparedStatement, 57, flavorTextJapanese);
            preparedStatementOriental(preparedStatement, 58, textJapanese);
            preparedStatementOriental(preparedStatement, 59, typeJapanese);


/*
            preparedStatementOriental(preparedStatement, 60, nameKorean);
            preparedStatementOriental(preparedStatement, 61, flavorTextKorean);
            preparedStatementOriental(preparedStatement, 62, textKorean);
            preparedStatementOriental(preparedStatement, 63, typeKorean);

            preparedStatementOriental(preparedStatement, 64, nameSimplifiedChinese);
            preparedStatementOriental(preparedStatement, 65, flavorTextSimplifiedChinese);
            preparedStatementOriental(preparedStatement, 66, textSimplifiedChinese);
            preparedStatementOriental(preparedStatement, 67, typeSimplifiedChinese);

            preparedStatementOriental(preparedStatement, 68, nameTraditionalChinese);
            preparedStatementOriental(preparedStatement, 69, flavorTextTraditionalChinese);
            preparedStatementOriental(preparedStatement, 70, textTraditionalChinese);
            preparedStatementOriental(preparedStatement, 71, typeTraditionalChinese);

*/
            preparedStatement.setInt(72-12, extensionMagic_id);

            preparedStatementOriental(preparedStatement, 73-12, colorIndicator);

            preparedStatementOriental(preparedStatement, 74-12, duelDeck);
            preparedStatementOriental(preparedStatement, 75-12, faceConvertedManaCost);

            preparedStatementOriental(preparedStatement, 76-12, frameEffect);
            preparedStatementOriental(preparedStatement, 77-12, hand);
            preparedStatement.setBoolean( 78-12, isAlternative);
            preparedStatement.setBoolean(79-12, isFoilOnly);

            preparedStatement.setBoolean( 80-12, isOnlineOnly);
            preparedStatement.setBoolean(81-12, isOversized);
            preparedStatement.setBoolean(82-12, isTimeshifted);
            preparedStatementOriental(preparedStatement, 83-12, life);

            preparedStatementOriental(preparedStatement, 84-12, loyalty);
            preparedStatementOriental(preparedStatement, 85-12, names);
            preparedStatementOriental(preparedStatement, 86-12, side);

            preparedStatement.setBoolean( 87-12, starter);
            preparedStatementOriental(preparedStatement, 88-12, tcgplayerPurchaseUrl);
            preparedStatementOriental(preparedStatement, 89-12, variations);//variations);
            preparedStatementOriental(preparedStatement, 90-12, watermark);



            preparedStatement.executeUpdate();
            statement.close();


        } catch (Exception e) {
            System.out.println(variations);
            e.printStackTrace();
        }


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


    public void update(){
        try{
            Statement statement = Cmkt2Magic.connect.createStatement();

            String sqlUpdate = "UPDATE " + Cmkt2Magic.table_CarteMKTmagic + " SET name = ?, colors = ?, " +
                    "originalText = ?, text = ?, manaCost = ?,  faceConvertedManaCost = ? , side = ?, variations =? , " +
                    "watermark = ? where id=" + id;



            PreparedStatement ppdStatementUpdate = Cmkt2Magic.connect.prepareStatement(sqlUpdate);

            preparedStatementOriental(ppdStatementUpdate,1, name);
            preparedStatementOriental(ppdStatementUpdate,2, colors);
            preparedStatementOriental(ppdStatementUpdate,3, originalText);
            preparedStatementOriental(ppdStatementUpdate,4, text);

            preparedStatementOriental(ppdStatementUpdate,5, manaCost);
            preparedStatementOriental(ppdStatementUpdate,6, faceConvertedManaCost);
            preparedStatementOriental(ppdStatementUpdate,7, side);
            preparedStatementOriental(ppdStatementUpdate,8, variations);
            preparedStatementOriental(ppdStatementUpdate,9, watermark);

            ppdStatementUpdate.executeUpdate();
            statement.close();

        } catch (Exception e1) {
            System.out.println(e1);
        }

    }
    public void delete(){
        try{
            Statement statement = Cmkt2Magic.connect.createStatement();

            String sqlDelete = "DELETE FROM " + Cmkt2Magic.table_CarteMKTmagic + " WHERE id=" + id;

            statement.executeUpdate(sqlDelete);
            statement.close();

        } catch (Exception e1) {
            System.out.println(e1);
        }

    }



}

class Ruling{
    public LocalDate date;
    public String text;
    Ruling(LocalDate date,String text){
        this.date=date;
        this.text=text;
    }
    public LocalDate getDate(){
        return date;
    }
    public String getText(){
        return text;
    }
}

