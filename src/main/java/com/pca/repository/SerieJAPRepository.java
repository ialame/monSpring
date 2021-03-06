package com.pca.repository;

import com.pca.model.SerieJAP;

public interface SerieJAPRepository {
    SerieJAP getSerieJAPById(Integer id);
    SerieJAP getSerieJAPByNomFR(String nomFR);
    SerieJAP saveSerieJAP(SerieJAP serieJAP);
    void deleteSerieJAP(SerieJAP serieJAP);
}
