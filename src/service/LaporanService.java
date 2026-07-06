package service;

import dao.LaporanDAO;
import model.PrediksiML;

import java.util.List;

public class LaporanService {

    private final LaporanDAO dao =
            new LaporanDAO();

    public int totalPasien() {

        return dao.totalPasien();

    }

    public int totalDokter() {

        return dao.totalDokter();

    }

    public int totalPemeriksaan() {

        return dao.totalPemeriksaan();

    }

    public int totalPrediksi() {

        return dao.totalPrediksi();

    }

    public List<PrediksiML> getRiwayatPrediksi() {

        return dao.getRiwayatPrediksi();

    }

}