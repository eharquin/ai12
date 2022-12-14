package utc.pokerut.client.communication.Commands;

import utc.pokerut.client.communication.Core;
import utc.pokerut.common.dataclass.Action;
import utc.pokerut.common.dataclass.Round;

public class CommandActionRefused implements Command {

    public void execute(Core core) {
        Action action = (Action) core.getClient().receive();
        core.getComCallsData().sendUserActionRefused(action);
    }
}
