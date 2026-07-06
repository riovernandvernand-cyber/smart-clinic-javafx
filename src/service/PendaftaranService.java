package service;

import dao.PendaftaranDAO;
import javafx.collections.ObservableList;
import model.Pendaftaran;

public class PendaftaranService {

    private PendaftaranDAO dao =
            new PendaftaranDAO();

    public ObservableList<Pendaftaran> getAll() {

        return dao.getAllPendaftaran();
    }

    public void delete(int id)
            throws Exception {

        if(id <= 0) {

            throw new Exception(
                    "ID tidak valid");
        }

        dao.deletePendaftaran(id);
    }

    public void simpan(
            Pendaftaran p,
            boolean modeEdit)
            throws Exception {

        if(p.getPasien() == null) {

            throw new Exception(
                    "Pilih pasien");
        }

        if(p.getDokter() == null) {

            throw new Exception(
                    "Pilih dokter");
        }

        if(modeEdit) {

            dao.updatePendaftaran(p);

        } else {

            dao.insertPendaftaran(p);
        }
    }
}