package com.pca.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "crochet" )
@PrimaryKeyJoinColumn( name = "id" )
public class Crochet implements Comparable<Crochet>{

@Id @GeneratedValue( strategy= GenerationType.IDENTITY )
    private Integer id;
    public String nom;
    private String charset;
    //private String hidden;
    //private boolean isCrochetPCA;
    private boolean isCardGras;
    public Crochet(){}
    public Crochet(String nom,String charset){
        this.nom=nom;
        this.charset=charset;
    }
/*
    @ManyToMany
    @JoinTable(name = "cartepokemons_crochets",
            joinColumns = @JoinColumn( name = "crochet_id" ),
            inverseJoinColumns = @JoinColumn( name = "cartepokemon_id" ) )
    private List<CartePokemon> cartePokemons = new ArrayList<>();
*/


    @ManyToMany(cascade = CascadeType.ALL, mappedBy="crochets")
    private List<CartePokemon> cartePokemons = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public boolean isCardGras() {
        return isCardGras;
    }

    public void setCardGras(boolean cardGras) {
        isCardGras = cardGras;
    }

    public List<CartePokemon> getCartePokemons() {
        return cartePokemons;
    }

    public void setCartePokemons(List<CartePokemon> cartePokemons) {
        this.cartePokemons = cartePokemons;
    }

    @Override
    public int compareTo(Crochet o) {
        if(nom.equals(o.getNom()))
            return charset.compareTo(o.getCharset());
        else
            return nom.compareTo(o.getNom());
    }

    @Override
    public String toString() {
        if(nom!=null)
            return "nom='" + nom + '\'';
        else
            return "";
    }
}
