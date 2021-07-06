package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.Encargado;
import models.Persona;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button ingresarButton;
    @FXML
    private Label wrongLogin;


    public void userLogIn(ActionEvent event) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {

        try {
            Main m = new Main();

            if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty())
                wrongLogin.setText("Por favor ingresa tus datos.");
            else {
                Main.manager.getTransaction().begin();
                int dniIngresado = Integer.parseInt(usernameField.getText());
                Encargado e = Main.manager.find(Encargado.class, dniIngresado);
                System.out.println(e);
                Main.manager.getTransaction().commit();

                if (e != null) {
                    if (e.getPassword().equals(passwordField.getText())) {
                        wrongLogin.setText("Ingreso exitoso!");
                        System.out.println("Ingreso exitoso!");
                        m.changeScene("src/main/java/ui/main-menu.fxml", "Scott Menu");
                    } else {
                        wrongLogin.setText("Contraseña incorrecta!");
                        System.out.println("Contraseña incorrecta!");
                    }
                } else {
                    wrongLogin.setText("Usuario inexistente");
                    System.out.println("Usuario inexistente");
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
