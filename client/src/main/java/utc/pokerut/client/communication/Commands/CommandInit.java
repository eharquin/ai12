package utc.pokerut.client.communication.Commands;

import java.util.ArrayList;

import utc.pokerut.client.communication.Core;
import utc.pokerut.common.dataclass.*;

public class CommandInit implements Command {

    public void execute(Core core) {
        
        ArrayList<Game> games = (ArrayList<Game>) core.getClient().receive();
        ArrayList<Player> players = (ArrayList<Player>) core.getClient().receive();

        core.getComCallsData().sendLists(players, games);
    }
}
