package utc.pokerut.client.communication.commands;

import utc.pokerut.common.messages.Command;
import utc.pokerut.common.messages.UpdateRound;

public class CommandUpdateRound extends ClientCommand<UpdateRound> {
    @Override
    public void execute() {
        core.getComCallsData().updateRound(message.round);
    }
}
