package utc.pokerut.common.messages;

import java.util.UUID;

public class JoinGameAsked extends Message{
    public UUID playerID;
    public UUID gameID;

    public JoinGameAsked(UUID playerID, UUID gameID) {
        this.playerID = playerID;
        this.gameID = gameID;
    }
}
