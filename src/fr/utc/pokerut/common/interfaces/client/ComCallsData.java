package src.fr.utc.pokerut.common.interfaces.client;

import src.fr.utc.pokerut.common.dataclass.Game;
import src.fr.utc.pokerut.common.dataclass.Player;
import src.fr.utc.pokerut.common.dataclass.ServerProfile;

import java.util.List;
import java.util.UUID;

public interface ComCallsData {
    public void connexionUser(ServerProfile profile, String IP, int port);
    public void logoutUser(UUID playerId);
    public void modifyUser(ServerProfile profile);
    public void addUserAtList(Player player);
    public void sendLists(List<Player> players, List<Game> games);
}
