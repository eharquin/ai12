package utc.pokerut.client.communication.Commands;

import utc.pokerut.client.communication.Core;
import utc.pokerut.common.dataclass.Player;
import utc.pokerut.common.messages.LoginMessage;
import utc.pokerut.common.messages.Message;

public class CommandUserLoggedIn extends ClientCommand<LoginMessage> {

    public void execute() {
        core.getComCallsData().addUserAtList(message.profile);
    }
}
