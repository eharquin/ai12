package utc.pokerut.common.interfaces.client;

import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Player;
import utc.pokerut.common.dataclass.ServerProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface ComCallsData {
    public void addUserAtList(Player player);
    public void sendLists(List<Player> players, List<Game> games);
    public  void updateGameList(Game game);
    public void userDisconnected(UUID playerID);
}
