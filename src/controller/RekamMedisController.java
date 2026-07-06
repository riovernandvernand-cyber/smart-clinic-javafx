package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import model.RekamMedis;
import service.RekamMedisService;
import util.AlertUtil;
import util.SceneUtil;

public class RekamMedisController {

    @FXML
    private TextField txtCari;

    @FXML
    private TableView<RekamMedis> tableRekamMedis;

    @FXML
    private TableColumn<RekamMedis,Integer> colId;

    @FXML
    private TableColumn<RekamMedis,String> colPasien;

    @FXML
    private TableColumn<RekamMedis,java.util.Date> colTanggal;

    @FXML
    private TableColumn<RekamMedis,String> colDiagnosa;

    @FXML
    private TableColumn<RekamMedis,String> colRingkasan;

    private ObservableList<RekamMedis> list =
            FXCollections.observableArrayList();

    private final RekamMedisService service =
            new RekamMedisService();

    @FXML
    public void initialize() {

        colId.setCellValueFactory(
                new PropertyValueFactory<>("idRekam"));

        colPasien.setCellValueFactory(
                new PropertyValueFactory<>("namaPasien"));

        colTanggal.setCellValueFactory(
                new PropertyValueFactory<>("tanggal"));

        colDiagnosa.setCellValueFactory(
                new PropertyValueFactory<>("diagnosa"));

        colRingkasan.setCellValueFactory(
                new PropertyValueFactory<>("ringkasan"));

        loadData();

        tableRekamMedis.setColumnResizePolicy(
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

        tableRekamMedis.setItems(list);

    }

    @FXML
    public void handleTambah() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/view/form_rekam_medis.fxml"));

            Stage stage =
                    SceneUtil.createModal(
                            loader,
                            "Tambah Rekam Medis",
                            900,
                            650);

            FormRekamMedisController controller =
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

            RekamMedis rm =
                    tableRekamMedis
                            .getSelectionModel()
                            .getSelectedItem();

            if (rm == null) {

                AlertUtil.warning(
                        "Pilih data rekam medis dahulu.");

                return;

            }

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/view/form_rekam_medis.fxml"));

            Stage stage =
                    SceneUtil.createModal(
                            loader,
                            "Edit Rekam Medis",
                            900,
                            650);

            FormRekamMedisController controller =
                    loader.getController();

            controller.setModeEdit(rm);

            stage.showAndWait();

            loadData();

        } catch (Exception e) {

            AlertUtil.error(
                    "Gagal membuka form edit.");

            e.printStackTrace();

        }

    }

    @FXML
    public void handleHapus() {

        try {

            RekamMedis rm =
                    tableRekamMedis
                            .getSelectionModel()
                            .getSelectedItem();

            if (rm == null) {

                AlertUtil.warning(
                        "Pilih data terlebih dahulu.");

                return;

            }

            boolean confirm =
                    AlertUtil.confirm(
                            "Yakin ingin menghapus data?");

            if (!confirm) {

                return;

            }

            service.delete(
                    rm.getIdRekam());

            AlertUtil.success(
                    "Data berhasil dihapus.");

            loadData();

        } catch (Exception e) {

            AlertUtil.error(
                    e.getMessage());

            e.printStackTrace();

        }

    }

    @FXML
    public void handleCari() {

        String keyword =
                txtCari.getText().trim();

        if (keyword.isEmpty()) {

            loadData();

            return;

        }

        list =
                service.search(keyword);

        tableRekamMedis.setItems(list);

    }

    @FXML
    public void handleClose() {

        Stage stage =
                (Stage) tableRekamMedis
                        .getScene()
                        .getWindow();

        stage.close();

    }

}