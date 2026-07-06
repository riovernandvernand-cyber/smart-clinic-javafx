package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import model.Poli;
import service.PoliService;
import util.AlertUtil;
import util.ValidationUtil;

public class FormPoliController {

    @FXML
    private TextField txtId,
            txtNamaPoli;

    @FXML
    private TextArea txtKeterangan;

    private PoliService poliService =
            new PoliService();

    private boolean modeEdit = false;

    public void setModeTambah() {

        modeEdit = false;

        txtId.clear();
        txtNamaPoli.clear();
        txtKeterangan.clear();

        txtId.setEditable(false);
    }

    public void setModeEdit(Poli p) {

        modeEdit = true;

        txtId.setText(
                String.valueOf(
                        p.getIdPoli()));

        txtId.setEditable(false);

        txtNamaPoli.setText(
                p.getNamaPoli());

        txtKeterangan.setText(
                p.getKeterangan());
    }

    @FXML
    public void handleSimpan() {

        try {

            if (ValidationUtil.isEmpty(
                    txtNamaPoli,
                    "Nama poli wajib diisi")) {

                return;
            }

            Poli poli =
                    new Poli(
                            modeEdit
                                    ? Integer.parseInt(
                                    txtId.getText())
                                    : 0,

                            txtNamaPoli.getText(),

                            txtKeterangan.getText()
                    );

            poliService.simpan(
                    poli,
                    modeEdit);

            AlertUtil.success(
                    modeEdit
                            ? "Data berhasil diupdate"
                            : "Data berhasil disimpan");

            closeWindow();

        } catch (Exception e) {

            AlertUtil.error(
                    e.getMessage());

            e.printStackTrace();
        }
    }

    @FXML
    public void handleBatal() {

        closeWindow();
    }

    private void closeWindow() {

        Stage stage =
                (Stage) txtNamaPoli
                        .getScene()
                        .getWindow();

        stage.close();
    }
}