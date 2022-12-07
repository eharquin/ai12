package utc.pokerut.server.communication.commands;

import utc.pokerut.common.dataclass.ServerProfile;
import utc.pokerut.server.communication.ClientHandler;
import utc.pokerut.server.communication.Core;

public class CommandLogin implements Command{
    public void execute(Core core, ClientHandler client)
    {
        ServerProfile profile = (ServerProfile)client.receive();
        client.setProfile(profile);
        core.getComCallsData().saveUser(profile);

        // send init data to new client
        InitCommand command = new InitCommand();
        command.execute(core, client);
    }
}
