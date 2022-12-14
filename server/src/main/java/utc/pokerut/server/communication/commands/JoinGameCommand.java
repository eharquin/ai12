package utc.pokerut.server.communication.commands;

import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Player;
import utc.pokerut.common.dataclass.ServerProfile;
import utc.pokerut.common.messages.server.MessageType;
import utc.pokerut.server.communication.ClientHandler;
import utc.pokerut.server.communication.Core;

import java.util.ArrayList;
import java.util.UUID;

public class JoinGameCommand implements Command {

    private UUID gameID;
    private UUID playerID;
    public JoinGameCommand(UUID playerID, UUID gameID) {
        this.gameID = gameID;
        this.playerID = playerID;
    }

    public void execute(Core core, ClientHandler client) {
        client.send(MessageType.JoinGameRequest);
        client.send(playerID);
        client.send(gameID);
    }
    
}
