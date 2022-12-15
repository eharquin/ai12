package utc.pokerut.common.messages;

import utc.pokerut.common.dataclass.Action;

public class ActionRefused extends Message {
    public Action action;

    public ActionRefused(Action action) {
        this.action = action;
    }
}
