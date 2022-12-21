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
import utc.pokerut.common.dataclass.Player;
import javafx.util.Callback;
import utc.pokerut.common.dataclass.ClientProfile;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Player;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class WaitingRoomController {
    @FXML
    private TableView<Player> myTableView;

    @FXML
    private TableColumn<Player, String> username;

    @FXML
    private TableColumn<Player, Void> status;

    @FXML
    private Button launchGame;

    @FXML
    private Button cancel;

    public void handleLaunchGameButtonAction(ActionEvent event) {
        // launchGame()
    }

    public void handleCancelButtonAction(ActionEvent event) {
        // cancel --> redirection?
    }

    public void initialize() {
        List<Player> list = new ArrayList<>(); // getPlayerlist ?
        


        //Player p1 = new Player();
        //Player p2 = new Player();

        //p1.setPseudo("Bob");
        //p2.setPseudo("Bob2");

        //list.add(p1);
        //list.add(p2);

        username.setCellValueFactory(Player -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            property.setValue(Player.getValue().getPseudo());
            return property;
        });

        status.setCellFactory(col -> new TableCell<Player, Void>() {
            private final HBox container;
            private final Button clearButton;

            {
                Button viewBtn = new Button("Accepter");
                clearButton = new Button("Refuser");

                viewBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //acceptPlayer();
                    }
                });

                clearButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //rejectPlayer();
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
        myTableView.getItems().setAll(list);
    }
}
