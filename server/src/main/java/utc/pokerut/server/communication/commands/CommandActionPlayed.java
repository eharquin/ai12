package utc.pokerut.server.communication.commands;

import utc.pokerut.common.dataclass.Action;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.messages.ActionPlayed;
import utc.pokerut.server.communication.ClientHandler;
import utc.pokerut.server.communication.Core;

import java.util.UUID;

public class CommandActionPlayed extends ServerCommand<ActionPlayed> {
    public void execute() {
//        core.getComCallsData().applyAction(message.playerId, message.gameId, message.action);
    }
}
