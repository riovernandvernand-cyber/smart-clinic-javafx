package service;

import dao.PetugasDAO;
import javafx.collections.ObservableList;
import model.Petugas;

public class PetugasService {

    private PetugasDAO dao =
            new PetugasDAO();

    // GET ALL
    public ObservableList<Petugas> getAll() {

        return dao.getAllPetugas();
    }

    // SEARCH
    public ObservableList<Petugas> search(
            String keyword) {

        return dao.searchPetugas(
                keyword);
    }

    // DELETE
    public void delete(int id)
            throws Exception {

        if(id <= 0){

            throw new Exception(
                    "ID petugas tidak valid");
        }

        dao.deletePetugas(id);
    }

    // SIMPAN
    public void simpan(
            Petugas p,
            boolean modeEdit)
            throws Exception {

        // VALIDASI
        if(p.getNama() == null
                || p.getNama()
                .trim()
                .isEmpty()) {

            throw new Exception(
                    "Nama petugas wajib diisi");
        }

        if(modeEdit){

            dao.updatePetugas(p);

        } else {

            dao.insertPetugas(p);
        }
    }
}