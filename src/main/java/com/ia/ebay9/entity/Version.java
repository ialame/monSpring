package com.ia.ebay9.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "version" )
@PrimaryKeyJoinColumn( name = "id" )
public class Version {
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    private int id;

    private String nom;

    @OneToMany( cascade = CascadeType.ALL )
    @JoinTable( name = "promo",
            joinColumns = @JoinColumn( name = "version_id" ),
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

    public List<Promo> getPromos() {
        return promos;
    }

    public void setPromos(List<Promo> promos) {
        this.promos = promos;
    }
}
