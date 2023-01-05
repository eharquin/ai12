package utc.pokerut.common.messages;

import utc.pokerut.common.dataclass.Result;

import java.util.List;

public class UpdateRoundResult extends Message {
    public List<Result> results;
    public UpdateRoundResult(List<Result> results) {
        this.results = results;
    }
}
