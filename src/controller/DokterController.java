package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import model.Dokter;
import service.DokterService;
import util.AlertUtil;
import util.SceneUtil;

public class DokterController {

    @FXML
    private TextField txtCari;

    @FXML
    private TableView<Dokter> tableDokter;

    @FXML
    private TableColumn<Dokter,Integer> colId;

    @FXML
    private TableColumn<Dokter,String> colNama;

    @FXML
    private TableColumn<Dokter,String> colSpesialis;

    @FXML
    private TableColumn<Dokter,String> colHP;

    private ObservableList<Dokter> list =
            FXCollections.observableArrayList();

    private DokterService dokterService =
            new DokterService();

    @FXML
    public void initialize() {

        colId.setCellValueFactory(
                new PropertyValueFactory<>("idDokter"));

        colNama.setCellValueFactory(
                new PropertyValueFactory<>("nama"));

        colSpesialis.setCellValueFactory(
                new PropertyValueFactory<>("spesialis"));

        colHP.setCellValueFactory(
                new PropertyValueFactory<>("noHP"));

        loadData();

        tableDokter.setColumnResizePolicy(
        TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
    }

    @FXML
    public void loadData() {

        list.clear();

        list = dokterService.getAll();

        tableDokter.setItems(list);
    }

    @FXML
    public void handleTambah() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/view/form_dokter.fxml"));

            Stage stage =
                    SceneUtil.createModal(
                            loader,
                            "Tambah Dokter",
                            700,
                            500);

            FormDokterController controller =
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

            Dokter dokter =
                    tableDokter
                            .getSelectionModel()
                            .getSelectedItem();

            if (dokter == null) {

                AlertUtil.warning(
                        "Pilih data dokter dahulu");

                return;
            }

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/view/form_dokter.fxml"));

            Stage stage =
                    SceneUtil.createModal(
                            loader,
                            "Edit Dokter",
                            700,
                            500);

            FormDokterController controller =
                    loader.getController();

            controller.setModeEdit(dokter);

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

            Dokter dokter =
                    tableDokter
                            .getSelectionModel()
                            .getSelectedItem();

            if (dokter == null) {

                AlertUtil.warning(
                        "Pilih data dokter dahulu");

                return;
            }

            boolean confirm =
                    AlertUtil.confirm(
                            "Yakin ingin menghapus data?");

            if (!confirm) {
                return;
            }

            dokterService.delete(
                    dokter.getIdDokter());

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
                dokterService.search(
                        txtCari.getText());

        tableDokter.setItems(list);
    }

    @FXML
    public void handleClose() {

        Stage stage =
                (Stage) tableDokter
                        .getScene()
                        .getWindow();

        stage.close();
    }
}