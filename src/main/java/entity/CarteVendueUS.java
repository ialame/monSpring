package com.pca.entity;
/**
 * Created by ia on 23/05/2018.
 * https://www.ebay.fr/itm/POKEMON-Pokemon-Basic-Pikipek-HP50-2017-CCG-Collectors-Trading-Card-Good-Con/273228820624?hash=item3f9db4f090:g:bjMAAOSwF8la-aZV
 */

import com.pca.gui.Tools;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;


@Entity
@Table( name = "CarteVendue" )
@DiscriminatorValue(value="US")
public class CarteVendueUS extends CarteVendue {
    private static final long serialVersionUID = 1L;
    CarteVendueUS(){

    }
    public CarteVendueUS(Element x, CarteEbay carteEbay) {
        super(carteEbay);
        this.carteEbay = carteEbay;
        //super(c);
        //Document doc = Jsoup.parseBodyFragment(x.toString());
        //urlDescription = x.select("H3").first().parent().attr("href");
        setDescription();
        img = x.select("img.s-item__image-img").attr("src").replace("/thumbs", "").replace("225", "1600");
        String imgprim = x.select("img.s-item__image-img").attr("data-src").replace("/thumbs", "").replace("225", "1600");
        if (!imgprim.equals(""))
            img = imgprim;
        //System.out.println("l image :  "+img);
        //System.out.println("l image :  "+imgprim);

        Element eTitre = x.select("h3.s-item__title").first();
        String vente = eTitre.child(0).text();
        this.setVente(vente);
        String titre = eTitre.text().substring(vente.length());
        this.titre = titre.trim();
        setPays();

        String etatEbay2018;
        if (x.select("div.s-item__subtitle") != null) {
            Element eSousTitre = x.select("div.s-item__subtitle").first();
            if (eSousTitre != null)
                etatEbay2018 = eSousTitre.text();
        }
        if (x.select("div.s-item__reviews") != null) {
            Element eReviews = x.select("div.s-item__reviews").first();
        }
        //s-item__detail s-item__detail--primary
        Element eDetails = x.select("div.s-item__details").first();

        if (x.select("s-item__buyItNowOption") != null) {
            Element eVente = x.select("s-item__buyItNowOption").first();
            if (eVente != null)
                setVente(eVente.text());
        }
        if (x.select("span.s-item__location").size() != 0) {
            setPays(x.select("span.s-item__location").text());
        }
        String prix = eDetails.select("span.POSITIVE").first().text();
        this.setPrix(prix);
        String date = eDetails.select("span.s-item__ended-date").first().text();
        this.setDate(date);
        //String enchere = eDetails.select("span.s-item__bids").first().text();

        if (titre.equals("") && x.text().indexOf("found from eBay international sellers") == -1) {
            //existe = false;
            return;//
        }

    }

    public boolean titreContient2Noms(ArrayList<Pokemons> pokemons) {

        String t = Tools.removeCaracSpecial(titre).toLowerCase();
        //t = t.replace(Tools.removeCaracSpecial(carteEbay.nomEBAY).toLowerCase(), "");
        for (Pokemons pkm : pokemons) {
            if (t.indexOf(pkm.getNomUS()) != -1) {
                return true;
            }
        }
        return false;
    }//

    public void setDescription() {
        /*
        if(urlDescription.equals("https://www.ebay.com/itm/GRENINJA-14-39-X-Y-KALOS-SET-HOLO-NM-POKEMON-pn17-0542/362009446047?hash=item544971c69f:g:HeAAAOSwjRpZQpWs"))
            urlDescription=urlDescription;

         */
        //System.out.println(urlDescription);
        try {
            Document docPage = Jsoup.connect("urlDescription").get();  //Commentaires du vendeur : la chaine de recherche dans google
            java.util.Iterator<Element>  TRs = docPage.select("div#viTabs_0_is").select("table").first().select("tr").iterator();
            Elements desc = docPage.select("div#desc_div");
            if(desc.size()>0)
                System.out.println("desc=" + desc.first().text());

            while (TRs.hasNext()){
                Element tr = TRs.next();
                java.util.Iterator<Element>  TDs = tr.children().iterator();
                while (TDs.hasNext()) {

                    String td = TDs.next().text();
                    int pos = td.indexOf(":");
                    if (pos == -1) continue;
                    String propriete = td.substring(0, pos).trim();
                    if (!TDs.hasNext()) continue;
                    td = TDs.next().text();
                    switch (propriete) {  //
                        case "Condition":
                            pos = td.indexOf(":");
                            if (pos == -1) continue;
                            String valeur = td.substring(0, pos).trim();
                            //etatDescription = valeur; //
                            System.out.println("Condition=" + valeur);
                            break;
                        case "Card Type":
                            if (td.indexOf("Pokémon") != -1)
                                td = td;
                            //CardType = td; //
                            System.out.println("CardType=" + td);
                            break;
                        case "Features":
                            //Features = td; //
                            System.out.println("Features=" + td);
                            break;
                        case "Seller Notes":
                            sellerNotes = td; //
                            System.out.println("Seller Notes=" + td);
                            break;
                        case "Grade":
                            //Grade = td; //
                            System.out.println("Grade=" + td);
                            break;
                        case "UPC":
                            //UPC = td; //
                            System.out.println("UPC=" + td);
                            break;
                        case "ISBN":
                            //ISBN = td; //
                            System.out.println("ISBN=" + td);
                            break;
                        case "Set":
                            //Set = td; //
                            System.out.println("Set=" + td);
                            break;
                        case "Language":
                            //Langue = td; //
                            System.out.println("Language=" + td);
                            break;
                        default:
                            break;
                    }

                }


            }

        } catch (Exception httpExcep) {/////////// ICI ICI ici
            System.out.println("PAS DE VENDEUR=" + "urlDescription");

        }
    }
}
