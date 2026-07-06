package service;

import dao.JadwalDAO;
import javafx.collections.ObservableList;
import model.Jadwal;

public class JadwalService {

    private JadwalDAO dao =
            new JadwalDAO();

    // =====================
    // GET ALL
    // =====================
    public ObservableList<Jadwal> getAll() {

        return dao.getAllJadwal();
    }

    // =====================
    // SEARCH
    // =====================
    public ObservableList<Jadwal> search(
            String keyword) {

        return dao.searchJadwal(keyword);
    }

    // =====================
    // DELETE
    // =====================
    public void delete(int id)
            throws Exception {

        if (id <= 0) {

            throw new Exception(
                    "ID jadwal tidak valid");
        }

        dao.deleteJadwal(id);
    }

    // =====================
    // SIMPAN
    // =====================
    public void simpan(
            Jadwal jadwal,
            boolean modeEdit)
            throws Exception {

        if (jadwal.getDokter() == null) {

            throw new Exception(
                    "Pilih dokter terlebih dahulu");
        }

        if (jadwal.getHari() == null ||
                jadwal.getHari().trim().isEmpty()) {

            throw new Exception(
                    "Hari wajib dipilih");
        }

        if (jadwal.getJamMulai() == null) {

            throw new Exception(
                    "Jam mulai wajib diisi");
        }

        if (jadwal.getJamSelesai() == null) {

            throw new Exception(
                    "Jam selesai wajib diisi");
        }

        if (modeEdit) {

            dao.updateJadwal(jadwal);

        } else {

            dao.insertJadwal(jadwal);
        }
    }
}