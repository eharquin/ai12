package utc.pokerut.common.messages;

import utc.pokerut.common.dataclass.Round;

public class UpdateNewRound extends Message {
    public Round round;
    public UpdateNewRound(Round newRound) {
        this.round = newRound;
    }
}
