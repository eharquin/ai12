package utc.pokerut.server.communication.commands;

import utc.pokerut.common.dataclass.ServerProfile;
import utc.pokerut.common.messages.AskJoinGame;
import utc.pokerut.common.messages.GameCreated;
import utc.pokerut.server.communication.ClientHandler;
import utc.pokerut.server.communication.Core;

import java.util.UUID;

public class CommandAskJoinGame extends ServerCommand<AskJoinGame>{
    @Override
    public void execute() {
        core.getComCallsData().askJoinTableComDataServ(message.game, message.player);
    }
}
