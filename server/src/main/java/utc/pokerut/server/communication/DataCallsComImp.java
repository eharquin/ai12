package utc.pokerut.server.communication;

import utc.pokerut.common.dataclass.Action;
import utc.pokerut.common.dataclass.ClientProfile;
import utc.pokerut.common.dataclass.Result;
import utc.pokerut.common.dataclass.Round;
import utc.pokerut.common.dataclass.ServerProfile;
import utc.pokerut.common.interfaces.server.DataCallsCom;

import java.util.List;

public class DataCallsComImp implements DataCallsCom {

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

    public void sendNextPlayerActions(List<Action> actions, int playerID)
    {

    }

    public void sendUpdateRound(Round round, List<Integer> players)
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
