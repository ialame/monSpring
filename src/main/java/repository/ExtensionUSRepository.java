package com.pca.repository;

import com.pca.entity.ExtensionUS;
import com.pca.entity.SerieUS;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ExtensionUSRepository {
    private EntityManager em;
    public ExtensionUSRepository(EntityManager em){
        this.em = em;
    }
    public SerieUS getExtensionUSById(Integer id) {
        return em.find(SerieUS.class,id);
    }

    public ExtensionUS getExtensionUSByNomFR(String nomFR) {
        TypedQuery<ExtensionUS> q = em.createQuery("From ExtensionUS s WHERE s.nomFR = : nomFR", ExtensionUS.class);
        return q.getSingleResult();
    }

    public ExtensionUS saveExtensionUS(ExtensionUS extensionUS) {
        if(extensionUS.getId()==null){
            em.persist(extensionUS);
        }else{
            extensionUS = em.merge(extensionUS);
        }
        return extensionUS;
    }

    public void deleteExtensionUS(ExtensionUS extensionUS) {
        if(em.contains(extensionUS))
            em.remove(extensionUS);
        else
            em.merge(extensionUS);

    }
}
