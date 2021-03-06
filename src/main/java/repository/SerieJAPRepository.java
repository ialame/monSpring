package com.pca.repository;

import com.pca.entity.SerieJAP;

public interface SerieJAPRepository {
    SerieJAP getSerieJAPById(Integer id);
    SerieJAP getSerieJAPByNomFR(String nomFR);
    SerieJAP saveSerieJAP(SerieJAP serieJAP);
    void deleteSerieJAP(SerieJAP serieJAP);
}
