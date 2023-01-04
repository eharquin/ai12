package utc.pokerut.server.communication.commands;

import utc.pokerut.common.messages.Command;
import utc.pokerut.common.messages.NotifyRejection;

public class CommandNotifyRejection extends ServerCommand<NotifyRejection> {
    @Override
    public void execute() {

        core.getServer().getClientById(message.playerID).send(new NotifyRejection(message.gameID, message.playerID));
    }
}
