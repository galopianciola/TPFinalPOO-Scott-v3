package ui.Areas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Persona;
import models.Turno;
import ui.Main;

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

    @FXML
    private CheckBox titularCheck;

    private Turno turno;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.dniField.setText("");
        this.nombreField.setText("");
        this.apellidoField.setText("");
        this.telefonoField.setText("");
        this.titularCheck.setSelected(false);
    }

    @FXML
    void addButtonClicked(ActionEvent event) {

        // Si la conexion no está activa
        if (!Main.manager.getTransaction().isActive())
            Main.manager.getTransaction().begin(); // La abro


        // Si ningun dato esta incompleto
        if ((!nombreField.getText().equals("")) && (!apellidoField.getText().equals("")) && (!dniField.getText().equals("")) && (!telefonoField.getText().equals(""))){

            // Busco a la persona en la base por DNI
            Persona personaExistente = Main.manager.find(Persona.class, Integer.parseInt(this.dniField.getText()));

            if (personaExistente == null){ // Si la persona no existe previamente
                System.out.println("LA PERSONA NO EXISTE" + this.dniField.getText());

                // La creo
                Persona p = new Persona(
                        Integer.parseInt(this.dniField.getText()),
                        this.nombreField.getText(),
                        this.apellidoField.getText(),
                        Integer.parseInt(this.telefonoField.getText()),
                        false
                );

                this.chequearTitular(p, true);

            } else {
                System.out.println("LA PERSONA EXISTE" + this.dniField.getText());

                // Si ya existe, y encima ya esta agregada como jugador en este turno
                if (this.turno.getJugadores().contains(personaExistente)){

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("El jugador ya existe en el turno");
                    alert.showAndWait();

                } else { // Si ya existe pero no esta en el turno aún
                    // Simplemente aviso y lo agrego

                    Main m = new Main();
                    m.sendAlert(Alert.AlertType.INFORMATION, "Jugador agregado al turno", "La persona ya se encuentra en el sistema.\nJugador agregado");

                    this.chequearTitular(personaExistente, false);

                }
            }
            if (Main.manager.getTransaction().isActive())
                Main.manager.getTransaction().commit(); // Cierro la conexion

        } else { // Si algun dato esta incompleto...
            Main m = new Main();
            m.sendAlert(Alert.AlertType.ERROR, "Datos incompletos", "Los datos están incompletos. Inténtelo de nuevo");
        }
    }

    public void initAttributes(Turno t) {
        this.turno = t;
    }

    public void chequearTitular(Persona persona, boolean persist) {
        /*
        Este metodo chequea si la persona a agregar a la lista de jugadores fue marcada como titular o no,
        y de haber sido marcada, se chequea si el turno no tiene ya un titular existente.
        Recibe un parametro persist que determinara si la persona es nueva o no (si debe ir a la base o no)
         */

        // Si quiere ser titular pero ya hay un titular
        if (this.turno.getTitular() != null && this.titularCheck.isSelected()){
            Main m = new Main();
            if (this.titularCheck.isSelected()) {
                m.sendAlert(Alert.AlertType.ERROR, "Error en el turno", "Ya existe un jugador titular del turno");
                return; // Corto la funcion
            }
        }

        this.turno.setJugadores(persona); // Lo agrego

        if (persist) // Lo persisto de ser necesario
            Main.manager.persist(persona);

        if (this.titularCheck.isSelected()) // Y si el checkbox de titular esta marcado
            this.turno.setTitular(persona); // Lo seteo como titular

        if (Main.manager.getTransaction().isActive())
            Main.manager.getTransaction().commit(); // Cierro la conexion

        // Cierro la ventana porque la persona ya fue agregada
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }

}
