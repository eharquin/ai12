package utc.pokerut.common.interfaces.client;

import utc.pokerut.common.dataclass.*;

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

    public boolean joinTableRequestComGameCreator(String username, UUID gameID);

    public void notifyRejectionComMainCli(UUID gameID, UUID playerID);

    void updateTableNewPlayerComDataOthers(Game game, ServerProfile profile, UUID playerID);

    void updateRoundEnd(Round round);

    void updateNewRound(Round round);

    void displayResults(List<Result> results);

    void sendUserActionRefused(Action action);

    void newCurrentGame(Game game);
}
