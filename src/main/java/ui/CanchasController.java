package ui;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Area;
import models.Cancha;
import models.Elemento;
import models.Turno;

import java.io.File;
import java.io.IOException;
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
    private TableColumn colMantenimiento;

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
           // for(Elemento elemento:this.listaCanchas)
            this.canchas = FXCollections.observableArrayList(this.listaCanchas);//Agrego las canchas al observable

            this.colID.setCellValueFactory(new PropertyValueFactory<>("id"));
            this.colMantenimiento.setCellValueFactory(new PropertyValueFactory<>("mantenimiento"));
            this.colOcupada.setCellValueFactory(new PropertyValueFactory<>("ocupada"));
            this.colRecaudado.setCellValueFactory(new PropertyValueFactory<>("getRecaudado"));
            this.tablaCanchas.setItems(this.canchas);
            this.tablaCanchas.refresh();
            this.diaPicker.setValue(null);
        }


    @FXML
    void agregarCanchaButtonClicked(ActionEvent event) throws IOException {
        /*URL url = new File("src/main/java/ui/agregarCancha.fxml").toURI().toURL();

        //Creo FXMLLoader para poder pasarle el turno y que agregue los jugadores y el titular.
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        AgregarJugadorController controlador = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Agregar Cancha");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();*/
        Main m = new Main();
        m.changeSceneOnParent("src/main/java/ui/agregarCancha.fxml","Basq");

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

