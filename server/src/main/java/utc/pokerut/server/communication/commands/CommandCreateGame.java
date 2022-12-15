package utc.pokerut.server.communication.commands;

import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Player;
import utc.pokerut.common.messages.GameCreated;
import utc.pokerut.common.messages.server.MessageType;
import utc.pokerut.server.communication.ClientHandler;
import utc.pokerut.server.communication.Core;

import java.util.ArrayList;

public class CommandCreateGame extends ServerCommand<GameCreated> {
    public void execute() {
        core.getComCallsData().initGameServer(message.game);

        // execute BroadCastGameCreatedCommand
        ArrayList<ClientHandler> clients = (ArrayList<ClientHandler>)core.getServer().getClients();

        for (ClientHandler other : clients) {
            other.send(new GameCreated(message.game));
        }
    }
}
