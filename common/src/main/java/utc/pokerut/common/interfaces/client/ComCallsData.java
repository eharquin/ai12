package utc.pokerut.common.interfaces.client;

import utc.pokerut.common.dataclass.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface ComCallsData {
    public void addUserAtList(Player player);
    public void sendLists(List<Player> players, List<Game> games);
    public  void updateGameList(Game game);
    public void addUserToGameComDataCli(Game gameNewPlayer, Player newPlayer, UUID idUser);
    public void updateTableNewPlayerComDataOthers(Game gameNewPlayer, Player newPlayer, UUID idUser);
    public void newCurrentGame(Game game); // add Game as argument if needed
    public void notifyNextPlayerPossibleActions(List<Action> actions);
    public void updateRound(Round round);
    public void updateGameEnd(Round round, List<Result> results);
    public void updateNewRound(Round round);
    public void userDisconnected(UUID playerID);
}
