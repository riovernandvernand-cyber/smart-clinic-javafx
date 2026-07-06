package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import model.PrediksiML;
import service.PrediksiMLService;
import util.AlertUtil;

public class PrediksiMLController {

    @FXML
    private TextField txtPregnancies;

    @FXML
    private TextField txtGlucose;

    @FXML
    private TextField txtBloodPressure;

    @FXML
    private TextField txtSkinThickness;

    @FXML
    private TextField txtInsulin;

    @FXML
    private TextField txtBMI;

    @FXML
    private TextField txtDPF;

    @FXML
    private TextField txtAge;

    @FXML
    private Label lblHasil;

    @FXML
    private Label lblProbabilitas;

    private final PrediksiMLService service =
            new PrediksiMLService();

    @FXML
    public void handlePrediksi() {

        try {

            if (txtPregnancies.getText().isBlank()
                    || txtGlucose.getText().isBlank()
                    || txtBloodPressure.getText().isBlank()
                    || txtSkinThickness.getText().isBlank()
                    || txtInsulin.getText().isBlank()
                    || txtBMI.getText().isBlank()
                    || txtDPF.getText().isBlank()
                    || txtAge.getText().isBlank()) {

                AlertUtil.warning(
                        "Semua data wajib diisi.");

                return;
            }

            PrediksiML p = new PrediksiML();

            p.setPregnancies(
                    Integer.parseInt(
                            txtPregnancies.getText()));

            p.setGlucose(
                    Integer.parseInt(
                            txtGlucose.getText()));

            p.setBloodPressure(
                    Integer.parseInt(
                            txtBloodPressure.getText()));

            p.setSkinThickness(
                    Integer.parseInt(
                            txtSkinThickness.getText()));

            p.setInsulin(
                    Integer.parseInt(
                            txtInsulin.getText()));

            p.setBmi(
                    Double.parseDouble(
                            txtBMI.getText()));

            p.setDpf(
                    Double.parseDouble(
                            txtDPF.getText()));

            p.setAge(
                    Integer.parseInt(
                            txtAge.getText()));

            // ==========================
            // HITUNG SKOR RISIKO
            // ==========================

            int skor = 0;

            if (p.getGlucose() >= 140) {
                skor += 3;
            } else if (p.getGlucose() >= 100) {
                skor += 1;
            }

            if (p.getBmi() >= 30) {
                skor += 2;
            } else if (p.getBmi() >= 25) {
                skor += 1;
            }

            if (p.getAge() >= 45) {
                skor += 2;
            } else if (p.getAge() >= 35) {
                skor += 1;
            }

            if (p.getPregnancies() >= 5) {
                skor += 1;
            }

            if (p.getBloodPressure() >= 90) {
                skor += 1;
            }

            if (p.getInsulin() >= 200) {
                skor += 1;
            }

            if (p.getDpf() >= 0.5) {
                skor += 1;
            }

            if (p.getSkinThickness() >= 35) {
                skor += 1;
            }

            // ==========================
            // HASIL PREDIKSI
            // ==========================

            if (skor >= 7) {

                p.setHasilPrediksi(
                        "RISIKO DIABETES TINGGI");

                p.setProbabilitas(0.92);

                lblHasil.setStyle(
                        "-fx-text-fill:#e53935;"
                      + "-fx-font-size:28px;"
                      + "-fx-font-weight:bold;");

            } else if (skor >= 4) {

                p.setHasilPrediksi(
                        "RISIKO DIABETES SEDANG");

                p.setProbabilitas(0.67);

                lblHasil.setStyle(
                        "-fx-text-fill:#fb8c00;"
                      + "-fx-font-size:28px;"
                      + "-fx-font-weight:bold;");

            } else {

                p.setHasilPrediksi(
                        "RISIKO DIABETES RENDAH");

                p.setProbabilitas(0.22);

                lblHasil.setStyle(
                        "-fx-text-fill:#2e7d32;"
                      + "-fx-font-size:28px;"
                      + "-fx-font-weight:bold;");
            }

            lblHasil.setText(
                    p.getHasilPrediksi());

            lblProbabilitas.setText(
                    "Probabilitas : "
                    + (int)(p.getProbabilitas() * 100)
                    + "%");

            service.simpan(p);

            AlertUtil.success(
                    "Prediksi berhasil disimpan.");
                    } catch (NumberFormatException e) {

            AlertUtil.warning(
                    "Semua input harus berupa angka.");

        } catch (Exception e) {

            AlertUtil.error(
                    e.getMessage());

            e.printStackTrace();

        }

    }

    // ==========================
    // RESET
    // ==========================

    @FXML
    public void handleReset() {

        txtPregnancies.clear();
        txtGlucose.clear();
        txtBloodPressure.clear();
        txtSkinThickness.clear();
        txtInsulin.clear();
        txtBMI.clear();
        txtDPF.clear();
        txtAge.clear();

        lblHasil.setText("Silakan masukkan data pasien");
        lblProbabilitas.setText("Probabilitas : -");

        lblHasil.setStyle(
                "-fx-text-fill:2e7d32;"
              + "-fx-font-size:24px;"
              + "-fx-font-weight:bold;");

        txtPregnancies.requestFocus();

    }

    // ==========================
    // CLOSE
    // ==========================

    @FXML
    public void handleClose() {

        Stage stage =
                (Stage) txtPregnancies
                        .getScene()
                        .getWindow();

        stage.close();

    }

}