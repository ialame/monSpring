package com.pca.repository;

import com.pca.entity.SerieJAP;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class SerieJAPRepositoryImpl implements SerieJAPRepository {
    private EntityManager em;
    public SerieJAPRepositoryImpl(EntityManager em){
        this.em = em;
    }
    @Override
    public SerieJAP getSerieJAPById(Integer id) {
        return em.find(SerieJAP.class,id);
    }

    @Override
    public SerieJAP getSerieJAPByNomFR(String nomFR) {
        TypedQuery<SerieJAP> q = em.createQuery("From SerieJAP s WHERE s.nom = : nomFR",SerieJAP.class);
        return q.getSingleResult();
    }

    @Override
    public SerieJAP saveSerieJAP(SerieJAP serieJAP) {
        if(serieJAP.getId()==null){
            em.persist(serieJAP);
        }else{
            serieJAP = em.merge(serieJAP);
        }
        return serieJAP;
    }

    @Override
    public void deleteSerieJAP(SerieJAP serieJAP) {
        if(em.contains(serieJAP))
            em.remove(serieJAP);
        else
            em.merge(serieJAP);

    }
}
