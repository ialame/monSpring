package com.pca.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//import java.sql.Date;
@Data
@Entity
@Table( name = "cartePokemon" )
@PrimaryKeyJoinColumn( name = "id" )
public class CartePokemon extends Carte
{
    public String Card;
    private String japCardGras;
    private String cardGras;
    private String japCrochet;
    //private Crochet crochet;
    private String enExpansion;
    private String enNum;
    private String japExpansion;
    private String japType;
    private String japTypePCA;
    private String krTypePCA;
    private String cnTypePCA;
    private String japConflit;
    private String japRarity;
    private String krRarity;
    private String cnRarity;
    private String japRarityPCA;
    private String krRarityPCA;
    private String cnRarityPCA;
    private String japNum;
    private String krNum;
    private String cnNum;
    private String japNumBis;
    private String krNumBis;
    private String cnNumBis;
    private String japSurBis;
    private String krSurBis;
    private String cnSurBis;
    private String japName;
    private String krName;
    private String cnName;
    private String japTraductionName;
    private String japPromoBulbapedia;
    private String japPromoPCA;
    private String krPromoPCA;
    private String cnPromoPCA;
    private String cnPromoBulpapedia;
    private String evolutionStage;
    private String promoBulbapedia;
    private String PromoFrPca;
    private String PromoUsPca;
    private boolean japProbleme =false;
    private String idPrim;
    //private int japIndice;
    //private int stock;
    private String idPrimJap;
    private String idPrimKr;
    private String idPrimCn;

    //////////////////////   Relations  CartePokemon  <=====>  CartePokemon

    @ManyToOne
    @JoinColumn(name="carteMere_id", nullable=true)
    public CartePokemon carteMere;

    @OneToMany( mappedBy = "carteMere",orphanRemoval = true)
    private List<CartePokemon> carteFilles = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="japCarteMere_id", nullable=true)
    public CartePokemon japCarteMere;

    @OneToMany( mappedBy = "japCarteMere",orphanRemoval = true)
    private List<CartePokemon> japCarteFilles = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="krCarteMere_id", nullable=true)
    public CartePokemon krCarteMere;

    @OneToMany( mappedBy = "krCarteMere",orphanRemoval = true)
    private List<CartePokemon> krCarteFilles = new ArrayList<>();




    @ManyToOne
    @JoinColumn(name="distributionMere_id", nullable=true)
    public CartePokemon distributionMere;

    @OneToMany( mappedBy = "distributionMere",orphanRemoval = true)
    private List<CartePokemon> distributionFilles = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="japDistributionMere_id", nullable=true)
    public CartePokemon japDistributionMere;

    @OneToMany( mappedBy = "japDistributionMere",orphanRemoval = true)
    private List<CartePokemon> japDistributionFilles = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="krDistributionMere_id", nullable=true)
    public CartePokemon krDistributionMere;

    @OneToMany( mappedBy = "krDistributionMere",orphanRemoval = true)
    private List<CartePokemon> krDistributionFilles = new ArrayList<>();




    @ManyToOne
    @JoinColumn(name="extension_id", nullable=true)
    public ExtensionUS extensionus;

    @ManyToOne
    @JoinColumn(name="extensionjap_id", nullable=true)
    private ExtensionJAP extensionjap;

/*
    @ManyToOne
    @JoinColumn(name="extensionkr_id", nullable=true)
    private ExtensionKR extensionkr;


    @ManyToOne
    @JoinColumn(name="extensioncn_id", nullable=true)
    private ExtensionCN extensioncn;
*/

    //private MoyennePrix moyennePrix;
    @ManyToMany( cascade = CascadeType.MERGE )
    @JoinTable( name = "cartepokemons_promosused",
            joinColumns = @JoinColumn( name = "cartepokemon_id" ),
            inverseJoinColumns = @JoinColumn( name = "promo_id" ) )
    private List<Promo> promosused = new ArrayList<>();


    @ManyToMany( cascade = CascadeType.MERGE )
    @JoinTable( name = "cartepokemons_particularites",
            joinColumns = @JoinColumn( name = "cartepokemon_id" ),
            inverseJoinColumns = @JoinColumn( name = "particularite_id" ) )
    private List<Particularite> particularites = new ArrayList<>();



    @ManyToMany( cascade = CascadeType.MERGE )
    @JoinTable( name = "cartepokemons_crochets",
            joinColumns = @JoinColumn( name = "cartepokemon_id" ),
            inverseJoinColumns = @JoinColumn( name = "crochet_id" ) )
    private List<Crochet> crochets = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cartePokemon",orphanRemoval = true)
    private List<Promo> promos = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cartePokemon",orphanRemoval = true)
    private List<CartePrixClient> cartesPrixClient = new ArrayList<>();


    public List<Promo> getPromosused() {
        return promosused;
    }

    public void setPromosused(List<Promo> promosused) {
        this.promosused = promosused;
    }

    public void setExtensionus(ExtensionUS extensionus) {
        this.extensionus = extensionus;
    }

    public void setExtensionjap(ExtensionJAP extensionjap) {
        this.extensionjap = extensionjap;
    }

    public List<Particularite> getParticularites() {
        return particularites;
    }

    public void setParticularites(List<Particularite> particularites) {
        this.particularites = particularites;
    }
    public void addParticularites(Particularite particularite) {
        this.particularites.add(particularite);
    }

    public List<Promo> getPromos() {
        return promos;
    }

    public void setPromos(List<Promo> promos) {
        this.promos = promos;
    }

    public List<Crochet> getCrochets() {
        return crochets;
    }

    public void setCrochets(List<Crochet> crochets) {
        this.crochets = crochets;
    }

    @Column(columnDefinition="TEXT")
    private String numSerie;
    @Column(columnDefinition="TEXT")
    private String num;
    @Column(columnDefinition="TEXT")
    private String sur;
    @Column(columnDefinition="TEXT")
    private String type1;
    @Column(columnDefinition="TEXT")
    private String type2;
    @Column(columnDefinition="TEXT")
    private String nomFR;
    @Column(columnDefinition="TEXT")
    private String nomUS;
    @Column(columnDefinition="TEXT")
    private String nomIT;
    @Column(columnDefinition="TEXT")
    private String nomDE;
    @Column(columnDefinition="TEXT")
    private String nomES;
    @Column(columnDefinition="TEXT")
    private String nomPT;
    @Column(columnDefinition="TEXT")
    private String nomJAP;
    @Column(columnDefinition="TEXT")
    private String nomKR;
    @Column(columnDefinition="TEXT")
    private String nomCN;

    @Column(columnDefinition="TEXT")
    private String nomFrancaisEBAY;
    @Column(columnDefinition="TEXT")
    private String nomAnglaisEBAY;
    @Column(columnDefinition="TEXT")
    private String nomItalienEBAY;
    @Column(columnDefinition="TEXT")
    private String nomAllemandEBAY;
    @Column(columnDefinition="TEXT")
    private String nomEspagnolEBAY;
    @Column(columnDefinition="TEXT")
    private String nomPortugaisEBAY;
    @Column(columnDefinition="TEXT")
    private String nomJapEBAY;
    @Column(columnDefinition="TEXT")
    private String nomKrEBAY;
    @Column(columnDefinition="TEXT")
    private String nomCnEBAY;
    @Column(columnDefinition="TEXT")
    private String nomFrancaisZEBRA;
    @Column(columnDefinition="TEXT")
    private String nomAnglaisZEBRA;
    @Column(columnDefinition="TEXT")
    private String nomItalienZEBRA;
    @Column(columnDefinition="TEXT")
    private String nomEspagnolZEBRA;
    @Column(columnDefinition="TEXT")
    private String nomAllemandZEBRA;
    @Column(columnDefinition="TEXT")
    private String nomPortugaisZEBRA;
    @Column(columnDefinition="TEXT")
    private String nomJapZEBRA;
    @Column(columnDefinition="TEXT")
    private String nomKrZEBRA;
    @Column(columnDefinition="TEXT")
    private String nomCnZEBRA;
    @Column(columnDefinition="TEXT")
    private String nomCompletFR;
    @Column(columnDefinition="TEXT")
    private String nomCompletUS;
    @Column(columnDefinition="TEXT")
    private String nomCompletJap;
    @Column(columnDefinition="TEXT")
    private String nomCompletPT;
    @Column(columnDefinition="TEXT")
    private String nomCompletKr;
    @Column(columnDefinition="TEXT")
    private String nomCompletCn;
    @Column(columnDefinition="TEXT")
    private String nomCompletIT;
    @Column(columnDefinition="TEXT")
    private String nomCompletDE;
    @Column(columnDefinition="TEXT")
    private String nomCompletES;
    @Column(columnDefinition="TEXT")
    private String postIndicationSpecialeFR;
    @Column(columnDefinition="TEXT")
    private String postIndicationSpecialeUS;
    @Column(columnDefinition="TEXT")
    private String postIndicationSpecialeJap;
    @Column(columnDefinition="TEXT")
    private String postIndicationSpecialeIT;
    @Column(columnDefinition="TEXT")
    private String postIndicationSpecialeDE;
    @Column(columnDefinition="TEXT")
    private String postIndicationSpecialeES;
    @Column(columnDefinition="TEXT")
    private String postIndicationSpecialePT;
    @Column(columnDefinition="TEXT")
    private String rarete;
    //private boolean hasImg;
    private boolean isActive = true;
    private boolean isAffichable = true;
    @Column(columnDefinition="TEXT")
    public String Recherche;
    @Column(columnDefinition="TEXT")
    private String HP;
    @Column(columnDefinition="TEXT")
    private String zPostExtensionFR;
    @Column(columnDefinition="TEXT")
    private String zPostExtensionUS;
    @Column(columnDefinition="TEXT")
    private String zPostExtensionIT;
    @Column(columnDefinition="TEXT")
    private String zPostExtensionDE;
    @Column(columnDefinition="TEXT")
    private String zPostExtensionES;
    @Column(columnDefinition="TEXT")
    private String zPostExtensionPT;
    private boolean distribution =false;
    private boolean isDistributionFille = false;
    private boolean isJapDistributionFille=false;
    private boolean isKrDistributionFille=false;
    private boolean isCrochetFille=false;
    private boolean isJapCrochetFille=false;
    private boolean isKrCrochetFille=false;


    private boolean hasDistribution = false;
    private boolean hasJapDistribution = false;
    private boolean hasKrDistribution = false;
    private boolean FR = false;
    private boolean US = false;
    private boolean JAP = false;
    private boolean KR = false;
    private boolean CN = false;
    private boolean IT = false;
    private boolean DE = false;
    private boolean ES = false;
    private boolean PT = false;
    private boolean NL = false;
    private int levelDE;
    private boolean hasReverse = false;
    private boolean hasNonReverse = true;
    private Boolean reverse = false;
    private String ajoutManuelJap;
    private boolean FA = false;
    private int edition = 2;
    private String nbCartes;
    private LocalDateTime dateSortieUs;
    private LocalDateTime dateSortieFr;
    private LocalDateTime dateSortieJap;
    private LocalDateTime dateSortieKr;
    private LocalDateTime dateSortieCn;
    private LocalDateTime dateSortieIT;
    private LocalDateTime dateSortieES;
    private LocalDateTime dateSortieDE;
    private LocalDateTime dateSortiePT;
    @Column(columnDefinition="TEXT")
    private String crochetFR;
    @Column(columnDefinition="TEXT")
    private String crochetUS;
    @Column(columnDefinition="TEXT")
    private String crochetIT;
    @Column(columnDefinition="TEXT")
    private String crochetDE;
    @Column(columnDefinition="TEXT")
    private String crochetES;
    @Column(columnDefinition="TEXT")
    private String crochetPT;
    private Boolean hasDate = false;
    private Boolean cacherSerieExtension=false;

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn(name="source_id", nullable=true)
    private CartePokemonSource source;


    public CartePokemonSource getSource() {
        return source;
    }

    public void setSource(CartePokemonSource source) {
        this.source = source;
    }

    //private boolean hasHomonymeJap = false;
    //private int LV = 0;

    /*
    private NomMultiple nomMultiple;
    private String detail;
    private String japDetail;
    private List<Promo> promoUsed;
    */

    public CartePokemon()
    {

    }

    public CartePokemon(String card, ExtensionUS extensionus, ExtensionJAP extensionjap, String nomFR, String Recherche) {
        Card = card;
        this.extensionus = extensionus;
        this.extensionjap = extensionjap;
        this.nomFR = nomFR;
        this.Recherche = Recherche;
    }

    public ExtensionUS getExtensionUS() {
        return extensionus;
    }
    public ExtensionJAP getExtensionJAP() {
        return extensionjap;
    }

    @Override
    public String toString() {
        return "(" + "id" +"), " + nomUS + " " + Recherche;
    }

    


    
   
    public void copy(CartePokemon cp) {
        //super.copy(cp);
        //cp.isPokemon = this.isPokemon;
        //cp.rarity = this.rarity;
        cp.Card = this.Card;
        cp.extensionus = this.extensionus;
        //if (cp.extensionUS != null)
        //    cp.extensionUS.setId(this.extension_id);
        cp.idPrimJap = this.idPrimJap;//
        cp.JAP = this.JAP;
        cp.enExpansion = this.enExpansion;
        cp.enNum = this.enNum;
        cp.japRarity = this.japRarity;
        cp.Card = this.Card;
        cp.japNum = this.japNum;
        cp.japNumBis = this.japNumBis;
        cp.japSurBis = this.japSurBis;
        cp.japName = this.japName;

        //cp.extensionjap_id = this.extensionjap_id;
        cp.extensionjap = this.extensionjap;
        cp.japTraductionName = this.japTraductionName;
        cp.japProbleme = this.japProbleme;
        //cp.japIndice = this.japIndice;
        cp.japConflit = this.japConflit;
        cp.FA = this.FA;
        cp.ajoutManuelJap = this.ajoutManuelJap;
        cp.evolutionStage = this.evolutionStage;
        //cp.href = this.href;
        cp.promos =promos;
    }

    public boolean isNL() {
        return NL;
    }

    public void setNL(boolean NL) {
        this.NL = NL;
    }

    public void ajoutePartieJapDe(CartePokemon cj){
        this.setExtensionJAP(cj.getExtensionJAP());
        this.setCard(cj.getCard());
        this.setNomCompletJap(cj.getNomCompletJap());
        this.JAP=true;
        this.setJapCrochet(cj.getJapCrochet());
        this.setEnExpansion(cj.getEnExpansion());
        this.setEnNum(cj.getEnNum());
        this.setJapExpansion(cj.getJapExpansion());
        this.setJapRarity(cj.getJapRarity());
        this.setJapRarityPCA(cj.getJapRarityPCA());
        this.setJapNum(cj.getJapNum());
        this.setJapNumBis(cj.getJapNumBis());
        this.setJapSurBis(cj.getJapSurBis());
        this.setJapName(cj.getJapName());
        this.setJapTraductionName(cj.getJapTraductionName());
        this.setJapType(cj.getJapType());
        this.setJapTypePCA(cj.getJapTypePCA());
        this.setJapConflit(cj.getJapConflit());
        this.setEvolutionStage(cj.getEvolutionStage());
        this.setJapPromoPCA(cj.getJapPromoPCA());
        this.setDateSortieJap(cj.getDateSortieJap());
        /*
        for(Particularite p : cj.getParticularites()) {
           if(!getParticularites().contains(p))
                this.getParticularites().add(p);
        }

         */
        for(Promo p : cj.getPromos()) {
            p.setCartePokemon(this);
            this.getPromos().add(p);
        }
    }

    public void setExtensionJAP(ExtensionJAP extensionJAP) {
    }

    public void ajoutePartieUsDe(CartePokemon cj){
        this.US=true;
        this.setIdPrim(cj.getIdPrim());
        this.setExtensionUS(cj.getExtensionUS());
        this.setNum(cj.getNum());
        this.setSur(cj.getSur());
        this.setRecherche(cj.getRecherche());
        this.setRarete(cj.getRarete());
        this.setType1(cj.getType1());
        this.setNomUS(cj.getNomUS());
    }

    public void setExtensionUS(ExtensionUS extensionUS) {
    }
}
