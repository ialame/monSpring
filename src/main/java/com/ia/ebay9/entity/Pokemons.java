package com.ia.ebay9.entity;

import javax.persistence.*;

@Entity
@Table( name="pokemon" )
public class Pokemons
{
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    private int id;
    private String NomFR;
    private String NomAN;

    public Pokemons()
    {
    }

    @Override
    public String toString() { return "(" + id + ") " + NomAN ; }

    public Integer getId() { return id;}
    public void setId(int id) {this.id = id; }

    public String getNomFR() {
        return NomFR;
    }

    public void setNomFR(String nomFR) {
        this.NomFR = nomFR;
    }

    public String getNomUS() {
        return NomAN;
    }

    public void setNomUS(String nomUS) {
        this.NomAN = nomUS;
    }
}
