package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ResumenController {

    @FXML
    private Label disponibilidadLabel;

    @FXML
    public void backButtonClicked(ActionEvent event){
        Main m = new Main();
        m.backButtonClicked("src/main/java/ui/main-menu.fxml", "Menú principal");
    }

}
