package utc.pokerut.client.ihmmain.controllers;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.*;

import javafx.scene.control.*;
import utc.pokerut.client.ihmmain.ViewNames;
import utc.pokerut.client.ihmmain.listeners.LobbyGameListener;
import utc.pokerut.common.dataclass.ClientProfile;
import javafx.util.Callback;
import utc.pokerut.common.dataclass.ClientProfile;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.ClientProfile;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class WaitingRoomController extends Controller {
    @FXML
    private TableView<ClientProfile> myTableView;

    @FXML
    private TableColumn<ClientProfile, String> username;

    @FXML
    private TableColumn<ClientProfile, Void> status;

    @FXML
    private Button launchGame;

    @FXML
    private Button cancel;

    public LobbyGameListener getLobbyGameListener() {
        return lobbyGameListener;
    }

    private LobbyGameListener lobbyGameListener;

    public void handleLaunchGameButtonAction(ActionEvent event) {
        // launchGame()
    }

    public void handleCancelButtonAction(ActionEvent event) {
        // cancel --> redirection?
    }
    public void incomingJoinRequest(ClientProfile requester)
    {
        myTableView.getItems().add(requester);
    }
    public void acceptJoinRequest(ClientProfile requester)
    {

    }
    public void initialize() {

        username.setCellValueFactory(ClientProfile -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            property.setValue(ClientProfile.getValue().getPseudo());
            return property;
        });

        status.setCellFactory(col -> new TableCell<ClientProfile, Void>() {
            private final HBox container;
            private final Button clearButton;

            {
                Button viewBtn = new Button("Accepter");
                clearButton = new Button("Refuser");

                viewBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //acceptClientProfile();
                    }
                });

                clearButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //rejectClientProfile();
                    }
                });

                container = new HBox(5, viewBtn, clearButton);
                container.setStyle("-fx-alignment: center;");
            }

            @Override
            public void updateItem(Void item, boolean empty) {
                clearButton.disableProperty().unbind();
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(container);
                }
            }
        });
    }
}
