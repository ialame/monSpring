package com.pca.repository;

import com.pca.model.SerieJAP;
import org.springframework.data.repository.CrudRepository;

public interface SerieJAPRepository extends CrudRepository<SerieJAP, Integer> {

    SerieJAP findSerieJAPById(Integer id);

}
