package ui.Areas;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Area;
import models.Cancha;
import models.Elemento;
import ui.Main;

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
    private Button agregarCanchaButton;

    @FXML
    private Button modificarDisponibilidadButton;

    @FXML
    private Button nuevoMesButton;

    private Area area;
    private ObservableList<Elemento> canchas;
    private List<Elemento> listaCanchas = new ArrayList<>();

    public void initAttributes(Area a) {

        this.area=a;
        this.listaCanchas.addAll(this.area.getCanchasXFiltro(null)); //Obtengo las canchas del area
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
        URL url = new File("src/main/java/ui/Areas/agregarCancha.fxml").toURI().toURL();
        this.changeSceneController(url, this.area);
        //Busco en la base para poder actualizar la tabla que muestro por pantalla
        this.actualizarCanchas();
    }

    @FXML
    void eliminarCanchaButtonClicked(ActionEvent event) {
        Cancha c = (Cancha)tablaCanchas.getSelectionModel().getSelectedItem();
        List<Area> areas = (List<Area>) Main.manager.createQuery("FROM Area" ).getResultList();

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
    void modificarDisponibilidadButtonClicked(ActionEvent event) {
        Cancha c = (Cancha)tablaCanchas.getSelectionModel().getSelectedItem();
        if (tablaCanchas.getSelectionModel().getSelectedItem() != null) {
            if (tablaCanchas.getSelectionModel().getSelectedItem().getMantenimiento() == true) {
                Main.manager.getTransaction().begin();
                c.setMantenimiento(false);
                Main.manager.merge(c);
                Main.manager.getTransaction().commit();
            } else {
                Main.manager.getTransaction().begin();
                c.setMantenimiento(true);
                Main.manager.merge(c);
                Main.manager.getTransaction().commit();
            }
            this.actualizarCanchas();
        }
        else{
            Main m = new Main();
            m.sendAlert(Alert.AlertType.ERROR,"No selecciono cancha","No se selecciono una cancha. Inténtelo de nuevo");
        }
    }


    @FXML
    void nuevoMesButtonClicked(ActionEvent event) {
        Main.manager.getTransaction().begin();
        for(Elemento e:area.getElementos()){
            e.reiniciarGananciaMensual();
            Main.manager.merge(e);
            }
        Main.manager.getTransaction().commit();
        this.actualizarCanchas();
    }

    public void actualizarCanchas(){
        this.listaCanchas.clear();
        this.area = (Area)Main.manager.createQuery("FROM Area where id ="+this.area.getId()).getSingleResult();
        this.listaCanchas.addAll(this.area.getCanchasXFiltro(null)); //Obtengo las canchas del area
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
            stage.setTitle("Agregar cancha");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        }

    }
}

