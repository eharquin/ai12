package utc.pokerut.client.ihmmain.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Parent login;

    @FXML
    private Parent importProfile;

    @FXML
    private Parent createProfile;

    @FXML
    private LoginController loginController;

    public void initialize(){
        loginController.loginListenerProperty().addListener((obs, wasRequested, isNowRequested) -> {
            if (isNowRequested == "login") {
                login.setVisible(false);
            } else if(isNowRequested == "create") {
                login.setVisible(false);
            } else {
                login.setVisible(false);
            }
        });
    }
}