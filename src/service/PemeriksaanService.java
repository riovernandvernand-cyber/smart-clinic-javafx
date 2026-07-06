package service;

import dao.PemeriksaanDAO;
import javafx.collections.ObservableList;
import model.Pemeriksaan;

public class PemeriksaanService {

    private PemeriksaanDAO dao =
            new PemeriksaanDAO();

    public ObservableList<Pemeriksaan> getAll() {

        return dao.getAllPemeriksaan();
    }

    // ======================
    // SIMPAN
    // ======================
    public void simpan(
        Pemeriksaan p)
        throws Exception {

    if(p.getPendaftaran() == null) {

        throw new Exception(
                "Pilih pendaftaran");
    }

    if(p.getTanggalPeriksa() == null) {

        throw new Exception(
                "Tanggal wajib diisi");
    }

    dao.insertPemeriksaan(p);
    }

    // ======================
    // UPDATE
    // ======================
    public void update(
        Pemeriksaan p)
        throws Exception {

    if(p.getIdPeriksa() <= 0) {

        throw new Exception(
                "ID tidak valid");
    }

    dao.updatePemeriksaan(p);
    }

    // ======================
    // DELETE
    // ======================
    public void delete(int id)
        throws Exception {

    if(id <= 0) {

        throw new Exception(
                "ID tidak valid");
    }

    dao.deletePemeriksaan(id);
    }
    // ======================
    // SEARCH
    // ======================
    public ObservableList<Pemeriksaan> search(
        String keyword) {

    return dao.searchPemeriksaan(keyword);

    }
}