package com.pca.repository;

import com.pca.entity.SerieUS;

public interface SerieUSRepository {
    SerieUS getSerieUSById(Integer id);
    SerieUS getSerieUSByNomFR(String nomFR);
    SerieUS saveSerieUS(SerieUS serieUS);
    void deleteSerieUS(SerieUS serieUS);
}
