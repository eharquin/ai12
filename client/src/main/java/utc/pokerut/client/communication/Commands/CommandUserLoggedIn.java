package utc.pokerut.client.communication.Commands;

import utc.pokerut.client.communication.Core;
import utc.pokerut.common.dataclass.Player;

public class CommandUserLoggedIn implements Command {

    public void execute(Core core) {
        Player newPlayer = (Player) core.getClient().receive();
        core.getComCallsData().addUserAtList(newPlayer);
    }
}
