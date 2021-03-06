package com.pca.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

//import com.is.webstore.business.Article;

public class Main {

    public static void main(String[] args) throws Exception {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("alame");
            entityManager = entityManagerFactory.createEntityManager();
/*
            System.out.println( "- Lecture de tous les articles -----------" );

            List<Serie> series = entityManager.createQuery( "from Serie", Serie.class ).getResultList();
            for (Serie serie : series) {
                System.out.println( serie );
            }*/

            /*Extension extension = entityManager.find( Extension.class, 1 );
            System.out.println( extension );

            Serie serie = extension.getSerie();
            System.out.println( serie );
            List<Extension> extensions = serie.getExtensions();
            for (Extension e: extensions) {
                System.out.println( e );
            }*/
            CartePokemonSource source = entityManager.find( CartePokemonSource.class, 3567 );
            List<CartePokemon> cartePokemons = source.getCartePokemons();
            for (CartePokemon c: cartePokemons) {
                System.out.println(c);
            }

            CartePokemon cartePokemon = entityManager.find( CartePokemon.class, 1 );
            System.out.println( cartePokemon );

            ExtensionUS extension = cartePokemon.getExtensionUS();
            System.out.println( extension );
            List<CartePokemon> cartesp = extension.getCartes();
            for (CartePokemon c: cartesp) {
                System.out.println(c);
            }
            System.out.println( "- Insertion d'un nouvel article ----------" );


        } finally {
            if ( entityManager != null ) entityManager.close();
            if ( entityManagerFactory != null ) entityManagerFactory.close();
        }
    }
}