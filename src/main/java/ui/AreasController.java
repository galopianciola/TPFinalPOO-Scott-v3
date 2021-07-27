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
    void backButtonClicked(ActionEvent event) {
        Main m = new Main();
        m.backButtonClicked("src/main/java/ui/main-menu.fxml", "Menú principal");
    }


    @FXML
    void basquetButtonClicked(MouseEvent event) {
        Main m = new Main();
        if(Main.encargadoLogeado.getDni()==1)
            m.sendAlert(Alert.AlertType.ERROR,"Acceso denegado,","No es posible ingresar al área seleccionada ya que no tiene un deporte asignado");
        else {

            try {
                if (Main.encargadoLogeado.getArea().getDeporte().equals("Basquet"))
                    m.changeScene("src/main/java/ui/deporte.fxml", "Basquet");
                else
                    m.sendAlert(Alert.AlertType.ERROR, "Acceso denegado,", "No es posible ingresar al área seleccionada");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void futbolButtonClicked(MouseEvent event) {
        Main m = new Main();
        if(Main.encargadoLogeado.getDni()==1)
            m.sendAlert(Alert.AlertType.ERROR,"Acceso denegado,","No es posible ingresar al área seleccionada ya que no tiene un deporte asignado");
        else {
            try {
                if (Main.encargadoLogeado.getArea().getDeporte().equals("Futbol"))
                    m.changeScene("src/main/java/ui/deporte.fxml", "Futbol");
                else
                    m.sendAlert(Alert.AlertType.ERROR, "Acceso denegado,", "No es posible ingresar al área seleccionada");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void paddleButtonClicked(MouseEvent event) {
        Main m = new Main();
        if(Main.encargadoLogeado.getDni()==1)
            m.sendAlert(Alert.AlertType.ERROR,"Acceso denegado,","No es posible ingresar al área seleccionada ya que no tiene un deporte asignado");
        else {
            try {
                if (Main.encargadoLogeado.getArea().getDeporte().equals("Paddle"))
                    m.changeScene("src/main/java/ui/deporte.fxml", "Paddle");
                else
                    m.sendAlert(Alert.AlertType.ERROR, "Acceso denegado,", "No es posible ingresar al área seleccionada");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void tenisButtonClicked(MouseEvent event) {
        Main m = new Main();
        if(Main.encargadoLogeado.getDni()==1)
            m.sendAlert(Alert.AlertType.ERROR,"Acceso denegado,","No es posible ingresar al área seleccionada ya que no tiene un deporte asignado");
        else {
            try {
                if (Main.encargadoLogeado.getArea().getDeporte().equals("Tenis"))
                    m.changeScene("src/main/java/ui/deporte.fxml", "Tenis");
                else
                    m.sendAlert(Alert.AlertType.ERROR, "Acceso denegado,", "No es posible ingresar al área seleccionada");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void voleyButtonClicked(MouseEvent event) {
        Main m = new Main();
        if(Main.encargadoLogeado.getDni()==1)
            m.sendAlert(Alert.AlertType.ERROR,"Acceso denegado,","No es posible ingresar al área seleccionada ya que no tiene un deporte asignado");
        else {
            try {
                if (Main.encargadoLogeado.getArea().getDeporte().equals("Voley"))
                    m.changeScene("src/main/java/ui/deporte.fxml", "Voley");
                else
                    m.sendAlert(Alert.AlertType.ERROR, "Acceso denegado,", "No es posible ingresar al área seleccionada");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}