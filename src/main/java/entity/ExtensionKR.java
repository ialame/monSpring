package com.pca.entity;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name="extensionkr" )
public class ExtensionKR extends Extension
{
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String nom;
    private String sousExtension;
    private String krName;
    private String URL;
    public String nomTableauBulbapedia;
    //private String extensionSources;
    private String nomDossier;

    @ManyToOne
    @JoinColumn(name="seriekr_id", nullable=false)
    private SerieKR seriekr;



    private String SerieExtensionZEBRA = "A renseigner ...";
/*
    @OneToMany( mappedBy = "extensionkr",orphanRemoval = true)
    private List<CartePokemon> cartes = new ArrayList<>();
*/
    @OneToMany( cascade=CascadeType.ALL)
    @JoinTable( name = "image_carte", joinColumns = @JoinColumn(name = "extensionkr_id"),inverseJoinColumns = @JoinColumn( name = "id" ) )
    private List<Image_Carte> imageCartes= new ArrayList<>();

    private LocalDateTime dateSortie = LocalDateTime.now();
    private String nomRaccourci;
    private Boolean cacherSerieExtension = false;
    private String numSur;
    private String premierNumero;
    private boolean isPromo = false;
    //private String extensionConcatenees;
    public ExtensionKR()
    {

    }

    public ExtensionKR(String nom, SerieKR seriekr) {
        this.nom = nom;
        this.seriekr = seriekr;
        this.setSerieExtensionZEBRA(nom+seriekr.getNom());
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



    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getNomTableauBulbapedia() {
        return nomTableauBulbapedia;
    }

    public void setNomTableauBulbapedia(String nomTableauBulbapedia) {
        this.nomTableauBulbapedia = nomTableauBulbapedia;
    }

    public String getNomDossier() {
        return nomDossier;
    }

    public void setNomDossier(String nomDossier) {
        this.nomDossier = nomDossier;
    }



    public String getSerieExtensionZEBRA() {
        return SerieExtensionZEBRA;
    }

    public void setSerieExtensionZEBRA(String serieExtensionZEBRA) {
        SerieExtensionZEBRA = serieExtensionZEBRA;
    }
/*
    public List<CartePokemon> getCartes() {
        return cartes;
    }

    public void setCartes(List<CartePokemon> cartes) {
        this.cartes = cartes;
    }

*/


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

    public String getPremierNumero() {
        return premierNumero;
    }

    public void setPremierNumero(String premierNumero) {
        this.premierNumero = premierNumero;
    }

    public boolean isPromo() {
        return isPromo;
    }

    public void setPromo(boolean promo) {
        isPromo = promo;
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

    public String getKrName() {
        return krName;
    }

    public void setKrName(String krName) {
        this.krName = krName;
    }


    public SerieKR getSeriekr() {
        return seriekr;
    }

    public void setSeriekr(SerieKR seriekr) {
        this.seriekr = seriekr;
    }

    public Boolean getCacherSerieExtension() {
        return cacherSerieExtension;
    }

    public void setCacherSerieExtension(Boolean cacherSerieExtension) {
        this.cacherSerieExtension = cacherSerieExtension;
    }
}
