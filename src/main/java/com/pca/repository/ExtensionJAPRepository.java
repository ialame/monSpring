package com.pca.repository;

import com.pca.model.ExtensionJAP;
import com.pca.model.SerieJAP;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ExtensionJAPRepository {
    private EntityManager em;
    public ExtensionJAPRepository(EntityManager em){
        this.em = em;
    }
    public SerieJAP getExtensionJAPById(Integer id) {
        return em.find(SerieJAP.class,id);
    }

    public ExtensionJAP getExtensionJAPByNomFR(String nomFR) {
        TypedQuery<ExtensionJAP> q = em.createQuery("From ExtensionJAP s WHERE s.nom = : nomFR", ExtensionJAP.class);
        return q.getSingleResult();
    }

    public ExtensionJAP saveExtensionJAP(ExtensionJAP extensionJAP) {
        if(extensionJAP.getId()==null){
            em.persist(extensionJAP);
        }else{
            extensionJAP = em.merge(extensionJAP);
        }
        return extensionJAP;
    }

    public void deleteExtensionJAP(ExtensionJAP extensionJAP) {
        if(em.contains(extensionJAP))
            em.remove(extensionJAP);
        else
            em.merge(extensionJAP);

    }
}
