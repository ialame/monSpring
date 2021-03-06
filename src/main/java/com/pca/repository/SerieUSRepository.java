package com.pca.repository;

import com.pca.model.SerieUS;

public interface SerieUSRepository {
    SerieUS getSerieUSById(Integer id);
    SerieUS getSerieUSByNomFR(String nomFR);
    SerieUS saveSerieUS(SerieUS serieUS);
    void deleteSerieUS(SerieUS serieUS);
}
