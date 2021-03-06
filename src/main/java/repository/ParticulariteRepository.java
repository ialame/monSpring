package com.pca.repository;

import com.pca.entity.Particularite;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ParticulariteRepository {

        private EntityManager em;
        public ParticulariteRepository(EntityManager em){
            this.em = em;
        }
        public Particularite getParticulariteById(Integer id) {
            return em.find(Particularite.class,id);
        }
        public Particularite  getParticulariteByImgHrefBulbapedia(String Bulbapedia) {
            TypedQuery<Particularite> query = em.createQuery("From Particularite p WHERE p.name = : Bulbapedia", Particularite.class);//imgHrefBulbapedia bulbapedia
            query.setParameter("Bulbapedia", Bulbapedia);
            List<Particularite> particularites = (List<Particularite>) query.getResultList();
            if (particularites == null || particularites.isEmpty()) {
                return null;
            }
            Particularite particularite = particularites.get(0);
            return particularite;
        }

        public Particularite saveParticularite(Particularite particularite) {
            if(particularite.getId()==null){
                em.persist(particularite);
            }else{
                particularite = em.merge(particularite);
            }
            return particularite;
        }

        public void deleteParticularite(Particularite particularite) {
            if(em.contains(particularite))
                em.remove(particularite);
            else
                em.merge(particularite);

        }
    }