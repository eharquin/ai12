package utc.pokerut.client.communication.Commands;

import java.util.ArrayList;
import java.util.List;

import utc.pokerut.client.communication.Core;
import utc.pokerut.common.dataclass.*;
import utc.pokerut.common.messages.InitMessage;

public class CommandInit extends ClientCommand<InitMessage> {
    public void execute() {
        core.getComCallsData().sendLists((List<Player>) message.players, message.games);
    }
}
