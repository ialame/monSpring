package com.pca.gui;


import com.pca.model.Crochet;
import com.pca.model.Particularite;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;


public class ParseNomGeneralise {
    public List<NomGeneralise> nomsGeneralises = new ArrayList<>();
    int statut = 0;

    ParseNomGeneralise(Element td){
            for(String nomBrut : td.html().split("<br>")) {
                nomsGeneralises.add(parse(nomBrut));
            }
        }
    // La fonction parse
    public NomGeneralise parse(String nomBrut){
        List<Particularite> particularites = new ArrayList<>();
        Crochet crochet = null;
        String nomCarte=null, href0=null, nomCrochet=null;
        boolean gras = false;
        ArrayList<Element> elemNom =  Jsoup.parseBodyFragment(nomBrut).body().children();//.get(1).tag() // td.html().split("<br>")[0]
        String[] toto = new String[]{"a","b"};
        ArrayList<String[]> ts = new ArrayList<>();

        for(Element e : elemNom) {
            switch (e.tag().toString()) {
                case "a":
                    String text = e.text();// text="" si particularité
                    String part = ""; // part="" si non particularité
                    String[] TEXT = text.split(" ");
                    if(TEXT.length>1) {
                        String text2 = TEXT[TEXT.length - 1];
                        if (!text2.equals("")){
                            Particularite p = Particularite.chercheParticularite(text2);
                            if(p!=null){
                                text = text.replace(text2, "").trim();
                                particularites.add(p);
                            }
                        }
                    }
                    if (!text.equals("")){
                        Particularite p = Particularite.chercheParticularite(text);
                        if(p!=null){
                            part=text;
                            text = "";
                            particularites.add(p);
                        }
                    }
                    ts.add(new String[]{text,part, e.attr("title"), e.attr("href")});
                    break;
                case "small":
                    int pos = e.text().indexOf("[");
                    if (pos != -1) {
                        nomCrochet= e.text().substring(pos + 1, e.text().length() - 1);
                    } else {
                        gras = true ;
                        nomCrochet=e.text();
                    }
                    for(Crochet c : ApplicationPM.crochets){
                        if( c.getNom()!= null && c.getNom().equals(nomCrochet)) {
                            crochet = c;
                            break;
                        }
                    }
                    break;
                default:

            }
        }
        int p=0,q=ts.size();
        while(ts.get(p)[0].equals(""))p++;
        while(ts.get(q-1)[0].equals(""))q--;
        for(int k=0;k<p;k++){
            String s1 =ts.get(k)[2].trim(), s2 = null;
            if(ts.get(k)[3]!=null){
                int pos1 = ts.get(k)[3].indexOf(":");
                int pos2 = ts.get(k)[3].indexOf(".");
                if(pos1!=-1 && pos2 != -1)
                    s2 =ts.get(k)[3].substring(pos1+1,pos2);
                else{
                    Particularite Par = Particularite.chercheParticularite(ts.get(k)[1]);
                    if(Par!=null){
                        if(!particularites.contains(Par))
                            particularites.add(Par);
                    }
                    continue;
                }

            }
            for(Particularite particularite : ApplicationPM.particularites){
                if( particularite.getBulbapedia()!= null && particularite.getBulbapedia().equals(s1)) {
                    if(particularite.getImgHrefBulbapedia()==null
                            || (particularite.getImgHrefBulbapedia()!=null  && particularite.getImgHrefBulbapedia().equals(s2)))
                        particularites.add(particularite);
                    break;
                }
            }
        }
        for(int k=p;k<q;k++){
            if(!ts.get(k)[0].equals("")) {
                if(nomCarte==null)
                    nomCarte =  ts.get(k)[0];
                else
                    nomCarte += " " + ts.get(k)[0];
                if(ts.get(k)[2].indexOf("page does not exist")!=-1)
                    statut=K.STATUS_CARTE_PAGE_DOES_NOT_EXIST;
                href0 = ts.get(k)[3];
                continue;
            }
            String s1 =ts.get(k)[2].trim(), s2 = null;
            if(ts.get(k)[3]!=null){
                int pos1 = ts.get(k)[3].indexOf(":");
                int pos2 = ts.get(k)[3].indexOf(".");
                if(pos1!=-1 && pos2!=-1)
                    s2 =ts.get(k)[3].substring(pos1+1,pos2);
                else{
                    Particularite Par = Particularite.chercheParticularite(ts.get(k)[1]);
                    if(Par!=null){
                        if(!particularites.contains(Par))
                            particularites.add(Par);
                    }
                    continue;
                }
            }
            if ("Darkness Colorless Grass Metal Fighting Water Fire Lightning Psychic".indexOf(s1)!=0)
                nomCarte += " " + s1;
            for(Particularite particularite : ApplicationPM.particularites){
                if( particularite.getBulbapedia()!= null && particularite.getBulbapedia().equals(s1)) {
                    if(s2==null || particularite.getImgHrefBulbapedia() == null ||  particularite.getImgHrefBulbapedia().equals(s2))
                        nomCarte += " " + s1;
                    break;
                }
            }
        }
            for (int k = q; k < ts.size(); k++) {
                String s1 =ts.get(k)[2].trim(), s2 = null;
                if(ts.get(k)[2]!=null){
                   int pos1 = ts.get(k)[3].indexOf(":");
                   int pos2 = ts.get(k)[3].indexOf(".");
                    if(pos1!=-1 && pos2!=-1)
                        s2 =ts.get(k)[3].substring(pos1+1,pos2);
                    else{
                        Particularite Par = Particularite.chercheParticularite(ts.get(k)[1]);
                        if(Par!=null){
                            if(!particularites.contains(Par))
                                particularites.add(Par);
                        }
                        continue;
                    }
                }
                for(Particularite particularite : ApplicationPM.particularites){
                    if( particularite.getBulbapedia()!= null && particularite.getBulbapedia().equals(s1)) {
                        if(particularite.getImgHrefBulbapedia()==null
                                || (particularite.getImgHrefBulbapedia()!=null  && particularite.getImgHrefBulbapedia().equals(s2)))
                                particularites.add(particularite);
                        break;
                    }
                }
            }
            NomGeneralise lg = new NomGeneralise(nomCarte,href0, particularites,crochet,statut);
            return lg;
    }


}

class NomGeneralise {
    String nomCarte;
    String href0;
    List<Particularite>  particularites = new ArrayList<>();
    Crochet crochet;
    String gras;
    int statut;

    public NomGeneralise(String nomCarte, String href0, List<Particularite>  particularites, Crochet crochet,int status) {
        this.nomCarte = nomCarte;
        this.href0 = href0;
        this.particularites = particularites;
        this.crochet = crochet;
        this.gras = gras;
        this.statut=status;
    }

    public String getNomCarte() {
        return nomCarte;
    }

    public void setNomCarte(String nomCarte) {
        this.nomCarte = nomCarte;
    }

    public String getHref0() {
        return href0;
    }

    public void setHref0(String href0) {
        this.href0 = href0;
    }

    public List<Particularite> getParticularites() {
        return particularites;
    }

    public void setParticularites(List<Particularite> particularites) {
        this.particularites = particularites;
    }

    public Crochet getCrochet() {
        return crochet;
    }

    public void setCrochet(Crochet crochet) {
        this.crochet = crochet;
    }

    public String getGras() {
        return gras;
    }

    public void setGras(String gras) {
        this.gras = gras;
    }
}
