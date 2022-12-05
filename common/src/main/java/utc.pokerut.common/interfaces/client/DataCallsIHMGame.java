package utc.pokerut.common.interfaces.client;

import utc.pokerut.common.dataclass.Action;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Player;

import java.util.List;
import java.util.UUID;

public interface DataCallsIHMGame{

    public void notifyNewRoundUpdate();

    public void newGameWindow(List<Action> actions);

    public void notifyRoundUpdate();

    public void notifyRoundEndUpdate();

    public void notifyPlayersInGame(UUID player_uuid, UUID game_uuid);

    public void newPlayerJoinedDataGameOthers(Game gameNewPlayer, Player newPlayer, UUID idUser);
}