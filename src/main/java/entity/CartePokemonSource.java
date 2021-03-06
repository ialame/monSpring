package com.pca.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "cartePokemonSource" )
@PrimaryKeyJoinColumn( name = "id" )
public class CartePokemonSource {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected int id ;
    private String url;
    private String card1;
    private String card2;
    private String card3;

    @OneToMany( cascade = CascadeType.ALL )
    @JoinTable( name = "cartePokemon", joinColumns = @JoinColumn( name = "source_id" ), inverseJoinColumns = @JoinColumn( name = "id" ) )
    private List<CartePokemon> cartePokemons = new ArrayList<>();
    public CartePokemonSource(){}
    public CartePokemonSource(String url){
        this.url=url;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCard1() {
        return card1;
    }

    public void setCard1(String card1) {
        this.card1 = card1;
    }

    public String getCard2() {
        return card2;
    }

    public void setCard2(String card2) {
        this.card2 = card2;
    }

    public String getCard3() {
        return card3;
    }

    public void setCard3(String card3) {
        this.card3 = card3;
    }

    public List<CartePokemon> getCartePokemons() {
        return cartePokemons;
    }

    public void setCartePokemons(List<CartePokemon> cartePokemons) {
        this.cartePokemons = cartePokemons;
    }
}
