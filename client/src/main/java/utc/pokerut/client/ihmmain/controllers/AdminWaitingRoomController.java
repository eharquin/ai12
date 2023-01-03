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
import javafx.fxml.FXML;
import javafx.scene.control.*;
import utc.pokerut.client.ihmmain.ViewNames;
import utc.pokerut.common.dataclass.Player;
import javafx.util.Callback;
import utc.pokerut.common.dataclass.ClientProfile;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Player;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AdminWaitingRoomController extends Controller{

    @FXML
    private TableView<Player> tableLobby;

    @FXML
    private TableColumn<Player, String> participant;

    @FXML
    private TableColumn<Player, Void> action;

    @FXML
    private Button launchGame;

    @FXML
    private Button cancel;

    public void handleLaunchGameButtonAction(ActionEvent event){
        //launchGame()
    }

    public void handleCancelButtonAction(ActionEvent event) {
        // cancel --> redirection
    }

    private List<Player> getItemsToAdd() {
        List<Player> playerList1 = new ArrayList<>();
        
         /* ArrayList<Player> playerList1 = new ArrayList<>();
         /* ArrayList<Player> playerList2 = new ArrayList<>();*/
//UUID id, String pseudo, String avatar, String password, String name, String surname, Date birthdate, String ip, int port
        //ClientProfile client1= new ClientProfile(1,"bib", "bib", "bib", "bib", "bib",02/04/2000,1,1);
        Player player1 =new Player();
        player1.setPseudo("Bob");
        playerList1.add(player1);
         /* Game game1 = new Game();
         * 
         * game1.setName("Partie1");
         * game1.setCreator(player1);
         * game1.setNbMaxPlayers(10);
         * game1.setMinimalBet(2);
         * game1.setPlayers(playerList1);
         * game1.setNbRounds(2);
         * game1.setStatus(WAITING_FOR_PLAYER);
         * 
         * list.add(game1);
         * 
         * System.out.println(game1.getName());
         * System.out.println(player1.getPseudo());
         * 
         * Player player2 =new Player(client1);
         * player2.setPseudo("Eve");
         * playerList2.add(player2);
         * Player player3 =new Player(client1);
         * player3.setPseudo("Oscar");
         * playerList2.add(player3);
         * Game game2 = new Game();
         * 
         * game2.setName("Partie2");
         * game2.setCreator(player3);
         * game2.setNbMaxPlayers(5);
         * game2.setMinimalBet(10);
         * game2.setPlayers(playerList2);
         * game2.setNbRounds(50);
         * game2.setStatus(ON_GOING);
         * 
         * list.add(game2);
         */

        return playerList1;
    }

    public void initialize() {
        setLobby(getItemsToAdd());
    }
    
    public void setLobby(List<Player> playerList) {
        participant.setCellValueFactory(Player -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            property.setValue(Player.getValue().getPseudo());
            return property;
        });
        addButtonToTable();
    }
        
    private void addButtonToTable() {

        Callback<TableColumn<Player, Void>, TableCell<Player, Void>> cellFactory = new Callback<TableColumn<Player, Void>, TableCell<Player, Void>>() {
            @Override
            public TableCell<Player, Void> call(final TableColumn<Player, Void> param) {
                final TableCell<Player, Void> cell = new TableCell<Player, Void>() {

                    private final Button btnAccept = new Button("Accepter");

                    {
                        btnAccept.setOnAction((ActionEvent event) -> {
                            //
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnAccept);
                        }
                    }
                };
                return cell;
            }
        };

        action.setCellFactory(cellFactory);

    }
}
