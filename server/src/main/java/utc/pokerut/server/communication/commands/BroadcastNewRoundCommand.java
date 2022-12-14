package utc.pokerut.server.communication.commands;

import utc.pokerut.common.dataclass.Player;
import utc.pokerut.common.dataclass.Round;
import utc.pokerut.common.messages.server.MessageType;
import utc.pokerut.server.communication.ClientHandler;
import utc.pokerut.server.communication.Core;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BroadcastNewRoundCommand {
    private final Round round;
    private final List<UUID> players;

    public BroadcastNewRoundCommand(Round round, List<UUID> players) {
        this.round = round;
        this.players = players;
    }
    public void execute(Core core) {
        for (int i = 0; i < players.size(); i++) {
            ClientHandler client = core.getServer().getClientById((UUID) players.get(i));
            client.send(MessageType.ActionPlayed);
            client.send(round);
        }
    }
}
