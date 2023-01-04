package utc.pokerut.common.messages;

import java.util.UUID;

public class NotifyRejection extends Message{
    public UUID gameID;
    public UUID playerID;

    public NotifyRejection(UUID gameID, UUID playerID) {
        this.gameID = gameID;
        this.playerID = playerID;
    }
}
