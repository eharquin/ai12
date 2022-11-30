package utc.pokerut.client.ihmmain.controllers;

import javafx.beans.property.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import utc.pokerut.client.ihmmain.ViewNames;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends Controller {


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

        errorMessage.setVisible(true);
    }

    public void navigateToCreateProfile() {

        core.getMainController().Navigate(ViewNames.CREATE_PROFILE_VIEW);
    }
    public void navigateToImportProfile() {
        core.getMainController().Navigate(ViewNames.LOGOUT_VIEW);
    }
}