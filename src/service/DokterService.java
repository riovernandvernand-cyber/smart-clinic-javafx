package service;

import dao.DokterDAO;
import javafx.collections.ObservableList;
import model.Dokter;

public class DokterService {

    private DokterDAO dao =
            new DokterDAO();

    public ObservableList<Dokter> getAll() {

        return dao.getAllDokter();
    }

    public ObservableList<Dokter> search(
            String keyword) {

        return dao.searchDokter(keyword);
    }

    public void delete(int id)
            throws Exception {

        if (id <= 0) {

            throw new Exception(
                    "ID dokter tidak valid");
        }

        dao.deleteDokter(id);
    }

    public void simpan(
            Dokter dokter,
            boolean modeEdit)
            throws Exception {

        if (dokter.getNama() == null
                || dokter.getNama()
                .trim()
                .isEmpty()) {

            throw new Exception(
                    "Nama dokter wajib diisi");
        }

        if (modeEdit) {

            dao.updateDokter(dokter);

        } else {

            dao.insertDokter(dokter);
        }
    }
}