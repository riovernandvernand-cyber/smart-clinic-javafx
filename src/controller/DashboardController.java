package controller;

import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
// import javafx.stage.Stage;
// import javafx.animation.TranslateTransition;
// import javafx.util.Duration;
import util.SceneUtil;

public class DashboardController {
    @FXML
    private VBox sidebar;
    @FXML
    private Label logoTitle;
    @FXML
    private Button btnDashboard,btnPasien,btnDokter,btnPetugas,btnObat,btnPoli,btnJadwal;
    private boolean collapsed = false;
    @FXML
    private Label lblMaster, lblTransaksi, lblLaporan;
    @FXML
    private Button btnPendaftaran,btnPemeriksaan,btnResep,btnRekam,btnPrediksi;

    @FXML
    private void toggleSidebar(){

        if(!collapsed){
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
            collapsed = true;
        }else{
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
            btnJadwal.setText("📅");
            btnPendaftaran.setText("📝 Pendaftaran");
            btnPemeriksaan.setText("🩻 Pemeriksaan");
            btnResep.setText("💊 Resep Obat");
            btnRekam.setText("📋 Rekam Medis");
            btnPrediksi.setText("🧠 Prediksi ML");

            collapsed = false;
        }
    }
    @FXML
    private void openPasien() {
        SceneUtil.openMaximizedWindow("/view/pasien.fxml","Data Pasien");
    }
    @FXML
    private void openDokter() {
        SceneUtil.openMaximizedWindow("/view/dokter.fxml","Data Dokter");
    }
    @FXML
    private void openPetugas() {
    SceneUtil.openMaximizedWindow("/view/petugas.fxml","Data Petugas");
    }
    @FXML
    private void openObat() {
    SceneUtil.openMaximizedWindow("/view/obat.fxml","Data Obat");
    }
    @FXML
    private void openPoli() {
    SceneUtil.openMaximizedWindow("/view/poli.fxml","Data Poli");
    }
    @FXML
    private void openJadwal() {
    SceneUtil.openMaximizedWindow("/view/jadwal.fxml","Data Jadwal Dokter");
    }
    @FXML
    private void openPendaftaran() {
    SceneUtil.openMaximizedWindow("/view/pendaftaran.fxml","Pendaftaran");
    }

    @FXML
    private void openPemeriksaan() {
    SceneUtil.openMaximizedWindow("/view/pemeriksaan.fxml","Pemeriksaan");
    }

    @FXML
    private void openResep() {
    SceneUtil.openMaximizedWindow("/view/resep_obat.fxml","Resep Obat");
    }

    @FXML
    private void openRekam() {
    SceneUtil.openMaximizedWindow("/view/rekam_medis.fxml","Rekam Medis");
    }

    @FXML
    private void openPrediksi() {
    SceneUtil.openMaximizedWindow("/view/prediksi.fxml","Prediksi ML");
    }
}