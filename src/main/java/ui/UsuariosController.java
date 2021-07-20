package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Area;
import models.Encargado;
import models.Turno;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UsuariosController implements Initializable {

    @FXML
    private TableView tablaUsuarios;

    @FXML
    private TableColumn colDNI;

    @FXML
    private TableColumn colArea;

    @FXML
    private TableColumn colDeporte;

    @FXML
    private TableColumn colSueldo;

    @FXML
    private Button agregarUsuarioButton;

    @FXML
    private Button backButton;

    @FXML
    private Button borrarUsuarioTurnoButton;

    private ObservableList<Encargado> encargados; // Lista observable que alimenta el tableView

    private List<Encargado> listaEncargados = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.colDNI.setCellValueFactory(new PropertyValueFactory<>("Dni"));
        this.colArea.setCellValueFactory(new PropertyValueFactory<>("IdArea"));
        this.colDeporte.setCellValueFactory(new PropertyValueFactory<>("Deporte"));
        this.colSueldo.setCellValueFactory(new PropertyValueFactory<>("Sueldo"));

        this.actualizarUsuarios();
    }

    @FXML
    void agregarUsuarioButtonClicked(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeSceneOnParent("src/main/java/ui/agregarUsuario.fxml", "Agregar usuario");
        this.actualizarUsuarios();
    }

    @FXML
    void backButtonClicked(ActionEvent event) {
        Main m = new Main();
        m.backButtonClicked("src/main/java/ui/main-menu.fxml", "MenÃº principal");
    }

    @FXML
    void borrarUsuarioButtonClicked(ActionEvent event) {
        Encargado encargadoSeleccionado = (Encargado) this.tablaUsuarios.getSelectionModel().getSelectedItem();

        List<Turno> turnos = (List<Turno>)Main.manager.createQuery("FROM Turno where dniEncargado ="+encargadoSeleccionado.getDni()).getResultList();
        Main.manager.getTransaction().begin();
            for(Turno t:turnos){
                t.setEncargado(null);
                Main.manager.merge(t);
            }
        Main.manager.remove(encargadoSeleccionado);
        Main.manager.getTransaction().commit();

        this.actualizarUsuarios();
    }

    public void actualizarUsuarios(){
        // Obtengo todos los encargados de la base
        this.listaEncargados = (ArrayList<Encargado>)Main.manager.createQuery("FROM Encargado").getResultList();

        // Los agrego al observable
        this.encargados = FXCollections.observableArrayList(this.listaEncargados);

        // Inserto los encargados en la tabla que muestro por pantalla
        this.tablaUsuarios.setItems(this.encargados);
        this.tablaUsuarios.refresh();
    }

}