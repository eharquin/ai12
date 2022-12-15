package utc.pokerut.server.communication.commands;

import utc.pokerut.common.dataclass.ServerProfile;
import utc.pokerut.common.messages.InitMessage;
import utc.pokerut.common.messages.LoginMessage;
import utc.pokerut.server.communication.ClientHandler;
import utc.pokerut.server.communication.Core;

import java.util.ArrayList;

public class CommandLogin extends ServerCommand<LoginMessage> {

    public void execute()
    {
        getClient().setProfile(message.profile);
        core.getComCallsData().saveUser(message.profile);

        // send init data to new client
        getClient().send(new InitMessage(
                core.getComCallsData().getWaitingGames(),
                core.getComCallsData().getConnectedPlayers()
        ));

        // execute BroadcastNewPlayerCommand
        ArrayList<ClientHandler> clients = (ArrayList<ClientHandler>)core.getServer().getClients();

        for (ClientHandler other : clients) {
            if(other != getClient()) {
                other.send(new LoginMessage(getClient().getProfile()));
            }
        }
    }
}
