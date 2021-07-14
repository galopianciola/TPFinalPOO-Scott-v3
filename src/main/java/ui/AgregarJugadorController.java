package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Encargado;
import models.Persona;
import models.Turno;

import java.net.URL;
import java.util.ResourceBundle;

public class AgregarJugadorController implements Initializable {

    @FXML
    private TextField nombreField;

    @FXML
    private TextField apellidoField;

    @FXML
    private TextField dniField;

    @FXML
    private TextField telefonoField;

    @FXML
    private Button addButton;

    private Turno turno;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.dniField.setText("");
        this.nombreField.setText("");
        this.apellidoField.setText("");
        this.telefonoField.setText("");
    }

    @FXML
    void addButtonClicked(ActionEvent event) {

        // Si la conexion no está activa
        if (!Main.manager.getTransaction().isActive())
            Main.manager.getTransaction().begin(); // La abro


        // Si ningun dato esta incompleto
        if ((!nombreField.getText().equals("")) && (!apellidoField.getText().equals("")) && (!dniField.getText().equals("")) && (!telefonoField.getText().equals(""))){

            System.out.println(nombreField.getText());
            System.out.println(apellidoField.getText());
            System.out.println(dniField.getText());
            System.out.println(telefonoField.getText());

            // Busco a la persona en la base por DNI
            Persona personaExistente = Main.manager.find(Persona.class, Integer.parseInt(this.dniField.getText()));
            System.out.println("EL DNI ES" + this.dniField.getText());

            if (personaExistente == null){ // Si la persona no existe previamente
                // La creo
                Persona p = new Persona(
                        Integer.parseInt(this.dniField.getText()),
                        this.nombreField.getText(),
                        this.apellidoField.getText(),
                        null,
                        Integer.parseInt(this.telefonoField.getText()),
                        false
                );
                this.turno.setTitular(p);

                Main.manager.persist(p);
                Main.manager.getTransaction().commit();
            } else {

                // Si ya existe, simplemente aviso y lo seteo como titular

                // TODO Aca ver si ya esta en el turno o no. Si ya esta, error, ya lo cargaste pelotudo

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Jugador agregado al turno");
                alert.setContentText("La persona ya se encuentra en el sistema.\nJugador agregado");
                alert.showAndWait();

                this.turno.setTitular(personaExistente);

                // TODO: y deberia tambien agregarla a la lista de jugadores

                Main.manager.getTransaction().commit(); // Cierro la conexion
            }

            // Cierro la ventana porque la persona ya fue agregada
            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.close();

        } else { // Si algun dato esta incompleto...

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Datos incompletos");
            alert.setContentText("Los datos están incompletos. Inténtelo de nuevo");
            alert.showAndWait();

        }



    }

    public void initAttributes(Turno t) {
        this.turno = t;
    }
}