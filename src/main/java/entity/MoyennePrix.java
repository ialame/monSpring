package com.pca.entity;

import javax.persistence.*;

@Entity
@Table( name = "moyennePrix" )
@Inheritance( strategy = InheritanceType.JOINED )
public class MoyennePrix {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    /*
    @OneToOne
    @JoinColumn(name="carte_id", nullable=true)
    private Carte carte;
*/
    /*
    @ManyToOne
    private Carte carte;

     */


    private float q81FR;
    private float q82FR;
    private float q71FR;
    private float q72FR;
    private float q61FR;
    private float q62FR;
    private float q51FR;
    private float q52FR;
    private float q41FR;
    private float q42FR;
    private float q31FR;
    private float q32FR;
    private float q21FR;
    private float q22FR;
    private float q11FR;
    private float q12FR;

    MoyennePrix(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public float getQ81FR() {
        return q81FR;
    }

    public void setQ81FR(float q81FR) {
        this.q81FR = q81FR;
    }

    public float getQ82FR() {
        return q82FR;
    }

    public void setQ82FR(float q82FR) {
        this.q82FR = q82FR;
    }

    public float getQ71FR() {
        return q71FR;
    }

    public void setQ71FR(float q71FR) {
        this.q71FR = q71FR;
    }

    public float getQ72FR() {
        return q72FR;
    }

    public void setQ72FR(float q72FR) {
        this.q72FR = q72FR;
    }

    public float getQ61FR() {
        return q61FR;
    }

    public void setQ61FR(float q61FR) {
        this.q61FR = q61FR;
    }

    public float getQ62FR() {
        return q62FR;
    }

    public void setQ62FR(float q62FR) {
        this.q62FR = q62FR;
    }

    public float getQ51FR() {
        return q51FR;
    }

    public void setQ51FR(float q51FR) {
        this.q51FR = q51FR;
    }

    public float getQ52FR() {
        return q52FR;
    }

    public void setQ52FR(float q52FR) {
        this.q52FR = q52FR;
    }

    public float getQ41FR() {
        return q41FR;
    }

    public void setQ41FR(float q41FR) {
        this.q41FR = q41FR;
    }

    public float getQ42FR() {
        return q42FR;
    }

    public void setQ42FR(float q42FR) {
        this.q42FR = q42FR;
    }

    public float getQ31FR() {
        return q31FR;
    }

    public void setQ31FR(float q31FR) {
        this.q31FR = q31FR;
    }

    public float getQ32FR() {
        return q32FR;
    }

    public void setQ32FR(float q32FR) {
        this.q32FR = q32FR;
    }

    public float getQ21FR() {
        return q21FR;
    }

    public void setQ21FR(float q21FR) {
        this.q21FR = q21FR;
    }

    public float getQ22FR() {
        return q22FR;
    }

    public void setQ22FR(float q22FR) {
        this.q22FR = q22FR;
    }

    public float getQ11FR() {
        return q11FR;
    }

    public void setQ11FR(float q11FR) {
        this.q11FR = q11FR;
    }

    public float getQ12FR() {
        return q12FR;
    }

    public void setQ12FR(float q12FR) {
        this.q12FR = q12FR;
    }
}
