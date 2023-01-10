package utc.pokerut.client.ihmgame.pcl;

import javafx.scene.text.Text;
import utc.pokerut.client.ihmgame.adapters.PlayersAdapter;
import utc.pokerut.client.ihmgame.controllers.GameViewController;
import utc.pokerut.common.dataclass.Player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class PlayersListener implements PropertyChangeListener {

    private GameViewController gameViewController;

    private PlayersAdapter playersAdapter;

    @Override
    public void propertyChange(PropertyChangeEvent event){

        this.playersAdapter = new PlayersAdapter(gameViewController);

        if(event != null){

            String action = event.getPropertyName();
            String[] actions = action.split("_");

            ArrayList<Player> listPlayers = (ArrayList<Player>) event.getOldValue();
            Player newPlayer = (Player) event.getNewValue();

            // Mise à jour de la liste des joueurs
            gameViewController.getGame().setPlayers(listPlayers);

            // Mise à jour du nombre de joueurs
            Text nbPlayers = new Text(Integer.toString(listPlayers.size()));
            gameViewController.setNbPlayers(nbPlayers);

            switch (actions[0]){
                case "init":
                    playersAdapter.initPlayers(listPlayers);
                    break;
                case "add":
                    playersAdapter.addPlayers(newPlayer);
                    break;
                case "remove":
                    playersAdapter.removePlayers(newPlayer);
                    break;
            }
        }
    }

    public PlayersListener(GameViewController gameViewController){
        this.gameViewController = gameViewController;
    }


}
