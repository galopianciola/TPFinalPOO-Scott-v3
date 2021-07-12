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
import javafx.stage.Stage;
import models.*;


import javax.management.Query;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
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

    @FXML
    private DatePicker diaPicker;

    @FXML
    private ComboBox diaSelect;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Turno> turnosBd = (List<Turno>) Main.manager.createQuery("FROM Turno").getResultList(); //Obtengo los turnos de la base de datos.
        this.turnos = FXCollections.observableArrayList(turnosBd); //Agrego los turnos al observable

        this.colID.setCellValueFactory(new PropertyValueFactory<>("idTurno"));
        this.colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        this.colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        this.colTitular.setCellValueFactory(new PropertyValueFactory<>("DniTitular"));
        this.colPago.setCellValueFactory(new PropertyValueFactory<>("Pagado"));

        this.tablaTurnos.setItems(turnos); //Inserto los turnos en la tabla que muestro por pantalla
    }

    @FXML
    void registrarTurnoButtonClicked(ActionEvent event) {

        Main m = new Main();

        try {
            m.changeScene("src/main/java/ui/registrarTurno.fxml", "Registrar turno");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
