package utc.pokerut.client.communication.Commands;

import utc.pokerut.client.communication.Core;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.messages.GameCreated;

public class CommandGameCreated extends ClientCommand<GameCreated> {
    public void execute() {
        core.getComCallsData().updateGameList(message.game);
    }
}
