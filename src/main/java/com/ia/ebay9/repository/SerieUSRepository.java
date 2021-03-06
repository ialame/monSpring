package com.ia.ebay9.repository;

import com.ia.ebay9.entity.SerieUS;

public interface SerieUSRepository {
    SerieUS getSerieUSById(Integer id);
    SerieUS getSerieUSByNomFR(String nomFR);
    SerieUS saveSerieUS(SerieUS serieUS);
    void deleteSerieUS(SerieUS serieUS);
}
