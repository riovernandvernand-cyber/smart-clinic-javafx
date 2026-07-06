package controller;

import dao.DashboardDAO;
import dao.PasienDAO;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import model.Pasien;

import util.SceneUtil;

public class DashboardController {

    // ===========================
    // SIDEBAR
    // ===========================

    @FXML
    private VBox sidebar;

    @FXML
    private Label logoTitle;

    @FXML
    private Label lblMaster;

    @FXML
    private Label lblTransaksi;

    @FXML
    private Label lblLaporan;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnPasien;

    @FXML
    private Button btnDokter;

    @FXML
    private Button btnPetugas;

    @FXML
    private Button btnObat;

    @FXML
    private Button btnPoli;

    @FXML
    private Button btnJadwal;

    @FXML
    private Button btnPendaftaran;

    @FXML
    private Button btnPemeriksaan;

    @FXML
    private Button btnResep;

    @FXML
    private Button btnRekam;

    @FXML
    private Button btnPrediksi;

    @FXML
    private Button btnHasil;

    @FXML
    private Button btnLaporan;

    @FXML
    private Button btnLogout;

    // ===========================
    // CARD DASHBOARD
    // ===========================

    @FXML
    private Label lblTotalPasien;

    @FXML
    private Label lblTotalRekam;

    @FXML
    private Label lblTotalPrediksi;

    @FXML
    private Label lblTotalObat;

    // ===========================
    // SEARCH
    // ===========================

    @FXML
    private TextField txtCariDashboard;

    // ===========================
    // TABLE DASHBOARD
    // ===========================

    @FXML
    private TableView<Pasien> tableDashboard;

    @FXML
    private TableColumn<Pasien, Integer> colId;

    @FXML
    private TableColumn<Pasien, String> colNama;

    @FXML
    private TableColumn<Pasien, Integer> colUmur;

    @FXML
    private TableColumn<Pasien, String> colGender;

    @FXML
    private TableColumn<Pasien, String> colAlamat;

    // ===========================
    // DAO
    // ===========================

    private final DashboardDAO dashboardDAO =
            new DashboardDAO();

    private final PasienDAO pasienDAO =
            new PasienDAO();

    private boolean collapsed = false;

    // ===========================
    // INITIALIZE
    // ===========================

    @FXML
public void initialize() {

    initTable();

    tableDashboard.setColumnResizePolicy(
            TableView.CONSTRAINED_RESIZE_POLICY);

    loadStatistik();

    loadPasienTerbaru();

    txtCariDashboard.textProperty().addListener(
            (observable, oldValue, newValue) ->
                    cariPasien(newValue));

}

    // ===========================
    // LOAD STATISTIK DASHBOARD
    // ===========================

    private void loadStatistik() {

    try {

        lblTotalPasien.setText(
                String.valueOf(
                        dashboardDAO.getTotalPasien()));

        lblTotalRekam.setText(
                String.valueOf(
                        dashboardDAO.getTotalRekam()));

        lblTotalPrediksi.setText(
                String.valueOf(
                        dashboardDAO.getTotalPrediksi()));

        lblTotalObat.setText(
                String.valueOf(
                        dashboardDAO.getTotalObat()));

    } catch (Exception e) {

        e.printStackTrace();

    }

}

    // ===========================
    // INISIALISASI TABLE
    // ===========================

    private void initTable() {

    colId.setCellValueFactory(
            new PropertyValueFactory<>("idPasien"));

    colNama.setCellValueFactory(
            new PropertyValueFactory<>("nama"));

    colUmur.setCellValueFactory(
            new PropertyValueFactory<>("umur"));

    colGender.setCellValueFactory(
            new PropertyValueFactory<>("gender"));

    colAlamat.setCellValueFactory(
            new PropertyValueFactory<>("alamat"));

    // ukuran kolom
    colId.setPrefWidth(70);
    colNama.setPrefWidth(240);
    colUmur.setPrefWidth(90);
    colGender.setPrefWidth(140);
    colAlamat.setPrefWidth(420);

}

    // ===========================
    // LOAD 5 PASIEN TERBARU
    // ===========================

    private void loadPasienTerbaru() {

    try {

        ObservableList<Pasien> list =
                pasienDAO.getLatestPasien();

        tableDashboard.setItems(list);

    } catch (Exception e) {

        e.printStackTrace();

    }

}   

    // ===========================
    // SEARCH PASIEN
    // ===========================

    private void cariPasien(String keyword) {

    try {

        if (keyword == null || keyword.trim().isEmpty()) {

            loadPasienTerbaru();
            return;

        }

        ObservableList<Pasien> list =
                pasienDAO.searchPasien(keyword);

        tableDashboard.setItems(list);

    } catch (Exception e) {

        e.printStackTrace();

    }

}

    // ===========================
    // TOGGLE SIDEBAR
    // ===========================

    @FXML
    private void toggleSidebar() {

        if (!collapsed) {

            sidebar.setPrefWidth(80);

            logoTitle.setVisible(false);

            lblMaster.setVisible(false);
            lblTransaksi.setVisible(false);
            lblLaporan.setVisible(false);

            btnDashboard.setText("🏠");
            btnPasien.setText("👨");
            btnDokter.setText("🩺");
            btnPetugas.setText("👩");
            btnObat.setText("💊");
            btnPoli.setText("🏥");
            btnJadwal.setText("📅");

            btnPendaftaran.setText("📝");
            btnPemeriksaan.setText("🩻");
            btnResep.setText("💊");
            btnRekam.setText("📋");
            btnPrediksi.setText("🧠");

            btnHasil.setText("📊");
            btnLaporan.setText("📈");
            btnLogout.setText("🚪");

            collapsed = true;

        } else {

            sidebar.setPrefWidth(240);

            logoTitle.setVisible(true);

            lblMaster.setVisible(true);
            lblTransaksi.setVisible(true);
            lblLaporan.setVisible(true);

            logoTitle.setText("SMART CLINIC");

            btnDashboard.setText("🏠 Dashboard");
            btnPasien.setText("👨‍⚕ Pasien");
            btnDokter.setText("🩺 Dokter");
            btnPetugas.setText("👩‍💼 Petugas");
            btnObat.setText("💊 Obat");
            btnPoli.setText("🏥 Poli");
            btnJadwal.setText("📅 Jadwal Dokter");

            btnPendaftaran.setText("📝 Pendaftaran");
            btnPemeriksaan.setText("🩻 Pemeriksaan");
            btnResep.setText("💊 Resep Obat");
            btnRekam.setText("📋 Rekam Medis");
            btnPrediksi.setText("🧠 Prediksi ML");

            btnHasil.setText("📊 Hasil & Riwayat");
            btnLaporan.setText("📈 Laporan Klinik");
            btnLogout.setText("🚪 Logout");

            collapsed = false;

        }

    }

    // ===========================
    // MASTER DATA
    // ===========================

    @FXML
    private void openPasien() {
        SceneUtil.openMaximizedWindow(
                "/view/pasien.fxml",
                "Data Pasien");
    }

    @FXML
    private void openDokter() {
        SceneUtil.openMaximizedWindow(
                "/view/dokter.fxml",
                "Data Dokter");
    }

    @FXML
    private void openPetugas() {
        SceneUtil.openMaximizedWindow(
                "/view/petugas.fxml",
                "Data Petugas");
    }

    @FXML
    private void openObat() {
        SceneUtil.openMaximizedWindow(
                "/view/obat.fxml",
                "Data Obat");
    }

    @FXML
    private void openPoli() {
        SceneUtil.openMaximizedWindow(
                "/view/poli.fxml",
                "Data Poli");
    }

    @FXML
    private void openJadwal() {
        SceneUtil.openMaximizedWindow(
                "/view/jadwal.fxml",
                "Data Jadwal Dokter");
    }

    // ===========================
    // TRANSAKSI
    // ===========================

    @FXML
    private void openPendaftaran() {
        SceneUtil.openMaximizedWindow(
                "/view/pendaftaran.fxml",
                "Pendaftaran");
    }

    @FXML
    private void openPemeriksaan() {
        SceneUtil.openMaximizedWindow(
                "/view/pemeriksaan.fxml",
                "Pemeriksaan");
    }

    @FXML
    private void openResep() {
        SceneUtil.openMaximizedWindow(
                "/view/resep_obat.fxml",
                "Resep Obat");
    }

    @FXML
    private void openRekam() {
        SceneUtil.openMaximizedWindow(
                "/view/rekam_medis.fxml",
                "Rekam Medis");
    }

    @FXML
    private void openPrediksi() {
        SceneUtil.openMaximizedWindow(
                "/view/prediksi.fxml",
                "Prediksi Machine Learning");
    }

    // ===========================
    // LAPORAN
    // ===========================

    @FXML
    private void openHasil() {
        SceneUtil.openMaximizedWindow(
                "/view/hasil_prediksi.fxml",
                "Hasil & Riwayat Prediksi");
    }

    @FXML
    private void openLaporan() {
        SceneUtil.openMaximizedWindow(
                "/view/laporan.fxml",
                "Laporan Klinik");
    }

    // ===========================
    // LOGOUT
    // ===========================

    @FXML
    private void logout() {

        SceneUtil.openWindow(
                "/view/login.fxml",
                "Login Smart Clinic");

        btnLogout
                .getScene()
                .getWindow()
                .hide();

    }

}