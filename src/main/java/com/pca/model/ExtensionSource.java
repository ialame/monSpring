package com.pca.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "extensionSource" )
@PrimaryKeyJoinColumn( name = "id" )
public class ExtensionSource {

@Id @GeneratedValue( strategy= GenerationType.IDENTITY )
    private Integer id;
    public String url;
    private String locale;
    private String discriminator;
    public String nomExtensionSource;
    private String nomExtensionTraduit;
    private String source;
    private boolean isSansEquivalentChezPCA;
    public ExtensionSource(){

    }
    public ExtensionSource(String nomExtensionSource, String url){
        this.nomExtensionSource=nomExtensionSource;
        this.url=url;
    }

    @ManyToMany( mappedBy="extensionSources")
    private List<ExtensionJAP> extensionJAPs = new ArrayList<>();

    @OneToMany( cascade=CascadeType.ALL)
    @JoinTable( name = "image_carte", joinColumns = @JoinColumn(name = "extensionSource_id"),inverseJoinColumns = @JoinColumn( name = "id" ) )
    private List<Image_Carte> imageCartes= new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLocal() {
        return locale;
    }

    public void setLocal(String local) {
        this.locale = local;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    public String getNomExtensionSource() {
        return nomExtensionSource;
    }

    public void setNomExtensionSource(String nomExtensionSource) {
        this.nomExtensionSource = nomExtensionSource;
    }

    public String getNomExtensionTraduit() {
        return nomExtensionTraduit;
    }

    public void setNomExtensionTraduit(String nomExtensionTraduit) {
        this.nomExtensionTraduit = nomExtensionTraduit;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isSansEquivalentChezPCA() {
        return isSansEquivalentChezPCA;
    }

    public void setSansEquivalentChezPCA(boolean sansEquivalentChezPCA) {
        isSansEquivalentChezPCA = sansEquivalentChezPCA;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public List<ExtensionJAP> getExtensionJAPs() {
        return extensionJAPs;
    }

    public void setExtensionJAPs(List<ExtensionJAP> extensionJAPs) {
        this.extensionJAPs = extensionJAPs;
    }

    public List<Image_Carte> getImageCartes() {
        return imageCartes;
    }

    public void setImageCartes(List<Image_Carte> imageCartes) {
        this.imageCartes = imageCartes;
    }
}
