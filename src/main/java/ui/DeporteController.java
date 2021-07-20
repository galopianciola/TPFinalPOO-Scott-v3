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
import models.filters.FxDisponibilidad;
import models.filters.FxMantenimiento;


import javax.management.Query;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class DeporteController implements Initializable {

    @FXML
    private TableView<Turno> tablaTurnos;

    // Lista que alimenta la tablaTurnos
    private ObservableList<Turno> turnos;
    private ObservableList<String> horarios;
    private ObservableList<String> areas;

    private List<Turno> listaTurnos;
    private Area area;

    @FXML
    private Label deporteLabel;

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


    @FXML
    private Button verCanchaButton;

    @FXML
    private ComboBox tipoAreaSelect;

    @FXML
    private Button agregarAreaButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Inicializo variables
        this.turnos = FXCollections.observableArrayList();
        this.horarios = FXCollections.observableArrayList();
        this.areas = FXCollections.observableArrayList();

        this.colID.setCellValueFactory(new PropertyValueFactory<>("idTurno"));
        this.colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        this.colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        this.colTitular.setCellValueFactory(new PropertyValueFactory<>("DniTitular"));
        this.colPago.setCellValueFactory(new PropertyValueFactory<>("Pagado"));

        this.diaPicker.setValue(null);
        this.horaSelect.setValue(null);
        this.tipoAreaSelect.setValue(null);

        this.actualizarAreas();//Muestro por pantalla las areas que tiene mi complejo.

        this.area = (Area)Main.manager.createQuery("FROM Area where nombreArea='General' and idEncargado ="+Main.encargadoLogeado.getDni()).getSingleResult();
        this.deporteLabel.setText("Área "+this.area.getDeporte());
    }

    @FXML
    void registrarTurnoButtonClicked(ActionEvent event) throws IOException {

        // Si se seleccionaron ambos datos (que habian sido inicializados en null previamente con setValue)
        if (this.diaPicker.getValue() != null && this.horaSelect.getValue() != null && this.tipoAreaSelect.getValue()!=null ) {
            int idTurnoMax = 0;
            //Obtengo el IdTurno maximo, para generar el siguiente
            if(Main.manager.createQuery("SELECT max(idTurno) FROM Turno").getResultList().get(0)!=null) {
                idTurnoMax = (int) Main.manager.createQuery("SELECT max(idTurno) FROM Turno").getResultList().get(0);
            }
                System.out.println("ID TURNO " + idTurnoMax);
                Turno t = new Turno(idTurnoMax + 1,
                        null,
                        this.diaPicker.getValue(),
                        (LocalTime.parse((String) this.horaSelect.getValue())),
                        Main.encargadoLogeado,
                        false,
                        300);

                //Paso por parametro el turno para que se le puedan añadir los jugadores y setear el titular
                //Tambien paso el area, porque es donde se debe setear el turno.

                URL url = new File("src/main/java/ui/registrarTurno.fxml").toURI().toURL();
                this.changeSceneController(url, t);
                this.actualizarListaTurnos();
                this.actualizarHorarios();

        } else { // Si no se selecciono dia u horario
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error en los datos");
            alert.setContentText("No se seleccionó el Area, el día y el horario para el turno");
            alert.showAndWait();

        }
    }

    @FXML
    void seleccionarFecha()  {
        //Reseteo tablas que muestro por pantalla para volverlas a llenar con la nueva fecha
        this.obtenerAreaSeleccionada();
        this.actualizarListaTurnos();
        this.actualizarHorarios();
    }


    @FXML
    void seleccionarHora(ActionEvent event){

    }

    @FXML
    void seleccionarTipoArea(ActionEvent event)  {
        //Vuelvo a obtener el Area general.
        this.obtenerAreaSeleccionada();
        this.actualizarListaTurnos();
        this.actualizarHorarios();
    }

    @FXML
    void agregarAreaButtonClicked(ActionEvent event) throws IOException {
        if(this.tipoAreaSelect.getSelectionModel().getSelectedItem()!=null){
            URL url = new File("src/main/java/ui/agregarArea.fxml").toURI().toURL();
            this.changeSceneController(url,this.area);
            this.actualizarAreas();

        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error en los datos");
            alert.setContentText("No se seleccionó area sobre la cual se quiere agregar una sub-area");
            alert.showAndWait();
        }
    }

    @FXML
    void backButtonClicked(ActionEvent event){
        Main m = new Main();
        m.backButtonClicked("src/main/java/ui/areas.fxml", "Áreas");
    }

    @FXML
    void verCanchaButtonClicked(ActionEvent event) throws IOException {
        if(this.tipoAreaSelect.getSelectionModel().getSelectedItem()!=null){
        URL url = new File("src/main/java/ui/canchas.fxml").toURI().toURL();
        this.changeSceneController(url,Cancha.class);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error en los datos");
            alert.setContentText("No se seleccionó area de la cual quiere ver las canchas");
            alert.showAndWait();
        }
    }

    public void obtenerAreaSeleccionada(){

        this.area = (Area)Main.manager.createQuery("FROM Area where nombreArea='General' and idEncargado ="+Main.encargadoLogeado.getDni()).getSingleResult();
        String areaSeleccionada = (String)this.tipoAreaSelect.getSelectionModel().getSelectedItem();
        if(!this.area.getAreaXNombre(areaSeleccionada).isEmpty())
            this.area = this.area.getAreaXNombre(areaSeleccionada).get(0);
    }

    public void actualizarListaTurnos(){
        //Reseteo tabla que muestro por pantalla para volverla a llenar con los nuevos turnos filtrados
        this.turnos.clear();
        if(!this.area.getTurnos().isEmpty()){
        if(this.diaPicker.getValue()!=null) {
            List<Turno> turnosFiltrados = new ArrayList<>();
            this.listaTurnos = new ArrayList<>(this.area.getTurnos());
            LocalDate f = this.diaPicker.getValue();

            //Obtengo los turnos filtrados por fecha y por el tipo de area ya filtrado anteriormente.
            for (Turno turno : this.listaTurnos) {
                if (turno.getFecha().equals(f))
                    turnosFiltrados.add(turno);
            }

            this.turnos.addAll(turnosFiltrados);
            this.tablaTurnos.setItems(this.turnos);
            this.tablaTurnos.refresh();
        }
        else{
            //Muestro todos los turnos pertenecientes a la sub area
            List<Turno> turnos = new ArrayList<>();
            this.listaTurnos = new ArrayList<>(this.area.getTurnos());
            for (Turno turno : this.listaTurnos)
                    turnos.add(turno);
            this.turnos.addAll(turnos);
            this.tablaTurnos.setItems(this.turnos);
            this.tablaTurnos.refresh();
        }
        }
    }

    public void actualizarHorarios() {
        FxMantenimiento fxMantenimiento = new FxMantenimiento();
        //Reseteo comboBox de horarios que muestro por pantalla para volverlo a llenar para la nueva fecha y la nueva Area.
        this.horarios.clear();
        //Agrego todos los horarios
        if(!this.area.getCanchasXFiltro(fxMantenimiento).isEmpty()) {
            List<String> horariosDisponibles = new ArrayList<>();
            for (int j = 12; j <= 20; j++) {
                horariosDisponibles.add(j + ":00");
            }
            //Borro los horarios en los cuales ya tengo una reserva
            for (int i = 0; i < this.turnos.size(); i++) {
                for (int j = 0; j < 8; j++) {
                    if (horariosDisponibles.contains(this.turnos.get(i).getHora().toString()))
                        horariosDisponibles.remove(this.turnos.get(i).getHora().toString());
                }
            }
            //Muestro por pantalla los horarios disponibles.
            this.horarios.addAll(horariosDisponibles);
            this.horaSelect.setItems(this.horarios);
        }
    }

    public void actualizarAreas(){
        this.areas.clear();
        this.area = (Area)Main.manager.createQuery("FROM Area where nombreArea='General' and idEncargado ="+Main.encargadoLogeado.getDni()).getSingleResult();
        List<String> areasDisponibles = new ArrayList<>();
        areasDisponibles.addAll(this.area.getNombreSubAreas());
        this.areas.addAll(areasDisponibles);
        this.tipoAreaSelect.setItems(this.areas);
    }

    public void changeSceneController(URL url,Object o) throws IOException {

        FXMLLoader loader = new FXMLLoader(url); //Creo FXMLLoader para poder pasarle el turno y que agregue los jugadores y el titular.
        Parent root = loader.load();

        if(o.getClass().equals(Turno.class)) {
            RegistrarTurnoController controlador = loader.getController();
            controlador.initAttributes((Turno)o, this.area);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Registrar turno");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        }
        if(o.equals(Cancha.class)) {
            CanchasController controlador = loader.getController();
            controlador.initAttributes((Area)this.area);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Canchas");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        }
        if(o.getClass().equals(Area.class)) {
            AgregarAreaController controlador = loader.getController();
            controlador.initAttributes((Area)this.area);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Areas");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        }
    }


}
