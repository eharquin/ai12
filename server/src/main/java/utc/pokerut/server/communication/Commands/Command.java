package utc.pokerut.server.communication.Commands;

import utc.pokerut.server.communication.ClientHandler;
import utc.pokerut.server.communication.Core;

public interface Command {
    void execute(Core core, ClientHandler client);
}
