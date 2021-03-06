package com.pca.entity;

import javax.persistence.*;

@Entity
@Table( name = "particularite_translation" )
@PrimaryKeyJoinColumn( name = "id" )
public class ParticulariteTranslation {
    @Id @GeneratedValue( strategy= GenerationType.IDENTITY )
    private int id;
        private String nomComplet;
        private String nomZEBRA;
        private boolean isAvant = false;
        private boolean isParsable;
        private String nomImgPCA;
        private String nomImgHref;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getNomZEBRA() {
        return nomZEBRA;
    }

    public void setNomZEBRA(String nomZEBRA) {
        this.nomZEBRA = nomZEBRA;
    }

    public boolean isAvant() {
        return isAvant;
    }

    public void setAvant(boolean avant) {
        isAvant = avant;
    }

    public boolean isParsable() {
        return isParsable;
    }

    public void setParsable(boolean parsable) {
        isParsable = parsable;
    }

    public String getNomImgPCA() {
        return nomImgPCA;
    }

    public void setNomImgPCA(String nomImgPCA) {
        this.nomImgPCA = nomImgPCA;
    }

    public String getNomImgHref() {
        return nomImgHref;
    }

    public void setNomImgHref(String nomImgHref) {
        this.nomImgHref = nomImgHref;
    }
}
