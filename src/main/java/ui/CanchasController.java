package ui;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Area;
import models.Cancha;
import models.Elemento;
import models.Turno;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CanchasController implements Initializable {

    @FXML
    private TableView<Elemento> tablaCanchas;

    @FXML
    private TableColumn colID;

    @FXML
    private TableColumn colEstado;

    @FXML
    private TableColumn colOcupada;

    @FXML
    private TableColumn colRecaudado;

    @FXML
    private Button eliminarCanchaButton;

    @FXML
    private Button backButton;

    @FXML
    private Button agregarCanchaButton;

    @FXML
    private Button modificarCanchaButton;

    @FXML
    private DatePicker diaPicker;

    private Area area;
    private ObservableList<Elemento> canchas;
    private List<Elemento> listaCanchas = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.area = (Area)Main.manager.createQuery("FROM Area where idEncargado ="+Main.encargadoLogeado.getDni()).getSingleResult();
        System.out.println(this.area.getElementos().size());
            this.listaCanchas.addAll(this.area.getElementos()); //Obtengo las canchas del area
            this.canchas = FXCollections.observableArrayList(this.listaCanchas);//Agrego las canchas al observable

            this.colID.setCellValueFactory(new PropertyValueFactory<>("id"));
            this.colEstado.setCellValueFactory(new PropertyValueFactory<>("mantenimiento"));
            this.colOcupada.setCellValueFactory(new PropertyValueFactory<>("isOcupadaXFecha"));
            this.colRecaudado.setCellValueFactory(new PropertyValueFactory<>("getRecaudado"));

            this.tablaCanchas.setItems(this.canchas);
            this.tablaCanchas.refresh();
            this.diaPicker.setValue(null);
        }


    @FXML
    void agregarCanchaButtonClicked(ActionEvent event) {

    }

    @FXML
    void backButtonClicked(ActionEvent event) {
        Main m = new Main();
        m.backButtonClicked("src/main/java/ui/basquet.fxml", "Basquet");
    }

    @FXML
    void eliminarCanchaButtonClicked(ActionEvent event) {

    }

    @FXML
    void modificarCanchaButtonClicked(ActionEvent event) {

    }


}

