package com.pca.model;

import com.pca.gui.ApplicationPM;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "particularite" )
@PrimaryKeyJoinColumn( name = "id" )
public class Particularite {

    @Id @GeneratedValue( strategy= GenerationType.IDENTITY )
    private Integer id;
    private String particularitePCA;
    private String name;
    private String pokemontcg;
    public String bulbapedia;
    private int ordre =1;
    private boolean avant =false;
    private boolean visible =false;
    private boolean isTitle =true;
    private String imgHrefBulbapedia;

/*
    @ManyToMany( cascade = CascadeType.ALL )
    @JoinTable( name = "cartepokemons_particularites",
            joinColumns = @JoinColumn( name = "particularite_id" ),
            inverseJoinColumns = @JoinColumn( name = "cartepokemon_id" ) )

 */
    @ManyToMany(cascade = CascadeType.ALL, mappedBy="particularites")
    private List<CartePokemon> cartePokemons = new ArrayList<>();


    public List<CartePokemon> getCartePokemons() {
        return cartePokemons;
    }

    public void setCartePokemons(List<CartePokemon> cartePokemons) {
        this.cartePokemons = cartePokemons;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParticularitePCA() {
        return particularitePCA;
    }

    public void setParticularitePCA(String particularitePCA) {
        this.particularitePCA = particularitePCA;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPokemontcg() {
        return pokemontcg;
    }

    public void setPokemontcg(String pokemontcg) {
        this.pokemontcg = pokemontcg;
    }

    public String getBulbapedia() {
        return bulbapedia;
    }

    public void setBulbapedia(String bulbapedia) {
        this.bulbapedia = bulbapedia;
    }

    public int getOrdre() {
        return ordre;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    public boolean isAvant() {
        return avant;
    }

    public void setAvant(boolean avant) {
        this.avant = avant;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isTitle() {
        return isTitle;
    }

    public void setTitle(boolean title) {
        isTitle = title;
    }

    public String getImgHrefBulbapedia() {
        return imgHrefBulbapedia;
    }

    public void setImgHrefBulbapedia(String imgHrefBulbapedia) {
        this.imgHrefBulbapedia = imgHrefBulbapedia;
    }

    @Override
    public String toString() {
        if(bulbapedia!=null)
            return  bulbapedia  ;
        else
            return "";
    }
    public static Particularite chercheParticularite(String text){
        for (Particularite p : ApplicationPM.particularites) {
            if (p.getBulbapedia() != null && p.getBulbapedia().equals(text))
                return p;

        }
        return null;
    }
}