package utc.pokerut.client.ihmgame.adapters;

import utc.pokerut.client.ihmgame.GameViewController;
import utc.pokerut.common.dataclass.Player;

import java.util.List;

public class PlayersAdapter {

    private GameViewController gameViewController;

    public PlayersAdapter(GameViewController gameViewController){
        this.gameViewController = gameViewController;
    }

    private List<Player> playersList;

    public List<Player> getPlayersList() {
        return playersList;
    }

    public void setPlayersList(List<Player> playersList) {
        this.playersList = playersList;
    }

    public void initPlayers(List<Player> players){
        //@TODO: Update game scene in GameViewController
    }

    public void addPlayers(List<Player> players){
        //@TODO: Update game scene in GameViewController
    }

    public void removePlayers(List<Player> players){
        //@TODO: Update game scene in GameViewController
    }

}
