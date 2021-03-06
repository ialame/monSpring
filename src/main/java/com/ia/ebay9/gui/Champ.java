package com.ia.ebay9.gui;

/**
 * Created by ia on 29/02/2020.
 */
public class Champ {
    int col;
    String titre;
    Object valeur;
    Champ(int col,String titre){
        this.col=col;
        this.titre=titre;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Object getValeur() {
        return valeur;
    }

    public void setValeur(Object valeur) {
        this.valeur = valeur;
    }
    public String toString(){
        return col+" , " + titre+" : "+valeur+"\n";
    }
}
