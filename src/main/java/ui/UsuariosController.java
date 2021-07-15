package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class UsuariosController implements Initializable {

    @FXML
    private TableView tablaUsuarios;

    @FXML
    private TableColumn colDNI;

    @FXML
    private TableColumn colArea;

    @FXML
    private TableColumn colSueldo;

    @FXML
    private Button agregarUsuarioButton;

    @FXML
    private Button backButton;

    @FXML
    private Button borrarUsuarioTurnoButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void agregarUsuarioButtonClicked(ActionEvent event) {

    }

    @FXML
    void backButtonClicked(ActionEvent event) {

    }

    @FXML
    void borrarUsuarioButtonClicked(ActionEvent event) {

    }

}
