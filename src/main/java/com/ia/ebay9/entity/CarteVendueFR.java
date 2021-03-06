package com.ia.ebay9.entity;

import com.ia.ebay9.gui.Tools;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;

/**
 * Created by fxc on 19/10/2017.
 */
@Entity
@Table( name = "CarteVendue" )
@DiscriminatorValue(value="FR")
public class CarteVendueFR extends CarteVendue {
    private static final long serialVersionUID = 1L;
    CarteVendueFR(){
    }
    public CarteVendueFR(Element x, CarteEbay carteEbay) {
        super(carteEbay);
        this.carteEbay = carteEbay;
        titre = x.select("H3").text();
        //urlDescription = x.select("H3").first().child(0).attr("href");
        setDescription();
        if (titre.equals("") && x.text().indexOf("found from eBay international sellers") == -1) {
            //existe = false;
            return;//
        }
        setPrix(x.select(".s-item__price").text());
        setVente(x.select(".s-item__detail.s-item__detail--primary .s-item__purchase-options-with-icon").text());

        img = x.select(".s-item__image-img").attr("src");
        //if (img.indexOf("thumbs/") == -1) img = x.select(".img").attr("src");
        img = img.replace("thumbs/", "").replace("225", "1600");
        String date = x.select(".s-item__detail.s-item__detail--secondary .s-item__ended-date.s-item__endedDate").text();
        setDate(date);
        pays=x.select(".s-item__detail.s-item__detail--secondary .s-item__location.s-item__itemLocation").text();
        //pays = "France";
        //img = img.replace("https://i.ebayimg.com/images/","").replace("/s-l1600.jpg","");
        //System.out.println(x+"\n---------------------------------------------------\n");
    }

    public boolean titreContient2Noms(ArrayList<Pokemons> pokemons){
        String t = Tools.removeCaracSpecial(Tools.removeAccent(titre)).toLowerCase();
        //t=t.replace(Tools.removeAccent(carteEbay.nomEBAY.toLowerCase()),"");

        for(Pokemons pkm : pokemons){
            if(t.indexOf(pkm.getNomFR())!=-1) {
                return true;
            }
        }
        return false;
    }
    public void setDescription() {
        /*
        if(urlDescription.equals("https://www.ebay.fr/itm/CARTE-POKEMON-GRENOUSSE-ECRASFACE-12-39-BON-ETAT-LIVRAISON-GRATUITE/112395329562?hash=item1a2b48a01a:g:PVMAAOSwcgNZCu-b"))
            urlDescription=urlDescription;
*/
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
                    propriete =propriete.replace(" ","");
                    if (!TDs.hasNext()) continue;
                    td = TDs.next().text();
                    switch (propriete) {  //
                        case "État":
                            pos = td.indexOf(":");
                            if (pos == -1) continue;
                            String valeur = td.substring(0, pos).trim();
                            //etatDescription = valeur; //
                            System.out.println("État=" + valeur);
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
                        case "Version":
                            //version = td; //
                            System.out.println("Version=" + td);
                            break;
                        case "Commentaires du vendeur":
                            sellerNotes = td; //
                            System.out.println("Commentaires du vendeur=" + td);
                            break;
                        case "Set":
                            //Set = td; //
                            System.out.println("Set=" + td);
                            break;
                        case "Langue":
                            //Langue = td; //
                            System.out.println("Langue=" + td);
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
