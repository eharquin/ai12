package utc.pokerut.server.communication.commands;

import utc.pokerut.common.dataclass.Action;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.server.communication.ClientHandler;
import utc.pokerut.server.communication.Core;

import java.util.UUID;

public class CommandActionPlayed implements Command{
    public void execute(Core core, ClientHandler client) {
        UUID playerId = (UUID) client.receive();
        UUID gameId = (UUID) client.receive();
        Action action = (Action) client.receive();

        core.getComCallsData().applyAction(playerId, gameId, action);
    }
}
