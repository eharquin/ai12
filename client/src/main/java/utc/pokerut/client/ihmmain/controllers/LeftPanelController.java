package utc.pokerut.client.ihmmain.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class LeftPanelController extends Controller {

    public PlayerListController getPlayerListController() {
        return playerListController;
    }
    private @FXML PlayerListController playerListController;


}
