package utc.pokerut.client.ihmgame.adapters;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import utc.pokerut.client.MainApplication;
import utc.pokerut.client.ihmgame.controllers.GameViewController;
import utc.pokerut.common.dataclass.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayersAdapter {

    private GameViewController gameViewController;

    public PlayersAdapter(GameViewController gameViewController){
        this.gameViewController = gameViewController;
    }

    public void initPlayers(ArrayList<Player> players){
        Platform.runLater(() ->{
            gameViewController.getGame().setPlayers(players);

            Image pioche = new Image(String.valueOf(MainApplication.class.getResource("img/ihmgame/other/pioche.png")));

            for (int i=1 ; i <= players.size() ; i++){
                Player test = new Player();

                // Initialisation des pseudos
                Text pseudo = new Text(players.get(i).getPseudo());
                gameViewController.setPlayerNames(pseudo, i);

                // Initialisation des cartes des joueurs
                for (int j=1 ; j <= 2 ; j++){
                    gameViewController.getPlayerCardsImageArray()[i][j].setImage(pioche);
                }
            }
        });
    }

    public void addPlayers(ArrayList<Player> players){
        Platform.runLater(() ->{
            gameViewController.getGame().setPlayers(players);
        });
    }

    public void removePlayers(ArrayList<Player> players){
        Platform.runLater(() ->{
            gameViewController.getGame().setPlayers(players);

        });
    }

}
