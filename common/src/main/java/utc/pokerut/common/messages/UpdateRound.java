package utc.pokerut.common.messages;

import utc.pokerut.common.dataclass.Round;

public class UpdateRound extends Message {

    public Round round;
    public UpdateRound(Round round) {
        this.round = round;
    }
}
