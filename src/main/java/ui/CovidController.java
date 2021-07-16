package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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

    }

}
