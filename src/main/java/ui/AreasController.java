package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;


public class AreasController {

    @FXML
    private AnchorPane basquetButton;

    @FXML
    private AnchorPane futbolButton;

    @FXML
    private AnchorPane paddleButton;

    @FXML
    private AnchorPane tenisButton;

    @FXML
    private AnchorPane voleyButton;


    @FXML
    void backButtonClicked(ActionEvent event){
        Main m = new Main();
        m.backButtonClicked("src/main/java/ui/main-menu.fxml", "Menu principal");
    }


    @FXML
    void basquetButtonClicked(MouseEvent event) {
        Main m = new Main();

        try {
            if (Main.encargadoLogeado.getArea().getDeporte().equals("Basquet"))
                m.changeScene("src/main/java/ui/basquet.fxml", "Basquet");
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Acceso");
                alert.setContentText("No es posible ingresar al área seleccionada");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
