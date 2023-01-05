package utc.pokerut.common.interfaces.server;

import utc.pokerut.common.dataclass.*;

import java.util.List;
import java.util.UUID;

public interface DataCallsCom {

    ClientProfile AskForProfile(UUID playerID);

    void Connection(ServerProfile profile, String ip, int port);

    void joinTableRequestDataComServ(UUID playerID, UUID gameID);

    void sendNewRound(Round round, Round newRound, List<UUID> players);

    void sendNextPlayerActions(List<Action> actions, UUID playerID);

    void sendUpdateRound(Round round, List<UUID> players);

    void sendUpdateRoundAndEndResults(Round round, List<UUID> players, List<Result> results);

    void sendUserActionsRefused(UUID playerID, Action action);

    void transmitLeaveMessage(UUID playerID, UUID gameID, int nbCreditFinal, boolean result);

    void addUserToGameDataComServ(Game game, ServerProfile profile, UUID playerID);

    void launchGame(Game game);
}
