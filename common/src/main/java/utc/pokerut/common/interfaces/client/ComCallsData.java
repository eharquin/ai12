package utc.pokerut.common.interfaces.client;

import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Player;
import utc.pokerut.common.dataclass.ServerProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface ComCallsData {
    public void connexionUser(ServerProfile profile, String IP, int port);
    public void logoutUser(UUID playerId);
    public void modifyUser(ServerProfile profile);
    public void addUserAtList(Player player);
    public void sendLists(List<Player> players, List<Game> games);
    public  void updateGameList(Game game);
    public void addUserToGameComDataCli(Game gameNewPlayer, Player newPlayer, UUID idUser);
    public void updateTableNewPlayerComDataOthers(Game gameNewPlayer, Player newPlayer, UUID idUser);
}
