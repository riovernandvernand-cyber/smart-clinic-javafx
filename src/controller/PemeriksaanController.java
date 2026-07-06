package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import model.Pemeriksaan;
import service.PemeriksaanService;
import util.AlertUtil;
import util.SceneUtil;

public class PemeriksaanController {

    @FXML
    private TableView<Pemeriksaan> tablePemeriksaan;

    @FXML
    private TextField txtCari;

    @FXML
    private TableColumn<Pemeriksaan, Integer> colId;

    @FXML
    private TableColumn<Pemeriksaan, String> colPasien;

    @FXML
    private TableColumn<Pemeriksaan, java.util.Date> colTanggal;

    @FXML
    private TableColumn<Pemeriksaan, String> colDiagnosa;

    @FXML
    private TableColumn<Pemeriksaan, String> colPrediksi;

    @FXML
    private TableColumn<Pemeriksaan, String> colRisiko;

    @FXML
    private TableColumn<Pemeriksaan, String> colCatatan;

    @FXML
    private TableColumn<Pemeriksaan, Double> colGula;

    private ObservableList<Pemeriksaan> list =
            FXCollections.observableArrayList();

    private final PemeriksaanService service =
            new PemeriksaanService();

    @FXML
    public void initialize() {

        colId.setCellValueFactory(
                new PropertyValueFactory<>("idPeriksa"));

        colPasien.setCellValueFactory(
                new PropertyValueFactory<>("namaPasien"));

        colTanggal.setCellValueFactory(
                new PropertyValueFactory<>("tanggalPeriksa"));

        colDiagnosa.setCellValueFactory(
                new PropertyValueFactory<>("diagnosa"));

        colGula.setCellValueFactory(
                new PropertyValueFactory<>("gulaDarah"));

        colPrediksi.setCellValueFactory(
                new PropertyValueFactory<>("hasilPrediksi"));

        colRisiko.setCellValueFactory(
                new PropertyValueFactory<>("tingkatResiko"));

        colCatatan.setCellValueFactory(
                new PropertyValueFactory<>("catatan"));

        colGula.setCellValueFactory(
                new PropertyValueFactory<>("gulaDarah"));

        loadData();

        tablePemeriksaan.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        txtCari.textProperty().addListener((obs, oldValue, newValue) -> {

            if (newValue.trim().isEmpty()) {

                loadData();

            }

        });

    }

    @FXML
    public void loadData() {

        list.clear();

        list = service.getAll();

        tablePemeriksaan.setItems(list);

    }

    @FXML
    public void handleTambah() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/view/form_pemeriksaan.fxml"));

            Stage stage =
                    SceneUtil.createModal(
                            loader,
                            "Tambah Pemeriksaan",
                            1050,
                            720);

            FormPemeriksaanController controller =
                    loader.getController();

            controller.setModeTambah();

            stage.showAndWait();

            loadData();

        } catch (Exception e) {

            AlertUtil.error(
                    "Gagal membuka form pemeriksaan");

            e.printStackTrace();

        }

    }

    @FXML
    public void handleEdit() {

        try {

            Pemeriksaan p =
                    tablePemeriksaan
                            .getSelectionModel()
                            .getSelectedItem();

            if (p == null) {

                AlertUtil.warning(
                        "Pilih data yang akan diedit!");

                return;

            }

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/view/form_pemeriksaan.fxml"));

            Stage stage =
                    SceneUtil.createModal(
                            loader,
                            "Edit Pemeriksaan",
                            1050,
                            720);

            FormPemeriksaanController controller =
                    loader.getController();

            controller.setModeEdit(p);

            stage.showAndWait();

            loadData();

        } catch (Exception e) {

            AlertUtil.error(
                    "Gagal membuka form edit");

            e.printStackTrace();

        }

    }

    @FXML
    public void handleHapus() {

        try {

            Pemeriksaan p =
                    tablePemeriksaan
                            .getSelectionModel()
                            .getSelectedItem();

            if (p == null) {

                AlertUtil.warning(
                        "Pilih data dahulu!");

                return;

            }

            boolean confirm =
                    AlertUtil.confirm(
                            "Yakin ingin menghapus data?");

            if (!confirm) {

                return;

            }

            service.delete(
                    p.getIdPeriksa());

            AlertUtil.success(
                    "Data berhasil dihapus");

            loadData();

        } catch (Exception e) {

            AlertUtil.error(
                    e.getMessage());

            e.printStackTrace();

        }

    }

    @FXML
    public void handleCari() {

    String keyword = txtCari.getText().trim();

    if (keyword.isEmpty()) {

        loadData();
        return;

    }

    list = service.search(keyword);

    tablePemeriksaan.setItems(list);

}
    @FXML
    public void handleClose() {

        Stage stage =
                (Stage) tablePemeriksaan
                        .getScene()
                        .getWindow();

        stage.close();

    }

}