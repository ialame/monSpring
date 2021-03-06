package com.ia.ebay9.repository;

import com.ia.ebay9.entity.SerieJAP;

public interface SerieJAPRepository {
    SerieJAP getSerieJAPById(Integer id);
    SerieJAP getSerieJAPByNomFR(String nomFR);
    SerieJAP saveSerieJAP(SerieJAP serieJAP);
    void deleteSerieJAP(SerieJAP serieJAP);
}
