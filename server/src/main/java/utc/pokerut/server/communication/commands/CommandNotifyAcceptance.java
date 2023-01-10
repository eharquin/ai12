package utc.pokerut.server.communication.commands;

import utc.pokerut.common.messages.Command;
import utc.pokerut.common.messages.NotifyAcceptance;

public class CommandNotifyAcceptance extends ServerCommand<NotifyAcceptance> {
    @Override
    public void execute() {
        core.getComCallsData().newPlayerJoinedComDataServ(message.gameID, message.playerID);
    }
}
