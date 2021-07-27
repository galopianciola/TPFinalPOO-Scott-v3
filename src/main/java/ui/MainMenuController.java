package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    private Button resumenButton;

    @FXML
    private Button areasButton;

    @FXML
    private Button usuariosButton;

    @FXML
    private Button covidButton;

    @FXML
    private Button cerrarButton;


    @FXML
    void areasButtonClicked(ActionEvent event) throws IOException {

        try {
            Main m = new Main();
            m.changeScene("src/main/java/ui/areas.fxml", "Areas");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    void resumenButtonClicked(ActionEvent event) throws IOException {

        try {
            Main m = new Main();
            m.changeScene("src/main/java/ui/resumen.fxml", "Resumen");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    void usuariosButtonClicked(ActionEvent event) throws IOException {
        Main m = new Main();
        if(Main.encargadoLogeado.getDni()==1){
            try {
            m.changeScene("src/main/java/ui/usuarios.fxml", "Usuarios");
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
        else
            m.sendAlert(Alert.AlertType.ERROR,"Acceso denegado,","Solo usuarios root pueden ingresar a esta sección");
    }

    @FXML
    void covidButtonClicked(ActionEvent event) throws IOException {

        try {
            Main m = new Main();
            m.changeScene("src/main/java/ui/covid.fxml", "Reportar caso COVID-19");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    void cerrarClicked(ActionEvent event) throws IOException {
        try {
            Main.encargadoLogeado = null;

            // Cierro la ventana
            Stage stage = (Stage) cerrarButton.getScene().getWindow();
            stage.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}