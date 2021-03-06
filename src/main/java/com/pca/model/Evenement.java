package com.pca.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "evenement" )
@PrimaryKeyJoinColumn( name = "id" )
public class Evenement
{
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    private int id;
    private String nom;

    @OneToMany( cascade = CascadeType.ALL )
    @JoinTable( name = "promo",
            joinColumns = @JoinColumn( name = "evenement_id" ),
            inverseJoinColumns = @JoinColumn( name = "id" ) )
    private List<Promo> promos= new ArrayList<>();

    private boolean hidden = false;
    private boolean championship = false;
    private LocalDateTime dateSortie;
    private boolean withoutDate = false;

    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn(name="promoRaccourci_id", nullable=false)
    private PromoRaccourci promoRaccourci;

    @ManyToMany
    @JoinTable( name = "evenements_caracteristiques",
            joinColumns = @JoinColumn( name = "evenement_id" ),
            inverseJoinColumns = @JoinColumn( name = "caracteristique_id" ) )
    private List<Caracteristique> caracteristiques = new ArrayList<>();

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

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isChampionship() {
        return championship;
    }

    public void setChampionship(boolean championship) {
        this.championship = championship;
    }

    public LocalDateTime getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(LocalDateTime dateSortie) {
        this.dateSortie = dateSortie;
    }

    public boolean isWithoutDate() {
        return withoutDate;
    }

    public void setWithoutDate(boolean withoutDate) {
        this.withoutDate = withoutDate;
    }

    public PromoRaccourci getPromoRaccourci() {
        return promoRaccourci;
    }

    public void setPromoRaccourci(PromoRaccourci promoRaccourci) {
        this.promoRaccourci = promoRaccourci;
    }

    public List<Caracteristique> getCaracteristiques() {
        return caracteristiques;
    }

    public void setCaracteristiques(List<Caracteristique> caracteristiques) {
        this.caracteristiques = caracteristiques;
    }
}
