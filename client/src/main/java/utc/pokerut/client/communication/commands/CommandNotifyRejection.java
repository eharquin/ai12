package utc.pokerut.client.communication.commands;

import utc.pokerut.common.messages.Command;
import utc.pokerut.common.messages.NotifyRejection;

public class CommandNotifyRejection extends ClientCommand<NotifyRejection> {
    @Override
    public void execute() {
        core.getComCallsData().notifyRejectionComMainCli(message.gameID, message.playerID);
    }
}
