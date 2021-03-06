package com.pca.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table( name = "promoRaccourci" )
@PrimaryKeyJoinColumn( name = "id" )
public class PromoRaccourci {

    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    private int id;
    private String nom;
    private String charset;

    @OneToMany( cascade = CascadeType.ALL )
    @JoinTable( name = "promo",
            joinColumns = @JoinColumn( name = "promoRaccourci_id" ),
            inverseJoinColumns = @JoinColumn( name = "id" ) )
    private List<Promo> promos= new ArrayList<>();


    public int getId() {
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

    public List<Promo> getPromos() {
        return promos;
    }

    public void setPromos(List<Promo> promos) {
        this.promos = promos;
    }

}

