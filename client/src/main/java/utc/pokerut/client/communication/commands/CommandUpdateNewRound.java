package utc.pokerut.client.communication.commands;

import utc.pokerut.common.messages.Command;
import utc.pokerut.common.messages.UpdateNewRound;

public class CommandUpdateNewRound extends ClientCommand<UpdateNewRound> {
    @Override
    public void execute() {
        core.getComCallsData().updateNewRound(message.round);
    }
}
