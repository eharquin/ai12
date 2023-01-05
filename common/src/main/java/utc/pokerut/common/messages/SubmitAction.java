package utc.pokerut.common.messages;

import utc.pokerut.common.dataclass.Action;

import java.util.UUID;

public class SubmitAction extends Message {
    public UUID playerID;
    public UUID gameID;
    public Action action;
    public SubmitAction(UUID playerID, UUID gameID, Action action) {
        this.playerID = playerID;
        this.gameID = gameID;
        this.action = action;
    }
}
