package utc.pokerut.client.communication.Commands;

import utc.pokerut.client.communication.Core;
import utc.pokerut.common.dataclass.Game;

public class CommandGameCreated implements Command {
    public void execute(Core core) {
        Game newGame = (Game) core.getClient().receive();
        core.getComCallsData().updateGameList(newGame);
    }
}
