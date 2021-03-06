package com.pca.gui;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by fxc on 19/10/2017.
 */
public class Vendeur {
    public String nom;
    public String adresse;
    public String cp;
    public String pays;
    public String tel;
    public String email;
    public String boutique;
    public String derniereLigne;
    public String derniereLigne2;


    public Vendeur(Document docPage){
        String  vendeur = "";
        Element ElementVendeur=null;
        Elements coordonneesVendeur=null;
        java.util.Iterator<Element> iteratorCoordonneesVendeur=null;
        if(docPage!=null && docPage.select("div.bsi-bn")!=null) {
            boutique =docPage.select("div.bsi-bn").text();
        }
        if(docPage!=null && docPage.select("div.bsi-cic")!=null)
            ElementVendeur = docPage.select("div.bsi-cic").first();
        if(ElementVendeur!=null) {
            if(ElementVendeur.select("div.bsi-c1")!=null) {
                coordonneesVendeur = ElementVendeur.select("div.bsi-c1");



                iteratorCoordonneesVendeur = coordonneesVendeur.select("div").iterator();
                if (iteratorCoordonneesVendeur.hasNext()) {
                    Element z = iteratorCoordonneesVendeur.next();
                    if (iteratorCoordonneesVendeur.hasNext()) {
                        z = iteratorCoordonneesVendeur.next();
                        nom = z.text();
                    }
                    if (iteratorCoordonneesVendeur.hasNext()) {
                        z = iteratorCoordonneesVendeur.next();
                        adresse = z.text();
                    }
                    if (iteratorCoordonneesVendeur.hasNext()) {
                        z = iteratorCoordonneesVendeur.next();
                        cp = z.text();
                    }
                    if (iteratorCoordonneesVendeur.hasNext()) {
                        z = iteratorCoordonneesVendeur.next();
                        pays = z.text();
                    }
                    if (iteratorCoordonneesVendeur.hasNext()) {
                        z = iteratorCoordonneesVendeur.next();
                        derniereLigne = z.text();
                    }
                    if (iteratorCoordonneesVendeur.hasNext()) {
                        z = iteratorCoordonneesVendeur.next();
                        derniereLigne2 = z.text();
                    }
                    //System.out.println("CCCCCCCCC=" + z.text());
                }
            }
            if(ElementVendeur.select("div.bsi-c2")!=null) {
                coordonneesVendeur = ElementVendeur.select("div.bsi-c2");
                iteratorCoordonneesVendeur = coordonneesVendeur.select("div").iterator();
                java.util.Iterator<Element> descriptionTelMailVendeur = null;
                if (iteratorCoordonneesVendeur.hasNext()) {
                    Element z = iteratorCoordonneesVendeur.next();
                    if (iteratorCoordonneesVendeur.hasNext()) {
                        z = iteratorCoordonneesVendeur.next();
                        descriptionTelMailVendeur = z.select("span").iterator();
                        if (descriptionTelMailVendeur != null) {
                            Element te = descriptionTelMailVendeur.next();
                            te = descriptionTelMailVendeur.next();
                            tel = te.text();

                        }
                    }
                    if (iteratorCoordonneesVendeur.hasNext()) {
                        z = iteratorCoordonneesVendeur.next();
                        descriptionTelMailVendeur = z.select("span").iterator();
                        if (descriptionTelMailVendeur != null) {
                            Element te = descriptionTelMailVendeur.next();
                            te = descriptionTelMailVendeur.next();
                            email = te.text();

                        }
                    }
                }

                //System.out.println("telVendeur=" + telVendeur);
                //System.out.println("emailVendeur=" + emailVendeur);
            }
        }
    }

}


