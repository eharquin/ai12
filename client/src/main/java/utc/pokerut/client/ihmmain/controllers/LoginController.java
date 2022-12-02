package utc.pokerut.client.ihmmain.controllers;

import javafx.beans.property.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import utc.pokerut.client.ihmmain.ViewNames;
import utc.pokerut.common.dataclass.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;


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

    public void loginUser(Event event) {
        if(username.getText() != "" && passwordField.getText() != "" && validateIP(serverIp.getText()) && validatePort(port.getText())){
            //if(login(username.getText(),passwordField.getText(), serverIp.getText(), port.getText()) {
                //go to main Menu
                errorMessage.setVisible(false);
                core.getMainController().Navigate(ViewNames.GAME_LIST_VIEW);
            //} else {
                //errorMessage.setVisible(true);
           // }
        } else {
            errorMessage.setVisible(true);
            JOptionPane.showMessageDialog(null,
                    "Hi, In the message box",
                    "PopUp Dialog",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    public boolean validateIP(final String ip) {
        Pattern pattern;
        Matcher matcher;
        String IPADDRESS_PATTERN
                = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        pattern = Pattern.compile(IPADDRESS_PATTERN);
        matcher = pattern.matcher(ip);
        return matcher.matches();
    }

    public boolean validatePort(final String port) {
        Pattern pattern;
        Matcher matcher;
        String PORTADDRESS_PATTERN = "^(\\d+)$";
        pattern = Pattern.compile(PORTADDRESS_PATTERN);
        matcher = pattern.matcher(port);
        return matcher.matches();
    }


    public void navigateToCreateProfile() {
        core.getMainController().Navigate(ViewNames.CREATE_PROFILE_VIEW);
    }

    public void navigateToImportProfile() {
    }
}