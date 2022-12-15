package utc.pokerut.client.communication.Commands;

import utc.pokerut.client.communication.Core;
import utc.pokerut.common.dataclass.Action;
import utc.pokerut.common.dataclass.Round;
import utc.pokerut.common.messages.ActionRefused;

public class CommandActionRefused extends ClientCommand<ActionRefused> {

    public void execute() {
//        core.getComCallsData().sendUserActionRefused(message.action);
    }
}
