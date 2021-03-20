package com.pca.gui;

import com.pca.model.*;
import com.pca.repository.*;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fxc on 07/03/2020.
 */
@Data
public class CarteUsJap {
    public List<NomGeneralise> nomsGeneralises;
    public NomGeneralise nomGeneralise;
    public int N;
    public int id;
    public Extension extension = null;
    public int extension_id;
    public CartePokemon us = new CartePokemon();
    public CartePokemon jap = new CartePokemon();
    public CartePokemon usBDD = null;
    public CarteJAP japBDD = null;
    CartePokemonSource source;
    public String href;
    public String Card;
    public boolean usEnregistrable;
    public boolean japEnregistrable;
    public boolean mergeable;
    public int save;
    public DicoExpansions dicoExpansions = new DicoExpansions();

    public int status;
    public boolean promo;
    public ArrayList<Promo> Promos = new ArrayList<>();
    public final static int SAVE_JAPPUR = 1;
    public final static int SAVE_USPUR = 2;
    public final static int UPDATE_JAP = 3;
    public final static int UPDATE_US = 4;
    public final static int SAVE_USJAP = 5;
    public String charset;
    String href0 = null;
    boolean JAP = false, US = false;


    public CarteUsJap() {
    }

    CarteUsJap(CartePokemon us, CartePokemon jap) {
        this.us = us;
        this.jap = jap;
    }

    public CartePokemon getUs() {
        return us;
    }

    public void setUs(CartePokemon us) {
        this.us = us;
    }

    public CartePokemon getJap() {
        return jap;
    }

    public void setJap(CartePokemon jap) {
        this.jap = jap;
    }

    ////////////////////////    DETERMINATION     ////////////////////////

    public void determination(Element tr) {
        CartePokemon carte = null;
        if (extension instanceof ExtensionJAP) {
            charset = "jap";
            carte = jap;
        }
        if (extension instanceof ExtensionUS) {
            charset = "us";
            carte = us;
        }
        ArrayList<Element> TDs = tr.children();
        java.util.Iterator<Element> TD = tr.children().iterator();// les éléments (numero,nom,type,rareté) d'une carte
        Element td = null;

        td = TD.next();// le premier td est le numéro
        String num = td.text(), numer = null, denom = null;
        if(num.equals("162/185")) //return;
            num=num;
        int pos = num.indexOf("/");
        if (pos != -1) {
            numer = num.substring(0, pos);
            denom = num.substring(pos + 1);
        }
        ////////////////   M I G R A T I O N
        if (extension instanceof ExtensionJAP) {
            jap.setExtensionJAP((ExtensionJAP) extension);
            jap.setJapNum(num);
            jap.setJapNumBis(numer);
            jap.setJapSurBis(denom);
            //jap.promos =new Promos();
        } else if (extension instanceof ExtensionUS) {
            us.setExtensionUS((ExtensionUS) extension);
            us.setRecherche(num);
            us.setNum(numer);
            us.setSur(denom);
            //us.promos =new Promos();
        }

        td = TD.next();// le deuxième est le nom
        if (td.text().equals(""))
            td = TD.next(); // il se peut que cette td soit vide, on passe à la suivante
        if (td.text().equals("")) return;


        ParseNomGeneralise png = new ParseNomGeneralise(td);
        nomsGeneralises = png.nomsGeneralises;

        for (NomGeneralise ng : nomsGeneralises) {
            Card = ng.getNomCarte();
            if (Card == null) {
                System.out.println("Carte sans nom !!!!");
                return;
            }
            href0 = ng.getHref0();
        }

        if (extension instanceof ExtensionJAP) {
            jap.setNomJAP(td.child(0).text());

        }

        try {
            ParticulariteRepository pr = new ParticulariteRepository(ApplicationPM.entityManager);
            CrochetRepository cr = new CrochetRepository(ApplicationPM.entityManager);
            Particularite p = null;

            //String href0 = td.select("a").attr("href");// on sauvegarde le lien vers la page de la carte
            String href = "https://bulbapedia.bulbagarden.net" + href0; // url de la page de la carte
            setHref(href);


            if (extension instanceof ExtensionUS) {
                us.setNomUS(Card);
                us.setCard(Card);
                if (p != null) {
                    us.getParticularites().add(p);
                    p.getCartePokemons().add(us);
                }

            }
            if (extension instanceof ExtensionJAP) {
                //jap.setNomUS(Card);
                jap.setCard(Card);
                if (p != null) {
                    jap.getParticularites().add(p);
                    p.getCartePokemons().add(jap);
                }
            }


        } catch (Exception exception) {
            System.out.println("Dans CarteUsJap: " + exception);
        }


        td = TD.next();// le troisième td contient le type
        String type = td.select("a").attr("title");
        if (type.equals("")) type = td.text().trim();
        if (type.equals("")) type = td.select("img").attr("title");

        if (extension instanceof ExtensionJAP) {
            jap.setType1(type);
        } else if (extension instanceof ExtensionUS) {
            us.setType1(type);
        }

        td = TD.next();
        String rarity = "";
        if (td.select("a") != null)
            rarity = td.select("a").attr("title");
        if (extension instanceof ExtensionUS)
            us.setRarete(rarity);
        if (extension instanceof ExtensionJAP)
            jap.setJapRarity(rarity);
        if (extension.isPromo()) {
            if (TD.hasNext()) {
                td = TD.next();
                String[] htmlPromos = td.html().split("<br>");
                for (String nomPromo : htmlPromos) {
                    //nomPromo = td.text().trim();
                    nomPromo = Jsoup.parseBodyFragment(nomPromo).text();
                    Promos.add(new Promo(nomPromo, charset));
                }

            }
        }
    }

    public void equivalent() {
        String href = this.getHref();
        String japRarity = null;
        String japNum = null;
        String Card = this.getCard();
        CartePokemon cpBase = null;
        if (nomsGeneralises.get(0).statut==K.STATUS_CARTE_PAGE_DOES_NOT_EXIST)
            return;
        try {
            //if(getNomUS().equals("Rotom Dex"))
            //    Card=Card;
            String optionJsoup = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";
            Document docPage2 = Jsoup.connect(href).userAgent(optionJsoup).get();
            // docPage2.select("div#mw-content-text").first().child(0).select("tbody").first().child(2).text()
            Element TABLE = docPage2.select("div#mw-content-text").first();
            if (TABLE.child(0).select("tbody").first() != null && TABLE.child(0).select("tbody").first().select("span[lang]").size() == 0)//.children();
                TABLE = TABLE.child(2).select("tbody").first();

            Elements trs;
            Element element = null;
            Elements spansLang = TABLE.select("span[lang]");
            //spanLang.parent().children()

            Element spanLang = spansLang == null ? null : spansLang.first();
            String card1=null,card2=null,card3=null;
            if (spanLang != null) {
                spansLang = spanLang.parent().children();
                for(Element e : spansLang){

                }
                card1=spansLang.get(0).text();//// samedi071120
                int i=1;
                while (!spansLang.get(i).tagName().equals("br"))
                    i++;
                if(i==3){
                    card2=spansLang.get(1).text();
                    card3=spansLang.get(2).text();
                }else if(i==2){
                    if(spansLang.get(1).text().indexOf("LV")!=-1) {
                        card3=spansLang.get(1).text();
                    }else
                        card2=spansLang.get(1).text();
                }
                jap.setJapName(spansLang.get(i+1).text());
                jap.setJapTraductionName(spansLang.get(i+2).text());
            }
            for(CartePokemonSource cps : ApplicationPM.cartePokemonSources){
                if(cps.getUrl().equals(href)) {
                    source = cps;
                    break;
                }
            }
            if(source==null) {
                CartePokemonSourceRepository cr = new CartePokemonSourceRepository(ApplicationPM.entityManager);
                CartePokemonSource source0 = cr.getCartePokemonSourceByURL(href);
                if (source0 != null) {
                    source = source0;
                    ApplicationPM.cartePokemonSources.add(source);
                }
            }
            if(source==null){
                source  = new CartePokemonSource(href);
                source.setCard1(card1);
                source.setCard2(card2);
                source.setCard3(card3);
                ApplicationPM.cartePokemonSources.add(source);
            }
            us.setSource(source);
            jap.setSource(source);

            //jap.setJapName(spanLang == null ? null : spanLang.text());
            //jap.setJapTraductionName(spanLang.parent().select("i").text());

            Elements ths = TABLE.select("th");//select("[lang='ja']");
            for (Element th : ths) {
                if (th.text().trim().equals("Card name")) {// si on trouve le nom il faut qu'il soit suivi d'un td contenant "no."
                    element = th;// sachant qu'il est unique on recupère l'élément et on quitte la boucle
                    break;

                }
            }
            if (element == null) {

            }
            if (element != null) {
                while (!element.tag().getName().equals("tbody")) {
                    element = element.parent();
                }
                trs = element.select("tr");//select("[lang='ja']");
                for (Element tr : trs) {
                    if (tr.children().size() <= 1) continue;
                    Element td1 = tr.child(0), td2 = tr.child(1);
                    switch (td1.text().trim()) {
                        case "Evolution stage":
                            jap.setEvolutionStage(td2.text());
                            break;
                        case "Card name":
                            if (Card == null || Card.equals("")) {
                                Card = td2.text();
                                setCard(Card);
                                jap.setCard(Card);///  ICI   Card   il faut une définition rigoureuse
                                us.setCard(Card);
                                // us.setNomUS(getNomsPlus().get(0).getNomCarte());   MIGRATION
                            }
                            break;
                        case "Type":
                            String exjap = td2.text();
                            break;
                        case "Hit Points":
                            jap.setHP(td2.text());
                    }
                }
            }
            if(Card.indexOf("Galarian Sirfetch'd")>=0)
                Card=Card;
            Elements tds = TABLE.select("td");
            //Elements tds = TABLE.child(0).select("td");
            String num = null;
            if(extension instanceof ExtensionJAP)
                num = jap.getJapNum();
            else if(extension instanceof ExtensionUS)
                num = us.getRecherche();
            element = null;


            for (Element td : tds) {
                // A T T E N T I O N    A T T E N T I O N   num.equals("—") ?????
                if (td.text().equals(num) || num.equals("—")) {// si on trouve le numéro il faut qu'il soit suivi d'un td contenant "no."
                    element = td;// sachant qu'il est unique on recupère l'élément et on quitte la boucle
                    break;
                }
            }
            //                                    M I G R A T I O N
            if (element != null) {
                while (!element.tag().getName().equals("tbody")) {
                    element = element.parent();
                }
                if(Card.equals("Galarian Sirfetch'd"))
                    Card=Card;
                trs = element.select("tr");//select("[lang='ja']");
                String japExpansion = null, urlJapExpansion = null;
                for (Element tr : trs) {
                    if (tr.children().size() <= 1) continue;
                    Element td1 = tr.child(0), td2 = tr.child(1);
                    switch (td1.text()) {
                        case "Half Deck":
                        case "Expansion":// traiter deck us
                        case "English expansion":
                            US = true;
                            jap.setEnExpansion(td2.text());
                            dicoExpansions.add(new ExpansionBulbapedia("us",td1.text(),td2.text()));
                            // est-ce necessaire?  String urlEx = "https://bulbapedia.bulbagarden.net" + td2.child(0).attr("href");
                            break;
                        case "English card no.":
                            jap.setEnNum(td2.text());
                            us.setRecherche(jap.getEnNum());

                            break;
                        case "Japanese expansion":
                        case "Japanese Deck":
                        case "Japanese Deck Kit":
                        case "Japanese Half Deck":
                            JAP = true;
                            japExpansion = td2.text();
                            dicoExpansions.add(new ExpansionBulbapedia("jap",td1.text(),td2.text()));
                            if(td2.children().size()!=0)
                                urlJapExpansion = "https://bulbapedia.bulbagarden.net" + td2.child(0).attr("href");
                            break;
                        case "Japanese card no.":
                            japNum = td2.text();
                            jap.setJapNum(japNum);
                            int pos = japNum.indexOf("/");
                            if (pos != -1) {
                                jap.setJapNumBis(japNum.substring(0, pos));
                                jap.setJapSurBis(japNum.substring(pos + 1));
                            }
                    }
                }
            }else{
                // Carte sans page 2
                if(extension instanceof ExtensionJAP)
                    JAP=true;
                else if(extension instanceof ExtensionJAP)
                    US=true;

            }
        } catch (Exception e) {
            System.out.println(" Dans CarteUsJap equivalent : " + e);
        }
        try {
            if(Card.equals("Galarian Sirfetch'd"))
                Card=Card;
            CartePokemonRepository cr = new CartePokemonRepository(ApplicationPM.entityManager);
            ExpansionBulbapediaRepository ebr = new ExpansionBulbapediaRepository(ApplicationPM.entityManager);
            String sql = "select * from cartePokemon as c inner join extension as e " +
                    " where c.extension_id=e.id and c.nomUS='" + Card + "' and c.japNum='" + jap.getJapNum() + "' and e.nomUS='" + jap.getEnExpansion() + "'";
            if (JAP) {
                ExpansionBulbapedia expansionBulbapedia = null;
                ExtensionJAP extensionJAP=null;
                if (dicoExpansions.getByCharset("jap").size() > 0){
                    expansionBulbapedia = dicoExpansions.getByCharset("jap").get(0);
                    extensionJAP = (ExtensionJAP) ebr.getExtensionByTypeNom(expansionBulbapedia);
                }else {
                    extensionJAP = (ExtensionJAP) extension;
                    status=K.STATUS_CARTE_JAP_SANSPAGE2_DANSBASE;
                }

                japBDD = new CarteJAP(cr.getCartePokemonJAPByCardRechercheExtension(Card, jap.getJapNum(), extensionJAP));
                if(japBDD.getCartePokemon()==null)
                    if(status==K.STATUS_CARTE_JAP_SANSPAGE2_DANSBASE) {
                        status = K.STATUS_CARTE_JAP_SANSPAGE2_NON_DANSBASE;
                        japNum = japNum;
                    }
            }
            if (US) {
                if(Card.equals("Gligar"))
                    Card=Card;
                int num, sur, pos = jap.getEnNum().indexOf("/");
                String Recherche = jap.getEnNum(),Rechercheprim=null;

                try {
                    if (pos != -1) {
                        String strNum = jap.getEnNum().substring(0, pos);
                        String strSur = jap.getEnNum().substring(pos + 1);
                        num = Integer.parseInt(strNum);
                        sur = Integer.parseInt(strSur);
                        Rechercheprim = num + "/" + sur;
                    }
                } catch (Exception exception) {
                    System.out.println("Problème numérique dans Recherche");
                }

                ExpansionBulbapedia expansionBulbapedia=null;
                if(dicoExpansions.getByCharset("us").size()>0)
                    expansionBulbapedia = dicoExpansions.getByCharset("us").get(0);
                ExtensionUS extensionUS = (ExtensionUS) ebr.getExtensionByTypeNom(expansionBulbapedia);
                if (extensionUS==null){
                    if (expansionBulbapedia.getType().equals("English expansion")){
                        expansionBulbapedia.setType("Expansion");
                    }else if (expansionBulbapedia.getType().equals("Expansion")){
                        expansionBulbapedia.setType("English expansion");
                    }
                    extensionUS = (ExtensionUS) ebr.getExtensionByTypeNom(expansionBulbapedia);
                }
                usBDD = cr.getCartePokemonUSByCardRechercheExtension(Card, Recherche, extensionUS);
                if (usBDD==null)
                    usBDD = cr.getCartePokemonUSByCardRechercheExtension(Card, Rechercheprim, extensionUS);
            }
            if (US && JAP) {
                if (usBDD != null && japBDD != null && japBDD.getCartePokemon() != null) {
                    if (usBDD == japBDD.getCartePokemon()) {
                        status = K.STATUS_CARTE_USJAP_DANSBASE;
                    } else {
                        status = K.STATUS_CARTE_USJAP_DANSBASE_SEPAREMENT;
                        setMergeable(true);
                    }
                } else if (usBDD != null) {
                    status = K.STATUS_CARTE_USJAP_SANS_JAP_DANSBASE;

                } else if (japBDD != null && japBDD.getCartePokemon() != null) {
                        status = K.STATUS_CARTE_USJAP_SANS_US_DANSBASE;
                        // setSave(CarteUsJap.UPDATE_US);
                    } else {
                        status = K.STATUS_CARTE_USJAP_NON_DANSBASE;
                        setSave(CarteUsJap.UPDATE_JAP);
                }
            } else if (JAP) {
                if(status!=K.STATUS_CARTE_JAP_SANSPAGE2_DANSBASE && status!=K.STATUS_CARTE_JAP_SANSPAGE2_NON_DANSBASE) {
                    if (japBDD != null && japBDD.getCartePokemon() != null) {
                        status = K.STATUS_CARTE_JAPPUR_DANSBASE;
                    } else {
                        status = K.STATUS_CARTE_JAPPUR_NON_DANSBASE;
                    }
                }
            } else if (US) {
                if (usBDD != null) {
                    status = K.STATUS_CARTE_USPUR_DANSBASE;
                } else
                    status = K.STATUS_CARTE_USPUR_NON_DANSBASE;
            }
        } catch (Exception exception) {
            System.out.println("Dans equivalent: " + exception);
        }

        System.out.println(" carte: " + Card);
        if (Card.equals("Alolan Meowth"))
            Card = Card;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getCard() {
        return Card;
    }

    public void setCard(String card) {
        Card = card;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isUsEnregistrable() {
        return usEnregistrable;
    }

    public void setUsEnregistrable(boolean usEnregistrable) {
        this.usEnregistrable = usEnregistrable;
    }

    public boolean isJapEnregistrable() {
        return japEnregistrable;
    }

    public void setJapEnregistrable(boolean japEnregistrable) {
        this.japEnregistrable = japEnregistrable;
    }

    public boolean isMergeable() {
        return mergeable;
    }

    public void setMergeable(boolean mergeable) {
        this.mergeable = mergeable;
    }

    public int getSave() {
        return save;
    }

    public void setSave(int save) {
        this.save = save;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Extension getExtension() {
        return extension;
    }

    public void setExtension(Extension extension) {
        this.extension = extension;
        this.extension_id = extension.getId();
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    void save() {// les cartes energy ne sont pas enregistrées pour le moment, à voir ...
        //if(japBDD!=null && japBDD.getCartePokemon()!=null && japBDD.getCartePokemon().getId()==1115551)
            jap=jap;
        EntityManager entityManager = null;
        switch (getStatus()){
            case K.STATUS_CARTE_USJAP_SANS_JAP_DANSBASE:
                usBDD.ajoutePartieJapDe(jap);
                try {
                    entityManager = ApplicationPM.entityManagerFactory.createEntityManager();
                    EntityTransaction trans = entityManager.getTransaction();
                    trans.begin();
                    CartePokemon cp = usBDD;
                    entityManager.merge(cp);
                    trans.commit();

                } finally {
                    if ( entityManager != null ) entityManager.close();
                    //if ( entityManagerFactory != null ) entityManagerFactory.close();
                }
                break;
            case K.STATUS_CARTE_JAPPUR_NON_DANSBASE:

                    jap.setJAP(true);
                    jap.setUS(false);
                    try {
                        entityManager = ApplicationPM.entityManagerFactory.createEntityManager();
                        EntityTransaction trans = entityManager.getTransaction();
                        trans.begin();
                        CartePokemon cp = jap;

                        entityManager.persist(cp);
                        trans.commit();

                    } finally {
                        if ( entityManager != null ) entityManager.close();
                        //if ( entityManagerFactory != null ) entityManagerFactory.close();
                    }
                    break;
            case K.STATUS_CARTE_JAPPUR_DANSBASE:
                jap.setJAP(true);
                jap.setUS(false);
                CartePokemon cp = japBDD.getCartePokemon();
                cp.setParticularites(jap.getParticularites());
                try {
                    entityManager = ApplicationPM.entityManagerFactory.createEntityManager();
                    EntityTransaction trans = entityManager.getTransaction();
                    trans.begin();
                    entityManager.merge(cp);
                    trans.commit();

                } finally {
                    if ( entityManager != null ) entityManager.close();
                    //if ( entityManagerFactory != null ) entityManagerFactory.close();
                }
                break;
            case K.STATUS_CARTE_USJAP_DANSBASE:
                if(us.getExtensionUS()!=null) jap.getExtensionUS().getCartes().remove(us);
                if(jap.getExtensionJAP()!=null) jap.getExtensionJAP().getCartes().remove(jap);
                //us=null;
                //jap = null;
        }


    }

    public boolean isPromo() {
        return promo;
    }

    public void setPromo(boolean promo) {
        this.promo = promo;
    }

    public CarteUsJap copy() {
        CarteUsJap c = new CarteUsJap();

        ///////////////////
        c.N = N;
        c.id = id;
        c.extension = extension;
        c.extension_id = extension_id;
        c.us = new CartePokemon();
        c.jap = new CartePokemon();
        c.usBDD = usBDD;
        c.japBDD = japBDD;
        // MIGRATION
        us.copy(c.us);
        jap.copy(c.jap);

        c.href = href;
        c.Card = Card;
        c.usEnregistrable = usEnregistrable;
        c.japEnregistrable = japEnregistrable;
        c.mergeable = mergeable;
        c.save = save;
        //c.carteMergeUS=carteMergeUS;
        //c.carteMergeJAP=carteMergeJAP;
        //c.nomsPlus = nomsPlus;
        c.status = status;
        c.promo = promo;

        ///////////////////
        return c;
    }

    public String getHref0() {
        return href0;
    }

    public void setHref0(String href0) {
        this.href0 = href0;
    }

    public ArrayList<Promo> getPromos() {
        return Promos;
    }

    public void setPromos(ArrayList<Promo> promos) {
        Promos = promos;
    }

    public NomGeneralise getNomGeneralise() {
        return nomGeneralise;
    }

    public void setNomGeneralise(NomGeneralise nomGeneralise) {
        this.nomGeneralise = nomGeneralise;
    }


    private class DicoExpansions extends ArrayList<ExpansionBulbapedia> {
        public String getType(String key) {
            for (ExpansionBulbapedia expansion : this) {
                if (expansion.getType().equals(key)) {
                    return expansion.getType();
                }
            }
            return null;
        }
        public ArrayList<ExpansionBulbapedia> getByCharset(String charset){
            ArrayList<ExpansionBulbapedia> exps = new ArrayList<>();
            for (ExpansionBulbapedia expansion : this) {
                if (expansion.getCharset().equals(charset)) {
                    exps.add(expansion);
                }
            }
            return exps;
        }
    }


}

