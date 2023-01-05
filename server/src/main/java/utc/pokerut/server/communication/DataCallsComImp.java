package utc.pokerut.server.communication;

import utc.pokerut.common.dataclass.*;
import utc.pokerut.common.interfaces.server.DataCallsCom;
import utc.pokerut.common.messages.*;

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

    public void sendNewRound(Round round, Round newRound, List<UUID> players)
    {
        core.getServer().broadcast(new UpdateRoundEnd(round), players);
        core.getServer().broadcast(new UpdateNewRound(newRound), players);
    }

    public void sendNextPlayerActions(List<Action> actions, UUID playerID)
    {

    }

    public void sendUpdateRound(Round round, List<UUID> players)
    {
        core.getServer().broadcast(new UpdateRoundEnd(round), players);
    }

    public void sendUpdateRoundAndEndResults(Round round, List<UUID> players, List<Result> results)
    {
        core.getServer().broadcast(new UpdateRoundEnd(round), players);
        core.getServer().broadcast(new UpdateRoundResult(results), players);
    }

    public void sendUserActionsRefused(UUID playerID, Action action)
    {
        core.getServer().getClientById(playerID).send(new ActionRefused(action));
    }

    public void transmitLeaveMessage(UUID playerID, UUID gameID, int nbCreditFinal, boolean result)
    {

    }

    @Override
    public void addUserToGameDataComServ(Game game, ServerProfile profile, UUID playerID) {
        core.getServer().broadcast(new PlayerJoinGame(game, profile, playerID));


    }

    @Override
    public void launchGame(Game game) {
        core.getServer().broadcastPlayers(new LaunchGame(game), game.getPlayers());
    }
}
