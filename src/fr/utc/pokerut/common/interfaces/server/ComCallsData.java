package src.fr.utc.pokerut.common.interfaces.server;

import src.fr.utc.pokerut.common.dataclass.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface ComCallsData {

    public void sendUpdateRound(Round round, List<UUID> Players);
    public void sendUpdateNextRound(Round round, List<UUID> players);
    public void sendNextPlayerActions(List<Action> actions, UUID nextPlayer);
    public void sendUserActionRefused();
    public void sendUpdateRoundAndNewRound(Round round, Round newRound, List<UUID> players);
    public void transmitLeaveMessage (UUID idPlayer, UUID idGame, int nbCreditFinal, boolean result);
    public void joinTableRequestDataComServ(UUID idUser, UUID idGame);
    public void addUserToGameDataComServ(Game gameNewPlayer, Player newPlayer, UUID idUser);
    public void modify(ServerProfile profile);
    public void sendUpdateRoundAndEndResult(Round round, ArrayList<Result> results, List<UUID> players);
    public void sendNewRound(Round round, Round newRound, List<UUID> players);
}
