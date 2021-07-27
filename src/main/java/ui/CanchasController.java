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
    private TableColumn colDisponible;

    @FXML
    private TableColumn colOcupada;

    @FXML
    private TableColumn colGananciaMensual;

    @FXML
    private Button eliminarCanchaButton;

    @FXML
    private Button backButton;

    @FXML
    private Button agregarCanchaButton;

    @FXML
    private Button modificarCanchaButton;

    private Area area;
    private ObservableList<Elemento> canchas;
    private List<Elemento> listaCanchas = new ArrayList<>();

    public void initAttributes(Area a) {

        this.area=a;
        this.listaCanchas.addAll(this.area.getCanchas()); //Obtengo las canchas del area
        this.canchas = FXCollections.observableArrayList(this.listaCanchas);//Agrego las canchas al observable
        this.tablaCanchas.setItems(this.canchas);
        this.tablaCanchas.refresh();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.colDisponible.setCellValueFactory(new PropertyValueFactory<>("disponible"));
        this.colOcupada.setCellValueFactory(new PropertyValueFactory<>("ocupada"));
        this.colGananciaMensual.setCellValueFactory(new PropertyValueFactory<>("gananciaMensual"));

    }


    @FXML
    void agregarCanchaButtonClicked(ActionEvent event) throws IOException {
        URL url = new File("src/main/java/ui/agregarCancha.fxml").toURI().toURL();
        this.changeSceneController(url,this.area);
        //Busco en la base para poder actualizar la tabla que muestro por pantalla
        this.actualizarCanchas();

    }

    @FXML
    void backButtonClicked(ActionEvent event) {
        Main m = new Main();
        m.backButtonClicked("src/main/java/ui/deporte.fxml", "Basquet");
    }

    @FXML
    void eliminarCanchaButtonClicked(ActionEvent event) {
        Cancha c = (Cancha)tablaCanchas.getSelectionModel().getSelectedItem();
        List<Area> areas = (List<Area>)Main.manager.createQuery("FROM Area" ).getResultList();

        Main.manager.getTransaction().begin();
        for(Area area:areas){
            if(area.getElementos().contains(c)) {
                area.eliminarElemento(c);
                Main.manager.merge(area);
            }
        }
        Main.manager.remove(c);
        Main.manager.getTransaction().commit();
        this.actualizarCanchas();
    }

    @FXML
    void modificarCanchaButtonClicked(ActionEvent event) {
        Cancha c = (Cancha)tablaCanchas.getSelectionModel().getSelectedItem();
        if(tablaCanchas.getSelectionModel().getSelectedItem().getMantenimiento()==true){
            Main.manager.getTransaction().begin();
            c.setMantenimiento(false);
            Main.manager.merge(c);
            Main.manager.getTransaction().commit();
        }
        else{
            Main.manager.getTransaction().begin();
            c.setMantenimiento(true);
            Main.manager.merge(c);
            Main.manager.getTransaction().commit();
        }
        this.actualizarCanchas();
    }

    public void actualizarCanchas(){
        this.listaCanchas.clear();
        this.area = (Area)Main.manager.createQuery("FROM Area where id ="+this.area.getId()).getSingleResult();
        this.listaCanchas.addAll(this.area.getCanchas()); //Obtengo las canchas del area
        this.canchas.setAll(listaCanchas);
        this.tablaCanchas.setItems(this.canchas);
        this.tablaCanchas.refresh();
    }

    public void changeSceneController(URL url,Object o) throws IOException {

        FXMLLoader loader = new FXMLLoader(url); //Creo FXMLLoader para poder pasarle el turno y que agregue los jugadores y el titular.
        Parent root = loader.load();

        if(o.getClass().equals(Area.class)) {
            AgregarCanchaController controlador = loader.getController();
            controlador.initAttributes((Area)o);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Registrar turno");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        }

    }
}

