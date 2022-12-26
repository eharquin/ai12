package utc.pokerut.server.communication;

import utc.pokerut.common.dataclass.*;
import utc.pokerut.common.interfaces.server.DataCallsCom;

import java.util.List;
import java.util.UUID;

public class DataCallsComImp implements DataCallsCom {

    private Core core;
    public DataCallsComImp(Core core) {
        this.core = core;
    }

    public ClientProfile AskForProfile(int playerID)
    {
        return null;
    }

    public void Connection(ServerProfile profile, String ip, int port)
    {

    }

    public void joinTableRequestComServCreator(int playerID, int gameID)
    {

    }

    public void joinTableRequestDataComServ(int playerID, int gameID)
    {

    }

    public void notifyAcceptorComCreatorServ(int playerID, int gameID)
    {

    }

    public void sendNewRound(Round round, Round newRound, List<Integer> players)
    {

    }

    public void sendNextPlayerActions(List<Action> actions, UUID playerID)
    {

    }

    public void sendUpdateRound(Round round, List<Player> players)
    {

    }

    public void sendUpdateRoundAndEndResults(Round round, List<Integer> players, List<Result> results)
    {

    }

    public void sendUserActionsRefused()
    {

    }

    public void transmitLeaveMessage(int playerID, int gameID, int nbCreditFinal, boolean result)
    {
        
    }

}
