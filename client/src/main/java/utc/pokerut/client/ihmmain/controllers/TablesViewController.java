package utc.pokerut.client.ihmmain.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
//import utc.pokerut.common.dataclass.Game;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;


public class TablesViewController {
    //@FXML private TableView<Game> tableView;
    @FXML private TextField nameField;
    @FXML private TextField creatorField;
    @FXML private TextField nbToursFiled;
    @FXML private TextField betField;
    @FXML private TextField playersField;
    @FXML private TextField statusField;


    @FXML
    private void joinGame() throws IOException {
       // addUserToGameDataMainClient(gameNewPlayer, newPlayer, idUser);
        //displayGame(newGame);
    }

    @FXML
    private void createGame() throws IOException {
    
    }

    //construction of the Tables list to display in the board 
    @FXML
    protected void addTable(ActionEvent event) {
        //quelle action faire (ini, ajout, remove?)
        //récupérer tous les games disponibles sur ce serveur dans une liste
        
        //pour chaque game current de cette liste:
    
            /*nameField.setText(current.getName());
            creatorField.setText(current.getCreator());
            nbToursField.setText(current.getNbTours());   
            betField.setText(current.getCrruentBet());
            playersField.setText(current.getPlayers());
            statusField.setText(current.getStatus()); */  
        
    }

    
}