package utc.pokerut.server.communication.commands;

import java.util.ArrayList;

import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Player;
import utc.pokerut.common.messages.server.MessageType;
import utc.pokerut.server.communication.ClientHandler;
import utc.pokerut.server.communication.Core;

public class BroadcastUserLoggedInCommand implements Command {
    
    private Game game;

    public BroadcastUserLoggedInCommand(Game game) {
        this.game = game;
    }

    public void execute(Core core, ClientHandler client) {

        ArrayList<ClientHandler> clients = (ArrayList<ClientHandler>)core.getServer().getClients();

        for (ClientHandler other : clients) {
            other.send(MessageType.UserLoggedIn);
            other.send((Player)client.getProfile());
        }
    }
}
