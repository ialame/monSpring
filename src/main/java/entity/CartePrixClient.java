package com.pca.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

//import java.sql.Date;
//import java.time.LocalDate;

@Entity
@Table( name="carte_prix_client" )
public class CartePrixClient {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private int client_id;

    @ManyToOne
    @JoinColumn(name="carte_id", nullable=true)
    private CartePokemon cartePokemon;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="datetime")
     */
    private LocalDateTime date;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public CartePokemon getCartePokemon() {
        return cartePokemon;
    }

    public void setCartePokemon(CartePokemon cartePokemon) {
        this.cartePokemon = cartePokemon;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

