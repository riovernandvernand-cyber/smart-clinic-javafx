package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import model.PrediksiML;
import service.LaporanService;

public class LaporanController {

    @FXML
    private Label lblPasien;

    @FXML
    private Label lblDokter;

    @FXML
    private Label lblPeriksa;

    @FXML
    private Label lblPrediksi;

    @FXML
    private TableView<PrediksiML> tablePrediksi;

    @FXML
    private TableColumn<PrediksiML, Integer> colId;

    @FXML
    private TableColumn<PrediksiML, Integer> colGlucose;

    @FXML
    private TableColumn<PrediksiML, Double> colBMI;

    @FXML
    private TableColumn<PrediksiML, Integer> colAge;

    @FXML
    private TableColumn<PrediksiML, String> colHasil;

    @FXML
    private TableColumn<PrediksiML, Double> colProb;

    private final LaporanService service =
            new LaporanService();

    @FXML
    public void initialize() {

        // Statistik
        lblPasien.setText(String.valueOf(service.totalPasien()));
        lblDokter.setText(String.valueOf(service.totalDokter()));
        lblPeriksa.setText(String.valueOf(service.totalPemeriksaan()));
        lblPrediksi.setText(String.valueOf(service.totalPrediksi()));

        // Table
        colId.setCellValueFactory(
                new PropertyValueFactory<>("idPrediksi"));

        colGlucose.setCellValueFactory(
                new PropertyValueFactory<>("glucose"));

        colBMI.setCellValueFactory(
                new PropertyValueFactory<>("bmi"));

        colAge.setCellValueFactory(
                new PropertyValueFactory<>("age"));

        colHasil.setCellValueFactory(
                new PropertyValueFactory<>("hasilPrediksi"));

        colProb.setCellValueFactory(
        new PropertyValueFactory<>("probabilitas"));

colProb.setCellFactory(column -> new javafx.scene.control.TableCell<PrediksiML, Double>() {
    @Override
    protected void updateItem(Double item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
        } else {
            setText((int) (item * 100) + "%");
        }
    }
});

        loadData();
    }

    private void loadData() {

        tablePrediksi.setItems(
                FXCollections.observableArrayList(
                        service.getRiwayatPrediksi()
                )
        );
    }

    @FXML
    private void handleRefresh() {

        lblPasien.setText(String.valueOf(service.totalPasien()));
        lblDokter.setText(String.valueOf(service.totalDokter()));
        lblPeriksa.setText(String.valueOf(service.totalPemeriksaan()));
        lblPrediksi.setText(String.valueOf(service.totalPrediksi()));

        loadData();
    }

    @FXML
    private void handleClose() {

        Stage stage =
                (Stage) tablePrediksi
                        .getScene()
                        .getWindow();

        stage.close();
    }

}