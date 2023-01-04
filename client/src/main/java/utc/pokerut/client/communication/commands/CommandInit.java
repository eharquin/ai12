package utc.pokerut.client.communication.commands;

import java.util.List;

import utc.pokerut.common.dataclass.*;
import utc.pokerut.common.messages.Init;

public class CommandInit extends ClientCommand<Init> {
    public void execute() {
        core.getComCallsData().sendLists((List<Player>) message.players, message.games);
    }
}
