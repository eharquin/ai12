package utc.pokerut.client.communication.commands;

import utc.pokerut.common.messages.UpdateRoundEnd;

public class CommandUpdateRoundEnd extends ClientCommand<UpdateRoundEnd> {
    @Override
    public void execute() {
        core.getComCallsData().updateGameEnd(message.round, message.results);
    }
}
