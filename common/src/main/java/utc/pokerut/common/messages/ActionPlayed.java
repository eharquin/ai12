package utc.pokerut.common.messages;

import utc.pokerut.common.dataclass.Round;

public class ActionPlayed extends Message {
    public Round round;

    public ActionPlayed(Round round) {
        this.round = round;
    }
}
