package com.pca.gui;

import com.pca.entity.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by fxc on 07/03/2020.
 */
public class ParseExtensionPok implements Runnable {
    protected Extension extension=null;
    protected ExtensionUS extensionPOK=null;
    protected ExtensionJAP extensionJAP=null;
    protected String premierNumero;
    protected ApplicationPM app;
    protected PageGui page;
    public JTablePok jTablePok;
    public Thread t,t2;


    ParseExtensionPok(Extension extension, PageGui page){
        this.extension=extension;
        if(extension instanceof ExtensionUS)
            this.extensionPOK=(ExtensionUS) extension;
        else if(extension instanceof ExtensionJAP)
            this.extensionJAP=(ExtensionJAP) extension;
        this.page=page;
        jTablePok = page.jTablePok;// new JTablePok(new ArrayList<CarteUsJap>(),app);
        t = new Thread(jTablePok);
        t.start();

        t2 = new Thread(this);
        t2.start();
    }

    public ParseExtensionPok(Object extension, ApplicationPM app) {
    }



    @Override
    public void run() {
        //page.bouton.setEnabled(false);
        String hrefNouvellesCartes=null;
        String extensionTable=null;
        if(extension instanceof ExtensionJAP) {
            hrefNouvellesCartes = ((ExtensionJAP) extension).getExpansionBulbapedias().get(0).getUrl();
            extensionTable = ((ExtensionJAP) extension).getExpansionBulbapedias().get(0).getNomTableau();
            premierNumero = ((ExtensionJAP) extension).getExpansionBulbapedias().get(0).getPremierNumero();
        }else if(extension instanceof ExtensionUS) {
            hrefNouvellesCartes = ((ExtensionUS) extension).getExpansionBulbapedias().get(0).getUrl();
            extensionTable = ((ExtensionUS) extension).getExpansionBulbapedias().get(0).getNomTableau();
            premierNumero = ((ExtensionUS) extension).getExpansionBulbapedias().get(0).getPremierNumero();
        }
        if(!extension.isPromo()){
            jTablePok.getTable().getColumn("promos").setMinWidth(0);
            jTablePok.getTable().getColumn("promos").setMaxWidth(0);
        }
        int iii = 0;
        Document docPage = null, doc = null;

        try {
            String agent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";
            docPage = Jsoup.connect(hrefNouvellesCartes).userAgent(agent).get();
            doc = Jsoup.parseBodyFragment(docPage.toString());

            ///////////////////////////////////////////////////////
            // On cherche la bonne table
            Iterator<Element> BIG = doc.select("big").iterator();// c'est un BIG dans TABLE
            Element big = null;

            while (BIG.hasNext()) {
                big = BIG.next();
                if (big.text().toUpperCase().equals(extensionTable.toUpperCase()) && !big.child(0).tagName().toUpperCase().equals("BIG")
                        && !big.parent().tagName().toUpperCase().equals("BIG")
                        ) {
                    while (!big.parent().tagName().toUpperCase().equals("TABLE")) big = big.parent();
                    big = big.parent();
                    if (big.text().indexOf("No.") == -1)
                        while (!big.parent().tagName().toUpperCase().equals("TABLE")) big = big.parent();
                    if (big.text().indexOf("No.") == -1) return;// return Extension;
                    if (big.parent().text().indexOf(premierNumero) != -1)
                        break;
                }

            }

            if (big == null) return;// return Extension;
            while (big.attr("style").indexOf("float:left;") == -1)
                big = big.parent();

            Iterator<Element> TR = big.select("tr").iterator();//  BIGtr.children().first().children().first().children().first().children().iterator();// tbmulti.select("table.roundy").iterator();

            TR.next();// les entêtes

            Element tr = null;
            int N = 0;
            while (TR.hasNext()) {
                tr = TR.next();
                N++;
            }

            TR = big.select("tr").iterator();//  BIGtr.children().first().children().first().children().first().children().iterator();// tbmulti.select("table.roundy").iterator();
            TR.next();// les entêtes
            int k = 1;
            ArrayList<Element> TRprim = big.select("tr");
            boolean promo = false, premierefois =true;
            while (TR.hasNext()) {
                tr = TR.next();// une ligne parmi les lignes
                k++;
                if(k==5)
                    k=k;
                //if(tr.select("th").size()>0 && tr.select("th").last().text().trim().equals("Promotion"))
                //    promo =true;
                // on filtre les lignes dont l'arrière plan est (background:#FFF) sinon on passe à la ligne suivante
                if (tr.children().first().attr("style").indexOf("background:#FFFFFF") >= 0) {
                    if(premierefois){
                        jTablePok.bar.setMinimum(k);
                        jTablePok.bar.setMaximum(N);
                        premierefois=false;
                    }
                    CarteUsJap carteUsJap = new CarteUsJap();
                    // A T T E N S I O N à cause de la migration
                    carteUsJap.setExtension(extension);
                    //carteUsJap.jap.setJapIndice(k);
                    //carteUsJap.jap.setIdPrimJap(k+"");


/////////////////////////////////////////////////////////////////////
                    final int percent=k;
                    try
                    {
                        SwingUtilities.invokeLater(new Runnable( ) {
                            public void run( ) {
                                jTablePok.bar.setValue(percent);
                            }
                        });
                        Thread.sleep(1);
                    } catch (InterruptedException e) {;}
/////////////////////////////////////////////////////////////////////
                    carteUsJap.determination(tr);

                    if (carteUsJap.Card == null)
                        continue;
                    /*
                    if(carteUsJap.Card.equals("Tyranitar ex"))
                        carteUsJap=carteUsJap;
                    else
                        continue;

                     */
                    if(extension instanceof ExtensionJAP && carteUsJap.jap.getJapNum().equals("—")
                            && carteUsJap.nomsGeneralises.size()>0
                            && carteUsJap.nomsGeneralises.get(0).nomCarte.indexOf("nergy")!=-1)
                        continue;
                    carteUsJap.equivalent();
                    int k1 = 0, k2 = 0;
                    k1 = carteUsJap.nomsGeneralises.size();
                    k2 = carteUsJap.getPromos().size();
                    if(carteUsJap.nomsGeneralises.get(0).statut==K.STATUS_CARTE_PAGE_DOES_NOT_EXIST)
                        carteUsJap.status=K.STATUS_CARTE_PAGE_DOES_NOT_EXIST;
                    if (k1 == 1) {
                        carteUsJap.setNomGeneralise(carteUsJap.nomsGeneralises.get(0));
                        if(extension instanceof ExtensionJAP) {
                            //carteUsJap.jap.setJapExpansion(carteUsJap.getExtension().getNomTableauBulbapedia());
                            carteUsJap.jap.setParticularites(carteUsJap.getNomGeneralise().getParticularites());
                            carteUsJap.jap.getCrochets().add(carteUsJap.getNomGeneralise().getCrochet());
                            carteUsJap.jap.setExtensionJAP((ExtensionJAP) carteUsJap.getExtension());
                            carteUsJap.jap.getExtensionJAP().getCartes().add(carteUsJap.jap);
                            carteUsJap.jap.setPromos(carteUsJap.getPromos());
                            //if(carteUsJap.status==K.STATUS_CARTE_PAGE_DOES_NOT_EXIST)
                            //    carteUsJap.jap.setS;
                            for (Promo p : carteUsJap.getPromos())
                                p.setCartePokemon(carteUsJap.jap);
                        }else{
                            //carteUsJap.us.setJapExpansion(carteUsJap.getExtension().getNomTableauBulbapedia());
                            carteUsJap.us.setParticularites(carteUsJap.getNomGeneralise().getParticularites());
                            carteUsJap.us.getCrochets().add(carteUsJap.getNomGeneralise().getCrochet());
                            carteUsJap.us.setExtensionUS((ExtensionUS) carteUsJap.getExtension());
                            carteUsJap.us.getExtensionUS().getCartes().add(carteUsJap.us);
                            carteUsJap.us.setPromos(carteUsJap.getPromos());
                            for (Promo p : carteUsJap.getPromos())
                                p.setCartePokemon(carteUsJap.us);
                        }
                        jTablePok.getModele().addCarte(carteUsJap);
                        //carteUsJap.save();

                    }else {
                        for (int i = 0; i < k1; i++) {
                            CarteUsJap c = carteUsJap.copy();
                            NomGeneralise nomGeneralise = carteUsJap.nomsGeneralises.get(i);

                            String nomCarte = carteUsJap.getCard();
                            //                 M I G R A T I O N
                            if (k1 == k2) {
                                Promo p = carteUsJap.getPromos().get(i);
                                ArrayList<Promo> P = new ArrayList<Promo>();
                                P.add(p);
                                if (p.getCharset().equals("jap"))
                                    c.jap.setPromos(P);
                                else if (p.getCharset().equals("us"))
                                    c.us.setPromos(P);

                            } else if (carteUsJap.getPromos().size() != 0) {
                                if (carteUsJap.getPromos().get(i).getCharset().equals("jap"))
                                    c.jap.setPromos(carteUsJap.getPromos());
                                else if (carteUsJap.getPromos().get(i).getCharset().equals("us"))
                                    c.us.setPromos(carteUsJap.getPromos());
                            }

                            if(extension instanceof ExtensionJAP){
                                c.jap.setParticularites(nomGeneralise.getParticularites());
                                c.jap.setCard(nomCarte);
                            }else if(extension instanceof ExtensionUS){
                                c.us.setParticularites(nomGeneralise.getParticularites());
                                c.us.setCard(nomCarte);
                            }
                            jTablePok.getModele().addCarte(c);
                            //carteUsJap.save();

                        }

                    }
                }

            }

            //jTablePok.getModele().save();


            JOptionPane.showMessageDialog(jTablePok, "Parsing terminé !");
            page.bouton.setEnabled(true);
            boolean b =true;
            for(Object o: jTablePok.getModele().getCartes()){
                CarteUsJap carte =(CarteUsJap)o;
                if(!carte.isUsEnregistrable()){
                    b=false;
                    break;
                }
            }
            //jTableJAP.boutonSave.setEnabled(b);
            //app.jap.bAjouter.setEnabled(true);
            //}
        } catch (Exception httpExcep) {/////////// ICI ICI ici
            System.out.println("ERREUR    =" + httpExcep + "   ------>   " + extension);

        }




    }
}

