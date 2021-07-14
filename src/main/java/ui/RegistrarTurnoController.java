package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Area;
import models.Encargado;
import models.Persona;
import models.Turno;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrarTurnoController implements Initializable {

    @FXML
    private TableColumn colDni;

    @FXML
    private TableColumn colNombre;

    @FXML
    private TableColumn colApellido;

    @FXML
    private TableColumn colTelefono;

    @FXML
    private Button registrarTurnoButton;

    @FXML
    private TableView tablaJugadores;

    private ObservableList<Persona> listaJugadores;

    @FXML
    private Button agregarJugadorButton;

    @FXML
    private CheckBox pagadoCheck;

    @FXML
    private Label diaLabel;

    @FXML
    private Label titularLabel;

    private Turno turno = new Turno();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.listaJugadores = FXCollections.observableArrayList();

        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        this.colApellido.setCellValueFactory(new PropertyValueFactory<>("Apellido"));
        this.colDni.setCellValueFactory(new PropertyValueFactory<>("Dni"));
        this.colTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
    }

    @FXML
    void agregarJugadorClicked(ActionEvent event) throws IOException {
        URL url = new File("src/main/java/ui/agregarJugador.fxml").toURI().toURL();

        //Creo FXMLLoader para poder pasarle el turno y que agregue los jugadores y el titular.
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        AgregarJugadorController controlador = loader.getController();
        controlador.initAttributes(this.turno);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Agregar Jugador");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

        // Al volver de agregar un jugador, refresco el TableView
        this.listaJugadores = FXCollections.observableArrayList(this.turno.getJugadores());

        for (Persona j: this.turno.getJugadores())
            System.out.println(j.toString());
        System.out.println("Cant jugadores ahora " + this.turno.getJugadores().size());

        this.tablaJugadores.setItems(this.listaJugadores);
        this.tablaJugadores.refresh();
    }

    @FXML
    void registrarTurnoButtonClicked(ActionEvent event) {
        Area basquet1 = new Area(23, 300, "Basquet",4,1);

        Main.manager.getTransaction().begin();
        Main.manager.persist(basquet1);
        Main.manager.persist(this.turno);
        Main.manager.getTransaction().commit();

        // Cierro la ventana porque la persona ya fue agregada
        Stage stage = (Stage) registrarTurnoButton.getScene().getWindow();
        stage.close();

        System.out.println("Fecha del turno creado "+this.turno.getFecha()+" hora del turno "+this.turno.getHora());

    }

    public void initAttributes(Turno t){
        this.turno = t;
        this.diaLabel.setText(this.turno.getFecha().toString() + "\n" + this.turno.getHora().toString());

        if (this.turno.getTitular() == null) // Si aun no hay titular
            this.titularLabel.setText("Titular:\n-");
        else
            this.titularLabel.setText("Titular:\n" + Integer.toString(this.turno.getTitular().getDni()));
    }


}
