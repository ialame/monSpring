package com.ia.ebay9.entity;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name="image_carte" )
public class Image_Carte
{
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String langue;
    private String fichier;

    @OneToMany( mappedBy = "imageCarte",orphanRemoval = true)
    private List<HistoriqueImageCarte> historiqueImageCartes = new ArrayList<>();


    @Column(name="caracteristiques" , length = 65535, columnDefinition="LONGTEXT")
    @Type(type="text")
    private String caracteristiques;
    private int statut;
    @Column(name="infos" , length = 65535, columnDefinition="TEXT")
    @Type(type="text")
    private String infos;

    @ManyToOne
    @JoinColumn(name="extensionSource_id", nullable=false)
    private ExtensionSource extensionSource;

    @ManyToOne
    @JoinColumn(name="carte_id", nullable=true)
    private Carte carte;

    @ManyToOne
    @JoinColumn(name="extension_id", nullable=true)
    private ExtensionUS extension;

    @ManyToOne
    @JoinColumn(name="extensionjap_id", nullable=true)
    private ExtensionJAP extensionjap;

    @ManyToOne
    @JoinColumn(name="extensionkr_id", nullable=true)
    private ExtensionKR extensionkr;

    private LocalDateTime downloaded_at = LocalDateTime.now();
    private String tailleImg;
    //private boolean cacherSerieExtension = false;
    //private String seqCartes;
    private String src;

    public Image_Carte()
    {

    }
/*
    public ImageCarte(String fichier, ExtensionSource extensionSource) {
        this.fichier = fichier;
        this.extensionSource = extensionSource;
    }

 */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getFichier() {
        return fichier;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }

    public String getCaracteristiques() {
        return caracteristiques;
    }

    public void setCaracteristiques(String caracteristiques) {
        this.caracteristiques = caracteristiques;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }

    public ExtensionSource getExtensionSource() {
        return extensionSource;
    }

    public void setExtensionSource(ExtensionSource extensionSource) {
        this.extensionSource = extensionSource;
    }

    public Carte getCarte() {
        return carte;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    public ExtensionUS getExtension() {
        return extension;
    }

    public void setExtension(ExtensionUS extension) {
        this.extension = extension;
    }

    public ExtensionJAP getExtensionjap() {
        return extensionjap;
    }

    public void setExtensionjap(ExtensionJAP extensionjap) {
        this.extensionjap = extensionjap;
    }

    public ExtensionKR getExtensionkr() {
        return extensionkr;
    }

    public void setExtensionkr(ExtensionKR extensionkr) {
        this.extensionkr = extensionkr;
    }

    public LocalDateTime getDownloaded_at() {
        return downloaded_at;
    }

    public void setDownloaded_at(LocalDateTime downloaded_at) {
        this.downloaded_at = downloaded_at;
    }

    public String getTailleImg() {
        return tailleImg;
    }

    public void setTailleImg(String tailleImg) {
        this.tailleImg = tailleImg;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public List<HistoriqueImageCarte> getHistoriqueImageCartes() {
        return historiqueImageCartes;
    }

    public void setHistoriqueImageCartes(List<HistoriqueImageCarte> historiqueImageCartes) {
        this.historiqueImageCartes = historiqueImageCartes;
    }
}
