package utc.pokerut.server.communication.commands;

import utc.pokerut.common.messages.Logout;

public class CommandLogOut extends ServerCommand<Logout> {
    public void execute() {
        core.getComCallsData().removeUser(message.idUser);
    }
}
