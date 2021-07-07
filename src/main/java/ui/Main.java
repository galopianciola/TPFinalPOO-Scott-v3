package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main extends Application {
    private static Stage stg;

    // Variables de persistencia
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

    public void changeScene(String fxml, String titulo) throws IOException {
        URL url = new File(fxml).toURI().toURL();
        Parent pane = FXMLLoader.load(url);

        //Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
        stg.setTitle(titulo);
    }


    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("Persistencia");
        manager = emf.createEntityManager();

        Area basquet = new Area(1, 300, "Basquet",4,1);
        Area futbol = new Area(2, 400, "Futbol",5,2);

        Encargado Juli = new Encargado(
                41537503,
                "Julian",
                "Wagner",
                "Urquiza 4263",
                220645,
                basquet,
                40000,
                "123j");

        Encargado Galo = new Encargado(
                41104148,
                "Galo",
                "Pianciola",
                "Alsina 3393",
                426487,
                futbol,
                38000,
                "123g");

        Persona p = new Persona(
                41104142,
                "Gonzalo",
                "Kolman",
                "Vte lopez 2123",
                424242,
                false
        );

        Turno t = new Turno(
                1,
                p,
                LocalDate.of(2021,7,10),
                LocalTime.of(2,00),
                Juli,
                false
        );
        Turno t2 = new Turno(
                2,
                p,
                LocalDate.of(2021,7,10),
                LocalTime.of(3,00),
                Galo,
                true
        );

        Turno t3 = new Turno(
                3,
                p,
                LocalDate.of(2021,7,10),
                LocalTime.of(4,00),
                Galo,
                true
        );


        Cancha c = new Cancha(
                3,
                15,
                "Basquet",
                10,
                false,
                1000,
                false,
                500
        );

        Cancha c2 = new Cancha(
                4,
                20,
                "Futbol",
                10,
                false,
                1000,
                false,
                600
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

        launch(args);
    }


}
