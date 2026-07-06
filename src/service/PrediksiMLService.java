package service;

import dao.PrediksiMLDAO;
import model.PrediksiML;

public class PrediksiMLService {

    private final PrediksiMLDAO dao =
            new PrediksiMLDAO();

    public void simpan(PrediksiML p)
            throws Exception {

        if (p.getGlucose() <= 0) {

            throw new Exception(
                    "Glucose harus lebih dari 0");

        }

        dao.insert(p);

    }

}