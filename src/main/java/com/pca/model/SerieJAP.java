package com.pca.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name="seriejap" )
public class SerieJAP
{
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    private int id;
    private int idPCA;
    private String nom="Nom? à renseignier";
    private String nomJAP;
    private String nomDossier;


    @OneToMany( cascade = CascadeType.ALL ,mappedBy = "seriejap")
    private List<ExtensionJAP> extensionsjap= new ArrayList<>();


    public SerieJAP()
    {
    }

    public SerieJAP(String nom, String nomJAP, String nomDossier) {
        this.nom = nom;
        this.nomJAP = nomJAP;
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
    public String getNomJAP() {
        return nomJAP;
    }
    public void setNomJAP(String nomJAP) {
        this.nomJAP = nomJAP;
    }
    public String getNomDossier() {
        return nomDossier;
    }
    public void setNomDossier(String nomDossier) {
        this.nomDossier = nomDossier;
    }
    public List<ExtensionJAP> getExtensionsJAP() {
        return extensionsjap;
    }
    public void setExtensionsJAP(List<ExtensionJAP> extensionsjap) {
        this.extensionsjap = extensionsjap;
    }

}
