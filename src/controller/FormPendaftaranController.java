package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import model.*;
import service.*;
import util.AlertUtil;

import java.sql.Date;

public class FormPendaftaranController {

    @FXML
    private TextField txtId;

    @FXML
    private DatePicker dpTanggal;

    @FXML
    private ComboBox<Pasien> cbPasien;

    @FXML
    private ComboBox<Dokter> cbDokter;

    @FXML
    private TextArea txtKeluhan;

    private PendaftaranService pendaftaranService =
            new PendaftaranService();

    private PasienService pasienService =
            new PasienService();

    private DokterService dokterService =
            new DokterService();

    private boolean modeEdit = false;

    @FXML
    public void initialize() {

        cbPasien.setItems(
                pasienService.getAll());

        cbDokter.setItems(
                dokterService.getAll());
    }

    public void setModeTambah() {

        modeEdit = false;
    }

    public void setModeEdit(
            Pendaftaran p) {

        modeEdit = true;

        txtId.setText(
                String.valueOf(
                        p.getIdDaftar()));

        dpTanggal.setValue(
                new java.sql.Date(
                        p.getTanggal().getTime())
                        .toLocalDate());

        cbPasien.setValue(
                p.getPasien());

        cbDokter.setValue(
                p.getDokter());

        txtKeluhan.setText(
                p.getKeluhan());
    }

    @FXML
    public void handleSimpan() {

        try {

            Pendaftaran p =
                    new Pendaftaran();

            if(modeEdit) {

                p.setIdDaftar(
                        Integer.parseInt(
                                txtId.getText()));
            }

            p.setTanggal(
                    Date.valueOf(
                            dpTanggal.getValue()));

            p.setPasien(
                    cbPasien.getValue());

            p.setDokter(
                    cbDokter.getValue());

            p.setKeluhan(
                    txtKeluhan.getText());

            pendaftaranService.simpan(
                    p,
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