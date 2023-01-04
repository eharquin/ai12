package utc.pokerut.server.communication;

import utc.pokerut.common.dataclass.*;
import utc.pokerut.common.interfaces.server.DataCallsCom;
import utc.pokerut.common.messages.JoinGameAsked;

import java.util.List;
import java.util.UUID;

public class DataCallsComImp implements DataCallsCom {

    private Core core;
    public DataCallsComImp(Core core) {
        this.core = core;
    }

    public ClientProfile AskForProfile(UUID playerID)
    {
        return null;
    }

    public ClientProfile AskForProfile(int playerID)
    {
        return null;
    }

    public void Connection(ServerProfile profile, String ip, int port)
    {

    }

    public void joinTableRequestDataComServ(UUID playerID, UUID gameID)
    {
        Game game = core.getComCallsData().getGameById(gameID);
        ClientHandler client = core.getServer().getClientById(game.getCreator().getId());

        client.send(new JoinGameAsked(playerID, gameID));
    }

    public void notifyAcceptorComCreatorServ(UUID playerID, UUID gameID)
    {

    }

    public void sendNewRound(Round round, Round newRound, List<UUID> players)
    {

    }

    public void sendNextPlayerActions(List<Action> actions, UUID playerID)
    {

    }

    public void sendUpdateRound(Round round, List<UUID> players)
    {
//        BroadcastNewRoundCommand command = new BroadcastNewRoundCommand(round, players);
//        command.execute(core);
    }

    public void sendUpdateRoundAndEndResults(Round round, List<UUID> players, List<Result> results)
    {
//        BroadcastNewRoundCommand command = new BroadcastNewRoundCommand(round, players);
//        command.execute(core);
        // TODO: send results to players
    }

    public void sendUserActionsRefused()
    {
        //TODO : send a MessageType.ActionRefused to the client
    }

    public void transmitLeaveMessage(UUID playerID, UUID gameID, int nbCreditFinal, boolean result)
    {
        
    }

}
