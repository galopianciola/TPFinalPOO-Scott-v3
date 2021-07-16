package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AgregarCanchaController implements Initializable {

    @FXML
    private TextField capacidadField;

    @FXML
    private TextField dimensionField;

    @FXML
    private TextField precioTurnoField;

    @FXML
    private Button addButton;

    @FXML
    private CheckBox terminadaCheck;

    @FXML
    void addButtonClicked(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("xd");
    }
}
