package com.pca.repository;

import com.pca.model.ExtensionJAP;
import org.springframework.data.repository.CrudRepository;

public interface ExtensionJAPRepository extends CrudRepository<ExtensionJAP, Integer> {

    ExtensionJAP findExtensionJAPById(Integer id);//getCartePokemonUSByCardRechercheExtension
    /*
    @Query(value = "select carte From CartePokemon as carte  where carte.nomUS = ?1 AND carte.Recherche = ?2 AND carte.extensionus=?3", nativeQuery = true)
    ExtensionUS findCartePokemonByNomUSAndRechercheAndExtensionUS(String nomUS, String Recherche, ExtensionUS eus);
    @Query(value = "select carte From CartePokemon as carte  where carte.nomUS = ?1 AND carte.Recherche = ?2 AND carte.extensionus=?3", nativeQuery = true)
    ExtensionUS findCartePokemonByNomJAPAndRechercheAndExtensionUS(String nomUS,String Recherche,ExtensionJAP eus);

     */
}
