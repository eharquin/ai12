package utc.pokerut.client.communication.commands;

import utc.pokerut.common.messages.Logout;

public class CommandUserLoggedOut extends ClientCommand<Logout> {

    public void execute() {
        core.getComCallsData().userDisconnected(message.idUser);
    }
}
