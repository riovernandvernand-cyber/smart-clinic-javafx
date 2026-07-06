package service;

import dao.ObatDAO;
import javafx.collections.ObservableList;
import model.Obat;

public class ObatService {

    private ObatDAO dao =
            new ObatDAO();

    // GET ALL
    public ObservableList<Obat> getAll() {

        return dao.getAllObat();
    }

    // SEARCH
    public ObservableList<Obat> search(
            String keyword) {

        return dao.searchObat(
                keyword);
    }

    // DELETE
    public void delete(int id)
            throws Exception {

        if(id <= 0){

            throw new Exception(
                    "ID obat tidak valid");
        }

        dao.deleteObat(id);
    }

    // SIMPAN
    public void simpan(
            Obat o,
            boolean modeEdit)
            throws Exception {

        if(o.getNamaObat() == null
                || o.getNamaObat()
                .trim()
                .isEmpty()) {

            throw new Exception(
                    "Nama obat wajib diisi");
        }

        if(modeEdit){

            dao.updateObat(o);

        } else {

            dao.insertObat(o);
        }
    }
}