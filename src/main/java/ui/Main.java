package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Main extends Application {
    private static Stage stg;

    // Variables de persistencia
    public static Encargado encargadoLogeado;
    public static EntityManager manager;
    public static EntityManagerFactory emf;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stg = primaryStage;
        primaryStage.setResizable(true);

        URL url = new File("src/main/java/ui/login.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        primaryStage.setTitle("Scott Login");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void sendAlert(Alert.AlertType alertType, String title, String content){
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void changeScene(String fxml, String titulo) throws IOException {
        //cambiar pantalla 100%
        URL url = new File(fxml).toURI().toURL();
        Parent pane = FXMLLoader.load(url);

        //Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
        stg.setTitle(titulo);
    }

    public void backButtonClicked(String fxml, String titulo){
        try {
            this.changeScene(fxml, titulo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeSceneOnParent(String fxml, String titulo) throws IOException {
        // abrir sobre la otra pantalla
        URL url = new File(fxml).toURI().toURL();
        Parent pane = FXMLLoader.load(url);

        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setTitle(titulo);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }


    public static void main(String[] args) {

        emf = Persistence.createEntityManagerFactory("Persistencia");
        manager = emf.createEntityManager();

        /*
        CREACION DE Deportes que incluye el complejo y encargado root
         */

/*
        Area basquet = new Area(1, 100, "Basquet", 4, -1, "General");
        Area futbol = new Area(2, 80, "Futbol", 5, -1, "General");
        Area paddle= new Area(3, 95, "Paddle", 3, -1, "General");
        Area tenis = new Area(4, 100, "Tenis", 2, -1, "General");
        Area voley = new Area(5, 110, "Voley", 4, -1, "General");

        Encargado root = new Encargado(
                1,
                "root",
                "root",
                0,
                null,
                0,
                "root");

        Encargado julian = new Encargado(
                41537503,
                "Julian",
                "Wagner",
                220645,
                basquet,
                0,
                "123j");


        manager.getTransaction().begin();
        manager.persist(basquet);
        manager.persist(futbol);
        manager.persist(paddle);
        manager.persist(tenis);
        manager.persist(voley);
        manager.persist(root);
        manager.persist(julian);
        manager.getTransaction().commit();
*/
        launch(args);
    }


}
