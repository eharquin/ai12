package utc.pokerut.common.interfaces.server;

import utc.pokerut.common.dataclass.*;

import java.util.List;
import java.util.UUID;

public interface DataCallsCom {

    ClientProfile AskForProfile(int playerID);

    void joinTableRequestComServCreator(int playerID, int gameID);

    void joinTableRequestDataComServ(UUID idUser, UUID idGame);

    void notifyAcceptorComCreatorServ(int playerID, int gameID);

    void sendNewRound(Round newRound, List<Player> players);

    void sendNextPlayerActions(List<Action> actions, UUID playerID);

    void sendUpdateRound(Round round, List<Player> players);

    void sendUpdateRoundAndEndResults(Round round, List<Player> players, List<Result> results);

    void sendUserActionsRefused();

    void transmitLeaveMessage(int playerID, int gameID, int nbCreditFinal, boolean result);
    
    void addUserToGameDataComServ(Game game, Player player,  UUID id_user);
}
