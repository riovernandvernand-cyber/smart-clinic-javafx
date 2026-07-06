package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import model.Petugas;
import service.PetugasService;
import util.AlertUtil;
import util.ValidationUtil;

public class FormPetugasController {

    @FXML
    private TextField txtId,
            txtNama,
            txtJabatan,
            txtHP;

    private PetugasService petugasService =
            new PetugasService();

    private boolean modeEdit = false;

    // MODE TAMBAH
    public void setModeTambah() {

        modeEdit = false;
    }

    // MODE EDIT
    public void setModeEdit(Petugas p) {

        modeEdit = true;

        txtId.setText(
                String.valueOf(
                        p.getIdPetugas()));

        txtNama.setText(
                p.getNama());

        txtJabatan.setText(
                p.getJabatan());

        txtHP.setText(
                p.getNoHP());
    }

    @FXML
    public void handleSimpan() {

        try {

            if (ValidationUtil.isEmpty(
                    txtNama,
                    "Nama petugas wajib diisi")) {

                return;
            }

            Petugas p = new Petugas(
                    modeEdit
                            ? Integer.parseInt(
                            txtId.getText())
                            : 0,

                    txtNama.getText(),
                    txtJabatan.getText(),
                    txtHP.getText()
            );

            petugasService.simpan(
                    p,
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