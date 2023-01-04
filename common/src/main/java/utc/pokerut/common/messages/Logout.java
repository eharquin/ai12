package utc.pokerut.common.messages;

import java.util.UUID;

public class Logout extends Message {
    public UUID idUser;

    public Logout(UUID idUser) {
        this.idUser = idUser;
    }
}
