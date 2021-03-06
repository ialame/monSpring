package com.ia.ebay9.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.ia.ebay9.entity.Version;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
//import java.sql.Date;

@Entity
@Table(name = "promo" )
@PrimaryKeyJoinColumn( name = "id" )
public class Promo {

@Id
@GeneratedValue( strategy= GenerationType.IDENTITY )
    private Integer id = null;
    private String nomBulbapedia;
    private String nomPCA;
    private LocalDateTime dateSortie;
    private String charset ="us";// par défaux

    @ManyToOne
    @JoinColumn(name="version_id", nullable=true)
    private Version version;

    @ManyToOne
    @JoinColumn(name="cartePokemon_id", nullable=true)
    private CartePokemon cartePokemon;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy="promosused")
    private List<CartePokemon> cartePokemons = new ArrayList<>();

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="evenement_id", nullable=true)
    private Evenement evenement;

    @ManyToOne
    @JoinColumn(name="promoRaccourci_id", nullable=true)
    private PromoRaccourci promoRaccourci;

    public Promo(){}
    public Promo(String nomBulbapedia,String charset){
        this.nomBulbapedia=nomBulbapedia;
        this.charset = charset;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomBulbapedia() {
        return nomBulbapedia;
    }

    public void setNomBulbapedia(String nomBulbapedia) {
        this.nomBulbapedia = nomBulbapedia;
    }

    public String getNomPCA() {
        return nomPCA;
    }

    public void setNomPCA(String nomPCA) {
        this.nomPCA = nomPCA;
    }

    public LocalDateTime getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(LocalDateTime dateSortie) {
        this.dateSortie = dateSortie;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public CartePokemon getCartePokemon() {
        return cartePokemon;
    }

    public void setCartePokemon(CartePokemon cartePokemon) {
        this.cartePokemon = cartePokemon;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public PromoRaccourci getPromoRaccourci() {
        return promoRaccourci;
    }

    public void setPromoRaccourci(PromoRaccourci promoRaccourci) {
        this.promoRaccourci = promoRaccourci;
    }

    @Override
    public String toString() {
        return nomBulbapedia ;
    }

    public List<CartePokemon> getCartePokemons() {
        return cartePokemons;
    }

    public void setCartePokemons(List<CartePokemon> cartePokemons) {
        this.cartePokemons = cartePokemons;
    }
}
