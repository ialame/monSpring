package com.pca.model;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name="extension" )
public class ExtensionUS  extends Extension //implements ExtensionInterface
{
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer id;

    private String nomFR;
    private String nomUS;
    private String nomIT;
    private String nomDE;
    private String nomES;
    private String nomPT;

    private String nomTableauBulbapedia;
    //private String enExpansionBulbapedia;
    private String nomTableauWikiIT;
    private String nomTableauWikiDE;
    private String nomTableauWikiES;
    private String nomTableauWikiPT;
    private String nomJap = "Jap";

    //private String SerieExtensionZEBRA= "A renseigner ...";

    private String SerieExtensionZEBRAus;
    private String SerieExtensionZEBRAit;
    private String SerieExtensionZEBRAde;
    private String SerieExtensionZEBRAes;
    private String SerieExtensionZEBRApt;
    private String nomDossier;
    //private String nomRaccourci;
    private String premierNumero;
    private String codePkmncards;

    private String codePokemonTCG;

    private String nbCartes;

    @ManyToOne
    @JoinColumn(name="serie_id", nullable=false)
    private SerieUS serie;

    private boolean fr = false;
    private boolean us = false;


    @OneToMany( mappedBy = "extensionus",orphanRemoval = true)
    private List<CartePokemon> cartes = new ArrayList<>();
/*
    @OneToMany( cascade=CascadeType.ALL)
    @JoinTable( name = "cartePokemon", joinColumns = @JoinColumn(name = "extension_id"),inverseJoinColumns = @JoinColumn( name = "id" ) )
    private List<CartePokemon> cartes= new ArrayList<>();
*/
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "extensionUS",orphanRemoval = true)
    private List<ExpansionBulbapedia> expansionBulbapedias = new ArrayList<>();

    @OneToMany( cascade=CascadeType.ALL)
    @JoinTable( name = "image_carte", joinColumns = @JoinColumn(name = "extension_id"),inverseJoinColumns = @JoinColumn( name = "id" ) )
    private List<Image_Carte> imageCartes= new ArrayList<>();
/*
    @OneToMany( cascade=CascadeType.ALL)
    @JoinTable( name = "expansionBulbapedia", joinColumns = @JoinColumn(name = "extension_id"),inverseJoinColumns = @JoinColumn( name = "id" ) )
    private List<ExpansionBulbapedia> expansionBulbapedias= new ArrayList<>();
*/

    private LocalDateTime dateSortie = LocalDateTime.now();

    private boolean isPromo = false;
    private String urlUS;
    private String urlIT;
    private String urlDE;
    private String urlES;
    private String urlPT;
    private boolean isActive = true;

    public ExtensionUS()
    {
    }

    public ExtensionUS(String nomFR, SerieUS serie) {
        this.nomFR = nomFR;
        this.serie = serie;
    }

    public List<CartePokemon> getCartes() {
        return cartes;
    }

    public void setCartes(List<CartePokemon> cartes) {
        this.cartes = cartes;
    }
    public String getZEBRA(String langue){return null;};
    public ExtensionUS getExtensionPCA(String langue, String langueMention){return null;};
    public SerieUS getSerieUS(){return serie;};


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomFR() {
        return nomFR;
    }

    public void setNomFR(String nomFR) {
        this.nomFR = nomFR;
    }

    public String getNomUS() {
        return nomUS;
    }

    public void setNomUS(String nomUS) {
        this.nomUS = nomUS;
    }

    @Override
    public void setNom(String nom) {

    }

    public String getNomIT() {
        return nomIT;
    }

    public void setNomIT(String nomIT) {
        this.nomIT = nomIT;
    }

    public String getNomDE() {
        return nomDE;
    }

    public void setNomDE(String nomDE) {
        this.nomDE = nomDE;
    }

    public String getNomES() {
        return nomES;
    }

    public void setNomES(String nomES) {
        this.nomES = nomES;
    }

    public String getNomPT() {
        return nomPT;
    }

    public void setNomPT(String nomPT) {
        this.nomPT = nomPT;
    }

    public String getNomTableauWikiIT() {
        return nomTableauWikiIT;
    }

    public void setNomTableauWikiIT(String nomTableauWikiIT) {
        this.nomTableauWikiIT = nomTableauWikiIT;
    }

    public String getNomTableauWikiDE() {
        return nomTableauWikiDE;
    }

    public void setNomTableauWikiDE(String nomTableauWikiDE) {
        this.nomTableauWikiDE = nomTableauWikiDE;
    }

    public String getNomTableauWikiES() {
        return nomTableauWikiES;
    }

    public void setNomTableauWikiES(String nomTableauWikiES) {
        this.nomTableauWikiES = nomTableauWikiES;
    }

    public String getNomTableauWikiPT() {
        return nomTableauWikiPT;
    }

    public void setNomTableauWikiPT(String nomTableauWikiPT) {
        this.nomTableauWikiPT = nomTableauWikiPT;
    }

    public String getNomJap() {
        return nomJap;
    }

    public void setNomJap(String nomJap) {
        this.nomJap = nomJap;
    }

    public String getSerieExtensionZEBRA() {
        return SerieExtensionZEBRA;
    }

    public void setSerieExtensionZEBRA(String serieExtensionZEBRA) {
        SerieExtensionZEBRA = serieExtensionZEBRA;
    }

    public String getSerieExtensionZEBRAus() {
        return SerieExtensionZEBRAus;
    }

    public void setSerieExtensionZEBRAus(String serieExtensionZEBRAus) {
        SerieExtensionZEBRAus = serieExtensionZEBRAus;
    }

    public String getSerieExtensionZEBRAit() {
        return SerieExtensionZEBRAit;
    }

    public void setSerieExtensionZEBRAit(String serieExtensionZEBRAit) {
        SerieExtensionZEBRAit = serieExtensionZEBRAit;
    }

    public String getSerieExtensionZEBRAde() {
        return SerieExtensionZEBRAde;
    }

    public void setSerieExtensionZEBRAde(String serieExtensionZEBRAde) {
        SerieExtensionZEBRAde = serieExtensionZEBRAde;
    }

    public String getSerieExtensionZEBRAes() {
        return SerieExtensionZEBRAes;
    }

    public void setSerieExtensionZEBRAes(String serieExtensionZEBRAes) {
        SerieExtensionZEBRAes = serieExtensionZEBRAes;
    }

    public String getSerieExtensionZEBRApt() {
        return SerieExtensionZEBRApt;
    }

    public void setSerieExtensionZEBRApt(String serieExtensionZEBRApt) {
        SerieExtensionZEBRApt = serieExtensionZEBRApt;
    }

    public String getNomDossier() {
        return nomDossier;
    }

    public void setNomDossier(String nomDossier) {
        this.nomDossier = nomDossier;
    }

    public String getCodePkmncards() {
        return codePkmncards;
    }

    public void setCodePkmncards(String codePkmncards) {
        this.codePkmncards = codePkmncards;
    }

    public String getCodePokemonTCG() {
        return codePokemonTCG;
    }

    public void setCodePokemonTCG(String codePokemonTCG) {
        this.codePokemonTCG = codePokemonTCG;
    }

    public String getNbCartes() {
        return nbCartes;
    }

    public void setNbCartes(String nbCartes) {
        this.nbCartes = nbCartes;
    }

    public void setSerieUS(SerieUS serie) {
        this.serie = serie;
    }

    public boolean isFr() {
        return fr;
    }

    public void setFr(boolean fr) {
        this.fr = fr;
    }

    public boolean isUs() {
        return us;
    }

    public void setUs(boolean us) {
        this.us = us;
    }

    public boolean isPromo() {
        return isPromo;
    }

    public void setPromo(boolean promo) {
        isPromo = promo;
    }

    public String getUrlUS() {
        return urlUS;
    }

    public void setUrlUS(String urlUS) {
        this.urlUS = urlUS;
    }

    public String getUrlIT() {
        return urlIT;
    }

    public void setUrlIT(String urlIT) {
        this.urlIT = urlIT;
    }

    public String getUrlDE() {
        return urlDE;
    }

    public void setUrlDE(String urlDE) {
        this.urlDE = urlDE;
    }

    public String getUrlES() {
        return urlES;
    }

    public void setUrlES(String urlES) {
        this.urlES = urlES;
    }

    public String getUrlPT() {
        return urlPT;
    }

    public void setUrlPT(String urlPT) {
        this.urlPT = urlPT;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    public static int getMaxIdPCA(){
        // à implémenter
        return 0;
    }
    @Override
    public String toString() {
        return "(" + id + ") " + nomUS ;
    }

    public SerieUS getSerie() {
        return serie;
    }

    public void setSerie(SerieUS serie) {
        this.serie = serie;
    }

    public List<Image_Carte> getImageCartes() {
        return imageCartes;
    }

    public void setImageCartes(List<Image_Carte> imageCartes) {
        this.imageCartes = imageCartes;
    }

    @Override
    public LocalDateTime getDateSortie() {
        return dateSortie;
    }

    @Override
    public void setDateSortie(LocalDateTime dateSortie) {
        this.dateSortie = dateSortie;
    }

    public List<ExpansionBulbapedia> getExpansionBulbapedias() {
        return expansionBulbapedias;
    }

    public void setExpansionBulbapedias(List<ExpansionBulbapedia> expansionBulbapedias) {
        this.expansionBulbapedias = expansionBulbapedias;
    }

    @Override
    public String getPremierNumero() {
        return premierNumero;
    }

    @Override
    public void setPremierNumero(String premierNumero) {
        this.premierNumero = premierNumero;
    }
}
