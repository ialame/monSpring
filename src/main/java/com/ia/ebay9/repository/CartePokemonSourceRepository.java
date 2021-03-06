package com.ia.ebay9.repository;

import com.ia.ebay9.entity.CartePokemon;
import com.ia.ebay9.entity.CartePokemonSource;
import com.ia.ebay9.entity.ExtensionJAP;
import com.ia.ebay9.entity.ExtensionUS;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CartePokemonSourceRepository {

        private EntityManager em;
        public CartePokemonSourceRepository(EntityManager em){
            this.em = em;
        }
        public CartePokemonSource getCartePokemonSourceById(Integer id) {
            return em.find(CartePokemonSource.class,id);
        }
        public CartePokemonSource  getCartePokemonSourceByURL(String url) {
            try {
                TypedQuery<CartePokemonSource> query = em.createQuery("select cps From CartePokemonSource as cps  where cps.url = : url", CartePokemonSource.class);

                query.setParameter("url", url);

                List<CartePokemonSource> cpss = (List<CartePokemonSource>) query.getResultList();
                if (cpss == null || cpss.isEmpty()) {
                    return null;
                }
                CartePokemonSource cps = cpss.get(0);
                return cps;
            }catch(Exception e){
                System.out.println("Dans CartePokemonSourceRepository : "+ e);
            }
            return null;
        }
    }