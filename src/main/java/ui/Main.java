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
        CREACION DE PRIMERAS AREAS Y ENCARGADOS
         */


/*
        Area basquet = new Area(1, 300, "Basquet",4,1);
        Area futbol = new Area(2, 400, "Futbol",5,2);

        Encargado Juli = new Encargado(
                41537503,
                "Julian",
                "Wagner",
                220645,
                basquet,
                40000,
                "123j");

        Encargado Galo = new Encargado(
                41104148,
                "Galo",
                "Pianciola",
                426487,
                futbol,
                38000,
                "123g");

        Persona p = new Persona(
                41104142,
                "Gonzalo",
                "Kolman",
                424242,
                false
        );

        Turno t = new Turno(
                1,
                p,
                LocalDate.of(2021,7,10),
                LocalTime.of(14,00),
                Juli,
                false,
                300
        );
        Turno t2 = new Turno(
                2,
                p,
                LocalDate.of(2021,7,10),
                LocalTime.of(17,00),
                Galo,
                true,
                300
        );

        Turno t3 = new Turno(
                3,
                p,
                LocalDate.of(2021,7,10),
                LocalTime.of(20,00),
                Galo,
                true,
                300
        );


        Cancha c = new Cancha(
                3,
                15,
                "Basquet",
                10,
                false,
                false,
                2500
        );

        Cancha c2 = new Cancha(
                4,
                20,
                "Futbol",
                10,
                false,
                false,
                3000
        );

        basquet.setElementos(c);

        c.setTurno(t);
        c.setTurno(t2);
        c2.setTurno(t3);


        manager.getTransaction().begin();

        manager.persist(basquet);
        manager.persist(futbol);
        manager.persist(Juli);
        manager.persist(Galo);
        manager.persist(p);
        manager.persist(t);
        manager.persist(t2);
        manager.persist(t3);
        manager.persist(c);
        manager.persist(c2);
        manager.getTransaction().commit();


        //Busco cosas
        manager.getTransaction().begin();
        Encargado aux = manager.find(Encargado.class,41537503);
        manager.getTransaction().commit();
        System.out.println(aux.toString());


*/


        launch(args);
    }


}
