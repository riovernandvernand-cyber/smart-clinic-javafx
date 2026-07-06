package service;

import dao.ResepObatDAO;
import javafx.collections.ObservableList;
import model.ResepObat;

public class ResepObatService {

    private ResepObatDAO dao =
            new ResepObatDAO();

    // ======================
    // GET ALL
    // ======================
    public ObservableList<ResepObat> getAll() {

        return dao.getAllResep();

    }

    // ======================
    // SIMPAN
    // ======================
    public void simpan(
            ResepObat resep)
            throws Exception {

        if (resep.getPemeriksaan() == null) {

            throw new Exception(
                    "Pemeriksaan harus dipilih");

        }

        if (resep.getObat() == null) {

            throw new Exception(
                    "Obat harus dipilih");

        }

        if (resep.getJumlah() <= 0) {

            throw new Exception(
                    "Jumlah obat tidak valid");

        }

        if (resep.getDosis() == null
                || resep.getDosis().trim().isEmpty()) {

            throw new Exception(
                    "Dosis harus diisi");

        }

        dao.insertResep(resep);

    }

    // ======================
    // UPDATE
    // ======================
    public void update(
            ResepObat resep)
            throws Exception {

        if (resep.getIdResep() <= 0) {

            throw new Exception(
                    "ID Resep tidak valid");

        }

        dao.updateResep(resep);

    }

    // ======================
    // DELETE
    // ======================
    public void delete(int id)
            throws Exception {

        if (id <= 0) {

            throw new Exception(
                    "ID Resep tidak valid");

        }

        dao.deleteResep(id);

    }

}