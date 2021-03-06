package com.pca.repository;

import com.pca.model.CartePokemon;
import com.pca.model.ExtensionJAP;
import com.pca.model.ExtensionUS;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CartePokemonRepository {

        private EntityManager em;
        public CartePokemonRepository(EntityManager em){
            this.em = em;
        }
        public CartePokemon getCartePokemonById(Integer id) {
            return em.find(CartePokemon.class,id);
        }
        public CartePokemon  getCartePokemonUSByCardRechercheExtension(String nomUS, String Recherche, ExtensionUS extensionUS) {
            try {
                //TypedQuery<CartePokemon> query = em.createQuery("select carte From CartePokemon as carte INNER JOIN carte.extensionus as extension where carte.Card = : Card AND carte.Recherche = : Recherche AND extension.enExpansionBulbapedia = : enExpansionBulbapedia", CartePokemon.class);//imgHrefBulbapedia bulbapedia
                TypedQuery<CartePokemon> query = em.createQuery("select carte From CartePokemon as carte  where carte.nomUS = : nomUS AND carte.Recherche = : Recherche AND carte.extensionus=:ext", CartePokemon.class);//imgHrefBulbapedia bulbapedia

                query.setParameter("nomUS", nomUS);
                query.setParameter("Recherche", Recherche);
                query.setParameter("ext", extensionUS);
                List<CartePokemon> cartePokemons = (List<CartePokemon>) query.getResultList();
                if (cartePokemons == null || cartePokemons.isEmpty()) {
                    return null;
                }
                CartePokemon cartePokemon = cartePokemons.get(0);
                return cartePokemon;
            }catch(Exception e){
                System.out.println("Dans CartePokemonRepository : "+ e);
            }
            return null;
        }
    public CartePokemon  getCartePokemonJAPByCardRechercheExtension(String Card, String japNum, ExtensionJAP extensionJAP) {
        try {
            TypedQuery<CartePokemon> query = em.createQuery("select carte From CartePokemon as carte  where carte.Card = : Card AND carte.japNum = : japNum AND carte.extensionjap = : extensionJAP", CartePokemon.class);//imgHrefBulbapedia bulbapedia

            query.setParameter("Card", Card);
            query.setParameter("japNum", japNum);
            query.setParameter("extensionJAP", extensionJAP);
            List<CartePokemon> cartePokemons = (List<CartePokemon>) query.getResultList();
            if (cartePokemons == null || cartePokemons.isEmpty()) {
                return null;
            }
            CartePokemon cartePokemon = cartePokemons.get(0);
            return cartePokemon;
        }catch(Exception e){
            System.out.println("Dans CartePokemonRepository 2 : "+ e);
        }
        return null;
    }

        public CartePokemon saveParticularite(CartePokemon particularite) {
            if(particularite.getId()==null){
                em.persist(particularite);
            }else{
                particularite = em.merge(particularite);
            }
            return particularite;
        }

        public void deleteParticularite(CartePokemon particularite) {
            if(em.contains(particularite))
                em.remove(particularite);
            else
                em.merge(particularite);

        }
    }