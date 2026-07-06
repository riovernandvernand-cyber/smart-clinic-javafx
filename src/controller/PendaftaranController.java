package controller;
import javafx.fxml.FXMLLoader;
import util.SceneUtil;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import model.Pendaftaran;
import service.PendaftaranService;
import util.AlertUtil;

public class PendaftaranController {

    @FXML
    private TextField txtCari;

    @FXML
    private TableView<Pendaftaran> tablePendaftaran;

    @FXML
    private TableColumn<Pendaftaran,Integer> colId;

    @FXML
    private TableColumn<Pendaftaran,java.util.Date> colTanggal;

    @FXML
    private TableColumn<Pendaftaran,String> colPasien;

    @FXML
    private TableColumn<Pendaftaran,String> colDokter;

    @FXML
    private TableColumn<Pendaftaran,String> colKeluhan;

    private ObservableList<Pendaftaran> list =
            FXCollections.observableArrayList();

    private PendaftaranService pendaftaranService =
            new PendaftaranService();

    @FXML
    public void initialize(){

        colId.setCellValueFactory(
                new PropertyValueFactory<>("idDaftar"));

        colTanggal.setCellValueFactory(
                new PropertyValueFactory<>("tanggal"));

        colPasien.setCellValueFactory(
                new PropertyValueFactory<>("namaPasien"));

        colDokter.setCellValueFactory(
                new PropertyValueFactory<>("namaDokter"));

        colKeluhan.setCellValueFactory(
                new PropertyValueFactory<>("keluhan"));

        loadData();

        tablePendaftaran.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    public void loadData(){

        list.clear();

        list = pendaftaranService.getAll();

        tablePendaftaran.setItems(list);
    }

    @FXML
    public void handleTambah() {

    try {

        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource(
                                "/view/form_pendaftaran.fxml"));

        Stage stage =
                SceneUtil.createModal(
                        loader,
                        "Tambah Pendaftaran",
                        700,
                        500);

        FormPendaftaranController controller =
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

        Pendaftaran p =
                tablePendaftaran
                        .getSelectionModel()
                        .getSelectedItem();

        if (p == null) {

            AlertUtil.warning(
                    "Pilih data dahulu!");

            return;
        }

        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource(
                                "/view/form_pendaftaran.fxml"));

        Stage stage =
                SceneUtil.createModal(
                        loader,
                        "Edit Pendaftaran",
                        700,
                        500);

        FormPendaftaranController controller =
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
    public void handleHapus(){

        try{

            Pendaftaran p =
                    tablePendaftaran
                            .getSelectionModel()
                            .getSelectedItem();

            if(p == null){

                AlertUtil.warning(
                        "Pilih data dahulu!");

                return;
            }

            boolean confirm =
                    AlertUtil.confirm(
                            "Yakin ingin menghapus data?");

            if(!confirm){
                return;
            }

            pendaftaranService.delete(
                    p.getIdDaftar());

            AlertUtil.success(
                    "Data berhasil dihapus");

            loadData();

        }catch(Exception e){

            AlertUtil.error(
                    e.getMessage());

            e.printStackTrace();
        }
    }

    @FXML
    public void handleCari(){

        loadData();
    }

    @FXML
    public void handleClose(){

        Stage stage =
                (Stage) tablePendaftaran
                        .getScene()
                        .getWindow();

        stage.close();
    }
}