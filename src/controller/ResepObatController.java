package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import model.ResepObat;
import service.ResepObatService;
import util.AlertUtil;
import util.SceneUtil;

public class ResepObatController {

    @FXML
    private TableView<ResepObat> tableResep;

    @FXML
    private TextField txtCari;

    @FXML
    private TableColumn<ResepObat, Integer> colId;

    @FXML
    private TableColumn<ResepObat, String> colPasien;

    @FXML
    private TableColumn<ResepObat, String> colObat;

    @FXML
    private TableColumn<ResepObat, Integer> colJumlah;

    @FXML
    private TableColumn<ResepObat, String> colDosis;

    @FXML
    private TableColumn<ResepObat, String> colKeterangan;

    private ObservableList<ResepObat> list =
            FXCollections.observableArrayList();

    private final ResepObatService service =
            new ResepObatService();

    @FXML
    public void initialize() {

        colId.setCellValueFactory(
                new PropertyValueFactory<>("idResep"));

        colPasien.setCellValueFactory(
                new PropertyValueFactory<>("namaPasien"));

        colObat.setCellValueFactory(
                new PropertyValueFactory<>("namaObat"));

        colJumlah.setCellValueFactory(
                new PropertyValueFactory<>("jumlah"));

        colDosis.setCellValueFactory(
                new PropertyValueFactory<>("dosis"));

        colKeterangan.setCellValueFactory(
                new PropertyValueFactory<>("keterangan"));

        loadData();

        tableResep.setColumnResizePolicy(
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

        tableResep.setItems(list);

    }

    @FXML
    public void handleTambah() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/view/form_resep_obat.fxml"));

            Stage stage =
                    SceneUtil.createModal(
                            loader,
                            "Tambah Resep Obat",
                            950,
                            650);

            FormResepObatController controller =
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

            ResepObat resep =
                    tableResep.getSelectionModel().getSelectedItem();

            if (resep == null) {

                AlertUtil.warning(
                        "Pilih data resep dahulu");

                return;

            }

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/view/form_resep_obat.fxml"));

            Stage stage =
                    SceneUtil.createModal(
                            loader,
                            "Edit Resep Obat",
                            950,
                            650);

            FormResepObatController controller =
                    loader.getController();

            controller.setModeEdit(resep);

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

            ResepObat resep =
                    tableResep.getSelectionModel().getSelectedItem();

            if (resep == null) {

                AlertUtil.warning(
                        "Pilih data dahulu");

                return;

            }

            boolean confirm =
                    AlertUtil.confirm(
                            "Yakin ingin menghapus data?");

            if (!confirm) {

                return;

            }

            service.delete(
                    resep.getIdResep());

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

        // sementara refresh semua data
        // nanti kita tambahkan search di DAO

        loadData();

    }

    @FXML
    public void handleClose() {

        Stage stage =
                (Stage) tableResep
                        .getScene()
                        .getWindow();

        stage.close();

    }

}