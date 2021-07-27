package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Area;
import models.Encargado;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AgregarUsuarioController implements Initializable {

    @FXML
    private TextField nombreField;

    @FXML
    private TextField apellidoField;

    @FXML
    private TextField dniField;

    @FXML
    private TextField telefonoField;

    @FXML
    private Button addButton;

    @FXML
    private TextField sueldoField;

    @FXML
    private TextField passwordField;

    @FXML
    private ComboBox area; //Combobox donde se van a mostrar las areas sin encargado

    private ObservableList<String> areas;
    List <Area> areasDisponibles = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.areas = FXCollections.observableArrayList();
        this.areasDisponibles = Main.manager.createQuery("FROM Area where nombreArea='General' and idEncargado = -1").getResultList();
        for(Area a:areasDisponibles)
            this.areas.add(a.getDeporte());
        this.area.setItems(this.areas);
    }

    @FXML
    void addButtonClicked(ActionEvent event) {

        if(this.area.getSelectionModel().getSelectedItem()!=null && !this.nombreField.getText().equals("") && !this.apellidoField.getText().equals("") && !this.dniField.getText().equals("") && !this.telefonoField.getText().equals("") && !this.sueldoField.getText().equals("") && !this.passwordField.getText().equals("")) {
            Area areaSeleccionada=null;
            for(Area a:this.areasDisponibles)
                if(a.getDeporte().equals(this.area.getSelectionModel().getSelectedItem()))
                    areaSeleccionada=a;
            Encargado encargado = new Encargado(
                    Integer.parseInt(this.dniField.getText()),
                    this.nombreField.getText(),
                    this.apellidoField.getText(),
                    Integer.parseInt(this.telefonoField.getText()),
                    areaSeleccionada,
                    Integer.parseInt(this.sueldoField.getText()),
                    this.passwordField.getText()
            );
            if (!Main.manager.getTransaction().isActive())
                Main.manager.getTransaction().begin(); // La abro
            Main.manager.persist(encargado);
            areaSeleccionada.setIdEncargado(encargado.getDni());
            Main.manager.merge(areaSeleccionada);
            Main.manager.getTransaction().commit();
        }
        else{
            Main m = new Main();
            m.sendAlert(Alert.AlertType.ERROR,"Datos incompletos","Los datos están incompletos. Inténtelo de nuevo");
        }

        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }

}