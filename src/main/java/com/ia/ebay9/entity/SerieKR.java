package com.ia.ebay9.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name="seriekr" )
public class SerieKR
{
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    private int id;
    private int idPCA;
    private String nom;
    private String nomKR;
    private String nomDossier;


    @OneToMany( cascade = CascadeType.ALL ,mappedBy = "seriekr")
    private List<ExtensionKR> extensionskr= new ArrayList<>();



    public SerieKR()
    {
    }

    public SerieKR(String nom, String nomKR, String nomDossier) {
        this.nom = nom;
        this.nomKR = nomKR;
        this.nomDossier = nomDossier;
    }
    @Override
    public String toString() {
        return "(" + id + ") " + nom ;
    }

    public Integer getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdPCA() {
        return idPCA;
    }
    public void setIdPCA(int idPCA) {
        this.idPCA = idPCA;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomDossier() {
        return nomDossier;
    }
    public void setNomDossier(String nomDossier) {
        this.nomDossier = nomDossier;
    }

    public String getNomKR() {
        return nomKR;
    }

    public void setNomKR(String nomKR) {
        this.nomKR = nomKR;
    }

    public List<ExtensionKR> getExtensionskr() {
        return extensionskr;
    }

    public void setExtensionskr(List<ExtensionKR> extensionskr) {
        this.extensionskr = extensionskr;
    }
}
