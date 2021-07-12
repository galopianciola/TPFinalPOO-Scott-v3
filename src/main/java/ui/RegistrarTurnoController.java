package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RegistrarTurnoController {

    @FXML
    private Button registrarTurnoButton;

    @FXML
    private TableView<?> tablaJugadores;

    @FXML
    private Button agregarJugadorButton;

    @FXML
    private CheckBox pagadoCheck;

    @FXML
    void agregarJugadorClicked(ActionEvent event) {
        Main m = new Main();
        try {
            m.changeSceneOnParent("src/main/java/ui/agregarJugador.fxml", "Agregar persona");
        } catch (Exception e){
           e.printStackTrace();
        }

    }

    @FXML
    void registrarTurnoButtonClicked(ActionEvent event) {



    }

}
