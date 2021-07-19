package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Area;
import models.Cancha;

import java.net.URL;
import java.util.ResourceBundle;

public class AgregarAreaController {

    @FXML
    private TextField nombreField;

    @FXML
    private TextField dimensionField;

    @FXML
    private TextField bañosField;

    @FXML
    private Button addButton;

    private Area area;

    public void initAttributes(Area a) {
        this.area = a;
    }

    @FXML
    void addButtonClicked(ActionEvent event) {

        int idElementoMax = (int) Main.manager.createQuery("SELECT max(id) FROM Elemento").getResultList().get(0); //Obtengo el idCanchaMaximo para generar el de la proxima cancha.

        // Si la conexion no está activa
        if (!Main.manager.getTransaction().isActive())
            Main.manager.getTransaction().begin(); // La abro

        if(!this.nombreField.equals("") && !this.bañosField.equals("") && !this.dimensionField.equals("")) {
            Area area = new Area(idElementoMax+1,
                    Integer.parseInt(this.dimensionField.getText()),
                    this.area.getDeporte(),
                    Integer.parseInt(this.bañosField.getText()),
                    Main.encargadoLogeado.getDni(),
                    this.nombreField.getText()
                    );

            this.area.setElementos(area);
            Main.manager.persist(area);
            Main.manager.getTransaction().commit();
        }
        else{
            Main m = new Main();
            m.sendAlert(Alert.AlertType.ERROR, "Datos incompletos", "Los datos están incompletos. Inténtelo de nuevo");
        }

        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }
}





