package com.ia.ebay9.entity;
import javax.persistence.*;

@Entity
@Table( name="expansionBulbapedia" )
public class ExpansionBulbapedia
{
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="charset", nullable=false)
    private String charset;

    @ManyToOne
    @JoinColumn(name="extensionjap_id", nullable=true)
    private ExtensionJAP extensionJAP;

    @ManyToOne
    @JoinColumn(name="extension_id", nullable=true)
    private ExtensionUS extensionUS;

    private String type;
    private String nom;
    private String sousType;
    private String sousNom;
    private String premierNumero;
    private String nomTableau;
    private String url;
    public ExpansionBulbapedia()
    {

    }

    public ExpansionBulbapedia(String charset) {
        this.charset=charset;
    }

    public ExpansionBulbapedia(String charset, String type, String nom) {
        this.charset=charset;
        this.type = type;
        this.nom = nom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public ExtensionJAP getExtensionJAP() {
        return extensionJAP;
    }

    public void setExtensionJAP(ExtensionJAP extensionJAP) {
        this.extensionJAP = extensionJAP;
    }

    public ExtensionUS getExtensionUS() {
        return extensionUS;
    }

    public void setExtensionUS(ExtensionUS extensionUS) {
        this.extensionUS = extensionUS;
    }



    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSousType() {
        return sousType;
    }

    public void setSousType(String sousType) {
        this.sousType = sousType;
    }

    public String getSousNom() {
        return sousNom;
    }

    public void setSousNom(String sousNom) {
        this.sousNom = sousNom;
    }

    public String getPremierNumero() {
        return premierNumero;
    }

    public void setPremierNumero(String premierNumero) {
        this.premierNumero = premierNumero;
    }

    public String getNomTableau() {
        return nomTableau;
    }

    public void setNomTableau(String nomTableau) {
        this.nomTableau = nomTableau;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



}
