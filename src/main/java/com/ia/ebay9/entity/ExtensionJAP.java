package com.ia.ebay9.entity;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name="extensionjap" )
public class ExtensionJAP extends Extension
{
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String nom;
    private String sousExtension;
    private String japName;
    private String japTraductionName;
    //private String URLpokellector;

    //private String extensionSources;
    private String nomDossier;

    @ManyToOne( cascade = CascadeType.MERGE )
    @JoinColumn(name="seriejap_id", nullable=false)
    private SerieJAP seriejap;

    private String SerieExtensionZEBRA = "A renseigner ...";
/*
    @OneToMany( cascade=CascadeType.ALL)
    @JoinTable( name = "cartePokemon", joinColumns = @JoinColumn(name = "extensionjap_id"),inverseJoinColumns = @JoinColumn( name = "id" ) )
    private List<CartePokemon> cartes= new ArrayList<>();
*/
    @OneToMany( mappedBy = "extensionjap",orphanRemoval = true)
    private List<CartePokemon> cartes = new ArrayList<>();

    @OneToMany(cascade=CascadeType.ALL,mappedBy = "extensionJAP",orphanRemoval = true)
    private List<ExpansionBulbapedia> expansionBulbapedias = new ArrayList<>();

    @OneToMany( cascade=CascadeType.ALL)
    @JoinTable( name = "image_carte", joinColumns = @JoinColumn(name = "extensionjap_id"),inverseJoinColumns = @JoinColumn( name = "id" ) )
    private List<Image_Carte> imageCartes= new ArrayList<>();

    @ManyToMany( cascade = CascadeType.ALL )
    @JoinTable( name = "extensionjap_extensionsource",
            joinColumns = @JoinColumn( name = "extensionjap_id" ),
            inverseJoinColumns = @JoinColumn( name = "extensionsource_id" ) )
    private List<ExtensionSource> extensionSources = new ArrayList<>();

    private LocalDateTime dateSortie = LocalDateTime.now();
    private String nomRaccourci;
    //private boolean cacherSerieExtension = false;
    private String numSur;
    private boolean isPromo = false;
    private boolean hasParseJsoupIrregulier;
    private boolean isActive = true;
    //private String extensionConcatenees;
    public ExtensionJAP()
    {

    }

    public ExtensionJAP(String nom, SerieJAP seriejap) {
        this.nom = nom;
        this.seriejap = seriejap;
        this.setSerieExtensionZEBRA(nom+seriejap.getNom());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public void setNomFR(String nomFR) {
        this.nom = nomFR;
    }

    @Override
    public void setNomUS(String nomUS) {
        this.nom = nomUS;
    }

    public String getIdPCA() {
        return idPCA;
    }

    public void setIdPCA(String idPCA) {
        this.idPCA = idPCA;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSousExtension() {
        return sousExtension;
    }

    public void setSousExtension(String sousExtension) {
        this.sousExtension = sousExtension;
    }

    public String getJapName() {
        return japName;
    }

    public void setJapName(String japName) {
        this.japName = japName;
    }

    public String getJapTraductionName() {
        return japTraductionName;
    }

    public void setJapTraductionName(String japTraductionName) {
        this.japTraductionName = japTraductionName;
    }

    public String getNomDossier() {
        return nomDossier;
    }

    public void setNomDossier(String nomDossier) {
        this.nomDossier = nomDossier;
    }

    public SerieJAP getSerieJAP() {
        return seriejap;
    }

    public void setSerieJAP(SerieJAP seriejap) {
        this.seriejap = seriejap;
    }

    public String getSerieExtensionZEBRA() {
        return SerieExtensionZEBRA;
    }

    public void setSerieExtensionZEBRA(String serieExtensionZEBRA) {
        SerieExtensionZEBRA = serieExtensionZEBRA;
    }

    public List<CartePokemon> getCartes() {
        return cartes;
    }

    public void setCartes(List<CartePokemon> cartes) {
        this.cartes = cartes;
    }


    public SerieJAP getSeriejap() {
        return seriejap;
    }

    public void setSeriejap(SerieJAP seriejap) {
        this.seriejap = seriejap;
    }

    @Override
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

    public String getNumSur() {
        return numSur;
    }

    public void setNumSur(String numSur) {
        this.numSur = numSur;
    }

    public boolean isPromo() {
        return isPromo;
    }

    public void setPromo(boolean promo) {
        isPromo = promo;
    }

    public boolean isHasParseJsoupIrregulier() {
        return hasParseJsoupIrregulier;
    }

    public void setHasParseJsoupIrregulier(boolean hasParseJsoupIrregulier) {
        this.hasParseJsoupIrregulier = hasParseJsoupIrregulier;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    @Override
    public String toString() {
        return "(" + id + ") " + nom ;
    }

    public List<Image_Carte> getImageCartes() {
        return imageCartes;
    }

    public void setImageCartes(List<Image_Carte> imageCartes) {
        this.imageCartes = imageCartes;
    }

    public List<ExpansionBulbapedia> getExpansionBulbapedias() {
        return expansionBulbapedias;
    }

    public void setExpansionBulbapedias(List<ExpansionBulbapedia> expansionBulbapedias) {
        this.expansionBulbapedias = expansionBulbapedias;
    }

    public List<ExtensionSource> getExtensionSources() {
        return extensionSources;
    }

    public void setExtensionSources(List<ExtensionSource> extensionSources) {
        this.extensionSources = extensionSources;
    }
}
