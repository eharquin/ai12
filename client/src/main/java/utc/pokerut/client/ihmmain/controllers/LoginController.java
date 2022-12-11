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

import static java.lang.Integer.parseInt;


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

    public void loginUser(Event event) {
        if(username.getText() != "" && passwordField.getText() != "" && validateIP(serverIp.getText()) && validatePort(port.getText())) {
            try {
                core.getDataInterface().login(username.getText(), passwordField.getText(), serverIp.getText(), parseInt(port.getText()));
                core.getMainController().Navigate(ViewNames.GAME_LIST_VIEW);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Erreur de connection",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Erreur de connection",
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