package service;

import dao.RekamMedisDAO;
import javafx.collections.ObservableList;
import model.RekamMedis;

public class RekamMedisService {

    private RekamMedisDAO dao =
            new RekamMedisDAO();

    public ObservableList<RekamMedis> getAll() {

        return dao.getAll();
    }

    public ObservableList<RekamMedis> search(String key) {

        return dao.search(key);
    }

    public void delete(int id) {

        dao.delete(id);
    }

    public void simpan(
            RekamMedis rm,
            boolean modeEdit) {

        if (modeEdit) {

            dao.update(rm);

        } else {

            dao.insert(rm);

        }

    }

}