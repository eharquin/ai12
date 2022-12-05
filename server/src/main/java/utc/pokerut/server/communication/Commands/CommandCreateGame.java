package utc.pokerut.server.communication.Commands;

import utc.pokerut.common.dataclass.Game;
import utc.pokerut.server.communication.ClientHandler;
import utc.pokerut.server.communication.Core;

public class CommandCreateGame implements Command{
    public void execute(Core core, ClientHandler client) {
        Game game = (Game) client.receive();
        core.getComCallsData().initGameServer(game);

        // execute BroadCastGameCreatedCommand
        BroadcastGameCreatedCommand command = new BroadcastGameCreatedCommand();
        command.execute(core, client);
    }
}
