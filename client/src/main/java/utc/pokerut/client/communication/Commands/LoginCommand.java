package utc.pokerut.client.communication.Commands;

import utc.pokerut.client.communication.Core;
import utc.pokerut.common.dataclass.ServerProfile;
import utc.pokerut.common.messages.client.MessageType;

public class LoginCommand implements Command {

    public ServerProfile profile;

    public LoginCommand(ServerProfile profile) {
        this.profile = profile;
    }

    public void execute(Core core) {
        core.getClient().send(MessageType.Login);
        core.getClient().send(this.profile);
    }
}
