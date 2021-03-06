package com.pca.gui;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by fxc on 04/05/2019.
 */
public class CarteMagicComplete extends CarteMagic{



    //public String foreignData;


    public ArrayList<Ruling> rulings = new ArrayList<>();
    public int idRulings = 0;


    //public String foreignData;




    public String artist;
    public String borderColor;
    public String colorIndicator;
    public float convertedManaCost;
    public String duelDeck;
    public float faceConvertedManaCost;
    public String flavorText;
    public String frameEffect;
    public String frameVersion;
    public String hand;
    public boolean isAlternative;
    public boolean isReserved;
    public boolean isStarter;
    public String legalities;
    public String life;
    public String loyalty;
    public String manaCost;
    public String mcmName;
    public int mcmId;
    public int mcmMetaId;
    public int mtgstocksId;
    public int multiverseId;
    public String names;
    public String originalText;
    public String originalType;
    public String printings;
    public String prices;
    public String power;
    public String purchaseUrls;
    public String scryfallId;
    public String scryfallOracleId;
    public String scryfallIllustrationId;
    public String setCode;
    public String subtypes;
    public String supertypes;
    public int tcgplayerProductId;
    public String tcgplayerPurchaseUrl;
    public String text;
    public String toughness;
    public String type;
    public String uuid;
    public String uuidV421;
    public String variations;
    public String watermark;

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



    CarteMagicComplete() {
    }
    /*
    CarteMagicComplete(String table){
        super(table);
    }

     */

    CarteMagicComplete(int id){
        super(id);
        /*try {
            Connection connect = ApplicationPM.DB.getConnect();//
            Statement statementDev =connect.createStatement();
            String sql="SELECT * FROM carteMagic where id="+id;
            this.id=id;
            ResultSet rs = statementDev.executeQuery(sql);
            if (rs.next()){
                mySql2CarteMagicComplete(rs);
            }
            rs.close();
            statementDev.close();
            //connectDev.close();
        }catch(Exception e){
            System.out.println("probleme carte2 ..."+e);

        }*/
    }

    CarteMagicComplete(ResultSet rs) {
        super(rs);
        mySql2CarteMagicComplete(rs);
    }
    private void mySql2CarteMagicComplete(ResultSet rs){

        try {
            colorIndicator = rs.getString("colorIndicator");
            duelDeck = rs.getString("duelDeck");
            faceConvertedManaCost = rs.getFloat("faceConvertedManaCost");
            frameEffect = rs.getString("frameEffect");
            hand = rs.getString("hand");
            isAlternative = rs.getBoolean("isAlternative");
            isTimeshifted = rs.getBoolean("isTimeshifted");
            life = rs.getString("life");
            loyalty = rs.getString("loyalty");
            names = rs.getString("names");
            isStarter = rs.getBoolean("isStarter");
            tcgplayerPurchaseUrl = rs.getString("tcgplayerPurchaseUrl");
            variations = rs.getString("variations");
            watermark = rs.getString("watermark");
            flavorText = rs.getString("flavorText");
            borderColor = rs.getString("borderColor");
            originalType = rs.getString("originalType");
            artist = rs.getString("artist");
        } catch (Exception e) {
            System.out.println("probleme carteMagicComplete..." + e);

        }

    }


    public ArrayList<Ruling> getRulings() {
        return rulings;
    }

    public void setRulings(ArrayList<Ruling> rulings) {
        this.rulings = rulings;
    }

    public int getIdRulings() {
        return idRulings;
    }

    public void setIdRulings(int idRulings) {
        this.idRulings = idRulings;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public String getColorIndicator() {
        return colorIndicator;
    }

    public void setColorIndicator(String colorIndicator) {
        this.colorIndicator = colorIndicator;
    }

    public float getConvertedManaCost() {
        return convertedManaCost;
    }

    public void setConvertedManaCost(float convertedManaCost) {
        this.convertedManaCost = convertedManaCost;
    }

    public String getDuelDeck() {
        return duelDeck;
    }

    public void setDuelDeck(String duelDeck) {
        this.duelDeck = duelDeck;
    }

    public float getFaceConvertedManaCost() {
        return faceConvertedManaCost;
    }

    public void setFaceConvertedManaCost(float faceConvertedManaCost) {
        this.faceConvertedManaCost = faceConvertedManaCost;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    public String getFrameEffect() {
        return frameEffect;
    }

    public void setFrameEffect(String frameEffect) {
        this.frameEffect = frameEffect;
    }

    public String getFrameVersion() {
        return frameVersion;
    }

    public void setFrameVersion(String frameVersion) {
        this.frameVersion = frameVersion;
    }

    public String getHand() {
        return hand;
    }

    public void setHand(String hand) {
        this.hand = hand;
    }

    public boolean isAlternative() {
        return isAlternative;
    }

    public void setAlternative(boolean alternative) {
        isAlternative = alternative;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public boolean isStarter() {
        return isStarter;
    }

    public void setStarter(boolean starter) {
        isStarter = starter;
    }

    public String getLegalities() {
        return legalities;
    }

    public void setLegalities(String legalities) {
        this.legalities = legalities;
    }

    public String getLife() {
        return life;
    }

    public void setLife(String life) {
        this.life = life;
    }

    public String getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(String loyalty) {
        this.loyalty = loyalty;
    }

    public String getManaCost() {
        return manaCost;
    }

    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }

    public String getMcmName() {
        return mcmName;
    }

    public void setMcmName(String mcmName) {
        this.mcmName = mcmName;
    }

    public int getMcmId() {
        return mcmId;
    }

    public void setMcmId(int mcmId) {
        this.mcmId = mcmId;
    }

    public int getMcmMetaId() {
        return mcmMetaId;
    }

    public void setMcmMetaId(int mcmMetaId) {
        this.mcmMetaId = mcmMetaId;
    }

    public int getMtgstocksId() {
        return mtgstocksId;
    }

    public void setMtgstocksId(int mtgstocksId) {
        this.mtgstocksId = mtgstocksId;
    }

    public int getMultiverseId() {
        return multiverseId;
    }

    public void setMultiverseId(int multiverseId) {
        this.multiverseId = multiverseId;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public String getOriginalType() {
        return originalType;
    }

    public void setOriginalType(String originalType) {
        this.originalType = originalType;
    }

    public String getPrintings() {
        return printings;
    }

    public void setPrintings(String printings) {
        this.printings = printings;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getPurchaseUrls() {
        return purchaseUrls;
    }

    public void setPurchaseUrls(String purchaseUrls) {
        this.purchaseUrls = purchaseUrls;
    }

    public String getScryfallId() {
        return scryfallId;
    }

    public void setScryfallId(String scryfallId) {
        this.scryfallId = scryfallId;
    }

    public String getScryfallOracleId() {
        return scryfallOracleId;
    }

    public void setScryfallOracleId(String scryfallOracleId) {
        this.scryfallOracleId = scryfallOracleId;
    }

    public String getScryfallIllustrationId() {
        return scryfallIllustrationId;
    }

    public void setScryfallIllustrationId(String scryfallIllustrationId) {
        this.scryfallIllustrationId = scryfallIllustrationId;
    }

    public String getSetCode() {
        return setCode;
    }

    public void setSetCode(String setCode) {
        this.setCode = setCode;
    }

    public String getSubtypes() {
        return subtypes;
    }

    public void setSubtypes(String subtypes) {
        this.subtypes = subtypes;
    }

    public String getSupertypes() {
        return supertypes;
    }

    public void setSupertypes(String supertypes) {
        this.supertypes = supertypes;
    }

    public int getTcgplayerProductId() {
        return tcgplayerProductId;
    }

    public void setTcgplayerProductId(int tcgplayerProductId) {
        this.tcgplayerProductId = tcgplayerProductId;
    }

    public String getTcgplayerPurchaseUrl() {
        return tcgplayerPurchaseUrl;
    }

    public void setTcgplayerPurchaseUrl(String tcgplayerPurchaseUrl) {
        this.tcgplayerPurchaseUrl = tcgplayerPurchaseUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getToughness() {
        return toughness;
    }

    public void setToughness(String toughness) {
        this.toughness = toughness;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuidV421() {
        return uuidV421;
    }

    public void setUuidV421(String uuidV421) {
        this.uuidV421 = uuidV421;
    }

    public String getVariations() {
        return variations;
    }

    public void setVariations(String variations) {
        this.variations = variations;
    }

    public String getWatermark() {
        return watermark;
    }

    public void setWatermark(String watermark) {
        this.watermark = watermark;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public void setNameEnglish(String nameEnglish) {
        this.nameEnglish = nameEnglish;
    }

    public String getFlavorTextEnglish() {
        return flavorTextEnglish;
    }

    public void setFlavorTextEnglish(String flavorTextEnglish) {
        this.flavorTextEnglish = flavorTextEnglish;
    }

    public String getTextEnglish() {
        return textEnglish;
    }

    public void setTextEnglish(String textEnglish) {
        this.textEnglish = textEnglish;
    }

    public String getTypeEnglish() {
        return typeEnglish;
    }

    public void setTypeEnglish(String typeEnglish) {
        this.typeEnglish = typeEnglish;
    }

    public int getMultiverseIdEnglish() {
        return multiverseIdEnglish;
    }

    public void setMultiverseIdEnglish(int multiverseIdEnglish) {
        this.multiverseIdEnglish = multiverseIdEnglish;
    }

    public String getNameSpanish() {
        return nameSpanish;
    }

    public void setNameSpanish(String nameSpanish) {
        this.nameSpanish = nameSpanish;
    }

    public String getFlavorTextSpanish() {
        return flavorTextSpanish;
    }

    public void setFlavorTextSpanish(String flavorTextSpanish) {
        this.flavorTextSpanish = flavorTextSpanish;
    }

    public String getTextSpanish() {
        return textSpanish;
    }

    public void setTextSpanish(String textSpanish) {
        this.textSpanish = textSpanish;
    }

    public String getTypeSpanish() {
        return typeSpanish;
    }

    public void setTypeSpanish(String typeSpanish) {
        this.typeSpanish = typeSpanish;
    }

    public int getMultiverseIdSpanish() {
        return multiverseIdSpanish;
    }

    public void setMultiverseIdSpanish(int multiverseIdSpanish) {
        this.multiverseIdSpanish = multiverseIdSpanish;
    }

    public String getNameFrench() {
        return nameFrench;
    }

    public void setNameFrench(String nameFrench) {
        this.nameFrench = nameFrench;
    }

    public String getFlavorTextFrench() {
        return flavorTextFrench;
    }

    public void setFlavorTextFrench(String flavorTextFrench) {
        this.flavorTextFrench = flavorTextFrench;
    }

    public String getTextFrench() {
        return textFrench;
    }

    public void setTextFrench(String textFrench) {
        this.textFrench = textFrench;
    }

    public String getTypeFrench() {
        return typeFrench;
    }

    public void setTypeFrench(String typeFrench) {
        this.typeFrench = typeFrench;
    }

    public int getMultiverseIdFrench() {
        return multiverseIdFrench;
    }

    public void setMultiverseIdFrench(int multiverseIdFrench) {
        this.multiverseIdFrench = multiverseIdFrench;
    }

    public String getNameGerman() {
        return nameGerman;
    }

    public void setNameGerman(String nameGerman) {
        this.nameGerman = nameGerman;
    }

    public String getFlavorTextGerman() {
        return flavorTextGerman;
    }

    public void setFlavorTextGerman(String flavorTextGerman) {
        this.flavorTextGerman = flavorTextGerman;
    }

    public String getTextGerman() {
        return textGerman;
    }

    public void setTextGerman(String textGerman) {
        this.textGerman = textGerman;
    }

    public String getTypeGerman() {
        return typeGerman;
    }

    public void setTypeGerman(String typeGerman) {
        this.typeGerman = typeGerman;
    }

    public int getMultiverseIdGerman() {
        return multiverseIdGerman;
    }

    public void setMultiverseIdGerman(int multiverseIdGerman) {
        this.multiverseIdGerman = multiverseIdGerman;
    }

    public String getNameItalian() {
        return nameItalian;
    }

    public void setNameItalian(String nameItalian) {
        this.nameItalian = nameItalian;
    }

    public String getFlavorTextItalian() {
        return flavorTextItalian;
    }

    public void setFlavorTextItalian(String flavorTextItalian) {
        this.flavorTextItalian = flavorTextItalian;
    }

    public String getTextItalian() {
        return textItalian;
    }

    public void setTextItalian(String textItalian) {
        this.textItalian = textItalian;
    }

    public String getTypeItalian() {
        return typeItalian;
    }

    public void setTypeItalian(String typeItalian) {
        this.typeItalian = typeItalian;
    }

    public int getMultiverseIdItalian() {
        return multiverseIdItalian;
    }

    public void setMultiverseIdItalian(int multiverseIdItalian) {
        this.multiverseIdItalian = multiverseIdItalian;
    }

    public String getNamePortuguese() {
        return namePortuguese;
    }

    public void setNamePortuguese(String namePortuguese) {
        this.namePortuguese = namePortuguese;
    }

    public String getFlavorTextPortuguese() {
        return flavorTextPortuguese;
    }

    public void setFlavorTextPortuguese(String flavorTextPortuguese) {
        this.flavorTextPortuguese = flavorTextPortuguese;
    }

    public String getTextPortuguese() {
        return textPortuguese;
    }

    public void setTextPortuguese(String textPortuguese) {
        this.textPortuguese = textPortuguese;
    }

    public String getTypePortuguese() {
        return typePortuguese;
    }

    public void setTypePortuguese(String typePortuguese) {
        this.typePortuguese = typePortuguese;
    }

    public int getMultiverseIdPortuguese() {
        return multiverseIdPortuguese;
    }

    public void setMultiverseIdPortuguese(int multiverseIdPortuguese) {
        this.multiverseIdPortuguese = multiverseIdPortuguese;
    }

    public String getNameJapanese() {
        return nameJapanese;
    }

    public void setNameJapanese(String nameJapanese) {
        this.nameJapanese = nameJapanese;
    }

    public String getFlavorTextJapanese() {
        return flavorTextJapanese;
    }

    public void setFlavorTextJapanese(String flavorTextJapanese) {
        this.flavorTextJapanese = flavorTextJapanese;
    }

    public String getTextJapanese() {
        return textJapanese;
    }

    public void setTextJapanese(String textJapanese) {
        this.textJapanese = textJapanese;
    }

    public String getTypeJapanese() {
        return typeJapanese;
    }

    public void setTypeJapanese(String typeJapanese) {
        this.typeJapanese = typeJapanese;
    }

    public int getMultiverseIdJapanese() {
        return multiverseIdJapanese;
    }

    public void setMultiverseIdJapanese(int multiverseIdJapanese) {
        this.multiverseIdJapanese = multiverseIdJapanese;
    }

    public String getNameKorean() {
        return nameKorean;
    }

    public void setNameKorean(String nameKorean) {
        this.nameKorean = nameKorean;
    }

    public String getFlavorTextKorean() {
        return flavorTextKorean;
    }

    public void setFlavorTextKorean(String flavorTextKorean) {
        this.flavorTextKorean = flavorTextKorean;
    }

    public String getTextKorean() {
        return textKorean;
    }

    public void setTextKorean(String textKorean) {
        this.textKorean = textKorean;
    }

    public String getTypeKorean() {
        return typeKorean;
    }

    public void setTypeKorean(String typeKorean) {
        this.typeKorean = typeKorean;
    }

    public int getMultiverseIdKorean() {
        return multiverseIdKorean;
    }

    public void setMultiverseIdKorean(int multiverseIdKorean) {
        this.multiverseIdKorean = multiverseIdKorean;
    }

    public String getNameRussian() {
        return nameRussian;
    }

    public void setNameRussian(String nameRussian) {
        this.nameRussian = nameRussian;
    }

    public String getFlavorTextRussian() {
        return flavorTextRussian;
    }

    public void setFlavorTextRussian(String flavorTextRussian) {
        this.flavorTextRussian = flavorTextRussian;
    }

    public String getTextRussian() {
        return textRussian;
    }

    public void setTextRussian(String textRussian) {
        this.textRussian = textRussian;
    }

    public String getTypeRussian() {
        return typeRussian;
    }

    public void setTypeRussian(String typeRussian) {
        this.typeRussian = typeRussian;
    }

    public int getMultiverseIdRussian() {
        return multiverseIdRussian;
    }

    public void setMultiverseIdRussian(int multiverseIdRussian) {
        this.multiverseIdRussian = multiverseIdRussian;
    }

    public String getNameSimplifiedChinese() {
        return nameSimplifiedChinese;
    }

    public void setNameSimplifiedChinese(String nameSimplifiedChinese) {
        this.nameSimplifiedChinese = nameSimplifiedChinese;
    }

    public String getFlavorTextSimplifiedChinese() {
        return flavorTextSimplifiedChinese;
    }

    public void setFlavorTextSimplifiedChinese(String flavorTextSimplifiedChinese) {
        this.flavorTextSimplifiedChinese = flavorTextSimplifiedChinese;
    }

    public String getTextSimplifiedChinese() {
        return textSimplifiedChinese;
    }

    public void setTextSimplifiedChinese(String textSimplifiedChinese) {
        this.textSimplifiedChinese = textSimplifiedChinese;
    }

    public String getTypeSimplifiedChinese() {
        return typeSimplifiedChinese;
    }

    public void setTypeSimplifiedChinese(String typeSimplifiedChinese) {
        this.typeSimplifiedChinese = typeSimplifiedChinese;
    }

    public int getMultiverseIdSimplifiedChinese() {
        return multiverseIdSimplifiedChinese;
    }

    public void setMultiverseIdSimplifiedChinese(int multiverseIdSimplifiedChinese) {
        this.multiverseIdSimplifiedChinese = multiverseIdSimplifiedChinese;
    }

    public String getNameTraditionalChinese() {
        return nameTraditionalChinese;
    }

    public void setNameTraditionalChinese(String nameTraditionalChinese) {
        this.nameTraditionalChinese = nameTraditionalChinese;
    }

    public String getFlavorTextTraditionalChinese() {
        return flavorTextTraditionalChinese;
    }

    public void setFlavorTextTraditionalChinese(String flavorTextTraditionalChinese) {
        this.flavorTextTraditionalChinese = flavorTextTraditionalChinese;
    }

    public String getTextTraditionalChinese() {
        return textTraditionalChinese;
    }

    public void setTextTraditionalChinese(String textTraditionalChinese) {
        this.textTraditionalChinese = textTraditionalChinese;
    }

    public String getTypeTraditionalChinese() {
        return typeTraditionalChinese;
    }

    public void setTypeTraditionalChinese(String typeTraditionalChinese) {
        this.typeTraditionalChinese = typeTraditionalChinese;
    }

    public int getMultiverseIdTraditionalChinese() {
        return multiverseIdTraditionalChinese;
    }

    public void setMultiverseIdTraditionalChinese(int multiverseIdTraditionalChinese) {
        this.multiverseIdTraditionalChinese = multiverseIdTraditionalChinese;
    }

    public void creerBDD() {
        try {



        } catch (Exception ex) {
            ex.printStackTrace();


        }


    }


    protected void save() {
        super.save();
/*
        try {
            Connection connect = ApplicationPM.DB.getConnect();
            Statement statement = connect.createStatement();
            String sql="SET NAMES 'utf8'";
            statement.execute(sql);


            sql = "insert into " + ApplicationPM.DB.getTable_CarteMagicComplete() + "( artist, borderColor, colorIndicator, convertedManaCost, " +
                    " duelDeck, faceConvertedManaCost, flavorText, frameEffect, frameVersion, hand, isAlternative, isReserved, " +
                    " isStarter, legalities, life, loyalty, manaCost, mcmName, mcmId, mcmMetaId, mtgstocksId, multiverseId, " +
                    " names, originalText, originalType, printings, prices, power, purchaseUrls, scryfallId, scryfallOracleId, " +
                    " scryfallIllustrationId, setCode, subtypes, supertypes, tcgplayerProductId, tcgplayerPurchaseUrl, text, " +
                    " toughness, type, uuid, uuidV421, variations, watermark, " +

                    "            nameSpanish, flavorTextSpanish, textSpanish, typeSpanish, " +
                    "            nameFrench,flavorTextFrench,textFrench, typeFrench, " +
                    "            nameGerman, flavorTextGerman, textGerman, typeGerman, " +
                    "            nameItalian, flavorTextItalian, textItalian, typeItalian, " +
                    "            namePortuguese, flavorTextPortuguese, textPortuguese, typePortuguese, " +
                    "            nameRussian, flavorTextRussian, textRussian, typeRussian, " +
                    "            nameJapanese, flavorTextJapanese, textJapanese, typeJapanese, id )" +

                    " values (?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?)";


            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            preparedStatement.executeQuery("SET NAMES 'UTF8'");
            preparedStatement.executeQuery("SET CHARACTER SET 'UTF8'");

            preparedStatement.setString(1, artist);
            preparedStatement.setString(2, borderColor);
            preparedStatement.setString(3, colorIndicator);
            preparedStatement.setFloat(4, convertedManaCost);
            preparedStatement.setString(5, duelDeck);
            preparedStatement.setFloat(6, faceConvertedManaCost);
            preparedStatement.setString(7, flavorText);
            preparedStatement.setString(8, frameEffect);
            preparedStatement.setString(9, frameVersion);//

            preparedStatement.setString(10, hand);
            preparedStatement.setBoolean(11, isAlternative);
            preparedStatement.setBoolean(12, isReserved);
            preparedStatement.setBoolean(13, isStarter);
            preparedStatement.setString(14, legalities);
            preparedStatement.setString(15, life);

            preparedStatement.setString( 16, loyalty);
            preparedStatement.setString(17, manaCost);
            preparedStatement.setString(18, mcmName);
            preparedStatement.setInt(19, mcmId);
            ////////////////////////////////////////
            preparedStatement.setInt( 20, mcmMetaId);
            preparedStatement.setInt(21, mtgstocksId);
            preparedStatement.setInt(22, multiverseId);

            preparedStatement.setString(23, names);
            preparedStatement.setString(24, originalText);
            preparedStatement.setString(25, originalType);

            preparedStatement.setString(26, printings);
            preparedStatement.setString(27, prices);
            preparedStatement.setString(28, power);
            preparedStatement.setString(29, purchaseUrls);

            preparedStatement.setString(30, scryfallId);
            preparedStatement.setString( 31, scryfallOracleId);
            preparedStatement.setString(32, scryfallIllustrationId);
            preparedStatement.setString(33, setCode);
            ////////////////////////////////////
            preparedStatement.setString( 34, subtypes);
            preparedStatement.setString( 35, supertypes);
            preparedStatement.setInt( 36, tcgplayerProductId);
            preparedStatement.setString( 37, tcgplayerPurchaseUrl);

            preparedStatement.setString( 38, text);
            preparedStatement.setString( 39, toughness);
            preparedStatement.setString( 40, type);
            preparedStatement.setString( 41, uuid);
            preparedStatement.setString( 42, uuidV421);
            preparedStatement.setString( 43, variations);
            preparedStatement.setString( 44, watermark);


            preparedStatement.setInt( 73, id);

            preparedStatement.executeUpdate();
            statement.close();


        } catch (Exception e) {
            System.out.println(variations);
            e.printStackTrace();
        }
*/


    }
    public CarteMagicComplete copy() {
        CarteMagicComplete cmc = new CarteMagicComplete();
        super.copy(cmc);
        cmc.rulings = rulings;
        cmc.idRulings = idRulings;
        cmc.artist=artist ;
        cmc.borderColor=borderColor ;
        cmc.colorIndicator= colorIndicator;
        cmc.convertedManaCost= convertedManaCost;
        cmc.duelDeck= duelDeck;
        cmc.faceConvertedManaCost= faceConvertedManaCost;
        cmc.flavorText= flavorText;
        cmc.frameEffect= frameEffect;
        cmc.frameVersion= frameVersion;
        cmc.hand= hand;
        cmc.isAlternative= isAlternative;
        cmc.isReserved= isReserved;
        cmc.isStarter= isStarter;
        cmc.legalities= legalities;
        cmc.life= life;
        cmc.loyalty= loyalty;
        cmc.manaCost= manaCost;
        cmc.mcmName= mcmName;
        cmc.mcmId= mcmId;
        cmc.mcmMetaId= mcmMetaId;
        cmc.mtgstocksId= mtgstocksId;
        cmc.multiverseId= multiverseId;
        cmc.names= names;
        cmc.originalText= originalText;
        cmc.originalType= originalType;
        cmc.printings= printings;
        cmc.prices= prices;
        cmc.power= power;
        cmc.purchaseUrls= purchaseUrls;
        cmc.scryfallId= scryfallId;
        cmc.scryfallOracleId= scryfallOracleId;
        cmc.scryfallIllustrationId= scryfallIllustrationId;
        cmc.setCode= setCode;
        cmc.subtypes= subtypes;
        cmc.supertypes= supertypes;
        cmc.tcgplayerProductId= tcgplayerProductId;
        cmc.tcgplayerPurchaseUrl= tcgplayerPurchaseUrl;
        cmc.text= text;
        cmc.toughness= toughness;
        cmc.type= type;
        cmc.uuid= uuid;
        cmc.uuidV421= uuidV421;
        cmc.variations= variations;
        cmc.watermark= watermark;

        cmc.nameEnglish = nameEnglish;
        cmc.flavorTextEnglish = flavorTextEnglish;
        cmc.textEnglish = textEnglish;
        cmc.typeEnglish = typeEnglish;
        cmc.multiverseIdEnglish=multiverseIdEnglish;

        cmc.nameSpanish = nameSpanish;
        cmc.flavorTextSpanish = flavorTextSpanish;
        cmc.textSpanish = textSpanish;
        cmc.typeSpanish = typeSpanish;
        cmc.multiverseIdSpanish=multiverseIdSpanish;

        cmc.nameFrench = nameFrench;
        cmc.flavorTextFrench = flavorTextFrench;
        cmc.textFrench = textFrench;
        cmc.typeFrench = typeFrench;
        cmc.multiverseIdFrench=multiverseIdFrench;

        cmc.nameGerman = nameGerman;
        cmc.flavorTextGerman = flavorTextGerman;
        cmc.textGerman = textGerman;
        cmc.typeGerman = typeGerman;
        cmc.multiverseIdGerman=multiverseIdGerman;

        cmc.nameItalian = nameItalian;
        cmc.flavorTextItalian = flavorTextItalian;
        cmc.textItalian = textItalian;
        cmc.typeItalian = typeItalian;
        cmc.multiverseIdItalian=multiverseIdItalian;

        cmc.namePortuguese = namePortuguese;
        cmc.flavorTextPortuguese = flavorTextPortuguese;
        cmc.textPortuguese = textPortuguese;
        cmc.typePortuguese = typePortuguese;
        cmc.multiverseIdPortuguese=multiverseIdPortuguese;

        cmc.nameJapanese = nameJapanese;
        cmc.flavorTextJapanese = flavorTextJapanese;
        cmc.textJapanese = textJapanese;
        cmc.typeJapanese = typeJapanese;
        cmc.multiverseIdJapanese=multiverseIdJapanese;

        cmc.nameKorean = nameKorean;
        cmc.flavorTextKorean = flavorTextKorean;
        cmc.textKorean = textKorean;
        cmc.typeKorean = typeKorean;
        cmc.multiverseIdKorean=multiverseIdKorean;

        cmc.nameRussian = nameRussian;
        cmc.flavorTextRussian = flavorTextRussian;
        cmc.textRussian = textRussian;
        cmc.typeRussian = typeRussian;
        cmc.multiverseIdRussian=multiverseIdRussian;

        cmc.nameSimplifiedChinese = nameSimplifiedChinese;
        cmc.flavorTextSimplifiedChinese = flavorTextSimplifiedChinese;
        cmc.textSimplifiedChinese = textSimplifiedChinese;
        cmc.typeSimplifiedChinese = typeSimplifiedChinese;
        cmc.multiverseIdSimplifiedChinese=multiverseIdSimplifiedChinese;

        cmc.nameTraditionalChinese = nameTraditionalChinese;
        cmc.flavorTextTraditionalChinese = flavorTextTraditionalChinese;
        cmc.textTraditionalChinese = textTraditionalChinese;
        cmc.typeTraditionalChinese = typeTraditionalChinese;
        cmc.multiverseIdTraditionalChinese =multiverseIdTraditionalChinese;





        return cmc;
    }

    protected void saveRulings() {
        /*try {
            Statement statement = ApplicationPM.DB.getConnect().createStatement();
            int N = rulings.size();
            if (N == 0) return;
            idRulings=getMaxIdRuling()+1;
            String sql = "insert into " + ApplicationPM.DB.getTable_CarteMagicRuling() + "(";
            for (int i = 1; i < N; i++)
                sql += "date" + i + ", text" + i + ", ";
            sql += "date" + N + ", text" + N + " ) values (";
            for (int i = 1; i < N; i++)
                sql += "?,?,";
            sql += "?,?)";

            PreparedStatement preparedStatement = ApplicationPM.DB.getConnect().prepareStatement(sql);
            //preparedStatement.setInt(1, idRulings);
            for (int i = 1; i <= N; i++) {
                LocalDate t = rulings.get(i - 1).getDate();

                String text = rulings.get(i - 1).getText();
                preparedStatement.setDate(2 * i-1, Date.valueOf(t));//.getYear()+"-"+t.getMonth()+"-"+t.getDayOfMonth()+" 0:0:0"));
                preparedStatement.setString(2 * i , text);
            }

            preparedStatement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(rulings.get(8).getText());
        }*/

    }

    public void getCard(JSONObject carte) {
        try {

            Set<Object> Proprietes = carte.keySet();
            Iterator<Object> iterProprietes = Proprietes.iterator();
            while (iterProprietes.hasNext()) {
                Object prop = iterProprietes.next();

                switch (prop.toString()) {
                    case "colorIdentity":
                        this.setColorIdentity(carte.get(prop).toString());
                        break;
                    case "types":
                        this.setTypes(carte.get(prop).toString());
                        break;
                    case "tcgplayerProductId":
                        try{
                            tcgplayerProductId=Integer.parseInt(carte.get(prop).toString());
                        }catch(Exception ex){
                            System.out.println("problème avec " + tcgplayerProductId);
                        }
                        break;
                    case "legalities":
                        this.setLegalities(carte.get(prop).toString());
                        break;
                    case "scryfallId":
                        this.setScryfallId(carte.get(prop).toString());
                        break;
                    case "rulings":
                        //this.setRulings(carte.get(prop).toString());
                        JSONArray jsonRulings = (JSONArray) carte.get(prop);
                        ArrayList<Ruling> rulings = new ArrayList<>();
                        for (int i = 0; i < jsonRulings.size(); i++) {
                            JSONObject ligneRuling = (JSONObject) jsonRulings.get(i);

                            Set<Object> ProprietesRuling = ((JSONObject) ligneRuling).keySet();
                            Iterator<Object> iterProprietesRuling = ProprietesRuling.iterator();
                            String key[] = new String[5];
                            String value[] = new String[5];
                            LocalDate date = null;
                            String text = "";
                            while (iterProprietesRuling.hasNext()) {
                                Object propRuling = iterProprietesRuling.next();
                                //System.out.println(propLangue.toString() + "\t" + ligneLangue.get(propLangue));
                                String propriete = propRuling.toString();

                                String valeur = ligneRuling.get(propriete).toString();

                                if (propriete.equals("date"))
                                    date = LocalDate.parse(valeur);
                                else
                                    text = valeur;
                            }
                            rulings.add(new Ruling(date, text));
                        }
                        this.setRulings(rulings);

                        break;
                    case "uuid":
                        this.setUuid(carte.get(prop).toString());
                        break;
                    case "colors":
                        this.setColors(carte.get(prop).toString());
                        break;
                    case "subtypes":
                        this.setSubtypes(carte.get(prop).toString());
                        break;
                    case "layout":
                        String layout = carte.get(prop).toString();
                        this.setLayout(layout);
                        /*switch(layout.toUpperCase()){
                            case "SPLIT":
                                setStatus(com.pca.gui.ApplicationPM.STATUS_MAGIC_SPLIT);
                                break;
                            case "MELD":
                                setStatus(com.pca.gui.ApplicationPM.STATUS_MAGIC_MELD);
                                break;
                            case "FLIP":
                                setStatus(com.pca.gui.ApplicationPM.STATUS_MAGIC_FLIP);
                                break;
                            case "PLANAR":
                            case "SCHEME":
                            case "VANGUARD":
                                setStatus(com.pca.gui.ApplicationPM.STATUS_MAGIC_PLANAR_SCHEME_VANGUARD);
                                break;
                            case "TRANSFORM":
                                setStatus(com.pca.gui.ApplicationPM.STATUS_MAGIC_TRANSFORM);
                                break;
                        }*/
                        break;
                    case "printings":
                        this.setPrintings(carte.get(prop).toString());
                        break;
                    case "name":
                        String name=carte.get(prop).toString();
                        if(name.length()>50) name=name.substring(0,50);
                        //this.setName(name);
                        //this.setNomComplet(name);
                        this.setNomUS(name);
                        break;
                    case "foreignData":

                        JSONArray langues = (JSONArray) carte.get(prop);
                        for (int i = 0; i < langues.size(); i++) {
                            JSONObject ligneLangue = (JSONObject) langues.get(i);
                            Set<Object> ProprietesLangue = ((JSONObject) ligneLangue).keySet();
                            Iterator<Object> iterProprietesLangue = ProprietesLangue.iterator();
                            String key[] = new String[5];
                            String value[] = new String[5];
                            int k = 0;
                            //flavorText,language,name,text,type
                            while (iterProprietesLangue.hasNext()) {
                                Object propLangue = iterProprietesLangue.next();
                                //System.out.println(propLangue.toString() + "\t" + ligneLangue.get(propLangue));
                                String nomDeLaPropriete = propLangue.toString();
                                switch (nomDeLaPropriete) {
                                    case "flavorText":
                                        value[0] = ligneLangue.get(propLangue).toString();
                                        break;
                                    case "language":
                                        value[1] = ligneLangue.get(propLangue).toString();
                                        break;
                                    case "name":
                                        value[2] = ligneLangue.get(propLangue).toString();
                                        break;
                                    case "text":
                                        value[3] = ligneLangue.get(propLangue).toString();
                                        break;
                                    case "type":
                                        value[4] = ligneLangue.get(propLangue).toString();
                                        break;
                                }
                                k++;
                            }
                            switch (value[1]) {
                                case "English":
                                    this.flavorTextEnglish = value[0];
                                    this.nameEnglish = value[2];
                                    this.textEnglish = value[3];
                                    this.typeEnglish = value[4];
                                    //this.setNomComplet(this.nameEnglish);
                                    this.setNomUS(this.nameEnglish);

                                    break;
                                case "Spanish":
                                    this.flavorTextSpanish = value[0];
                                    this.nameSpanish = value[2];
                                    this.textSpanish = value[3];
                                    this.typeSpanish = value[4];
                                    if(nameSpanish.length()>50) nameSpanish=nameSpanish.substring(0,50);
                                    break;
                                case "French":
                                    this.flavorTextFrench = value[0];
                                    this.nameFrench = value[2];
                                    this.textFrench = value[3];
                                    this.typeFrench = value[4];
                                    if(nameFrench.length()>50) nameFrench=nameFrench.substring(0,50);
                                    //this.nomFR=nameFrench;

                                    break;
                                case "German":
                                    this.flavorTextGerman = value[0];
                                    this.nameGerman = value[2];
                                    this.textGerman = value[3];
                                    this.typeGerman = value[4];
                                    if(nameGerman.length()>50) nameGerman=nameGerman.substring(0,50);
                                    break;
                                case "Italian":
                                    this.flavorTextItalian = value[0];
                                    this.nameItalian = value[2];
                                    this.textItalian = value[3];
                                    this.typeItalian = value[4];
                                    if(nameItalian.length()>50) nameItalian=nameItalian.substring(0,50);
                                    break;
                                case "Portuguese (Brazil)":
                                    this.flavorTextPortuguese = value[0];
                                    this.namePortuguese = value[2];
                                    this.textPortuguese = value[3];
                                    this.typePortuguese = value[4];
                                    if(namePortuguese.length()>50) namePortuguese=namePortuguese.substring(0,50);
                                    break;
                                case "Japanese":
                                    this.flavorTextJapanese = value[0];
                                    this.nameJapanese = value[2];
                                    this.textJapanese = value[3];
                                    this.typeJapanese = value[4];

                                    if(nameJapanese.length()>50) nameJapanese=nameJapanese.substring(0,50);
                                    //this.nomJAP=nameJapanese;
                                    break;
                                case "Korean":
                                    this.flavorTextKorean = value[0];
                                    this.nameKorean = value[2];
                                    this.textKorean = value[3];
                                    this.typeKorean = value[4];
                                    break;
                                case "Russian":
                                    this.flavorTextRussian = value[0];
                                    this.nameRussian = value[2];
                                    this.textRussian = value[3];
                                    this.typeRussian = value[4];
                                    if(nameRussian.length()>50) nameRussian=nameRussian.substring(0,50);
                                    break;
                                case "Chinese Simplified":
                                    this.flavorTextSimplifiedChinese = value[0];
                                    this.nameSimplifiedChinese = value[2];
                                    this.textSimplifiedChinese = value[3];
                                    this.typeSimplifiedChinese = value[4];
                                    break;
                                case "Chinese Traditional":
                                    this.flavorTextTraditionalChinese = value[0];
                                    this.nameTraditionalChinese = value[2];
                                    this.textTraditionalChinese = value[3];
                                    this.typeTraditionalChinese = value[4];
                                    break;

                            }

                        }

                        break;
                    case "text":
                        this.setText(carte.get(prop).toString());
                        break;
                    case "manaCost":
                        this.setManaCost(carte.get(prop).toString());
                        break;
                    case "convertedManaCost":
                        try{
                            convertedManaCost=Float.parseFloat(carte.get(prop).toString());
                        }catch(Exception ex){
                        }
                        break;
                    case "supertypes":
                        this.setSupertypes(carte.get(prop).toString());
                        break;


                    case "flavorText":
                        this.setFlavorText(carte.get(prop).toString());
                        break;
                    case "borderColor":
                        this.setBorderColor(carte.get(prop).toString());
                        break;
                    case "originalType":
                        this.setOriginalType(carte.get(prop).toString());
                        break;
                    case "artist":
                        this.setArtist(carte.get(prop).toString());
                        break;
                    case "number":
                        number = carte.get(prop).toString();
                        this.setNumber(number);
                        StringBuffer sb = new StringBuffer();
                        for(int i=0;i<number.length();i++) {
                            char c= number.charAt(i);
                            if ('0' <=c && c<= '9')
                                sb.append(c);
                        }
                        /*
                        num = sb.toString();
                        String nbZeros = "";
                        if (num.length()<=2) nbZeros = "00";
                        else if (num.length()<=3) nbZeros = "0";
                        else if (num.length()<=4) nbZeros = "";
                        this.idPrim =getExtension().getIdPCA() + nbZeros + num + "00";

                         */
                        break;
                    case "power":
                        this.setPower(carte.get(prop).toString());
                        break;
                    case "toughness":
                        this.setToughness(carte.get(prop).toString());
                        break;
                    case "multiverseId":
                        if(carte.get(prop)!=null)
                            this.setMultiverseId(Integer.parseInt(carte.get(prop).toString()));
                        break;
                    case "hasFoil":
                        this.setHasFoil(carte.get(prop).toString().equals("true") ? true : false);
                        break;
                    case "isReserved":
                        try{
                            isReserved=carte.get(prop).toString().equals("true") ? true : false;
                        }catch(Exception ex){

                        }
                        break;
                    case "frameVersion":
                        this.setFrameVersion(carte.get(prop).toString());
                        break;
                    case "originalText":
                        this.setOriginalText(carte.get(prop).toString());
                        break;
                    case "hasNonFoil":
                        this.setHasNonFoil(carte.get(prop).toString().equals("true") ? true : false);
                        break;
                    case "rarity":
                        this.setRarity(carte.get(prop).toString());
                        break;
                    case "colorIndicator":
                        this.setColorIndicator(carte.get(prop).toString());
                        break;
                    case "duelDeck":
                        this.setDuelDeck(carte.get(prop).toString());
                        break;
                    case "faceConvertedManaCost":
                        try{
                            faceConvertedManaCost=Float.parseFloat(carte.get(prop).toString()) ;
                        }catch(Exception ex){

                        }
                        break;
                    case "frameEffect":
                        this.setFrameEffect(carte.get(prop).toString());
                        break;
                    case "hand":
                        this.setHand(carte.get(prop).toString());
                        break;
                    case "isAlternative":
                        try{
                            isAlternative=carte.get(prop).toString().equals("true") ? true : false;
                        }catch(Exception ex){

                        }
                        break;
                    case "isFoilOnly":
                        this.setIsFoilOnly(carte.get(prop).toString().equals("true") ? true : false);
                        break;
                    case "isOnlineOnly":
                        this.setIsOnlineOnly(carte.get(prop).toString().equals("true") ? true : false);
                        break;
                    case "isOversized":
                        boolean b = carte.get(prop).toString().equals("true") ? true : false;
                        this.setIsOversized(b);
                        /*if(b)
                            setStatus(com.pca.gui.ApplicationPM.STATUS_MAGIC_OVERSIZED);*/
                        break;
                    case "isTimeshifted":
                        try{
                            isTimeshifted=carte.get(prop).toString().equals("true") ? true : false;
                        }catch(Exception ex){

                        }
                        break;
                    case "life":
                        this.setLife(carte.get(prop).toString());
                        break;
                    case "loyalty":
                        this.setLoyalty(carte.get(prop).toString());
                        break;
                    case "names":
                        this.setNames(carte.get(prop).toString());
                        break;
                    case "side":
                        this.setSide(carte.get(prop).toString());
                        break;
                    case "isStarter":
                        try{
                            isStarter=carte.get(prop).toString().equals("true") ? true : false;
                        }catch(Exception ex){

                        }
                        break;
                    case "tcgplayerPurchaseUrl":
                        this.setTcgplayerPurchaseUrl(carte.get(prop).toString());
                        break;
                    case "variations":
                        this.setVariations(carte.get(prop).toString());
                        break;
                    case "watermark":
                        this.setWatermark(carte.get(prop).toString());
                        break;

                }

            }

            LocalDate date = ((ExtensionMagic) getExtension()).getReleaseDate();
            if(date.compareTo(LocalDate.parse("1998-06-15"))>=0)
                //sur = ((ExtensionMagic) getExtension()).getTotalSetSize()+"";
            //setRecherche(num+"/"+ sur);
            setCertifiable(true);
            //setFiltre();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }




    public void update(){
        /*try{
            Statement statement = ApplicationPM.DB.getConnect().createStatement();

            String sqlUpdate = "UPDATE " + ApplicationPM.DB.getTable_CarteMagic() + " SET name = ?, colors = ?, " +
                    "originalText = ?, text = ?, manaCost = ?,  faceConvertedManaCost = ? , side = ?, variations =? , " +
                    "watermark = ?, certifiable = ? where id=" + id;

            PreparedStatement ppdStatementUpdate = ApplicationPM.DB.getConnect().prepareStatement(sqlUpdate);
            //System.out.println("->carte:   Card = " + Extension.get(i)[0] + "   englishExpansion = " + Extension.get(i)[1] + "   englishCarteNum = " + Extension.get(i)[2] + "   japExpansion = " + Extension.get(i)[3] + "   japCarteNum = " + Extension.get(i)[4]);

            ppdStatementUpdate.setBoolean(10, certifiable);

            ppdStatementUpdate.executeUpdate();
            statement.close();

        } catch (Exception e1) {
            System.out.println(e1);
        }
*/
    }




}



