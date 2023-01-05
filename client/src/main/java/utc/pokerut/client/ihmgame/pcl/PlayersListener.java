package utc.pokerut.client.ihmgame.pcl;

import utc.pokerut.client.ihmgame.adapters.PlayersAdapter;
import utc.pokerut.common.dataclass.Player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class PlayersListener implements PropertyChangeListener {

    private PlayersAdapter playersAdapter;

    @Override
    public void propertyChange(PropertyChangeEvent event){

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

    public PlayersListener(PlayersAdapter playersAdapter){
        this.playersAdapter = playersAdapter;
    }


}
