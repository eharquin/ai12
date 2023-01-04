package utc.pokerut.client.communication.commands;

import utc.pokerut.common.messages.Login;

public class CommandUserLoggedIn extends ClientCommand<Login> {

    public void execute() {
        core.getComCallsData().addUserAtList(message.profile);
    }
}
