package ui.Areas;

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
import ui.Main;

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
    private TextField gastoMensualField;

    @FXML
    private Button addButton;

    @FXML
    private CheckBox terminadaCheck;

    private Area area;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.terminadaCheck.setSelected(false);
    }

    @FXML
    void addButtonClicked(ActionEvent event) {

        int idElementoMax = (int) Main.manager.createQuery("SELECT max(id) FROM Elemento").getResultList().get(0); //Obtengo el idCanchaMaximo para generar el de la proxima cancha.
        // Si la conexion no está activa
        if (!Main.manager.getTransaction().isActive())
            Main.manager.getTransaction().begin(); // La abro

        if(!this.capacidadField.getText().equals("") && !this.gastoMensualField.getText().equals("") && !this.dimensionField.getText().equals("") && !this.precioTurnoField.getText().equals("")) {
            System.out.println("ENTRE");
            boolean mantenimiento = true;
            if(this.terminadaCheck.isSelected())
                mantenimiento = false;
            Cancha cancha = new Cancha(
                    idElementoMax + 1,
                    Integer.parseInt(this.dimensionField.getText()),
                    Main.encargadoLogeado.getDeporte(),
                    Integer.parseInt(this.capacidadField.getText()),
                    false,
                    mantenimiento,
                    Integer.parseInt(this.gastoMensualField.getText())
            );
            this.area.setElementos(cancha);
            Main.manager.persist(cancha);
            Main.manager.getTransaction().commit();
        }
        else{
            Main m = new Main();
            m.sendAlert(Alert.AlertType.ERROR, "Datos incompletos", "Los datos están incompletos. Inténtelo de nuevo");
        }

        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }


    public void initAttributes(Area a) {
        this.area=a;
    }
}
