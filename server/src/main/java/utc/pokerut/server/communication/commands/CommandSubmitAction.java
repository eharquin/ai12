package utc.pokerut.server.communication.commands;

import utc.pokerut.common.messages.Command;
import utc.pokerut.common.messages.SubmitAction;

public class CommandSubmitAction extends ServerCommand<SubmitAction> {
    @Override
    public void execute() {
        core.getComCallsData().applyAction(message.playerID, message.gameID, message.action);
    }
}
