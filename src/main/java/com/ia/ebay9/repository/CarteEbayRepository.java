package com.ia.ebay9.repository;

import com.ia.ebay9.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CarteEbayRepository {

        private EntityManager em;
        public CarteEbayRepository(EntityManager em){
            this.em = em;
        }
        public CarteEbay getCarteEbayById(Integer id) {
            return em.find(CarteEbay.class,id);
        }
        public CarteEbay  getCarteEbayByCardEtatQualitePointFR(Carte carte, String etat, String qualite,boolean pointFR) {
            try {
                //String sql="SELECT * FROM " + baseDDCarteEbay +" where carte_id="+carte.getId()+" and etat='"+this.etat+"' and qualite='"+this.qualite+"'";
                CarteEbay carteEbay1;
                TypedQuery<CarteEbay> query = em.createQuery("select carteEbay From CarteEbay as carteEbay  where carteEbay.carte = : carte AND carteEbay.etat = : etat AND carteEbay.qualite=:qualite  AND carteEbay.pointFR=:pointFR", CarteEbay.class);//imgHrefBulbapedia bulbapedia

                String qualitePrim,etatPrim;
                if (pointFR) {
                    switch (etat) {
                        case "1000":
                            etatPrim = "Neuf";
                            break;
                        case "3000":
                            etatPrim = "Occasion";
                            break;
                        default:
                            etatPrim = "Non spécifié";
                            break;
                    }
                    switch (qualite.charAt(0)) {  //
                        case 'C':
                            qualitePrim = "Comme neuf"; //
                            break;
                        case 'E':
                            qualitePrim = "Endommagé"; //
                            break;
                        case 'M':
                            qualitePrim = "Moyennement usé"; //
                            break;
                        case 'P':
                            qualitePrim = "Peu usé"; //
                            break;
                        case 'T':
                            qualitePrim = "Très usé"; //
                            break;
                        default:
                            qualitePrim = "Non spécifié"; //
                            break;
                    }
                } else {
                    switch (etat) {
                        case "1000":
                            etatPrim = "New";
                            break;
                        case "3000":
                            etatPrim = "Used";
                            break;
                        default:
                            etatPrim = "Not specified";
                            break;
                    }

                    qualitePrim = "Not specified";

                }
                query.setParameter("carte", carte);
                query.setParameter("etat", etatPrim);
                query.setParameter("qualite", qualitePrim);
                query.setParameter("pointFR", pointFR);
                List<CarteEbay> carteEbays = (List<CarteEbay>) query.getResultList();
                if (carteEbays == null || carteEbays.isEmpty()) {
                    return null;
                }
                CarteEbay carteEbay = carteEbays.get(0);
                return carteEbay;
            }catch(Exception e){
                System.out.println("Dans CarteEbayRepository : "+ e);
            }
            return null;
        }

    }