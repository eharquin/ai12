package utc.pokerut.client.ihmmain.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
//import utc.pokerut.common.dataclass.Game;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import utc.pokerut.common.dataclass.ClientProfile;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Player;
import utc.pokerut.common.dataclass.StatusEnum;

import static utc.pokerut.common.dataclass.StatusEnum.WAITING_FOR_PLAYER;


public class TablesViewController {
    @FXML
    private TableView<Game> myTableView;

    @FXML
    private TableColumn<Game, String> name;

    @FXML
    private TableColumn<Game, String> pseudo;

    public void initialize() {
        name.setCellValueFactory(new PropertyValueFactory<Game, String>("name"));
        pseudo.setCellValueFactory(Game -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            property.setValue(Game.getValue().getCreator().getPseudo());
            return property;
        });

        myTableView.getItems().setAll(getItemsToAdd());
    }

    private List<Game> getItemsToAdd(){
        List<Game> list = new ArrayList<>();
        ArrayList<Player> playerList = new ArrayList<>();

        ClientProfile client1= new ClientProfile();
        Player player1 =new Player(client1);
        player1.setPseudo("Bob");
        playerList.add(player1);
        Game game1 = new Game();

        game1.setName("Partie1");
        game1.setCreator(player1);
        game1.setPlayers(playerList);
        game1.setNbRounds(2);
        game1.setNbPoints(200); //crédits de départ mais doit être mise minimale
        game1.setStatus(WAITING_FOR_PLAYER);

        list.add(game1);
        System.out.println(game1.getName());
        System.out.println(player1.getPseudo());
        return list;
    }

    
}