package com.pca.model;

import java.time.LocalDateTime;
//import java.sql.Date;

/**
 * Created by fxc on 25/04/2019.
 */


public abstract class Extension implements Comparable<Extension> {

    public String idPCA ;
    public LocalDateTime dateSortie;
    public String nomRaccourci = null;
    public String SerieExtensionZEBRA = "A renseigner ...";
    public String PremierNumero = null;
    public String numSur;

    public String getNumSur() {
        return numSur;
    }

    public void setNumSur(String numSur) {
        this.numSur = numSur;
    }

    public String getSerieExtensionZEBRA() {
        return SerieExtensionZEBRA;
    }

    public void setSerieExtensionZEBRA(String serieExtensionZEBRA) {
        this.SerieExtensionZEBRA = serieExtensionZEBRA;
    }


    public abstract Integer getId();

    public abstract void setId(Integer id);
    public abstract void setNomFR(String nomFR);
    public abstract void setNomUS(String nomUS);
    public abstract void setNom(String nom);
    public abstract void setPromo(boolean promo);
    public abstract boolean isPromo();


    public LocalDateTime getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(LocalDateTime dateSortie) {
        this.dateSortie = dateSortie;
    }

    public String getNomRaccourci() {
        return nomRaccourci;
    }

    public void setNomRaccourci(String nomRaccourci) {
        this.nomRaccourci = nomRaccourci;
    }


    public String getIdPCA() {
        return idPCA;
    }

    public void setIdPCA(String idPCA) {
        this.idPCA = idPCA;
    }

    public String getPremierNumero() {
        return PremierNumero;
    }

    public void setPremierNumero(String premierNumero) {
        PremierNumero = premierNumero;
    }

    @Override
    public int compareTo(Extension o) {
        return this.getId()-o.getId();
    }


}

