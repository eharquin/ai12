package utc.pokerut.common.messages;

import utc.pokerut.common.dataclass.Result;
import utc.pokerut.common.dataclass.Round;

import java.util.List;

public class UpdateRoundEnd extends Message {
    public Round round;
    public List<Result> results;

    public UpdateRoundEnd(Round round, List<Result> results) {
        this.round = round;
        this.results = results;
    }
}
