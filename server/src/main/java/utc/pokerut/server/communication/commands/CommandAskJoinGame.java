package utc.pokerut.server.communication.commands;

import utc.pokerut.common.dataclass.ServerProfile;
import utc.pokerut.server.communication.ClientHandler;
import utc.pokerut.server.communication.Core;

import java.util.UUID;

public class CommandAskJoinGame implements Command{
    public void execute(Core core, ClientHandler client) {

//        UUID playerId = (UUID) client.receive();
//        UUID gameId = (UUID) client.receive();

//        core.getComCallsData().joinTableRequestDataComServ(playerId, gameId);
    }
}
