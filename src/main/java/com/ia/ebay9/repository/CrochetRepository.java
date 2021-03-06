package com.ia.ebay9.repository;

import com.ia.ebay9.entity.Crochet;
import com.ia.ebay9.entity.Particularite;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CrochetRepository {

        private EntityManager em;
        public CrochetRepository(EntityManager em){
            this.em = em;
        }
        public Crochet getCrochetById(Integer id) {
            return em.find(Crochet.class,id);
        }

        public Crochet getCrochetByNom(String nom) {
            TypedQuery<Crochet> query = em.createQuery("From Crochet s WHERE s.nom = : nom", Crochet.class);
            query.setParameter("nom", nom);
            List<Crochet> crochets = (List<Crochet>) query.getResultList();
            if (crochets == null || crochets.isEmpty()) {
                return null;
            }
            Crochet crochet = crochets.get(0);
            return crochet;
        }

        public Crochet saveCrochet(Crochet crochet) {
            if(crochet.getId()==null){
                em.persist(crochet);
            }else{
                crochet = em.merge(crochet);
            }
            return crochet;
        }

        public void deleteCrochet(Crochet crochet) {
            if(em.contains(crochet))
                em.remove(crochet);
            else
                em.merge(crochet);

        }
    }