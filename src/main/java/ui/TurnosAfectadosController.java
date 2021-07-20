package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Area;
import models.Persona;
import models.Turno;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class TurnosAfectadosController implements Initializable {

    // Lista que alimenta tablaAfectados
    private ObservableList<Persona> listaAfectados;

    @FXML
    private TableView tablaAfectados;

    @FXML
    private TableColumn colDni;

    @FXML
    private TableColumn colNombre;

    @FXML
    private TableColumn colApellido;

    @FXML
    private TableColumn colTelefono;

    @FXML
    private Label infectadoLabel;

    private int dniAfectado;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.colDni.setCellValueFactory(new PropertyValueFactory<>("Dni"));
        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        this.colApellido.setCellValueFactory(new PropertyValueFactory<>("Apellido"));
        this.colTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));

        this.listaAfectados = FXCollections.observableArrayList();
    }

    public void initAttributes(int dniAfectado) {
        this.dniAfectado = dniAfectado;
        this.infectadoLabel.setText("DNI del infectado/a:\n" + this.dniAfectado);

        // Traigo los turnos de hace 2 dias en adelante
        List<Turno> turnosEnRango = (List<Turno>)Main.manager.createQuery("FROM Turno WHERE fecha BETWEEN '" + LocalDate.now().minusDays(2).toString() + "' AND '" + LocalDate.now().toString() + "'").getResultList();

        // De los turnos en el rango...
        for (Turno t: turnosEnRango)
            // Si el jugador infectado participó en el turno
            if (t.participaJugador(this.dniAfectado))
                for(Persona p:t.getJugadores())
                    if(!this.listaAfectados.contains(p) && p.getDni()!=this.dniAfectado)
                        // Agrego todos los jugadores
                        this.listaAfectados.add(p);

        // Seteo finalmente la lista de jugadores afectados en la tabla para que se muestren
        this.tablaAfectados.setItems(this.listaAfectados);
        this.tablaAfectados.refresh();
    }

}