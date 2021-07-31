package ui.Covid;

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
import models.filters.Turno.FxAnd;
import models.filters.Turno.FxDni;
import models.filters.Turno.FxFecha;
import ui.Main;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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

        // Traigo las areas de la base
        List<Area> areas = (List<Area>) Main.manager.createQuery("FROM Area where nombreArea ='General'").getResultList();
        System.out.println("Cantida de areas "+areas.size());
        //Por cada area, filtro los turnos por dni y por fecha
        FxDni fxDni = new FxDni(this.dniAfectado);
        FxFecha fxFecha = new FxFecha(LocalDate.now().minusDays(3),LocalDate.now());
        FxAnd fxAnd = new FxAnd(fxDni,fxFecha);

        List<Turno> turnosFiltrados = new ArrayList<>();
        for(Area a:areas)
            turnosFiltrados.addAll(a.getTurnosXFiltro(fxAnd));
        System.out.println("Cantidad de turnos filtrados "+turnosFiltrados.size());

        // De los turnos filtrados por dni y rango...
        for (Turno t: turnosFiltrados)
                for(Persona p:t.getJugadores())
                    // Agrego todos los jugadores
                    if(!this.listaAfectados.contains(p) && p.getDni()!=this.dniAfectado)
                        this.listaAfectados.add(p);

        // Seteo finalmente la lista de jugadores afectados en la tabla para que se muestren
        this.tablaAfectados.setItems(this.listaAfectados);
        this.tablaAfectados.refresh();
    }

}