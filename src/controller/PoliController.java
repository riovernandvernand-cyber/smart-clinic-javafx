package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import model.Poli;
import service.PoliService;
import util.AlertUtil;
import util.SceneUtil;

public class PoliController {

    @FXML
    private TextField txtCari;

    @FXML
    private TableView<Poli> tablePoli;

    @FXML
    private TableColumn<Poli, Integer> colId;

    @FXML
    private TableColumn<Poli, String> colNama;

    @FXML
    private TableColumn<Poli, String> colKeterangan;

    private ObservableList<Poli> list =
            FXCollections.observableArrayList();

    private PoliService poliService =
            new PoliService();

   @FXML
   public void initialize() {

     colId.setCellValueFactory(
            new PropertyValueFactory<>("idPoli"));

     colNama.setCellValueFactory(
            new PropertyValueFactory<>("namaPoli"));

     colKeterangan.setCellValueFactory(
            new PropertyValueFactory<>("keterangan"));

     loadData();

     tablePoli.setColumnResizePolicy(
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

        list = poliService.getAll();

        tablePoli.setItems(list);
     }

    @FXML
    public void handleTambah() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/view/form_poli.fxml"));

            Stage stage =
                    SceneUtil.createModal(
                            loader,
                            "Tambah Poli",
                            700,
                            500);

            FormPoliController controller =
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

            Poli poli =
                    tablePoli
                            .getSelectionModel()
                            .getSelectedItem();

            if (poli == null) {

                AlertUtil.warning(
                        "Pilih data poli dahulu");

                return;
            }

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/view/form_poli.fxml"));

            Stage stage =
                    SceneUtil.createModal(
                            loader,
                            "Edit Poli",
                            700,
                            500);

            FormPoliController controller =
                    loader.getController();

            controller.setModeEdit(poli);

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

            Poli poli =
                    tablePoli
                            .getSelectionModel()
                            .getSelectedItem();

            if (poli == null) {

                AlertUtil.warning(
                        "Pilih data poli dahulu");

                return;
            }

            boolean confirm =
                    AlertUtil.confirm(
                            "Yakin ingin menghapus data?");

            if (!confirm) {

                return;
            }

            poliService.delete(
                    poli.getIdPoli());

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
        list =
                poliService.search(
                        txtCari.getText());

        tablePoli.setItems(list);
     }

    @FXML
    public void handleClose() {
        Stage stage =
                (Stage) tablePoli
                        .getScene()
                        .getWindow();

        stage.close();
     }
}