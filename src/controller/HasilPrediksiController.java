package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import model.PrediksiML;
import service.PrediksiMLService;

public class HasilPrediksiController {

    @FXML
    private TextField txtCari;

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
    private TableColumn<PrediksiML, Double> colProbabilitas;

    private final PrediksiMLService service =
            new PrediksiMLService();

    private ObservableList<PrediksiML> masterData =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

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

        colProbabilitas.setCellValueFactory(
                new PropertyValueFactory<>("probabilitas"));

        // Probabilitas menjadi persen
        colProbabilitas.setCellFactory(column ->
                new TableCell<>() {

                    @Override
                    protected void updateItem(Double item, boolean empty) {

                        super.updateItem(item, empty);

                        if (empty || item == null) {

                            setText(null);

                        } else {

                            setText((int)(item * 100) + "%");

                        }

                    }

                });

        loadData();

        txtCari.textProperty().addListener((obs, oldValue, newValue) -> cariData());

    }

    private void loadData() {

        masterData.clear();

        masterData.addAll(service.getAll());

        tablePrediksi.setItems(masterData);

    }

    private void cariData() {

        String keyword = txtCari.getText().toLowerCase();

        ObservableList<PrediksiML> hasil =
                FXCollections.observableArrayList();

        for (PrediksiML p : masterData) {

            if (String.valueOf(p.getGlucose()).contains(keyword)
                    || String.valueOf(p.getAge()).contains(keyword)
                    || p.getHasilPrediksi().toLowerCase().contains(keyword)) {

                hasil.add(p);

            }

        }

        tablePrediksi.setItems(hasil);

    }

    @FXML
    private void handleRefresh() {

        txtCari.clear();

        loadData();

    }

    @FXML
    private void handleClose() {

        Stage stage =
                (Stage) tablePrediksi.getScene().getWindow();

        stage.close();

    }

}