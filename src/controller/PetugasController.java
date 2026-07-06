package controller;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import model.Petugas;
import service.PetugasService;
import util.AlertUtil;
import util.SceneUtil;

public class PetugasController {

    @FXML
    private TextField txtCari;

    @FXML
    private TableView<Petugas> tablePetugas;

    @FXML
    private TableColumn<Petugas,Integer> colId;

    @FXML
    private TableColumn<Petugas,String> colNama;

    @FXML
    private TableColumn<Petugas,String> colJabatan;

    @FXML
    private TableColumn<Petugas,String> colHP;

    private ObservableList<Petugas> list =
            FXCollections.observableArrayList();

    private PetugasService petugasService =
            new PetugasService();

    @FXML
    public void initialize(){

        colId.setCellValueFactory(
                new PropertyValueFactory<>("idPetugas"));

        colNama.setCellValueFactory(
                new PropertyValueFactory<>("nama"));

        colJabatan.setCellValueFactory(
                new PropertyValueFactory<>("jabatan"));

        colHP.setCellValueFactory(
                new PropertyValueFactory<>("noHP"));

        loadData();
        tablePetugas.setColumnResizePolicy(
        TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    public void loadData(){

        list.clear();

        list = petugasService.getAll();

        tablePetugas.setItems(list);
    }

    @FXML
    public void handleTambah() {

    try {

        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource(
                                "/view/form_petugas.fxml"));

        Stage stage =
                SceneUtil.createModal(
                        loader,
                        "Tambah Petugas",
                        800,
                        500);

        FormPetugasController controller =
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

        Petugas petugas =
                tablePetugas
                        .getSelectionModel()
                        .getSelectedItem();

        if (petugas == null) {

            AlertUtil.warning(
                    "Pilih data petugas dahulu");

            return;
        }

        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource(
                                "/view/form_petugas.fxml"));

        Stage stage =
                SceneUtil.createModal(
                        loader,
                        "Edit Petugas",
                        800,
                        500);

        FormPetugasController controller =
                loader.getController();

        controller.setModeEdit(
                petugas);

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

            Petugas petugas =
                    tablePetugas
                            .getSelectionModel()
                            .getSelectedItem();

            if(petugas == null){

                AlertUtil.warning(
                        "Pilih data petugas dahulu!");

                return;
            }

            boolean confirm =
                    AlertUtil.confirm(
                            "Yakin ingin menghapus data?");

            if(!confirm){
                return;
            }

            petugasService.delete(
                    petugas.getIdPetugas());

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

        list.clear();

        list =
                petugasService.search(
                        txtCari.getText());

        tablePetugas.setItems(list);
    }

    @FXML
    public void handleClose(){

        Stage stage =
                (Stage) tablePetugas
                        .getScene()
                        .getWindow();

        stage.close();
    }
}