package utc.pokerut.client.ihmgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utc.pokerut.client.MainApplication;
import utc.pokerut.common.dataclass.Game;

import java.io.IOException;

public class PlayGameController {

    @FXML
    public void Raise(ActionEvent event, Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/RaiseAction.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }
    @FXML
    public void RaiseValue(ActionEvent event){
        // Call Data : montant de la relance retiré de ses crédits et ajouté au pot
    }
    @FXML
    public void Call(ActionEvent event){
        // Call Data : montant de la mise retiré de ses crédits et ajouté au pot
        // Si pas dernier joueur et pas eu de relance -> fin du tour ? Est-ce à nous de gérer ça?
        // go to next player
    }
    @FXML
    public void Check(ActionEvent event){
        // Inform Data, go to next player
    }
    @FXML
    public void Pass(ActionEvent event){
        // Inform Data, go to next player
    }

    @FXML
    void Bet(ActionEvent event, Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/BetAction.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }

    public void BetValue(ActionEvent event){
        // Calling Data
    }

    public void updatePlayers(Game game){
        // load every player in the game
        // if player leave, update view
    }
}
