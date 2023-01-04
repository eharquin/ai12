package utc.pokerut.client.communication.commands;

import utc.pokerut.common.messages.GameCreated;

public class CommandGameCreated extends ClientCommand<GameCreated> {
    public void execute() {
        core.getComCallsData().updateGameList(message.game);
    }
}
