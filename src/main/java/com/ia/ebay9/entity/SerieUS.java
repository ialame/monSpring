package com.ia.ebay9.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name="serie" )
public class SerieUS
{
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    private int id;
    private int idPCA;
    private String nomFR;
    private String nomUS;
    private String nomDossier;

    @OneToMany( cascade = CascadeType.ALL ,mappedBy = "serie")
    private List<ExtensionUS> extensions= new ArrayList<>();


    public SerieUS()
    {
    }

    public SerieUS(String nomFR) {
        this.nomFR = nomFR;
    }

    @Override
    public String toString() { return "(" + id + ") " + nomUS ; }

    public Integer getId() { return id;}
    public void setId(int id) {this.id = id; }
    public int getIdPCA() { return idPCA;}
    public void setIdPCA(int idPCA) { this.idPCA = idPCA; }
    public String getNomFR() {return nomFR;}
    public void setNomFR(String nomFR) { this.nomFR = nomFR; }
    public String getNomUS() {  return nomUS;  }
    public void setNomUS(String nomUS) { this.nomUS = nomUS;  }

    public String getNomDossier() {
        return nomDossier;
    }
    public void setNomDossier(String nomDossier) {
        this.nomDossier = nomDossier;
    }
    public List<ExtensionUS> getExtensionsUS() {
        return extensions;
    }
    public void setExtensionsUS(List<ExtensionUS> extensions) {
        this.extensions = extensions;
    }
}
