package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Encargado;
import models.Persona;
import models.Turno;

public class AgregarJugadorController {

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

    @FXML
    void addButtonClicked(ActionEvent event) {

        Main.manager.getTransaction().begin();
        // Busco a la persona en la base por DNI
        Persona personaExistente = Main.manager.find(Persona.class, Integer.parseInt(this.dniField.getText()));

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

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Jugador agregado al turno");
            alert.setContentText("La persona ya se encuentra en el sistema.\nJugador agregado");
            alert.showAndWait();

            this.turno.setTitular(personaExistente);

            // TODO: y deberia tambien agregarla a la lista de jugadores

            Main.manager.getTransaction().commit();
        }

        // Cierro la ventana porque la persona ya fue agregada
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();

    }

    public void initAttributes(Turno t) {
        this.turno = t;
    }
}