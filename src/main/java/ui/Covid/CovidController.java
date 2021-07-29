package ui.Covid;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ui.Main;

import java.io.File;
import java.net.URL;

public class CovidController {

    @FXML
    private Button backButton;

    @FXML
    private TextField dniField;

    @FXML
    private Button buscarButton;

    @FXML
    void backButtonClicked(ActionEvent event) {
        Main m = new Main();
        m.backButtonClicked("src/main/java/ui/main-menu.fxml", "Menú principal");
    }

    @FXML
    void buscarButtonClicked(ActionEvent event) {

        try {
            if (!this.dniField.getText().isEmpty()){
                URL url = new File("src/main/java/ui/Covid/turnosAfectados.fxml").toURI().toURL();
                FXMLLoader loader = new FXMLLoader(url);
                Parent root = loader.load();

                //Creo FXMLLoader para poder pasarle el dni del infectado y que busque los afectados
                TurnosAfectadosController controlador = loader.getController();
                controlador.initAttributes(Integer.parseInt(this.dniField.getText()));

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Turnos afectados");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

            } else {
                Main m = new Main();
                m.sendAlert(Alert.AlertType.ERROR, "Error en los datos", "No se ingresó un DNI.");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
