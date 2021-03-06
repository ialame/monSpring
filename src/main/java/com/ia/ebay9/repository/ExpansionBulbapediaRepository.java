package com.ia.ebay9.repository;

import com.ia.ebay9.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ExpansionBulbapediaRepository {
    private EntityManager em;
    public ExpansionBulbapediaRepository(EntityManager em){
        this.em = em;
    }
    public ExpansionBulbapedia geExpansionBulbapediaById(Integer id) {
        return em.find(ExpansionBulbapedia.class,id);
    }

    public Extension getExtensionByTypeNom(ExpansionBulbapedia expansionBulbapediap) {
        switch (expansionBulbapediap.getCharset()){
            case "us":
                try {
                    TypedQuery<ExtensionUS> query = em.createQuery("select eb.extensionUS From ExpansionBulbapedia as eb  where eb.type = : type AND eb.nom = : nom", ExtensionUS.class);//imgHrefBulbapedia bulbapedia
                    query.setParameter("type", expansionBulbapediap.getType());
                    query.setParameter("nom", expansionBulbapediap.getNom());
                    List<ExtensionUS> extensions = (List<ExtensionUS>) query.getResultList();
                    if (extensions == null || extensions.isEmpty()) {
                        return null;
                    }
                    ExtensionUS cartePokemon = extensions.get(0);
                    return cartePokemon;
                }catch(Exception e){
                    System.out.println("Dans CartePokemonRepository 2 : "+ e);
                }
                break;
            case "jap":
                try {
                    TypedQuery<ExtensionJAP> query = em.createQuery("select eb.extensionJAP From ExpansionBulbapedia as eb  where eb.type = : type AND eb.nom = : nom", ExtensionJAP.class);
                    query.setParameter("type", expansionBulbapediap.getType());
                    query.setParameter("nom", expansionBulbapediap.getNom());
                    List<ExtensionJAP> extensions = (List<ExtensionJAP>) query.getResultList();
                    if (extensions == null || extensions.isEmpty()) {
                        return null;
                    }
                    ExtensionJAP cartePokemon = extensions.get(0);
                    return cartePokemon;
                }catch(Exception e){
                    System.out.println("Dans CartePokemonRepository 2 : "+ e);
                }
                break;

        }

        return null;
    }

}
