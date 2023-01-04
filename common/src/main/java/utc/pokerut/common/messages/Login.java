package utc.pokerut.common.messages;

import utc.pokerut.common.dataclass.ServerProfile;

public class Login extends Message {
    public ServerProfile profile;

    public Login(ServerProfile profile) {
        this.profile = profile;
    }
}
