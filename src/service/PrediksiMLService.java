package service;

import dao.PrediksiMLDAO;
import model.PrediksiML;

import java.util.List;

public class PrediksiMLService {

    private final PrediksiMLDAO dao =
            new PrediksiMLDAO();

    // ==========================
    // SIMPAN DATA PREDIKSI
    // ==========================
    public void simpan(PrediksiML p)
            throws Exception {

        if (p.getGlucose() <= 0) {

            throw new Exception(
                    "Glucose harus lebih dari 0");

        }

        dao.insert(p);

    }

    // ==========================
    // AMBIL SEMUA DATA PREDIKSI
    // ==========================
    public List<PrediksiML> getAll() {

        return dao.getAll();

    }

}