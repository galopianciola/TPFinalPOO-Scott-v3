package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import models.Area;
import models.Encargado;
import models.Turno;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrarTurnoController {

    @FXML
    private Button registrarTurnoButton;

    @FXML
    private TableView<?> tablaJugadores;

    @FXML
    private Button agregarJugadorButton;

    @FXML
    private CheckBox pagadoCheck;

    @FXML
    private Label diaLabel;

    private Turno turno;


    @FXML
    void agregarJugadorClicked(ActionEvent event) {
        Main m = new Main();

        try {
            m.changeSceneOnParent("src/main/java/ui/agregarJugador.fxml", "Agregar jugador");
        } catch (Exception e){
           e.printStackTrace();
        }

    }

    @FXML
    void registrarTurnoButtonClicked(ActionEvent event) {
        Area basquet1 = new Area(10, 300, "Basquet",4,1);


        Encargado Juli2 = new Encargado(
                41537502,
                "Julian",
                "Wagner",
                "Urquiza 4263",
                220645,
                basquet1,
                40000,
                "123j");

        this.turno.setTitular(Juli2);
        Main.manager.getTransaction().begin();
        Main.manager.persist(basquet1);
        Main.manager.persist(Juli2);
        Main.manager.persist(this.turno);
        Main.manager.getTransaction().commit();


        System.out.println("Fecha del turno creado "+this.turno.getFecha()+" hora del turno "+this.turno.getHora());

    }

    public void initAtributtes(Turno t){
        this.turno = t;
        this.diaLabel.setText(this.turno.getFecha().toString() + "  /  " + this.turno.getHora().toString());
    }


}
