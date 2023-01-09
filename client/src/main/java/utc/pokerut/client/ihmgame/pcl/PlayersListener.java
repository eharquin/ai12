package utc.pokerut.client.ihmgame.pcl;

import utc.pokerut.client.ihmgame.adapters.PlayersAdapter;
import utc.pokerut.client.ihmgame.controllers.GameViewController;
import utc.pokerut.common.dataclass.Player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
            List<Player> players = (List<Player>) event.getOldValue();

            switch (actions[0]){
                case "init":
                    playersAdapter.initPlayers(players);
                    break;
                case "add":
                    playersAdapter.addPlayers(players);
                    break;
                case "remove":
                    playersAdapter.removePlayers(players);
                    break;
            }
        }
    }

    public PlayersListener(GameViewController gameViewController){
        this.gameViewController = gameViewController;
    }


}
