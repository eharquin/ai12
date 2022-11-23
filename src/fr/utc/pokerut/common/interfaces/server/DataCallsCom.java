package src.fr.utc.pokerut.common.interfaces.server;

import src.fr.utc.pokerut.common.dataclass.*;

import java.util.List;

public interface DataCallsCom {

    ClientProfile AskForProfile(int playerID);

    void Connection(ServerProfile profile, String ip, int port);

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
