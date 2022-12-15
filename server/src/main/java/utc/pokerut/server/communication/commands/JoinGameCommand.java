package utc.pokerut.server.communication.commands;

import utc.pokerut.common.messages.server.MessageType;
import utc.pokerut.server.communication.ClientHandler;
import utc.pokerut.server.communication.Core;

import java.util.UUID;

public class JoinGameCommand {

    private UUID gameID;
    private UUID playerID;
    public JoinGameCommand(UUID playerID, UUID gameID) {
        this.gameID = gameID;
        this.playerID = playerID;
    }

    public void execute(Core core) {
        ClientHandler client = core.getServer().getClientById(playerID);
//        client.send(MessageType.JoinGameRequest);
//        client.send(playerID);
//        client.send(gameID);
    }
    
}
