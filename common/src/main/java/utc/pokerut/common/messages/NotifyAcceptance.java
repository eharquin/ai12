package utc.pokerut.common.messages;

import utc.pokerut.common.messages.Message;

import java.util.UUID;

public class NotifyAcceptance extends Message {
    public  UUID gameID;
    public UUID playerID;

    public NotifyAcceptance(UUID gameID, UUID playerID) {
        this.gameID = gameID;
        this.playerID = playerID;
    }
}
