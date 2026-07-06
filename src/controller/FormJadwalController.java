package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import model.Dokter;
import model.Jadwal;
import service.DokterService;
import service.JadwalService;
import util.AlertUtil;

import java.sql.Time;

public class FormJadwalController {

    @FXML
    private TextField txtId;

    @FXML
    private ComboBox<Dokter> cbDokter;

    @FXML
    private ComboBox<String> cbHari;

    @FXML
    private TextField txtJamMulai;

    @FXML
    private TextField txtJamSelesai;

    private DokterService dokterService =
            new DokterService();

    private JadwalService jadwalService =
            new JadwalService();

    private boolean modeEdit = false;

    private Jadwal dataEdit;

    @FXML
    public void initialize() {

        cbDokter.setItems(
                dokterService.getAll());

        cbHari.setItems(
                FXCollections.observableArrayList(
                        "Senin",
                        "Selasa",
                        "Rabu",
                        "Kamis",
                        "Jumat",
                        "Sabtu"
                ));
    }

    public void setModeTambah() {

        modeEdit = false;

        txtId.clear();

        cbDokter.getSelectionModel().clearSelection();

        cbHari.getSelectionModel().clearSelection();

        txtJamMulai.clear();

        txtJamSelesai.clear();

        txtId.setEditable(false);
    }

    public void setModeEdit(Jadwal j) {

        modeEdit = true;

        dataEdit = j;

        txtId.setText(
                String.valueOf(
                        j.getIdJadwal()));

        txtId.setEditable(false);

        cbDokter.setValue(
                j.getDokter());

        cbHari.setValue(
                j.getHari());

        txtJamMulai.setText(
                j.getJamMulai().toString());

        txtJamSelesai.setText(
                j.getJamSelesai().toString());
    }

    @FXML
    public void handleSimpan() {

        try {

            if (cbDokter.getValue() == null) {

                AlertUtil.warning(
                        "Pilih dokter");

                return;
            }

            if (cbHari.getValue() == null) {

                AlertUtil.warning(
                        "Pilih hari");

                return;
            }

            Jadwal jadwal =
                    new Jadwal();

            jadwal.setDokter(
                    cbDokter.getValue());

            jadwal.setHari(
                    cbHari.getValue());

            jadwal.setJamMulai(
                    Time.valueOf(
                            txtJamMulai.getText()));

            jadwal.setJamSelesai(
                    Time.valueOf(
                            txtJamSelesai.getText()));

            if (modeEdit) {

                jadwal.setIdJadwal(
                        dataEdit.getIdJadwal());

            }

            jadwalService.simpan(
                    jadwal,
                    modeEdit);

            AlertUtil.success(
                    "Data berhasil disimpan");

            closeWindow();

        } catch (Exception e) {

            AlertUtil.error(
                    e.getMessage());

            e.printStackTrace();
        }
    }

    @FXML
    public void handleBatal() {

        closeWindow();
    }

    private void closeWindow() {

        Stage stage =
                (Stage) txtId
                        .getScene()
                        .getWindow();

        stage.close();
    }
}