package utc.pokerut.common.interfaces.client;

import utc.pokerut.common.dataclass.*;

import java.util.List;
import java.util.UUID;

public interface ComCallsData {

    public void addUserAtList(Player player);

    public void sendLists(List<Player> players, List<Game> games);

    public void updateGameList(Game game);

    public boolean joinTableRequestComGameCreator(String username, UUID gameID);

    public void notifyRejectionComMainCli(UUID gameID, UUID playerID);

    public void newCurrentGame(Game game);

    public void sendUserActionRefused(Action action);

    public void displayResults(List<Result> results);

    public void addUserToGameComDataCli(Game gameNewPlayer, Player newPlayer, UUID idUser);

    public void newCurrentGame(Game game); // add Game as argument if needed

    public void updateTableNewPlayerComDataOthers(Game gameNewPlayer, Player newPlayer, UUID idUser);

    public void notifyNextPlayerPossibleActions(List<Action> actions);

    public void updateRound(Round round);

    public void updateGameEnd(Round round, List<Result> results);

    public void updateNewRound(Round round);

    public void userDisconnected(UUID playerID);

    public void logoutUser(UUID idUser);
}
