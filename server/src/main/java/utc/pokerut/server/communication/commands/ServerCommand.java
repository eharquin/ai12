package utc.pokerut.server.communication.commands;

import utc.pokerut.server.communication.ClientHandler;
import utc.pokerut.server.communication.Core;
import utc.pokerut.common.messages.Command;
import utc.pokerut.common.messages.Message;

abstract public class ServerCommand<T extends Message> extends Command<T, Core> {
    private ClientHandler client;

    public void setClient(ClientHandler client) {
        this.client = client;
    }

    public ClientHandler getClient() {
        return this.client;
    }
}
