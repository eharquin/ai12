package utc.pokerut.common.interfaces.client;

import utc.pokerut.common.dataclass.Action;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Player;
import utc.pokerut.common.dataclass.Result;

import java.util.List;
import java.util.UUID;

public interface DataCallsIHMGame {

    public void notifyNewRoundUpdate();

    public void newGameWindow(List<Action> actions);

    public void notifyRoundUpdate();

    public void notifyRoundEndUpdate();

    public void notifyPlayersInGame(UUID player_uuid, UUID game_uuid);

    public boolean joinTableRequestComGameCreator(String username, UUID gameID);

    public boolean notifyRejectionComMainCli(UUID userUuid, UUID gameUuid);

    public void displayResults(List<Result> results);

    public void newPlayerJoinedDataGameOthers(Game gameNewPlayer, Player newPlayer, UUID idUser);

    public void displayPossibleActions(List<Action> actions);
}