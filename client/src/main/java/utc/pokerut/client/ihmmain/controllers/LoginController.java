package utc.pokerut.client.ihmmain.controllers;

import javafx.beans.property.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField serverIp;

    @FXML
    private TextField port;

    @FXML
    private Button loginButton;

    @FXML
    private Label errorMessage;

    private final StringProperty loginListener = new SimpleStringProperty();

    public StringProperty loginListenerProperty() {
        return loginListener ;
    }

    public void loginUser(Event event) {
        try {
           // login(username,passwordField)
            // this.showMainMenu();
            System.out.println("called");
            loginListenerProperty().set("login");
        } catch(Exception err) {
            //   errorMessage.setVisible(true);
        }
    }

    public void navigateToCreateProfile() {
        loginListenerProperty().set("create");
    }

    public void navigateToImportProfile() {
        loginListenerProperty().set("import");
    }
}