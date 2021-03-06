package com.ia.ebay9.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "caracteristique" )
@PrimaryKeyJoinColumn( name = "id" )
public class Caracteristique {
    @Id @GeneratedValue( strategy= GenerationType.IDENTITY )
        private Integer id;
        private String idPCA;
        private String nomParsable;
        private String nomPCA;

        @ManyToOne( cascade = CascadeType.ALL )
        @JoinColumn(name="id", nullable=false, insertable=false, updatable=false)
        private Caracteristique parent;

        private boolean isYellowAlternate = false;
        private boolean hasHolographisme = false;
        private boolean isReverse = false;
        private boolean isExclusive = false;
        private boolean isHoloOnly = false;
        private boolean isCategorieFront = false;
        private String description;

        @ManyToMany
        @JoinTable( name = "evenements_caracteristiques",
                joinColumns = @JoinColumn( name = "caracteristique_id" ),
                inverseJoinColumns = @JoinColumn( name = "evenement_id" ) )
        private List<Evenement> evenements = new ArrayList<>();

        private boolean categoriePCA = false;

        private boolean championship = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdPCA() {
        return idPCA;
    }

    public void setIdPCA(String idPCA) {
        this.idPCA = idPCA;
    }

    public String getNomParsable() {
        return nomParsable;
    }

    public void setNomParsable(String nomParsable) {
        this.nomParsable = nomParsable;
    }

    public String getNomPCA() {
        return nomPCA;
    }

    public void setNomPCA(String nomPCA) {
        this.nomPCA = nomPCA;
    }

    public Caracteristique getParent() {
        return parent;
    }

    public void setParent(Caracteristique parent) {
        this.parent = parent;
    }

    public boolean isYellowAlternate() {
        return isYellowAlternate;
    }

    public void setYellowAlternate(boolean yellowAlternate) {
        isYellowAlternate = yellowAlternate;
    }

    public boolean isHasHolographisme() {
        return hasHolographisme;
    }

    public void setHasHolographisme(boolean hasHolographisme) {
        this.hasHolographisme = hasHolographisme;
    }

    public boolean isReverse() {
        return isReverse;
    }

    public void setReverse(boolean reverse) {
        isReverse = reverse;
    }

    public boolean isExclusive() {
        return isExclusive;
    }

    public void setExclusive(boolean exclusive) {
        isExclusive = exclusive;
    }

    public boolean isHoloOnly() {
        return isHoloOnly;
    }

    public void setHoloOnly(boolean holoOnly) {
        isHoloOnly = holoOnly;
    }

    public boolean isCategorieFront() {
        return isCategorieFront;
    }

    public void setCategorieFront(boolean categorieFront) {
        isCategorieFront = categorieFront;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Evenement> getEvenements() {
        return evenements;
    }

    public void setEvenements(List<Evenement> evenements) {
        this.evenements = evenements;
    }

    public boolean isCategoriePCA() {
        return categoriePCA;
    }

    public void setCategoriePCA(boolean categoriePCA) {
        this.categoriePCA = categoriePCA;
    }

    public boolean isChampionship() {
        return championship;
    }

    public void setChampionship(boolean championship) {
        this.championship = championship;
    }
}
