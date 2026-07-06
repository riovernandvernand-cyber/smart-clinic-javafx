package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import model.Obat;
import service.ObatService;
import util.AlertUtil;
import util.ValidationUtil;

public class FormObatController {

    @FXML
    private TextField txtId,
            txtNama,
            txtStok,
            txtHarga;

    private ObatService obatService =
            new ObatService();

    private boolean modeEdit = false;

    public void setModeTambah() {

        modeEdit = false;
    }

    public void setModeEdit(Obat o) {

        modeEdit = true;

        txtId.setText(
                String.valueOf(
                        o.getIdObat()));

        txtNama.setText(
                o.getNamaObat());

        txtStok.setText(
                String.valueOf(
                        o.getStok()));

        txtHarga.setText(
                String.valueOf(
                        o.getHarga()));
    }

    @FXML
    public void handleSimpan() {

        try {

            if (ValidationUtil.isEmpty(
                    txtNama,
                    "Nama obat wajib diisi")) {

                return;
            }

            Obat o = new Obat(
                    modeEdit
                            ? Integer.parseInt(
                                    txtId.getText())
                            : 0,

                    txtNama.getText(),

                    Integer.parseInt(
                            txtStok.getText()),

                    Double.parseDouble(
                            txtHarga.getText())
            );

            obatService.simpan(
                    o,
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
                (Stage) txtNama
                        .getScene()
                        .getWindow();

        stage.close();
    }
}