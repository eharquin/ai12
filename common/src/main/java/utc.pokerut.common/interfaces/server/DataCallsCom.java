package utc.pokerut.common.interfaces.server;

import utc.pokerut.common.dataclass.*;

import java.util.List;
import java.util.UUID;

public interface DataCallsCom {

    ClientProfile AskForProfile(int playerID);

    void joinTableRequestComServCreator(int playerID, int gameID);

    void joinTableRequestDataComServ(int playerID, int gameID);

    void notifyAcceptorComCreatorServ(int playerID, int gameID);

    void sendNewRound(Round round, Round newRound, List<Integer> players);

    void sendNextPlayerActions(List<Action> actions, int playerID);

    void sendUpdateRound(Round round, List<Integer> players);

    void sendUpdateRoundAndEndResults(Round round, List<Integer> players, List<Result> results);

    void sendUserActionsRefused();

    void transmitLeaveMessage(int playerID, int gameID, int nbCreditFinal, boolean result);
}
