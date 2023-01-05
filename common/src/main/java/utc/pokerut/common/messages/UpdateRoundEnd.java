package utc.pokerut.common.messages;

import utc.pokerut.common.dataclass.Round;

public class UpdateRoundEnd extends Message {
    public Round round;

    public UpdateRoundEnd(Round round) {
        this.round = round;
    }
}
