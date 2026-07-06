package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import model.Pemeriksaan;
import model.RekamMedis;
import service.PemeriksaanService;
import service.RekamMedisService;
import util.AlertUtil;

import java.sql.Date;

public class FormRekamMedisController {

    @FXML
    private TextField txtId;

    @FXML
    private ComboBox<Pemeriksaan> cbPemeriksaan;

    @FXML
    private DatePicker dpTanggal;

    @FXML
    private TextArea txtRingkasan;

    @FXML
    private Button btnSimpan;

    private final PemeriksaanService pemeriksaanService =
            new PemeriksaanService();

    private final RekamMedisService rekamMedisService =
            new RekamMedisService();

    private boolean modeEdit = false;

    private RekamMedis dataEdit;

    // ==========================================
    // INITIALIZE
    // ==========================================

    @FXML
    public void initialize() {

        cbPemeriksaan.setItems(
                pemeriksaanService.getAll());

        txtId.setEditable(false);

        cbPemeriksaan.setOnAction(e -> {

            Pemeriksaan p =
                    cbPemeriksaan.getValue();

            if (p != null) {

                txtRingkasan.setText(
                p.getCatatan()
        );
    }
    });
}

    // ==========================================
    // MODE TAMBAH
    // ==========================================

    public void setModeTambah() {

        modeEdit = false;

        dataEdit = null;

        txtId.clear();

        cbPemeriksaan.setDisable(false);

        cbPemeriksaan.getSelectionModel()
                .clearSelection();

        dpTanggal.setValue(
                java.time.LocalDate.now());

        txtRingkasan.clear();

    }

    // ==========================================
    // MODE EDIT
    // ==========================================

    public void setModeEdit(
            RekamMedis rm) {

        modeEdit = true;

        dataEdit = rm;

        txtId.setText(
                String.valueOf(
                        rm.getIdRekam()));

        cbPemeriksaan.setDisable(true);

        cbPemeriksaan.setValue(
                rm.getPemeriksaan());

        dpTanggal.setValue(
                ((Date) rm.getTanggal())
                        .toLocalDate());

        txtRingkasan.setText(
                rm.getRingkasan());

    }

    // ==========================================
    // SIMPAN
    // ==========================================

    @FXML
    public void handleSimpan() {

        try {

            if (cbPemeriksaan.getValue() == null) {

                AlertUtil.warning(
                        "Silakan pilih data pemeriksaan.");

                return;

            }

            if (dpTanggal.getValue() == null) {

                AlertUtil.warning(
                        "Tanggal rekam medis belum dipilih.");

                return;

            }

            if (txtRingkasan.getText().trim().isEmpty()) {

                AlertUtil.warning(
                        "Ringkasan rekam medis tidak boleh kosong.");

                return;

            }

            RekamMedis rm =
                    new RekamMedis();

            rm.setPemeriksaan(
                    cbPemeriksaan.getValue());

            rm.setTanggal(
                    Date.valueOf(
                            dpTanggal.getValue()));

            rm.setRingkasan(
                    txtRingkasan.getText());

            if (modeEdit) {

                rm.setIdRekam(
                        dataEdit.getIdRekam());

            }

            rekamMedisService.simpan(
                    rm,
                    modeEdit);

            AlertUtil.success(
                    "Data Rekam Medis berhasil disimpan.");

            handleBatal();

        } catch (Exception e) {

            AlertUtil.error(
                    e.getMessage());

            e.printStackTrace();

        }

    }

    // ==========================================
    // BATAL
    // ==========================================

    @FXML
    public void handleBatal() {

        Stage stage =
                (Stage) txtId
                        .getScene()
                        .getWindow();

        stage.close();

    }

}