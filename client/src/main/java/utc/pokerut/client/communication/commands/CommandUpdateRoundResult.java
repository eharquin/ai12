package utc.pokerut.client.communication.commands;

import utc.pokerut.common.messages.Command;
import utc.pokerut.common.messages.UpdateRoundResult;

public class CommandUpdateRoundResult extends ClientCommand<UpdateRoundResult> {
    @Override
    public void execute() {
        core.getComCallsData().displayResults(message.results);
    }
}
