package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SceneUtil {

    // =========================
    // CREATE SCENE + GLOBAL CSS
    // =========================
    private static Scene createScene(Parent root) {

        Scene scene = new Scene(root);

        scene.getStylesheets().add(
                SceneUtil.class
                        .getResource("/style/global.css")
                        .toExternalForm());

        return scene;
    }

    // =========================
    // CREATE MODAL
    // =========================
    public static Stage createModal(
            FXMLLoader loader,
            String title,
            double width,
            double height) throws Exception {

        Parent root = loader.load();

        Stage stage = new Stage();

        stage.setTitle(title);

        Scene scene = new Scene(root, width, height);

        scene.getStylesheets().add(
                SceneUtil.class
                        .getResource("/style/global.css")
                        .toExternalForm());

        stage.setScene(scene);

        stage.initModality(Modality.APPLICATION_MODAL);

        return stage;
    }

    // =========================
    // OPEN MODAL
    // =========================
    public static void openModal(
            String fxml,
            String title) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            SceneUtil.class.getResource(fxml));

            Parent root = loader.load();

            Stage stage = new Stage();

            stage.setTitle(title);

            stage.setScene(createScene(root));

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();

        } catch (Exception e) {

            AlertUtil.error("Gagal membuka form");

            e.printStackTrace();
        }
    }

    // =========================
    // OPEN WINDOW
    // =========================
    public static void openWindow(
            String fxml,
            String title) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            SceneUtil.class.getResource(fxml));

            Parent root = loader.load();

            Stage stage = new Stage();

            stage.setTitle(title);

            stage.setScene(createScene(root));

            stage.show();

        } catch (Exception e) {

            AlertUtil.error("Gagal membuka halaman");

            e.printStackTrace();
        }
    }

    // =========================
    // OPEN MODAL + RETURN LOADER
    // =========================
    public static FXMLLoader openModalWithLoader(
            String fxml,
            String title) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            SceneUtil.class.getResource(fxml));

            Parent root = loader.load();

            Stage stage = new Stage();

            stage.setTitle(title);

            stage.setScene(createScene(root));

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.showAndWait();

            return loader;

        } catch (Exception e) {

            AlertUtil.error("Gagal membuka form");

            e.printStackTrace();

            return null;
        }
    }

    // =========================
    // OPEN MAXIMIZED WINDOW
    // =========================
    public static void openMaximizedWindow(
            String fxml,
            String title) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            SceneUtil.class.getResource(fxml));

            Parent root = loader.load();

            Stage stage = new Stage();

            stage.setTitle(title);

            stage.setScene(createScene(root));

            stage.setMaximized(true);

            stage.show();

        } catch (Exception e) {

            e.printStackTrace();

            AlertUtil.error(
                    e.getClass().getSimpleName()
                            + "\n\n"
                            + e.getMessage());
        }
    }

}