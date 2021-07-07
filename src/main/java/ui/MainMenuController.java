package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}