package com.pca.repository;

import com.pca.model.CartePokemon;
import com.pca.model.ExtensionJAP;
import com.pca.model.ExtensionUS;
import com.pca.model.SerieUS;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ExtensionUSRepository extends CrudRepository<ExtensionUS, Integer> {

    ExtensionUS findExtensionUSById(Integer id);//getCartePokemonUSByCardRechercheExtension
    Iterable<ExtensionUS> findExtensionUSBySerie(SerieUS serieUS);
    /*
    @Query(value = "select carte From CartePokemon as carte  where carte.nomUS = ?1 AND carte.Recherche = ?2 AND carte.extensionus=?3", nativeQuery = true)
    ExtensionUS findCartePokemonByNomUSAndRechercheAndExtensionUS(String nomUS, String Recherche, ExtensionUS eus);
    @Query(value = "select carte From CartePokemon as carte  where carte.nomUS = ?1 AND carte.Recherche = ?2 AND carte.extensionus=?3", nativeQuery = true)
    ExtensionUS findCartePokemonByNomJAPAndRechercheAndExtensionUS(String nomUS,String Recherche,ExtensionJAP eus);

     */
}
