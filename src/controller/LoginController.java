package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.SceneUtil;

public class LoginController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private void handleLogin() {

        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();

        if (username.equals("admin") && password.equals("admin123")) {

            SceneUtil.openMaximizedWindow(
                    "/view/dashboard.fxml",
                    "Smart Clinic");

            txtUsername.getScene().getWindow().hide();

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Gagal");
            alert.setHeaderText(null);
            alert.setContentText("Username atau Password salah!");
            alert.showAndWait();

        }

    }

}