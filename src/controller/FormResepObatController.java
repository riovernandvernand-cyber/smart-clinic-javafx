package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import model.Obat;
import model.Pemeriksaan;
import model.ResepObat;
import service.ObatService;
import service.PemeriksaanService;
import service.ResepObatService;
import util.AlertUtil;

public class FormResepObatController {

    @FXML
    private TextField txtId,
            txtJumlah;

    @FXML
    private ComboBox<String> cbDosis;
    @FXML
    private TextArea txtKeterangan;

    @FXML
    private ComboBox<Pemeriksaan> cbPemeriksaan;

    @FXML
    private ComboBox<Obat> cbObat;

    private final PemeriksaanService pemeriksaanService =
            new PemeriksaanService();

    private final ObatService obatService =
            new ObatService();

    private final ResepObatService resepService =
            new ResepObatService();

    private boolean modeEdit = false;

    private ResepObat dataEdit;

    @FXML
    public void initialize() {

        cbPemeriksaan.setItems(
                pemeriksaanService.getAll());

        cbObat.setItems(
                obatService.getAll());

        txtId.setEditable(false);
        cbDosis.getItems().addAll(
        "1x1 sehari",
        "2x1 sehari",
        "3x1 sehari",
        "2x2 sehari",
        "3x2 sehari");

    }

    // ==========================
    // MODE TAMBAH
    // ==========================

    public void setModeTambah() {

        modeEdit = false;

        txtId.clear();

        cbPemeriksaan.getSelectionModel().clearSelection();
        cbObat.getSelectionModel().clearSelection();

        txtJumlah.clear();
        cbDosis.getSelectionModel().clearSelection();
        txtKeterangan.clear();

    }

    // ==========================
    // MODE EDIT
    // ==========================

    public void setModeEdit(
            ResepObat r) {

        modeEdit = true;

        dataEdit = r;

        txtId.setText(
                String.valueOf(
                        r.getIdResep()));

        cbPemeriksaan.setValue(
                r.getPemeriksaan());

        cbObat.setValue(
                r.getObat());

        txtJumlah.setText(
                String.valueOf(
                        r.getJumlah()));

        cbDosis.setValue(
            r.getDosis());

        txtKeterangan.setText(
                r.getKeterangan());

    }

    // ==========================
    // SIMPAN
    // ==========================

    @FXML
    public void handleSimpan() {

        try {

            ResepObat resep =
                    new ResepObat();

            resep.setPemeriksaan(
                    cbPemeriksaan.getValue());

            resep.setObat(
                    cbObat.getValue());

            resep.setJumlah(
                    Integer.parseInt(
                            txtJumlah.getText()));

            resep.setDosis(
                    cbDosis.getValue());

            resep.setKeterangan(
                    txtKeterangan.getText());

            if (modeEdit) {

                resep.setIdResep(
                        dataEdit.getIdResep());

                resepService.update(resep);

            } else {

                resepService.simpan(resep);

            }

            AlertUtil.success(
                    "Data berhasil disimpan");

            handleBatal();

        } catch (Exception e) {

            AlertUtil.error(
                    e.getMessage());

            e.printStackTrace();

        }

    }

    // ==========================
    // BATAL
    // ==========================

    @FXML
    public void handleBatal() {

        Stage stage =
                (Stage) txtJumlah
                        .getScene()
                        .getWindow();

        stage.close();

    }

}