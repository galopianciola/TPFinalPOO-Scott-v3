package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
        Persona p = new Persona(
                Integer.parseInt(this.dniField.getText()),
                this.nombreField.getText(),
                this.apellidoField.getText(),
                null,
                Integer.parseInt(this.telefonoField.getText()),
                false
                );
        this.turno.setTitular(p);

        Main.manager.getTransaction().begin();
        Main.manager.persist(p);
        Main.manager.getTransaction().commit();


    }

    public void initAttributes(Turno t) {
        this.turno = t;
    }
}