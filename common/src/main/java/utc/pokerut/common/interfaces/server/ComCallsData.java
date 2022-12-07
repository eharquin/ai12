package utc.pokerut.common.interfaces.server;

import utc.pokerut.common.dataclass.Round;
import utc.pokerut.common.dataclass.Action;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Result;
import utc.pokerut.common.dataclass.ServerProfile;

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
    public void modify(ServerProfile profile);
    public void sendUpdateRoundAndEndResult(Round round, ArrayList<Result> results, List<UUID> players);
    public void sendNewRound(Round round, Round newRound, List<UUID> players);
    public void initGameServer(Game newGame);
    public void saveUser(ServerProfile newUser);
    public ArrayList<Game> getWaitingGames();
    public ArrayList<ServerProfile> getConnectedPlayers();
    public void askJoinTableComDataServ(UUID idUser, UUID idGame);
    public void newPlayerJoinedComDataServ(UUID id_user, UUID id_game);
    public void startGame(UUID gameId);
    public void applyAction(UUID idPlayer, UUID idGame, Action action);
}
