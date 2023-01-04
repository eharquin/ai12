package utc.pokerut.client.ihmgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utc.pokerut.client.MainApplication;
import utc.pokerut.client.ihmmain.controllers.PlayerListController;
import utc.pokerut.client.ihmmain.listeners.PlayerListListener;
import utc.pokerut.common.dataclass.Action;
import utc.pokerut.common.dataclass.ActionTypeEnum;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.client.ihmgame.Core;

import javafx.scene.control.TextField;
import java.io.IOException;
import java.security.Timestamp;
import java.util.UUID;

public class PlayGameController extends Controller {

    @FXML
    public TextField betting;
    @FXML
    public TextField raising;

    @FXML
    public void Raise(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/RaiseAction.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();

    }
    @FXML
    public void RaiseValue(ActionEvent event){
        event.consume();
        System.out.println(raising.getText());
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        // Call Data : montant de la relance retiré de ses crédits et ajouté au pot

        // Calling round to get player
        Action action = new Action();
        action.setPlayer(PlayerListController.getCore().getPlayerConnected());
        action.setType(ActionTypeEnum.RAISE);
        action.setBetting(Integer.parseInt(raising.getText()));
        // action.setTimestamp(new Timestamp());

        // sendAction(PlayerListController.getCore().getPlayerConnected().getId(), getCore().getGame().getId(), action);
    }
    @FXML
    public void Call(ActionEvent event){

        // Call Data : montant de la mise retiré de ses crédits et ajouté au pot
        // Si pas dernier joueur et pas eu de relance -> fin du tour ? Est-ce à nous de gérer ça?
        // go to next player
        event.consume();
        Action action = new Action();
        action.setPlayer(PlayerListController.getCore().getPlayerConnected());
        action.setType(ActionTypeEnum.CALL);
        // Récupérer la mise du round en cours Int mise = ...
        // action.setBetting(mise);
        // action.setTimestamp(new Timestamp());

        //sendAction(PlayerListController.getCore().getPlayerConnected().getId(), getCore().getGame().getId(), action);

    }
    @FXML
    public void Check(ActionEvent event){
        // Inform Data, go to next player
        event.consume();
        Action action = new Action();
        action.setPlayer(PlayerListController.getCore().getPlayerConnected());
        action.setType(ActionTypeEnum.CHECK);
        // Récupérer la mise du round en cours Int mise = ...
        action.setBetting(0);
        //action.setTimestamp(new Timestamp());

        //sendAction(PlayerListController.getCore().getPlayerConnected().getId(), getCore().getGame().getId(), action);
    }
    @FXML
    public void Fold(ActionEvent event){
        // Inform Data, go to next player
        event.consume();
        Action action = new Action();
        action.setPlayer(PlayerListController.getCore().getPlayerConnected());
        action.setType(ActionTypeEnum.FOLD);
        // Récupérer la mise du round en cours Int mise = ...
        action.setBetting(0);
        //action.setTimestamp(new Timestamp());

        //sendAction(PlayerListController.getCore().getPlayerConnected().getId(), getCore().getGame().getId(), action);
    }

    @FXML
    public void Bet(ActionEvent event ) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/BetAction.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }

    public void BetValue(ActionEvent event){
        event.consume();
        System.out.println(betting.getText());
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        // Calling Data with the value
        Action action = new Action();
       // Action.setPlayer(PlayerListController.getCore().getPlayerConnected());
       // action.setType(ActionTypeEnum.); // Demander implémentation de BET à DATA
        // Récupérer la mise du round en cours Int mise = ...
       // action.setBetting(mise);
        //action.setTimestamp(new Timestamp());

        //sendAction(PlayerListController.getCore().getPlayerConnected().getId(), getCore().getGame().getId(), action);

    }

    public void updatePlayers(Game game){
        // load every player in the game
        // if player leave, update view
    }

}
