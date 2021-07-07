package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    void basquetButtonClicked(MouseEvent event) {
        Main m = new Main();

        try {
            m.changeScene("src/main/java/ui/basquet.fxml", "Basquet");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
