package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import model.Pemeriksaan;
import model.Pendaftaran;
import service.PemeriksaanService;
import service.PendaftaranService;
import util.AlertUtil;

import java.sql.Date;
import java.time.LocalDate;

public class FormPemeriksaanController {

    @FXML
    private ComboBox<Pendaftaran> cbPendaftaran;

    @FXML
    private DatePicker dpTanggal;

    @FXML
    private TextField txtId,
            txtDiagnosa,
            txtTekanan,
            txtGula,
            txtSuhu,
            txtBerat,
            txtPrediksi,
            txtRisiko;

    @FXML
    private TextArea txtCatatan;

    private final PendaftaranService pendaftaranService =
            new PendaftaranService();

    private final PemeriksaanService pemeriksaanService =
            new PemeriksaanService();

    private boolean modeEdit = false;

    private Pemeriksaan dataEdit;

    @FXML
    public void initialize() {

        cbPendaftaran.setItems(
                pendaftaranService.getAll());

        txtId.setEditable(false);
        txtPrediksi.setEditable(false);
        txtRisiko.setEditable(false);

        dpTanggal.setValue(LocalDate.now());

        txtGula.textProperty().addListener((obs, oldValue, newValue) -> {

            if(newValue == null || newValue.isBlank()){

                txtPrediksi.clear();
                txtRisiko.clear();
                return;
            }

            try{

                double gula =
                        Double.parseDouble(newValue);

                if(gula > 200){

                    txtPrediksi.setText(
                            "Risiko Diabetes Tinggi");

                    txtRisiko.setText(
                            "Tinggi");

                }else{

                    txtPrediksi.setText(
                            "Risiko Normal");

                    txtRisiko.setText(
                            "Rendah");
                }

            }catch(Exception e){

                txtPrediksi.clear();
                txtRisiko.clear();

            }

        });

    }

    // ==========================
    // MODE TAMBAH
    // ==========================

    public void setModeTambah(){

        modeEdit = false;
        dataEdit = null;

        txtId.clear();

        cbPendaftaran.getSelectionModel().clearSelection();

        dpTanggal.setValue(LocalDate.now());

        txtDiagnosa.clear();
        txtTekanan.clear();
        txtGula.clear();
        txtSuhu.clear();
        txtBerat.clear();

        txtCatatan.clear();

        txtPrediksi.clear();
        txtRisiko.clear();

    }

    // ==========================
    // MODE EDIT
    // ==========================

    public void setModeEdit(Pemeriksaan p){

        modeEdit = true;
        dataEdit = p;

        txtId.setText(
                String.valueOf(
                        p.getIdPeriksa()));

        cbPendaftaran.setValue(
                p.getPendaftaran());

        dpTanggal.setValue(
                ((Date)p.getTanggalPeriksa())
                        .toLocalDate());

        txtDiagnosa.setText(
                p.getDiagnosa());

        txtTekanan.setText(
                String.valueOf(
                        p.getTekananDarah()));

        txtGula.setText(
                String.valueOf(
                        p.getGulaDarah()));

        txtSuhu.setText(
                String.valueOf(
                        p.getSuhu()));

        txtBerat.setText(
                String.valueOf(
                        p.getBeratBadan()));

        txtCatatan.setText(
                p.getCatatan());

        txtPrediksi.setText(
                p.getHasilPrediksi());

        txtRisiko.setText(
                p.getTingkatResiko());

    }

    // ==========================
    // SIMPAN
    // ==========================

    @FXML
    public void handleSimpan(){

        try{

            if(cbPendaftaran.getValue()==null){

                AlertUtil.warning(
                        "Pilih pendaftaran terlebih dahulu!");

                return;
            }

            if(dpTanggal.getValue()==null){

                AlertUtil.warning(
                        "Tanggal pemeriksaan belum dipilih!");

                return;
            }

            if(txtDiagnosa.getText().isBlank()
                    || txtTekanan.getText().isBlank()
                    || txtGula.getText().isBlank()
                    || txtSuhu.getText().isBlank()
                    || txtBerat.getText().isBlank()){

                AlertUtil.warning(
                        "Semua data wajib diisi!");

                return;
            }

            Pemeriksaan p =
                    new Pemeriksaan();

            p.setPendaftaran(
                    cbPendaftaran.getValue());

            p.setTanggalPeriksa(
                    Date.valueOf(
                            dpTanggal.getValue()));

            p.setDiagnosa(
                    txtDiagnosa.getText());

            p.setTekananDarah(
                    Double.parseDouble(
                            txtTekanan.getText()));

            p.setGulaDarah(
                    Double.parseDouble(
                            txtGula.getText()));

            p.setSuhu(
                    Double.parseDouble(
                            txtSuhu.getText()));

            p.setBeratBadan(
                    Double.parseDouble(
                            txtBerat.getText()));

            p.setCatatan(
                    txtCatatan.getText());

            if(p.getGulaDarah()>200){

                p.setHasilPrediksi(
                        "Risiko Diabetes Tinggi");

                p.setTingkatResiko(
                        "Tinggi");

            }else{

                p.setHasilPrediksi(
                        "Risiko Normal");

                p.setTingkatResiko(
                        "Rendah");

            }

            txtPrediksi.setText(
                    p.getHasilPrediksi());

            txtRisiko.setText(
                    p.getTingkatResiko());

            if(modeEdit){

                p.setIdPeriksa(
                        dataEdit.getIdPeriksa());

                pemeriksaanService.update(p);

            }else{

                pemeriksaanService.simpan(p);

            }

            AlertUtil.success(
                    "Data berhasil disimpan");

            handleBatal();

        }catch(NumberFormatException e){

            AlertUtil.error(
                    "Tekanan darah, gula darah, suhu, dan berat badan harus berupa angka.");

        }catch(Exception e){

            AlertUtil.error(
                    e.getMessage());

            e.printStackTrace();

        }

    }

    // ==========================
    // BATAL
    // ==========================

    @FXML
    public void handleBatal(){

        Stage stage =
                (Stage) cbPendaftaran
                        .getScene()
                        .getWindow();

        stage.close();

    }

}