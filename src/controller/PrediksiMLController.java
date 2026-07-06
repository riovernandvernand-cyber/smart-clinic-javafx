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

    // ==========================
    // PREDIKSI
    // ==========================

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
                    Integer.parseInt(txtPregnancies.getText()));

            p.setGlucose(
                    Integer.parseInt(txtGlucose.getText()));

            p.setBloodPressure(
                    Integer.parseInt(txtBloodPressure.getText()));

            p.setSkinThickness(
                    Integer.parseInt(txtSkinThickness.getText()));

            p.setInsulin(
                    Integer.parseInt(txtInsulin.getText()));

            p.setBmi(
                    Double.parseDouble(txtBMI.getText()));

            p.setDpf(
                    Double.parseDouble(txtDPF.getText()));

            p.setAge(
                    Integer.parseInt(txtAge.getText()));

            // =====================================
            // LOGIKA SEMENTARA (UNTUK DEMO/UJIAN)
            // =====================================

            if (p.getGlucose() >= 200) {

                p.setHasilPrediksi(
                        "RISIKO DIABETES TINGGI");

                p.setProbabilitas(0.87);

                lblHasil.setStyle(
                        "-fx-text-fill:#d63031;"
                        + "-fx-font-size:28px;"
                        + "-fx-font-weight:bold;");

            }

            else if (p.getGlucose() >= 140) {

                p.setHasilPrediksi(
                        "RISIKO DIABETES SEDANG");

                p.setProbabilitas(0.63);

                lblHasil.setStyle(
                        "-fx-text-fill:#e67e22;"
                        + "-fx-font-size:28px;"
                        + "-fx-font-weight:bold;");

            }

            else {

                p.setHasilPrediksi(
                        "RISIKO DIABETES RENDAH");

                p.setProbabilitas(0.20);

                lblHasil.setStyle(
                        "-fx-text-fill:#27ae60;"
                        + "-fx-font-size:28px;"
                        + "-fx-font-weight:bold;");
            }

            lblHasil.setText(
                    p.getHasilPrediksi());

            lblProbabilitas.setText(
                    "Probabilitas : "
                            + String.format("%.2f",
                            p.getProbabilitas()));

            // simpan ke database
            service.simpan(p);

            AlertUtil.success(
                    "Prediksi berhasil disimpan.");

        }

        catch (NumberFormatException e) {

            AlertUtil.warning(
                    "Semua input harus berupa angka.");

        }

        catch (Exception e) {

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

        lblHasil.setText("-");
        lblProbabilitas.setText("Probabilitas : -");

        lblHasil.setStyle(
                "-fx-text-fill:green;"
                + "-fx-font-size:28px;"
                + "-fx-font-weight:bold;");

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