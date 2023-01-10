package utc.pokerut.client.ihmgame.pcl;

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

            switch (actions[0]){
                case "init":
                    ArrayList<Player> init_players = (ArrayList<Player>) event.getNewValue();
                    playersAdapter.initPlayers(init_players);
                    break;
                case "add":
                    ArrayList<Player> add_players = (ArrayList<Player>) event.getOldValue();
                    playersAdapter.addPlayers(add_players);
                    break;
                case "remove":
                    ArrayList<Player> remove_players = (ArrayList<Player>) event.getOldValue();
                    playersAdapter.removePlayers(remove_players);
                    break;
            }
        }
    }

    public PlayersListener(GameViewController gameViewController){
        this.gameViewController = gameViewController;
    }


}
