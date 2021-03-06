package com.pca.model;

import com.pca.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ConsoleEbay {

    public static void main(String[] args) throws Exception {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            //entityManagerFactory = Persistence.createEntityManagerFactory("alame");
            //entityManager = entityManagerFactory.createEntityManager();

            System.out.println("--------------------------------------------------------------");



            //Carte c = entityManager.find( Carte.class, 7051 );
            //CartCert cc = entityManager.find( CartCert.class, 166 );
            int id=166;
            CartCert cc =session.get(CartCert.class,id);
            cc.setCodeBarre("81964589");
            SerieUS serie = new SerieUS("mnp");
            ExtensionUS extensionUS = new ExtensionUS("extensionUS",serie);
            CartePokemon cartePokemon = new CartePokemon("Card2",extensionUS,null,"nomFR","123/456");

            //cc= new CartCert(LocalDateTime.now(),"aaaaa",cartePokemon);
            //cartePokemon.getCartcerts().add(cc);

            /*EntityTransaction trans = entityManager.getTransaction();
            trans.begin();
            entityManager.persist( cartePokemon );
            cc.setCarte((Carte) cartePokemon);
            cartePokemon.getCartcerts().add(cc);

            System.out.println("--------------------------------------------------------------");
            trans.commit();*/
            //System.out.println( extensionJAP );
            /*for (CartCert cc: cmd.getCartcerts()) {
                System.out.println( cc.id );
            }*/

            /*CartePokemon cartePokemon = entityManager.find( CartePokemon.class, 1 );
            ExtensionJAP extensionjap =cartePokemon.getExtensionJAP();
            SerieJAP seriejap = extensionjap.getSerieJAP();

            System.out.println( "=============================================================================" );
            System.out.println( cartePokemon );
            System.out.println( "=============================================================================" );
            System.out.println( extensionjap );
            System.out.println( "=============================================================================" );
            System.out.println( "Serie: "+seriejap );
            System.out.println( "=============================================================================" );

            List<ExtensionJAP> extensionsjap = seriejap.getExtensionsJAP();
            for (ExtensionJAP e: extensionsjap) {
                System.out.println( e );
            }*/

            /*Eleve e = new Eleve("GOUAR","Latifa",16);
            EntityTransaction trans = entityManager.getTransaction();

            trans.begin();
            entityManager.persist( e );
            trans.commit();*/

        } finally {
            if ( entityManager != null ) entityManager.close();
            if ( entityManagerFactory != null ) entityManagerFactory.close();
        }
    }
}