package com.ia.ebay9.repository;

import com.ia.ebay9.entity.CartePokemon;
import com.ia.ebay9.entity.ExtensionJAP;
import com.ia.ebay9.entity.ExtensionUS;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CartePokemon2Repository extends CrudRepository<CartePokemon, Integer> {

    CartePokemon findCartePokemonById(Integer id);//getCartePokemonUSByCardRechercheExtension
    @Query(value = "select carte From CartePokemon as carte  where carte.nomUS = ?1 AND carte.Recherche = ?2 AND carte.extensionus=?3", nativeQuery = true)
    CartePokemon findCartePokemonByNomUSAndRechercheAndExtensionUS(String nomUS, String Recherche, ExtensionUS eus);
    @Query(value = "select carte From CartePokemon as carte  where carte.nomUS = ?1 AND carte.Recherche = ?2 AND carte.extensionus=?3", nativeQuery = true)
    CartePokemon findCartePokemonByNomJAPAndRechercheAndExtensionUS(String nomUS,String Recherche,ExtensionJAP eus);
}
