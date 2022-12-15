package utc.pokerut.common.messages;

import utc.pokerut.common.dataclass.ServerProfile;

public class LoginMessage extends Message {
    public ServerProfile profile;

    public LoginMessage(ServerProfile profile) {
        this.profile = profile;
    }
}
