package com.pca.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table( name="historique_ImageCarte" )
public class HistoriqueImageCarte
{
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private int statut;

    @ManyToOne
    @JoinColumn(name="imageCarte_id", nullable=false)
    private Image_Carte imageCarte;

    private LocalDateTime date = LocalDateTime.now();

    public HistoriqueImageCarte()
    {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public Image_Carte getImageCarte() {
        return imageCarte;
    }

    public void setImageCarte(Image_Carte imageCarte) {
        this.imageCarte = imageCarte;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
