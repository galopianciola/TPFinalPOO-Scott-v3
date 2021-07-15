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
import models.*;


import javax.management.Query;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class BasquetController implements Initializable {

    @FXML
    private TableView<Turno> tablaTurnos;

    // Lista que alimenta la tablaTurnos
    private ObservableList<Turno> turnos;
    private ObservableList<String> horarios;
    private List<Turno> listaTurnos;
    private Area area;
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
    private ComboBox horaSelect;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Obtengo los turnos de la base de datos.

        //Area de la cual esta a cargo el encargado logeado
        this.area = (Area)Main.manager.createQuery("FROM Area where idEncargado ="+Main.encargadoLogeado.getDni()).getSingleResult();

        //List<Turno> turnosBd = (List<Turno>) Main.manager.createQuery("FROM Turno").getResultList(); //Obtengo los turnos de la base de datos.
        this.listaTurnos= new ArrayList<>(area.getTurnos());
        System.out.println(area.getTurnos().size());
        this.turnos = FXCollections.observableArrayList(this.listaTurnos); //Agrego los turnos al observable
        this.horarios = FXCollections.observableArrayList();

        this.colID.setCellValueFactory(new PropertyValueFactory<>("idTurno"));
        this.colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        this.colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        this.colTitular.setCellValueFactory(new PropertyValueFactory<>("DniTitular"));
        this.colPago.setCellValueFactory(new PropertyValueFactory<>("Pagado"));

        this.tablaTurnos.setItems(this.turnos); //Inserto los turnos en la tabla que muestro por pantalla
        this.tablaTurnos.refresh();
        this.diaPicker.setValue(null);
        this.horaSelect.setValue(null);
    }

    @FXML
    void registrarTurnoButtonClicked(ActionEvent event) throws IOException {

        // Si se seleccionaron ambos datos (que habian sido inicializados en null previamente con setValue)
        if (this.diaPicker.getValue() != null && this.horaSelect.getValue() != null){

            URL url = new File("src/main/java/ui/registrarTurno.fxml").toURI().toURL();

            FXMLLoader loader = new FXMLLoader(url); //Creo FXMLLoader para poder pasarle el turno y que agregue los jugadores y el titular.
            Parent root = loader.load();
            RegistrarTurnoController controlador = loader.getController();

            //Obtengo el IdTurno maximo, para generar el siguiente
            int idTurnoMax = (int) Main.manager.createQuery("SELECT max(idTurno) FROM Turno").getResultList().get(0);

            Turno t = new Turno(idTurnoMax+1,
                    null,
                    this.diaPicker.getValue(),
                    (LocalTime.parse((String)this.horaSelect.getValue())),
                    Main.encargadoLogeado,
                    false);

            //Paso por parametro el turno para que se le puedan añadir los jugadores y setear el titular
            controlador.initAttributes(t);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Registrar turno");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();



        } else { // Si no se selecciono dia u horario

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error en los datos");
            alert.setContentText("No se seleccionó día y horario para el turno");
            alert.showAndWait();

        }

    }

    @FXML
    void seleccionarFecha(ActionEvent event) {
        //Reseteo tablas que muestro por pantalla para volverlas a llenar con la nueva fecha
        this.horarios.clear();
        this.turnos.clear();
        this.area = (Area)Main.manager.createQuery("FROM Area where idEncargado ="+Main.encargadoLogeado.getDni()).getSingleResult();
        this.listaTurnos= new ArrayList<>(area.getTurnos());

        LocalDate f = this.diaPicker.getValue();

        //Obtengo los turnos filtrados por fecha
        List<Turno> turnosFiltrados = new ArrayList<>();
        for(Turno turno:this.listaTurnos) {
            if (turno.getFecha().equals(f))
                turnosFiltrados.add(turno);
        }
        //List<Turno> turnosFiltrados = (List<Turno>) Main.manager.createQuery("FROM Turno where fecha='"+f+"'").getResultList();

        this.turnos.addAll(turnosFiltrados);
        this.tablaTurnos.setItems(this.turnos);
        this.tablaTurnos.refresh();

        //Agrego todos los horarios
        List <String> horariosDisponibles = new ArrayList<>();
            for(int j=12;j<=20;j++) {
                horariosDisponibles.add(j+":00");
            }
        //Borro los horarios en los cuales ya tengo una reserva
        for(int i=0; i<this.turnos.size();i++){
            for(int j=0;j<8;j++) {
                if(horariosDisponibles.contains(this.turnos.get(i).getHora().toString()))
                    horariosDisponibles.remove(this.turnos.get(i).getHora().toString());
            }
        }
        //Muestro por pantalla los horarios disponibles.
        this.horarios.addAll(horariosDisponibles);
        this.horaSelect.setItems(this.horarios);

        //this.refrescarTabla();
    }


    @FXML
    void seleccionarHora(ActionEvent event){

    }

    @FXML
    void backButtonClicked(ActionEvent event){
        Main m = new Main();
        m.backButtonClicked("src/main/java/ui/areas.fxml", "Áreas");
    }

    public void refrescarTabla(){
        this.listaTurnos= new ArrayList<>(area.getTurnos());
        this.turnos.clear();
        this.turnos = FXCollections.observableArrayList(listaTurnos); //Agrego los turnos al observable
        this.tablaTurnos.setItems(turnos);
        this.tablaTurnos.refresh();
    }

}
