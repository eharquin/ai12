package utc.pokerut.client.communication.Commands;

import utc.pokerut.client.communication.Core;
import utc.pokerut.common.dataclass.Player;
import utc.pokerut.common.dataclass.Round;

public class CommandActionPlayed implements Command {

    public void execute(Core core) {
        Round round = (Round) core.getClient().receive();
        core.getComCallsData().updateNewRound(round);
    }
}
