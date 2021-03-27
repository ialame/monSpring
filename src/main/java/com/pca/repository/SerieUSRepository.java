package com.pca.repository;

import com.pca.model.SerieUS;
import org.springframework.data.repository.CrudRepository;

public interface SerieUSRepository extends CrudRepository<SerieUS, Integer> {
    SerieUS findSerieUSById(Integer id);
}
