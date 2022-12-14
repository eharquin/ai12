package utc.pokerut.client.ihmmain.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class LeftPanelController extends Controller {

    public PlayerListController getPlayerListController() {
        return playerListController;
    }

    public boolean isIsPlayerListVisible() {
        return isPlayerListVisible.get();
    }

    public BooleanProperty isPlayerListVisibleProperty() {
        return isPlayerListVisible;
    }

    public void setIsPlayerListVisible(boolean isPlayerListVisible) {
        this.isPlayerListVisible.set(isPlayerListVisible);
    }

    private final BooleanProperty isPlayerListVisible = new SimpleBooleanProperty();
    private @FXML PlayerListController playerListController;


}
