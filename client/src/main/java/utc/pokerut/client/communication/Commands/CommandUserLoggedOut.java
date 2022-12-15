package utc.pokerut.client.communication.Commands;

import utc.pokerut.client.communication.Core;
import utc.pokerut.common.messages.LogoutMessage;

public class CommandUserLoggedOut extends ClientCommand<LogoutMessage> {

    public void execute() {
        core.getComCallsData().logoutUser(message.idUser);
    }
}
