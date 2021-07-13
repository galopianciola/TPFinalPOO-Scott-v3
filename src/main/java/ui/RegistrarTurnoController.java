package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Area;
import models.Encargado;
import models.Turno;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrarTurnoController {

    @FXML
    private Button registrarTurnoButton;

    @FXML
    private TableView<?> tablaJugadores;

    @FXML
    private Button agregarJugadorButton;

    @FXML
    private CheckBox pagadoCheck;

    @FXML
    private Label diaLabel;

    private Turno turno;


    @FXML
    void agregarJugadorClicked(ActionEvent event) throws IOException {
        URL url = new File("src/main/java/ui/agregarJugador.fxml").toURI().toURL();

        FXMLLoader loader = new FXMLLoader(url); //Creo FXMLLoader para poder pasarle el turno y que agregue los jugadores y el titular.
        Parent root = loader.load();
        AgregarJugadorController controlador = loader.getController();
        controlador.initAttributes(this.turno);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Agregar Jugador");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

    }

    @FXML
    void registrarTurnoButtonClicked(ActionEvent event) {
        Area basquet1 = new Area(23, 300, "Basquet",4,1);

        Main.manager.getTransaction().begin();
        Main.manager.persist(basquet1);
        Main.manager.persist(this.turno);
        Main.manager.getTransaction().commit();


        System.out.println("Fecha del turno creado "+this.turno.getFecha()+" hora del turno "+this.turno.getHora());

    }

    public void initAttributes(Turno t){
        this.turno = t;
        this.diaLabel.setText(this.turno.getFecha().toString() + "  /  " + this.turno.getHora().toString());
    }


}
