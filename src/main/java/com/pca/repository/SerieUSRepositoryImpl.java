package com.pca.repository;

import com.pca.model.SerieUS;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class SerieUSRepositoryImpl implements SerieUSRepository {
    private EntityManager em;
    public SerieUSRepositoryImpl(EntityManager em){
        this.em = em;
    }
    @Override
    public SerieUS getSerieUSById(Integer id) {
        return em.find(SerieUS.class,id);
    }

    @Override
    public SerieUS getSerieUSByNomFR(String nomFR) {
        TypedQuery<SerieUS> q = em.createQuery("From SerieUS s WHERE s.nomFR = : nomFR",SerieUS.class);
        return q.getSingleResult();
    }

    @Override
    public SerieUS saveSerieUS(SerieUS serieUS) {
        if(serieUS.getId()==null){
            em.persist(serieUS);
        }else{
            serieUS = em.merge(serieUS);
        }
        return serieUS;
    }

    @Override
    public void deleteSerieUS(SerieUS serieUS) {
        if(em.contains(serieUS))
            em.remove(serieUS);
        else
            em.merge(serieUS);

    }
}
