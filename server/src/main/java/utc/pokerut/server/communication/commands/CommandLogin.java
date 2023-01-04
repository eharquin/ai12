package utc.pokerut.server.communication.commands;

import utc.pokerut.common.messages.Init;
import utc.pokerut.common.messages.Login;
import utc.pokerut.server.communication.ClientHandler;

import java.util.ArrayList;

public class CommandLogin extends ServerCommand<Login> {

    public void execute()
    {
        getClient().setProfile(message.profile);
        core.getComCallsData().saveUser(message.profile);

        // send init data to new client
        getClient().send(new Init(
                core.getComCallsData().getWaitingGames(),
                core.getComCallsData().getConnectedPlayers()
        ));

        // execute BroadcastNewPlayerCommand
        ArrayList<ClientHandler> clients = (ArrayList<ClientHandler>)core.getServer().getClients();

        for (ClientHandler other : clients) {
            if(other != getClient()) {
                other.send(new Login(getClient().getProfile()));
            }
        }
    }
}
