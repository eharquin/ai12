package utc.pokerut.server.communication.commands;

import java.lang.reflect.Array;
import java.util.ArrayList;

import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Player;
import utc.pokerut.common.dataclass.ServerProfile;
import utc.pokerut.common.messages.server.MessageType;
import utc.pokerut.server.communication.ClientHandler;
import utc.pokerut.server.communication.Core;

public class InitCommand implements Command {

    public void execute(Core core, ClientHandler client) {
        
        // send games and players to the new player
        ArrayList<Game> games = core.getComCallsData().getWaitingGames();
        ArrayList<ServerProfile> serverProfiles = core.getComCallsData().getConnectedPlayers();
        ArrayList<? extends Player> players = serverProfiles;

        client.send(MessageType.Init);
        client.send(games);
        client.send(players);


        // execute BroadcastNewPlayerCommand
    }
    
}
