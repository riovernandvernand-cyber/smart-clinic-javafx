package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import model.Jadwal;
import service.JadwalService;
import util.AlertUtil;
import util.SceneUtil;

public class JadwalController {

    @FXML
    private TextField txtCari;

    @FXML
    private TableView<Jadwal> tableJadwal;

    @FXML
    private TableColumn<Jadwal,Integer> colId;

    @FXML
    private TableColumn<Jadwal,String> colDokter;

    @FXML
    private TableColumn<Jadwal,String> colHari;

    @FXML
    private TableColumn<Jadwal,String> colJamMulai;

    @FXML
    private TableColumn<Jadwal,String> colJamSelesai;

    private ObservableList<Jadwal> list =
            FXCollections.observableArrayList();

    private JadwalService jadwalService =
            new JadwalService();

    @FXML
    public void initialize() {

        colId.setCellValueFactory(
                new PropertyValueFactory<>("idJadwal"));

        // mengambil nama dokter dari object Dokter
        colDokter.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue()
                                .getDokter()
                                .getNama()
                ));

        colHari.setCellValueFactory(
                new PropertyValueFactory<>("hari"));

        colJamMulai.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue()
                                .getJamMulai()
                                .toString()
                ));

        colJamSelesai.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue()
                                .getJamSelesai()
                                .toString()
                ));

        loadData();

        tableJadwal.setColumnResizePolicy(
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

        list = jadwalService.getAll();

        tableJadwal.setItems(list);

    }

    @FXML
    public void handleTambah() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/view/form_jadwal.fxml"));

            Stage stage =
                    SceneUtil.createModal(
                            loader,
                            "Tambah Jadwal",
                            750,
                            550);

            FormJadwalController controller =
                    loader.getController();

            controller.setModeTambah();

            stage.showAndWait();

            loadData();

        } catch (Exception e) {

            AlertUtil.error(
                    "Gagal membuka form");

            e.printStackTrace();

        }

    }

    @FXML
    public void handleEdit() {

        try {

            Jadwal jadwal =
                    tableJadwal
                            .getSelectionModel()
                            .getSelectedItem();

            if (jadwal == null) {

                AlertUtil.warning(
                        "Pilih data jadwal dahulu");

                return;

            }

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/view/form_jadwal.fxml"));

            Stage stage =
                    SceneUtil.createModal(
                            loader,
                            "Edit Jadwal",
                            750,
                            550);

            FormJadwalController controller =
                    loader.getController();

            controller.setModeEdit(jadwal);

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

            Jadwal jadwal =
                    tableJadwal
                            .getSelectionModel()
                            .getSelectedItem();

            if (jadwal == null) {

                AlertUtil.warning(
                        "Pilih data jadwal dahulu");

                return;

            }

            boolean confirm =
                    AlertUtil.confirm(
                            "Yakin ingin menghapus data?");

            if (!confirm) {

                return;

            }

            jadwalService.delete(
                    jadwal.getIdJadwal());

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

        list.clear();

        list =
                jadwalService.search(
                        txtCari.getText());

        tableJadwal.setItems(list);

    }

    @FXML
    public void handleClose() {

        Stage stage =
                (Stage) tableJadwal
                        .getScene()
                        .getWindow();

        stage.close();

    }

}