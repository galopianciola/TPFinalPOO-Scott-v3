package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainMenuController {
    @FXML
    private Button cerrarButton;

    public void userLogOut(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("ui/login.fxml", "Scott Login");
    }
}
