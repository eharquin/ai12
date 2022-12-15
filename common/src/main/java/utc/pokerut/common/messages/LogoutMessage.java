package utc.pokerut.common.messages;

import java.util.UUID;

public class LogoutMessage extends Message {
    public UUID idUser;

    public LogoutMessage(UUID idUser) {
        this.idUser = idUser;
    }
}
