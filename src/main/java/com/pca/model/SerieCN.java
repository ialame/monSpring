package com.pca.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name="seriecn" )
public class SerieCN
{
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    private int id;
    private Integer idPCA;
    private String nom;
    private String nomCN;
    private String nomDossier;


    @OneToMany( cascade = CascadeType.ALL ,mappedBy = "seriecn")
    private List<ExtensionCN> extensionscn= new ArrayList<>();



    public SerieCN()
    {
    }

    public SerieCN(String nom, String nomCN, String nomDossier) {
        this.nom = nom;
        this.nomCN = nomCN;
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

    public String getNomCN() {
        return nomCN;
    }

    public void setNomCN(String nomCN) {
        this.nomCN = nomCN;
    }

    public List<ExtensionCN> getExtensionscn() {
        return extensionscn;
    }

    public void setExtensionscn(List<ExtensionCN> extensionscn) {
        this.extensionscn = extensionscn;
    }

    public Integer getIdPCA() {
        return idPCA;
    }

    public void setIdPCA(Integer idPCA) {
        this.idPCA = idPCA;
    }
}
