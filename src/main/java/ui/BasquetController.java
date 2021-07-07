package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.*;



import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;


public class BasquetController implements Initializable {

    @FXML
    private TableView<Turno> tablaTurnos;

    // Lista que alimenta la tablaTurnos
    private ObservableList<Turno> turnos;

    @FXML
    private TableColumn colID;

    @FXML
    private TableColumn colFecha;

    @FXML
    private TableColumn colHora;

    @FXML
    private TableColumn colTitular;

    @FXML
    private TableColumn colPago;

    @FXML
    private Button registrarTurnoButton;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.turnos = FXCollections.observableArrayList();

        this.colID.setCellValueFactory(new PropertyValueFactory<>("idTurno"));
        this.colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        this.colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        this.colTitular.setCellValueFactory(new PropertyValueFactory<>("DniTitular"));
        this.colPago.setCellValueFactory(new PropertyValueFactory<>("Pagado"));

        Persona p = new Persona(
                41104148,
                "Galo",
                "Pianciola",
                "Alsina",
                434343,
                false
                );


        Turno t = new Turno(
                1,
                    p,
                    LocalDate.of(2021, 7, 8),
                    LocalTime.of(16, 00),
                    null,
                    false
                );

        this.turnos.add(t);
        this.tablaTurnos.setItems(this.turnos);
    }
}
