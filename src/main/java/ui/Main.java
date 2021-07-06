package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Area;
import models.Encargado;
import models.Persona;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.IOException;
import java.net.URL;

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

        Area basquet = new Area(1, 4, -1);
        Area futbol = new Area(2, 5, -1);

        Encargado Juli = new Encargado(
                3333,
                "Julian",
                "Wagner",
                "Urquiza 4263",
                423242,
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

        manager.getTransaction().begin();
        manager.persist(basquet);
        manager.persist(futbol);
        manager.persist(Juli);
        manager.persist(Galo);
        manager.persist(p);
        manager.getTransaction().commit();

        launch(args);
    }
}
